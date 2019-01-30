package bangumi;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import spider.Film;

public class WriteAnimeXML {
	public static void writeAnime(List<Anime> animes) {
		System.out.println("正在保存为XML文件...请稍后");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.newDocument();
			Element root = doc.createElement("animes");
			// 遍历
			for (Anime a : animes) {
				/*
				 * 
				 * //排名 private int id; //名字 private String name; //原名 private
				 * String oldName; //信息 private String message; //分数 private
				 * double rating; //评分人数 private int number;
				 */
				Element anime = doc.createElement("anime");
				
				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(a.getId()+""));
				
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(a.getName()));
				
				Element oldName = doc.createElement("oldName");
				oldName.appendChild(doc.createTextNode(a.getOldName()));
				
				Element message = doc.createElement("message");
				message.appendChild(doc.createTextNode(a.getMessage()));

				Element rating = doc.createElement("rating");
				rating.appendChild(doc.createTextNode(a.getRating()+""));
				
				Element number = doc.createElement("number");
				number.appendChild(doc.createTextNode(a.getNumber()+""));
				
				anime.appendChild(id);
				anime.appendChild(name);
				anime.appendChild(oldName);
				anime.appendChild(message);
				anime.appendChild(rating);
				anime.appendChild(number);
				root.appendChild(anime);
			}
			doc.appendChild(root);
	
			//document --> xml文件
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			//设置编码
			t.setOutputProperty("encoding", "UTF-8");
			
			//源头
			DOMSource source = new DOMSource(doc);
			//传输的位置
			Result result = new StreamResult("bangumi动画排行.xml");
			
			t.transform(source, result);
			System.out.println("已经保存为XML文件");
		} catch (Exception e) {

		}

	}
}
