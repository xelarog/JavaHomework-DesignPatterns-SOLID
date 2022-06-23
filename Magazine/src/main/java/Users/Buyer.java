package Users;

import Database.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Buyer implements IBuyer {

    private static int countBuyers;

    private final int ID;
    private String name;

    private Map<Integer, Order> listOrders;
    private Map<Product, Integer> productBasket;

    public Buyer(String name) {
        this.ID = countBuyers + 1;
        this.name = name;
        listOrders = new HashMap<>();
        productBasket = new HashMap<>();
        countBuyers++;
    }

    @Override
    public List<Product> filterProductsList(List<Product> list, String name, String producer, ProductCategory category, int priceMax) {

        System.out.println("Запускаю поиск...");
        Stream<Product> stream = list.stream();
        if (name != null)
            stream = stream.filter(p -> p.getName().contains(name));
        if (producer != null)
            stream = stream.filter(p -> p.getProducer().contains(producer));
        if (category != null)
            stream = stream.filter(p -> p.getCategory().equals(category));
        if (priceMax > 0)
            stream = stream.filter(p -> p.getPrice() < priceMax);
        return stream.collect(Collectors.toList());
    }

    @Override
    public void addProductToBasket(Product product, int count) {

        if (productBasket.containsKey(product)) {
            productBasket.replace(product, productBasket.get(product) + count);
            return;
        }
        productBasket.put(product, count);

    }

    @Override
    public Map<Product, Integer> getProductBasket() {
        return productBasket;
    }

    @Override
    public void makeOrder() {

        if (productBasket.isEmpty()) {
            System.out.println("Ваша корзина пуста!");
            return;
        }
        Order order = new Order(productBasket);
        productBasket = new HashMap<>();
        listOrders.put(order.getNumberOrder(), order);
        System.out.println("Ваш заказ оформлен!");

    }

    @Override
    public Order getOrderByNumber(int numberOrder) {

        if (!listOrders.containsKey(numberOrder)) {
            System.out.println("Заказа с таким номером не существует");
            return null;
        }
        return listOrders.get(numberOrder);
    }

    @Override
    public Map<Integer, Order> getListOrders() {
        return listOrders;
    }

}
