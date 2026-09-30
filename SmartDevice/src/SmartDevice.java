public abstract class SmartDevice {
    protected final String name;
    protected boolean isOn;
    protected final RoomType room;

    public SmartDevice(String name, RoomType room) {
        this.name = name;
        this.room = room;
        this.isOn = false;
    }

    public final void turnOn(){ this.isOn = true; }
    public final void turnOff(){this.isOn = false; }
    public String getName(){ return this.name; }
    public boolean isOn(){ return this.isOn; }
    public RoomType getRoom(){ return this.room; }
    public abstract String isActive();

}
