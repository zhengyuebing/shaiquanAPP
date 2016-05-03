package jxnu.n433.x3107.SunGroup;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import jxnu.n433.x3107.Fragment.HomePageFragment;
import jxnu.n433.x3107.Fragment.MessageFragment;
import jxnu.n433.x3107.Fragment.PersonalView;
import jxnu.n433.x3107.Fragment.ShoppingFragment;
import jxnu.n433.x3107.SunGroup.OtherActivity.MerchandiseleftActivity;
import jxnu.n433.x3107.SunGroup.OtherActivity.PersonalleftActivity;
import jxnu.n433.x3107.SunGroup.OtherActivity.SettingleftActivity;
import jxnu.n433.x3107.SunGroup.View.SlidingMenu;
import jxnu.n433.x3107.bean.UserInfo;
import jxnu.n433.x3107.sqlite.UserInfoDataHelper;
import jxnu.n433.x3107.utils.Utils;
import android.view.KeyEvent;

public class MainActivity extends Activity implements android.view.View.OnClickListener{

	//点击返回退出
	private long mExitTime;
	//	首页fragment
	private  HomePageFragment homepageFragment;
	//	购物车fragment
	private ShoppingFragment shoppingFragment;
	//	消息fragment
	private MessageFragment messageFragment;
	//	我的fragment
	private PersonalView myselfFragment;
	//	首页布局
	private  View homepageLayout;
	//	购物车布局
	private View shoppingLayout;
	//	首页标签图片
	private ImageView homepageImage;
	//	购物车标签图片
	private ImageView shoppingImage;
	//	消息标签图片
	private ImageView messageImage;
	//	我的标签图片
	private ImageView myselfImage;
	//	首页标签文字
	private TextView homgpageText;
	//	购物车标签文字
	private TextView shoppingText;
	//	消息标签文字
	private TextView messageText;
	//	我的标签文字
	private TextView myselfText;
	//	 用于对Fragment进行管理
	private FragmentManager fragmentManager;
	//侧滑面板
	private SlidingMenu slidingMenu;
	//侧面板点击头像
	private ImageView userLogo;
	private RelativeLayout Head;
	private View leftPersonal;
	private View leftMerchandise;
	private View leftSetting;
	private View leftAbout;

	private SharedPreferences sPreferences;

	private String isLogin;


	private UserInfoDataHelper userinfoDH ;
	private List<UserInfo> userInfoList ;

	private Bitmap btPersonalHead;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		sPreferences = getSharedPreferences(WelComeActivity.STUDENTNUMBER,0);
		isLogin = sPreferences.getString("sn", "");
		if(isLogin.equals("")){

			Utils.showToast(getApplicationContext(), "未登录账号");
		}

		//初始化布局
		initViews();
		fragmentManager=getFragmentManager();
		// 第一次启动时选中第0个fragment
		setTabSelection(0);
		//侧滑
		initSlidingMenu();

		initLeftView();

	}

	private void setTabSelection(int i) {

		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (i) {
		case 0:
			// 当点击了首页时，改变控件的图片和文字颜色
			homepageImage.setImageResource(R.drawable.guide_home_on);
			homgpageText.setTextColor(Color.parseColor("#ff3300"));
			if (homepageFragment==null) {
				// 如果为空，则创建一个并添加到界面上
				homepageFragment=new HomePageFragment();
				transaction.add(R.id.content, homepageFragment);
			}else {
				// 如果不为空，则直接将它显示出来
				transaction.show(homepageFragment);
			}
			break;
		case 1:
			shoppingImage.setImageResource(R.drawable.guide_tfaccount_on);
			shoppingText.setTextColor(Color.parseColor("#ff3300"));
			if (shoppingFragment==null) {
				// 如果为空，则创建一个并添加到界面上
				if (isLogin.equals("")) {
					Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
					startActivity(intent);
					finish();
					
					// 先判断是否已经回收
					if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
						// 回收并且置为null
						btPersonalHead.recycle(); 
						btPersonalHead = null; 
					} 
					System.gc();
					return;
				}
				shoppingFragment=new ShoppingFragment();
				transaction.add(R.id.content, shoppingFragment);
			}else {
				// 如果不为空，则直接将它显示出来
				transaction.show(shoppingFragment);
			}
			break;
		case 2:
			messageImage.setImageResource(R.drawable.guide_ww_on);
			messageText.setTextColor(Color.parseColor("#ff3300"));
			if (messageFragment==null) {
				// 如果为空，则创建一个并添加到界面上
				if (isLogin.equals("")) {
					Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
					startActivity(intent);
					finish();
					// 先判断是否已经回收
					if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
						// 回收并且置为null
						btPersonalHead.recycle(); 
						btPersonalHead = null; 
					} 
					System.gc();
					return;
				}
				messageFragment=new MessageFragment();
				transaction.add(R.id.content, messageFragment);
			}else {
				// 如果不为空，则直接将它显示出来
				transaction.show(messageFragment);
			}
			break;
		case 3:
			myselfImage.setImageResource(R.drawable.detail_fav_light);
			myselfText.setTextColor(Color.parseColor("#ff3300"));
			if (myselfFragment==null) {
				// 如果为空，则创建一个并添加到界面上
				if (isLogin.equals("")) {
					Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
					startActivity(intent);
					finish();
					
					// 先判断是否已经回收
					if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
						// 回收并且置为null
						btPersonalHead.recycle(); 
						btPersonalHead = null; 
					} 
					System.gc();					
					return;
				}
				myselfFragment=new PersonalView();
				transaction.add(R.id.content, myselfFragment);
			}else {
				// 如果不为空，则直接将它显示出来
				transaction.show(myselfFragment);
			}
			break;

		default:
			break;
		}
		transaction.commit();
	}

	//隐藏所有页面
	private void hideFragments(FragmentTransaction transaction) {
		if (homepageFragment != null) {
			transaction.hide(homepageFragment);
		}
		if (shoppingFragment != null) {
			transaction.hide(shoppingFragment);
		}
		if (messageFragment != null) {
			transaction.hide(messageFragment);
		}
		if (myselfFragment != null) {
			transaction.hide(myselfFragment);
		}
	}

	//清除掉所有的选中状态。
	private void clearSelection() {
		homepageImage.setImageResource(R.drawable.guide_home_nm);
		homgpageText.setTextColor(Color.parseColor("#82858b"));
		shoppingImage.setImageResource(R.drawable.guide_tfaccount_nm);
		shoppingText.setTextColor(Color.parseColor("#82858b"));
		messageImage.setImageResource(R.drawable.guide_ww_nm);
		messageText.setTextColor(Color.parseColor("#82858b"));
		myselfImage.setImageResource(R.drawable.detail_fav_light_gray);
		myselfText.setTextColor(Color.parseColor("#82858b"));
	}


	private void initViews() {
		homepageLayout=findViewById(R.id.tab_homepage);
		shoppingLayout=findViewById(R.id.tab_bbs);
		

		homepageImage=(ImageView) findViewById(R.id.homepage_image);
		//shoppingImage=(ImageView) findViewById(R.id.shopping_image);
	

		homgpageText=(TextView) findViewById(R.id.homepage_text);
		shoppingText=(TextView) findViewById(R.id.bbs_text);
	

		homepageLayout.setOnClickListener(this);
		shoppingLayout.setOnClickListener(this);
	
	}
	/*
	 * 侧滑
	 * */
	private void initSlidingMenu() {
		slidingMenu = (SlidingMenu) findViewById(R.id.main_id_menu);

		userLogo = (ImageView)findViewById(R.id.sliding_userLogo);
		Head = (RelativeLayout) findViewById(R.id.iv_left_head);
		leftPersonal = findViewById(R.id.sliding_menu_list_1);
		leftMerchandise = findViewById(R.id.sliding_menu_list_2);
		leftSetting = findViewById(R.id.sliding_menu_list_3);
		leftAbout = findViewById(R.id.sliding_menu_list_4);

		userInfoList = new ArrayList<UserInfo>();
		userinfoDH = new UserInfoDataHelper(getApplicationContext());
		userInfoList = userinfoDH.getUserInfoList();
		String strImage = null;

		for (int i = 0; i < userInfoList.size(); i++) {
			if (userInfoList.get(i).getStudentNumber().equals(isLogin)) {
				strImage = userInfoList.get(i).getUserIcon()+"";
			}else {
			}
		}

		try {
			btPersonalHead = BitmapFactory.decodeFile(strImage);
			if (btPersonalHead != null) {
				@SuppressWarnings("deprecation")
				Drawable drawable = new BitmapDrawable(btPersonalHead);
				userLogo.setImageDrawable(drawable);
			}else {
				userLogo.setImageResource(R.drawable.beautiful);
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		Head.setOnClickListener(this);
		
	}

	public void SlidingMenu(View view){
		slidingMenu.slidingMenu();

	}
	
	private void initLeftView() {

		leftPersonal.setOnClickListener(this);
		leftMerchandise.setOnClickListener(this);
		leftSetting.setOnClickListener(this);
		leftAbout.setOnClickListener(this);

	}

	private void setLeftSelect(int i) {
		switch (i) {
		case 1:
		case 2:
			if (isLogin.equals("")) {
				Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
				startActivity(intent);
				finish();
				
				// 先判断是否已经回收
				if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
					// 回收并且置为null
					btPersonalHead.recycle(); 
					btPersonalHead = null; 
				} 
				System.gc();
				return;
			}
			Intent intent1 = new Intent(MainActivity.this,PersonalleftActivity.class);
			startActivity(intent1);
			finish();
			
			// 先判断是否已经回收
			if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
				// 回收并且置为null
				btPersonalHead.recycle(); 
				btPersonalHead = null; 
			} 
			System.gc();

			break;
		case 3:

			List<UserInfo> userInfoList = new ArrayList<UserInfo>();
			UserInfoDataHelper userDHelper = new UserInfoDataHelper(getApplicationContext());
			userInfoList = userDHelper.getUserInfoList();
			for(int l = 0; l < userInfoList.size(); l++){
				if (isLogin.equals(userInfoList.get(l).getStudentNumber())) {
					if (null == (userInfoList.get(l).getPhoneNumber()) || "".equals(userInfoList.get(l).getPhoneNumber())) {
						Utils.showToast(getApplicationContext(), "请先绑定手机号码");
						return;
					}

				}
			}

			if (isLogin.equals("")) {
				Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
				startActivity(intent);
				finish();
				
				// 先判断是否已经回收
				if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
					// 回收并且置为null
					btPersonalHead.recycle(); 
					btPersonalHead = null; 
				} 
				System.gc();
				return;
			}
			Intent intent3 = new Intent(MainActivity.this,MerchandiseleftActivity.class);
			startActivity(intent3);
			finish();
			
			// 先判断是否已经回收
			if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
				// 回收并且置为null
				btPersonalHead.recycle(); 
				btPersonalHead = null; 
			} 
			System.gc();
			break;
		case 4:

			if (isLogin.equals("") ) {
				Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
				startActivity(intent);
				finish();
				
				// 先判断是否已经回收
				if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
					// 回收并且置为null
					btPersonalHead.recycle(); 
					btPersonalHead = null; 
				} 
				System.gc();
				return;
			}
			Intent intent4 = new Intent(MainActivity.this,SettingleftActivity.class);
			startActivity(intent4);
			finish();
			
			// 先判断是否已经回收
			if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
				// 回收并且置为null
				btPersonalHead.recycle(); 
				btPersonalHead = null; 
			} 
			System.gc();
			break;
		case 5:
			Intent intent5 = new Intent(MainActivity.this,AboutleftActivity.class);
			startActivity(intent5);
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

	public boolean dispatchKeyEvent(KeyEvent event) {//点击两次退出

		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {


				if ((System.currentTimeMillis() - mExitTime) > 2000) {// 如果两次按键时间间隔大于2000毫秒，则不退出
					Utils.showToast(getApplicationContext(), "再按一次退出程序");
					mExitTime = System.currentTimeMillis();//更新
				} else {

					int id = android.os.Process.myPid();
					if (id != 0) {
						android.os.Process.killProcess(id);
					}
				}
				return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab_homepage:
			// 当点击了消息tab时，选中第1个tab
			setTabSelection(0);
			break;
		case R.id.tab_bbs:
			// 当点击了联系人tab时，选中第2个tab
			setTabSelection(1);
			break;
		case R.id.iv_left_head:
			setLeftSelect(1);
			break;
		case R.id.sliding_menu_list_1:
			setLeftSelect(2);
			break;
		case R.id.sliding_menu_list_2:
			setLeftSelect(3);
			break;
		case R.id.sliding_menu_list_3:
			setLeftSelect(4);
			break;
		case R.id.sliding_menu_list_4:

			setLeftSelect(5);

			break;
		default:
			break;
		}
	}







}
