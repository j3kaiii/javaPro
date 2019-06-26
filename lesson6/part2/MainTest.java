package lesson6.part2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MainTest {

    @Test
    public void testGetMass() {
        int[] sources = {1,2,3,4,5,4,6,4,7,8};
        int[] expected = {7,8};
        String s = Arrays.toString(sources);
        String e = Arrays.toString(expected);
        int[] actual = Main.getMass(sources);
        String a = Arrays.toString(actual);
        Assert.assertEquals(e, a);
    }

    @Test
    public void testGetMass2() {
        int[] sources = {1,2,3,5,4,6,7,8};
        int[] expected = {6,7,8};
        String s = Arrays.toString(sources);
        String e = Arrays.toString(expected);
        int[] actual = Main.getMass(sources);
        String a = Arrays.toString(actual);
        Assert.assertEquals(e, a);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMass3() {
        int[] sources = {1,2,3,5,6,7,8};
        Main.getMass(sources);
    }
}
