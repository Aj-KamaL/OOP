/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.Serializable;

/**
 *
 * @author Kaustav Vats (2016048)
 */
public abstract class User implements Serializable {
    private final String Name;
    private final String Id;
    private final String Password;
    
    public User(String name, String id, String pw)
    {
        this.Name = name;
        this.Id = id;
        this.Password = pw;
    }
    public String getName()
    {
        return this.Name;
    }
    public String getId()
    {
        return this.Id;
    }
    @Override
    public String toString()
    {
        return this.Name+" "+this.Id;
    }
    // Checks if both users are same or not. If same returns true else false.
    public boolean Validate(String id, String pw)
    {
        if ( this.Id.equals(id) )
        {
            if ( this.Password.equals(pw) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    // Books room and add this objects to their past booking array.
    public abstract void BookRoom(Booking booking);
    
    // This method removes booking by removing same object from ArrayList<Booking>.
    public abstract void CancelRoomBooking(Booking booking);
}
