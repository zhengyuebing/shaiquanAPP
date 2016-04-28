package jxnu.n433.x3107.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import jxnu.n433.x3017.Adapter.GirdViewAdapter;
import jxnu.n433.x3107.Fragment.View.HGridView;
import jxnu.n433.x3107.Fragment.View.Logger;
import jxnu.n433.x3107.Fragment.View.PullDownElasticImp;
import jxnu.n433.x3107.Fragment.View.PullDownScrollView;
import jxnu.n433.x3107.Fragment.View.PullDownScrollView.RefreshListener;
import jxnu.n433.x3107.SunGroup.LocatorActivity;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.SunGroup.SeekActivity;
import jxnu.n433.x3107.SunGroup.WelComeActivity;
import jxnu.n433.x3107.SunGroup.OtherActivity.HomeGoodsGVActivity;
import jxnu.n433.x3107.SunGroup.OtherActivity.HomeMenuClassActivity;
import jxnu.n433.x3107.bean.Ad;
import jxnu.n433.x3107.bean.MySelfListing;
import jxnu.n433.x3107.bean.UserInfo;
import jxnu.n433.x3107.sqlite.GoodsDataHelper;
import jxnu.n433.x3107.sqlite.UserInfoDataHelper;
import jxnu.n433.x3107.utils.Utils;

public class HomePageFragment extends Fragment implements RefreshListener,OnClickListener{


	private ViewPager viewPager;

	private TextView tvHomeNumber,tvHomeNumberRight;//广告条数字显示

	private View homepageLayout;
	private ImageView ivSeek;
	private TextView tvHomeLocator;

	private List<Ad> imageList=new ArrayList<Ad>();

	private boolean ADisRunning = false;

	//商品列表
	private HGridView hotGridView;
	private HGridView catGridView;
	private List<MySelfListing> mslList;
	private GoodsDataHelper goodsDHelper;
	private RelativeLayout layoutMenu1,layoutMenu2,layoutMenu3,layoutMenu4;//菜单

	private PullDownScrollView mPullDownScrollView; 

	private SharedPreferences sPreferences;

	public static String strLocator;//静态方法获取定位信息
	
	public static String getStrLocator() {
		return strLocator;
	}
	
	public static void setStrLocator(String strLocator) {
		HomePageFragment.strLocator = strLocator;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		homepageLayout=(ViewGroup) inflater.inflate(R.layout.homepage_layout, container,false);

		sPreferences = getActivity().getSharedPreferences(WelComeActivity.STUDENTNUMBER,0);

		//initPhoneNumber();

		initView();

		initListener();//广告条滚动

		initData();//准备数据

		initLocator();//定位

		initSeek();//搜索
		
		initPullDown();//下拉刷新

		initLayoutMenu();//菜单


		//物品界面
		initCommodity();

		ADisRunning = true;
		handler.sendEmptyMessageDelayed(0, 4000);


		return homepageLayout;
	}

	private void initView() {
		viewPager=(ViewPager) homepageLayout.findViewById(R.id.homeviewPage);
		//		pointGroup=(LinearLayout) homepageLayout.findViewById(R.id.dot_layout);
	}

	private void initListener() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	
	/**
	 * 判断是否自动滚动
	 */
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			//让viewPager 滑动到下一页
			viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
			if(ADisRunning){
				handler.sendEmptyMessageDelayed(0, 4000);
			}
		};
	};

	private void initData() {
		
		imageList.add(new Ad(R.drawable.shouji, null));
		imageList.add(new Ad(R.drawable.yingpan, null));
		imageList.add(new Ad(R.drawable.chujiaquan, null));
		imageList.add(new Ad(R.drawable.peijian, null));
		
		viewPager.setAdapter(new MyPagerAdapter());
	}

	//	广告条
	class MyPagerAdapter extends PagerAdapter{
		/**
		 * 返回多少page
		 */
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}
		/**
		 * true: 表示不去创建，使用缓存  false:去重新创建
		 * view： 当前滑动的view
		 * object：将要进入的新创建的view，由instantiateItem方法创建
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		/**
		 * 类似于BaseAdapger的getView方法
		 * 用了将数据设置给view
		 * 由于它最多就3个界面，不需要viewHolder
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view=View.inflate(getActivity(), R.layout.adapter_ad, null);
			ImageView imageView=(ImageView) view.findViewById(R.id.image);
			tvHomeNumber = (TextView) homepageLayout.findViewById(R.id.home_tv_number_ho);
			tvHomeNumberRight = (TextView) homepageLayout.findViewById(R.id.home_tv_number_ho_right);

			Ad ad=imageList.get(position%imageList.size());
			imageView.setImageResource(ad.getIconResId());

			tvHomeNumber.setText(((position%imageList.size())+1)+"");
			tvHomeNumberRight.setText(" / "+imageList.size());

			container.addView(view);//一定不能少，将view加入到viewPager中

			return view;
		}
		/**
		 * 销毁page
		 * position： 当前需要消耗第几个page
		 * object:当前需要消耗的page
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
	//图片滚动


	private void initLocator() {//定位

		tvHomeLocator=(TextView) homepageLayout.findViewById(R.id.tv_home_locator);

				String baiduSite = sPreferences.getString("locator", "");

				if (baiduSite.equals("null") || baiduSite == null || baiduSite.equals("")
						) {
					tvHomeLocator.setText("江西师范大学软件学院");
				}else {
					tvHomeLocator.setText(baiduSite);
				}

		tvHomeLocator.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent=new Intent(getActivity(),LocatorActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});

	}

	private void initSeek() {
		ivSeek=(ImageView) homepageLayout.findViewById(R.id.iv_home1);
		ivSeek.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),SeekActivity.class));
				getActivity().finish();
			}
		});

	}

	/*
	 * 下拉刷新
	 * */
	private void initPullDown() {

		mPullDownScrollView = (PullDownScrollView) homepageLayout.findViewById(R.id.refresh_root);  
		mPullDownScrollView.setRefreshListener( this);  
		mPullDownScrollView.setPullDownElastic(new PullDownElasticImp(getActivity()));


	}
	//下拉刷新时间
	@Override
	public void onRefresh(PullDownScrollView view) {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				mPullDownScrollView.finishRefresh("最后刷新："+getCurrentTime());
			}
		}, 2000);
	}
	//下拉刷新时间格式
	@SuppressLint("SimpleDateFormat")
	private String getCurrentTime(){
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	/*
	 * 菜单点击
	 * */

	private void initLayoutMenu() {
		
		layoutMenu1 = (RelativeLayout) homepageLayout.findViewById(R.id.home_layout_main_menu_1);
		layoutMenu2 = (RelativeLayout) homepageLayout.findViewById(R.id.home_layout_main_menu_2);
		layoutMenu3 = (RelativeLayout) homepageLayout.findViewById(R.id.home_layout_main_menu_3);
		layoutMenu4 = (RelativeLayout) homepageLayout.findViewById(R.id.home_layout_main_menu_4);

		layoutMenu1.setOnClickListener(this);
		layoutMenu2.setOnClickListener(this);
		layoutMenu3.setOnClickListener(this);
		layoutMenu4.setOnClickListener(this);
	}

	/*
	 * 商品列表
	 * */
	private void initCommodity() {
		
		goodsDHelper = new GoodsDataHelper(getActivity());
		mslList = new ArrayList<MySelfListing>();
		mslList = goodsDHelper.getGoodsInfoList();
		Utils.showToast(getActivity(), mslList.size()+"");
		
		if (mslList.size() == 0) {
			
		}else {
			
			hotGridView = (HGridView) homepageLayout.findViewById(R.id.home_commodity_hotgridview);
			hotGridView.setAdapter(new GirdViewAdapter(getActivity(),mslList));
			hotGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					Intent intent = new Intent(getActivity(),HomeGoodsGVActivity.class);
					intent.putExtra("int","hotGridView:"+arg2);
					intent.putExtra("goodsName", ""+mslList.get(arg2).getGoodsName());
					intent.putExtra("studentNumber", ""+mslList.get(arg2).getStudentNumber());
					startActivity(intent);
					getActivity().finish();
					Logger.d(arg2+"");

				}
			});

			catGridView = (HGridView) homepageLayout.findViewById(R.id.home_commodity_catgridview);
//			catGridView.setAdapter(new GirdViewAdapter(getActivity(),null));
			catGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					Intent intent = new Intent(getActivity(),HomeGoodsGVActivity.class);
					intent.putExtra("int", "catGridView:"+arg2);
					startActivity(intent);
					getActivity().finish();
					Logger.d(arg2+"");
				}
			});
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_layout_main_menu_1:

			Utils.showToast(getActivity(), "生活");
			Intent intent1 = new Intent(getActivity(),HomeMenuClassActivity.class);
			intent1.putExtra("menu", "生活");
			startActivity(intent1);
			getActivity().finish();

			break;
		case R.id.home_layout_main_menu_2:

			Utils.showToast(getActivity(), "学习");
			Intent intent2 = new Intent(getActivity(),HomeMenuClassActivity.class);
			intent2.putExtra("menu", "学习");
			startActivity(intent2);
			getActivity().finish();
			break;
		case R.id.home_layout_main_menu_3:

			Utils.showToast(getActivity(), "数码");
			Intent intent3 = new Intent(getActivity(),HomeMenuClassActivity.class);
			intent3.putExtra("menu", "数码");
			startActivity(intent3);
			getActivity().finish();
			break;
		case R.id.home_layout_main_menu_4:

			Utils.showToast(getActivity(), "其他");
			Intent intent4 = new Intent(getActivity(),HomeMenuClassActivity.class);
			intent4.putExtra("menu", "其他");
			startActivity(intent4);
			getActivity().finish();
			break;

		default:
			break;
		}
	}

}
