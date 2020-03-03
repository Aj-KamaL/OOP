/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.*;
import java.util.Date;
/**
 *
 * @author Kaustav Vats (2016048)
 */
public class Booking implements Serializable {
    private Room room;
    private String time;
    private String Date;
    private String Day;
    private String Reason;
    private String Status;
    private Date BookingDate;
    private String ID;
    
    public Booking(Room r, String t, String d, String day, String rea)
    {
        this.room = r;
        this.time = t;
        this.Date = d;
        this.Day = day;
        this.Reason = rea;
        this.Status = "Pending";
        this.BookingDate = null;
        this.ID = null;
    }
    public void setID(String s)
    {
        this.ID = s;
    }
    public String getID()
    {
        return this.ID;
    }
    public Date getBookingDate()
    {
        return this.BookingDate;
    }
    public void setBookingDate(Date d)
    {
        this.BookingDate = d;
    }
    public String getStatus()
    {
        return this.Status;
    }
    public Room getRoom()
    {
        return this.room;
    }
    public String getDate()
    {
        return this.Date;
    }
    public String getRoomNo()
    {
        return this.room.getRoomNo();
    }
    public String getDay()
    {
        return this.Day;
    }
    public String getTime()
    {
        return this.time;
    }
    public String getReason()
    {
        return this.Reason;
    }
    // Update the status of room as booked.
    public void Book()
    {
        this.Status = "Booked";
    }
    // Update the status of room as Rejected.
    public void Reject()
    {
        this.Status = "Rejected";
    }
    public int getMonth()
    {
        String month = Date.substring(3, 5);
        return Integer.parseInt(month);
    }
    public int getYear()
    {
        String year = Date.substring(6, 10);
        return Integer.parseInt(year);
    }
    public String toString()
    {
        return "Room: "+room.getRoomNo()+"\n"+"Time: "+time+"\n"+"Date: "+Date+"\n"+"Reason: "+Reason+"\n"+"Status: "+Status+"\n"+"Booking Date: "+BookingDate.toString();
    }
}
