import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Order {
    private static int countOrders;

    private int numberOrder;
    private String dateTime;
    private Map<Product, Integer> productList;

    public Order(Map<Product, Integer> productList) {
        numberOrder = countOrders + 1;
        this.productList = productList;
        countOrders++;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");
        dateTime = LocalDateTime.now().format(formatter);
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public Map<Product, Integer> getOrderProductList() {
        return productList;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "    " + numberOrder + "       " + dateTime;
    }
}
