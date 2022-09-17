import java.io.*;
import java.util.Arrays;

public class Basket {
    protected int[] prices; //цены
    protected String[] products; //товары
    protected int[] quantityes; //количество
    protected int totalCost = 0;

    protected Basket(int[] prices, String[] products) {
        this.prices = prices;
        this.products = products;
        quantityes = new int[products.length];
    }

    //вывод корзины на экран:
    protected void printCart() {
        System.out.println("В вашей корзине: ");
        for (int i = 0; i < products.length; i++) {
            if (quantityes[i] > 0) {
                System.out.println(products[i] + " по " + prices[i] + " руб. - "
                        + quantityes[i] + " шт. (на " + (quantityes[i] * prices[i]) + " руб.)");
                totalCost += (quantityes[i] * prices[i]);
            }
        }
        System.out.println("Общая стоимость: " + totalCost + " руб.");
    }

    //запись корзины в файл basket.txt:
    protected void saveTxt(File file) throws IOException {
        try (PrintWriter saveTxt = new PrintWriter(file)) {
            for (int qua : quantityes) {
                saveTxt.print(qua + " ");
            }
            saveTxt.print("\n");
            for (int pri : prices) {
                saveTxt.print(pri + " ");
            }
            saveTxt.print("\n");
            for (String pro : products) {
                saveTxt.print(pro + " ");
            }
        }
        System.out.println("Данные вашей корзины сохранены в файл basket.txt");
    }

    //добавление товаров в корзину:
    protected void addToCart(int productNum, int quantity) {
        quantityes[productNum] += quantity;
    }

    //чтение из файла и восстановление корзины:
    protected static Basket loadFromTxtFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String[] readArr1 = br.readLine().split(" "); //читаем 1ю строку (количество)
            int[] quantities = new int[readArr1.length];
            for (int i = 0; i < readArr1.length; i++) {
                quantities[i] = Integer.parseInt(readArr1[i]);
            }

            String[] readArr2 = br.readLine().split(" "); //читаем 2ю строку (цены)
            int[] prices = new int[readArr2.length];
            for (int i = 0; i < readArr2.length; i++) {
                prices[i] = Integer.parseInt(readArr2[i]);
            }

            String[] products = br.readLine().split(" "); //читаем 3ю строку (продукты)

            Basket b02 = new Basket(prices, products); //объект - корзина2
            for (int i = 0; i < quantities.length; i++) {
                b02.addToCart(i, quantities[i]);
            }
            b02.printCart(); //выводим корзину
            b02.saveTxt(file); //запись в файл

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}

