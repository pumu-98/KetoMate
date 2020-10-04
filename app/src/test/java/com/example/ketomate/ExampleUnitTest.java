package com.example.ketomate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private MainActivity2 mainActivity2;

//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }




    @Before
    public void setUp(){
        mainActivity2=new MainActivity2();
    }

    //Assume that customer selected large portion and additinally added fish and egg

    @Test
    public void TestPortion1() {

        float outcome=mainActivity2.calcAmount(false,true,true,false,false,true,false);

        assertEquals(550,outcome,0.001);
    }

    //Assume that customer selected large portion and additinally added chicken, prawns and cuttlefish

    @Test
    public void TestPortion2() {

        float outcome=mainActivity2.calcAmount(false,true,false,true,true,false,true);

        assertEquals(940,outcome,0.001);
    }

    //Assume that customer selected medium portion and additinally added fish and egg

    @Test
    public void TestPortion3() {

        float outcome=mainActivity2.calcAmount(true,false,true,false,false,true,false);

        assertEquals(450,outcome,0.001);
    }

    //Assume that customer selected medium portion and additinally added chicken, prawns and cuttlefish

    @Test
    public void TestPortion4() {

        float outcome=mainActivity2.calcAmount(true,false,false,true,true,false,true);

        assertEquals(840,outcome,0.001);
    }

    //Assume that customer selected large portion and additinally added  prawns and egg

    @Test
    public void TestPortion5() {

        float outcome=mainActivity2.calcAmount(false,true,false,false,true,true,false);

        assertEquals(600,outcome,0.001);
    }

}