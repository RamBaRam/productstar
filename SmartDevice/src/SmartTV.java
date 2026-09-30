public class SmartTV extends SmartDevice implements Controllable {

    private int volume;

    public SmartTV(String name, RoomType room) {
        super(name, room);
        this.volume = 0;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String isActive() {
        return isOn ? "статус: Устройство включено - громкость: " + this.volume : "статус: Устройство выключено";
    }

    @Override
    public void increaseValue(int value){
        this.volume = Math.max(0, Math.min(100, this.volume + value));
    }

    @Override
    public void decreaseValue(int value){
        this.volume = Math.min(100, Math.max(0, this.volume - value));
    }
}
