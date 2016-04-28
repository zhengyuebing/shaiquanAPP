package jxnu.n433.x3107.bean;

public class AttentionUser {
	public static final String ID = "_id";
	public static final String USERATTENTION = "userAttention";
	public static final String StudentNumber = "studentNumber";//学号
	
	private int  _id;
	private String studentNumber;
	private String userAttention;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getUserAttention() {
		return userAttention;
	}
	public void setUserAttention(String userAttention) {
		this.userAttention = userAttention;
	}

}
