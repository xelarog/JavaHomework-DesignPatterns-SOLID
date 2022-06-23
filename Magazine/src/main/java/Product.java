public class Product {
    private String name;
    private ProductCategory category;
    private int price;
    private String producer;

    public Product(String name, ProductCategory category, int price, String producer) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public String getCategoryString() {
        switch(category) {
            case MEAT:
                return "Мясные";
            case MILK:
                return "Молочные";
            case VEGETABLE:
                return "Овощи";
            case FRUIT:
                return "Фрукты";
            case BAKERY:
                return "Выпечка";
            case WINE:
                return "Вино";
        }
        return "";
    }

    public ProductCategory getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public String getProducer() {
        return producer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }
}
