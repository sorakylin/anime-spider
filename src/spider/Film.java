package spider;

public class Film {
	//排名
	private String id;
	//标题
	private String title;
	//简介
	private String info;
	//评分
	private String rating;
	//标语
	private String quto;
	//路径
	private String path;
	
	
	public Film() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Film(String id, String title, String info, String rating, String quto, String path) {
		super();
		this.id = id;
		this.title = title;
		this.info = info;
		this.rating = rating;
		this.quto = quto;
		this.path = path;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getQuto() {
		return quto;
	}


	public void setQuto(String quto) {
		this.quto = quto;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	@Override
	public String toString() {
		return "Film [排名:" + id + ", 标题:" + title + ", 简介:" + info + ", 评分:" + rating + ", 标语:" + quto
				+ ", 路径:" + path + "]";
	}
	
	
}
