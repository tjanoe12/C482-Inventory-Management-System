package Controller;

import Model.*;
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
import java.util.Optional;
import java.util.ResourceBundle;



public class ModifyPart implements Initializable {
    private Part newModifyPart;
    boolean isInHouse;
    final int index = MainMenu.getSelectedPartIndex();

    @FXML
    private TextField PartIDTxt;

    public Label CompanyNameLbl;
    @FXML
    private TextField CompanyNameTxtField;
    @FXML
    private TextField PartMaxTxt;
    @FXML
    private TextField PartPriceCostTxt;
    @FXML
    private TextField PartInvTxt;
    @FXML
    private TextField PartNameTxt;
    @FXML
    private TextField PartMinTxt;
    @FXML
    private RadioButton ModifyPartInHouseRadBtn;
    @FXML
    private ToggleGroup ModifyPartTgl;
    @FXML
    private RadioButton ModifyPartOutsourcedRadBtn;

    @FXML
    void OnActionMainMenu(ActionEvent event) throws IOException {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Cancel Modifying Part?");
        alert.setContentText("Are you sure you want to cancel modifying the part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    @FXML
    void OnActionSaveModify(ActionEvent event) throws IOException {

        if (ModifyPartInHouseRadBtn.isSelected()) {
            int id = Integer.parseInt(PartIDTxt.getText());
            String name = PartNameTxt.getText();
            Double price = Double.parseDouble(PartPriceCostTxt.getText());
            int inv = Integer.parseInt(PartInvTxt.getText());
            int min = Integer.parseInt(PartMinTxt.getText());
            int max = Integer.parseInt(PartMaxTxt.getText());
            int machineId = Integer.parseInt(CompanyNameTxtField.getText());
            //Assigns values
            InHouse inHousePart = new InHouse(id, name, inv, price, min, max, machineId);
            inHousePart.setID(Integer.parseInt(PartIDTxt.getText()));
            inHousePart.setName(PartNameTxt.getText());
            inHousePart.setPrice(Double.parseDouble(PartPriceCostTxt.getText()));
            inHousePart.setInv(Integer.parseInt(PartInvTxt.getText()));
            inHousePart.setMax(Integer.parseInt(PartMaxTxt.getText()));
            inHousePart.setMin(Integer.parseInt(PartMinTxt.getText()));
            inHousePart.setMachineID(Integer.parseInt(CompanyNameTxtField.getText()));
            //updates part
            Inventory.updatePart(inHousePart, Integer.parseInt(PartIDTxt.getText()) - 1);


        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }


       else if (ModifyPartOutsourcedRadBtn.isSelected())

                {
                    int id = Integer.parseInt(PartIDTxt.getText());
                    String name = PartNameTxt.getText();
                    Double price = Double.parseDouble(PartPriceCostTxt.getText());
                    int inv = Integer.parseInt(PartInvTxt.getText());
                    int min = Integer.parseInt(PartMinTxt.getText());
                    int max = Integer.parseInt(PartMaxTxt.getText());
                    String companyName = (CompanyNameTxtField.getText());

                    Outsourced outSourcedPart = new Outsourced(id,name,inv,price,min,max,companyName);
                    //Assigns values
                    outSourcedPart.setID(id);
                    outSourcedPart.setName(name);
                    outSourcedPart.setPrice(price);
                    outSourcedPart.setInv(inv);
                    outSourcedPart.setMax(max);
                    outSourcedPart.setMin(min);
                    outSourcedPart.setCompanyName(companyName);
                    //updates part
                    if (true) {
                        Inventory.updatePart(outSourcedPart, Integer.parseInt(PartIDTxt.getText()) - 1);}
                        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        Object scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                        stage.setScene(new Scene((Parent) scene));
                        stage.show();
                }
        }


    public void OnActionInHouseAdd(ActionEvent event) throws IOException {
        if ((this.ModifyPartTgl.getSelectedToggle().equals(this.ModifyPartInHouseRadBtn))) {
            CompanyNameLbl.setText("Machine ID");
            CompanyNameTxtField.setPromptText("Machine ID");
        }
    }

    public void OnActionOutsourcedAdd (ActionEvent event) throws IOException {
        if ((this.ModifyPartTgl.getSelectedToggle().equals(this.ModifyPartOutsourcedRadBtn))) {
            CompanyNameLbl.setText("Company Name");
            CompanyNameTxtField.setPromptText("Company Name");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Part currentPart = Inventory.getAllParts().get(index);
        isInHouse = currentPart instanceof InHouse ? true : false;
        PartIDTxt.setText(String.valueOf(currentPart.getPartID()));
        if (isInHouse) {
            ModifyPartTgl.selectToggle(ModifyPartInHouseRadBtn);
            CompanyNameLbl.setText("Machine ID");
            CompanyNameTxtField.setText(String.valueOf(InHouse.getMachineID()));
        }
        else {
            ModifyPartTgl.selectToggle(ModifyPartOutsourcedRadBtn);
            CompanyNameLbl.setText("Company Name");
            CompanyNameTxtField.setText(Outsourced.getCompanyName());
        }
        PartIDTxt.setText(String.valueOf(currentPart.getPartID()));
        PartNameTxt.setText(currentPart.getName());
        PartMaxTxt.setText(String.valueOf(currentPart.getMax()));
        PartMinTxt.setText(String.valueOf(currentPart.getMin()));
        PartInvTxt.setText(String.valueOf(currentPart.getInv()));
        PartPriceCostTxt.setText(String.valueOf(currentPart.getPrice()));

   
    }

    public void setPart(Part modifyPart) {
        newModifyPart = modifyPart;
        PartIDTxt.setText(String.valueOf(newModifyPart.getPartID()));
        PartNameTxt.setText(newModifyPart.getName());
        PartInvTxt.setText(String.valueOf(newModifyPart.getInv()));
        PartPriceCostTxt.setText(String.valueOf(newModifyPart.getPrice()));
        PartMinTxt.setText(String.valueOf(newModifyPart.getMin()));
        PartMaxTxt.setText(String.valueOf(newModifyPart.getMax()));
    }
}
