package com.mac.smartcontrol;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mac.smartcontrol.util.NotificationUtil;

public class MainActivity extends ActivityGroup {
	private LinearLayout container = null;
	private int currentPage = 0;
	ImageView manage_Iv = null;
	ImageView control_Iv = null;
	ImageView camera_Iv = null;
	ImageView location_Iv = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ر�����
		setContentView(R.layout.activity_main);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
			currentPage = bundle.getInt("currentPage");
		// ������ͼ
		container = (LinearLayout) findViewById(R.id.containerBody);
		// ģ��1
		manage_Iv = (ImageView) findViewById(R.id.menu_manage_iv);
		control_Iv = (ImageView) findViewById(R.id.menu_control_iv);
		camera_Iv = (ImageView) findViewById(R.id.menu_camera_iv);
		location_Iv = (ImageView) findViewById(R.id.menu_location_iv);

		manage_Iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initFocus();
				currentPage = 0;
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"manage",
						new Intent(MainActivity.this, ManageActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());
				manage_Iv.setImageResource(R.drawable.menu_manage_btn_focus);
			}
		});

		// ģ��2

		control_Iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initFocus();
				currentPage = 1;
				Intent intent = new Intent(MainActivity.this,
						EnterAreaActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("msgId", 34);
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"control", intent).getDecorView());
				control_Iv.setImageResource(R.drawable.menu_device_btn_focus);
			}
		});

		// ģ��3

		camera_Iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initFocus();
				currentPage = 2;
				// container.removeAllViews();
				// container.addView(getLocalActivityManager().startActivity(
				// "camera",
				// new Intent(MainActivity.this, CameraActivity.class)
				// .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				// .getDecorView());
				camera_Iv.setImageResource(R.drawable.menu_camera_btn_focus);
			}
		});

		// ģ��4

		location_Iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initFocus();
				currentPage = 3;
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"location",
						new Intent(MainActivity.this, LocationActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());
				location_Iv
						.setImageResource(R.drawable.menu_location_btn_focus);
			}
		});

		switch (currentPage) {
		case 0:
			manage_Iv.performClick();
			break;
		case 1:
			control_Iv.performClick();
			break;
		case 2:
			camera_Iv.performClick();
			break;
		case 3:
			location_Iv.performClick();
			break;
		default:
			break;
		}
	}

	private void initFocus() {
		switch (currentPage) {
		case 0:
			manage_Iv.setImageResource(R.drawable.menu_manage_btn);
			break;
		case 1:
			control_Iv.setImageResource(R.drawable.menu_device_btn);
			break;
		case 2:
			camera_Iv.setImageResource(R.drawable.menu_camera_btn);
			break;
		case 3:
			location_Iv.setImageResource(R.drawable.menu_location_btn);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SocketService.class);
			stopService(intent);
			dialog();
			return true;
		}
		return true;
	}

	protected void dialog() {
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setMessage("ȷ��Ҫ�˳���?");
		builder.setTitle("��ʾ");
		builder.setPositiveButton("ȷ��",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						finish();
					}
				});
		builder.setNegativeButton("ȡ��",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.setNeutralButton("����",
				new android.content.DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						NotificationUtil.notify(MainActivity.this,
								R.string.app_name, currentPage);
						Intent intent = new Intent(Intent.ACTION_MAIN);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.addCategory(Intent.CATEGORY_HOME);
						MainActivity.this.startActivity(intent);
					}
				});

		builder.create().show();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, SocketService.class);
		stopService(intent);
		android.os.Process.killProcess(android.os.Process.myPid());
		super.finish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		NotificationUtil.cancelNotify(R.string.app_name);
		super.onResume();
	}
}