package com.henrique.bankplus.utils;

import java.util.Scanner;

public class Validations {

    /**
     * Valida um CPF e retorna:
     * - mensagens de erro específicas para cada tipo de problema
     * - "x" se, após limpeza, houver exatamente 11 dígitos
     *
     * Observação: aqui não estou fazendo a validação do dígito verificador do CPF —
     * você pediu que se tivesse 11 dígitos retornasse "x". Se quiser validar o
     * checksum (dígitos), eu adiciono sem problema.
     */
    
    public static String validateCpf() {
        Scanner sc = new Scanner(System.in);
        String cpf;

        while (true) {
            cpf = sc.nextLine();

            String original = cpf;
            String cleaned = cpf.replaceAll("[\\.\\-\\s]", "");

            if (cpf == null || cpf.isEmpty()) {
                System.out.println("CPF cannot be empty");
                continue;
            }

            if (original.matches(".*[A-Za-z].*")) {
                System.out.println("CPF contains letters (invalid characters)");
                continue;
            }

            if (!cleaned.matches("\\d+")) {
                System.out.println("CPF contains invalid characters");
                continue;
            }

            if (cleaned.length() < 11) {
                System.out.println("CPF has less than 11 digits");
                continue;
            }

            if (cleaned.length() > 11) {
                System.out.println("CPF has more than 11 digits");
                continue;
            }

            if (cleaned.matches("(\\d)\\1{10}")) {
                System.out.println("CPF has all digits equal (invalid)");
                continue;
            }

            // Se chegou aqui, o CPF está correto
            System.out.println("CPF accepted!");
            return cleaned; // retorna o CPF limpo
        }
    }

    public static double validateAmount() {
        Scanner sc = new Scanner(System.in);
        double amount;

        while (true) {

            if (!sc.hasNextDouble()) {
                System.out.println("Invalid input, please enter a numeric value");
                sc.next(); // limpa a entrada inválida
                continue;
            }

            amount = sc.nextDouble();

            if (Double.isNaN(amount)) {
                System.out.println("Amount is not a number (NaN)");
                continue;
            }

            if (Double.isInfinite(amount)) {
                System.out.println("Amount is infinite (invalid value)");
                continue;
            }

            if (amount < 0) {
                System.out.println("Negative amount not allowed");
                continue;
            }

            if (amount == 0) {
                System.out.println("Amount cannot be zero");
                continue;
            }

            // Verifica se tem mais de duas casas decimais
            String text = String.valueOf(amount);
            if (text.contains(".")) {
                int decimalPlaces = text.length() - text.indexOf('.') - 1;
                if (decimalPlaces > 2) {
                    System.out.println("Amount has more than two decimal places");
                    continue;
                }
            }

            System.out.println("Amount accepted!");
            return amount;
        }
    }
}
