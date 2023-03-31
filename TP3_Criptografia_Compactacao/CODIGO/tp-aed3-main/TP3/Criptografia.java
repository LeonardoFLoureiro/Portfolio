package TP3;

public class Criptografia {

    //cifra de ceasar

    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
    private static final int DESLOCAMENTO = 4;

    public static String criptografar(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            boolean isMaisculo = false;
            if (Character.isUpperCase(s.charAt(i))) {
                isMaisculo = true;
            }
            int pos = ALFABETO.indexOf(s.charAt(i));
            int chaveChar = (DESLOCAMENTO + pos) % 26;
            char novoChar = ALFABETO.charAt(chaveChar);

            if (ALFABETO.indexOf(Character.toLowerCase(s.charAt(i))) == -1) {
                sb.append(s.charAt(i));
            } else if (isMaisculo) {
                sb.append(Character.toUpperCase(novoChar));
            } else {
                sb.append(novoChar);
            }
        }

        return sb.toString();
    }

    public static String descriptografar(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            boolean isMaisculo = false;
            if (Character.isUpperCase(s.charAt(i))) {
                isMaisculo = true;
            }

            int pos = ALFABETO.indexOf(Character.toLowerCase(s.charAt(i)));

            int chaveChar = (pos - DESLOCAMENTO) % 26;

            if (chaveChar < 0) {
                chaveChar = ALFABETO.length() + chaveChar;
            }
            char novoChar = ALFABETO.charAt(chaveChar);

            if (ALFABETO.indexOf(Character.toLowerCase(s.charAt(i))) == -1) {
                sb.append(s.charAt(i));
            } else if (isMaisculo) {
                sb.append(Character.toUpperCase(novoChar));
            } else {
                sb.append(novoChar);
            }
        }
        return sb.toString();
    }
}
