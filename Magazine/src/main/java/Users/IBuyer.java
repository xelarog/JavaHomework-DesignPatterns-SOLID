package Users;

import Database.*;

import java.util.List;
import java.util.Map;

public interface IBuyer {


    List<Product> filterProductsList(List<Product> list, String name, String producer, ProductCategory category, int priceMax);

    void addProductToBasket(Product product, int count);

    Map<Product, Integer> getProductBasket();

    void makeOrder();

    Order getOrderByNumber (int numberOrder);

    Map<Integer, Order> getListOrders();



}
