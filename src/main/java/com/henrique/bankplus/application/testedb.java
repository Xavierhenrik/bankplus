package com.henrique.bankplus.application;
import com.henrique.bankplus.db.DB;
// import com.henrique.bankplus.entities.Client;
import com.henrique.bankplus.service.ClientService;
import com.henrique.bankplus.utils.Validations;
// import com.henrique.bankplus.DAO.ClientDAO;

import java.sql.Connection;
import java.util.Scanner;


public class testedb {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        conn = DB.getConnection();


        ClientService clientService = new ClientService(conn);

        System.out.println("Enter the CPF of the account to be debited");
        String senderCpf = Validations.validateCpf();

        System.out.println("Enter the CPF of the account to be credited");
        String reciverCpf = Validations.validateCpf();

        System.out.println("Enter the transfer amount");
        Double transferAmount = Validations.validateAmount();

        clientService.trasnference(reciverCpf, senderCpf, transferAmount);










        DB.closeConnection();
        sc.close();
    }


}

