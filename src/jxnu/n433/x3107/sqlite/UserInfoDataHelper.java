package jxnu.n433.x3107.sqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.bean.UserInfo;

public class UserInfoDataHelper {
	// 数据库名称
	private static String USERDB_NAME = "userinfodb.db";

	// 数据库版本
	private static int USERDB_VERSION = 2;
	private SQLiteDatabase database;
	private UserInfoSqliteHelper dbHelper;

	public UserInfoDataHelper(Context context) {
		dbHelper = new UserInfoSqliteHelper(context, USERDB_NAME, null, USERDB_VERSION);
		database = dbHelper.getWritableDatabase();
	}


	public void Colse(){
		database.close();
		dbHelper.close();
	}

	// 获取userinfotb表中的记录Boolean isSimple
	public ArrayList<UserInfo> getUserInfoList(){

		ArrayList<UserInfo> userinfoList = new ArrayList<UserInfo>();
		String sql="select * from "+UserInfoSqliteHelper.USERTB_NAME;
		Cursor cursor = database.rawQuery(sql, null);
		//		cursor.moveToFirst();
		while(cursor.moveToNext()){
			UserInfo userInfo = new UserInfo();
			userInfo.set_id(cursor.getString(cursor.getColumnIndex("_id")));//?
			userInfo.setUserid(cursor.getString(cursor.getColumnIndex("userid")));//?
			userInfo.setUserIcon(cursor.getString(cursor.getColumnIndex("userIcon")));//?
			userInfo.setUserName(cursor.getString(cursor.getColumnIndex("userName")));//?
			userInfo.setUserPassword(cursor.getString(cursor.getColumnIndex("userPassword")));//?
			userInfo.setStudentNumber(cursor.getString(cursor.getColumnIndex("studentNumber")));//
			userInfo.setStudentPassword(cursor.getString(cursor.getColumnIndex("studentPassword")));//
			userInfo.setPhoneNumber(cursor.getString(cursor.getColumnIndex("phoneNumber")));
			userInfo.setUserSexs(cursor.getString(cursor.getColumnIndex("userSexs")));//?
			userInfo.setUserSite(cursor.getString(cursor.getColumnIndex("userSite")));//?
			userInfo.setUserIntro(cursor.getString(cursor.getColumnIndex("userIntro")));//?
			userInfo.setUserSchool(cursor.getString(cursor.getColumnIndex("userSchool")));//?
			userInfo.setUserBirthday(cursor.getString(cursor.getColumnIndex("userBirthday")));//?
			userInfo.setUserQQ(cursor.getString(cursor.getColumnIndex("userQQ")));//?
			userInfo.setUserMailBox(cursor.getString(cursor.getColumnIndex("userMailBox")));//?
			userInfo.setBaiduSite(cursor.getString(cursor.getColumnIndex("baiduSite")));
			userInfo.setIsLogin(cursor.getString(cursor.getColumnIndex("isLogin")));

			//			if (!isSimple) {
			//			}

			userinfoList.add(userInfo);


		}
		//		cursor.close();

		Logger.e(userinfoList.size()+"");
		return userinfoList;

	}
	public ArrayList<UserInfo> getUserInfoList(String studentNumber){
		
		ArrayList<UserInfo> userinfoList = new ArrayList<UserInfo>();
		String sql="select * from "+UserInfoSqliteHelper.USERTB_NAME+" where studentNumber = '"+studentNumber+"'";
		Cursor cursor = database.rawQuery(sql, null);
		//		cursor.moveToFirst();
		while(cursor.moveToNext()){
			UserInfo userInfo = new UserInfo();
			userInfo.set_id(cursor.getString(cursor.getColumnIndex("_id")));//?
			userInfo.setUserid(cursor.getString(cursor.getColumnIndex("userid")));//?
			userInfo.setUserIcon(cursor.getString(cursor.getColumnIndex("userIcon")));//?
			userInfo.setUserName(cursor.getString(cursor.getColumnIndex("userName")));//?
			userInfo.setUserPassword(cursor.getString(cursor.getColumnIndex("userPassword")));//?
			userInfo.setStudentNumber(cursor.getString(cursor.getColumnIndex("studentNumber")));//
			userInfo.setStudentPassword(cursor.getString(cursor.getColumnIndex("studentPassword")));//
			userInfo.setPhoneNumber(cursor.getString(cursor.getColumnIndex("phoneNumber")));
			userInfo.setUserSexs(cursor.getString(cursor.getColumnIndex("userSexs")));//?
			userInfo.setUserSite(cursor.getString(cursor.getColumnIndex("userSite")));//?
			userInfo.setUserIntro(cursor.getString(cursor.getColumnIndex("userIntro")));//?
			userInfo.setUserSchool(cursor.getString(cursor.getColumnIndex("userSchool")));//?
			userInfo.setUserBirthday(cursor.getString(cursor.getColumnIndex("userBirthday")));//?
			userInfo.setUserQQ(cursor.getString(cursor.getColumnIndex("userQQ")));//?
			userInfo.setUserMailBox(cursor.getString(cursor.getColumnIndex("userMailBox")));//?
			userInfo.setBaiduSite(cursor.getString(cursor.getColumnIndex("baiduSite")));
			userInfo.setIsLogin(cursor.getString(cursor.getColumnIndex("isLogin")));
			
			//			if (!isSimple) {
			//			}
			
			userinfoList.add(userInfo);
			
			
		}
		//		cursor.close();
		
		Logger.e(userinfoList.size()+"");
		return userinfoList;
		
	}

	
	public int updateUserInfo(String userInfoname ,String studentNumber,int n){
		switch (n) {
		case 1:
			ContentValues values1 = new ContentValues();
			values1.put(UserInfo.USERNAME, userInfoname);
			int id1 = database.update(UserInfoSqliteHelper.USERTB_NAME, values1, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id1+"updateusername");
			
			break;
		case 2:
			ContentValues values2 = new ContentValues();
			values2.put(UserInfo.USERSEXS, userInfoname);
			int id2 = database.update(UserInfoSqliteHelper.USERTB_NAME, values2, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id2+"updateusersexs");
			
			break;
		case 3:
			ContentValues values3 = new ContentValues();
			values3.put(UserInfo.USERSITE, userInfoname);
			int id3 = database.update(UserInfoSqliteHelper.USERTB_NAME, values3, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id3+"updateusersite");
			
			break;
		case 4:
			ContentValues values4 = new ContentValues();
			values4.put(UserInfo.USERINTRO, userInfoname);
			int id4 = database.update(UserInfoSqliteHelper.USERTB_NAME, values4, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id4+"updateuserintro");
			
			break;
		case 5:
			ContentValues values5 = new ContentValues();
			values5.put(UserInfo.USERSCHOOL, userInfoname);
			int id5 = database.update(UserInfoSqliteHelper.USERTB_NAME, values5, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id5+"updateschool");
			
			break;
		case 6:
			ContentValues values6 = new ContentValues();
			values6.put(UserInfo.USERBIRTHDAY, userInfoname);
			int id6 = database.update(UserInfoSqliteHelper.USERTB_NAME, values6, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id6+"updatebiethday");
			
			break;
		case 7:
			ContentValues values7 = new ContentValues();
			values7.put(UserInfo.USERQQ, userInfoname);
			int id7 = database.update(UserInfoSqliteHelper.USERTB_NAME, values7, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id7+"qq");
			
			break;
		case 8:
			ContentValues values8 = new ContentValues();
			values8.put(UserInfo.USERMAILBOX, userInfoname);
			int id8 = database.update(UserInfoSqliteHelper.USERTB_NAME, values8, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id8+"mailbox");
			
			break;
		case 9:
			ContentValues values9 = new ContentValues();
			values9.put(UserInfo.PhoneNumber, userInfoname);
			int id9 = database.update(UserInfoSqliteHelper.USERTB_NAME, values9, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id9+"phonenumber");
			
			break;
		case 10:
			ContentValues values10 = new ContentValues();
			values10.put(UserInfo.USERPASSWORD, userInfoname);
			int id10 = database.update(UserInfoSqliteHelper.USERTB_NAME, values10, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id10+"password");
			
			break;
		case 11:
			ContentValues values11 = new ContentValues();
			values11.put(UserInfo.USERICON, userInfoname);
			int id11 = database.update(UserInfoSqliteHelper.USERTB_NAME, values11, UserInfo.StudentNumber +" =? ", new String[]{studentNumber});
			Logger.e(id11+"icon");
			
			break;
		case 12:
			ContentValues values12 = new ContentValues();
			values12.put(UserInfo.BAIDUSITE, userInfoname);
			int id12 = database.update(UserInfoSqliteHelper.USERTB_NAME, values12, UserInfo.StudentNumber + " =? ", new String[]{studentNumber});
			Logger.e(id12+"baidusite");
			break;
		case 13:
			ContentValues values13 = new ContentValues();
			values13.put(UserInfo.ISLOGIN, userInfoname);
			int id13 = database.update(UserInfoSqliteHelper.USERTB_NAME, values13, UserInfo.StudentNumber + " =? ", new String[]{studentNumber});
			Logger.e(id13+"islogin");
			break;

		default:
			break;
		}

		return n;
	}

	//添加表记录
	public Long insertUserInfo(UserInfo userInfo){
		
		
		ContentValues values = new ContentValues();
		values.put(UserInfo.USERNAME, userInfo.getUserName());
		values.put(UserInfo.USERPASSWORD, userInfo.getUserPassword());
		values.put(UserInfo.USERSCHOOL, userInfo.getUserSchool());
		values.put(UserInfo.StudentNumber, userInfo.getStudentNumber());
		values.put(UserInfo.StudentPassword, userInfo.getStudentPassword());
		values.put(UserInfo.PhoneNumber, userInfo.getPhoneNumber());

		Long uid = database.insert(UserInfoSqliteHelper. USERTB_NAME, UserInfo.ID, values);

		Logger.e(uid+"uid");
		return uid;
	}
	public long insertBaiDuSite(String baiduSite){
		ContentValues values = new ContentValues();
		values.put(UserInfo.BAIDUSITE, baiduSite);
		Long uidSite = database.insert(UserInfoSqliteHelper.USERTB_NAME, UserInfo.ID, values);
		Logger.e(uidSite+"uidSite");
		return uidSite;
	}

	// 删除表的记录
	public int deletelUserInfo(String studentNumber) {
		int id = database.delete(UserInfoSqliteHelper. USERTB_NAME,
				UserInfo. StudentNumber + "=?", new String[]{studentNumber});
		Logger. e( id + "");
		return id;
	}

	public static UserInfo getUserId(String Id,ArrayList<UserInfo> userList){
		UserInfo userInfo = null;
		int size = userList.size();
		for( int i=0;i<size;i++){
			if(Id.equals(userList.get(i).get_id())){
				userInfo = userList.get(i);
				break;
			}
		}
		return userInfo;
	}     

}
