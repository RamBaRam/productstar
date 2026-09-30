public class Main {
    public static void main(String[] args) {

        SmartHome smartHome = new SmartHome();

        smartHome.addDevice(new SmartLight("Yandex Light", RoomType.BEDROOM));
        smartHome.addDevice(new SmartTV("Samsung", RoomType.HALL));
        smartHome.addDevice(new SmartThermostat("Sony", RoomType.KITCHEN));

        smartHome.turnOnAllDevices();
        smartHome.turnOffThermostat();
        smartHome.turnOffSmartTV();
        smartHome.turnOffLight();

        smartHome.turnOnSmartTV();
        smartHome.increaseVolumeOfSmartTV(50);

        smartHome.turnOnLight();
        smartHome.increaseValueOfSmartLight(20);

        smartHome.turnOnThermostat();
        smartHome.increaseValueOfTemperature(18);

        System.out.println(smartHome.getStats());

    }
}