/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyExceptions;

/**
 *
 * @author Kaustav Vats (2016048)
 */
// Exception used if user already exists.
public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String mssg)
    {
        super(mssg);
    }
}
