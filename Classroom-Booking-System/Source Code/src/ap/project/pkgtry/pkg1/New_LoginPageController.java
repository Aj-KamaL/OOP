/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap.project.pkgtry.pkg1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Application.*;
import MyExceptions.*;
import java.io.*;
import javafx.scene.paint.Color;

/**
 *
 * @author Kaustav Vats (2016048)
 */
public class New_LoginPageController implements Initializable {
    private App myApp = new App();
    @FXML
    private TextField Name;
    @FXML
    private TextField Id1;
    @FXML
    private TextField Pw1;
    @FXML
    private TextField Id2;
    @FXML
    private TextField Pw2;
    @FXML
    private RadioButton Admin;
    @FXML
    private RadioButton Faculty;
    @FXML
    private RadioButton Student;
    @FXML
    private Label Exc1;
    @FXML
    private Label Exc2;
    @FXML
    private ToggleGroup or;
    @FXML
    private void LoginAction(ActionEvent event) throws IOException, ClassNotFoundException {
        System.out.println("Login Button Pressed!");
        Institute iiitd = myApp.deserialize();
        try {
            int index = iiitd.Validate_Login(Id2.getText(), Pw2.getText());
            Exc2.setVisible(false);
            System.out.println(index);
            iiitd.ShowUser();
            if ( iiitd.isStudent(iiitd.getUser(index)) )
            {
                // Start Student Page
            }
            else if ( iiitd.isFaculty(iiitd.getUser(index)) )
            {
                // Start Faculty Page
            }
            else
            {
                
            }
        }
        catch(WrongCredentials e) {
            Exc2.setVisible(true);
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void CreateAccount(ActionEvent event) throws IOException, ClassNotFoundException {
        System.out.println("Create Account Button Pressed!!");
        Institute iiitd = myApp.deserialize();
        try {
            iiitd.Validate_Signup(Id1.getText());
            RadioButton a = (RadioButton) or.getSelectedToggle();
            System.out.println(a.getText());
            if ( a.getText().equals("Student") )
            {
                iiitd.addUser(new Student(Name.getText(),Id1.getText(),Pw1.getText()));
            }
            else if ( a.getText().equals("Faculty") )
            {
                iiitd.addUser(new Faculty(Name.getText(),Id1.getText(),Pw1.getText()));
            }
            else
            {
                iiitd.addUser(new Admin(Name.getText(),Id1.getText(),Pw1.getText()));
            }
            myApp.serialize(iiitd);
            iiitd.ShowUser();
            Exc1.setVisible(false);
        }
        catch(UserAlreadyExists e){
            Exc1.setVisible(true);
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
