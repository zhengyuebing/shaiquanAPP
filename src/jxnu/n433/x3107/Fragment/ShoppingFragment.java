package jxnu.n433.x3107.Fragment;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.SunGroup.WelComeActivity;
import jxnu.n433.x3107.SunGroup.OtherActivity.HomeGoodsGVActivity;
import jxnu.n433.x3107.bean.MySelfListing;
import jxnu.n433.x3107.sqlite.GoodsDataHelper;

@SuppressLint("HandlerLeak")
public class ShoppingFragment extends Fragment {
	
	private ListView lvCollection;
	private View shoppingLayout;
	
	private List<MySelfListing> myselfList;
	private GoodsDataHelper goodsDHelper;
	private SharedPreferences sPreferences;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		shoppingLayout = inflater.inflate(R.layout.shopping_layout, container,false);
		
		sPreferences = getActivity().getSharedPreferences(WelComeActivity.STUDENTNUMBER,0);
		
		initView();
		
		return shoppingLayout;
	}

	private void initView() {

		lvCollection = (ListView) shoppingLayout.findViewById(R.id.shopping_news_head_lv);
	
		myselfList = new ArrayList<MySelfListing>();
		goodsDHelper = new GoodsDataHelper(getActivity());
		myselfList = goodsDHelper.getGoodsInfoList(sPreferences.getString("sn", "")+"",4);
		
		

		GoodsAdapter shareAdapter = new GoodsAdapter(myselfList);
		lvCollection.setAdapter(shareAdapter);
		lvCollection.setDivider(null);//消除listview横线
		
		lvCollection.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(getActivity(),HomeGoodsGVActivity.class);
				intent.putExtra("int","hotGridView:"+arg2);
				intent.putExtra("goodsName", ""+myselfList.get(arg2).getGoodsName());
				intent.putExtra("studentNumber", ""+myselfList.get(arg2).getStudentNumber());
				startActivity(intent);
				getActivity().finish();
				Logger.d(arg2+"");
			}
		});
		
		ItemOnLongClick1();
		
	}
	
	private void ItemOnLongClick1() {
		lvCollection.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
				menu.add(0,0,0,"删除");
			
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
			
			goodsDHelper.updateGoods("", myselfList.get(mListPos).getGoodsName()+"",  myselfList.get(mListPos).getStudentNumber()+"", 11);
			initView();
			
			break;

		default:
			break;
		}
		
		return super.onContextItemSelected(item);
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
			} catch (Exception e1) {
				e1.printStackTrace();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
			


			//显示时间
			holder.tvDate.setText(mySelfListing.get(position).getGoodsDate()+"");
			
			//查看商品详情的textview
			
			
			holder.tvDetailed.setText("");

			holder.ivLike.setImageResource(R.drawable.ic_thanked);
			/*String strInt = mySelfListing.get(position).getGoodsAttention()+"";


			if(strInt.equals("Y")){
				
			}else {
				holder.ivLike.setImageResource(R.drawable.ic_thank);
			}
			*/
			
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
	
}
