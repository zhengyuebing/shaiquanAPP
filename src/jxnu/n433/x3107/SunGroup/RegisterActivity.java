package jxnu.n433.x3107.SunGroup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegisterActivity extends Activity implements OnClickListener{

	private Button btnLogIn;
	private Button btnRegist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		
		initView();
	}

	private void initView() {
		btnLogIn = (Button) findViewById(R.id.register_log_rl_login_btn);
		btnRegist = (Button) findViewById(R.id.register_log_rl_regist_btn);
		
		btnLogIn.setOnClickListener(this);
		btnRegist.setOnClickListener(this);
		
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_log_rl_login_btn:
			
			Intent intentLogin = new Intent(RegisterActivity.this,RegisterLoginActivity.class);
			startActivity(intentLogin);
			finish();
			
			break;
		case R.id.register_log_rl_regist_btn:
			//注册
			Intent intentRegister = new Intent(RegisterActivity.this,RegisterRegisterActivity.class);
			startActivity(intentRegister);
			finish();
			break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intentLogin = new Intent(RegisterActivity.this,MainActivity.class);
			startActivity(intentLogin);
			finish();
			return false;
		}
		
		
		return super.onKeyDown(keyCode, event);
	}

}
