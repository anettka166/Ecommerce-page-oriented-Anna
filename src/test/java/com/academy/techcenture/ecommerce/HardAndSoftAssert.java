package com.academy.techcenture.ecommerce;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HardAndSoftAssert {
    @Test
    public void testHard(){

        //we are using hard assertions. when hard assertions fail, the thread will be terminated
        Assert.assertEquals(1,  1);
        Assert.assertTrue("abc".equals("abc"));
        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");
        Assert.assertEquals(8, 9);

        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");

        Assert.assertTrue(true);
        Assert.assertTrue("abc".equals("abc"));
        Assert.assertTrue("abc".equals("abc"));
        Assert.assertTrue("abc".equals("abc"));

        System.out.println("End of test");
    }


    @Test
    public void testSoft(){

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(1,  1);
        softAssert.assertTrue("abc".equals("abc"));
        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");
        softAssert.assertEquals(8, 9);

        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");
        System.out.println("fhjaskdfasd");

        softAssert.assertTrue(true);
        softAssert.assertTrue("abc".equals("abc"));
        softAssert.assertTrue("abc".equals("abc"));
        softAssert.assertTrue("abc".equals("abc"));

        System.out.println("End of test");

        //      DONT FORGET assertAll();
        softAssert.assertAll();


    }

}
