package com.henrique.bankplus.utils;

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
    public static String validateCpf(String cpf) {
        if (cpf == null) {
            return "CPF nulo";
        }

        String original = cpf; // só para referência ou logs, se precisar
        // remove pontos, traços e espaços comuns (aceitamos esses separadores)
        String cleaned = cpf.replaceAll("[\\.\\-\\s]", "");

        // Se havia letras (ex.: "123a45..."), detectamos isso pelo original
        if (original.matches(".*[A-Za-z].*")) {
            return "CPF contém letras (caracteres inválidos)";
        }

        // agora cleaned deve conter apenas dígitos; se tiver outros símbolos, erro
        if (!cleaned.matches("\\d+")) {
            return "CPF contém caracteres inválidos";
        }

        // tamanho
        if (cleaned.length() < 11) {
            return "CPF com menos de 11 dígitos";
        }
        if (cleaned.length() > 11) {
            return "CPF com mais de 11 dígitos";
        }

        // caso todos os dígitos sejam iguais (00000000000, 11111111111, ...) — CPF inválido
        if (cleaned.matches("(\\d)\\1{10}")) {
            return "CPF com todos os dígitos iguais (inválido)";
        }

        // Se chegou aqui, cleaned.length() == 11
        return "x";
    }
}
