package spider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {
		//要传的集合
		List<Film> films = new ArrayList<Film>();
		//闭锁
		CountDownLatch cdl = new CountDownLatch(10);
		//单个线程的线程池，为了集合中的数据顺序排列
		ExecutorService pool = Executors.newSingleThreadExecutor();
		
		//把十页的数据都扒下来
		for(int i = 0 ; i <= 250 ; i += 25){
			pool.execute(new Spider
				("https://movie.douban.com/top250?start="+i+"&filter=",films,cdl));
		}
		//关池
		pool.shutdown();
		try {
			//开锁
			cdl.await();
			
			//遍历集合
			for(Film f : films){
				System.out.println(f);
			}
			
			//转换成XML
			MyWriteXML.writeXML(films);
			
			//下载图片
			ExecutorService pool2 = Executors.newFixedThreadPool(8);
			for (Film film : films) {
				pool2.execute(new ImgLoader(film));
			}
			pool2.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void downImage(List<Film> films){
		ExecutorService pool = Executors.newFixedThreadPool(8);
		for(Film f : films){
			pool.execute(new ImgLoader(f));
		}
		pool.shutdown();
	}

}
