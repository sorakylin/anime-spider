package spider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import bangumi.Anime;
import okhttp3.OkHttpClient;
import okhttp3.Request;

class ImgLoader implements Runnable {
	private Film film;

	public ImgLoader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImgLoader(Film film) {
		super();
		this.film = film;
	}

	@Override
	public void run() {
		// 创建存放图片的文件夹
		File path = new File("豆瓣TOP250");
		if (!path.exists()) {
			path.mkdir();
		}
		//创建图片的名字
		String name = String.format(film.getId()+"_"+film.getTitle()+".jpg");
		
		BufferedOutputStream out =null;
		try {
			//输出流
			out = new BufferedOutputStream
					(new FileOutputStream(new File(path, name)));


			byte[] data = new OkHttpClient.Builder()
					.connectTimeout(60, TimeUnit.SECONDS)
					.readTimeout(60, TimeUnit.SECONDS)
					.writeTimeout(60, TimeUnit.SECONDS)
					.build()
					.newCall(new Request.Builder()
					.url(film.getPath()).build())
					.execute().body().bytes();
			
			
			out.write(data);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
