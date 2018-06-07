package bangumi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class WriteAnimeTXT {

	public static void writeAnime(List<Anime> animes) {
		System.out.println("正在写入TXT文件...");
		BufferedWriter bw = null;
		File file = new File("anime.txt");
		try {
			for (Anime a : animes) {
				 bw = new BufferedWriter(new FileWriter(file,true));
				 bw.write(a.toString());
			     bw.newLine();
			     bw.flush();
			     System.out.println(a);
				 
//				 ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream(file));
//		         oop.writeObject(a);
//		         oop.flush();
			}
			
			System.out.println("写入完成！");
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
