package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static Part selectedPart;
    public static Product selectedProduct;



    //-------Methods----------

    //Add
    public static void addPart(Part newPart)
    {
        allParts.add(newPart);
    }
    public static void addProduct(Product newProduct) { allProducts.add(newProduct); }

    //Lookup

    public static Part lookupPart (int partID) {
        for (Part part : allParts) {
            if (part.getPartID() == partID) {
                return part;
            }
        }
        return null;
    }

    public static ObservableList <Part> lookupPart (String partName) {
        ObservableList<Part> partsName = FXCollections.observableArrayList();

        for (Part prt : allParts) {
            if (prt.getName().contains(partName)) {
                partsName.add(prt);
            }
        }
        return partsName;
    }

    public static Product lookupProduct (int productID) {
        for (Product product : allProducts) {
            if (product.getId() == productID) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList <Product> lookupProduct (String productName) {
        ObservableList<Product> productsName = FXCollections.observableArrayList();

        for (Product pd : allProducts) {
            if (pd.getName().contains(productName)) {
                productsName.add(pd);
            }
        }
        return productsName;
    }

    //Update
    public static void updatePart(Integer index, Part selectPart) {
       // allParts.set(index, selectPart);
    }
    public static void updatePart(Part selectedPart, int index)
    {

        allParts.set(index, selectedPart);
    }
    public static void updateProduct(Product selectedProduct, int index) {
        allProducts.set(index, selectedProduct);
    }

    //Delete
    public static boolean deletePart (Part selectedPart) {
         allParts.remove(selectedPart);
         return true;
    }


    public static boolean deleteProduct (Product selectedProduct) {
        allProducts.remove(selectedProduct);
        return true;
    }

    //Getters

    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }

}