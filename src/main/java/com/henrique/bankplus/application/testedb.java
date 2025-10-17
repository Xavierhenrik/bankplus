package com.henrique.bankplus.application;
import com.henrique.bankplus.db.DB;
import com.henrique.bankplus.entities.Client;
import com.henrique.bankplus.DAO.ClientDAO;

import java.sql.Connection;
import java.util.Scanner;


public class testedb {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        conn = DB.getConnection();
        ClientDAO clientDAO = new ClientDAO(conn);

        // recebe o cpf
        System.out.println("Enter the client CPF:");
        String deletecpf = sc.nextLine();

        Client objClient = clientDAO.findByCpf(deletecpf); 

        // confirma a deleção
        System.out.println(objClient.toString());

        System.out.println("Are you sure you want to delete this client? (y/n)");
        String opDel = sc.nextLine().toUpperCase();

        if (opDel.equals(opDel)) {
            System.out.println("Client with Cpf " + deletecpf + " deleted successfully!");
            clientDAO.deleteClient(deletecpf);
        } 
        else {
            System.out.println("Deletion cancelled.");
        }










    DB.closeConnection();
    sc.close();
    }

}

