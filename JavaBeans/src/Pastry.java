import static java.lang.Thread.sleep;

public class Pastry extends MenuItem{

    private Bakery bakery;

    public Pastry(String name, int price, Bakery bakery) {
        super(name, price);
        this.bakery = bakery;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Bakery getBakery() {
        return bakery;
    }

    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }

    public void prepare() throws InterruptedException {
        System.out.println("Позиция: Булочка готовится...");
        sleep(150);
        System.out.println("Булочка: " + this.getName() + " готова !");
    }
}
