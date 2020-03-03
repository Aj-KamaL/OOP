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
public class TimeTable implements Serializable {
    private ArrayList<Course> Courses;
    private ArrayList<String> Monday;
    private ArrayList<String> Tuesday;
    private ArrayList<String> Wednesday;
    private ArrayList<String> Thursday;
    private ArrayList<String> Friday;
    
    public TimeTable()
    {
        this.Courses = new ArrayList<Course>();
        this.Monday = new ArrayList<String>();
        this.Tuesday = new ArrayList<String>();
        this.Wednesday = new ArrayList<String>();
        this.Thursday = new ArrayList<String>();
        this.Friday = new ArrayList<String>();
    }
    public ArrayList<String> getMonday()
    {
        return this.Monday;
    }
    public ArrayList<String> getTuesday()
    {
        return this.Tuesday;
    }
    public ArrayList<String> getWednesday()
    {
        return this.Wednesday;
    }
    public ArrayList<String> getThursday()
    {
        return this.Thursday;
    }
    public ArrayList<String> getFriday()
    {
        return this.Friday;
    }
    public void addCourse(Course c)
    {
        Courses.add(c);
    }
    public ArrayList<Course> getCourse()
    {
        return this.Courses;
    }
    /*
    This method sorts all array according to course timings and the days.
    */
    public void UpdateDailyArrays()
    {
        for ( int i=0; i<Courses.size(); i++ )
        {
            Course c = Courses.get(i);
            System.out.println("Course Name: "+c.getName());
            
            String[] s = c.getMonday().split(" ");
            if ( !s[0].equals("-") )
            {
                String z = c.getName()+"\n"+s[0]+"\n"+s[1];
                Monday.add(z);
            }
            
            s = c.getTuesday().split(" ");
            if ( !s[0].equals("-") )
            {
                String z = c.getName()+"\n"+s[0]+"\n"+s[1];
                Tuesday.add(z);
            }
            
            s = c.getWednesday().split(" ");
            if ( !s[0].equals("-") )
            {
                String z = c.getName()+"\n"+s[0]+"\n"+s[1];
                Wednesday.add(z);
            }
            
            s = c.getThursday().split(" ");
            if ( !s[0].equals("-") )
            {
                String z = c.getName()+"\n"+s[0]+"\n"+s[1];
                Thursday.add(z);
            }
            
            s = c.getFriday().split(" ");
            if ( !s[0].equals("-") )
            {
                String z = c.getName()+"\n"+s[0]+"\n"+s[1];
                Friday.add(z);
            }
        }
        Collections.sort(Monday, new DateCompare());
        Collections.sort(Tuesday, new DateCompare());
        Collections.sort(Wednesday, new DateCompare());
        Collections.sort(Thursday, new DateCompare());
        Collections.sort(Friday, new DateCompare());
    }
}
class DateCompare implements Comparator<String>{
    @Override
    public int compare(String e1, String e2) {
        String[] s1 = e1.split("\n");
        String time1 = s1[1].substring(0, 5);
        
        String[] s2 = e2.split("\n");
        String time2 = s2[1].substring(0, 5);
//        System.out.println("time1 "+time1+" "+"time2 "+time2);
        if ( time1.substring(0, 2).compareTo(time2.substring(0, 2)) == 0 )
        {
            return time1.substring(3, 5).compareTo(time2.substring(3, 5));
        }
        else
        {
            return time1.substring(0, 2).compareTo(time2.substring(0, 2));
        }
    }
}
