public final class CoffeeShopStats {
    private static int totalOrders = 0;
    private static int totalItems = 0;
    private static double totalRevenue = 0.0;

    private CoffeeShopStats() {}

    public static void onOrderClosed(int itemsCount, double orderSum) {
        totalOrders++;
        totalItems += itemsCount;
        totalRevenue += orderSum;
    }

    public static int getTotalOrders() {
        return totalOrders;
    }

    public static int getTotalItems() {
        return totalItems;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }
}