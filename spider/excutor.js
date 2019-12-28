const superagent = require('superagent');
const cheerio = require('cheerio');
const resource = require('./resource');
const anime = require('../model/concrete/anime_category');

exports.start = function start(pageNum = 1, pageSize = 50) {

    //排行
    let rank = (pageNum - 1) * pageSize;

    let url = resource.getBilibiliRankingApiUrl(pageNum, pageSize);

    superagent.get(url).end((err, result) => {

        if (err) {
            console.log(err);
            return;
        }

        let resultJson = JSON.parse((result.text));

        if (resultJson.code != 0) return;

        let adminList = resultJson.data.list;

        for (let animeInfo of adminList) {
            let biAnime = new anime.BilibiliAnime(animeInfo.title, animeInfo.cover, animeInfo.order)
                .badge(animeInfo.badge).mediaId(animeInfo.media_id).rank(++rank);

            console.log(biAnime.toJson());
        }

        if (resultJson.data.has_next == 1) {
            start(++pageNum, pageSize);
        }

    })
}


function bilibili() {

}