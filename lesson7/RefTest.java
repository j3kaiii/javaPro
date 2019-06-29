package lesson7;

import sun.security.jca.GetInstance;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RefTest {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException {

        File f = new File("D:/DZ");
        // для каждого файла в директории
        for ( File file : f.listFiles()) {
            String[] str = file.getName().split("\\.");
            // проверяем расширение файла. Если class, то ->
            if (str[1].equalsIgnoreCase("class")) {
                String name = str[0];
                System.out.println(name);
                Class  temp = URLClassLoader.newInstance(new URL[]{new File("D:/DZ").toURL()}).loadClass(name);
                System.out.println("Class name : " + temp.getSimpleName() + "\n");

                Field[] fields = temp.getDeclaredFields();
                if (fields.length == 0) {
                    System.out.println("No fields\n");
                } else {
                    for (Field fi : fields) {
                        System.out.println(fi.getType().getName() + " : " + fi.getName());
                    }
                }

//                Method[] m = temp.getDeclaredMethods();
//                for (Method met : m) {
//                    System.out.println(met);
//                }

                Method isNegativeM = temp.getDeclaredMethod("isNegative", int.class);
                isNegativeM.setAccessible(true);
                try {

                    if ((boolean)isNegativeM.invoke(temp, -1) && !(boolean)isNegativeM.invoke(temp, 1)) {
                        System.out.println("Method " + isNegativeM.getName() + " is correct!");
                    } else {
                        System.out.println("Method " + isNegativeM.getName() + " is incorrect!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }


                Method calculateIntM = temp.getDeclaredMethod("calculate", int.class, int.class, int.class, int.class);
                calculateIntM.setAccessible(true);
                try {

                    if ((int)calculateIntM.invoke(temp, 3,1,4,2) == 9) {
                        System.out.println("Method (int)" + calculateIntM.getName() + " is correct!");
                    } else {
                        System.out.println("Method (int)" + calculateIntM.getName() + " is incorrect!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                Method calculateFloatM = temp.getDeclaredMethod("calculate", float.class, float.class, float.class, float.class);
                calculateFloatM.setAccessible(true);
                try {

                    if ((float)calculateFloatM.invoke(temp, 4.0f,1.0f,3.0f,2.0f) == 10.0f) {
                        System.out.println("Method (float)" + calculateFloatM.getName() + " is correct!");
                    } else {
                        System.out.println("Method (float)" + calculateFloatM.getName() + " is incorrect!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                Method checkTwoNumbersM = temp.getDeclaredMethod("checkTwoNumbers", int.class, int.class);
                checkTwoNumbersM.setAccessible(true);
                try {

                    if ((boolean)checkTwoNumbersM.invoke(temp, 10, 5)) {
                        System.out.println("Method " + checkTwoNumbersM.getName() + " is correct!");
                    } else {
                        System.out.println("Method " + checkTwoNumbersM.getName() + " is incorrect!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

//                Method printIsPositiveM = temp.getDeclaredMethod("printIsPositive", int.class);
//                printIsPositiveM.setAccessible(true);
//                try {
//
//                    if () {
//                        System.out.println("Method " + printIsPositiveM.getName() + " is correct!");
//                    } else {
//                        System.out.println("Method " + printIsPositiveM.getName() + " is incorrect!");
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                Method isLeapYearM = temp.getDeclaredMethod("isLeapYear", int.class);
                isLeapYearM.setAccessible(true);
                try {

                    if ((boolean)isLeapYearM.invoke(temp, 2000)) {
                        System.out.println("Method " + isLeapYearM.getName() + " is correct!");
                    } else {
                        System.out.println("Method " + isLeapYearM.getName() + " is incorrect!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
