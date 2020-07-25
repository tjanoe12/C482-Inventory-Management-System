package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import Model.*;
import com.sun.tools.internal.xjc.model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static Model.Inventory.*;

public class ModifyProduct implements Initializable {

    
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();
    Stage stage;
    Parent scene;
    private Product newModifyProduct;
    int newModifyProductIndex;

    @FXML private TextField searchPartsTxt;
    @FXML private TextField ModProdID;
    @FXML private TextField ModProdNameTxt;
    @FXML private TextField ModProdInvTxt;
    @FXML private TextField ModProdPriceTxt;
    @FXML private TextField ModProdMaxTxt;
    @FXML private TextField ModProdMinTxt;

    @FXML private TableView<Part> allPartsTable;
    @FXML private TableColumn<Part, Integer> allPartsCol_ID;
    @FXML private TableColumn<Part, String> allPartsCol_Name;
    @FXML private TableColumn<Part, Integer> allPartsCol_Inv;
    @FXML private TableColumn<Part, Double> allPartsCol_Price;

    @FXML private TableView<Part> associatedPartsTable;
    @FXML private TableColumn<Part, Integer> associatedPartsCol_ID;
    @FXML private TableColumn<Part, String> associatedPartsCol_Name;
    @FXML private TableColumn<Part, Integer> associatedPartsCol_Inv;
    @FXML private TableColumn<Part, Double> associatedPartsCol_Price;

    @FXML
    public void sendProduct(Product modifyProduct)
    {
        newModifyProduct = modifyProduct;
        ModProdID.setText(String.valueOf(newModifyProduct.getId()));
        ModProdNameTxt.setText(newModifyProduct.getName());
        ModProdInvTxt.setText(String.valueOf(newModifyProduct.getInv()));
        ModProdPriceTxt.setText(String.valueOf(newModifyProduct.getPrice()));
        ModProdMinTxt.setText(String.valueOf(newModifyProduct.getMin()));
        ModProdMaxTxt.setText(String.valueOf(newModifyProduct.getMax()));

        for(Part part: newModifyProduct.getAllAssociatedParts())
        {
            associatedPart.add(part);
        }
        associatedPartsTable.setItems(newModifyProduct.associatedPart);
    }


    @FXML void OnActionAddPart(ActionEvent event)
    {
        boolean repeat = false;

        Part newPartToAdd = allPartsTable.getSelectionModel().getSelectedItem();
        for(int index = 0; index < newModifyProduct.getAllAssociatedParts().size(); index++)
        {
            if(newModifyProduct.getAllAssociatedParts().contains(newPartToAdd))
            {
                repeat = true;
            }
        }

        if(repeat == true)
        {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("ERROR");
            newAlert.setHeaderText("Part Already Added!");
            newAlert.setContentText(newModifyProduct.getName() + " already contains " + newPartToAdd.getName() + "!");
            newAlert.showAndWait();
        }
        else
        {
            newModifyProduct.associatedPart.add(newPartToAdd);
            associatedPartsTable.setItems(newModifyProduct.associatedPart);
        }

    }


    @FXML
    void OnActionCancelAddProduct(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Cancel Modifying Product?");
        alert.setContentText("Are you sure you want to cancel modifying the product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }

    }

    @FXML void OnActionDeleteProduct(ActionEvent event)
    //TODO deletes part and repopulates table with all parts data, but doesn't save

    {  Part part = associatedPartsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Removing part #" + part.getPartID() + " - \"" + part.getName() + "\"");
        alert.setContentText("Are you sure you want to remove the part from this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            newModifyProduct.associatedPart.remove(part);
            associatedPartsTable.setItems(newModifyProduct.associatedPart);
        }
    }

    @FXML
    void OnActionSaveProductAdd (ActionEvent event) throws IOException
    {
        if (newModifyProduct.getAllAssociatedParts().size() > 0) {

            int id = Integer.parseInt(ModProdID.getText());
            String name = ModProdNameTxt.getText();
            int inv = Integer.parseInt(ModProdInvTxt.getText());
            Double price = Double.parseDouble(ModProdPriceTxt.getText());
            int min = Integer.parseInt(ModProdMinTxt.getText());
            int max = Integer.parseInt(ModProdMaxTxt.getText());

            Product modProduct = new Product(id, name, inv, price, min, max);

            modProduct.setId(id);
            modProduct.setName(name);
            modProduct.setInv(inv);
            modProduct.setPrice(price);
            modProduct.setMin(min);
            modProduct.setMax(max);
            //TODO save addPart
            modProduct.associatedPart = associatedPartsTable.getItems();

            for (Product allProducts : Inventory.getAllProducts())
            {
                newModifyProductIndex = Inventory.getAllProducts().indexOf(newModifyProduct);
            }
            Inventory.updateProduct(modProduct, newModifyProductIndex);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
    }

        else  {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Product has no parts");
            alert.setContentText("Product must have at least one associated part.");
            alert.showAndWait();
        }
    }

    public void OnActionSearchProduct(ActionEvent event)
    {
        String partText = searchPartsTxt.getText();
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
        allPartsTable.setItems(sp);
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {


        //All Parts Table
        allPartsTable.setItems(getAllParts());
        allPartsCol_ID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        allPartsCol_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsCol_Inv.setCellValueFactory(new PropertyValueFactory<>("inv"));
        allPartsCol_Price.setCellValueFactory(new PropertyValueFactory<>("price"));


        //Associated Parts Table
        //TODO populate associated parts properly
        associatedPartsCol_ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPartID()).asObject());
        associatedPartsCol_Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        associatedPartsCol_Inv.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getInv()).asObject());
        associatedPartsCol_Price.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        //associatedPartsTable.setItems(selectedProduct.associatedPart);
    }
    public void setProduct(Product product) {
    }
}
