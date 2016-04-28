package jxnu.n433.x3107.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.bean.AttentionUser;

public class AttentionUserSqliteHelper extends SQLiteOpenHelper{

	public static final String ATTENTIONTB_NAME = "attentiontb";

	public AttentionUserSqliteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}


	//创建表
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table if not exists "+ATTENTIONTB_NAME+"("+ 
				AttentionUser.ID+" int primary key,"+
				AttentionUser.StudentNumber+" text,"+
				AttentionUser.USERATTENTION+" text"+
				")";

		Logger.d(sql);

		db.execSQL(sql);
	}

	//更新表
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL( "DROP TABLE IF EXISTS " + ATTENTIONTB_NAME );
		onCreate(db);
		Logger. e("onUpgrade");
	}
}
