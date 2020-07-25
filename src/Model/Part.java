//abstract class can't be instantiated//

package Model;

public abstract class Part {


    private int partID;
    private String name;

    public Part(int partID, String name, double price, int inv, int min, int max) {
        this.partID = partID;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

    private double price;
    private int inv;
    private int min;
    private int max;

    public int getPartID()
    {
        return partID;
    }

    public void setID(int partID)
    {
        this.partID = partID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

}