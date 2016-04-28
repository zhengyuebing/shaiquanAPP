package jxnu.n433.x3107.SunGroup.OtherActivity.Fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.SunGroup.WelComeActivity;
import jxnu.n433.x3107.SunGroup.OtherActivity.MerchandiseleftIssueActivity;
import jxnu.n433.x3107.bean.MySelfListing;
import jxnu.n433.x3107.sqlite.GoodsDataHelper;
import jxnu.n433.x3107.utils.Utils;


/*
 * 发布商品
 * */

public class MerchandiseleftFragmentIssue extends Fragment{

	private View merIssue;
	private List<MySelfListing> mLists;
	private GoodsDataHelper goodsDHelper;
	
	private ListView lvIssue;
	
	private SharedPreferences sPreferences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		merIssue = inflater.inflate(R.layout.activity_merchandise_left_fragment_issue, container,false);

		initView();
		
		return merIssue;


	}

	private void initView() {

		lvIssue = (ListView) merIssue.findViewById(R.id.merchandise_issue_news_head_lv);
		
		sPreferences = getActivity().getSharedPreferences(WelComeActivity.STUDENTNUMBER,0);
		String studentN = sPreferences.getString("sn", "");
		mLists = new ArrayList<MySelfListing>();
		goodsDHelper = new GoodsDataHelper(getActivity());
		mLists = goodsDHelper.getGoodsInfoList(studentN+"",1);
		
		GoodsAdapter goodsAdapter = new GoodsAdapter(mLists);
		
		Logger.d(mLists.size()+"mLists");
		lvIssue.setAdapter(goodsAdapter);
		
		ItemOnLongClick1();
		
	}
	

	private void ItemOnLongClick1() {
		lvIssue.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
				menu.add(0,0,0,"编辑");
				menu.add(0,1,0,"删除");
			
			}	
		});
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterView.AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		
		int mListPos = info.position;
//		MID = (int) info.id;
		switch (item.getItemId()) {
		case 0:
			
			Utils.showToast(getActivity(), "编辑");
			
			
			
			Intent intent = new Intent(getActivity(),MerchandiseleftIssueActivity.class);
			
			String goodsNameBJ = mLists.get(mListPos).getGoodsName()+"";
			String studentNumberBJ = mLists.get(mListPos).getStudentNumber()+"";
			
			intent.putExtra("menu", "编辑");
			intent.putExtra("studentNumber", studentNumberBJ);
			intent.putExtra("goodsName", goodsNameBJ);
			execCommand(mLists.get(mListPos).getGoodsImages()+"");
			
			startActivity(intent);
			
			
			break;
		case 1:
			
			Utils.showToast(getActivity(), "删除"+mListPos);
			
			
			String goodsName = mLists.get(mListPos).getGoodsName()+"";
			String studentNumber = mLists.get(mListPos).getStudentNumber()+"";
			
			
			
			goodsDHelper.deleteGoods(goodsName, studentNumber);
			execCommand(mLists.get(mListPos).getGoodsImages()+"");
			
			initView();

		
			
			break;

		default:
			break;
		}
		
		return super.onContextItemSelected(item);
	}
	public void execCommand(String path){
		if (!TextUtils.isEmpty(path)) {
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File file = new File(path);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                getActivity().sendBroadcast(intent);
                file.delete();                                          
                }
   }

	private class GoodsAdapter extends BaseAdapter{

		private List<MySelfListing> mySelfListing  = new ArrayList<MySelfListing>();
		private LayoutInflater mInflater;
		private int size;
		public GoodsAdapter(List<MySelfListing> mySelfListings) {
			this.mySelfListing = mySelfListings;
			size = mySelfListing.size();
			mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return size;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			

			ViewHolder holder = null;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.myself_layout_list_share, null);
				holder = new ViewHolder();

				holder.ivUserPohto = (ImageView) convertView.findViewById(R.id.myself_news_userPhoto);
				holder.tvuserName = (TextView) convertView.findViewById(R.id.myself_news_share_user_pohot);
				holder.tvDate = (TextView) convertView.findViewById(R.id.myself_news_date);
				holder.tvDetailed  = (TextView) convertView.findViewById(R.id.myself_news_detailed);
				holder.tvshare = (TextView) convertView.findViewById(R.id.myself_news_share_text);
				holder.ivPohot = (ImageView) convertView.findViewById(R.id.myself_news_share_pohot);
				holder.ivLike  = (ImageView) convertView.findViewById(R.id.myself_news_reply_icon);

				convertView.setTag(holder);
			}
			else{
				holder = (ViewHolder)convertView.getTag();
			}


			//用户头像
			
		
			
			Logger.d("\""+mySelfListing.get(position).getUserIcon()+"\"");
			
			try {
				Bitmap btUP = BitmapFactory.decodeFile(mySelfListing.get(position).getUserIcon());//从Sd中找头像，转换成Bitmap
				if(btUP!=null){
					@SuppressWarnings("deprecation")
					Drawable drawable = new BitmapDrawable(btUP);//转换成drawable
					holder.ivUserPohto.setImageDrawable(drawable);
				}else{
					/**
					 *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
					 * 
					 */
					
//				Utils.showToast(getActivity(), "没有图片");
				}
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
			}
			

			//用户名
			holder.tvuserName.setText(mySelfListing.get(position).getUserName());

			//用户发布商品信息
			holder.tvshare.setText(mySelfListing.get(position).getGoodsIntro());

			//用户发布商品图片
			
			Logger.d("\""+mySelfListing.get(position).getGoodsImages()+"\"");
			
			try {
				Bitmap bt = BitmapFactory.decodeFile(mySelfListing.get(position).getGoodsImages());//从Sd中找头像，转换成Bitmap
				if(bt!=null){
					@SuppressWarnings("deprecation")
					Drawable drawable = new BitmapDrawable(bt);//转换成drawable
					holder.ivPohot.setImageDrawable(drawable);
				}else{
					/**
					 *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
					 * 
					 */
					
//				Utils.showToast(getActivity(), "没有图片");
				}
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
			}
			


			//显示时间
			holder.tvDate.setText(mySelfListing.get(position).getGoodsDate()+"");
			
			//查看商品详情的textview
			
			
			holder.tvDetailed.setText("");


			String strInt = mySelfListing.get(position).getGoodsAttention()+"";


			if(strInt.equals("Y")){
				holder.ivLike.setImageResource(R.drawable.ic_thanked);
			}else {
				holder.ivLike.setImageResource(R.drawable.ic_thank);
			}
			
			
			return convertView;

		}

/*		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}

		@Override
		public boolean isEnabled(int position)  {
			return false;
		}
		
		*/
	}
	static class ViewHolder { 
		ImageView ivUserPohto;
		TextView tvuserName;
		TextView tvshare;
		ImageView ivPohot;
		TextView tvDate;
		TextView tvDetailed;
		TextView tvLikenum;
		ImageView ivLike;
	}
	
	public  void initClean() {
		
		lvIssue.removeAllViews();
		
	}
}
