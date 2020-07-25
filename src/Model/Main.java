package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 996, 411));
        primaryStage.show();
    }

    public static void main(String[] args) {

        InHouse inHouse1 = new InHouse(1, "monitor", 15, 151.11, 0, 99, 4);
        InHouse inHouse2 = new InHouse(2, "hard drive", 23, 23.23, 0, 99, 3);
        InHouse inHouse3 = new InHouse(3, "keyboard", 1, 32.77, 0, 99, 2);
        InHouse inHouse4 = new InHouse(4, "mouse", 9, 45.34, 0, 99, 1);
        Inventory.addPart(inHouse1);
        Inventory.addPart(inHouse2);
        Inventory.addPart(inHouse3);
        Inventory.addPart(inHouse4);

        Outsourced outsourced1 = new Outsourced(5, "cpu", 13, 13.19, 0, 99, "TechDeal");
        Outsourced outsourced2 = new Outsourced(6, "ethernet", 78, 78.23, 0, 99, "TechDeal");
        Outsourced outsourced3 = new Outsourced(7, "display port", 43, 43.99, 0, 99, "TechDeal");
        Outsourced outsourced4 = new Outsourced(8, "keypad", 7, 7.99, 0, 99, "TechDeal");
        Inventory.addPart(outsourced1);
        Inventory.addPart(outsourced2);
        Inventory.addPart(outsourced3);
        Inventory.addPart(outsourced4);

        Product product1 = new Product(1, "coding computer", 499.99, 8, 0, 99);
        Product product2 = new Product(2, "gaming computer", 615.79, 4, 0, 99);
        Product product3 = new Product(3, "business computer", 389.99, 21, 0, 99);
        Product product4 = new Product(4, "home computer", 199.99, 28, 0, 99);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        launch(args);
    }
}
