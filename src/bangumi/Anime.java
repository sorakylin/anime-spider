package bangumi;

import java.io.Serializable;

public class Anime{ //implements Serializable
	//排名
	private int id;
	//名字
	private String name;
	//原名
	private String oldName;
	//信息
	private String message;
	//分数
	private double rating;
	//评分人数
	private int number;
	
	private String imagePath;
	
	public Anime() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	



	public Anime(int id, String name, String oldName, String message, double rating, int number, String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.oldName = oldName;
		this.message = message;
		this.rating = rating;
		this.number = number;
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	/*@Override
	public String toString() {
		return "Anime [id=" + id + ", name=" + name + ", oldName=" + oldName + ", message=" + message + ", rating="
				+ rating + ", number=" + number + ", imagePath=" + imagePath + "]";
	}*/



	@Override
	public String toString() {
		return "Anime [id=" + id + ", name=" + name + ", oldName=" + oldName + ", message=" + message + ", rating="
				+ rating + ", number=" + number + "]";
	}
	
	
	
	
	
	
}
