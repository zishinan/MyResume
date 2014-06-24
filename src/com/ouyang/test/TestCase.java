package com.ouyang.test;

import junit.framework.Assert;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestCase extends AndroidTestCase
{
  private void debugLog(Object msg)
  {
    Log.i("debugInfo", msg + "");
  }
  
  private void testRight() throws Throwable
  {
    // TODO 单元测试方法找不到，查下原因
    Assert.assertTrue(1 + 1 == 2);
  }
}
