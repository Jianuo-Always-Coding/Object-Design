package Object_Oriented_Design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//  先定义车 - single file
public enum VehicleSize {
    Compact(1),
    Large(3),
    Regualr(2);

    private final int size;

    VehicleSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

// single file
public abstract class Vehicle {
    public abstract VehicleSize getSize();
}

// single file
public class Car extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Compact;
    }
}

// single file
public class Truck extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Large;
    }
}

// 定义停车场，从一个车位到整体
class ParkingSpot {  // 一个车位
    private final VehicleSize size;
    private Vehicle currentVehicle; // if is null -> no vehicle

    ParkingSpot(VehicleSize size) {
        this.size = size;
    }

    boolean fit(Vehicle v) {
        return currentVehicle == null && size.getSize() >= v.getSize().getSize();
    }

    boolean park(Vehicle v) {
        if (fit(v)) {
            currentVehicle = v;
            return true;
        }
        return false;
    }

    void leave() {
        currentVehicle = null;
    }

    Vehicle getVehicle() {
        return currentVehicle;
    }
}

class Level { // 停车场的一层
    private final List<ParkingSpot> spots;

    Level(int numSpots) {
        List<ParkingSpot> list = new ArrayList<ParkingSpot>(numSpots);
        int i = 0;
        while (i < numSpots / 2) {
            list.add(new ParkingSpot(VehicleSize.Compact));
            i++;
        }

        while (i < numSpots) {
            list.add(new ParkingSpot(VehicleSize.Large));
            i++;
        }
        // unmodifiablelist returns a list that is read- only, protect from changing the value
        // why not use final? final means the reference cannot be change, not the value itself.
        spots = Collections.unmodifiableList(list);
    }

    // 可以优化
    boolean hasSpot(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) {
                return true;
            }
        }
        return false;
    }

    boolean park(Vehicle v) {
        for (ParkingSpot s : spots) {
            return s.park(v);
        }
        return false;
    }

    boolean leave(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.getVehicle() == v) {
                s.leave();
                return true;
            }
        }
        return false;
    }
}


public class ParkingLot {
    private final Level[] levels;

    public ParkingLot(int numLevels, int numSpotsPerLevel) {
        levels = new Level[numLevels];
        for (int i = 0; i < numLevels; i++) {
            levels[i] = new Level(numSpotsPerLevel);
        }
    }

    public boolean hasSpot(Vehicle v) {
        for (Level l : levels) {
            if (l.hasSpot(v)) {
                return true;
            }
        }
        return false;
    }

    public boolean park(Vehicle v) {
        for ( Level l : levels) {
            if (l.park(v)) {
                return true;
            }
        }
        return false;
    }

    public boolean leave(Vehicle v) {
        for (Level l : levels) {
            if (l.leave(v)) {
                return true;
            }
        }
        return false;
    }
}



class Test {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(4, 10);
        List<Vehicle> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            final Vehicle v = (i % 2 == 0 ? new Car() : new Truck());
            list.add(v);
            boolean hasSpot = lot.hasSpot(v);
            if (i < 40) {
                assert hasSpot; // 如果是true 程序正常执行，不是，会报错
                assert lot.park(v);
            } else {
                assert !hasSpot; // 如果是true 程序正常执行，不是，会报错
                assert !lot.park(v);
            }
        }
        assert list.size() == 50;
        int i = 0;
        for (Vehicle v : list) {
            assert i >= 40 || lot.leave(v);
            i++;
        }
    }
}