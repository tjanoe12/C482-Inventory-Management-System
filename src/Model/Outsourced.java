package Model;

public class Outsourced extends Part {
    private static String companyName;

    //Constructor
    public Outsourced(int partID, String name,int inv, double price, int min, int max, String companyName) {
        super(partID, name, price, inv, min, max);
        this.companyName = companyName;
    }
    //Getters and Setters
    public static String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


}