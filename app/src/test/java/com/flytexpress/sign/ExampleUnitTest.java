package com.flytexpress.sign;

import org.junit.Test;

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
    public void testData(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        System.out.println("testData: "+sdf.format(new Date()));
    }
    @Test
    public void testMain(){
        MainActivity m=new MainActivity();
        m.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
    @Test
    public void  testApp(){
        //返回大写字母

    }
}