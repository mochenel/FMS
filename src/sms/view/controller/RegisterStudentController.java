package sms.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sms.Service.ClassService;
import sms.Service.StudentService;
import sms.model.SchoolClass;
import sms.model.Student;
import sms.validation.StudentValidation;

/**
 * @author Freddy on 21/08/2022
 * @project Fees Management System
 **/

public class RegisterStudentController implements Initializable {

	@FXML
	private ChoiceBox<String> class_id;
	@FXML
	private TextField first_name;

	@FXML
	private TextField last_name;

	@FXML
	private TextField phone_number;

	@FXML
	private TextField email;

	@FXML
	private DatePicker date_of_birth;

	@FXML
	private TextField address_city;

	@FXML
	private TextField address_street_and_house_no;

	@FXML
	private TextField address_region;

	@FXML
	private TextField address_subregion;

	@FXML
	private TextField address_country;

	@FXML
	private JFXButton Back;

	@FXML
	private AnchorPane root;
	
	private Map<String, Integer> ClassChoices;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ClassChoices = new HashMap<>(); // For class id reference since only class is visible to user
		ClassChoices.put("None",0);
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("None");
		int i = 1;
		try {
			for(SchoolClass sc: ClassService.finAllClasses())  {
				ClassChoices.put(sc.getName(),i++);
				list.add(sc.getName());
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        loadComboBox();
		
		// populate the Choicebox;
		class_id.setItems(list);
		class_id.getSelectionModel().selectFirst();
	}

	@FXML
	void Back() {
		try {
			AnchorPane studentMgmt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/StudentManagement.fxml")));
			root.getChildren().setAll(studentMgmt);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@FXML
	private void AddStudent(ActionEvent event) {
	
	


		try {
			Student s = new Student(0, ClassChoices.get(this.class_id.getValue()), this.first_name.getText(),
					this.last_name.getText(), this.phone_number.getText(), this.email.getText(),
					Date.valueOf(this.date_of_birth.getValue()), this.address_city.getText(),
					this.address_street_and_house_no.getText(), this.address_region.getText(),
					this.address_subregion.getText(), this.address_country.getText());
			StudentValidation.isNameValid(s);
			StudentValidation.isPhoneNumberValid(s, false);
			StudentValidation.isEmailValid(s, false);
			
			int i = StudentService.AddStudent(s);

			if (i > 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Student Registration");
				alert.setHeaderText(null);
				alert.setContentText("Student Registered Successfully..!");
				alert.showAndWait();

				reset();

			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Student Registration");
				alert.setHeaderText(null);
				alert.setContentText("OOPs there is an error adding Student");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Student Registration");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

	}

	@FXML
	private void reset() {

		first_name.setText(null);
		last_name.setText(null);
		phone_number.setText(null);
		email.setText(null);
		date_of_birth.setValue(null);
		address_city.setText(null);
		address_street_and_house_no.setText(null);
		address_region.setText(null);
		address_subregion.setText(null);
		address_country.setText(null);
		class_id.setValue("None");
	}

}
