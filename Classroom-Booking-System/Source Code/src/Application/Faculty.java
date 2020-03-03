/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.util.ArrayList;

/**
 *
 * @author Kaustav Vats (2106048)
 */
public class Faculty extends User {
    private TimeTable mytimetable;
    private ArrayList<Booking> bookings;
    public Faculty(String name, String id, String pw)
    {
        super(name,id,pw);
        this.mytimetable = null;
        this.bookings = new ArrayList<Booking>();
    }
    public ArrayList<Course> getCourses()
    {
        return mytimetable.getCourse();
    }
    @Override
    public void BookRoom(Booking booking)
    {
        bookings.add(booking);
    }
    public ArrayList<Booking> getbookings()
    {
        return this.bookings;
    }
    
    @Override
    public void CancelRoomBooking(Booking booking)
    {
        RemoveRequest(booking);
    }
    /*
    This methods removes booking objects that are made by Faculty.
    */
    public void RemoveRequest(Booking e)
    {
        for ( int i=0; i<bookings.size(); i++ )
        {
            if ( bookings.get(i).getDate().equals(e.getDate()) )
            {
                if ( bookings.get(i).getTime().equals(e.getTime()) )
                {
                    if ( bookings.get(i).getRoomNo().equals(e.getRoomNo()) )
                    {
                        bookings.remove(i);
                    }
                }
            }
        }
    }
    public TimeTable getTimeTable()
    {
        return this.mytimetable;
    }
    public void addTimeTable(TimeTable tt)
    {
        this.mytimetable = tt;
    }
}
