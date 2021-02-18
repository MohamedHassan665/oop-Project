package oop_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oop_project.Connect;
public class Admin
{

    private Scanner input = new Scanner(System.in);
    private Connection connection;
    private Statement statement;
    private Connect connect = new Connect();
    private ResultSet resultSet;
    private String query2;
    private String query;


    public Admin()
    {
        try
        {
            connection = connect.connectt();
            statement = connection.createStatement();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void Add_stadium(String name, String loctaion, int seat_capacity)
    {
        try
        {
            statement.execute("insert into stadium values('" + name + "','" + seat_capacity + "','" + loctaion + "')");

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }


    public void add_list_of_matches()
    {
        try
        {
            String stadname;
            int ssn=0;
            System.out.println("Number of Matches ");
            int numberOfMatches = input.nextInt();
            for (int i = 0; i < numberOfMatches; i++)
            {
                System.out.println("enter time");
                String time = input.next();
                System.out.println("enter match_id");
                int match_id = input.nextInt();
                System.out.println("enter match_date");
                String date = input.next();
                System.out.println("Is Stadium avalaible?(y/n)");
                char answer = input.next().charAt(0);
                if (answer == 'y')
                {
                    System.out.println("enter Stadium name");
                    input.nextLine();
                    stadname = input.nextLine();
                }
                else
                {
                    stadname=null;
                }

                System.out.println("Is Referee avalaible?(y/n)");
                answer = input.next().charAt(0);
                if (answer == 'y')
                {
                    System.out.println("Enter SSN OF Referee ");
                    ssn = input.nextInt();
                }

                System.out.println("enter first team");
                String first_team = input.next();
                System.out.println("enter second team");
                String Second_team = input.next();


                statement.execute("insert into matche (Time,Match_id ,Match_date,SSN,Stadename,Frist_Team,Second_Team)values('" + time + "','" + match_id + "','" + date + "','" + ssn + "','" + stadname + "','" + first_team + "','" + Second_team + "')");


            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    void Update_stadium()
    {
        try
        {



            System.out.println("Enter Column That should be Updated");
            String column_Name = input.next();
            column_Name=column_Name.toLowerCase();
            if (column_Name.charAt(0)=='c')
            {
                System.out.println("Enter The Name Of The Stadium");
                String Stadium_Name = input.next();

                System.out.println("Enter New Value");
                int New_Value = input.nextInt();
                query = "update  stadium set Capacity ='" + New_Value + "' "
                        + "where Stadium_Name='" + Stadium_Name + "' ";
                statement.execute(query);

            }
            else if(column_Name.charAt(0)=='l')
            {
                System.out.println("Enter The Name Of The Stadium");
                String Stadium_Name = input.next();

                System.out.println("Enter New Value");
                String New_Value = input.next();
                query = "update  stadium set loctaion='" + New_Value + "' "
                        + "where Stadium_Name='" + Stadium_Name + "' ";
                statement.execute(query);
            }
            else
            {
                System.out.println("Enter Old Value ");
                input.nextLine();
                String Old_Value = input.nextLine();
                System.out.println("Enter New Value Of");
                String New_Value = input.next();
                query = "select * from stadium where Stadium_Name='" + Old_Value + "'";
                resultSet = statement.executeQuery(query);
                resultSet.next();
                int capicty = resultSet.getInt("Capacity");
                String loction = resultSet.getString("loctaion");
                query = "insert into stadium values('" + New_Value + "','" + capicty + "','" + loction + "')";
                statement.execute(query);
                query = "update matche set stadename='" + New_Value + "' where stadename='" + Old_Value + "'";
                statement.execute(query);
                query = "delete from   stadium  where Stadium_Name='" + Old_Value + "'";
                statement.execute(query);

            }

            statement.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    void delete_Stadium()
    {
        try
        {



            System.out.println("Enter The Name Of The Stadium");
            String Stadium_Name = input.next();

            statement.execute("update matche set stadename=NULL where stadename='" + Stadium_Name + "'");

            statement.execute("delete from  stadium  where Stadium_Name='" + Stadium_Name + "'");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void delete_match()
    {
        try
        {





            System.out.println("please enter id of match you want to delete");
            int id = input.nextInt();
            query2 = "delete from matche where Match_id='" + id + "'";
            statement.execute(query2);

        }
        catch (SQLException se)
        {
            System.out.println(se.getMessage());
        }

    }

    public void add_referee()
    {
        try
        {
            System.out.println("enter referee first name");
            String first_name = input.next();
            System.out.println("enter referee last name");
            String last_name = input.next();
            System.out.println("enter referee height");
            float height2 = input.nextInt();
            System.out.println("enter referee weight");
            float weight2 = input.nextInt();
            System.out.println("enter referee SSN");
            int ssn = input.nextInt();
            statement.execute("insert into referee values('" + ssn + "','" + first_name + "',"
                              + "'" + last_name + "','" + height2 + "','" + weight2 + "')");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void update_Referee()
    {
        try
        {
            System.out.println("Enter Column That should be Updated");
            String column_Name = input.next();
            if (!"SSN".equals(column_Name))
            {
                System.out.println("Enter SSN of The Referee");
                int ssn = input.nextInt();

                System.out.println("Enter New Value Of Column   " + column_Name);
                String New_Value = input.next();
                query = "update  referee set " + column_Name + "='" + New_Value + "' "
                        + "where SSN ='" + ssn + "' ";
                statement.execute(query);

            }
            else
            {
                System.out.println("Enter Old SSN");

                int Old_Value = input.nextInt();
                System.out.println("Enter New SSN  ");
                int New_Value = input.nextInt();
                query = "select * from referee where SSN='" + Old_Value + "'";
                resultSet = statement.executeQuery(query);
                resultSet.next();
                String First_Name = resultSet.getString("Referee_firstName");
                String Second_Name = resultSet.getString("Referee_LastName");
                float Height = resultSet.getFloat("Referee_height");
                float Weihgt = resultSet.getFloat("Referee_weight");
                query = "insert into referee values('" + New_Value + "','" + First_Name + "','" + Second_Name + "','" + Height + "'"
                        + ",'" + Weihgt + "')";
                statement.execute(query);
                query = "update matche set SSN='" + New_Value + "' where SSN ='" + Old_Value + "'";
                statement.execute(query);
                query = "delete from  referee  where SSN ='" + Old_Value + "'";
                statement.execute(query);

            }

            statement.close();

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void delete_Referee()
    {
        try
        {
            System.out.println("enter referee SSN");
            int ssn = input.nextInt();
            statement.execute("update matche set SSN=NULL where SSN ='" + ssn + "'");
            statement.execute("delete from referee where SSN='" + ssn + "' ");

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void Add_Team()
    {
        try
        {
            System.out.println("please enter name of team");
            String name = input.next();
            query = "insert into team values('" + name + "')";
            statement.execute(query);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void add_player()
    {

        try
        {
            System.out.println("please enter name of team");
            String name = input.next();
            System.out.println("please enter number of players you want to enserted");
            int num = input.nextInt();
            for (int i = 0; i < num; i++)
            {
                System.out.println("please enter player number");
                int player_num = input.nextInt();
                System.out.println("please enter player name");
                String player_name = input.next();
                System.out.println("please enter the player height");
                float player_height = input.nextFloat();
                System.out.println("please enter player weight ");
                float player_weight = input.nextFloat();
                query2 = "insert into player values('" + player_name + "','" + player_height + "','" + player_weight + "','" + name + "','" + player_num + "')";
                statement.execute(query2);
                System.out.println("Inserted done");
            }
        }
        catch (SQLException se)
        {
            System.out.println(se.getMessage());
        }
    }
    public void  upDateMatch()
    {
        try
        {
            System.out.println("Enter number of column , You want to modify it ");
            System.out.println("1- SSN of Referee ");
            System.out.println("2- Time Match");
            System.out.println("3- Stadium Match");
            System.out.println("4- Result Match ");
            System.out.println("5- ID Match");


            char Choose=input.next().charAt(0);


            if (Choose=='1')
            {

                System.out.println("Enter Id Match");
                int IdMatch=input.nextInt();

                System.out.println("Enter SSN Referee");

                int SSNReferee=input.nextInt();
                statement.execute("update matche set SSN='" + SSNReferee + "'where Match_id='" + IdMatch + "'" );

            }

            else if (Choose=='2')
            {

                System.out.println("Enter Id Match");
                int IdMatch=input.nextInt();


                System.out.println("Enter Date of Match (Year ,Month,Day)");
                String date=input.next();


                System.out.println("Enter Time  Match (Hour: Minute) ");
                String time=input.next();
                statement.execute("update matche set Time='" + time+ "' and Match_dat='"+date+"' where Match_id='" + IdMatch + "'" );

            }



            else   if (Choose=='3')
            {
                System.out.println("Enter Id Match");
                int IdMatch=input.nextInt();

                System.out.println("Enter Name of Stadium ");
                String Stadium =input.nextLine();
                statement.execute("update matche set stadename='" + Stadium+ "' where Match_id='" + IdMatch + "'" );

            }

            else   if (Choose=='4')
            {
                System.out.println("Enter Id Match");
                int IdMatch=input.nextInt();

                System.out.println(" Enter Result Match");
                String Result=input.nextLine();

                statement.execute("update matche set Result ='" + Result+ "' where Match_id='" + IdMatch + "'" );

            }
            else   if (Choose=='5')
            {
                System.out.println("Enter Id Match");
                int IdMatch=input.nextInt();

                System.out.println("Enter New Id ");
                int NewIdMatch=input.nextInt();
                statement.execute("update matche set Match_id='" + IdMatch+ "' where Match_id='" + IdMatch + "'" );

            }



        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }


    }
    public void update_Player(int id, String name1, String Team_Name)
    {
        try
        {
            statement.execute("update player set Player_name='" + name1 + "'where Player_num='" + id + "' and Teame_name='" + Team_Name + "'");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void update_Player(float height, int id, String Team_Name)
    {
        try
        {
            statement.execute("update player set player_height='" + height + "'where player_num='" + id + "' and Teame_name='" + Team_Name + "'");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void update_Player(int id, String Team_Name, float weight)
    {
        try
        {
            statement.execute("update player set Player_weight='" + weight + "'where Player_num='" + id + "' and Teame_name='" + Team_Name + "'");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void update_Player(String New_Team, int id, String old_Team)
    {
        try
        {
            statement.execute("update player set Teame_name='" + New_Team + "'where Player_num='" + id + "' and Teame_name='" + old_Team + "'");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void update_Player(int new_id, int old_id, String Team)
    {
        try
        {
            statement.execute("update player set Player_num='" + new_id + "'where Player_num='" + old_id + "' and Teame_name='" + Team + "'");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void delete_Player()
    {
        try
        {

            int id;

            System.out.println("Enter player Number");
            id = input.nextInt();
            System.out.println("Enter player Team Name");
            String Name = input.next();
            statement.execute("delete from player  WHERE player_num='" + id + "' and Teame_name='" + Name + "' ");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void update_team()
    {
        try
        {
            System.out.println("please enter the new name of team");
            String new_name=input.next();
            System.out.println("please enter the old name of team");
            String team=input.next();
            query2 = "insert into team values('" + new_name+ "')";
            statement.execute(query2);
            query2="update matche set Frist_Team= '"+new_name+"' where Frist_Team='"+team+"' ";
            statement.execute(query2);
            query2="update matche set Second_Team= '"+new_name+"' where Second_Team='"+team+"' ";
            statement.execute(query2);
            delete_team(team);
        }
        catch(SQLException se)
        {
            System.out.println(se.getMessage());
        }

    }
    public void delete_team(String team)
    {
        try
        {
            query2="delete from team where Team_name='"+team+"'";
            statement.execute(query2);
            query2="delete from matche where Frist_Team='"+team+"' OR Second_Team='"+team+"'";
            statement.execute(query2);
            System.out.println("deleted done");

        }

        catch(SQLException se)
        {
            System.out.println(se.getMessage());
        }
    }
}
