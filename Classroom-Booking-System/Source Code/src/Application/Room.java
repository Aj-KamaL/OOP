/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Kaustav Vats (2016048)
 */
public class Room implements Serializable {
    private final String RoomNo;
    private final int Capacity;
    private ArrayList<String> bookings;
    
    public Room(String room, int cap)
    {
        this.RoomNo = room;
        this.Capacity = cap;
        bookings = new ArrayList<String>();
    }
    @Override
    public String toString()
    {
        return this.RoomNo+"\n"+this.Capacity+"\n";
    }
    public String getRoomNo()
    {
        return this.RoomNo;
    }
    public int getCapacity()
    {
        return this.Capacity;
    }
    public void addBooking(String e)
    {
        bookings.add(e);
    }
    public ArrayList<String> getBookings()
    {
        return bookings;
    }
    public void deleteBooking(String e)
    {
        for ( int i=0; i<bookings.size(); i++ )
        {
            if ( e.equals(bookings.get(i)) )
            {
                bookings.remove(i);
                break;
            }
        }
    }
    /*
    This Algo Check if both time intersect with each other or not.
    I used this method while booking the room.
    */
    public boolean isOverlap(String time1, String time2)
    {
        if ( time1.substring(0, 2).compareTo(time2.substring(0, 2)) <= 0 )
        {
            if ( time2.substring(0, 2).compareTo(time1.substring(6, 8)) < 0 )
            {
//                System.out.println(time2.substring(0, 2)+" "+time1.substring(6, 8));
//                System.out.println(time2.substring(0, 2).compareTo(time1.substring(6, 8)));
//                System.out.println("1");
                return true;
            }
            else if ( time2.substring(0, 2).compareTo(time1.substring(6, 8)) == 0 )
            {
                if ( time2.substring(3, 5).compareTo(time1.substring(9, 11)) < 0 )
                {
//                    System.out.println("2");
                    return true;
                }
                else
                {
//                    System.out.println("3");
                    return false;
                }
            }
            else
            {
//                System.out.println("4");
                return false;
            }
        }
        else
        {
            if ( time1.substring(0, 2).compareTo(time2.substring(6, 8)) < 0  )
            {
//                System.out.println("5");
                return true;
            }
            else if ( time1.substring(0, 2).compareTo(time2.substring(6, 8)) == 0 )
            {
                if ( time1.substring(3, 5).compareTo(time2.substring(9, 11)) < 0 )
                {
//                    System.out.println("6");
                    return true;
                }
                else
                {
//                    System.out.println("7");
                    return false;
                }
            }
            else
            {
//                System.out.println("8");
                return false;
            }
        }
    }
}
