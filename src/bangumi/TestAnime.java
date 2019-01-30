package bangumi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestAnime {

	public static void main(String[] args) {
		System.out.println("开始检索bangumi动画排行榜...");
		//要传的集合
		List<Anime> animes = new ArrayList<Anime>();
		//闭锁
		CountDownLatch cdl = new CountDownLatch(191);
		//单个线程的线程池，为了集合中的数据顺序排列
		ExecutorService pool = Executors.newSingleThreadExecutor();
		for(int i = 1 ; i <= 191 ;i++){
			String url = "http://bangumi.tv/anime/browser?sort=rank&page="+i;
			pool.execute(new AnimeSpider(url,animes,cdl));
		}
		//关池子
		pool.shutdown();
		
		
		try {
			//开锁
			cdl.await();
			System.out.println("检索bangumi动画排行榜完毕，已全部储存...");
			System.out.println("正在准备输出所有排行信息:");
			System.out.print("5\t");
			Thread.sleep(1000);
			System.out.print("4\t");
			Thread.sleep(1000);
			System.out.print("3\t");
			Thread.sleep(1000);
			System.out.print("2\t");
			Thread.sleep(1000);
			System.out.print("1\t");
			Thread.sleep(1000);
			
			//遍历
			for (Anime a : animes) {
			    System.out.println(a); 
			}
			
			
			//写入txt文件
			//WriteAnimeTXT.writeAnime(animes);
			
			//写入XML文件
			//WriteAnimeXML.writeAnime(animes);
			
			//写入数据库
			//MybaseInsert.insert(animes);
			
			//下载图片
			//writeImage(animes);
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//下载图片的方法
	public static void writeImage(List<Anime> animes){
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for (Anime a : animes) {
			//调用ImgLoader线程传个Anime对象过去
			pool.execute(new ImgLoader(a));
		}
		pool.shutdown();
	}
}

