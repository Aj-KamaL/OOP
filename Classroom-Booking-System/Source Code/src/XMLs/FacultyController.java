/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLs;

import Application.*;
import static XMLs.AdminController.currentStage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kaust
 */
public class FacultyController implements Initializable {
    
    public static User user;
    public static Stage currentStage;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Button logout;
    @FXML
    private Label user_name;
    @FXML
    private ListView<String> current_course_list;
    @FXML
    private HBox hbox1;
    @FXML
    private HBox hbox2;
    @FXML
    private HBox hbox3;
    @FXML
    private HBox hbox4;
    @FXML
    private HBox hbox5;
    @FXML
    private ChoiceBox<String> room_menu;
     @FXML
    private DatePicker date;
    @FXML
    private TextField start;
    @FXML
    private TextField end;
    @FXML
    private TextArea reason;
    @FXML
    private ListView<String> room_availability;
    @FXML
    private Button book_room;
    @FXML
    private TableView<Booking> past_bookings;
    @FXML
    private TableColumn<Booking, String> room;
    @FXML
    private TableColumn<Booking, String> dates;
    @FXML
    private TableColumn<Booking, String> days;
    @FXML
    private TableColumn<Booking, String> datecol;
    @FXML
    private TableColumn<Booking, String> time;
    @FXML
    private TableColumn<Booking, String> reasoncol;
    @FXML
    private TableColumn<Booking, String> status;
    @FXML
    private TableView<Room> my_room_table;
    @FXML
    private TableColumn<Room, String> my_room;
    @FXML
    private TableColumn<Room, String> my_capacity;
    @FXML
    private Button Search_room;
    
    
    
    // Add Courses that faculty teaches to listview.
    public void addItems() throws IOException, ClassNotFoundException
    {
        Institute iiitd = App.deserialize();
        Faculty u = (Faculty)iiitd.getUser(user.getId());
        ObservableList<String> elements = FXCollections.observableArrayList();
        for ( int i=0; i<u.getCourses().size(); i++ )
        {
//            System.out.println(user.getCourses().get(i));
            elements.add(u.getCourses().get(i).toString());
        }
        current_course_list.setItems(elements);
    }
    // shows faculty timetable based on the courses he teach
    public void UpdateTimeTable() throws IOException, ClassNotFoundException
    {
        Institute iiitd = App.deserialize();
        Faculty u = (Faculty)iiitd.getUser(user.getId());
        TimeTable tt = u.getTimeTable();
        tt.UpdateDailyArrays();
//        ObservableList<String> elements = FXCollections.observableArrayList();
        
        Label monday = new Label("Monday");
        monday.setFont(new Font("Tahoma", 19));
        monday.setTextFill(Color.web("#3fada8"));
        monday.setMinWidth(105);
//        monday.setMaxWidth(40);
        hbox1.getChildren().add(monday);
        hbox1.setPadding(new Insets(1));
        
        Label tuesday = new Label("Tuesday");
        tuesday.setFont(new Font("Tahoma", 19));
        tuesday.setTextFill(Color.web("#3fada8"));
        tuesday.setMinWidth(105);
//        monday.setMaxWidth(40);
        hbox2.getChildren().add(tuesday);
        hbox2.setPadding(new Insets(1));
        
        Label wednesday = new Label("Wednesday");
        wednesday.setFont(new Font("Tahoma", 19));
        wednesday.setTextFill(Color.web("#3fada8"));
        wednesday.setMinWidth(105);
//        monday.setMaxWidth(40);
        hbox3.getChildren().add(wednesday);
        hbox3.setPadding(new Insets(1));
        
        Label thursday = new Label("Thursday");
        thursday.setFont(new Font("Tahoma", 19));
        thursday.setTextFill(Color.web("#3fada8"));
        thursday.setMinWidth(105);
//        monday.setMaxWidth(40);
        hbox4.getChildren().add(thursday);
        hbox4.setPadding(new Insets(1));
        
        Label friday = new Label("Friday");
        friday.setFont(new Font("Tahoma", 19));
        friday.setTextFill(Color.web("#3fada8"));
        friday.setMinWidth(105);
//        monday.setMaxWidth(40);
        hbox5.getChildren().add(friday);
        hbox5.setPadding(new Insets(1));
        for ( int i=0; i<tt.getMonday().size(); i++ )
        {
            Label tmp = new Label(tt.getMonday().get(i));
            tmp.setFont(new Font("Tahoma",16));
            tmp.setMinWidth(200);
            tmp.setWrapText(true);
            hbox1.getChildren().add(tmp);
        }
        for ( int i=0; i<tt.getTuesday().size(); i++ )
        {
            Label tmp = new Label(tt.getTuesday().get(i));
            tmp.setFont(new Font("Tahoma",16));
            tmp.setMinWidth(200);
            tmp.setWrapText(true);
            hbox2.getChildren().add(tmp);
        }
        for ( int i=0; i<tt.getWednesday().size(); i++ )
        {
            Label tmp = new Label(tt.getWednesday().get(i));
            tmp.setFont(new Font("Tahoma",16));
            tmp.setMinWidth(200);
            tmp.setWrapText(true);
            hbox3.getChildren().add(tmp);
        }
        for ( int i=0; i<tt.getThursday().size(); i++ )
        {
            Label tmp = new Label(tt.getThursday().get(i));
            tmp.setFont(new Font("Tahoma",16));
            tmp.setMinWidth(200);
            tmp.setWrapText(true);
            hbox4.getChildren().add(tmp);
        }
        for ( int i=0; i<tt.getFriday().size(); i++ )
        {
            Label tmp = new Label(tt.getFriday().get(i));
            tmp.setFont(new Font("Tahoma",16));
            tmp.setMinWidth(200);
            tmp.setWrapText(true);
            hbox5.getChildren().add(tmp);
        }
    }
    @FXML
    public void UpdateRoomCapacity(ActionEvent event) throws IOException, ClassNotFoundException
    {
        if ( !room_menu.getValue().equals("Nil") )
        {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("Error");
            a.setHeaderText("Wrong Button Pressed.");
            a.setResizable(false);
            String content = String.format("Please Press book room.");
            a.setContentText(content);
            a.showAndWait();
        }
        else
        {
            Institute iiitd = App.deserialize();
            ObservableList<Room> elements = FXCollections.observableArrayList();
        
            int num = date.getValue().getDayOfWeek().getValue()-1;
            int month = date.getValue().getMonthValue();
            int day = date.getValue().getDayOfMonth();
            String lasvegas = "";
            if ( num == 0 )
            {
                lasvegas = "Monday";
            }
            if ( num == 1 )
            {
               lasvegas = "Tuesday";
            }
            if ( num == 2 )
            {
                lasvegas = "Wednesday";
            }
            if ( num == 3 )
            {
                lasvegas = "Thursday";
            }
            if ( num == 4 )
            {
                lasvegas = "Friday";
            }
            if ( num == 5 )
            {
                lasvegas = "Saturday";
            }
            if ( num == 6 )
            {
                lasvegas = "Sunday";
            }
//        Booking x = new Booking(r, start.getText()+"-"+end.getText(), date.getValue().toString(), lasvegas, reason.getText());
//        Calendar cal = Calendar.getInstance();
//        x.setBookingDate(cal.getTime());
            ArrayList<Room> r = iiitd.getRooms();
            for ( int i=0; i<r.size(); i++ )
            {
                boolean flag = true;
                ArrayList<String> bookings = r.get(i).getBookings();
                for (String booking : bookings)
                {
                    String[] help = booking.split("\t");
                    if ( help[0].equals("Always") || help[0].equals(date.getValue().toString()) )
                    {
                        if ( help[1].equals(lasvegas) )
                        {
                            if (r.get(i).isOverlap(help[2],start.getText()+"-"+end.getText()))
                            {
                                System.out.println("Time Comparison: "+help[2]+" 2nd Time "+start.getText()+"-"+end.getText());
                                flag = false;
                            }
                        }
                    }
                }
                if ( flag )
                {
                    elements.add(r.get(i));
                }
            }
            my_room.setCellValueFactory(new PropertyValueFactory<Room, String>("RoomNo"));
            my_room.setStyle("-fx-alignment: CENTER;");
            my_capacity.setCellValueFactory(new PropertyValueFactory<Room, String>("Capacity"));
            my_capacity.setStyle("-fx-alignment: CENTER;");
            my_room_table.setItems(elements);
        }
    }
    @FXML
    public void Submit_room(ActionEvent event) throws IOException, ClassNotFoundException
    {
        Room r = null;
        if ( room_menu.getValue().equals("Nil") && my_room_table.getSelectionModel().getSelectedItem() == null )
        {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("Error");
            a.setHeaderText("Room not selected");
            a.setResizable(false);
            String content = String.format("Please select room from table");
            a.setContentText(content);
            a.showAndWait();
        }
        else
        {
            Institute iiitd = App.deserialize();
            if ( room_menu.getValue().equals("Nil") && my_room_table.getSelectionModel().getSelectedItem() != null )
            {
                r = iiitd.SearchRoom(my_room_table.getSelectionModel().getSelectedItem().getRoomNo());
            }
            else
            {
                r = iiitd.SearchRoom(room_menu.getValue());
            }
            Faculty u = (Faculty)iiitd.getUser(user.getId());
//        System.out.println("Date Picker:- " + date.getValue().toString() );
        
        int num = date.getValue().getDayOfWeek().getValue()-1;
        int month = date.getValue().getMonthValue();
        int day = date.getValue().getDayOfMonth();
//        System.out.println("Num: "+num);
//        System.out.println("month "+month);
//        System.out.println("day "+day);
        String lasvegas = "";
        if ( num == 0 )
        {
            lasvegas = "Monday";
        }
        if ( num == 1 )
        {
            lasvegas = "Tuesday";
        }
        if ( num == 2 )
        {
            lasvegas = "Wednesday";
        }
        if ( num == 3 )
        {
            lasvegas = "Thursday";
        }
        if ( num == 4 )
        {
            lasvegas = "Friday";
        }
        if ( num == 5 )
        {
            lasvegas = "Saturday";
        }
        if ( num == 6 )
        {
            lasvegas = "Sunday";
        }
        Booking x = new Booking(r, start.getText()+"-"+end.getText(), date.getValue().toString(), lasvegas, reason.getText());
        x.setID(user.getId());
        Calendar cal = Calendar.getInstance();
        x.setBookingDate(cal.getTime());
        boolean flag = true;
        ArrayList<String> bookings = r.getBookings();
        System.out.println("Bookings Size "+bookings.size());
        for (String booking : bookings) 
        {
            String[] help = booking.split("\t");
//            System.out.println(help[0]+" "+help[1]+" "+help[2]);
//            if ( help[0].equals("Always") )
//            {
//               
//            }
            if ( help[0].equals("Always") || help[0].equals(date.getValue().toString()) )
            {
                if ( help[1].equals(lasvegas) )
                {
                    if (r.isOverlap(help[2],start.getText()+"-"+end.getText()))
                    {
                        System.out.println("Time Comparison: "+help[2]+" 2nd Time "+start.getText()+"-"+end.getText());
                        flag = false;
                    }
                }
            }
        }
        if ( flag )
        {
            x.Book();
            u.BookRoom(x);
            r.addBooking(date.getValue().toString()+"\t"+lasvegas+"\t"+start.getText()+"-"+end.getText());
            iiitd.getAdmin().addRequest(x);
        }
        else
        {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("Room Booked");
            a.setHeaderText("Room Already booked for this time");
            a.setResizable(false);
            String content = String.format("Please select a different time or different room."+"\n"+x);
            a.setContentText(content);
            a.showAndWait();
        }
        App.serialize(iiitd);
        UpdatePastBookings();
        UpdateAvailableRoom();
        }
//        System.out.println("Total Requests: ");
//        iiitd.getAdmin().printRequests();
        
    }
    public void UpdateAvailableRoom() throws IOException, ClassNotFoundException
    {
        Institute iiitd = App.deserialize();
        ObservableList<String> elements = FXCollections.observableArrayList();
        ArrayList<Room> all_rooms = iiitd.getRooms();
        for (Room rm : all_rooms) 
        {
            ArrayList<String> Monday = new ArrayList<String>();
            ArrayList<String> Tuesday = new ArrayList<String>();
            ArrayList<String> Wednesday = new ArrayList<String>();
            ArrayList<String> Thursday = new ArrayList<String>();
            ArrayList<String> Friday = new ArrayList<String>();
            ArrayList<String> Saturday = new ArrayList<String>();
            ArrayList<String> Sunday = new ArrayList<String>();
            
            ArrayList<String> all_bookings = rm.getBookings();
            for ( String book : all_bookings )
            {
                String[] tmp = book.split("\t");
                if ( tmp[1].equals("Monday") )
                {
                    Monday.add("("+tmp[0]+") "+tmp[2]);
                }
                else if ( tmp[1].equals("Tuesday") )
                {
                    Tuesday.add("("+tmp[0]+") "+tmp[2]);
                }
                else if ( tmp[1].equals("Wednesday") )
                {
                    Wednesday.add("("+tmp[0]+") "+tmp[2]);
                }
                else if ( tmp[1].equals("Thursday") )
                {
                    Thursday.add("("+tmp[0]+") "+tmp[2]);
                }
                else if ( tmp[1].equals("Friday") )
                {
                    Friday.add("("+tmp[0]+") "+tmp[2]);
                }
                else if ( tmp[1].equals("Saturday") )
                {
                    Saturday.add("("+tmp[0]+") "+tmp[2]);
                }
                else if ( tmp[1].equals("Sunday") )
                {
                    Sunday.add("("+tmp[0]+") "+tmp[2]);
                }
            }
            Collections.sort(Monday, new TimeCompare());
            Collections.sort(Tuesday, new TimeCompare());
            Collections.sort(Wednesday, new TimeCompare());
            Collections.sort(Thursday, new TimeCompare());
            Collections.sort(Friday, new TimeCompare());
            Collections.sort(Saturday, new TimeCompare());
            Collections.sort(Friday, new TimeCompare());
            String data = rm.getRoomNo()+"\n"+"SUNDAY:"+"\t";
            for ( String s : Sunday )
            {
                data += s +", ";
            }
            data += "\n"+"MONDAY:"+"\t";
            for ( String s : Monday )
            {
                data += s +", ";
            }
            data += "\n"+"TUESDAY:"+"\t";
            for ( String s : Tuesday )
            {
                data += s +", ";
            }
            data += "\n"+"WEDNESDAY:"+"\t";
            for ( String s : Wednesday )
            {
                data += s +", ";
            }
            data += "\n"+"THURSDAY:"+"\t";
            for ( String s : Thursday )
            {
                data += s +", ";
            }
            data += "\n"+"FRIDAY:"+"\t";
            for ( String s : Friday )
            {
                data += s +", ";
            }
            data += "\n"+"SATURDAY:"+"\t";
            for ( String s : Saturday )
            {
                data += s +", ";
            }
            elements.add(data);
        }
        room_availability.setItems(elements);
    }
    
    public void UpdatePastBookings() throws IOException, ClassNotFoundException
    {
        Institute iiitd = App.deserialize();
        Faculty u = (Faculty)iiitd.getUser(user.getId());
        ArrayList<Booking> b = u.getbookings();
        ObservableList<Booking> elements = FXCollections.observableArrayList();
        for (Booking b1 : b) 
        {
            elements.add(b1);
        }
        room.setCellValueFactory(new PropertyValueFactory<Booking, String>("RoomNo"));
        room.setStyle("-fx-alignment: CENTER;");
        datecol.setCellValueFactory(new PropertyValueFactory<Booking, String>("Date"));
        datecol.setStyle("-fx-alignment: CENTER;");
        days.setCellValueFactory(new PropertyValueFactory<Booking, String>("Day"));
        days.setStyle("-fx-alignment: CENTER;");
        time.setCellValueFactory(new PropertyValueFactory<Booking, String>("Time"));
        time.setStyle("-fx-alignment: CENTER;");
        reasoncol.setCellValueFactory(new PropertyValueFactory<Booking, String>("Reason"));
        reasoncol.setStyle("-fx-alignment: CENTER;");
        status.setCellValueFactory(new PropertyValueFactory<Booking, String>("Status"));
        status.setStyle("-fx-alignment: CENTER;");
        past_bookings.setItems(elements);
    }
    @FXML
    public void Remove_button(ActionEvent event) throws IOException, ClassNotFoundException
    {
        Institute iiitd = App.deserialize();
        Faculty s = (Faculty)iiitd.getUser(user.getId());
        Booking b = past_bookings.getSelectionModel().getSelectedItem();
        iiitd.getAdmin().removeRequest(b);
        s.CancelRoomBooking(b);
        Room r = iiitd.SearchRoom(b.getRoomNo());
        r.deleteBooking(b.getDate()+"\t"+b.getDay()+"\t"+b.getTime());
        App.serialize(iiitd);
        UpdatePastBookings();
        UpdateAvailableRoom();
    }
    @FXML
    private void LogoutAction(ActionEvent event) throws IOException {
        System.out.println("Opening Login.fxml After Pressing Logout Button");
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        currentStage = LoginController.currentStage;
        Scene scene = new Scene(root);
        
        currentStage.setScene(scene);
//      stage.setResizable(false);
        currentStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        user = LoginController.currentUser;
        user_name.setText("Welcome "+user.getName());
        room_menu.setValue("Nil");
        room_menu.getItems().addAll("Nil","C01", "C02", "C03", "C11", "C12", "C13", "C21", "C22", "C23","C24");
        try 
        {
            addItems();
            UpdateTimeTable();
            UpdatePastBookings();
            UpdateAvailableRoom();
        } catch (IOException ex) {
            Logger.getLogger(FacultyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacultyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
