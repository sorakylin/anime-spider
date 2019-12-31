const request = require('superagent');
const cheerio = require('cheerio');
const resource = require('./resource');
const {
    BilibiliAnime,
    BangumiAnime,
    MALAnime
} = require('../model/concrete/anime_category');
const EventEmitter = require('events').EventEmitter;

const emitter = new EventEmitter();

/**
 * myanimelist 拥有超过10000部动画,每页固定50部
 * 爬到后面，可能出现分数与排名不匹配的情况，即有可能出现分数高一点的排名反而在下面的场景
 * 这是由于 myadminlist 的rank规则导致的
 * 
 * 并且到更后面，因为评分人数过少，甚至有些动画没分了(到 146xx 为止) 所以设置一个 15000 的界限
 */
async function myanimelist(pageNum = 1) {
    if (pageNum > (15000 / 50)) {
        return;
    }

    let url = resource.getMyAnimeListPageUrl(pageNum);

    request.get(url)
        .retry(5)
        .timeout(5000)
        .end((err, result) => {

            if (err) {
                console.log(err);
                return;
            }

            // MALAnime[] 
            let animeInfos = parseMALModel(result.text);
            emitter.emit('spider.animerank.myanimelist', animeInfos);

            myanimelist(++pageNum);
        })
}

/**
 * B站每页固定五十个
 */
async function bilibili(pageNum = 1, pageSize = 50) {

    //排行
    let rank = (pageNum - 1) * pageSize;

    let url = resource.getBilibiliRankingApiUrl(pageNum, pageSize);

    request.get(url).retry(5).timeout(5000).end((err, result) => {

        let animeInfos = new Array();

        if (err) {
            console.log(err);
            return;
        }

        let resultJson = JSON.parse((result.text));

        if (resultJson.code != 0) return;

        let adminList = resultJson.data.list;

        for (let animeInfo of adminList) {
            let biAnime = new BilibiliAnime(animeInfo.title, animeInfo.cover, animeInfo.order)
                .badge(animeInfo.badge)
                .mediaId(animeInfo.media_id)
                .rank(++rank);

            animeInfos.push(biAnime);
        }

        emitter.emit('spider.animerank.bilibili', animeInfos);

        if (resultJson.data.has_next == 1) {
            bilibili(++pageNum, pageSize);
        }

    })
}

/**
 * bangumi 排行榜单页数据固定为24个
 * rank 可以从dom中获取
 */
async function bangumi(pageNum = 1, pageCount) {

    if (pageCount && pageNum > pageCount) return;
    if (!pageCount) {
        console.log('get rank count num');
        let rankCount = await findBangumiRankNum();
        pageCount = rankCount % 24 == 0 ? rankCount / 24 : (rankCount - (rankCount % 24)) / 24;
    }

    let url = resource.getBangumiRankingApiUrl(pageNum);

    request.get(url)
        .retry(5)
        .timeout(5000)
        .end((err, result) => {

            if (err) {
                console.log(err);
                return;
            }

            // BangumiAnime[] 
            let animeInfos = parseBangumiModel(result.text);
            emitter.emit('spider.animerank.bangumi', animeInfos);

            bangumi(++pageNum, pageCount);
        })
}

/**
 * 取 bangumi 番剧的 rank 数量
 */
async function findBangumiRankNum() {

    //从雷锋的故事获取 bangumi 最低 rank
    let requestUrl = 'https://bangumi.tv/subject/6476';

    let rank;

    await request.get(requestUrl)
        .retry(5).timeout(5000)
        .then(res => {
            let $dom = cheerio.load(res.text);
            let rankText = $dom('#panelInterestWrapper small.alarm').text();
            rank = parseInt(rankText.replace('#', ''));
        }).catch(err => {
            rank = 5000; //默认值
        });

    return rank;

}

function parseBangumiModel(domText) {
    let $dom = cheerio.load(domText);

    let result = new Array();

    let $admins = $dom('#browserItemList>li');

    if ($admins.length == 0) return result;

    $admins.each((index, ele) => {
        let $ele = $dom(ele);

        // bangumi id
        let id = $ele.attr('id');
        //分数
        let score = $ele.find('small.fade').text();
        //图片地址
        let imgUrl = $ele.find('img').attr('src');
        imgUrl = imgUrl ? imgUrl.replace('/s/', '/l/') : imgUrl;
        //动画名
        let animeName = $ele.find('.inner>h3>a').text();
        //排名
        let rank = $ele.find('.rank').remove('small').text().replace('Rank ', '');
        //描述
        let desc = $ele.find('p.info').text().replace('\n', '').trim();
        //评分人数
        let raterNum = $ele.find('span.tip_j').text().replace('人评分', '').replace('(', '');

        //一个动画实体
        let singleAnime = new BangumiAnime(animeName, imgUrl, score)
            .id(id)
            .rank(rank)
            .desc(desc)
            .raterNum(raterNum);

        result.push(singleAnime);
    })

    return result;
}


function parseMALModel(domText) {
    let $dom = cheerio.load(domText);

    let result = new Array();

    let $admins = $dom('table.top-ranking-table>tbody>tr.ranking-list');

    if ($admins.length == 0) return result;

    $admins.each((index, ele) => {
        let $ele = $dom(ele);
        let $tds = $ele.find('td');

        //排名
        let rank = $tds.eq(0).find('span').text();

        let imgUrl = $tds.eq(1).find('a').first().find('img').attr('data-src').replace('/r/50x70', '').split("?")[0];

        let name = $tds.eq(1).find('div.detail>div').eq(1).find('a').first().text();

        let desc = $tds.eq(1).find('div.detail').children('div').eq(2).text()
            .replace(new RegExp('\n', 'gm'), '').replace(new RegExp('        ', 'g'), '   ').trim();

        let score = $tds.eq(2).find('span').text();

        let malAnime = new MALAnime(name, imgUrl, score)
            .rank(rank).desc(desc);

        result.push(malAnime);
    });

    return result;
}

module.exports.bilibili = bilibili;
module.exports.bangumi = bangumi;
module.exports.myanimelist = myanimelist;
module.exports.registrListener = function (key, listener) {
    emitter.on(key, listener);
};