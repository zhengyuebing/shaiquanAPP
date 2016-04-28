package jxnu.n433.x3107.SunGroup;

import android.os.Bundle;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
/*
 * 欢迎界面
 * */
public class WelComeActivity extends Activity {

	public  static final  String STUDENTNUMBER = "student_number";

	private SharedPreferences sPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wel_come);

		sPreferences = getSharedPreferences(STUDENTNUMBER, 0);

		new Thread(){
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/*				if (sPreferences.getString("sn", "").equals("") || sPreferences.getString("sn", "") == null) {
					Intent intent=new Intent(WelComeActivity.this,RegisterActivity.class);
					startActivity(intent);
					finish();
				}else {
				}*/
				
				if (sPreferences.getString("sn", "").equals("") || sPreferences.getString("sn", "") == null) {
					
					Intent intentMain =new  Intent(WelComeActivity.this,MainActivity.class);
					startActivity(intentMain);
					finish();
					
				}else {
					Intent intentMain =new  Intent(WelComeActivity.this,MainActivity.class);
					startActivity(intentMain);
					finish();
				}
				



			};
		}.start();
	}


}
