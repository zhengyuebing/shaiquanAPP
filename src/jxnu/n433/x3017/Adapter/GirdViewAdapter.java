package jxnu.n433.x3017.Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import jxnu.n433.x3107.SunGroup.R;
import jxnu.n433.x3107.bean.MySelfListing;
import jxnu.n433.x3107.sqlite.GoodsDataHelper;
import jxnu.n433.x3107.utils.Utils;

public class GirdViewAdapter extends BaseAdapter{



	private int colors[] = new int[] {
			Color.rgb(189, 202, 188),
			Color.rgb(222, 203, 161),
			Color.rgb(244, 107, 65)
	};


	private Context mContext;
	private GoodsDataHelper goodsDHelper ;
	private List<MySelfListing> mSelfListing= new ArrayList<MySelfListing>();;

	public GirdViewAdapter(Context context,List<MySelfListing> mSelfListings) {
		this.mContext = context;
		this.mSelfListing = mSelfListings;
		goodsDHelper = new GoodsDataHelper(mContext);


		mSelfListing = goodsDHelper.getGoodsInfoList();
	}

	@Override
	public int getCount() {

		if (mSelfListing.size()<6) {
			return mSelfListing.size();
		}else {
			return 6;
		}


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

		HotGriViewHolder holder = null;
		if (convertView == null) {
			Random random = new Random();
			int n = random.nextInt(4);
			if (n % 2 == 0) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.homepage_layout_main_adapter_commodity_view_left,
						null
						);
			} else {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.homepage_layout_main_adapter_commodity_view_right,
						null
						);
			}
			holder = new HotGriViewHolder(convertView,position,mSelfListing);
			Random random2 = new Random();
			int n2 = random2.nextInt(3);
			holder.getBgLayout().setBackgroundColor(colors[n2]);

			try {
				Bitmap btUP = BitmapFactory.decodeFile(mSelfListing.get(position).getGoodsImages());//从Sd中找头像，转换成Bitmap
				if(btUP!=null){
					@SuppressWarnings("deprecation")
					Drawable drawable = new BitmapDrawable(btUP);//转换成drawable
					holder.getImageView().setImageDrawable(drawable);
				}else{
					/**
					 *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
					 * 
					 */

					//				Utils.showToast(getActivity(), "没有图片");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}


			holder.getText();
			holder.getShopName();
			convertView.setTag(holder);
		}else {
			holder = (HotGriViewHolder) convertView.getTag();
		}

		return convertView;
	}

	private class HotGriViewHolder implements OnClickListener{

		private ImageView imageView;
		private TextView shopName;
		private TextView text;
		private View view;
		private int position;
		private GoodsDataHelper gDHelper;
		private List<MySelfListing> selfListing;

		private RelativeLayout bgLayout;

		private HotGriViewHolder(View view,int position,List<MySelfListing> selfListing){
			this.view = view;
			this.position = position;
			this.selfListing = selfListing;

			selfListing = new ArrayList<MySelfListing>();
			gDHelper = new GoodsDataHelper(mContext);
			selfListing = gDHelper.getGoodsInfoList();
		}

		public ImageView getImageView() {

			if (imageView == null) {
				imageView = (ImageView) view.findViewById(R.id.commodity_name);

			}

			return imageView;
		}

		public TextView getShopName() {

			if (shopName == null) {
				shopName = (TextView) view.findViewById(R.id.commodity_shopname);
				shopName.setText(selfListing.get(position).getGoodsName()+"");
			}

			return shopName;
		}

		public TextView getText() {

			if (text == null) {
				text = (TextView) view.findViewById(R.id.commodity_text);
				text.setText(selfListing.get(position).getGoodsIntro());
			}

			return text;
		}

		public RelativeLayout getBgLayout() {

			if (bgLayout == null) {
				bgLayout = (RelativeLayout) view.findViewById(R.id.commodity_bg);
				//				bgLayout.setOnClickListener(this);

			}

			return bgLayout;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.commodity_bg:
				Utils.showToast(mContext, shopName.getText().toString().trim()+"");
				break;

			default:
				break;
			}
		}


	}

}
