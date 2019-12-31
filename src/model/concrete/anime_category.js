const AnimeInfo = require('../anime_info');

class BilibiliAnime extends AnimeInfo {

    constructor(name, imgUrl, score) {
        super(name, imgUrl, score ? parseFloat(score.replace('分')).toFixed(1) : -1);
    }

    //限时免费、会员专享等类别
    badge(badge) {
        this.badge = badge;
        return this;
    }

    mediaId(mediaId) {
        this.mediaId = parseInt(mediaId);
        return this;
    }

}

class BangumiAnime extends AnimeInfo {

    constructor(name, imgUrl, score) {
        super(name, imgUrl, score ? parseFloat(score).toFixed(1) : -1);
    }

    id(id) {
        this.id = id;
        return this;
    }

    //评分人数
    raterNum(num) {
        this.raterNum = parseInt(num);
        return this;
    }

    //描述
    desc(desc) {
        this.desc = desc;
        return this;
    }

}


module.exports.BilibiliAnime = BilibiliAnime;
module.exports.BangumiAnime = BangumiAnime;