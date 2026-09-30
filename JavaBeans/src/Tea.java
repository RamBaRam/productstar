import static java.lang.Thread.sleep;

public class Tea extends MenuItem implements Preparable{

    private Type type;

    public Tea(String name, int price, Type type) {
        super(name,price);
        this.type = type;
    }

    public void setPrice(int price){
        this.price=price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void prepare() throws InterruptedException {
        System.out.println("Позиция: Чай готовится...");
        sleep(50);
        System.out.println("Чай: " + this.getName() + " готов !");
    }
}
