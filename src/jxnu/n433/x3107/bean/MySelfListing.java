package jxnu.n433.x3107.bean;


public class MySelfListing {
	public static final String ID = "_id";
	public static final String USERNAME = "userName";
	public static final String GOODSINTRO = "goodsIntro";
	public static final String GOODSIMAGE = "goodsImages";
	public static final String GOODSDATE = "goodsDate";
	public static final String GOODSDETAILS = "goodsDetails";
	public static final String GOODSATTENTION = "goodsAttention";
	public static final String USERICON = "userIcon";
	public static final String STUDENTNUMBER = "studentNumber";
	public static final String GOODSNAME = "goodsName";
	public static final String GOODSCLASSIFY = "goodsClassify";
	public static final String GOODSPRICE = "goodsPrice";
	public static final String GOODSCOLLECTION = "goodsCollenction";


	private int  _id;
	public String userName;
	public String goodsIntro;
	public String  goodsImages;
	public String goodsDate;
	public String goodsDetails;//详细信息
	public String  goodsAttention;//关注度
	public String userIcon;
	public String studentNumber;
	public String goodsName;
	public String goodsClassify;
	public String goodsPrice;
	public String goodsCollenction;

	public int  get_id() {
		return _id;
	}
	public void set_id(int  _id) {
		this._id = _id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGoodsIntro() {
		return goodsIntro;
	}
	public void setGoodsIntro(String goodsIntro) {
		this.goodsIntro = goodsIntro;
	}
	
	public String getGoodsImages() {
		return goodsImages;
	}
	
	public void setGoodsImages(String goodsImages) {
		this.goodsImages = goodsImages;
	}
	public String getGoodsDate() {
		return goodsDate;
	}
	public void setGoodsDate(String goodsDate) {
		this.goodsDate = goodsDate;
	}
	public String getGoodsDetails() {
		return goodsDetails;
	}
	public void setGoodsDetails(String goodsDetails) {
		this.goodsDetails = goodsDetails;
	}
	public String  getGoodsAttention() {
		return goodsAttention;
	}
	public void setGoodsAttention(String  goodsAttention) {
		this.goodsAttention = goodsAttention;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsClassify() {
		return goodsClassify;
	}
	public void setGoodsClassify(String goodsClassify) {
		this.goodsClassify = goodsClassify;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsCollenction() {
		return goodsCollenction;
	}
	public void setGoodsCollenction(String goodsCollenction) {
		this.goodsCollenction = goodsCollenction;
	}

}
