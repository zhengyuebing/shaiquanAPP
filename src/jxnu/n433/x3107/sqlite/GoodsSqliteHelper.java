package jxnu.n433.x3107.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.bean.MySelfListing;
import android.database.sqlite.SQLiteOpenHelper;

public class GoodsSqliteHelper extends SQLiteOpenHelper{

	public static final String GOODS_NAME = "goods";

	public GoodsSqliteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	//创建表
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table if not exists "+ GOODS_NAME +" ( "+
				MySelfListing.ID+" int PRIMARY KEY,"+
				MySelfListing.STUDENTNUMBER+" text,"+
				MySelfListing.USERNAME+" text,"+
				MySelfListing.GOODSINTRO+" text,"+
				MySelfListing.GOODSIMAGE+" text,"+
				MySelfListing.GOODSDATE+" text,"+
				MySelfListing.GOODSDETAILS+" text,"+
				MySelfListing.GOODSATTENTION+" text,"+
				MySelfListing.USERICON+" text,"+
				MySelfListing.GOODSNAME+" text,"+
				MySelfListing.GOODSCLASSIFY+" text,"+
				MySelfListing.GOODSPRICE+" text,"+
				MySelfListing.GOODSCOLLECTION+" text"+
				" )";
		
		Logger.d(sql);
		db.execSQL(sql);
	}
	
	//更新表
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+GOODS_NAME);
		onCreate(db);
		Logger.e("onUpgrade");
	}

}
