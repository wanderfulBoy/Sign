package com.flytexpress.sign;

import android.content.Context;
import android.test.ServiceTestCase;

import com.flytexpress.sign.util.Tools;
import com.flytexpress.sign.util.image.UploadFileTask;

import org.junit.Test;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        System.out.println("testData: " + sdf.format(new Date()));
        String str= testApp("abc");
    }

    @Test
    public void testMain() {
        MainActivity m = new MainActivity();
        m.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public String testApp(String str) {
        if (Tools.isEmpty(str)) {
            return "";
        }
        //返回大写字母
        return str.toUpperCase();
    }
    public void uploadImage(){
        String uploadFile="";
        Context context=getTestContext();
        MainActivity m=new MainActivity();
        UploadFileTask uploadFileTask = new UploadFileTask(m);
        uploadFileTask.execute(uploadFile);
    }
    private Context getTestContext() {
        try {
            Method getTestContext = ServiceTestCase.class.getMethod("getTestContext");
            return (Context) getTestContext.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}