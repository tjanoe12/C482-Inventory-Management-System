package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.*;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.Product;
import java.util.Optional;
import static Model.Inventory.getAllParts;


public class AddProduct implements Initializable {
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private Alert alert;
   private Product product;
    Stage stage;
   Parent scene;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private int productId = Inventory.getAllProducts().get(Inventory.getAllProducts().size() - 1).getId() + 1;
    @FXML
    private TextField productIdTxt;
    @FXML
    private TextField ProductNameTxt;
    @FXML
    private TextField ProductInvTxt;
    @FXML
    private TextField ProductPriceTxt;
    @FXML
    private TextField ProductMaxTxt;
    @FXML
    private TextField ProductMinTxt;
    @FXML
    private TextField partSearchTxt;
    @FXML
    private TableView<Part> addPartTbl;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInvCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableView<Part> ascPartTTbl;
    @FXML
    private TableColumn<Part, Integer> ascPartIDCol;
    @FXML
    private TableColumn<Part, String> ascPartNameCol;
    @FXML
    private TableColumn<Part, Integer> ascPartInvCol;
    @FXML
    private TableColumn<Part, Double> ascPartPriceCol;

    @FXML
    void OnActionAddPart(ActionEvent event) throws IOException {
//todo Adds part to all products, needs to be only one Product
        Part addPart = addPartTbl.getSelectionModel().getSelectedItem();
        if (addPart != null) {
            associatedPart.add(addPart);
            ascPartTTbl.setItems(associatedPart);
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product modification error!");
            alert.setContentText("No part selected");
            alert.show();
        }
    }


    @FXML
    void OnActionCancelAddProduct(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Cancel Adding New Product?");
        alert.setContentText("Are you sure you want to cancel adding the new product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }

    }

    @FXML
    void OnActionDeleteProduct(ActionEvent event) {
        Part part = ascPartTTbl.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Removing part #" + part.getPartID() + " - \"" + part.getName() + "\"");
        alert.setContentText("Are you sure you want to remove the part from this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            associatedPart.remove(part);
            ascPartTTbl.setItems(associatedPart);
        }
    }

    @FXML
    void OnActionSaveProductAdd(ActionEvent event) throws IOException {



        int id = Integer.parseInt(productIdTxt.getText());
        String name = ProductNameTxt.getText();
        int inv = Integer.parseInt(ProductInvTxt.getText());
        Double price = Double.parseDouble(ProductPriceTxt.getText());
        int min = Integer.parseInt(ProductMinTxt.getText());
        int max = Integer.parseInt(ProductMaxTxt.getText());

        if (ascPartTTbl.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Product has no parts");
            alert.setContentText("Product must have at least one associated part.");
            alert.showAndWait();

        }


         else {
            Product newProduct = new Product(id, name, inv, price, min, max);
            newProduct.setId(id);
            newProduct.setName(name);
            newProduct.setInv(inv);
            newProduct.setPrice(price);
            newProduct.setMin(min);
            newProduct.setMax(max);
            newProduct.associatedPart = ascPartTTbl.getItems();
            Inventory.addProduct(newProduct);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();


    }

}


    @FXML
    void OnActionSearchProduct(ActionEvent event) {
        String partText = partSearchTxt.getText();
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
        addPartTbl.setItems(sp);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Parts Table
        addPartTbl.setItems(getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("inv"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ascPartIDCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPartID()).asObject());
        ascPartNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        ascPartInvCol.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getInv()).asObject());
        ascPartPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

    }
}
