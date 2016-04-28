package jxnu.n433.x3107.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.bean.UserInfo;

public class UserInfoSqliteHelper extends SQLiteOpenHelper{//表

	public static final String USERTB_NAME = "userinfotb";

	public UserInfoSqliteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	//创建表
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table if not exists "+USERTB_NAME+"("+
				UserInfo.ID+" integer primary key,"+
				UserInfo.USERID+" text,"+ 
				UserInfo.USERICON+" text,"+
				UserInfo.USERNAME+" text,"+
				UserInfo.USERPASSWORD+" text,"+
				UserInfo.StudentNumber+" text,"+
				UserInfo.StudentPassword+" text,"+
				UserInfo.PhoneNumber+" text,"+
				UserInfo.USERSEXS+" text,"+
				UserInfo.USERSITE+" text,"+
				UserInfo.USERINTRO+" text,"+
				UserInfo.USERSCHOOL+" text,"+
				UserInfo.USERBIRTHDAY+" text,"+
				UserInfo.USERQQ+" text,"+
				UserInfo.USERMAILBOX+" text,"+
				UserInfo.BAIDUSITE+" text,"+
				UserInfo.ISLOGIN+" text"+
				")";
		Logger.d(sql);

		db.execSQL(sql);
	}

	//更新表
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL( "DROP TABLE IF EXISTS " + USERTB_NAME );
		onCreate(db);
		Logger. e("onUpgrade");
	}

}
