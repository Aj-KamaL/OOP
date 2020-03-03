/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Kaustav Vats (2016048)
 */
public class Course implements Serializable {
    private String Type;
    private final String Code;
    private final String Name;
    private String Acronym;
    private String Credit;
    private String Faculty;
    private ArrayList<String> Timings;
    private ArrayList<String> rooms;
    private ArrayList<String> PostConditions;
    
    public Course(String code ,String name, String f)
    {
        this.Code = code;
        this.Name = name;
        this.Faculty = f;
        this.Timings = new ArrayList<String>(7);
        this.rooms = new ArrayList<String>(7);
        this.PostConditions = new ArrayList<String>();
        for ( int i=0; i<7; i++ )
        {
            Timings.add("-");
            rooms.add("-");
        }
    }
    public void setAcronym(String e)
    {
        this.Acronym = e;
    }
    public String getAcronym()
    {
        return this.Acronym;
    }
    public void setCredit(String e)
    {
        this.Credit = e;
    }
    public String getCredit()
    {
        return this.Credit;
    }
    public String getRoomOnDay(int i)
    {
        return rooms.get(i);
    }
    public String getTimeOnDay(int i)
    {
        return Timings.get(i);
    }
    public void setType(String type)
    {
        this.Type = type;
    }
    public String getType()
    {
        return this.Type;
    }
    public String getCode()
    {
        return this.Code;
    }
    public String getName()
    {
        return this.Name;
    }
    public String getFaculty()
    {
        return this.Faculty;
    }
    public String getMonday()
    {
        return Timings.get(0)+" "+rooms.get(0);
    }
    public String getTuesday()
    {
        return Timings.get(1)+" "+rooms.get(1);
    }
    public String getWednesday()
    {
        return Timings.get(2)+" "+rooms.get(2);
    }
    public String getThursday()
    {
        return Timings.get(3)+" "+rooms.get(3);
    }
    public String getFriday()
    {
        return Timings.get(4)+" "+rooms.get(4);
    }
    public ArrayList<String> getTimes()
    {
        return this.Timings;
    }
    // This method add Course details in its own timetable.
    public void addTiming(int day, String time, String room)
    {
        Timings.add(day,time);
        rooms.add(day,room);
    }
    public ArrayList<String> getPostCondition()
    {
        return this.PostConditions;
    }
    public void addPostCondition(String c)
    {
        PostConditions.add(c);
    }
    @Override
    public String toString()
    {
        return "Type: "+Type+"\n"+"Code: "+Code+"\n"+"Credits: "+Credit+"\n"+"Course Name: "+Name+"\n"+"Instructor: "+Faculty+"\n"+"Monday Timings: "+Timings.get(0)+" "+rooms.get(0)+"\n"+"Tuesday Timings: "+Timings.get(1)+" "+rooms.get(1)+"\n"+"Wednesday Timings: "+Timings.get(2)+" "+rooms.get(2)+"\n"+"Thursday Timings: "+Timings.get(3)+" "+rooms.get(3)+"\n"+"Friday Timings: "+Timings.get(4)+" "+rooms.get(4)+"\n"+"\n";
    }
}
