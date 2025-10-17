package com.henrique.bankplus.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.henrique.bankplus.DAO.ClientDAO;
import com.henrique.bankplus.entities.Client;

public class ClientService {

    private final Connection connection;
    private final ClientDAO clientDAO;

    public ClientService(Connection connection) {
        this.connection = connection;
        this.clientDAO = new ClientDAO(connection);
    }

    public void transfer(String senderCpf, String receiverCpf, double amount) {
        
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        boolean previousAutoCommit;
        try {
            previousAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            // Opcional: bloquear as linhas durante a transação para evitar corrida:
            // SELECT ... FOR UPDATE (se quiser, crie um método específico no DAO)
            Client sender   = clientDAO.findByCpf(senderCpf);
            Client receiver = clientDAO.findByCpf(receiverCpf);

            if (sender == null || receiver == null) {
                throw new IllegalArgumentException("Sender or receiver not found");
            }
            if (sender.getBalance() == null || sender.getBalance() < amount) {
                throw new IllegalStateException("Insufficient funds");
            }

            clientDAO.updateBalance(senderCpf,   sender.getBalance() - amount);
            clientDAO.updateBalance(receiverCpf, receiver.getBalance() + amount);

            connection.commit();
            connection.setAutoCommit(previousAutoCommit);
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ignore) {}
            throw new RuntimeException(e);
        }
    }
}