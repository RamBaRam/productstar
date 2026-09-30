import static java.lang.Thread.sleep;

public class Coffee extends MenuItem implements Preparable {

    private Strength strength;

    public Coffee(String name, int price, Strength strength) {
        super(name, price);
        this.strength = strength;
    }

    public int setPrice(int price) {
        return price;
    }

    public void setStrength(Strength strength) {
        this.strength = strength;
    }

    public Strength getStrength() {
        return strength;
    }

    @Override
    public void prepare() throws InterruptedException {
        System.out.println("Позиция: Кофе готовится...");
        sleep(70);
        System.out.println("Кофе: " + this.getName() + " готов !");
    }
}
