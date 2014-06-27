package com.ouyang.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.SAXParserFactory;
import android.R.bool;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.ouyang.resume.R;

public class FileTestActivity extends Activity
{
  private final String fileName = "ouyangxi.xml";

  private void debugLog(Object msg)
  {
    Log.i("debugInfo", msg + "");
  }

  Button btn_write;
  Button btn_read;
  Button btn_sdOpen;
  Button btn_sdRead;
  Button btn_sax;
  Button btn_setting;
  
  private AlertDialog.Builder builder;

  @SuppressLint("NewApi")
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.filetest);
    btn_write = (Button) findViewById(R.id.btn_writeFile);
    btn_read = (Button) findViewById(R.id.btn_readFile);
    btn_sdOpen = (Button) findViewById(R.id.btn_sdOpen);
    btn_sax = (Button) findViewById(R.id.btn_sax);
    btn_setting = (Button) findViewById(R.id.btn_setting);
    btn_setting.setOnClickListener(listener);
    btn_sdOpen.setOnClickListener(listener);
    btn_write.setOnClickListener(listener);
    btn_read.setOnClickListener(listener);
    btn_sax.setOnClickListener(listener);
    
    final String[] items = { "java", ".net", "php","webservice" };
    builder = new AlertDialog.Builder(FileTestActivity.this).setTitle(R.string.settingText);
    builder.setItems(items, new DialogInterface.OnClickListener()
    {
       public void onClick(DialogInterface dialog, int item)
       {
          Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
       }
    });
  }

  View.OnClickListener listener = new View.OnClickListener()
  {
    @Override
    public void onClick(View v)
    {
      switch (v.getId())
      {
        case R.id.btn_readFile:
          readFile();
          break;
        case R.id.btn_writeFile:
          writeFile();
          break;
        case R.id.btn_sdOpen:
          sdOpen();
          break;
        case R.id.btn_sax:
          saxTest();
          break;
        case R.id.btn_setting:
          builder.show();
          break;

        default:
          break;
      }
    }

  };
  
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // TODO 初始化的时候也会弹出 暂不处理
    builder.show();
    return false;
  }

  /**
   * 写文件操作
   */
  private void writeFile()
  {
    FileOutputStream outStream;
    try
    {
      outStream = FileTestActivity.this.openFileOutput(fileName, Context.MODE_PRIVATE);
      outStream.write("ouyangxi test".getBytes());
      outStream.close();
    } catch (Exception e)
    {
      debugLog(e.getMessage());
    }
    debugLog("写入文件成功！");
  }
  
  
  
  

  /**
   * sax解析xml
   */
  private void saxTest()
  {
    // TODO 暂时不做xml解析处理，先学后面的内容
    SAXParserFactory.newInstance();
  }

  /**
   * sd写文件操作
   */
  private void sdOpen()
  {
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
    {
      File sdCardDir = Environment.getExternalStorageDirectory();
      File saveFile = new File(sdCardDir, fileName);
      try
      {
        FileOutputStream outputStream = new FileOutputStream(saveFile);
        String string = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><persons><person id=\"18\">"
            + "<name>allen</name><age>36</age></person><person id=\"28\"><name>james</name>"
            + "<age>25</age></person></persons>";
        outputStream.write(string.getBytes());
        outputStream.close();
      } catch (FileNotFoundException e)
      {
        debugLog(e.getMessage());
      } catch (IOException e)
      {
        debugLog(e.getMessage());
      }

    }
  }

  /**
   * 读取文件操作
   */
  private void readFile()
  {
    FileInputStream inStream = null;
    try
    {
      inStream = openFileInput(fileName);
    } catch (FileNotFoundException e)
    {
      debugLog(e.getMessage());
    }
    debugLog(readInStream(inStream));
  }

  private String readInStream(FileInputStream inStream)
  {
    try
    {
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int length = -1;
      while ((length = inStream.read(buffer)) != -1)
      {
        outStream.write(buffer, 0, length);
      }
      outStream.close();
      inStream.close();
      return outStream.toString();
    } catch (IOException e)
    {
      debugLog(e.getMessage());
    }
    return null;
  }
  
  
}
