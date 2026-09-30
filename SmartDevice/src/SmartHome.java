import java.util.ArrayList;

public class SmartHome {

    private final ArrayList<SmartDevice> devices;
    private final HomeStats stats;

    public SmartHome(){
        this.devices = new ArrayList<>();
        this.stats = new HomeStats();
    }

    public void addDevice(SmartDevice device){
        devices.add(device);
        stats.totalDevices = devices.size();
    }

    public void turnOnAllDevices(){
        stats.totalStatusONdevices = 0;
        for (SmartDevice device : devices){
            device.turnOn();
            stats.totalAdjustements++;
            stats.totalStatusONdevices++;
        }
        stats.totalStatusOFFdevices = 0;
        System.out.println("Все девайсы включены !");
    }

    public void turnOffAllDevices(){
        stats.totalStatusOFFdevices = 0;
        for (SmartDevice device : devices){
            device.turnOff();
            stats.totalAdjustements++;
            stats.totalStatusOFFdevices++;
        }
        stats.totalStatusONdevices = 0;
        System.out.println("Все девайсы выключены !");
    }

    public void increaseValueOfTemperature(int temperature){
        for (SmartDevice device : devices){
            if(device instanceof SmartThermostat thermostat){
                thermostat.increaseValue(temperature);
                stats.totalAdjustements++;
                System.out.println("Термостат: " + device.getName() + "Температура повышена ! Текущая температура: " + thermostat.getTemperature());
            }
        }
    }

    public void decreaseValueOfTemperature(int temperature){
        for (SmartDevice device : devices){
            if(device instanceof SmartThermostat thermostat){
                thermostat.decreaseValue(temperature);
                stats.totalAdjustements++;
                System.out.println("Термостат: " + device.getName() + "Температура понижена ! Текущая температура: " + thermostat.getTemperature());
            }
        }
    }

    public void increaseVolumeOfSmartTV(int volume){
        for (SmartDevice device : devices){
            if(device instanceof SmartTV tv){
                tv.increaseValue(volume);
                stats.totalAdjustements++;
                System.out.println("Телевизор: " + device.getName() + "Громкость повышена ! Текущая громкость: " + tv.getVolume());
            }
        }
    }

    public void decreaseVolumeOfSmartTV(int volume){
        for (SmartDevice device : devices){
            if(device instanceof SmartTV tv){
                tv.decreaseValue(volume);
                stats.totalAdjustements++;
                System.out.println("Телевизор: " + device.getName() + "Громкость понижена ! Текущая громкость: " + tv.getVolume());
            }
        }
    }

    public void increaseValueOfSmartLight(int value){
        for (SmartDevice device : devices){
            if(device instanceof SmartLight light){
                light.increaseValue(value);
                stats.totalAdjustements++;
                System.out.println("Лампа: " + device.getName() + "Яркость повышена ! Текущая яркость: " + light.getBrightness());
            }
        }
    }

    public void decreaseValueOfSmartLight(int value){
        for (SmartDevice device : devices){
            if(device instanceof SmartLight light){
                light.decreaseValue(value);
                stats.totalAdjustements++;
                System.out.println("Лампа: " + device.getName() + "Яркость понижена ! Текущая яркость: " + light.getBrightness());
            }
        }
    }

    public void turnOnLight(){
        for (SmartDevice device : devices){
            if(device instanceof SmartLight light && (!light.isOn)){
                light.turnOn();
                stats.totalStatusONdevices++;
                stats.totalStatusOFFdevices--;
                stats.totalAdjustements++;
                System.out.println("Лампа включена ! - " + device.isActive());
            }
        }
    }

    public void turnOffLight(){
        for (SmartDevice device : devices){
            if(device instanceof SmartLight light && (light.isOn)){
                light.turnOff();
                stats.totalStatusONdevices--;
                stats.totalStatusOFFdevices++;
                stats.totalAdjustements++;
                System.out.println("Лампа выключена ! - " + device.isActive());
            }
        }
    }

    public void turnOnSmartTV(){
        for (SmartDevice device : devices){
            if(device instanceof SmartTV tv && (!tv.isOn)){
                tv.turnOn();
                stats.totalStatusONdevices++;
                stats.totalStatusOFFdevices--;
                stats.totalAdjustements++;
                System.out.println("Телевизор включен ! - " + device.isActive());
            }
        }
    }

    public void turnOffSmartTV(){
        for (SmartDevice device : devices){
            if(device instanceof SmartTV tv && (tv.isOn)){
                tv.turnOff();
                stats.totalStatusONdevices--;
                stats.totalStatusOFFdevices++;
                stats.totalAdjustements++;
                System.out.println("Телевизор выключен ! - " + device.isActive());
            }
        }
    }

    public void turnOnThermostat(){
        for (SmartDevice device : devices){
            if(device instanceof SmartThermostat thermostat && (!thermostat.isOn)){
                thermostat.turnOn();
                stats.totalStatusONdevices++;
                stats.totalStatusOFFdevices--;
                stats.totalAdjustements++;
                System.out.println("Термостат включен ! - " + device.isActive());
            }
        }
    }

    public void turnOffThermostat(){
        for (SmartDevice device : devices){
            if(device instanceof SmartThermostat thermostat && (thermostat.isOn)){
                thermostat.turnOff();
                stats.totalStatusONdevices--;
                stats.totalStatusOFFdevices++;
                stats.totalAdjustements++;
                System.out.println("Термостат выключен ! - " + device.isActive());
            }
        }
    }

    public HomeStats getStats() {
        return stats;
    }

    private static class HomeStats{
        private int totalDevices;
        private int totalStatusONdevices;
        private ArrayList<SmartDevice> OnDevices;
        private int totalStatusOFFdevices;
        private ArrayList<SmartDevice> OffDevices;
        private int totalAdjustements;

        public int getTotalDevices() {
            return this.totalDevices;
        }

        public int getTotalStatusOnDevices() {
            return this.totalStatusONdevices;
        }

        public int getTotalStatusOffDevices() {
            return this.totalStatusOFFdevices;
        }

        public int getTotalAdjustments() {
            return this.totalAdjustements;
        }

        @Override
        public String toString(){
            return "[" + "\n" +
                    "Всего устройств: " +  this.totalDevices + "\n" +
                    "Устройств включено: " +  this.totalStatusONdevices + "\n" +
                    "Устройств выключено: " +  this.totalStatusOFFdevices + "\n" +
                    "Действий с устройствами: " + this.totalAdjustements + "\n" +
                    "]";
        }

    }

}
