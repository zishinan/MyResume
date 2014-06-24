package com.ouyang.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ouyang.resume.R;

public class CallActivity extends Activity
{
//	private DataBaseService service;
  private void debugLog(Object msg)
  {
    Log.i("debugInfo", msg + "");
  }
	
	private Button bt_call;
	private EditText edt_number;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

//		service = new DataBaseService(this);
//		service.createDataBase("resume");
		setContentView(R.layout.callphone);
		
		bt_call = (Button) findViewById(R.id.bt_call);
		edt_number = (EditText) findViewById(R.id.edt_number);
		
		bt_call.setOnClickListener(clickListener);
	}
	
	View.OnClickListener clickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
		  Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + edt_number.getText()));
		  CallActivity.this.startActivity(intent);
		}
	};


}
