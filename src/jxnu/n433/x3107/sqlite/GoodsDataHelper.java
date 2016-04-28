package jxnu.n433.x3107.sqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.bean.MySelfListing;

public class GoodsDataHelper {
	// 数据库名称
	private static String GOODS_NAME = "goodsdb.db";
	// 数据库版本
	private static int GOODS_VERSION = 2;
	private SQLiteDatabase database;
	private GoodsSqliteHelper dbHelper; 

	public GoodsDataHelper(Context context) {
		dbHelper = new GoodsSqliteHelper(context, GOODS_NAME, null,GOODS_VERSION);
		database = dbHelper.getWritableDatabase();
	}

	public void Colse(){
		database.close();
		dbHelper.close();
	}

	public ArrayList< MySelfListing> getGoodsInfoList(){
		ArrayList< MySelfListing> goodsList = new ArrayList<MySelfListing>();
		String sql = "select * from " + GoodsSqliteHelper.GOODS_NAME +" order by goodsDate desc";
		Cursor cursor = database.rawQuery(sql, null);
		while(cursor.moveToNext()){
			MySelfListing mListing = new MySelfListing();

			mListing.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
			mListing.setStudentNumber(cursor.getString(cursor.getColumnIndex("studentNumber")));
			mListing.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
			mListing.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsName")));
			mListing.setGoodsIntro(cursor.getString(cursor.getColumnIndex("goodsIntro")));
			mListing.setGoodsImages(cursor.getString(cursor.getColumnIndex("goodsImages")));
			mListing.setGoodsDate(cursor.getString(cursor.getColumnIndex("goodsDate")));
			mListing.setGoodsAttention(cursor.getString(cursor.getColumnIndex("goodsAttention")));
			mListing.setUserIcon(cursor.getString(cursor.getColumnIndex("userIcon")));
			mListing.setGoodsDetails(cursor.getString(cursor.getColumnIndex("goodsDetails")));
			mListing.setGoodsClassify(cursor.getString(cursor.getColumnIndex("goodsClassify")));
			mListing.setGoodsPrice(cursor.getString(cursor.getColumnIndex("goodsPrice")));
			mListing.setGoodsCollenction(cursor.getString(cursor.getColumnIndex("goodsCollenction")));

			goodsList.add(mListing);

		}

		Logger.e(goodsList.size()+"");
		return goodsList;
	}

	public ArrayList< MySelfListing> getGoodsInfoList(String studentClass,int i){
		ArrayList< MySelfListing> goodsList = new ArrayList<MySelfListing>();
		String sql = null;
		if (i == 1) {
			sql = "select * from " + GoodsSqliteHelper.GOODS_NAME + " where studentNumber = '"+studentClass+"' order by goodsDate desc";

		}
		if (i == 2) {
			sql = "select * from " + GoodsSqliteHelper.GOODS_NAME + " where goodsClassify = '"+studentClass+"' order by goodsDate desc";
		}
		if (i == 3) {
			sql = "select * from " + GoodsSqliteHelper.GOODS_NAME + " where goodsIntro like '%"+studentClass+"%' or goodsName like '%"+studentClass+"%' order by goodsDate desc";

			//			cursor = database.query(GoodsSqliteHelper.GOODS_NAME, null, "goodsIntro like ? ", new String[]{ "%"+studentClass+"%"},null,null,null);

		}
		if (i == 4) {
			sql = "select * from " + GoodsSqliteHelper.GOODS_NAME + " where goodsCollenction = '"+studentClass+"' order by goodsDate desc";
		}

		Cursor cursor = database.rawQuery(sql, null);
		while(cursor.moveToNext()){
			MySelfListing mListing = new MySelfListing();

			mListing.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
			mListing.setStudentNumber(cursor.getString(cursor.getColumnIndex("studentNumber")));
			mListing.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
			mListing.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsName")));
			mListing.setGoodsIntro(cursor.getString(cursor.getColumnIndex("goodsIntro")));
			mListing.setGoodsImages(cursor.getString(cursor.getColumnIndex("goodsImages")));
			mListing.setGoodsDate(cursor.getString(cursor.getColumnIndex("goodsDate")));
			mListing.setGoodsAttention(cursor.getString(cursor.getColumnIndex("goodsAttention")));
			mListing.setUserIcon(cursor.getString(cursor.getColumnIndex("userIcon")));
			mListing.setGoodsDetails(cursor.getString(cursor.getColumnIndex("goodsDetails")));
			mListing.setGoodsClassify(cursor.getString(cursor.getColumnIndex("goodsClassify")));
			mListing.setGoodsPrice(cursor.getString(cursor.getColumnIndex("goodsPrice")));
			mListing.setGoodsCollenction(cursor.getString(cursor.getColumnIndex("goodsCollenction")));

			goodsList.add(mListing);

		}

		Logger.e(goodsList.size()+"studentNumber");
		Logger.e(sql+"studentNumber");
		return goodsList;
	}
	public ArrayList< MySelfListing> getGoodsInfoList(String studentNumber,String goodsName,int i){
		ArrayList< MySelfListing> goodsList = new ArrayList<MySelfListing>();
		String sql = null;
		if (i == 1) {
			sql = "select * from " + GoodsSqliteHelper.GOODS_NAME + " where studentNumber = '"+studentNumber+"' and goodsName = '"+goodsName+"' order by goodsDate desc";
		}
		if (i == 2) {
			sql = "select * from " + GoodsSqliteHelper.GOODS_NAME + " where studentNumber = '"+studentNumber+"' or goodsCollenction = '"+goodsName+"' order by goodsDate desc";
		}
		Cursor cursor = database.rawQuery(sql, null);
		while(cursor.moveToNext()){
			MySelfListing mListing = new MySelfListing();

			mListing.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
			mListing.setStudentNumber(cursor.getString(cursor.getColumnIndex("studentNumber")));
			mListing.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
			mListing.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsName")));
			mListing.setGoodsIntro(cursor.getString(cursor.getColumnIndex("goodsIntro")));
			mListing.setGoodsImages(cursor.getString(cursor.getColumnIndex("goodsImages")));
			mListing.setGoodsDate(cursor.getString(cursor.getColumnIndex("goodsDate")));
			mListing.setGoodsAttention(cursor.getString(cursor.getColumnIndex("goodsAttention")));
			mListing.setUserIcon(cursor.getString(cursor.getColumnIndex("userIcon")));
			mListing.setGoodsDetails(cursor.getString(cursor.getColumnIndex("goodsDetails")));
			mListing.setGoodsClassify(cursor.getString(cursor.getColumnIndex("goodsClassify")));
			mListing.setGoodsPrice(cursor.getString(cursor.getColumnIndex("goodsPrice")));
			mListing.setGoodsCollenction(cursor.getString(cursor.getColumnIndex("goodsCollenction")));

			goodsList.add(mListing);

		}

		Logger.e(goodsList.size()+"studentNumber");
		Logger.e(sql+"studentNumber");
		return goodsList;
	}


	//更新数据
	public void updateGoods(String goodsUpdate,String goodsName,String studentNumber,int i){

		switch (i) {
		case 1:
			//修改商品名字
			updateGoodsWay(MySelfListing.GOODSNAME,goodsUpdate,goodsName,studentNumber);

			break;
		case 2:
			//修改商品信息
			updateGoodsWay(MySelfListing.GOODSINTRO,goodsUpdate,goodsName,studentNumber);

			break;
		case 3:
			//修改商品图片
			updateGoodsWay(MySelfListing.GOODSIMAGE,goodsUpdate,goodsName,studentNumber);

			break;
		case 4:
			//修改商品时间
			updateGoodsWay(MySelfListing.GOODSDATE,goodsUpdate,goodsName,studentNumber);

			break;
		case 5:
			//修改商品详细信息,没用
			updateGoodsWay(MySelfListing.GOODSDETAILS,goodsUpdate,goodsName,studentNumber);

			break;
		case 6:
			//修改商品关注度
			updateGoodsWay(MySelfListing.GOODSATTENTION,goodsUpdate,goodsName,studentNumber);

			break;
		case 7:
			//修改用户名字
			updateGoodsWay(MySelfListing.USERNAME,goodsUpdate,goodsName,studentNumber);

			break;
		case 8:
			//修改用户头像
			updateGoodsWay(MySelfListing.USERICON,goodsUpdate,goodsName,studentNumber);

			break;
		case 9:
			//修改商品分类
			updateGoodsWay(MySelfListing.GOODSCLASSIFY, goodsUpdate, goodsName, studentNumber);
			break;
		case 10:
			//修改商品价格
			updateGoodsWay(MySelfListing.GOODSPRICE, goodsUpdate, goodsName, studentNumber);
			break;
		case 11:
			//添加商品收藏
			updateGoodsWay(MySelfListing.GOODSCOLLECTION, goodsUpdate, goodsName, studentNumber);
			break;

		default:
			break;
		}

	}



	private int  updateGoodsWay(String myL,String goodsUpdate, String goodsName, String studentNumber) {
		ContentValues values = new ContentValues();
		values.put(myL, goodsUpdate);
		int id = database.update(GoodsSqliteHelper.GOODS_NAME,
				values, MySelfListing.GOODSNAME+" =? and "+MySelfListing.STUDENTNUMBER +" =? ",
				new String[]{goodsName,studentNumber});
		if (studentNumber == null) {
			int idNull = database.update(GoodsSqliteHelper.GOODS_NAME,
					values, MySelfListing.GOODSNAME+" =? ",
					new String[]{goodsName});
			return idNull;
		}
		if (goodsName == null) {
			int gnNull = database.update(GoodsSqliteHelper.GOODS_NAME,
					values,MySelfListing.STUDENTNUMBER+" =? ",new String[]{studentNumber});
			return gnNull;
		}

		return id;
	}


	//插入数据
	public Long insertGoods(MySelfListing mySelfListing){

		ContentValues values = new ContentValues();
		values.put(MySelfListing.STUDENTNUMBER, mySelfListing.getStudentNumber());
		values.put(MySelfListing.GOODSINTRO, mySelfListing.getGoodsIntro());
		values.put(MySelfListing.GOODSNAME, mySelfListing.getGoodsName());
		values.put(MySelfListing.GOODSIMAGE, mySelfListing.getGoodsImages());
		values.put(MySelfListing.GOODSDATE, mySelfListing.getGoodsDate());
		values.put(MySelfListing.GOODSCLASSIFY, mySelfListing.getGoodsClassify());
		values.put(MySelfListing.GOODSPRICE, mySelfListing.getGoodsPrice());

		Long goodsUid = database.insert(GoodsSqliteHelper.GOODS_NAME, MySelfListing.ID,values);

		Logger.e(goodsUid+"goodsUid");

		return goodsUid;

	}
	//删除表的记录
	public int deleteGoods(String goodsName, String studentNumber){
		int goodsid = database.delete(GoodsSqliteHelper.GOODS_NAME,
				MySelfListing.GOODSNAME + " =?  and " + MySelfListing.STUDENTNUMBER +" =? ", 
				new String[]{goodsName,studentNumber});
		Logger. e( goodsid + "");
		return goodsid;
	}

	public static MySelfListing getUserID(String ID,ArrayList<MySelfListing> mySelfLists){
		MySelfListing mySelfListings = null;
		int size = mySelfLists.size();
		for( int i=0;i<size;i++){
			if(ID.equals(mySelfLists.get(i).get_id())){
				mySelfListings = mySelfLists.get(i);
				break;
			}
		}
		return mySelfListings;
	}



}
