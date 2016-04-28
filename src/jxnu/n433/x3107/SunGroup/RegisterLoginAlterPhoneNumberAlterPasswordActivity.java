package jxnu.n433.x3107.SunGroup;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import jxnu.n433.x3107.bean.UserInfo;
import jxnu.n433.x3107.sqlite.UserInfoDataHelper;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;

public class RegisterLoginAlterPhoneNumberAlterPasswordActivity extends Activity implements OnClickListener{

	private ImageView ivBack;
	private EditText etNew;
	private Button btnOk;

	private List<UserInfo> userInfoList;
	private UserInfoDataHelper userInfoDHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_login_alter_phone_number_alter_password);

		initView();
	}

	private void initView() {
		ivBack = (ImageView) findViewById(R.id.alter_register_login_left_register_phone_number_new_password_login_iv_back); 
		etNew = (EditText) findViewById(R.id.alter_login_phone_number_register_register_phone_number_new_password_edit); 
		btnOk = (Button) findViewById(R.id.alter_log_rl_regist_phone_number_register_register_phone_number_new_password_btn_code); 

		ivBack.setOnClickListener(this);
		btnOk.setOnClickListener(this);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK ) { 

			Intent intentBack = new Intent(RegisterLoginAlterPhoneNumberAlterPasswordActivity.this,RegisterLoginAlterPhoneNumberActivity.class);
			startActivity(intentBack);
			finish();

			return false; 
		} 

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.alter_register_login_left_register_phone_number_new_password_login_iv_back:

			Intent intentBack = new Intent(RegisterLoginAlterPhoneNumberAlterPasswordActivity.this,RegisterLoginAlterPhoneNumberActivity.class);
			startActivity(intentBack);
			finish();

			break;
		case R.id.alter_log_rl_regist_phone_number_register_register_phone_number_new_password_btn_code:
			String phoneStr = getIntent().getStringExtra("phoneStr")+"";
			userInfoList = new ArrayList<UserInfo>();
			userInfoDHelper = new UserInfoDataHelper(getApplicationContext());
			userInfoList = userInfoDHelper.getUserInfoList();
			for (int i = 0; i < userInfoList.size(); i++) {
				String getPN = userInfoList.get(i).getPhoneNumber()+"";
				if (phoneStr.equals(getPN)) {
					userInfoDHelper.updateUserInfo(etNew.getText().toString().trim()+"", userInfoList.get(i).getStudentNumber()+"", 10);
					Intent intentNew = new Intent(RegisterLoginAlterPhoneNumberAlterPasswordActivity.this,RegisterLoginActivity.class);
					startActivity(intentNew);
					finish();
				}
			}

			break;

		default:
			break;
		}
	}


}
