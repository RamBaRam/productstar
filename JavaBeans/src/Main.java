public class Main {
    public static void main(String[] args) throws Exception {
        CoffeeShop shop = new CoffeeShop();

        Coffee espresso = new Coffee("Эспрессо", 120, Strength.LIGHT);
        Tea green = new Tea("Зелёный чай", 90, Type.GREEN);
        Pastry bun = new Pastry("Булочка с корицей", 80, Bakery.BUN);

        shop.addMenuItem(espresso);
        shop.addMenuItem(green);
        shop.addMenuItem(bun);

        CoffeeShop.Order order = shop.createOrder();

        order.addItem(espresso, Size.SMALL);
        order.addItem(green, Size.BIG);
        order.addItem(bun, Size.MEDIUM);

        shop.placeOrder(order);

        System.out.println("Статистика:");
        System.out.println("Orders: " + CoffeeShopStats.getTotalOrders());
        System.out.println("Items: " + CoffeeShopStats.getTotalItems());
        System.out.println("Revenue: " + CoffeeShopStats.getTotalRevenue());
    }
}