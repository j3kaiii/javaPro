package annotationTest;

public class TestClass {
    public static  void method1() {
        System.out.println("met1");
    }
    @BeforeSuite
    public static void start() {
        System.out.println("start");
    }
    @Test
    public static  void method2() {
        System.out.println("met2");
    }
    @Test(priority = 5)
    public static  void method3() {
        System.out.println("met3");
    }
    @Test(priority = 7)
    public static  void method4() {
        System.out.println("met4");
    }
    @AfterSuite
    public static void shutdown() {
        System.out.println("shutdown");
    }
}
