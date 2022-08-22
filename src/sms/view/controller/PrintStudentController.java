package sms.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sms.Service.ClassService;
import sms.Service.StudentService;
import sms.db.DBConnection;
import sms.main.StartProject;
import sms.model.SchoolClass;
import sms.model.Student;
import sms.tableModel.StudentTableModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class PrintStudentController implements Initializable {

	@FXML
	private TableView<StudentTableModel> studentTable;

	@FXML
	private TableColumn<Student, String> idColumn;

	@FXML
	private TableColumn<Student, String> fullnameColumn;

	@FXML
	private TableColumn<Student, String> phoneColumn;

	@FXML
	private TableColumn<Student, String> emailColumn;

	@FXML
	private TableColumn<Student, String> dobColumn;

	@FXML
	private TableColumn<Student, String> classColumn;

	@FXML
	private TableColumn<Student, String> addressColumn;

	@FXML
	private ComboBox<String> classes;

	@FXML
	private JFXButton generate;

	@FXML
	private JFXButton printStudents;

	@FXML
	private JFXCheckBox idCheckBox;

	@FXML
	private JFXCheckBox fullnameCheckBox;

	@FXML
	private JFXCheckBox phoneCheckBox;

	@FXML
	private JFXCheckBox emailCheckBox;

	@FXML
	private JFXCheckBox dobCheckBox;

	@FXML
	private JFXCheckBox classCheckBox;

	@FXML
	private JFXCheckBox addressCheckBox;

	@FXML
	private AnchorPane root;

	@FXML
	private JFXButton Back;

	private Map<String, Integer> ClassChoices;
	ObservableList<StudentTableModel> studentList = FXCollections.observableArrayList();

	@FXML
	void Back(ActionEvent event) {
		try {
			AnchorPane studentMgmt = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/StudentManagement.fxml")));
			root.getChildren().setAll(studentMgmt);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClassChoices = new HashMap<>(); // For class id reference since only class is visible to user
		ClassChoices.put("ALL", 0);
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("ALL");
		int i = 1;
		try {
			for (SchoolClass sc : ClassService.finAllClasses()) {
				ClassChoices.put(sc.getName(), i++);
				list.add(sc.getName());
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// populate the Choicebox;
		classes.getItems().addAll(list);
	}

	@FXML
	void generate(ActionEvent event) {

		try {
			studentTable.getItems().clear();

			Connection conn = DBConnection.getDBConnection().getConnection();

			// if((loadGrades != null)&&(loadGender != null)){
			int class_id = ClassChoices.get(classes.getValue());
			String sql = "select * from sms_students";
			if (class_id != 0) {
				 sql = "SELECT sms_students.*,sms_classes,name as className FROM sms_students INNER JOIN sms_classes ON class_id=id WHERE class_id = ? ";
				 conn = DBConnection.getDBConnection().getConnection();
				PreparedStatement stm = conn.prepareStatement(sql);
				stm.setObject(1, class_id);
				ResultSet rst = stm.executeQuery();
				while (rst.next())
					studentList.add(new StudentTableModel(rst.getInt("id"), rst.getString("className"), rst.getString("first_name"),
							rst.getString("last_name"), rst.getString("phone_number"), rst.getString("email"),
							rst.getDate("date_of_birth"), rst.getString("address_city") + "\s"
							+ rst.getString("address_street_and_house_no") +"\s" +rst.getString("address_region")
							+ "\n" +rst.getString("address_subregion") + " \n" +rst.getString("address_country")));

			}
			

			idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
			phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
			emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
			dobColumn.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
			classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
			addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));


			studentTable.setItems(studentList);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void printStudents(ActionEvent event) {
//
//		try {
//			studentTable.getItems().clear();
//			String grade = loadGrades.getValue();
//			String gender = loadGender.getValue();
//			String year = loadYears.getValue();
//
//			Connection conn = DBConnection.getDBConnection().getConnection();
//			InputStream report1 = getClass().getResourceAsStream("/sms/Reports/StudentList.jrxml");
//			InputStream report2 = getClass().getResourceAsStream("/sms/Reports/StudentListGender.jrxml");
//			InputStream report3 = getClass().getResourceAsStream("/sms/Reports/PastStudentList.jrxml");
//			InputStream report4 = getClass().getResourceAsStream("/sms/Reports/PastStudentListGender.jrxml");
//
//			/*
//			 * JasperDesign jd = JRXmlLoader.load("src\\sms\\Reports\\StudentList.jrxml");
//			 * JasperDesign jd2 =
//			 * JRXmlLoader.load("src\\sms\\Reports\\StudentListGender.jrxml"); JasperDesign
//			 * jd3 = JRXmlLoader.load("src\\sms\\Reports\\PastStudentList.jrxml");
//			 * JasperDesign jd4 =
//			 * JRXmlLoader.load("src\\sms\\Reports\\PastStudentListGender.jrxml");
//			 */
//			JRDesignQuery query = new JRDesignQuery();
//
//			if (loadYears.getValue() == null) {
//
//				if (loadGrades != null) {
//
//					if (gender == "All") {
//
//						JasperDesign jd = JRXmlLoader.load(report1);
//						query.setText("select * from students where grade = '" + grade + "'");
//						jd.setQuery(query);
//						ReportViewController r = new ReportViewController();
//						r.viewReport(jd);
//
//					} else {
//						JasperDesign jd2 = JRXmlLoader.load(report2);
//						query.setText(
//								"select * from students where grade = '" + grade + "' AND gender = '" + gender + "'");
//						jd2.setQuery(query);
//						ReportViewController r = new ReportViewController();
//						r.viewReport(jd2);
//					}
//				}
//			}
//			if (loadYears.getValue() != null) {
//
//				if (gender == "All") {
//
//					JasperDesign jd3 = JRXmlLoader.load(report3);
//					query.setText("select * from paststudents where year = '" + year + "'");
//					jd3.setQuery(query);
//					ReportViewController r = new ReportViewController();
//					r.viewReport(jd3);
//
//				} else {
//
//					JasperDesign jd4 = JRXmlLoader.load(report4);
//					query.setText(
//							"select * from paststudents where year = '" + year + "' AND gender = '" + gender + "'");
//					jd4.setQuery(query);
//					ReportViewController r = new ReportViewController();
//					r.viewReport(jd4);
//				}
//			}
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (JRException e) {
//			e.printStackTrace();
//		}

	}
}
