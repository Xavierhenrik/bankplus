// como a migração para o banco de dados esta classe sera descontinuada
/*
package com.henrique.bankplus.bankplus.entities;





import java.util.ArrayList;

public class Bank {

    private ArrayList<Client> clients = new ArrayList<>();

    public Bank(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }


    // add client

    public void addClient(String name, String cpf, double balance,String initialDeposit){

        Client client = new Client(name, cpf, balance);
        this.clients.add(client);


    }

    // search client for CPF

   public Client searchClient(String cpf) {

    return clients.stream()

            .filter(c -> c.getCpf().equals(cpf))
            .findFirst()
            .orElse(null);
    
    }


    // list clients

    public void listClients(){
        for (Client c : clients){
            System.out.println(c);
        }
    }
        
    }
*/

