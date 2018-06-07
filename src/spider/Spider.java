package spider;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider implements Runnable{
	
	private String url;
	private List<Film> films;
	private CountDownLatch cdl;
	
	
	
	
	public Spider() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//主要接收传过来的闭锁和集合还有要扒的URL
	public Spider(String url, List<Film> films,CountDownLatch cdl) {
		super();
		this.url = url;
		this.films = films;
		this.cdl = cdl;
	}

	public void run(){
		try {
			//获取指定URL的文档对象
			Document doc = Jsoup.connect(url).timeout(10000).get();
			//获得根节点
			Elements elems = doc.select(".grid_view .item");
			//遍历根节点里的子节点
			for(Element e :elems){
				String id = e.select("em").text();
				String path = e.select(".pic img").attr("src");
				String title = e.select(".title").text();
				String quto = e.select(".quote .inq").text();
				String rating = e.select(".star .rating_num").text();
				String info = e.select(".bd p").first().text();
				
				Film file = new Film(id,title,info,rating, quto, path);
				films.add(file);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			cdl.countDown();
		}
		
	}
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}
	
	
	
	
}
