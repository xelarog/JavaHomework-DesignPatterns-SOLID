import Database.*;
import Users.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Product> productsList = fillProductsList();

        IBuyer buyer = new Buyer("Александр");
        IBuyer buyer1 = new VipBuyer("Николай", "золотой");

        Scanner scanner = new Scanner(System.in);
        String input;

        boolean working = true;
        while (working) {

            printMainMenu();

            List<Product> filterList = productsList;
            input = scanner.nextLine().trim();

            switch (input) {

                case "1":  // Показать список
                    printProductsList(productsList);
                    break;

                case "2":  // Поиск

                    String name = null, producer = null;
                    ProductCategory category = null;
                    int priceMax = 0;

                    boolean sw = true;
                    while (sw) {
                        printFilterMenu(name, producer, category, priceMax);
                        input = scanner.nextLine().trim();
                        switch (input) {
                            case "1": //фильтр по товару
                                System.out.println("Введите название товара:");
                                name = scanner.nextLine();
                                break;

                            case "2": // фильтр по производителю
                                System.out.println("Введите производителя:");
                                producer = scanner.nextLine();
                                break;

                            case "3": // фильтр по категории
                                printFilterCategoryMenu();
                                String cat = scanner.nextLine();
                                switch (cat) {
                                    case "1":
                                        category = ProductCategory.VEGETABLE;
                                        break;
                                    case "2":
                                        category = ProductCategory.FRUIT;
                                        break;
                                    case "3":
                                        category = ProductCategory.WINE;
                                        break;
                                    case "4":
                                        category = ProductCategory.MILK;
                                        break;
                                    case "5":
                                        category = ProductCategory.MEAT;
                                        break;
                                    case "6":
                                        category = ProductCategory.BAKERY;
                                        break;
                                }
                                break;

                            case "4":  // фильтр по цене
                                System.out.println("Введите максимальную цену :");
                                try {
                                    priceMax = Integer.parseInt(scanner.nextLine());
                                    if (priceMax <= 0) {
                                        System.out.println("Введите любое число больше 0");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Вы ввели некорректную цену");
                                }
                                break;

                            case "9": // сброс фильтров
                                name = null;
                                producer = null;
                                category = null;
                                priceMax = 0;
                                break;

                            case "0":  // выход
                                if (name != null || producer != null || category != null || priceMax > 0)
                                    sw = false;
                                else System.out.println("Параметры поиска не заданы!");
                                break;

                        }
                    }
                    filterList = buyer.filterProductsList(productsList, name, producer, category, priceMax);
                    printProductsList(filterList);
                    break;

                case "3":  // Добавить в корзину

                    System.out.println("Введите номер товара и количество через пробел (0 - выход)");
                    int number = 0;
                    int amount = 0;

                    while (true) {
                        input = scanner.nextLine().trim();
                        if (input.equals("0"))
                            break;
                        try {
                            String[] productInfo = input.trim().replaceAll("\\s{2,}", " ").split(" ");
                            number = Integer.parseInt(productInfo[0]);
                            amount = Integer.parseInt(productInfo[1]);
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            System.out.println("Неверный формат данных");
                        }
                        if (number > 0 && number <= filterList.size() && amount > 0)
                            buyer.addProductToBasket(filterList.get(number - 1), amount);
                        else System.out.println("Неверный формат ввода");
                    }
                    break;

                case "4":   // Показать корзину
                    Map<Product, Integer> basket = buyer.getProductBasket();
                    if (basket.isEmpty()) {
                        System.out.println("Ваша корзина пуста");
                        break;
                    }
                    printProductBasket(basket);
                    break;

                case "5":   // Оформить заказ
                    buyer.makeOrder();
                    break;

                case "6":   // Просмотр истории заказов
                    if (emptyListOrders(buyer))
                        break;
                    Collection<Order> orderSet = buyer.getListOrders().values();
                    System.out.println("№ заказа       Дата заказа");
                    orderSet.forEach(System.out::println);
                    break;

                case "7":   // Подробная информация о заказе
                    if (emptyListOrders(buyer))
                        break;
                    int numberOrder = 0;
                    System.out.println("Введите номер заказа:");
                    try {
                        numberOrder = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат данных");
                    }

                    Order order = buyer.getOrderByNumber(numberOrder);
                    if (order != null) {
                        System.out.println("Заказ № " + order.getNumberOrder());
                        System.out.println("Дата оформления : " + order.getDateTime());
                        printProductBasket(order.getOrderProductList());
                    }
                    break;

                case "0":   // Выход
                    working = false;

            }

        }
    }

    private static boolean emptyListOrders(IBuyer buyer) {
        if (buyer.getListOrders().isEmpty()) {
            System.out.println("Вы ещё не сделали ни одного заказа");
            return true;
        }
        return false;
    }

    private static void printFilterCategoryMenu() {
        System.out.println("Выберите категорию:");
        System.out.println("1 Овощи");
        System.out.println("2 Фрукты");
        System.out.println("3 Вино");
        System.out.println("4 Молочные");
        System.out.println("5 Мясные");
        System.out.println("6 Выпечка");
    }

    private static void printProductBasket(Map<Product, Integer> basket) {
        printDelim();
        System.out.println("    Ваша корзина:");
        System.out.format("%2s %-20s %s %-6s %-6s\n", "№", "    Наименование", "Количество", "Цена за шт.", "Всего, руб.");
        int i = 1;
        int sumCostBasket = 0;

        for (Product product : basket.keySet()) {
            int sumCostProduct = product.getPrice() * basket.get(product);
            System.out.format("%2d %-20s %6d %10d %11d\n", i, product.getName(), basket.get(product), product.getPrice(), sumCostProduct);
            i++;
            sumCostBasket += sumCostProduct;
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Общая стоимость: " + sumCostBasket + " руб.");
    }

    private static void printFilterMenu(String name, String producer, ProductCategory category, int price) {
        printDelim();
        String categoryStr;
        if (name == null) name = "";
        if (producer == null) producer = "";
        if (category == null) categoryStr = "";
        else categoryStr = category.toString();
        String priceMax = "";
        if (price > 0) priceMax = String.valueOf(price);

        System.out.println("    Задайте параметры поиска (минимум один):");
        System.out.println("1 По названию : " + name);
        System.out.println("2 По производителю : " + producer);
        System.out.println("3 По категории : " + categoryStr);
        System.out.println("4 По цене : " + priceMax);
        System.out.println("9 Сбросить параметры поиска");
        System.out.println("0 Начать поиск >>");
    }

    private static void printMainMenu() {
        printDelim();
        System.out.println("    Выберите действие:");
        System.out.println("1 Показать список товаров");
        System.out.println("2 Найти товар");
        System.out.println("3 Добавить товары в корзину");
        System.out.println("4 Показать корзину");
        System.out.println("5 Оформить заказ");
        System.out.println("6 История заказов");
        System.out.println("7 Подробная информация о заказе (по номеру)");
        System.out.println("0 Выход");

    }

    private static void printProductsList(List<Product> list) {

        if (list.isEmpty()) {
            System.out.println("Ничего не найдено.");
            return;
        }

        printDelim();
        System.out.format("%2s    %-20s %-30s %-12s %5s \n", "№", "Название", "Производитель", "Категория", "Цена, руб.");
        printDelim();
        for (int i = 0; i < list.size(); i++) {
            System.out.format("%2d    %-20s %-30s %-12s %5d",
                    i + 1,
                    list.get(i).getName(),
                    list.get(i).getProducer(),
                    list.get(i).getCategoryString(),
                    list.get(i).getPrice());
            System.out.println();
        }
    }

    private static void printDelim() {
        System.out.println("----------------------------------------------------------------------------");
    }

    private static List<Product> fillProductsList() {
        List<Product> list = new ArrayList<>();

        list.add(new Product("Молоко", ProductCategory.MILK, 60, "Простоквашино"));
        list.add(new Product("Кефир", ProductCategory.MILK, 70, "Простоквашино"));
        list.add(new Product("Творог", ProductCategory.MILK, 300, "Горки"));
        list.add(new Product("Йогурт", ProductCategory.MILK, 35, "Горки"));

        list.add(new Product("Батон нарезной", ProductCategory.BAKERY, 30, "Волжский пекарь"));
        list.add(new Product("Хлеб Дарницкий", ProductCategory.BAKERY, 35, "Волжский пекарь"));
        list.add(new Product("Батон отрубной", ProductCategory.BAKERY, 30, "Хлеб"));
        list.add(new Product("Булочка сдобная", ProductCategory.BAKERY, 25, "Домашняя выпечка"));

        list.add(new Product("Яблоки", ProductCategory.FRUIT, 60, "Россия"));
        list.add(new Product("Бананы", ProductCategory.FRUIT, 89, "Эквадор"));
        list.add(new Product("Груша", ProductCategory.FRUIT, 200, "Испания"));
        list.add(new Product("Манго", ProductCategory.FRUIT, 300, "Бразилия"));

        list.add(new Product("Колбаса Докторская", ProductCategory.MEAT, 60, "Клинский"));
        list.add(new Product("Сервелат", ProductCategory.MEAT, 300, "Микоян"));
        list.add(new Product("Грудинка", ProductCategory.MEAT, 250, "Дмитрогорский продукт"));
        list.add(new Product("Паштет", ProductCategory.MEAT, 80, "Дмитрогорский продукт"));

        list.add(new Product("Огурцы", ProductCategory.VEGETABLE, 110, "Простоквашино"));
        list.add(new Product("Помидоры", ProductCategory.VEGETABLE, 150, "Простоквашино"));
        list.add(new Product("Морковь", ProductCategory.VEGETABLE, 35, "Горки"));
        list.add(new Product("Картофель", ProductCategory.VEGETABLE, 40, "Горки"));

        list.add(new Product("Белое", ProductCategory.WINE, 260, "Вина Кубани"));
        list.add(new Product("Красное", ProductCategory.WINE, 280, "Вина Кубани"));
        list.add(new Product("Шампанское", ProductCategory.WINE, 340, "МКШВ"));
        list.add(new Product("Коньяк", ProductCategory.WINE, 600, "Армянский"));

        return list;
    }
}
