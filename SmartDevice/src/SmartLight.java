public class SmartLight extends SmartDevice implements Controllable {

    private int brightness;

    public SmartLight(String name, RoomType room) {
        super(name, room);
        this.brightness = 0;
    }

    public void setBrightness(int brightness){
        this.brightness = brightness;
    }

    public int getBrightness(){
        return this.brightness;
    }

    @Override
    public String isActive(){
        return isOn ? "статус: Устройство включено - яркость: " + this.brightness : "статус: Устройство выключено";
    }

    @Override
    public void increaseValue(int value){
        this.brightness = Math.max(0, Math.min(100, this.brightness + value));
    }

    @Override
    public void decreaseValue(int value){
        this.brightness = Math.min(100, Math.max(0, this.brightness - value));
    }
}
