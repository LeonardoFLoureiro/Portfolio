package TP3;

public class CasamentoPadrao {

    public static String KMP(String padrao, String txt) {

        StringBuilder sb = new StringBuilder();
        sb.append("padrao encontrado no index: ");

        int padraoLength = padrao.length();
        int txtLength = txt.length();
        int maiorPrefixSufixo[] = new int[padraoLength];
        int indexPadrao = 0;

        preencherArrayPrefixoSufixo(padrao, padraoLength, maiorPrefixSufixo);

        int indexTxt = 0;
        while ((txtLength - indexTxt) >= (padraoLength - indexPadrao)) {
            if (padrao.charAt(indexPadrao) == txt.charAt(indexTxt)) {
                indexPadrao++;
                indexTxt++;
            }
            if (indexPadrao == padraoLength) {
                sb.append((indexTxt - indexPadrao) + " . ");
                indexPadrao = maiorPrefixSufixo[indexPadrao - 1];
            }

            else if (indexTxt < txtLength
                    && padrao.charAt(indexPadrao) != txt.charAt(indexTxt)) {
                if (indexPadrao != 0)
                    indexPadrao = maiorPrefixSufixo[indexPadrao - 1];
                else
                    indexTxt = indexTxt + 1;
            }
        }

        return sb.toString();
    }

    private static void preencherArrayPrefixoSufixo(String padrao, int M, int maiorPrefixSufixo[]) {
        int tam = 0;
        maiorPrefixSufixo[0] = 0;

        for (int i = 1; i < M;) {
            if (padrao.charAt(i) == padrao.charAt(tam)) {
                tam++;
                maiorPrefixSufixo[i] = tam;
                i++;
            } else {
                if (tam != 0) {
                    tam = maiorPrefixSufixo[tam - 1];
                } else {
                    maiorPrefixSufixo[i] = tam;
                    i++;
                }
            }
        }
    }

}
