/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.util.ArrayList;

/**
 *
 * @author Kaustav Vats (2016048)
 */
public class Admin extends User{
    private ArrayList<Booking> bookings = new ArrayList<Booking>();
    private ArrayList<Booking> request = new ArrayList<Booking>();
    
    public Admin(String name, String id, String pw)
    {
        super(name,id,pw);
    }
    @Override
    public void BookRoom(Booking booking)
    {
        // Need to implement.
        bookings.add(booking);
    }
    
    @Override
    public void CancelRoomBooking(Booking booking)
    {
        removeBooking(booking);
    }
    public ArrayList<Booking> getBookings()
    {
        return this.bookings;
    }
    public void addBooking(Booking e)
    {
        bookings.add(e);
    }
    public ArrayList<Booking> getRequests()
    {
        return this.request;
    }
    public void addRequest(Booking e)
    {
        request.add(e);
    }
    /*
    This methods removes Booking object that are made by student.
    */
    public void removeRequest(Booking e)
    {
        for ( int i=0; i<request.size(); i++ )
        {
            if ( request.get(i).getDate().equals(e.getDate()) )
            {
                if ( request.get(i).getTime().equals(e.getTime()) )
                {
                    if ( request.get(i).getRoomNo().equals(e.getRoomNo()) )
                    {
                        request.remove(i);
                    }
                }
            }
        }
    }
    /* 
    This methods removes booking objects that are made by Admin.
    */
    public void removeBooking(Booking e)
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
    /*
    This method returns booking object with same details.
    */
    public Booking getRequestCopy(Booking e)
    {
        if ( e == null )
        {
            return null;
        }
        for ( int i=0; i<request.size(); i++ )
        {
            if ( request.get(i).getDate().equals(e.getDate()) )
            {
                if ( request.get(i).getTime().equals(e.getTime()) )
                {
                    if ( request.get(i).getRoomNo().equals(e.getRoomNo()) )
                    {
                        return request.get(i);
                    }
                }
            }
        }
        return null;
    }
    // Prints all request made by Students. Only for testing purpose.
    public void printRequests()
    {
        for ( int i=0; i<request.size(); i++ )
        {
            System.out.println(request.get(i));
        }
    }
}
