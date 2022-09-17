import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Булка", "Сыр", "Молоко"}; //товары
        int[] prices = {50, 100, 70};                   //цены
        Basket b01 = new Basket(prices, products); //объект - корзина1
        File file = new File("basket.txt");   //

        if (file.exists()) {
            System.out.println("Восстанавливаю корзину (данными из файла)");
            Basket.loadFromTxtFile(file);
        } else {
            System.out.println("Файл с данными не найден, заполните корзину в ручную.");
            System.out.println("Список товаров, доступных к покупке:");
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + "\t" + products[i] + "\t" + prices[i] + " руб.");
            }
            while (true) {
                System.out.println("Веди номер товара и его количество (через пробел); " +
                        "для подсчета результатов и выхода набери end.");
                String input = scanner.nextLine();
                if ("end".equals(input)) {
                    break;
                }
                String[] choice = input.split(" "); // массив для покупок: 1 3
                int cellNum = Integer.parseInt(choice[0]) - 1; //номер ячейки: 1-1=0
                int quantity = Integer.parseInt(choice[1]); //количество: 3
                b01.addToCart(cellNum, quantity);// вызываем метод добавления товара в корзину

            }
            // вызываем метод вывода корзины на экран
            b01.printCart();

            // вызываем метод записи корзины в файл
            b01.saveTxt(file);
        }
    }
}
