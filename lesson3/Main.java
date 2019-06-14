package lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        //  --  читаем 50 байт файла и выводим на консоль  --
//        try {
//            FileInputStream in = new FileInputStream("test/test1.txt");
//            byte[]  arr= new byte[50];
//
//            int x;
//            x = in.read(arr);
//            System.out.print(new String(arr, 0, x, "UTF-8"));
//
//            in.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //  --  собираем несколько файлов в один  --

//        ArrayList<InputStream> ali = new ArrayList<InputStream>();
//        try {
//            FileOutputStream fos = new FileOutputStream("test/text4.txt");
//            ali.add(new FileInputStream("test/test1.txt"));
//            ali.add(new FileInputStream("test/test2.txt"));
//            SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(ali));
//
//            int x;
//            while ((x = sis.read()) != -1) {
//                fos.write(x);
//            }
//
//            fos.close();
//            sis.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        int count = 0;
        String text;
        System.out.print("Введите номер страницы для чтения : ");
        long t = System.currentTimeMillis();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int page = Integer.parseInt(br.readLine());  // юзер вводит номер страницы
            br = new BufferedReader(new FileReader("test/text4.txt"));  // читаем файл
            br.skip((page - 1) * 1800);  // пропускаем нужное кол-во символов
            while (true) {
                text = br.readLine(); // читаем строку из файла
                System.out.println(text); // выводим ее на консоль
                count += text.length();  // счетчик колличества символов
                if (count >= 1800) break;  // 1800 символов - страница прочитана
            }
            System.out.println("Страница прочитана");
            System.out.println(System.currentTimeMillis() - t);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
