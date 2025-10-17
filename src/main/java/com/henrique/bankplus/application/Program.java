package com.henrique.bankplus.application;

import java.util.Scanner;

import com.henrique.bankplus.DAO.ClientDAO;
import com.henrique.bankplus.db.DB;

import java.sql.Connection;

import java.util.Locale;
import com.henrique.bankplus.entities.Client;
import com.henrique.bankplus.service.ClientService;
import com.henrique.bankplus.utils.Validations;


public class Program {
    public static void main(String[] args) {
        
        Connection conn = DB.getConnection();
        ClientDAO clientDAO = new ClientDAO(conn);
       

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        // Criar instância do banco


        while (true) {
            
            // option menu
            System.out.println("|=============================|");
            System.out.println("|      Welcome to BanK+!      |");
            System.out.println("|=============================|");
            System.out.println("|   What do you wanto to do?  |");
            System.out.println("|-----------------------------|");
            System.out.println("|   1 - Add client            |");
            System.out.println("|   2 - Delete client         |");
            System.out.println("|   3 - Internal transaction  |");
            System.out.println("|   4 - Deposit               |");
            System.out.println("|   5 - Withdraw              |");
            System.out.println("|   6 - See balance           |");
            System.out.println("|   7 - Lista all clients     |");
            System.out.println("|   0 - Exit                  |");
            System.out.println("|_____________________________|");
            int op = sc.nextInt();
            System.out.println();


            switch (op) {
                
                case 1:

                    System.out.println("Type the client data: ");
                    String name, cpf;
                    double balance = 0.0;

                    //recebe  e valida o nome
                    while (true) {
                        System.out.println("Name: ");
                        name = sc.nextLine();
                        if (name.length() > 3 && name.matches("[A-Za-zÀ-ÿ ]+"))
                            break;
                        else 
                            System.out.println("Ivalid name, Try again!\n Make sure it contains only letters.");

                    }
                    

                    System.out.println("CPF: ");
                    cpf = Validations.validateCpf();


                    while (true) {
                        
                        System.out.println("Would you like to make an initial deposit?");
                        String opId = sc.nextLine().toUpperCase();

                        if (opId.equals("Y")) {

                            System.out.println("Enter the deposit amount: ");
                            balance = Validations.validateAmount();

                        }
                            break;
                    }

                    clientDAO.insertClient(new Client(name, cpf, balance));
                    
                    break;

                case 2:

                    // recebe o cpf
                    System.out.println("Enter the client CPF:");
                    String cpfToDelete = Validations.validateCpf();

                    Client objClient = clientDAO.findByCpf(cpfToDelete);

                    // confirma a deleção
                    System.out.println(objClient.toString());

                    System.out.println("Are you sure you want to delete this client? (y/n)");
                    String opDel = sc.nextLine().toUpperCase();

                    if (opDel.equals(opDel)) {
                        System.out.println("Client with Cpf " + cpfToDelete + " deleted successfully!");
                        clientDAO.deleteClient(cpfToDelete);
                    } 
                    else {
                        System.out.println("Deletion cancelled.");
                    }

                    break;
                
                case 3:

                    
                    ClientService clientService = new ClientService(conn);

                    System.out.println("Enter the CPF of the account to be credited");
                    String senderCpf = Validations.validateCpf();

                    System.out.println("Enter the CPF of the account to be debited");
                    String reciverCpf = Validations.validateCpf();

                    System.out.println("Enter the transfer amount");
                    Double transferAmount = Validations.validateAmount();

                    clientService.trasnference(reciverCpf, senderCpf, transferAmount);



                    break;
                default:
                    break;
            }
            

            
            
            
            
            
            
            
            break;

        }

        sc.close();
        DB.closeConnection();
    }

    

}
