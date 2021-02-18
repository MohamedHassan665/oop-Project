/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class Connect
{
    private final String user="root";
    private final String pass="";
    private final String add="jdbc:mysql://localhost/database";
    public Connection connectt() throws SQLException
    {
        Connection c=DriverManager.getConnection(add,user,pass);
        return c;
    }
}
