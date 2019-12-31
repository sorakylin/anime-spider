const excutor = require('./src/spider/excutor');


excutor.registrListener('spider.animerank.bilibili', adminInfos => {
    /*
    array(adminInfos) element:

    BilibiliAnime {
        name: '天降之物Final 我永远的鸟笼',
        imgUrl: 'http://i0.hdslb.com/bfs/bangumi/b00b6cecf7a476bcf9980a231814f46782b3d059.jpg',
        score: '9.8',
        badge: '',
        mediaId: 973,
        rank: 198 
    }
    */

    //do..
    console.log(adminInfos);
});

excutor.registrListener('spider.animerank.bangumi', adminInfos => {
    /*
    array(adminInfos) element:
    
    BangumiAnime {
        name: '少女与战车 剧场版',
        imgUrl: '//lain.bgm.tv/pic/cover/l/76/f3/72266_p0Nxo.jpg',
        score: '8.4',
        id: 'item_72266',
        rank: 48,
        desc: '1话 /  2015年11月21日 / 水島努 / 杉本功',
        raterNum: 3183 
    }
    */

    //do...
    // console.log(adminInfos);
});


excutor.registrListener('spider.animerank.myanimelist', adminInfos => {
    /*
    MALAnime {
        name: 'One Punch Man',
        imgUrl: 'https://cdn.myanimelist.net/images/anime/12/76049.jpg',
        score: 8.68,
        rank: 50,
        desc: 'TV (12 eps)   Oct 2015 - Dec 2015   1,464,576 members' 
    }

    */
    // console.log(adminInfos);
});

// execute spider
// excutor.bilibili();
// excutor.bangumi();
excutor.myanimelist();