package Model;

public class InHouse extends Part {
    private static int machineId;

    //Constructor
    public InHouse(int partID, String name, int inv, double price, int min, int max, int machineId) {
        super(partID, name, price, inv, min, max);
        this.machineId = machineId;
    }

    //Getters and Setters
    public static int getMachineID() {
        return machineId;
    }

    public void setMachineID(int machineID) {
        this.machineId = machineID;
    }
}