package jxnu.n433.x3107.bean;

public class HomeGoods {
	public static final String ID = "_id";
	public static final String HGOODSIMAGE = "hgoodsImages";
	public static final String HGOODSINTRO = "hgoodsIntro";
	public static final String HGOODSNAME = "hgoodsName";

	private String _id;
	private String hgoodsName;
	private String hgoodsIntro;
	private int hgoodsImages;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getHgoodsName() {
		return hgoodsName;
	}
	public void setHgoodsName(String hgoodsName) {
		this.hgoodsName = hgoodsName;
	}
	public String getHgoodsIntro() {
		return hgoodsIntro;
	}
	public void setHgoodsIntro(String hgoodsIntro) {
		this.hgoodsIntro = hgoodsIntro;
	}
	public int getHgoodsImages() {
		return hgoodsImages;
	}
	public void setHgoodsImages(int hgoodsImages) {
		this.hgoodsImages = hgoodsImages;
	}


}
