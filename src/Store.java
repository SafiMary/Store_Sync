import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Store {
    // объект хранения данных, член product является монитором
    // через который общаются поток производителя и магазина
    Map<String, Integer> product = new HashMap<String, Integer>();
    final static String outputFilePath = "data.txt";
    public void Add() {//метод добавления слов в словарь
        Scanner i = new Scanner(System.in);
        System.out.print("Для внесения новой категории продукта введите reg"+"\n"+
                "Для выхода из меню введите &"+"\n");
        boolean cheaker = true;
        while (cheaker) {
            String str = i.nextLine();
            switch (str) {
                case "reg":
                    Scanner in = new Scanner(System.in);
                    System.out.print("Введите новый продукт в формате 'Категория: количество'" + "\n");
                    String key_value = in.nextLine();
                    String[] v = key_value.split(": ");
                    String key = v[0];
                    Integer value = Integer.parseInt(v[1]);
                    synchronized (product) {
                        if (product.containsKey(key))
                            product.put(key, value + product.get(key));
                        else
                            product.put(key, value);
                        System.out.print("Продукт " + key + " добавлен в количестве " + value + "\n");
                        System.out.println("Товаров на складе: ");
                        Show();
                        product.notify();
                    }
                    Filewriter();
                    break;
                case "&":
                    cheaker = false; // выход из меню
                    System.out.println("Вы вышли из меню");
                    break;
                default:
                    break;
            }
        }

    }
    public void Show(){//показать что на складе есть
        Map<String, Integer>  sorted = new TreeMap<String, Integer>(product);
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
    public void Filewriter() {
        File file = new File(outputFilePath);
        BufferedWriter bf = null;
        synchronized (product) {
            try {
                bf = new BufferedWriter(new FileWriter(file));
                for (Map.Entry<String, Integer> entry : product.entrySet()) {
                    bf.write(entry.getKey() + ":" + entry.getValue());
                    bf.newLine();
                }
                bf.flush();
                System.out.println("Файл успешно записан!");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    bf.close();

                } catch (Exception e) {
                }
            }
        }
    }


}
