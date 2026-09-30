import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoffeeShop {

    private final List<MenuItem> menu = new ArrayList<>();

    public void addMenuItem(MenuItem item) {
        if (item == null) {
            throw new IllegalArgumentException("MenuItem is null");
        }
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return Collections.unmodifiableList(menu);
    }

    public Order createOrder() {
        return new Order();
    }

    public double placeOrder(Order order) throws InterruptedException {
        if (order == null) {
            throw new IllegalArgumentException("Order is null");
        }
        if (order.lines.isEmpty()) {
            System.out.println("Заказ пуст.");
            return 0.0;
        }

        System.out.println("Заказ принят. Позиций: " + order.lines.size());

        double total = 0.0;

        for (Order.OrderLine line : order.lines) {
            MenuItem item = line.item;

            // готовим только напитки через Preparable (как по условию)
            if (item instanceof Preparable preparable) {
                preparable.prepare();
            } else if (item instanceof Pastry pastry) {
                // у тебя у Pastry есть prepare() — можно так оставить
                pastry.prepare();
            } else {
                System.out.println("Не умею готовить: " + item.getName());
            }

            total += line.getFinalPrice();
        }

        System.out.println("Заказ готов. Сумма: " + total);

        CoffeeShopStats.onOrderClosed(order.lines.size(), total);

        return total;
    }

    public class Order {
        private final List<OrderLine> lines = new ArrayList<>();

        public void addItem(MenuItem item, Size size) {
            if (item == null) throw new IllegalArgumentException("item is null");
            if (size == null) throw new IllegalArgumentException("size is null");

            if (!menu.contains(item)) {
                throw new IllegalArgumentException("Такого товара нет в меню: " + item.getName());
            }

            lines.add(new OrderLine(item, size));
        }

        public List<OrderLine> getLines() {
            return Collections.unmodifiableList(lines);
        }

        public double getTotal() {
            double sum = 0.0;
            for (OrderLine line : lines) {
                sum += line.getFinalPrice();
            }
            return sum;
        }

        public class OrderLine {
            private final MenuItem item;
            private final Size size;

            private OrderLine(MenuItem item, Size size) {
                this.item = item;
                this.size = size;
            }

            public MenuItem getItem() {
                return item;
            }

            public Size getSize() {
                return size;
            }

            public double getFinalPrice() {
                if (item instanceof Coffee || item instanceof Tea) {
                    return item.getPrice() * sizeMultiplier(size);
                }
                return item.getPrice();
            }

            //для размерности
            private double sizeMultiplier(Size size) {
                return switch (size) {
                    case SMALL -> 1.0;
                    case MEDIUM -> 1.2;
                    case BIG -> 1.4;
                };
            }
        }
    }
}