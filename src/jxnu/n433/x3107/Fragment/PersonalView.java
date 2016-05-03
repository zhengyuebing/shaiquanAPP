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
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.SunGroup.WelComeActivity;
import jxnu.n433.x3107.SunGroup.OtherActivity.HomeGoodsGVActivity;
import jxnu.n433.x3107.SunGroup.OtherActivity.PersonalleftActivity;
import jxnu.n433.x3107.SunGroup.OtherActivity.UserIntroActivity;
import jxnu.n433.x3107.bean.MySelfListing;
import jxnu.n433.x3107.bean.UserInfo;
import jxnu.n433.x3107.sqlite.GoodsDataHelper;
import jxnu.n433.x3107.sqlite.UserInfoDataHelper;

@SuppressLint({ "SimpleDateFormat", "HandlerLeak" })
public class PersonalView extends Fragment implements OnClickListener{

	private View myselfLayout;

	private RelativeLayout Head;
	private ImageView myselfHead;
	private TextView myselfUserName;
	private ListView myselfListing;

	private List<MySelfListing> myselfList;
	private GoodsDataHelper goodsDHelper;

	private SharedPreferences sPreferences;
	private List<UserInfo> userInfoList;
	private UserInfoDataHelper userInfoDHelper;
	private int count = 0;


	//	SimpleDateFormat format = new SimpleDateFormat("kk:mm:ss");//yyyy-MM-dd 
	//	private String str = format.format(new java.util.Date());

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case 1:

				initListing();

				break;
			}
		}

	};

	private Bitmap btPersonalHead;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		myselfLayout = inflater.inflate(R.layout.myself_layout, container,false);

		sPreferences = getActivity().getSharedPreferences(WelComeActivity.STUDENTNUMBER,0);



		initView();

		initImageViewHead();

		initListing();


		return myselfLayout;
	}



	private void initView() {
		Head = (RelativeLayout) myselfLayout.findViewById(R.id.myself_news_head_image);
		myselfHead = (ImageView) myselfLayout.findViewById(R.id.zdy_image_myself);
		myselfUserName = (TextView) myselfLayout.findViewById(R.id.myself_news_username);
		myselfListing = (ListView) myselfLayout.findViewById(R.id.myself_news_head_lv);

		myselfUserName.setOnClickListener(this);
		userName();
	}
	private void userName(){//用户名
		userInfoList = new ArrayList<UserInfo>();
		userInfoDHelper = new UserInfoDataHelper(getActivity());
		sPreferences = getActivity().getSharedPreferences(WelComeActivity.STUDENTNUMBER,0);
		userInfoList = userInfoDHelper.getUserInfoList();
		for(int i = 0;i < userInfoList.size(); i++){
			if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
				myselfUserName.setText(userInfoList.get(i).getUserName()+"");
			}
		}
	}


	//头像
	@SuppressWarnings("deprecation")
	private void initImageViewHead() {




		userInfoList = new ArrayList<UserInfo>();
		userInfoDHelper = new UserInfoDataHelper(getActivity());
		userInfoList = userInfoDHelper.getUserInfoList();
		String strImage = null;
		sPreferences = getActivity().getSharedPreferences(WelComeActivity.STUDENTNUMBER,0);
		for (int i = 0; i < userInfoList.size(); i++) {
			if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
				strImage = userInfoList.get(i).getUserIcon()+"";
			}else {
			}
		}

		try {
			btPersonalHead = BitmapFactory.decodeFile(strImage);
			if (btPersonalHead != null) {
				Drawable drawable = new BitmapDrawable(btPersonalHead);
				myselfHead.setImageDrawable(drawable);


			}else {
				myselfHead.setImageResource(R.drawable.beautiful);
			}	

		} catch (OutOfMemoryError  e) {
			e.printStackTrace();
		}



		Head.setOnClickListener(this);


	}

	private void initListing() {



		myselfList = new ArrayList<MySelfListing>();
		goodsDHelper = new GoodsDataHelper(getActivity());
		myselfList = goodsDHelper.getGoodsInfoList();




		ShareAdapter shareAdapter = new ShareAdapter(myselfList);
		myselfListing.setAdapter(shareAdapter);
		myselfListing.setDivider(null);//消除listview横线


	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.myself_news_username:
		case R.id.myself_news_head_image:

			Intent intentHead = new Intent(getActivity(),PersonalleftActivity.class);
			startActivity(intentHead);
			getActivity().finish();

			break;

		default:
			break;
		}
	}

	class ShareAdapter extends BaseAdapter {

		private List<MySelfListing> mList  = new ArrayList<MySelfListing>();

		private LayoutInflater mInflater;

		private Bitmap bt;

		private Bitmap btUP;



		public ShareAdapter(List<MySelfListing> mList) {
			this.mList = mList;

			mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}


		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView( final int position, View convertView, ViewGroup parent) {



			goodsDHelper = new GoodsDataHelper(getActivity());
			mList = new ArrayList<MySelfListing>();
			mList = goodsDHelper.getGoodsInfoList();

			ViewHolder holder = null;
			if (convertView == null) {

				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.myself_layout_list_share, null);
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

			Logger.d("\""+mList.get(position).getUserIcon()+"\"");

			try {
				btUP = BitmapFactory.decodeFile(mList.get(position).getUserIcon());
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
			} catch (OutOfMemoryError  e) {
				e.printStackTrace();
			}

			holder.ivUserPohto.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					//					Utils.showToast(getActivity(), "用户名："+mList.get(position).getUserName()+" 学号："+mList.get(position).getStudentNumber());

					Intent intent = new Intent(getActivity(),UserIntroActivity.class);
					intent.putExtra("studentNumber", mList.get(position).getStudentNumber()+"");
					startActivity(intent);
					getActivity().finish();

					// 先判断是否已经回收
					if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
						// 回收并且置为null
						btPersonalHead.recycle(); 
						btPersonalHead = null; 
					} 
					System.gc();
					// 先判断是否已经回收
					if(btUP != null && !btUP.isRecycled()){ 
						// 回收并且置为null
						btUP.recycle(); 
						btUP = null; 
					} 
					System.gc();
					// 先判断是否已经回收
					if(bt != null && !bt.isRecycled()){ 
						// 回收并且置为null
						bt.recycle(); 
						bt = null; 
					} 
					System.gc();

				}
			});

			//用户名

			holder.tvuserName.setText(mList.get(position).getUserName());
			holder.tvuserName.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
				}
			});

			//用户发布商品信息
			holder.tvshare.setText(mList.get(position).getGoodsIntro());
			holder.tvshare.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
				}
			});

			//用户发布商品图片


			Logger.d("\""+mList.get(position).getGoodsImages()+"\"");

			try {
				bt = BitmapFactory.decodeFile(mList.get(position).getGoodsImages());
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




			} catch (OutOfMemoryError  e) {
				e.printStackTrace();
			}

			holder.ivPohot.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
				}
			});


			//显示时间
			holder.tvDate.setText(mList.get(position).getGoodsDate()+"");


			//查看商品详情的textview

			holder.tvDetailed.setText("点击查看商品详情");
			holder.tvDetailed.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(),HomeGoodsGVActivity.class);
					intent.putExtra("int","myselfListciew:"+position);
					intent.putExtra("goodsName", ""+mList.get(position).getGoodsName());
					intent.putExtra("studentNumber", ""+mList.get(position).getStudentNumber());
					startActivity(intent);
					getActivity().finish();
					Logger.d(position+"");
					
					
					// 先判断是否已经回收
					if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
						// 回收并且置为null
						btPersonalHead.recycle(); 
						btPersonalHead = null; 
					} 
					System.gc();
					// 先判断是否已经回收
					if(btUP != null && !btUP.isRecycled()){ 
						// 回收并且置为null
						btUP.recycle(); 
						btUP = null; 
					} 
					System.gc();
					// 先判断是否已经回收
					if(bt != null && !bt.isRecycled()){ 
						// 回收并且置为null
						bt.recycle(); 
						bt = null; 
					} 
					System.gc();
					
				}
			});
			//喜欢




			final String etGoodsName = mList.get(position).getGoodsName()+"";



			holder.ivLike.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String isLike = null ;
					count++;
					if (count % 2 == 0) {

						Message message = handler.obtainMessage();
						message.what = 1;
						handler.sendMessage(message);

						isLike = "N";
						goodsDHelper = new GoodsDataHelper(getActivity());
						mList = new ArrayList<MySelfListing>();
						mList = goodsDHelper.getGoodsInfoList();
						goodsDHelper.updateGoods(isLike, etGoodsName,null, 6);
					}else {

						Message message = handler.obtainMessage();
						message.what = 1;
						handler.sendMessage(message);

						isLike = "Y";
						goodsDHelper = new GoodsDataHelper(getActivity());
						mList = new ArrayList<MySelfListing>();
						mList = goodsDHelper.getGoodsInfoList();
						goodsDHelper.updateGoods(isLike, etGoodsName,null, 6);
					}

				}		
			});





			goodsDHelper = new GoodsDataHelper(getActivity());
			mList = new ArrayList<MySelfListing>();
			mList = goodsDHelper.getGoodsInfoList();

			String strInt = mList.get(position).getGoodsAttention()+"";


			if(strInt.equals("Y")){
				holder.ivLike.setImageResource(R.drawable.ic_thanked);
			}else {
				holder.ivLike.setImageResource(R.drawable.ic_thank);
			}

			return convertView;
		}

		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}

		@Override
		public boolean isEnabled(int position)  {
			return false;
		}


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
