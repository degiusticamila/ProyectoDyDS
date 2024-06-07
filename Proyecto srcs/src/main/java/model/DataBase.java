package model;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {

  public static void loadDatabase() {
    String url = "jdbc:sqlite:./dictionary.db";

    try (Connection connection = DriverManager.getConnection(url)) {
      if (connection != null) {

        DatabaseMetaData meta = connection.getMetaData();
        System.out.println("The driver name is " + meta.getDriverName());

        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        statement.executeUpdate("create table IF NOT EXISTS catalog (id INTEGER, title string PRIMARY KEY, extract string, source integer)");


        statement.executeUpdate("CREATE TABLE IF NOT EXISTS scores(title string PRIMARY KEY, score INTEGER, lastModificationDate date)");

      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static ArrayList<String> getTitles()
  {
    ArrayList<String> titles = new ArrayList<>();
    Connection connection = null;
    try
    {
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      ResultSet rs = statement.executeQuery("select * from catalog");
      while(rs.next()) titles.add(rs.getString("title"));
    }
    catch(SQLException e)
    {
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
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");

      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

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
        System.err.println( e);
      }
    }
  }

  public static String getExtract(String title)
  {
    Connection connection = null;
    try
    {
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      ResultSet rs = statement.executeQuery("select * from catalog WHERE title = '" + title + "'" );
      rs.next();
      return rs.getString("extract");
    }
    catch(SQLException e)
    {
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
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      statement.executeUpdate("DELETE FROM catalog WHERE title = '" + title + "'" );
    }
    catch(SQLException e)
    {
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
        System.err.println(e);
      }
    }
  }
  public static void saveScore(String title, Integer score){
    Connection connection = null;

    try {
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();

      statement.setQueryTimeout(30);
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
  }
  public static Date getDates(String title) {
    Connection connection = null;
    Date date = null;

    try {
      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();

      statement.setQueryTimeout(30);

      ResultSet rs = statement.executeQuery("SELECT lastModificationDate FROM scores WHERE title = '" + title + "'");

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

      connection = DriverManager.getConnection("jdbc:sqlite:./dictionary.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      ResultSet rs = statement.executeQuery("select * from scores");
      while(rs.next()) scoreTitles.add(rs.getString("title"));
    }
    catch(SQLException e)
    {
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
        System.err.println(e);
      }
      return scoreTitles;
    }
  }
}
