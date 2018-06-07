# Spider
码代码的，哪能没写过爬虫？  扒了豆瓣top250练手，然后兴趣使然扒了bangumi的动画排行，本来工程里有图片的，有点大我给删了  
要是要图片的话就下载下来自己运行一遍吧，注释非常详细，想研究代码的能轻易吃透

## 功能分析
* 实现了对豆瓣、bangumi网站的html获取    `使用了多线程、闭锁`  
* 获取html后通过Document解析得到其里面的内容    `核心jar包:jsoup`  
* 能够将数据保存成XML文件、txt文件、并持久化  
* 能够下载其对应的图片    `核心jar包:okhttp3`  

## 图片展示
![anime](https://pyb001.oss-cn-shenzhen.aliyuncs.com/Spider/bangumiAnime.jpg?x-oss-process=style/blogImg)  
  
    
      
      ---------------------------------------------分割线---------------------------------------------
      
        
          
![douban](https://pyb001.oss-cn-shenzhen.aliyuncs.com/Spider/douban250.jpg?x-oss-process=style/blogImg)
