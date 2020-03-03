/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLs;

import Application.*;
import java.util.*;
import java.io.IOException;
import static java.lang.String.format;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kaust
 */
public class AdminController implements Initializable {
    public static User user;
    public static Stage currentStage;
    @FXML
    private Label user_name;
    @FXML
    private Button logout;
    @FXML
    private TableView<Booking> past_bookingsa;
    @FXML
    private TableColumn<Booking, String> rooma;
    @FXML
    private TableColumn<Booking, String> datesa;
    @FXML
    private TableColumn<Booking, String> daysa;
    @FXML
    private TableColumn<Booking, String> datecola;
    @FXML
    private TableColumn<Booking, String> timea;
    @FXML
    private TableColumn<Booking, String> reasoncola;
    @FXML
    private TableColumn<Booking, String> statusa;
    @FXML
    private Button accept;
    @FXML
    private Button reject;
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
    private Button book_rooma;
    @FXML
    private ListView<String> room_availability;
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
    /**
     * Initializes the controller class.
     */
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
    public void UpdateBookingsRequests() throws IOException, ClassNotFoundException
    {
        Institute iiitd = App.deserialize();
        Admin u = iiitd.getAdmin();
        ArrayList<Booking> b = u.getRequests();
        ObservableList<Booking> elements = FXCollections.observableArrayList();
        for (Booking b1 : b) 
        {
            elements.add(b1);
        }
        rooma.setCellValueFactory(new PropertyValueFactory<Booking, String>("RoomNo"));
        rooma.setStyle("-fx-alignment: CENTER;");
        datecola.setCellValueFactory(new PropertyValueFactory<Booking, String>("Date"));
        datecola.setStyle("-fx-alignment: CENTER;");
        daysa.setCellValueFactory(new PropertyValueFactory<Booking, String>("Day"));
        daysa.setStyle("-fx-alignment: CENTER;");
        timea.setCellValueFactory(new PropertyValueFactory<Booking, String>("Time"));
        timea.setStyle("-fx-alignment: CENTER;");
        reasoncola.setCellValueFactory(new PropertyValueFactory<Booking, String>("Reason"));
        reasoncola.setStyle("-fx-alignment: CENTER;");
        statusa.setCellValueFactory(new PropertyValueFactory<Booking, String>("Status"));
        statusa.setStyle("-fx-alignment: CENTER;");
        past_bookingsa.setItems(elements);
    }
    // This will change booking status to Booked.
    @FXML
    private void Accept_Button(ActionEvent event) throws IOException, ClassNotFoundException 
    {
        Institute iiitd = App.deserialize();
        Admin u = iiitd.getAdmin();
        Booking b = past_bookingsa.getSelectionModel().getSelectedItem();
        b = u.getRequestCopy(b);
        if ( b == null )
        {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("Error");
            a.setHeaderText("Booking not selected");
            a.setResizable(false);
            String content = String.format("Please select a booking from table, before clicking Accept Button");
            a.setContentText(content);
            a.showAndWait();
        }
        else
        {
            if ( b.getStatus().equals("Pending") )
            {
                
                b.Book();
                Mail.Accepted(b.getID(), b.toString());
//                Room r = iiitd.SearchRoom(b.getRoomNo());
//                r.addBooking(b.getDate()+"\t"+b.getDay()+"\t"+b.getTime());
            }
            else if ( b.getStatus().equals("Rejected") )
            {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Error");
                a.setHeaderText("Booking can't be Accepted now");
                a.setResizable(false);
                String content = String.format("Rejected Applications cannot be Accepted.");
                a.setContentText(content);
                a.showAndWait();
            }
        }
        App.serialize(iiitd);
        UpdateBookingsRequests();
    }
    // This will change booking status to rejected.
    @FXML
    private void Reject_Button(ActionEvent event) throws IOException, ClassNotFoundException 
    {
        Institute iiitd = App.deserialize();
        Admin u = iiitd.getAdmin();
        Booking b = past_bookingsa.getSelectionModel().getSelectedItem();
        b = u.getRequestCopy(b);
        if ( b == null )
        {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("Error");
            a.setHeaderText("Booking not selected");
            a.setResizable(false);
            String content = String.format("Please select a booking from table, before clicking Reject Button");
            a.setContentText(content);
            a.showAndWait();
        }
        else
        {
            if ( b.getStatus().equals("Pending") || b.getStatus().equals("Booked"))
            {
                b.Reject();
                Mail.Rejected(b.getID(), b.toString());
                Room r = iiitd.SearchRoom(b.getRoomNo());
                r.deleteBooking(b.getDate()+"\t"+b.getDay()+"\t"+b.getTime());
            }
        }
        App.serialize(iiitd);
        UpdateBookingsRequests();
        UpdateAvailableRoom();
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
    // This method similar like student, but only difference is that room will directly be booked.
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
                System.out.println("1");
                r = iiitd.SearchRoom(my_room_table.getSelectionModel().getSelectedItem().getRoomNo());
            }
            else
            {
                System.out.println("2");
                r = iiitd.SearchRoom(room_menu.getValue());
            }
        Admin u = iiitd.getAdmin();
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
        Admin u = iiitd.getAdmin();
        ArrayList<Booking> b = u.getBookings();
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
        Admin u = iiitd.getAdmin();
        Booking b = past_bookings.getSelectionModel().getSelectedItem();
        u.CancelRoomBooking(b);
        Room r = iiitd.SearchRoom(b.getRoomNo());
        r.deleteBooking(b.getDate()+"\t"+b.getDay()+"\t"+b.getTime());
        App.serialize(iiitd);
        UpdatePastBookings();
        UpdateAvailableRoom();
    }
    public void AutoReject() throws IOException, ClassNotFoundException, ParseException
    {
        Institute iiitd = App.deserialize();
        ArrayList<Booking> bookings = iiitd.getAdmin().getRequests();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date one = cal.getTime();
        for ( int i=0; i<bookings.size(); i++ )
        {
            if ( bookings.get(i).getStatus().equals("Pending") )
            {
                Date two = bookings.get(i).getBookingDate();
                long difference =  (one.getTime()-two.getTime())/86400000;
//                System.out.println(Math.abs(difference));
                if ( Math.abs(difference) > 5 )
                {
                    bookings.get(i).Reject();
                    Room r = iiitd.SearchRoom(bookings.get(i).getRoomNo());
                    r.deleteBooking(bookings.get(i).getDate()+"\t"+bookings.get(i).getDay()+"\t"+bookings.get(i).getTime());
                    System.out.println("Booking Auto Rejected: "+bookings.get(i));
                }
            }
        }
        App.serialize(iiitd);
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
            AutoReject();
//            System.out.println("Somethings Wrong");
            UpdateBookingsRequests();
            UpdatePastBookings();
//            System.out.println("All correct");
            UpdateAvailableRoom();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
