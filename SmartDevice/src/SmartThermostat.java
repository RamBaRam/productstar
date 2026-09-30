public class SmartThermostat extends SmartDevice implements Controllable {

    private int temperature;

    public  SmartThermostat(String name, RoomType room) {
        super(name, room);
        this.temperature = 0;
    }

    public void setTemperature(int temperature){
        this.temperature = temperature;
    }

    public int getTemperature(){
        return this.temperature;
    }

    @Override
    public String isActive(){
        return isOn ? "статус: Устройство включено - температура: " + this.temperature : "статус: Устройство выключено";
    }

    @Override
    public void increaseValue(int value){
        this.temperature = Math.max(0, Math.min(100, this.temperature + value));
    }

    @Override
    public void decreaseValue(int value){
        this.temperature = Math.min(100, Math.max(0, this.temperature - value));
    }
}
