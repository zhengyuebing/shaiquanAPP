package jxnu.n433.x3107.SunGroup.OtherActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.SunGroup.MainActivity;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.bean.MySelfListing;
import jxnu.n433.x3107.sqlite.GoodsDataHelper;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class HomeMenuClassActivity extends Activity implements OnClickListener{

	private ImageView ivBack;
	private String menuClass;
	private TextView tvMenu;

	private ListView lvMenu;
	private List<MySelfListing> mslLists;
	private GoodsDataHelper goodsDHelper;
	private String etText;
	
	private List<MySelfListing> mslList;//搜索

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home_menu_class);

		Intent intent = getIntent();
		menuClass = intent.getStringExtra("menu");
		
		tvMenu = (TextView) findViewById(R.id.home_menu_activity_menu_tv);
		tvMenu.setText(menuClass+"");

		lvMenu = (ListView) findViewById(R.id.home_menu_activity_lv_menu_class);

		if (menuClass.equals("搜索")) {
			etText = intent.getStringExtra("text");
			Logger.e(""+etText);

			initSeek();
		}else {
			initView();
		}
		ivBack = (ImageView) findViewById(R.id.home_menu_activity_iv_back);
		ivBack.setOnClickListener(this);
		//		Utils.showToast(getApplicationContext(), menuClass+"");




	}

	private void initSeek() {
		mslList = new ArrayList<MySelfListing>();
		goodsDHelper = new GoodsDataHelper(getApplicationContext());
		mslList = goodsDHelper.getGoodsInfoList(etText,3);

		MenuAdapter menuAdapter = new MenuAdapter(mslList);
		lvMenu.setAdapter(menuAdapter);
		lvMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(HomeMenuClassActivity.this,HomeGoodsGVActivity.class);
				intent.putExtra("int","listview:"+arg2);
				intent.putExtra("goodsName", ""+mslList.get(arg2).getGoodsName());
				intent.putExtra("studentNumber", ""+mslList.get(arg2).getStudentNumber());
				startActivity(intent);
				finish();
				Logger.d(arg2+"");
			}
		});
	}

	private void initView() {



		mslLists = new ArrayList<MySelfListing>();
		goodsDHelper = new GoodsDataHelper(getApplicationContext());
		mslLists = goodsDHelper.getGoodsInfoList(menuClass,2);

		MenuAdapter menuAdapter = new MenuAdapter(mslLists);
		lvMenu.setAdapter(menuAdapter);

		lvMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(HomeMenuClassActivity.this,HomeGoodsGVActivity.class);
				intent.putExtra("int","listview:"+arg2);
				intent.putExtra("goodsName", ""+mslLists.get(arg2).getGoodsName());
				intent.putExtra("studentNumber", ""+mslLists.get(arg2).getStudentNumber());
				startActivity(intent);
				finish();
				Logger.d(arg2+"");
			}
		});


	}

	private class MenuAdapter extends BaseAdapter{

		List<MySelfListing> mListing = new ArrayList<MySelfListing>();

		private LayoutInflater mInflater;

		public MenuAdapter(List<MySelfListing> mListing) {
			this.mListing = mListing;

			mInflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return mListing.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.home_menu_class_list_view_share, null);

				holder = new ViewHolder();

				holder.ivGoodsImage = (ImageView) convertView.findViewById(R.id.home_menu_class_list_adapter_iv_share_goods_image);
				holder.tvDate = (TextView) convertView.findViewById(R.id.home_menu_class_list_adapter_tv_date);
				holder.tvGoodsIntro = (TextView) convertView.findViewById(R.id.home_menu_class_list_adapter_tv_share_intro);

				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}

			try {
				Bitmap btUP = BitmapFactory.decodeFile(mListing.get(position).getGoodsImages());//从Sd中找头像，转换成Bitmap
				if(btUP!=null){
					@SuppressWarnings("deprecation")
					Drawable drawable = new BitmapDrawable(btUP);//转换成drawable
					holder.ivGoodsImage.setImageDrawable(drawable);
				}else{
					/**
					 *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
					 * 
					 */

					//				Utils.showToast(getActivity(), "没有图片");
				}
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
			}

			holder.tvDate.setText(""+mListing.get(position).getGoodsDate());
			holder.tvGoodsIntro.setText(""+mListing.get(position).getGoodsIntro());

			return convertView;
		}

	}
	static class ViewHolder{
		ImageView ivGoodsImage;
		TextView tvDate;
		TextView tvGoodsIntro;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_menu_activity_iv_back:

			Intent intentBack = new Intent(HomeMenuClassActivity.this,MainActivity.class);
			startActivity(intentBack);
			finish();

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			Intent intentBack = new Intent(HomeMenuClassActivity.this,MainActivity.class);
			startActivity(intentBack);
			finish();

		}

		return super.onKeyDown(keyCode, event);
	}


}
