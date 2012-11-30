package com.wmp.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBHelper
{
    private DBHelper()
    {
    }

    private static String url = "jdbc:mysql://localhost:3306/wmp";
    private static String user = "root";
    private static String password = "wmpadmin";

    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, user, password);
    }

    public static void free(ResultSet rs, Statement st, Connection conn)
    {
        try
        {
            if (rs != null)
                rs.close();
        }
        catch (SQLException e)
        {
        }
        finally
        {
            try
            {
                if (st != null)
                    st.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (conn != null)
                        conn.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        try
        {
            JDBCUtilsTest();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void JDBCUtilsTest() throws Exception
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try
        {
            conn = DBHelper.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from user_number");
            while (rs.next())
            {
                System.out.println(rs.getObject(1) + "\t" + rs.getObject(2));
            }
        }
        finally
        {
            DBHelper.free(rs, st, conn);
        }

    }
}
