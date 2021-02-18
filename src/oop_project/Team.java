/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class Team
{

    private Connection connect;
    private Statement statement;
    private String Query;
    private Connect con=new Connect();
    private  ResultSet resultSet ;

    public Team()
    {
        try
        {
            connect= con.connectt();
            statement = connect.createStatement();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void GetTeams()
    {

        try
        {
            Query= "select* from team";
            resultSet = statement.executeQuery(Query);
            System.out.println("Team Name");
            while (resultSet.next())
            {
                System.out.println(resultSet.getString("Team_name"));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

}
