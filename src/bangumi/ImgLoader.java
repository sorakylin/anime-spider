package bangumi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

class ImgLoader implements Runnable {
	private Anime anime;

	public ImgLoader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImgLoader(Anime anime) {
		super();
		this.anime = anime;
	}
	
	static{
		System.out.println();
		System.out.println("正在下载图片...请稍后");
	}
	
	@Override
	public void run() {
		// 创建存放图片的文件夹
		File path = new File("bangumi动画排行榜");
		if (!path.exists()) {
			path.mkdir();
		}
		//创建图片的名字
		String name = String.format(anime.getId()+"_"+anime.getName()+".jpg");
		
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
					.url("https:"+anime.getImagePath()).build())
					.execute().body().bytes();
			
			out.write(data);
			out.close();
		} catch (IOException e) {
			System.out.println("...");
		} finally{
//			try {
//				out.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				System.out.println("...");
//			}
		}
	}

}
