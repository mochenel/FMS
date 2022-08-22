package sms.view.controller;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sms.Service.SchoolService;
import sms.Service.StudentService;
import sms.db.DBConnection;
import sms.model.School;


public class SchoolInfoController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            School s = SchoolService.schoolInfo();
            if (s != null) {
            	id.setText(String.valueOf(s.getId()));
                name.setText(s.getName());
                reg_number.setText(s.getReg_number());
                proprietor.setText(s.getProprietor());
               

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("School Information");
                alert.setHeaderText(null);
                alert.setContentText("No Information Found..!");
                alert.showAndWait();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SchoolService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private AnchorPane root;

    @FXML
    private TextField id;
    
    @FXML
    private TextField name;

    @FXML
    private TextField reg_number;

    @FXML
    private TextField proprietor;

    @FXML
    private JFXButton updateDetails;

    @FXML
    private JFXButton addDetails;

    @FXML
    private JFXButton printDetails;

    @FXML
    private JFXButton Back;

    @FXML
    void Back(ActionEvent event) {

        try {
            AnchorPane user = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/MainDashboard.fxml")));
            root.getChildren().setAll(user);
        }catch(IOException e){
            System.out.println(e);
        }

    }

    @FXML
    void addDetails(ActionEvent event) {          
        try {

            if (ValidationController.validateEmpty(this.name) && ValidationController.validateEmpty(this.reg_number)&&ValidationController.validateEmpty(this.proprietor)) {
   
                School sch = new School(0,  this.name.getText(), this.reg_number.getText(), this.proprietor.getText());
                int i = SchoolService.AddDetails(sch);

                if (i > 0) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("School Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Updated Successfully");
                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("School Information");
                    alert.setHeaderText(null);
                    alert.setContentText("OOPs there is an error in Updating Details..!");
                    alert.showAndWait();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void updateDetails(ActionEvent event) {
        try {
        	int id = Integer.parseInt(this.id.getText());
            String name = this.name.getText();
            String reg_number = this.reg_number.getText();
            String proprietor = this.proprietor.getText();

            School sch = new School(id,name,reg_number,proprietor);
            int i = SchoolService.updateInfo(sch);

            if (i > 0) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("School Information");
                alert.setHeaderText(null);
                alert.setContentText("Information Updated Successfully...!");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("School Information");
                alert.setHeaderText(null);
                alert.setContentText("OOPs there is an error Updating Details");
                alert.showAndWait();
            }

        }catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(SchoolService.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
}
