public class ThermostaticWaterTank {
    private TemperatureSensor temperatureSensor;
    private WaterLevelSensor waterLevelSensor;
    private Relay relay;
    private boolean isTankDamaged;
    private boolean isPowerOn;

    public ThermostaticWaterTank() {
        this.temperatureSensor = new TemperatureSensor();
        this.waterLevelSensor = new WaterLevelSensor();
        this.relay = new Relay();
        this.isTankDamaged = false;
        this.isPowerOn = true;
    }

    public void startHeatingProcess() {
        while (isPowerOn) {
            int currentTemperature = temperatureSensor.getCurrentTemperature();
            
            if (currentTemperature < 20) {
                if (waterLevelSensor.isWaterAvailable()) {
                    relay.turnOn();
                    System.out.println("开始烧水");
                } else {
                    System.out.println("水箱中无水，不打开电源烧水");
                }
            } else if (currentTemperature >= 100) {
                relay.turnOff();
                System.out.println("水温达到100°，断开电源");
            }

            if (isTankDamaged) {
                repairTank();
            }

            if (isBedTime()) {
                turnOffPower();
            }

            if (isWakeUpTime()) {
                turnOnPower();
            }
        }
    }

    public void repairTank() {
        System.out.println("水箱烧坏，进行维修");
        // 进行维修操作
        isTankDamaged = false;
    }

    public void turnOffPower() {
        System.out.println("晚上11点整，自动断开电源，进入休眠状态");
        isPowerOn = false;
    }

    public void turnOnPower() {
        System.out.println("早上7点整，自动进入恒温烧水过程");
        isPowerOn = true;
    }
}

class TemperatureSensor {
    public int getCurrentTemperature() {
        // 获取当前温度
        return 0;
    }
}

class WaterLevelSensor {
    public boolean isWaterAvailable() {
        // 检测水箱是否有水
        return true;
    }
}

class Relay {
    public void turnOn() {
        // 打开继电器电源
    }

    public void turnOff() {
        // 关闭继电器电源
    }
}
