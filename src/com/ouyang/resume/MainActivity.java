package com.ouyang.resume;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ouyang.demo.CallActivity;
import com.ouyang.demo.FileTestActivity;
import com.ouyang.demo.MsgActivity;
import com.ouyang.service.DataBaseService;

public class MainActivity extends Activity
{
  @SuppressWarnings("unused")
  private void debugLog(Object msg)
  {
    Log.i("debugInfo", msg + "");
  }
	private DataBaseService service;
	private Button bt_personinfo;
	private Button bt_education;
	private Button bt_workExp;
	private Button bt_projectExp;
	private Button bt_skill;
	private Button bt_certificate;
	private Button bt_evaluation;
	
	@Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    service = new DataBaseService(this);
    service.createDataBase("resume");
    setContentView(R.layout.main);
    
    bt_certificate = (Button) findViewById(R.id.bt_certificate);
    bt_education = (Button) findViewById(R.id.bt_education);
    bt_evaluation = (Button) findViewById(R.id.bt_evaluation);
    bt_personinfo = (Button) findViewById(R.id.bt_personalinfo);
    bt_projectExp = (Button) findViewById(R.id.bt_projectexp);
    bt_skill = (Button) findViewById(R.id.bt_skill);
    bt_workExp = (Button) findViewById(R.id.bt_workexp);
    
    bt_certificate.setOnClickListener(clickListener);
    bt_education.setOnClickListener(clickListener);
    bt_evaluation.setOnClickListener(clickListener);
    bt_personinfo.setOnClickListener(clickListener);
    bt_projectExp.setOnClickListener(clickListener);
    bt_skill.setOnClickListener(clickListener);
    bt_workExp.setOnClickListener(clickListener);
  }
	
	private void createDatabase()
  {
    service = new DataBaseService(this);
    service.createDataBase("resume");
  }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = new Intent();
		int id = item.getItemId();
		switch (id)
    {
      case R.id.itm_callPhone:
        intent.setClass(MainActivity.this, CallActivity.class);
        startActivity(intent);
        break;
      case R.id.itm_sendMsg:
        intent.setClass(MainActivity.this, MsgActivity.class);
        startActivity(intent);
        break;
      case R.id.itm_filePage:
        intent.setClass(MainActivity.this, FileTestActivity.class);
        startActivity(intent);
        break;

      default:
        break;
    }
		return super.onOptionsItemSelected(item);
	}
	
	
	View.OnClickListener clickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			int viewId = v.getId();
			switch (viewId)
			{
			case R.id.bt_personalinfo:
				clickPersonalinfo(v);
				break;
			case R.id.bt_certificate:
				clickCertificate(v);
				break;
			case R.id.bt_education:
				clickEducation(v);
				break;
			case R.id.bt_evaluation:
				clickEvaluation(v);
				break;
			case R.id.bt_projectexp:
				clickProjectExp(v);
				break;
			case R.id.bt_workexp:
				clickWorkExp(v);
				break;
			case R.id.bt_skill:
				clickSkill(v);
				break;

			default:
				break;
			}
		}
	};

	public void clickPersonalinfo(View source)
	{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, PersonalInfoActivity.class);
		startActivity(intent);
	}

	public void clickEducation(View source)
	{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, EducationActivity.class);
		startActivity(intent);
	}

	public void clickWorkExp(View source)
	{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, WorkExpActivity.class);
		startActivity(intent);
	}

	public void clickProjectExp(View source)
	{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, ProjectExpActivity.class);
		startActivity(intent);
	}

	public void clickSkill(View source)
	{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, SkillActivity.class);
		startActivity(intent);
	}

	public void clickCertificate(View source)
	{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, CertificateActivity.class);
		startActivity(intent);
	}

	public void clickEvaluation(View source)
	{
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, EvaluationActivity.class);
		startActivity(intent);
	}

	private static boolean isExit = false;
	private static boolean hasTask = false;
	Timer tExit = new Timer();
	TimerTask task = new TimerTask()
	{
		@Override
		public void run()
		{
			isExit = false;
			hasTask = true;
		}
	};

	/* 
	 * 连续点击两次返回键退出
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if (isExit == false)
			{
				isExit = true;
				Toast.makeText(this, "再按一次退出！", Toast.LENGTH_SHORT).show();
				if (!hasTask)
				{
					tExit.schedule(task, 2000);
				}
			}
			else
			{
				finish();
				System.exit(0);
			}
		}
		return false;
	}
}
