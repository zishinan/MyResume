package com.ouyang.demo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ouyang.resume.R;

public class FileTestActivity extends Activity
{
  private final String fileName = "ouyangxi.txt";
  private void debugLog(Object msg)
  {
    Log.i("debugInfo", msg + "");
  }

  Button btn_write;
  Button btn_read;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.filetest);
    btn_write = (Button) findViewById(R.id.btn_writeFile);
    btn_read = (Button) findViewById(R.id.btn_readFile);
    btn_write.setOnClickListener(listener);
    btn_read.setOnClickListener(listener);
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

        default:
          break;
      }
    }

  };

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
