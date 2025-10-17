package com.henrique.bankplus.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.henrique.bankplus.DAO.ClientDAO;
import com.henrique.bankplus.entities.Client;

public class ClientService {

    private Connection conn;

    public ClientService(Connection conn) {
        this.conn = conn;
    }

    public void trasnference(String receiverCpf, String senderCpf, Double amountTransfer){

            


        try {
            
            conn.setAutoCommit(false); // inicia transação manual

            ClientDAO clientDAO = new ClientDAO(conn);
            Client sender = clientDAO.findByCpf(senderCpf);
            Client receiver = clientDAO.findByCpf(receiverCpf);

             // valida se o remetente existe
            if (sender == null) {
                throw new SQLException("Sender not found with CPF: " + senderCpf);
            }

            // valida se o destinatário existe
            if (receiver == null) {
                throw new SQLException("Receiver not found with CPF: " + receiverCpf);
            }

            // valida saldo suficiente
            if (sender.getBalance() < amountTransfer) {
                throw new SQLException("Insufficient funds for CPF: " + senderCpf);
            }

            // calcula novos saldos
            double newSenderBalance = sender.getBalance() - amountTransfer;
            double newReceiverBalance = receiver.getBalance() + amountTransfer;

            clientDAO.updateBalance(senderCpf, newSenderBalance);
            clientDAO.updateBalance(receiverCpf, newReceiverBalance);

            conn.commit(); // confirma tudo de uma vez

        } catch (SQLException e) {
            try {

                conn.rollback(); // desfaz todas as alterações
                System.err.println("Transaction rolled back due to an error.");
            } 
            catch (SQLException rollbackEx) {

                rollbackEx.printStackTrace();

            }
        } finally {

            try {
                conn.setAutoCommit(true); // volta ao modo padrão
            } 
            catch (SQLException e) {

                e.printStackTrace();

            }
        }
    }
}