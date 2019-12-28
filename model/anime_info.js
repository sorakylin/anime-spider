module.exports = class AnimeInfo {

    constructor(name, imgUrl, score) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.score = score;
    }

    rank(rank) {
        this.rank = rank;
        return this;
    }

    toJson() {
        return JSON.stringify(this);
    }

}