# Spider
  
nodejs爬虫, 动画相关信息 

<br>
<br>


### 快速开始

```
git clone https://github.com/skypyb/anime-spider.git
npm install
node main.js
```

<br>
<br>


### 说明
* 采用事件机制监听爬虫数据, 方便做聚合等操作 

```nodejs
const excutor = require('./spider/excutor');


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
    // console.log(adminInfos);
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


// execute spider
excutor.bilibili();
excutor.bangumi();
```

<br>
<br>

### 能获取到的数据
* bilibili 的番剧排行榜-评分正序
    * 事件 `spider.animerank.bilibili`
* bangumi 的动画排行榜-评分正序
    * 事件 `spider.animerank.bangumi`

<br>
<br>

### 依赖 
* `superagent`: HTTP请求
* `cheerio`:    Dom解析