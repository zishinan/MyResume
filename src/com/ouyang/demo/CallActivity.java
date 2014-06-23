package com.ouyang.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ouyang.resume.R;

public class CallActivity extends Activity
{
//	private DataBaseService service;
	private static final String tag = "MainActivity";
	
	private Button bt_call;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

//		service = new DataBaseService(this);
//		service.createDataBase("resume");
		setContentView(R.layout.callphone);
		
		Log.i(tag, "call activity created!");

		bt_call = (Button) findViewById(R.id.bt_call);
		
		bt_call.setOnClickListener(clickListener);
	}
	
	View.OnClickListener clickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			Log.i(tag, " call phone");
		}
	};


}
