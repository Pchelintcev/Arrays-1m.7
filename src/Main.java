import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Булка", "Сыр", "Молоко"}; //товары
        int[] prices = {50, 100, 70};                   //цены
        Basket b01 = new Basket(prices, products); //экземпляр класса - корзина1
        File file = new File("basket.bin");   //файл для хранения данных


        if (file.exists()) {  // проверяем наличие файла с данными
            System.out.println("Восстанавливаю корзину (данными из файла)");
            Basket.loadFromBinFile(file);
        } else {
            System.out.println("Файл с данными не найден, заполните корзину в ручную.");
            System.out.println("Список товаров, доступных к покупке:");
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + "\t" + products[i] + " - " +
                        prices[i] + " руб.");
            }
            while (true) {
                System.out.println("Веди номер товара и его количество (через пробел); " +
                        "для подсчета результатов и выхода набери end.");
                String input = scanner.nextLine();
                if ("end".equals(input)) {
                    break;
                }
                String[] choice = input.split(" "); // массив для покупок
                int cellNum = Integer.parseInt(choice[0]) - 1; //номер ячейки
                int quantity = Integer.parseInt(choice[1]); //количество
                b01.addToCart(cellNum, quantity);// вызываем метод добавления товара в корзину
            }
            // вызываем метод вывода корзины на экран
            System.out.println("В вашей корзине: ");
            b01.printCart();
        }

// вызываем метод записи корзины в бинарный файл
        b01.saveBin(file, b01);
        System.out.println("Данные вашей корзины сохранены в файл: basket.bin");
    }
}
