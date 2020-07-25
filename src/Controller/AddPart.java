package Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Model.*;
import java.lang.reflect.ParameterizedType;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddPart implements Initializable {

    Stage stage;
    Parent scene;
    private String partType;
    private int id;

    @FXML
    private RadioButton InHouseRBtn;
    @FXML
    private Label CompanyName;
    @FXML
    private ToggleGroup PartTypeTg;
    @FXML
    private RadioButton OutsourcedRBtn;
    @FXML
    private TextField PartIDTxt;
    @FXML
    private TextField PartNameTxt;
    @FXML
    private TextField PartInvTxt;
    @FXML
    private TextField PartPriceCostTxt;
    @FXML
    private TextField PartMaxTxt;
    @FXML
    private TextField addPartFieldCompOrMach;
    @FXML
    private TextField PartMinTxt;


        @FXML
    void OnActionCancelAddPart(ActionEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Cancel Adding New Part?");
            alert.setContentText("Are you sure you want to cancel adding the new part?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                stage.setScene(new Scene((Parent) scene));
                stage.show();
            }
    }

   @FXML
    void OnActionSaveAddPart(ActionEvent event) throws IOException {
       //TODO Create alerts for min/max conflicts, inv, empty data, and not selecting radio buttons

       int partID = Integer.parseInt(PartIDTxt.getText());
       String partName = PartNameTxt.getText();
       int partInv = Integer.parseInt(PartInvTxt.getText());
       double partPrice = Double.parseDouble(PartPriceCostTxt.getText());
       int min = Integer.parseInt(PartMinTxt.getText());
       int max = Integer.parseInt(PartMaxTxt.getText());

       if (InHouseRBtn.isSelected()) {
           int partMachineID = Integer.parseInt(addPartFieldCompOrMach.getText());
           Inventory.addPart(new InHouse(partID, partName, partInv, partPrice, min, max, partMachineID));

           Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
           Object scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
           stage.setScene(new Scene((Parent) scene));
           stage.show();
       }
       if (OutsourcedRBtn.isSelected()) {
           String partCompanyName = addPartFieldCompOrMach.getText();
           Inventory.addPart(new Outsourced(partID, partName, partInv, partPrice, min, max, partCompanyName));

           Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
           Object scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
           stage.setScene(new Scene((Parent) scene));
           stage.show();
       }
   }

       /*if (min > max) {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Value is invalid.");
           alert.setHeaderText("Minimum value can not be greater than maximum value.");
           alert.showAndWait();

           return; }

       if (InHouseRBtn.isSelected() && Integer.parseInt(PartInvTxt.getText()) >= Integer.parseInt(PartMinTxt.getText()) && Integer.parseInt(PartInvTxt.getText()) <= Integer.parseInt(PartMaxTxt.getText())) {
           addInHouse();

           stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
           scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
           stage.setScene(new Scene(scene));
           stage.show();
       } else if (OutsourcedRBtn.isSelected() && Integer.parseInt(PartInvTxt.getText()) >= Integer.parseInt(PartMinTxt.getText()) && Integer.parseInt(PartInvTxt.getText()) <= Integer.parseInt(PartMaxTxt.getText())) {
           addOutsourced();

           stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
           scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
           stage.setScene(new Scene(scene));
           stage.show();

       } else {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Value is invalid.");
           alert.setContentText("Inventory must be between Minimum and Maximum value.");
           alert.showAndWait();
       }
    }
        public void addInHouse() {
            int id = Part.getNewPartID();
            String name = PartNameTxt.getText();
            int inv = Integer.parseInt(PartInvTxt.getText());
            Double price = Double.parseDouble(PartPriceCostTxt.getText());
            int min = Integer.parseInt(PartMinTxt.getText());
            int max = Integer.parseInt(PartMaxTxt.getText());
            int machineID = Integer.parseInt((addPartFieldCompOrMach.getText()));

            Inventory.addPart(new InHouse(id, name, inv, price, min, max, machineID));
        }

    public void addOutsourced() {
        int id = Part.getNewPartID();
        String name = PartNameTxt.getText();
        int inv = Integer.parseInt(PartInvTxt.getText());
        Double price = Double.parseDouble(PartPriceCostTxt.getText());
        int min = Integer.parseInt(PartMinTxt.getText());
        int max = Integer.parseInt(PartMaxTxt.getText());
        String companyName = (addPartFieldCompOrMach.getText());

        Inventory.addPart(new Outsourced(id, name, inv, price, min, max, companyName));
    }

    public void createNewPart() {
        if (InHouseRBtn.isSelected()) {
            Part newPart = new InHouse(Part.getNewPartID(),
                    PartNameTxt.getText(),
                    Integer.parseInt(PartInvTxt.getText()),
                    Double.parseDouble(PartPriceCostTxt.getText()),
                    Integer.parseInt(PartMinTxt.getText()),
                    Integer.parseInt(PartMaxTxt.getText()),
                    Integer.parseInt(addPartFieldCompOrMach.getText()));

            Inventory.addPart(newPart);
        } else if (OutsourcedRBtn.isSelected()) {
            Part newPart = new InHouse(Part.getNewPartID(),
                    PartNameTxt.getText(),
                    Integer.parseInt(PartInvTxt.getText()),
                    Double.parseDouble(PartPriceCostTxt.getText()),
                    Integer.parseInt(PartMinTxt.getText()),
                    Integer.parseInt(PartMaxTxt.getText()),
                    Integer.parseInt(addPartFieldCompOrMach.getText()));

            Inventory.addPart(newPart);
        }
    }*/

    public void OnActionInHouseAdd(ActionEvent event) throws IOException {
        if ((this.PartTypeTg.getSelectedToggle().equals(this.InHouseRBtn))) {
            CompanyName.setText("Machine ID");
            addPartFieldCompOrMach.setPromptText("Machine ID");
        }
    }

        public void OnActionOutsourcedAdd (ActionEvent event) throws IOException {
           if ((this.PartTypeTg.getSelectedToggle().equals(this.OutsourcedRBtn))) {
               CompanyName.setText("Company Name");
               addPartFieldCompOrMach.setPromptText("Company Name");
           }
       }

    @Override
 public void initialize(URL url, ResourceBundle rb) {
   }
    public void setPart(Part partSelected) {
        PartIDTxt.setText(Integer.toString(partSelected.getPartID())); //convert int to string
        PartNameTxt.setText(partSelected.getName());
        PartMinTxt.setText(Integer.toString(partSelected.getMin()));
        PartInvTxt.setText(Integer.toString(partSelected.getInv()));
        PartPriceCostTxt.setText(Double.toString(partSelected.getPrice()));
        PartMaxTxt.setText(Integer.toString(partSelected.getMax()));


        if (partSelected instanceof InHouse) {
            InHouseRBtn.setSelected(true);
        } else {
            if (partSelected instanceof Outsourced) {
                OutsourcedRBtn.setSelected(true);
                Outsourced os = (Outsourced) partSelected; //cast changed type of partSelected
                addPartFieldCompOrMach.setText(os.getCompanyName());
            }

        }
    }
}


