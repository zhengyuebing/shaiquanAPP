package jxnu.n433.x3107.SunGroup.OtherActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.SunGroup.MainActivity;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.SunGroup.WelComeActivity;
import jxnu.n433.x3107.bean.AttentionUser;
import jxnu.n433.x3107.bean.MySelfListing;
import jxnu.n433.x3107.bean.UserInfo;
import jxnu.n433.x3107.sqlite.AttentionUserDataHelper;
import jxnu.n433.x3107.sqlite.GoodsDataHelper;
import jxnu.n433.x3107.sqlite.UserInfoDataHelper;
import jxnu.n433.x3107.utils.Utils;

public class HomeGoodsGVActivity extends Activity implements OnClickListener{

	private ImageView ivBack;
	private ImageView ivAttention;
	private TextView tvGoodsName;
	private ImageView ivGoodsImage;
	private TextView tvPrice;
	private TextView tvGoodsIntro;
	private TextView tvUserIntro;
	private String strImage;


	private List<MySelfListing> mslList;
	private GoodsDataHelper goodsDHelper;
	private List<UserInfo> userInfoList;
	private UserInfoDataHelper userInfoDHelper;
	private List<AttentionUser> aUsers;
	private AttentionUserDataHelper aDHelper;


	private String intIntent;
	private String goodsName;
	private String studentNumber;

	private SharedPreferences sPreferences;
	private Bitmap btPersonalHead;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home_goods_gv);

		Intent intent= getIntent();
		intIntent = intent.getStringExtra("int");
		goodsName = intent.getStringExtra("goodsName");
		studentNumber = intent.getStringExtra("studentNumber");
		Utils.showToast(getApplicationContext(), intIntent+" "+goodsName+" "+studentNumber+" ");

		sPreferences = getSharedPreferences(WelComeActivity.STUDENTNUMBER, 0);

		initView();

		initShowIntro();

	}


	private void initView() {
		ivBack = (ImageView) findViewById(R.id.home_goods_gv_activity_iv_back);

		ivAttention = (ImageView) findViewById(R.id.home_goods_gv_activity_iv_user_name_shopping);
		tvGoodsName = (TextView) findViewById(R.id.home_goods_gv_activity_tv_goods_name);
		ivGoodsImage = (ImageView) findViewById(R.id.home_goods_gv_activity_iv_goods_image);
		tvPrice = (TextView) findViewById(R.id.home_goods_gv_activity_tv_goods_price);
		tvGoodsIntro = (TextView) findViewById(R.id.home_goods_gv_activity_tv_goods_intro);
		tvUserIntro = (TextView) findViewById(R.id.home_goods_gv_activity_tv_user_intro);

		ivBack.setOnClickListener(this);
		ivAttention.setOnClickListener(this);
		tvUserIntro.setOnClickListener(this);
	}

	private void initShowIntro() {
		goodsDHelper = new GoodsDataHelper(getApplicationContext());
		mslList = new ArrayList<MySelfListing>();
		mslList = goodsDHelper.getGoodsInfoList(studentNumber, goodsName,1);

		userInfoDHelper = new UserInfoDataHelper(getApplicationContext());
		userInfoList = new ArrayList<UserInfo>();
		userInfoList = userInfoDHelper.getUserInfoList(studentNumber);

		for(int i = 0; i < mslList.size(); i++){
			if (studentNumber.equals(mslList.get(i).getStudentNumber()) && goodsName.equals(mslList.get(i).getGoodsName())) {
				tvGoodsName.setText(mslList.get(i).getGoodsName()+"");
				tvPrice.setText("￥"+mslList.get(i).getGoodsPrice()+"");
				tvGoodsIntro.setText(mslList.get(i).getGoodsIntro()+"");

				strImage = mslList.get(i).getGoodsImages();

			}
		}

		try {
			btPersonalHead = BitmapFactory.decodeFile(strImage);
			if (btPersonalHead != null) {
				@SuppressWarnings("deprecation")
				Drawable drawable = new BitmapDrawable(btPersonalHead);
				ivGoodsImage.setImageDrawable(drawable);
			}else {
				ivGoodsImage.setImageResource(R.drawable.beautiful);
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}

		if (sPreferences.getString("sn", "").equals("")) {
			tvUserIntro.setVisibility(View.GONE);
			tvUserIntro.setEnabled(false);
		}else {
			tvUserIntro.setVisibility(View.VISIBLE);
			tvUserIntro.setEnabled(true);
			tvUserIntro.setText(
					"发布者"+"\n"
							+"用户名："+userInfoList.get(0).getUserName()+"\n"
							+"电话号码："+userInfoList.get(0).getPhoneNumber()+"\n"
							+"QQ："+userInfoList.get(0).getUserQQ()+"\n"
							+"邮箱："+userInfoList.get(0).getUserMailBox()+"\n"
							+"学校："+userInfoList.get(0).getUserSchool());
		}


	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_goods_gv_activity_iv_back:

			Intent intentBack = new Intent(HomeGoodsGVActivity.this,MainActivity.class);
			startActivity(intentBack);
			finish();
			
			
			// 先判断是否已经回收
			if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
				// 回收并且置为null
				btPersonalHead.recycle(); 
				btPersonalHead = null; 
			} 
			System.gc();
			
			break;
		case R.id.home_goods_gv_activity_iv_user_name_shopping:

			if (sPreferences.getString("sn", "").equals("")) {
				Utils.showToast(getApplicationContext(), "未登录账号，无法收藏");
				return;
			}

			mslList = new ArrayList<MySelfListing>();
			goodsDHelper = new GoodsDataHelper(getApplicationContext());

			goodsDHelper.updateGoods(sPreferences.getString("sn","")+"", goodsName, studentNumber, 11);

			Utils.showToast(getApplicationContext(), "收藏成功");

			break;
		case R.id.home_goods_gv_activity_tv_user_intro:



			if (sPreferences.getString("sn", "").equals("")) {
				Utils.showToast(getApplicationContext(), "未登录账号，无法关注");
				return;
			}

			aUsers = new ArrayList<AttentionUser>();
			aDHelper = new AttentionUserDataHelper(getApplicationContext());
			aUsers = aDHelper.getAttentionList(sPreferences.getString("sn", "")+"");

			for(int l = 0; l < aUsers.size(); l++){
				
				if (studentNumber.equals(sPreferences.getString("sn", ""))) {
					Utils.showToast(getApplicationContext(), "无法关注自己");
					return;
				}
				
				if (studentNumber.equals(aUsers.get(l).getUserAttention())) {
					
					aDHelper.deleteAttention(sPreferences.getString("sn", ""), studentNumber);
					
					Utils.showToast(getApplicationContext(), "取消关注该用户");
					
					aUsers = new ArrayList<AttentionUser>();
					aDHelper = new AttentionUserDataHelper(getApplicationContext());
					aUsers = aDHelper.getAttentionList(sPreferences.getString("sn", "")+"");
					Logger.d("关注的数目"+aUsers.size());
					return;
				}

				
			}

			AttentionUser attentionUser = new AttentionUser();

			attentionUser.setStudentNumber(sPreferences.getString("sn","")+"");
			attentionUser.setUserAttention(studentNumber+"");

			aDHelper.insertAttention(attentionUser);


			aUsers = new ArrayList<AttentionUser>();
			aDHelper = new AttentionUserDataHelper(getApplicationContext());
			aUsers = aDHelper.getAttentionList(sPreferences.getString("sn", "")+"");
			Logger.d("关注的数目"+aUsers.size());

			Utils.showToast(getApplicationContext(), "关注用户成功");

			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intentBack = new Intent(HomeGoodsGVActivity.this,MainActivity.class);
			startActivity(intentBack);
			finish();
			
			// 先判断是否已经回收
			if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
				// 回收并且置为null
				btPersonalHead.recycle(); 
				btPersonalHead = null; 
			} 
			System.gc();
			
			return false;
		}

		return super.onKeyDown(keyCode, event);
	}


}
