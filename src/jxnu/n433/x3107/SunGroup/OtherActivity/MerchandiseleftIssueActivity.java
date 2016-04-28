package jxnu.n433.x3107.SunGroup.OtherActivity;

import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.Window;
import android.view.WindowManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.SunGroup.WelComeActivity;
import jxnu.n433.x3107.bean.MySelfListing;
import jxnu.n433.x3107.bean.UserInfo;
import jxnu.n433.x3107.sqlite.GoodsDataHelper;
import jxnu.n433.x3107.sqlite.UserInfoDataHelper;
import jxnu.n433.x3107.utils.Utils;

@SuppressLint({ "SdCardPath", "SimpleDateFormat" })
public class MerchandiseleftIssueActivity extends Activity implements OnClickListener{

	private ImageView ivBack;

	public  Bitmap issueImage;//头像Bitmap
	private ImageView imageIssue;
	private Button btnTakephoto;//拍照
	private Button btnPhotos;//相册
	private Button btnOff;//取消
	private Dialog dialog;
	private EditText editIssue;
	private EditText editGoodsName;
	private EditText editPrice;
	private Button btnIssue;
	private TextView tvIssueImageHint;
	private Spinner spinner;
	private String strSpinner;


	private List<MySelfListing> msList;
	private GoodsDataHelper goodsDHelper;
	private SharedPreferences sPreferences;
	private MySelfListing msListings;


	private UserInfoDataHelper userInfoDHelper ;
	private List<UserInfo> userInfoList ;



	public  static final String ISSUE_PATH="/sdcard/MerIssue/";//sd路径

	private SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");//yyyy-MM-dd 
	public static  String str;



	private String menu;

	private String studentNumber;

//	private String etName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_merchandiseleft_issue);

		sPreferences = getSharedPreferences(WelComeActivity.STUDENTNUMBER, 0);
		studentNumber = sPreferences.getString("sn", "")+"";

		editGoodsName = (EditText) findViewById(R.id.merchandie_left_title_issue_add_content_name);
//		etName = editGoodsName.getText().toString().trim()+"";

		str = format.format(new java.util.Date());

		initView();
		initMenuUpdate();
		initComfit();



	}







	private void initView() {
		ivBack = (ImageView) findViewById(R.id.merchandie_left_title_issue_iv_back);
		ivBack.setOnClickListener(this);


	}

	private void initMenuUpdate() {

	}

	private void initComfit() {
		imageIssue = (ImageView) findViewById(R.id.merchandie_left_title_issue_iv_alter_image);
		editIssue = (EditText) findViewById(R.id.merchandie_left_title_issue_add_content);
		btnIssue = (Button) findViewById(R.id.merchandie_left_title_issue_submit);
		tvIssueImageHint = (TextView) findViewById(R.id.merchandie_left_title_issue_tv_image_null);

		spinner = (Spinner) findViewById(R.id.spinner);
		editPrice = (EditText) findViewById(R.id.merchandie_left_title_issue_price);



		Intent intent = getIntent();
		menu = intent.getStringExtra("menu")+"";
		String studentNumber = intent.getStringExtra("studentNumber");
		String goodsName = intent.getStringExtra("goodsName");

		if (!menu.equals("")) {
			Utils.showToast(getApplicationContext(), ""+menu);

			msList = new ArrayList<MySelfListing>();
			goodsDHelper = new GoodsDataHelper(getApplicationContext());
			msList = goodsDHelper.getGoodsInfoList(studentNumber, goodsName,1);
			
			goodsDHelper.updateGoods("", goodsName, studentNumber, 3);

			editGoodsName.setText(msList.get(0).getGoodsName()+"");
			editIssue.setText(msList.get(0).getGoodsIntro()+"");
			editPrice.setText(msList.get(0).getGoodsPrice()+"");

			/*Bitmap bt = BitmapFactory.decodeFile(msList.get(0).getGoodsImages()+"");//从Sd中找头像，转换成Bitmap
			if(bt!=null){
				@SuppressWarnings("deprecation")
				Drawable drawable = new BitmapDrawable(bt);//转换成drawable
				imageIssue.setImageDrawable(drawable);
			}else{
				*//**
				 *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
				 * 
				 *//*
			}
*/
			Utils.showToast(getApplicationContext(), ""+msList.size());

		}

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {



			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				strSpinner = spinner.getSelectedItem().toString().trim();
				//				Utils.showToast(getApplicationContext(), strSpinner+"");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});


		imageIssue.setOnClickListener(this);
		btnIssue.setOnClickListener(this);
		tvIssueImageHint.setEnabled(false);



	}

	@SuppressWarnings("deprecation")
	private void showSelectDialog() {
		View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog, null);

		btnPhotos = (Button) view.findViewById(R.id.picture_btn_photos);
		btnTakephoto = (Button) view.findViewById(R.id.picture_btn_takephoto);
		btnOff =(Button) view.findViewById(R.id.picture_btn_off);
		btnPhotos.setOnClickListener(this);
		btnTakephoto.setOnClickListener(this);
		btnOff.setOnClickListener(this);

		dialog = new Dialog(this,R.style.transparentFrameWindowStyle);
		dialog.setContentView(view,new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		Window window = dialog.getWindow();
		// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getWindowManager().getDefaultDisplay().getWidth();
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		dialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {



		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				cropPhoto(data.getData());//裁剪图片
			}
			break;
		case 2:
			if (resultCode == RESULT_OK) {



				File temp = new File(Environment.getExternalStorageDirectory()
						+ "/issue"+studentNumber+str+".png");
				cropPhoto(Uri.fromFile(temp));//裁剪图片


			}
			break;
		case 3:
			if (data != null) {
				Bundle extras = data.getExtras();
				issueImage = extras.getParcelable("data");
				if(issueImage!=null){
					/**
					 * 上传服务器代码
					 */
					setPicToView(issueImage);//保存在SD卡中
					imageIssue.setImageBitmap(issueImage);//用ImageView显示出来

					/*	Bitmap bt = BitmapFactory.decodeFile(ISSUE_PATH + "issue"+str+".png");//从Sd中找头像，转换成Bitmap
					if(bt!=null){
						@SuppressWarnings("deprecation")
						Drawable drawable = new BitmapDrawable(bt);//转换成drawable
						imageIssue.setImageDrawable(drawable);
					}else{
					 *//**
					 *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
					 * 
					 *//*
					}*/
				}


			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	};
	/**
	 * 调用系统的裁剪
	 * @param uri
	 */
	public void cropPhoto(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
		
		
	}

	private void setPicToView(Bitmap mBitmap) {
		String sdStatus = Environment.getExternalStorageState();  
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用  
			return;  
		}  
		FileOutputStream b = null;
		File file = new File(ISSUE_PATH);
		file.mkdirs();// 创建文件夹
		String fileName =ISSUE_PATH + "issue"+studentNumber+str+".png";//图片名字
		try {
			b = new FileOutputStream(fileName);
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, b);// 把数据写入文件
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				//关闭流
				b.flush();
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.merchandie_left_title_issue_iv_back:

			Intent intentBack = new Intent(MerchandiseleftIssueActivity.this,MerchandiseleftActivity.class);
			startActivity(intentBack);
			finish();
			break;
		case R.id.merchandie_left_title_issue_iv_alter_image:

/*			if (etName.equals("")) {
				Utils.showToast(getApplicationContext(), "请先添加物品名称，再添加图片");
			
				return;
			}*/
			showSelectDialog();
			Utils.showToast(getApplicationContext(), "图片");

			break;
		case R.id.merchandie_left_title_issue_submit:

			String etIssue = editIssue.getText().toString().trim();
			String etGoodsName = editGoodsName.getText().toString().trim();
			String etPrice = editPrice.getText().toString().trim();
			
			if (etIssue.equals("") || imageIssue.getDrawable() == null || etGoodsName.equals("") || strSpinner.equals("") || etPrice.equals("")) {
				Utils.showToast(getApplicationContext(), "请填写完整信息");
				return;
			}

			Intent intent = getIntent();
			menu = intent.getStringExtra("menu")+"";


			if (!menu.equals("")) {
				
				Utils.showToast(getApplicationContext(), "请重新添加图片");
				
				String studentNumber = intent.getStringExtra("studentNumber");
				String goodsName = intent.getStringExtra("goodsName");
				msList = new ArrayList<MySelfListing>();
				goodsDHelper = new GoodsDataHelper(getApplicationContext());

				goodsDHelper.updateGoods(etGoodsName, goodsName, studentNumber, 1);
				goodsDHelper.updateGoods(ISSUE_PATH+ "issue"+studentNumber+str+".png", goodsName, studentNumber, 3);
				goodsDHelper.updateGoods(etIssue, goodsName, studentNumber, 2);
				goodsDHelper.updateGoods(""+etPrice, goodsName, studentNumber, 10);
				goodsDHelper.updateGoods(strSpinner, goodsName, studentNumber, 9);
				goodsDHelper.updateGoods(str+"", goodsName, studentNumber, 4);

				Intent intentSubmit = new Intent(MerchandiseleftIssueActivity.this,MerchandiseleftActivity.class);
				startActivity(intentSubmit);
				finish();
				Utils.showToast(getApplicationContext(), editIssue.getText().toString().trim()+"");

				return;

			}


			msList = new ArrayList<MySelfListing>();
			goodsDHelper = new GoodsDataHelper(getApplicationContext());

			sPreferences = getSharedPreferences(WelComeActivity.STUDENTNUMBER,0);
			String strSN = sPreferences.getString("sn","");

			msListings = new MySelfListing();
			msListings.setStudentNumber(strSN+"");
			msListings.setGoodsIntro(etIssue+"");
			msListings.setGoodsName(etGoodsName+"");
			msListings.setGoodsPrice(""+etPrice+"");
			msListings.setGoodsImages(ISSUE_PATH+ "issue"+studentNumber+str+".png");
			msListings.setGoodsDate(str+"");
			msListings.setGoodsClassify(strSpinner+"");


			goodsDHelper.insertGoods(msListings);

			userInfoList = new ArrayList<UserInfo>();
			userInfoDHelper = new UserInfoDataHelper(getApplicationContext());
			userInfoList = userInfoDHelper.getUserInfoList();
			String strImage = null;
			String strUName = null;

			for (int i = 0; i < userInfoList.size(); i++) {
				if (userInfoList.get(i).getStudentNumber().equals(sPreferences.getString("sn", ""))) {
					strImage = userInfoList.get(i).getUserIcon()+"";
					strUName = userInfoList.get(i).getUserName() + "";


				}else {
					//					Utils.showToast(getApplicationContext(), "NULL");
				}
			}

			goodsDHelper.updateGoods(strUName, etGoodsName, strSN, 7);
			goodsDHelper.updateGoods(strImage, etGoodsName, strSN, 8);

			msList = goodsDHelper.getGoodsInfoList();
			Utils.showToast(getApplicationContext(), msList.size()+"");



			Intent intentSubmit = new Intent(MerchandiseleftIssueActivity.this,MerchandiseleftActivity.class);
			startActivity(intentSubmit);
			finish();
			Utils.showToast(getApplicationContext(), editIssue.getText().toString().trim()+"");
			break;

		case R.id.picture_btn_photos://从相册里面取照片
			Intent intent1 = new Intent(Intent.ACTION_PICK, null);
			intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
			startActivityForResult(intent1, 1);



			break;
		case R.id.picture_btn_takephoto://调用相机拍照

			//			SimpleDateFormat format = new SimpleDateFormat("kk:mm:ss");//yyyy-MM-dd 
			//			str = format.format(new java.util.Date());

			Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
					"issue"+studentNumber+str+".png")));
			startActivityForResult(intent2, 2);//采用ForResult打开
			break;
		case R.id.picture_btn_off:

			dialog.dismiss();

			break;

		default:
			break;
		}
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intentBack = new Intent(MerchandiseleftIssueActivity.this,MerchandiseleftActivity.class);
			startActivity(intentBack);
			finish();

			return false;
		}

		return super.onKeyDown(keyCode, event);
	}



}
