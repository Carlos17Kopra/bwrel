//CopyRight at Carlos17Kopra | Arvid

package de.carlos17kopra.bwrel.util.sql;

import java.sql.*;

public class SQLConnection {

    private String host;
    private int port;
    private String pw;
    private String database;
    private String currentStatement;
    private String userName;
    private Connection con;

    public SQLConnection(String host, String port,String userName, String pw, String database) {
        this.host = host;
        this.port = Integer.parseInt(port);
        this.pw = pw;
        this.database = database;
        this.userName = userName;
    }


    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, userName,pw);
        } catch (SQLException e) {
            System.out.println("MySQL error!");
            e.printStackTrace();
        }
    }
    public void Disconnect() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connectIfNot() {
        if(!isConnected()) {
            connect();
        }
    }

    public boolean isConnected() {
        //return (con == null ? false : true);
        try {
            if(con.isClosed()) {
                return false;
            }else {
                return true;
            }
        }catch (SQLException e) {
            return false;
        }
    }

    public void setStatement(String satement) {
        this.currentStatement = satement;
    }

    public void executeUpdate() {
        connectIfNot();
        try {
            Statement st = con.createStatement();
            st.executeUpdate(currentStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeInsert() {
        connectIfNot();
        try {
            Statement st = con.createStatement();
            st.executeUpdate(currentStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet(){
        connectIfNot();
        try {
            Statement st = con.createStatement();
            st.executeQuery(currentStatement);
            return st.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }

}
