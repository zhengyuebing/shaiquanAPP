package jxnu.n433.x3107.SunGroup.OtherActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.Window;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.SunGroup.WelComeActivity;
import jxnu.n433.x3107.bean.UserInfo;
import jxnu.n433.x3107.sqlite.UserInfoDataHelper;
import jxnu.n433.x3107.utils.Utils;

public class SettingAlterPasswordActivity extends Activity implements OnClickListener{

	private ImageView ivAlterBack;
	private EditText etAlterSNumber;
	private EditText etAlterPWord;
	private Button btnAlter;

	private ImageView passwordType;//显示隐藏密码
	private boolean isShow = false;//密码状态

	private List<UserInfo> userInfoList;
	private UserInfoDataHelper userInfoDHelper;

	private SharedPreferences sPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting_alter_password);

		sPreferences = getSharedPreferences(WelComeActivity.STUDENTNUMBER, 0);

		initView();


	}

	private void initView() {
		ivAlterBack = (ImageView) findViewById(R.id.alter_left_iv_back);
		etAlterSNumber = (EditText) findViewById(R.id.alter_login_user_edit);
		etAlterPWord = (EditText) findViewById(R.id.alter_login_passwd_edit);
		btnAlter = (Button) findViewById(R.id.alter_log_rl_regist_btn);

		passwordType = (ImageView) findViewById(R.id.alter_password_type);

		ivAlterBack.setOnClickListener(this);
		btnAlter.setOnClickListener(this);
		passwordType.setOnClickListener(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK ) { 

			Intent intentBack = new Intent(SettingAlterPasswordActivity.this,SettingleftActivity.class);
			startActivity(intentBack);
			finish();
			return false; 
		} 

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.alter_left_iv_back:
			Intent intentBack = new Intent(SettingAlterPasswordActivity.this,SettingleftActivity.class);
			startActivity(intentBack);
			finish();
			break;
		case R.id.alter_log_rl_regist_btn:

			userInfoList = new ArrayList<UserInfo>();
			userInfoDHelper = new UserInfoDataHelper(getApplicationContext());
			userInfoList = userInfoDHelper.getUserInfoList();
			if (etAlterPWord.getText().toString().trim().equals("") ||
					etAlterSNumber.getText().toString().trim().equals("")) {
				Utils.showToast(getApplicationContext(), "有信息未填写");
				return;

			}
			String studentNumberAlter = sPreferences.getString("sn", "")+"";
			for (int i = 0; i < userInfoList.size(); i++) {
				if (studentNumberAlter.equals(etAlterSNumber.getText().toString().trim())) {
					String password = etAlterPWord.getText().toString().trim()+"";
					userInfoDHelper.updateUserInfo(password, sPreferences.getString("sn", ""), 10);
				}
				if (!studentNumberAlter.equals(etAlterSNumber.getText().toString().trim())) {
					Utils.showToast(getApplicationContext(), "学号错误");
					return;
				}
			}
			Intent intentAlter = new Intent(SettingAlterPasswordActivity.this,SettingleftActivity.class);
			startActivity(intentAlter);
			finish();

			break;
		case R.id.alter_password_type:

			if (! isShow) {

				etAlterPWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
				isShow = true;
				return;
			}
			if (isShow) {
				etAlterPWord.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
				isShow = false;
				return;
			}

			break;

		default:
			break;
		}
	}


}
