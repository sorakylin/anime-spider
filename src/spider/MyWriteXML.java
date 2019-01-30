package spider;

import java.util.Calendar;
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

public class MyWriteXML {
	
	
	
	public static void writeXML(List<Film> films){
		System.out.println("已进入信息转换树装置");
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.newDocument();
			
			//创建根节点
			Element root = doc.createElement("films");
			//遍历
			for(Film f:films){
				Element film = doc.createElement("film");
				//id
				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(f.getId()));
				//标题
				Element title = doc.createElement("title");
				title.appendChild(doc.createTextNode(f.getTitle()));
				//简介
				Element info = doc.createElement("info");
				info.appendChild(doc.createTextNode(f.getInfo()));
				//评分
				Element rating = doc.createElement("rating");
				rating.appendChild(doc.createTextNode(f.getRating()));
				//标语
				Element quto = doc.createElement("quto");
				quto.appendChild(doc.createTextNode(f.getQuto()));
				
				film.appendChild(id);
				film.appendChild(title);
				film.appendChild(info);
				film.appendChild(rating);
				film.appendChild(quto);
				
				root.appendChild(film);
			}
			doc.appendChild(root);
			System.out.println("转换完毕");
			//document --> xml文件
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			//设置编码
			t.setOutputProperty("encoding", "UTF-8");
			
			//源头
			DOMSource source = new DOMSource(doc);
			//传输的位置
			Result result = new StreamResult("250电影.xml");
			
			t.transform(source, result);
			System.out.println("已经保存为XML文件");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
