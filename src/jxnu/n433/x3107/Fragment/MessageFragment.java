package jxnu.n433.x3107.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import jxnu.n433.x3107.SunGroup.R;

public class MessageFragment extends Fragment {
	private View messageLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		messageLayout = inflater.inflate(R.layout.message_layout, container,false);
		return messageLayout;
	}
	
}
