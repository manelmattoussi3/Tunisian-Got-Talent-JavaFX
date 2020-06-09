/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Baleina
 */
public final class UserSession {
    
    private static UserSession instance;
    
    private int id;
    
    public UserSession(int id)
    {
        this.id = id;
    }
    
    public static UserSession getInstance(int id)
    {
        if(instance == null)
        {
            instance = new UserSession(id);
        }
        
        return instance;
    }

    public int getId() {
        return id;
    }
    
    public void cleanUserSession()
    {
        id = 0;
        
    }
    
    public String toString()
    {
        return "UserSession{"+"id"+id+"}";
    }
}
