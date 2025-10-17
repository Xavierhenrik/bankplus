package com.henrique.bankplus.db;

import java.sql.Statement;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

    // Define a conexão como nula
    private static Connection conn = null;

    // Inicia uma conexão
    public static Connection getConnection(){
        if (conn == null){
            try {
            
                Properties props = loadProprieties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
        
            catch(SQLException e) {
                throw new com.henrique.bankplus.db.DbException(e.getMessage());
            }
        }
        return conn;
    }

    // fechar a conexão
    public static void closeConnection() {

        if (conn != null){
            try {

                conn.close();
                
            } catch (SQLException e) {
                throw new com.henrique.bankplus.db.DbException(e.getMessage());
            }
        }
    }

    // carrega as propriedades de uma concexão
    private static Properties loadProprieties(){
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e ){
            throw new com.henrique.bankplus.db.DbException(e.getMessage());
        }

    }

    public static void closeStatement (Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new com.henrique.bankplus.db.DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet (ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
