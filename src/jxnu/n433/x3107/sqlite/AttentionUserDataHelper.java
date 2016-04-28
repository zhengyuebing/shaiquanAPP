package jxnu.n433.x3107.sqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.bean.AttentionUser;

public class AttentionUserDataHelper {
	// 数据库名称
	private static String ATTENTIODB_NAMW = "attentiondb.db";

	// 数据库版本
	private SQLiteDatabase database;
	private AttentionUserSqliteHelper dbHelper;

	public AttentionUserDataHelper(Context context) {

		dbHelper = new AttentionUserSqliteHelper(context, ATTENTIODB_NAMW, null, 2);
		database = dbHelper.getWritableDatabase();
	}
	public void Colse(){
		database.close();
		dbHelper.close();
	}

	public ArrayList<AttentionUser> getAttentionList (){

		ArrayList<AttentionUser> attentionList = new ArrayList<AttentionUser>();
		String sql = "select * from "+AttentionUserSqliteHelper.ATTENTIONTB_NAME;

		Cursor cursor = database.rawQuery(sql, null);

		while(cursor.moveToNext()){
			AttentionUser aUser = new AttentionUser();

			aUser.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
			aUser.setStudentNumber(cursor.getString(cursor.getColumnIndex("studentNumber")));
			aUser.setUserAttention(cursor.getString(cursor.getColumnIndex("userAttention")));

			attentionList.add(aUser);

		}

		Logger.e(attentionList.size()+"get");

		return attentionList; 
	}
	public ArrayList<AttentionUser> getAttentionList (String oldSN){

		ArrayList<AttentionUser> attentionList = new ArrayList<AttentionUser>();
		String sql = "select * from "+AttentionUserSqliteHelper.ATTENTIONTB_NAME+" where studentNumber = "+oldSN;

		Cursor cursor = database.rawQuery(sql, null);

		while(cursor.moveToNext()){
			AttentionUser aUser = new AttentionUser();

			aUser.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
			aUser.setStudentNumber(cursor.getString(cursor.getColumnIndex("studentNumber")));
			aUser.setUserAttention(cursor.getString(cursor.getColumnIndex("userAttention")));

			attentionList.add(aUser);

		}

		Logger.e(attentionList.size()+"get2");

		return attentionList; 
	}


	public Long insertAttention(AttentionUser aUser){

		ContentValues values = new ContentValues();
		values.put(AttentionUser.StudentNumber, aUser.getStudentNumber());
		values.put(AttentionUser.USERATTENTION, aUser.getUserAttention());

		Long id = database.insert(AttentionUserSqliteHelper.ATTENTIONTB_NAME, AttentionUser.ID, values); 

		Logger.e(""+id);
		return id;
	}
	public int deleteAttention(String studentNumber,String userAttention){
		int id = database.delete(AttentionUserSqliteHelper.ATTENTIONTB_NAME,
				AttentionUser.StudentNumber+" =? and "+AttentionUser.USERATTENTION+" =? ",
				new String[]{studentNumber,userAttention});
		Logger.e("delete"+id);
		return id;
	}
}
