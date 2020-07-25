package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Node;

import static Model.Inventory.*;

public class MainMenu implements Initializable {


    public GridPane root;

    @FXML private Button exitButton;
    @FXML private Button searchPartButton;
    @FXML private Button searchProductButton;
    @FXML private TextField searchPartField;
    @FXML private TextField searchProductField;

    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partsIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partsQuantity;
    @FXML private TableColumn<Part, Double> priceColumn;

    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> productID;
    @FXML private TableColumn<Product, String> productName;
    @FXML private TableColumn<Product, Integer> productQuantity;
    @FXML private TableColumn<Product, Double> productPrice;

    private Product product;
    private Part part;
    public static int currentIndex;
    public Product selectedProduct;
    public Part selectedPart;
    private static int selectedPartIndex;
    private static int selectedProductIndex;
    public static  ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static  ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static int getSelectedProductIndex() { return selectedProductIndex; }
    public static int getSelectedPartIndex() { return selectedPartIndex; }

//Add Part or Product
    @FXML
    public void sceneAddPart (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AddPart.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void sceneAddProduct (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AddPart.fxml"));
        loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

//Modify Part or Product
    @FXML
    public void sceneModifyPart (ActionEvent event) throws IOException {
        Part modifyPart = partsTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ModifyPart.fxml"));
        Parent parent = loader.load();
        Scene modifyPartScene = new Scene(parent);
        ModifyPart PartController = loader.getController();
        PartController.setPart(modifyPart);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyPartScene);
        window.setResizable(false);
        window.show();
    }

    @FXML
    public void sceneModifyProduct (ActionEvent event) throws IOException {

            Product modifyProduct = productsTable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyProduct.fxml"));
            Parent parent = loader.load();
            Scene modifyProductScene = new Scene(parent);
            ModifyProduct controller = loader.getController();
            controller.sendProduct(modifyProduct);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyProductScene);
        window.setResizable(false);
        window.show();


    }
//Search Part or Product
    @FXML
    public void searchParts(ActionEvent event) {
            String partText = searchPartField.getText();
            ObservableList<Part> sp = Inventory.lookupPart(partText);

            //partID ver
            if (sp.isEmpty()) {
                try {
                    int id = Integer.parseInt(partText);
                    Part search = Inventory.lookupPart(id);

                    if (search != null) {
                        sp.add(search);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Try to convert to ID");
                }
            }
            partsTable.setItems(sp);
        }
    @FXML
    public void searchProducts(ActionEvent event){
        String pdText = searchProductField.getText();
        ObservableList<Product> sd = Inventory.lookupProduct(pdText);

        //ver prodID
        if (sd.isEmpty()) {
            try {
                int id = Integer.parseInt(pdText);
                Product search = Inventory.lookupProduct(id);

                if (search != null) {
                    sd.add(search);
                }
            } catch (NumberFormatException e) {
                System.out.println("Try to convert to ID");
            }
        }
        productsTable.setItems(sd);
    }

//Delete

    public void updatePartsTable()
    {
        partsTable.setItems(getAllParts());
    }

    public void updateProductsTable() {
        productsTable.setItems(getAllProducts());
    }

        @FXML
        private void handleDeletePart (ActionEvent event) throws IOException {
            {
                Part part = partsTable.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Removing part #" + part.getPartID() + " - \"" + part.getName() + "\"");
                alert.setContentText("Are you sure you want to remove the part");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Inventory.deletePart(partsTable.getSelectionModel().getSelectedItem());
                    updatePartsTable();
                }
            }
        }

        @FXML
        private void handleDeleteProduct (ActionEvent event) {
            {
                Product product = productsTable.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Removing product #" + product.getId() + " - \"" + product.getName() + "\"");
                alert.setContentText("Are you sure you want to remove the Product");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Inventory.deleteProduct(productsTable.getSelectionModel().getSelectedItem());
                    updateProductsTable();
                }
            }
        }


        //EXIT
    @FXML
    void exitApp(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        exitButton.setText(sourceButton.getText());
            System.exit(0);
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Parts Table
        partsTable.setItems(Inventory.getAllParts());
        partsIdColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsQuantity.setCellValueFactory(new PropertyValueFactory<>("inv"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Inventory.getAllParts());

        //Products Table
        productsTable.setItems(getAllProducts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productQuantity.setCellValueFactory(new PropertyValueFactory<>("inv"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.setItems(Inventory.getAllProducts());

        }
}
