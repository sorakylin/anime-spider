//bilibili的排行榜链接，是个api接口请求
const BILIBILI_ANIME_RANKING_URL = 'https://api.bilibili.com/pgc/season/index/result'

//bangumi的排行链接， 由于bangumi是服务端渲染，只能拿到 html 页面了
const BANGUMI_ANIME_RANKING_URL = 'https://bangumi.tv/anime/browser'

const MAL_PAGE_URL = 'https://myanimelist.net/topanime.php'


exports.getMyAnimeListPageUrl = function (pageNum) {
    let param = {
        limit: (pageNum - 1) * 50
    };

    return getUrl(MAL_PAGE_URL, param)

}

exports.getBilibiliRankingApiUrl = function (page, pagesize) {
    let param = {
        season_version: -1,
        area: -1,
        is_finish: -1,
        copyright: -1,
        season_status: -1,
        season_month: -1,
        year: -1,
        style_id: -1,
        order: 4,
        st: 1,
        sort: 0,
        season_type: 1,
        type: 1,
        page: page,
        pagesize: pagesize
    };

    return getUrl(BILIBILI_ANIME_RANKING_URL, param);
}

exports.getBangumiRankingApiUrl = function (page) {
    let param = {
        sort: 'rank',
        page: page
    };

    return getUrl(BANGUMI_ANIME_RANKING_URL, param);
}





function getUrl(url, param) {
    return url += (url.indexOf('?' < 0) ? '?' : '&') + paramHandler(param);
}

function paramHandler(data) {
    let url = ''
    for (var i in data) {
        let value = data[i] !== undefined ? data[i] : ''
        url += `&${i}=${encodeURIComponent(value)}`
    }
    return url ? url.substring(1) : ''
}