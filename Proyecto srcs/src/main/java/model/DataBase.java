package model;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {

  public static void loadDatabase() {
    //If the database doesnt exists we create it
    String url = "jdbc:sqlite:./dictionary.db";

    try (Connection connection = DriverManager.getConnection(url)) {
      if (connection != null) {

        DatabaseMetaData meta = connection.getMetaData();
        System.out.println("The driver name is " + meta.getDriverName());
        //System.out.println("A new database has been created.");

        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        //statement.executeUpdate("create table catalog (id INTEGER PRIMARY KEY AUTOINCREMENT, title string, extract string, source integer)");
        statement.executeUpdate("create table IF NOT EXISTS catalog (id INTEGER, title string PRIMARY KEY, extract string, source integer)");
        //If the DB was created before, a SQL error is reported but it is not harmfull...

        //Creo la tabla
       // statement.executeUpdate("CREATE TABLE IF NOT EXISTS scores (title TEXT PRIMARY KEY, score INTEGER, lastModificationDate DATE)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS scores(title string PRIMARY KEY, score INTEGER, lastModificationDate date)");
        System.out.println("entra a ejecutarse scores");
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void testDB()
  {
    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      //statement.executeUpdate("drop table if exists person");
      //statement.executeUpdate("create table person (id integer, name string)");
      //statement.executeUpdate("insert into person values(1, 'leo')");
      //statement.executeUpdate("insert into person values(2, 'yui')");
      ResultSet rs = statement.executeQuery("select * from catalog");
      while(rs.next())
      {
        // read the result set
        System.out.println("id = " + rs.getInt("id"));
        System.out.println("title = " + rs.getString("title"));
        System.out.println("extract = " + rs.getString("extract"));
        System.out.println("source = " + rs.getString("source"));

      }
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
  }

  public static ArrayList<String> getTitles()
  {
    ArrayList<String> titles = new ArrayList<>();
    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      ResultSet rs = statement.executeQuery("select * from catalog");
      while(rs.next()) titles.add(rs.getString("title"));
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
      return titles;
    }
  }

  public static void saveInfo(String title, String extract)
  {
    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");

      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      System.out.println("INSERT  " + title + "', '"+ extract);

      statement.executeUpdate("replace into catalog values(null, '"+ title + "', '"+ extract + "', 1)");
    }
    catch(SQLException e)
    {
      System.err.println("Error saving " + e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println( e);
      }
    }
  }

  public static String getExtract(String title)
  {
    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      ResultSet rs = statement.executeQuery("select * from catalog WHERE title = '" + title + "'" );
      rs.next();
      return rs.getString("extract");
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println("Get title error " + e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
    return null;
  }

  public static void deleteEntry(String title)
  {

    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      statement.executeUpdate("DELETE FROM catalog WHERE title = '" + title + "'" );

    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println("Get title error " + e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
  }
  public static void saveScore(String title, Integer score){
    Connection connection = null;

    try {
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();

      statement.setQueryTimeout(30);  // set timeout to 30 sec.
      statement.executeUpdate("replace into scores values('"+ title + "', "+ score + ", datetime('now'))");

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    finally {
      try {
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }
  public static String getScores(String title) {
    Connection connection = null;

    try {
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      String query = "SELECT score FROM scores WHERE title = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, title);
      ResultSet rs = preparedStatement.executeQuery();

      rs.next();
      if(rs.getString("score") != null)
        return rs.getString("score");
      else
        return "-1";

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
    //return score;
  }
  public static Date getDates(String title) {
    Connection connection = null;
    Date date = null;

    try {
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();

      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      // Execute the query to get the score for the given title
      ResultSet rs = statement.executeQuery("SELECT lastModificationDate FROM scores WHERE title = '" + title + "'");

      // Process the result set
      if (rs.next()) {
        date = rs.getDate("lastModificationDate");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
    return date;
  }
  public static ArrayList<String> getTitlesScores()
  {
    ArrayList<String> scoreTitles = new ArrayList<>();
    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      ResultSet rs = statement.executeQuery("select * from scores");
      while(rs.next()) scoreTitles.add(rs.getString("title"));
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
      return scoreTitles;
    }
  }
}
