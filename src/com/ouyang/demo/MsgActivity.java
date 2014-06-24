package com.ouyang.demo;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ouyang.resume.R;

public class MsgActivity extends Activity
{
  private void debugLog(Object msg)
  {
    Log.i("debugInfo", msg + "");
  }
	
  private EditText edt_phone;
  private EditText edt_content;
  private Button btn_commit;
  
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sendmsg);
		
		edt_content = (EditText) findViewById(R.id.edt_content);
		edt_phone = (EditText) findViewById(R.id.edt_phone);
		btn_commit = (Button) findViewById(R.id.btn_sms);
		
		btn_commit.setOnClickListener(clickListener);
		
	}
	
	View.OnClickListener clickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
		  String phoneString = edt_phone.getText().toString();
		  String contentString = edt_content.getText().toString();
		  SmsManager smsManager = SmsManager.getDefault();
		  PendingIntent sentIntent = PendingIntent.getBroadcast(MsgActivity.this, 0, new Intent(), 0);
		  List<String> msgs = smsManager.divideMessage(contentString);
		  for (String msg : msgs)
      {
        smsManager.sendTextMessage(phoneString, null, msg, sentIntent, null);
      }
		  Toast.makeText(MsgActivity.this, "发送成功！收费一元！", Toast.LENGTH_LONG).show();
		}
	};


}
