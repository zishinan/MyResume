package com.ouyang.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseService 
{
	private Context context;
	
	public DataBaseService(Context context)
	{
		this.context = context;
	}
	public void createDataBase(String name)
	{
		context.openOrCreateDatabase(name, Context.MODE_PRIVATE, null);
//		SQLiteDatabase.openOrCreateDatabase("data/data/com.ouyang.resume/haha", null);
	}
}
