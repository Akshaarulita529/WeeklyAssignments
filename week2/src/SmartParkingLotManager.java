
import java.util.*;

class SmartParkingLotManager {

    class Spot {
        String license;
        boolean occupied;
        Spot(){ this.license=null; this.occupied=false;}
    }

    Spot[] spots;
    int size;

    SmartParkingLotManager(int size) {
        this.size = size;
        spots = new Spot[size];
        for(int i=0;i<size;i++) spots[i]=new Spot();
    }

    int hash(String license) {
        return Math.abs(license.hashCode()) % size;
    }

    void parkVehicle(String license) {
        int idx = hash(license);
        int probes = 0;
        while(spots[idx].occupied) {
            idx = (idx+1)%size;
            probes++;
        }
        spots[idx].license = license;
        spots[idx].occupied = true;
        System.out.println("Vehicle " + license + " parked at spot #" + idx + " (" + probes + " probes)");
    }

    void exitVehicle(String license) {
        for(int i=0;i<size;i++) {
            if(spots[i].occupied && spots[i].license.equals(license)) {
                spots[i].occupied=false;
                spots[i].license=null;
                System.out.println("Vehicle " + license + " exited spot #" + i);
                return;
            }
        }
        System.out.println("Vehicle not found: " + license);
    }

    public static void main(String[] args) {
        SmartParkingLotManager lot = new SmartParkingLotManager(500);
        lot.parkVehicle("ABC1234");
        lot.parkVehicle("ABC1235");
        lot.parkVehicle("XYZ9999");
        lot.exitVehicle("ABC1234");
    }
}