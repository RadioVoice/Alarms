package alarms;

import org.junit.Test;
import static org.junit.Assert.*;

public class AlarmSystemTest {
    @Test
    public void normalTest(){
        String args[] = {"TestFiles\\test1.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("false", out.toString().trim());
    }

    @Test
    public void nonSquareTest(){
        String args[] = {"TestFiles\\test2.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("false", out.toString().trim());
    }

    @Test
    public void airplaneTest(){
        String args[] = {"TestFiles\\test3.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("false", out.toString().trim());
    }

    @Test
    public void topShiftTest(){
        String args[] = {"TestFiles\\test4.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("false", out.toString().trim());
    }

    @Test
    public void fallingTest(){
        String args[] = {"TestFiles\\test5.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("true", out.toString().trim());
    }

    @Test
    public void multipleFallTest(){
        String args[] = {"TestFiles\\test6.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("true", out.toString().trim());
    }

    @Test
    public void airplaneNextToBoxTest(){
        String args[] = {"TestFiles\\test7.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("false", out.toString().trim());
    }
    @Test
    public void invalidMatricesTest(){
        String args[] = {"TestFiles\\test8.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("invalid", out.toString().trim());
    }
    @Test
    public void invalidNegativeDimensionTest(){
        String args[] = {"TestFiles\\test9.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("invalid", out.toString().trim());
    }

    @Test
    public void invalidMatrixSizeTest(){
        String args[] = {"TestFiles\\test10.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("invalid", out.toString().trim());
    }
    @Test
    public void invalidMissingDimensionsTest(){
        String args[] = {"TestFiles\\test11.txt"};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("invalid", out.toString().trim());
    }

    @Test
    public void invalidArgumentTest(){
        String args[] = {""};
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        AlarmSystem.main(args);
        assertEquals("invalid", out.toString().trim());
    }
}
