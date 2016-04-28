package jxnu.n433.x3107.SunGroup.OtherActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import jxnu.n433.x3107.SunGroup.MainActivity;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.bean.UserInfo;
import jxnu.n433.x3107.sqlite.UserInfoDataHelper;

public class UserIntroActivity extends Activity implements OnClickListener{

	private ImageView leftPersonalback;

	private ImageView leftPersonalIViewHead;//头像

	public TextView pName;//昵称
	public TextView pSexs;//性别
	public TextView pSite;//地址
	public TextView pIntro;//简介
	public TextView pSchool;//学校
	public TextView pBirthday;//生日
	public TextView pQQ;//QQ
	public TextView pMailBox;//邮箱
	public TextView pPhoneNumber;
	
	private UserInfoDataHelper userinfoDH ;
	private List<UserInfo> userInfoList ;

	private Bitmap btPersonalHead;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_personal_left);
		
		Intent intent = getIntent();
		
		String studentNumber = intent.getStringExtra("studentNumber")+"";
		
		userInfoList = new ArrayList<UserInfo>();
		userinfoDH = new UserInfoDataHelper(getApplicationContext());
		userInfoList = userinfoDH.getUserInfoList(studentNumber);
		
		initView();
		initViewShow();
		
		
	}

	private void initViewShow() {
		try {
			btPersonalHead = BitmapFactory.decodeFile(userInfoList.get(0).getUserIcon()+"");
			if (btPersonalHead != null) {
				@SuppressWarnings("deprecation")
				Drawable drawable = new BitmapDrawable(btPersonalHead);
				leftPersonalIViewHead.setImageDrawable(drawable);
			}else {
				leftPersonalIViewHead.setImageResource(R.drawable.beautiful);
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		
		pName.setText(userInfoList.get(0).getUserName()+"");
		pSexs.setText(userInfoList.get(0).getUserSexs()+"");
		pSite.setText(userInfoList.get(0).getUserSite()+"");
		pIntro.setText(userInfoList.get(0).getUserIntro()+"");
		pSchool.setText(userInfoList.get(0).getUserSchool()+"");
		pBirthday.setText(userInfoList.get(0).getUserBirthday()+"");
		pQQ.setText(userInfoList.get(0).getUserQQ()+"");
		pMailBox.setText(userInfoList.get(0).getUserMailBox()+"");
		pPhoneNumber.setText(userInfoList.get(0).getPhoneNumber()+"");

	}

	private void initView() {
		leftPersonalback = (ImageView) findViewById(R.id.personal_left_iv_back_personal);

		leftPersonalIViewHead = (ImageView) findViewById(R.id.zdy_image_personal);		
		pName = (TextView) findViewById(R.id.personal_left_tv_name_personal);
		pSexs = (TextView) findViewById(R.id.sexs_personal);
		pSite = (TextView) findViewById(R.id.sites_personal);
		pIntro = (TextView) findViewById(R.id.intros_personal);
		pSchool = (TextView) findViewById(R.id.school_personal);
		pBirthday = (TextView) findViewById(R.id.birthday_personal);
		pQQ = (TextView) findViewById(R.id.qq_personal);
		pMailBox = (TextView) findViewById(R.id.mailbox_personal);
		pPhoneNumber = (TextView) findViewById(R.id.phonenumber_personal);
		
		leftPersonalback.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.personal_left_iv_back_personal:
			
			Intent intent = new Intent(UserIntroActivity.this,MainActivity.class);
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

		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(UserIntroActivity.this,MainActivity.class);
			startActivity(intent);
			finish();
		
			
			// 先判断是否已经回收
			if(btPersonalHead != null && !btPersonalHead.isRecycled()){ 
				// 回收并且置为null
				btPersonalHead.recycle(); 
				btPersonalHead = null; 
			} 
			System.gc();
			
		}
		
		return super.onKeyDown(keyCode, event);
	}


}
