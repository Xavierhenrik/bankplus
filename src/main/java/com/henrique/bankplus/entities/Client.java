package com.henrique.bankplus.entities;

public class Client {

    private String name;
    private String cpf;
    private Double balance;
        


    public Client(){

    }

    public Client(String name, String cpf, Double ballance) {
        this.name = name;
        this.cpf = cpf;
        this.balance = ballance;
    }

    public Client(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double ballance) {
        this.balance = ballance;
    }

    @Override
    public String toString() {
        return "Name = " + name + "\nCPF = " + cpf + "\nBallance = " + balance;
    }

    
    
    

}
