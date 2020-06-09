/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.Source;


/**
 *
 * @author Khoubaib
 */
public class SponsorService {
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    public SponsorService(){
        connection =(Connection) (Source.getInstance()).getConnection();
    }
     public int getSponsorById(int id) {
         
        String req = "select * from Sponsor where id= '" + id + "'";
        try {
            pst = (PreparedStatement) connection.prepareStatement(req);
            rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;

    }
    
    }

