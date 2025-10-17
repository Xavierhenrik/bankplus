package com.henrique.bankplus.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.henrique.bankplus.db.DB;
import com.henrique.bankplus.entities.Client;
import java.sql.ResultSet;


public class ClientDAO {

    private Connection conn;

    public ClientDAO(Connection conn) {
        this.conn = conn;
    }



    // insert client
    public void insertClient(Client obj) {

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "INSERT INTO clients (name, cpf, balance) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);

                st.setString(1, obj.getName());
                st.setString(2, obj.getCpf());
                st.setDouble(3, obj.getBalance());

                st.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {
            DB.closeStatement(st);
        }

    }

    // update balance -- ainda incompleta 
    public void updateBalance(String cpf, double newBalance) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "UPDATE clients "
                + "SET balance = ? "
                + "WHERE cpf = ?"
            );
    
            st.setDouble(1, newBalance);
            st.setString(2, cpf);
    
            st.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
        }
    }

    // deleta cliente buscando pelo cpf 
    public void deleteClient(String cpf) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "DELETE FROM clients "
                + "WHERE cpf = ?");

                st.setString(1, cpf);
            
            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);
        } 
        catch (SQLException e) {

            e.printStackTrace();

        }
        finally{
            DB.closeStatement(st);
        }
    }

    // Retorna um objeto cliente
    public Client findByCpf(String cpf) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT * FROM clients WHERE cpf = ?"
            );
            st.setString(1, cpf);
            rs = st.executeQuery();

            if (rs.next()) {
                Client obj = new Client();
                obj.setName(rs.getString("name"));
                obj.setCpf(rs.getString("cpf"));
                obj.setBalance(rs.getDouble("balance"));
                return obj;
            } else {
                return null; // Cliente não encontrado
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }


}