package jxnu.n433.x3107.SunGroup.OtherActivity;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import jxnu.n433.x3107.SunGroup.MainActivity;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.SunGroup.WelComeActivity;
import jxnu.n433.x3107.SunGroup.OtherActivity.View.LeftBtnClickListener;
import jxnu.n433.x3107.SunGroup.OtherActivity.View.PersonalleftDialog;
import jxnu.n433.x3107.bean.MySelfListing;
import jxnu.n433.x3107.bean.UserInfo;
import jxnu.n433.x3107.sqlite.GoodsDataHelper;
import jxnu.n433.x3107.sqlite.UserInfoDataHelper;
import jxnu.n433.x3107.utils.Utils;

public class PersonalleftActivity extends Activity implements OnClickListener{

	private ImageView leftPersonalback;

	private ImageView leftPersonalIViewHead;//头像
	private RelativeLayout Head;

	public TextView pName;//昵称
	public TextView pSexs;//性别
	public TextView pSite;//地址
	public TextView pIntro;//简介
	public TextView pSchool;//学校
	public TextView pBirthday;//生日
	public TextView pQQ;//QQ
	public TextView pMailBox;//邮箱
	public TextView pPhoneNumber;
	public RelativeLayout personalSexs,
			personalSite,
			personalIntro,
			personalSchool,
			personalBirthday,
			personalQQ,
			personalMailBox,
			personalPhomeNumber;//信息点击修改

	private DatePickerDialog dialogBirthday;
	private int  year,monthofyear,dayofMonth;
	private String textBirthday;
	
	private UserInfoDataHelper userinfoDH ;
	private List<UserInfo> userInfoList ;
	
	private GoodsDataHelper goodsDHelper;
	private List<MySelfListing> mslList;


	public PersonalleftDialog dialogOut;
	private Context mContext;

	private SharedPreferences sPreferences;


	public static String sqliteStudentNumber;

	private Bitmap btPersonalHead;
	public static String getSqliteStudentNumber() {
		return sqliteStudentNumber;
	}
	public static void setSqliteStudentNumber(String sqliteStudentNumber) {
		PersonalleftActivity.sqliteStudentNumber = sqliteStudentNumber;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_personal_left);
		mContext = this;



		sPreferences = getSharedPreferences(WelComeActivity.STUDENTNUMBER,0);
		initTitle();

		initImageViewHead();//头像



		initReativeLayout();//修改资料

		initReativeLayoutFirst();

	}


	private void initTitle() {
		leftPersonalback = (ImageView) findViewById(R.id.personal_left_iv_back_personal);
		leftPersonalback.setOnClickListener(this);

	}
	//头像
	@SuppressWarnings("deprecation")
	private void initImageViewHead() {


		leftPersonalIViewHead = (ImageView) findViewById(R.id.zdy_image_personal);
		Head = (RelativeLayout) findViewById(R.id.personal_left_iv_head_personal);

		userInfoList = new ArrayList<UserInfo>();
		userinfoDH = new UserInfoDataHelper(getApplicationContext());
		userInfoList = userinfoDH.getUserInfoList();
		String strImage = null;

		for (int i = 0; i < userInfoList.size(); i++) {
			if (sPreferences.getString("sn", "").equals(userInfoList.get(i).getStudentNumber())) {
				strImage = userInfoList.get(i).getUserIcon()+"";
			}else {
			}
		}

		try {
			btPersonalHead = BitmapFactory.decodeFile(strImage);
			if (btPersonalHead != null) {
				Drawable drawable = new BitmapDrawable(btPersonalHead);
				leftPersonalIViewHead.setImageDrawable(drawable);
			}else {
				leftPersonalIViewHead.setImageResource(R.drawable.beautiful);
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}

		Head.setOnClickListener(this);


	}

	private void initReativeLayout() {//修改资料

		pName = (TextView) findViewById(R.id.personal_left_tv_name_personal);
		pSexs = (TextView) findViewById(R.id.sexs_personal);
		pSite = (TextView) findViewById(R.id.sites_personal);
		pIntro = (TextView) findViewById(R.id.intros_personal);
		pSchool = (TextView) findViewById(R.id.school_personal);
		pBirthday = (TextView) findViewById(R.id.birthday_personal);
		pQQ = (TextView) findViewById(R.id.qq_personal);
		pMailBox = (TextView) findViewById(R.id.mailbox_personal);
		pPhoneNumber = (TextView) findViewById(R.id.phonenumber_personal);
		personalSexs = (RelativeLayout) findViewById(R.id.personal_left_layout_sexs_personal);
		personalSite = (RelativeLayout) findViewById(R.id.personal_left_layout_site_personal);
		personalIntro = (RelativeLayout) findViewById(R.id.personal_left_layout_intro_personal);
		personalSchool = (RelativeLayout) findViewById(R.id.personal_left_layout_school_personal);
		personalBirthday = (RelativeLayout) findViewById(R.id.personal_left_layout_birthday_personal);
		personalQQ = (RelativeLayout) findViewById(R.id.personal_left_layout_qq_personal);
		personalMailBox = (RelativeLayout) findViewById(R.id.personal_left_layout_mailbox_personal);
		personalPhomeNumber = (RelativeLayout) findViewById(R.id.personal_left_layout_phonenumber_personal);

		pName.setOnClickListener(this);
		personalSexs.setOnClickListener(this);
		personalSite.setOnClickListener(this);
		personalIntro.setOnClickListener(this);
		personalSchool.setOnClickListener(this);
		personalBirthday.setOnClickListener(this);
		personalQQ.setOnClickListener(this);
		personalMailBox.setOnClickListener(this);
		personalPhomeNumber.setOnClickListener(this);

	}
	private void initReativeLayoutFirst() {
		userinfoDH = new UserInfoDataHelper(getApplicationContext());
		userInfoList = new ArrayList<UserInfo>();
		userInfoList = userinfoDH.getUserInfoList();
		for(int i = 0;i < userInfoList.size();i++){
			if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
				pName.setText(userInfoList.get(i).getUserName()+"");
				pSexs.setText(userInfoList.get(i).getUserSexs()+"");
				pSite.setText(userInfoList.get(i).getUserSite()+"");
				pIntro.setText(userInfoList.get(i).getUserIntro()+"");
				pSchool.setText(userInfoList.get(i).getUserSchool()+"");
				pBirthday.setText(userInfoList.get(i).getUserBirthday()+"");
				pQQ.setText(userInfoList.get(i).getUserQQ()+"");
				pMailBox.setText(userInfoList.get(i).getUserMailBox()+"");
				pPhoneNumber.setText(userInfoList.get(i).getPhoneNumber()+"");
			}else {
			}
		}
	}
	@SuppressLint("ResourceAsColor")
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.personal_left_iv_head_personal:
			intent.setClass(PersonalleftActivity.this,PictureHeadActivity.class);
			startActivity(intent);
			finish();
			
			
			// 先判断是否已经回收
			if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
				// 回收并且置为null
				btPersonalHead.recycle(); 
				btPersonalHead = null; 
			} 
			System.gc();
			break;
		case R.id.personal_left_iv_back_personal:
			intent.setClass(PersonalleftActivity.this,MainActivity.class);
			startActivity(intent);
			finish();
			
			// 先判断是否已经回收
			if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
				// 回收并且置为null
				btPersonalHead.recycle(); 
				btPersonalHead = null; 
			} 
			System.gc();
			
			break;
		case R.id.personal_left_tv_name_personal:

			dialogOut = new PersonalleftDialog(mContext);
			dialogOut.setTitleInCenter();
			dialogOut.setDialogTitle("编辑用户名(12字内)");
			dialogOut.setDialogTitleSize(R.dimen.x16);
			dialogOut.setDialogTitleBacColor(R.color.bisque);


			userinfoDH = new UserInfoDataHelper(getApplicationContext());
			userInfoList = new ArrayList<UserInfo>();
			userInfoList = userinfoDH.getUserInfoList();
			for(int i = 0;i<userInfoList.size();i++){
				if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
					dialogOut.setDialogContent(userInfoList.get(i).getUserName()+"");
				}
			}


			//			
			dialogOut.setDialogContentLenght(12);
			dialogOut.setLeftBtnListener(new LeftBtnClickListener(mContext,dialogOut,1));
			dialogOut.setRightBtnListener(new RightBtnClickListener(mContext,dialogOut,1));
			dialogOut.show();

			break;
		case R.id.personal_left_layout_sexs_personal:

			dialogOut = new PersonalleftDialog(mContext);
			dialogOut.setTitleInCenter();
			dialogOut.setDialogTitle("编辑性别(男或女)");
			dialogOut.setDialogTitleSize(R.dimen.x16);
			dialogOut.setDialogTitleBacColor(R.color.bisque);


			userinfoDH = new UserInfoDataHelper(getApplicationContext());
			userInfoList = new ArrayList<UserInfo>();
			userInfoList = userinfoDH.getUserInfoList();
			for(int i = 0;i<userInfoList.size();i++){
				if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
					dialogOut.setDialogContent(userInfoList.get(i).getUserSexs()+"");
				}
			}


			dialogOut.setDialogContentLenght(1);//长度
			dialogOut.setLeftBtnListener(new LeftBtnClickListener(mContext,dialogOut,2));
			dialogOut.setRightBtnListener(new RightBtnClickListener(mContext,dialogOut,2));
			dialogOut.show();

			break;
		case R.id.personal_left_layout_site_personal:

			dialogOut = new PersonalleftDialog(mContext);
			dialogOut.setTitleInCenter();
			dialogOut.setDialogTitle("编辑地址(省市县/区)");
			dialogOut.setDialogTitleSize(R.dimen.x16);
			dialogOut.setDialogTitleBacColor(R.color.bisque);

			userinfoDH = new UserInfoDataHelper(getApplicationContext());
			userInfoList = new ArrayList<UserInfo>();
			userInfoList = userinfoDH.getUserInfoList();
			for(int i = 0;i<userInfoList.size();i++){
				if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
					dialogOut.setDialogContent(userInfoList.get(i).getUserSite()+"");
				}
			}


			dialogOut.setLeftBtnListener(new LeftBtnClickListener(mContext,dialogOut,3));
			dialogOut.setRightBtnListener(new RightBtnClickListener(mContext,dialogOut,3));
			dialogOut.show();

			break;
		case R.id.personal_left_layout_intro_personal:

			dialogOut = new PersonalleftDialog(mContext);
			dialogOut.setTitleInCenter();
			dialogOut.setDialogTitle("编辑简介(30字内)");
			dialogOut.setDialogTitleSize(R.dimen.x16);
			dialogOut.setDialogTitleBacColor(R.color.bisque);

			userinfoDH = new UserInfoDataHelper(getApplicationContext());
			userInfoList = new ArrayList<UserInfo>();
			userInfoList = userinfoDH.getUserInfoList();
			for(int i = 0;i<userInfoList.size();i++){
				if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
					dialogOut.setDialogContent(userInfoList.get(i).getUserIntro()+"");
				}
			}

			dialogOut.setDialogContentLenght(30);
			dialogOut.setLeftBtnListener(new LeftBtnClickListener(mContext,dialogOut,4));
			dialogOut.setRightBtnListener(new RightBtnClickListener(mContext,dialogOut,4));
			dialogOut.show();

			break;
		case R.id.personal_left_layout_school_personal:

			dialogOut = new PersonalleftDialog(mContext);
			dialogOut.setTitleInCenter();
			dialogOut.setDialogTitle("编辑学校");
			dialogOut.setDialogTitleSize(R.dimen.x16);
			dialogOut.setDialogTitleBacColor(R.color.bisque);

			userinfoDH = new UserInfoDataHelper(getApplicationContext());
			userInfoList = new ArrayList<UserInfo>();
			userInfoList = userinfoDH.getUserInfoList();
			for(int i = 0;i<userInfoList.size();i++){
				if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
					dialogOut.setDialogContent(userInfoList.get(i).getUserSchool()+"");
				}
			}

			dialogOut.setLeftBtnListener(new LeftBtnClickListener(mContext,dialogOut,5));
			dialogOut.setRightBtnListener(new RightBtnClickListener(mContext,dialogOut,5));
			dialogOut.show();

			break;
		case R.id.personal_left_layout_birthday_personal:

			/*	dialogOut = new PersonalleftDialog(mContext);
			dialogOut.setTitleInCenter();
			dialogOut.setDialogTitle("编辑生日(xxxx-x-x)");
			dialogOut.setDialogTitleSize(R.dimen.x16);
			dialogOut.setDialogTitleBacColor(R.color.bisque);

			userinfoDH = new UserInfoDataHelper(getApplicationContext());
			userInfoList = new ArrayList<UserInfo>();
			userInfoList = userinfoDH.getUserInfoList();
			for(int i = 0;i<userInfoList.size();i++){
				if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
					dialogOut.setDialogContent(userInfoList.get(i).getUserBirthday()+"");
				}
			}

			dialogOut.setLeftBtnListener(new LeftBtnClickListener(mContext,dialogOut,6));
			dialogOut.setRightBtnListener(new RightBtnClickListener(mContext,dialogOut,6));
			dialogOut.show();*/


			
			Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
			monthofyear = calendar.get(Calendar.MONTH);
			dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
			dialogBirthday = new DatePickerDialog(this, new OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					int month = monthOfYear+1;
					textBirthday = year+"-"+month+"-"+dayOfMonth+"";
					userInfoList = new ArrayList<UserInfo>();
					userinfoDH = new UserInfoDataHelper(getApplicationContext());
					userinfoDH.updateUserInfo(textBirthday, sPreferences.getString("sn", ""), 6);
					userInfoList = userinfoDH.getUserInfoList();

					for(int i = 0;i < userInfoList.size();i++){
						if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
							pBirthday.setText(userInfoList.get(i).getUserBirthday()+"");
						}
					}
				}
			}, year, monthofyear, dayofMonth);

			dialogBirthday.show();
			
			

			break;
		case R.id.personal_left_layout_qq_personal:

			dialogOut = new PersonalleftDialog(mContext);
			dialogOut.setTitleInCenter();
			dialogOut.setDialogTitle("编辑QQ");
			dialogOut.setDialogTitleSize(R.dimen.x16);
			dialogOut.setDialogTitleBacColor(R.color.bisque);

			userinfoDH = new UserInfoDataHelper(getApplicationContext());
			userInfoList = new ArrayList<UserInfo>();
			userInfoList = userinfoDH.getUserInfoList();
			for(int i = 0;i<userInfoList.size();i++){
				if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
					dialogOut.setDialogContent(userInfoList.get(i).getUserQQ()+"");
				}
			}

			dialogOut.setLeftBtnListener(new LeftBtnClickListener(mContext,dialogOut,7));
			dialogOut.setRightBtnListener(new RightBtnClickListener(mContext,dialogOut,7));
			dialogOut.show();

			break;
		case R.id.personal_left_layout_mailbox_personal:



			dialogOut = new PersonalleftDialog(mContext);
			dialogOut.setTitleInCenter();
			dialogOut.setDialogTitle("编辑邮箱");
			dialogOut.setDialogTitleSize(R.dimen.x16);
			dialogOut.setDialogTitleBacColor(R.color.bisque);

			userinfoDH = new UserInfoDataHelper(getApplicationContext());
			userInfoList = new ArrayList<UserInfo>();
			userInfoList = userinfoDH.getUserInfoList();
			for(int i = 0;i<userInfoList.size();i++){
				if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
					dialogOut.setDialogContent(userInfoList.get(i).getUserMailBox()+"");
				}
			}

			dialogOut.setLeftBtnListener(new LeftBtnClickListener(mContext,dialogOut,8));
			dialogOut.setRightBtnListener(new RightBtnClickListener(mContext,dialogOut,8));
			dialogOut.show();

			break;
		case R.id.personal_left_layout_phonenumber_personal:


			userInfoList = new ArrayList<UserInfo>();
			userinfoDH = new UserInfoDataHelper(getApplicationContext());
			userInfoList = userinfoDH.getUserInfoList();

			Intent intentPNumber = new Intent(PersonalleftActivity.this,PersonalleftAlterPhoneActivity.class);
			startActivity(intentPNumber);
			finish();
			
			
			// 先判断是否已经回收
			if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
				// 回收并且置为null
				btPersonalHead.recycle(); 
				btPersonalHead = null; 
			} 
			System.gc();
			break;
		default:
			break;
		}
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK ) { 
			Intent intent = new Intent();
			intent.setClass(PersonalleftActivity.this,MainActivity.class);
			startActivity(intent);
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

	public class RightBtnClickListener implements OnClickListener{

		private PersonalleftDialog dialog;
		private int n;
		private Context mContext;

		public RightBtnClickListener(Context mContext,PersonalleftDialog dialog,int n) {
			super();
			this.mContext = mContext;
			this.dialog = dialog;
			this.n = n;
		}

		@Override
		public void onClick(View v) {
			//			Utils.showToast(mContext, "点中了右边的按钮!");

			switch (n) {
			case 1: 
				if(dialog.getDialogContent().equals("") || dialog.getDialogContent() == null){

					Utils.showToast(getApplicationContext(), "请输入用户名");

					return;
				}
				userInfoList = new ArrayList<UserInfo>();
				UserInfo userInfo1 = new UserInfo();
				userInfo1.setUserName(dialog.getDialogContent());
				String userName = dialog.getDialogContent()+"";
				userinfoDH = new UserInfoDataHelper(getApplicationContext());
				userinfoDH.updateUserInfo(userName, sPreferences.getString("sn", ""), 1);
				userInfoList = userinfoDH.getUserInfoList();

				for(int i = 0;i < userInfoList.size();i++){
					if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
						pName.setText(userInfoList.get(i).getUserName()+"");
					}
				}
				
				mslList = new ArrayList<MySelfListing>();
				goodsDHelper = new GoodsDataHelper(getApplicationContext());
				mslList = goodsDHelper.getGoodsInfoList(sPreferences.getString("sn", ""),1);
				
				if (mslList.size() != 0) {
					for(int i = 0; i < mslList.size(); i++){
						if (mslList.get(i).getStudentNumber().equals(sPreferences.getString("sn","")+"")) {
							goodsDHelper.updateGoods(userName, null, sPreferences.getString("sn","")+"", 7);
						}
					}
				}
				
				dialog.dismiss();
				break;
			case 2:
				if (dialog.getDialogContent().equals("男")||dialog.getDialogContent().equals("女")) {

					userInfoList = new ArrayList<UserInfo>();
					String usersexs = dialog.getDialogContent();
					userinfoDH = new UserInfoDataHelper(getApplicationContext());
					userinfoDH.updateUserInfo(usersexs, sPreferences.getString("sn", ""), 2);
					userInfoList = userinfoDH.getUserInfoList();

					for(int i = 0;i < userInfoList.size();i++){
						if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
							pSexs.setText(userInfoList.get(i).getUserSexs()+"");
						}
					}
					dialog.dismiss();

				}else {
					Utils.showToast(mContext, "输出错误");
				}
				break;
			case 3:

				if (dialog.getDialogContent().equals("") || dialog.getDialogContent() == null){
					Utils.showToast(mContext, "输出错误");
					return;
				}
				userInfoList = new ArrayList<UserInfo>();
				String usersite = dialog.getDialogContent();
				userinfoDH = new UserInfoDataHelper(getApplicationContext());
				userinfoDH.updateUserInfo(usersite, sPreferences.getString("sn", ""), 3);
				userInfoList = userinfoDH.getUserInfoList();

				for(int i = 0;i < userInfoList.size();i++){
					if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
						pSite.setText(userInfoList.get(i).getUserSite()+"");
					}
				}
				dialog.dismiss();
				break;
			case 4:
				if (dialog.getDialogContent().equals("") || dialog.getDialogContent() == null){
					Utils.showToast(mContext, "输出错误");
					return;
				}

				userInfoList = new ArrayList<UserInfo>();
				String userintro = dialog.getDialogContent();
				userinfoDH = new UserInfoDataHelper(getApplicationContext());
				userinfoDH.updateUserInfo(userintro, sPreferences.getString("sn", ""), 4);
				userInfoList = userinfoDH.getUserInfoList();

				for(int i = 0;i < userInfoList.size();i++){
					if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
						pIntro.setText(userInfoList.get(i).getUserIntro()+"");
					}
				}
				dialog.dismiss();

				break;
			case 5:
				if (dialog.getDialogContent().equals("") || dialog.getDialogContent() == null){
					Utils.showToast(mContext, "输出错误");
					return;
				}

				userInfoList = new ArrayList<UserInfo>();
				String userschool = dialog.getDialogContent();
				userinfoDH = new UserInfoDataHelper(getApplicationContext());
				userinfoDH.updateUserInfo(userschool, sPreferences.getString("sn", ""), 5);
				userInfoList = userinfoDH.getUserInfoList();

				for(int i = 0;i < userInfoList.size();i++){
					if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
						pSchool.setText(userInfoList.get(i).getUserSchool()+"");
					}
				}
				dialog.dismiss();
				break;
			case 6:
				if (dialog.getDialogContent().equals("") || dialog.getDialogContent() == null){
					Utils.showToast(mContext, "输出错误");
					return;
				}

				userInfoList = new ArrayList<UserInfo>();
				String birthday = dialog.getDialogContent();
				userinfoDH = new UserInfoDataHelper(getApplicationContext());
				userinfoDH.updateUserInfo(birthday, sPreferences.getString("sn", ""), 6);
				userInfoList = userinfoDH.getUserInfoList();

				for(int i = 0;i < userInfoList.size();i++){
					if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
						pBirthday.setText(userInfoList.get(i).getUserBirthday()+"");
					}
				}
				dialog.dismiss();
				break;
			case 7:
				if (dialog.getDialogContent().equals("") || dialog.getDialogContent() == null){
					Utils.showToast(mContext, "输出错误");
					return;
				}

				userInfoList = new ArrayList<UserInfo>();
				String qq = dialog.getDialogContent();
				userinfoDH = new UserInfoDataHelper(getApplicationContext());
				userinfoDH.updateUserInfo(qq, sPreferences.getString("sn", ""), 7);
				userInfoList = userinfoDH.getUserInfoList();

				for(int i = 0;i < userInfoList.size();i++){
					if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
						pQQ.setText(userInfoList.get(i).getUserQQ()+"");
					}
				}
				dialog.dismiss();
				break;
			case 8:

				String mailbox = dialog.getDialogContent();

				if (dialog.getDialogContent().equals("") || dialog.getDialogContent() == null){
					Utils.showToast(mContext, "输出错误");
					return;
				}
				if (isMailBox(mailbox)) {



					userInfoList = new ArrayList<UserInfo>();

					userinfoDH = new UserInfoDataHelper(getApplicationContext());
					userinfoDH.updateUserInfo(mailbox, sPreferences.getString("sn", ""), 8);
					userInfoList = userinfoDH.getUserInfoList();

					for(int i = 0;i < userInfoList.size();i++){
						if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
							pMailBox.setText(userInfoList.get(i).getUserMailBox()+"");
						}
					}
					dialog.dismiss();
				}else {
					Utils.showToast(getApplicationContext(), "输入错误");
				}
				break;
			case 9:
				if (dialog.getDialogContent().equals("") || dialog.getDialogContent() == null){
					Utils.showToast(mContext, "输出错误");
					return;
				}

				userInfoList = new ArrayList<UserInfo>();
				String phonenumber = dialog.getDialogContent();
				userinfoDH = new UserInfoDataHelper(getApplicationContext());
				userinfoDH.updateUserInfo(phonenumber, sPreferences.getString("sn", ""), 9);
				userInfoList = userinfoDH.getUserInfoList();

				for(int i = 0;i < userInfoList.size();i++){
					if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
						pPhoneNumber.setText(userInfoList.get(i).getPhoneNumber()+"");
					}
				}
				dialog.dismiss();
				break;
			default:
				break;
			}


		}

	}

	/*
	 * 验证邮箱
	 * */
	private boolean isMailBox(String mail){
		Pattern pattern = Pattern.compile("^[A-Za-z0-9][\\w\\._]*[a-zA-Z0-9]+@[A-Za-z0-9-_]+\\.([A-Za-z]{2,4})");
		Matcher matcher = pattern.matcher(mail);
		return matcher.matches();
	}


}
