package com.crianto.candidatocard.candidatocard.util;

import org.hibernate.validator.constraints.br.CPF;

import java.util.InputMismatchException;

public class Funcoes {

    public static String somenteNumeros(String campo) {

        return campo.replaceAll("[^0-9]", "");
    }

    //https://www.devmedia.com.br/validando-cartao-de-credito-cpf-e-cnpj/1923
    public static String validaCartao(String numero) {

        String numCartao = somenteNumeros(numero);
        String numString;

        int soma = 0;

        //Cartão com seqüências de caracteres menor ou igual a 15 dígitos
        if (numCartao.length() <= 15) {

            for (int i = 0; i <= numCartao.length(); i++) {
                numString = (numCartao.substring(i, i + 1));

                if (i % 2 == 0) {
                    soma += (Integer.parseInt(numString));
                } else {
                    if ((Integer.parseInt(numString) * 2) > 9) {
                        soma += ((Integer.parseInt(numString) * 2) - 9);
                    } else {
                        soma += ((Integer.parseInt(numString) * 2));
                    }
                }
            }
        }

        //Cartão com seqüências de caracteres maior ou igual a 16 dígitos
        if (numCartao.length() >= 16) {

            for (int i = 0; i < numCartao.length(); i++) {
                numString = (numCartao.substring(i, i + 1));

                if (i % 2 == 0) {
                    if ((Integer.parseInt(numString) * 2) > 9) {
                        soma += ((Integer.parseInt(numString) * 2) - 9);
                    } else {
                        soma += ((Integer.parseInt(numString) * 2));
                    }
                } else {
                    soma += (Integer.parseInt(numString));
                }
            }
        }

        if (soma % 10 == 0) {
            return numCartao;
        } else {
            return "invalido";
        }
    }

    // https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
    public static String cpfValidado(String numeroCpf) {
        String cpf = somenteNumeros(numeroCpf);

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
                cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
                cpf.equals("99999999999") || (cpf.length() != 11))
            return "inválido";

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;

            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;

            for(i=0; i<10; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return(cpf);
            else
                return "inválido";
        } catch (InputMismatchException erro) {
            return "inválido";
        }
    }

//    public static String imprimeCPF(String CPF) {
//        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
//                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
//    }
}
