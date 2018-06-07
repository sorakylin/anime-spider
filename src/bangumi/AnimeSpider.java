package bangumi;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AnimeSpider implements Runnable {
	private String url;
	private List<Anime> animes;
	private CountDownLatch cdl;
	private static int id = 0;
	
	
	public AnimeSpider() {
		super();
	}


	public AnimeSpider(String url) {
		super();
		this.url = url;
	}


	public AnimeSpider(String url, List<Anime> animes, CountDownLatch cdl) {
		super();
		this.url = url;
		this.animes = animes;
		this.cdl = cdl;
	}


	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect(url).get();
			//System.out.println(doc);
			Elements elems = doc.select(".browserFull li");
			for(Element e:elems){
				
				String name = e.select("a").text();
				String oldName = e.select(".grey").text();
				String message = e.select("p").first().text();
				Double rating = new Double(e.select(".fade").text());
				String number = e.select(".tip_j").text();
				String imgPath = e.select(".cover").attr("src");
				
				String str = number;
				String regEx="[^0-9]";  
				Pattern p = Pattern.compile(regEx);  
				Matcher m = p.matcher(str);  
				number = m.replaceAll("");
				int num = new Integer(number);
				
				
				animes.add(new Anime(++id,name, oldName, message, rating, num,imgPath));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			cdl.countDown();
		}
		

	}

}
