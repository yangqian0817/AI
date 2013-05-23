package com.mac.smartcontrol.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mac.smartcontrol.EnterModeListActivity;
import com.mac.smartcontrol.ModeCmdListActivity;
import com.mac.smartcontrol.R;
import com.mac.smartcontrol.widget.MarqueeText;

import define.entity.Mode_S;

public class EnterModeListAdapter extends BaseAdapter {
	private Context context;
	private List<Mode_S> modeList;

	public EnterModeListAdapter(Context context, List<Mode_S> modeList) {
		super();
		this.context = context;
		this.modeList = modeList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return modeList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return modeList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater localinflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = localinflater.inflate(R.layout.enter_mode_list_item,
					null);
			TextView mode_name_Tv = (TextView) convertView
					.findViewById(R.id.mode_name_tv);
			MarqueeText voice_name_Tv = (MarqueeText) convertView
					.findViewById(R.id.voice_name_tv);
			MarqueeText area_name_Tv = (MarqueeText) convertView
					.findViewById(R.id.area_name_tv);
			ImageView enter_Iv = (ImageView) convertView
					.findViewById(R.id.enter_btn);
			final Mode_S mode_S = modeList.get(position);
			mode_name_Tv.setText(mode_S.getSzName());
			voice_name_Tv.setText(mode_S.getSzVoice());
			area_name_Tv.setText(((EnterModeListActivity) context).rgn_S
					.getSzName());
			enter_Iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.putExtra("mode", mode_S.getMode_S());
					intent.setClass(context, ModeCmdListActivity.class);
					context.startActivity(intent);
				}
			});
		}
		return convertView;
	}
}