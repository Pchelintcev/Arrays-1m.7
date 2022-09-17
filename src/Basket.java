import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    private final long serialVersionUID = 1L;
    protected int[] prices; //цены
    protected String[] products; //товары
    protected int[] purchases; //количество
    protected int totalCost = 0;


    protected Basket(int[] prices, String[] products) {
        this.prices = prices;
        this.products = products;
        this.purchases = new int[products.length];
    }

    public int[] getPrices() {
        return prices;
    }

    public String[] getProducts() {
        return products;
    }

    public int[] getPurchases() {
        return purchases;
    }

    public int getTotalCost() {
        return totalCost;
    }

    //добавление товаров в корзину:
    protected void addToCart(int productNum, int quantity) {
        purchases[productNum] += quantity;
    }

    //вывод корзины на экран:
    protected void printCart() {
        for (int i = 0; i < products.length; i++) {
            if (purchases[i] > 0) {
                System.out.println(products[i] + " по " + prices[i] + " руб. - "
                        + purchases[i] + " шт. (на " + (purchases[i] * prices[i]) + " руб.)");
                totalCost += (purchases[i] * prices[i]);
            }
        }
        System.out.print("Общая стоимость: " + totalCost + " руб.\n");
    }

    //сериализация:
    protected void saveBin(File file, Basket b01) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(b01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //де сериализация:
    protected static void loadFromBinFile(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Basket b01 = (Basket) ois.readObject();
            System.out.println(b01);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "В вашей корзине: \n" +
                "продукты: " + Arrays.toString(getProducts()) + "\n" +
                "количество: " + Arrays.toString(getPurchases()) + "\n" +
                "цены: " + Arrays.toString(getPrices()) + "\n" +
                "общая стоимость: " + getTotalCost();
    }
}

