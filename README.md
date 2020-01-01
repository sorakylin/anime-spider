# Anime spider
  
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
* B站的没有评分的动画 score 字段会展示为 -1
* myanimelist 拥有超过10000部动画数据, 爬到后面可能出现分数与排名不匹配的现象，即有可能出现分数高一点的排名反而在下面的情况, 这是由于 myadminlist 的rank规则导致的


<br>
<br>


### 使用方式

*注: 操作均在 main.js 中进行*
> 1、 注册你需要的爬虫事件监听器  
> 2、 在监听器中处理爬虫收集的数据 (如打印,保存到文件/DB,数据分析..等)
> 3、 执行具体的爬虫

<br>

*main.js*
```JavaScript
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
// excutor.myanimelist();

```

<br>
<br>

### 能获取到的数据
* bilibili 的番剧排行榜-评分正序
    * 事件 `spider.animerank.bilibili`
* bangumi 的动画排行榜-评分正序
    * 事件 `spider.animerank.bangumi`
* myadminlist 的动画排行榜-评分正序
    * 事件 `spider.animerank.myanimelist`

<br>
<br>

### 依赖 
* superagent: HTTP请求
* cheerio:    Dom解析