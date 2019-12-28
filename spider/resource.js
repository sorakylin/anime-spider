const BILIBILI_RANKING_URL = 'https://api.bilibili.com/pgc/season/index/result'


exports.getBilibiliRankingApiUrl = function(page, pagesize) {
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
        type: 1
    };

    param.page = page;
    param.pagesize = pagesize;

    return getUrl(BILIBILI_RANKING_URL, param);
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