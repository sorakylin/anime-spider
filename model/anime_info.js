module.exports = class AnimeInfo {

    constructor(name, imgUrl, score) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.score = score;
    }

    /**
     * 排行信息
     * @param {*} rank 排行数
     */
    rank(rank) {
        this.rank = rank;
        return this;
    }

    toJson() {
        return JSON.stringify(this);
    }

}