package jxnu.n433.x3107.bean;

import java.io.Serializable;


@SuppressWarnings("serial")
public class UserInfo implements Serializable{

	public static final String ID = "_id";
	public static final String USERID = "userid";//用户id
	public static final String USERICON = "userIcon";//用户头像
	public static final String USERNAME = "userName";//用户昵称
	public static final String USERPASSWORD = "userPassword";//用户密码
	public static final String StudentNumber = "studentNumber";//学号
	public static final String StudentPassword = "studentPassword";//学号密码
	public static final String PhoneNumber = "phoneNumber";
	public static final String USERSEXS = "userSexs";//用户性别
	public static final String USERSITE = "userSite";//用户地址
	public static final String USERINTRO = "userIntro";//用户简介
	public static final String USERSCHOOL = "userSchool";//用户学校
	public static final String USERBIRTHDAY = "userBirthday";//用户生日
	public static final String USERQQ = "userQQ";//用户qq
	public static final String USERMAILBOX = "userMailBox";//用户邮箱
	public static final String BAIDUSITE = "baiduSite";//定位地址
	public static final String ISLOGIN = "isLogin";
	
	private String _id;
	private String userid;
	private String userIcon;
	private String userName;
	private String userPassword;
	private String studentNumber;
	private String studentPassword;
	private String phoneNumber;
	private String userSexs;
	private String userSite;
	private String userIntro;
	private String userSchool;
	private String userBirthday;
	private String userQQ;
	private String userMailBox;
	private String baiduSite;
	private String isLogin;
	

	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserid() {
		return userid;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserSexs() {
		return userSexs;
	}
	public void setUserSexs(String userSexs) {
		this.userSexs = userSexs;
	}
	public String getUserSite() {
		return userSite;
	}
	public void setUserSite(String userSite) {
		this.userSite = userSite;
	}
	public String getUserIntro() {
		return userIntro;
	}
	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}
	public String getUserSchool() {
		return userSchool;
	}
	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserMailBox() {
		return userMailBox;
	}
	public void setUserMailBox(String userMailBox) {
		this.userMailBox = userMailBox;
	}
	public String getBaiduSite() {
		return baiduSite;
	}
	public void setBaiduSite(String baiduSite) {
		this.baiduSite = baiduSite;
	}
	public String getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}



}
