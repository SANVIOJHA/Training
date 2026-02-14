//package com.lpu;
package com.lpu.service;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class CalculationTest {

    @BeforeClass
    public static void Check(){
        System.out.println("before add class");
    }

    @Test
    public void addTest(){
        int res= CalculationService.add(3,3);
        int expected=6;
        Assert.assertEquals(expected,res);


    }
@AfterClass
public static void add(){
    System.out.println("after add class ");
}


    @Test
    public void subsTest() {
        int res1= CalculationService.subs(3,3);
        int expected1=0;
        Assert.assertEquals(expected1,res1);
    }


    @AfterClass
    public static void subs(){
        System.out.println("after subs class ");
    }



@Test
    public void mulTest(){
        int res2=CalculationService
                .mul(6,9);
        int exp=54;
    Assert.assertEquals(exp,res2);
    }
    @Test
    public void divTest(){
        int res2=CalculationService
                .div(9,9);
        int exp=1;
        Assert.assertEquals(exp,res2);
    }
@AfterClass

public static void last(){
    System.out.println("after  all  methods is done :  ");
}


    @Test(timeout = 2000) // 2 seconds
    public void testDelay() throws InterruptedException {
        Thread.sleep(1500);
    }




}
