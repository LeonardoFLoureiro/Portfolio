import java.util.*;

import java.io.BufferedReader;

import java.io.FileInputStream;

import java.io.IOException;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
Autor: Leonardo Faina Loureiro
Atividade: TP0301

*/

class FILME {
    // DECLARAÇÃO DE ATRIBUTOS
    private String nome;
    private String tituloOriginal;
    private Date dataLancamento;
    private int duracao;
    private String genero;
    private String idiomaOriginal;
    private String situacao;
    private float orcamento;
    private String palavrasChaves[] = new String[50];

    // METODOS CONSTRUTORES
    FILME() {

    }

    FILME(String nome, String tituloOriginal, Date dataLancamento, int duracao, String genero, String idiomaOriginal,
            String situacao, float orcamento, String[] palavrasChaves) {

        this.nome = nome;
        this.tituloOriginal = tituloOriginal;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.genero = genero;
        this.idiomaOriginal = idiomaOriginal;
        this.situacao = situacao;
        this.orcamento = orcamento;
        this.palavrasChaves = palavrasChaves;

    }

    // METODOS GETTERS E SETTERS

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setIdiomaOriginal(String IdiomaOriginal) {
        this.idiomaOriginal = IdiomaOriginal;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    public float getOrcamento() {
        return orcamento;
    }

    public void setPalavrasChaves(String[] palavrasChaves) {
        this.palavrasChaves = palavrasChaves;
    }

    public String[] getPalavrasChaves() {
        return palavrasChaves;
    }

    // METODO REMOVER TAGS
    public static String removeTag(String in) {
        String resp = "";
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) == '<') {
                while (in.charAt(i) != '>')
                    i++;
            } else if (in.charAt(i) == '&') {
                while (in.charAt(i) != ';')
                    i++;
            }

            else {
                resp += in.charAt(i);
            }

        }
        return resp;
    }
    // METODO TRANSFORMAR HORAS PRA MINUTOS

    public int transformarHoras(String s) {
        int horas = 0;
        int tempo = 0;
        int minutos = 0;

        s = s.trim();
        s = s.replace(" ", "");
        if (s.contains("h")) {
            horas = Integer.parseInt(s.substring(0, s.indexOf("h")));
            s = s.substring(s.indexOf("h") + 1, s.length());
        }

        if (s.contains("m")) {
            minutos = Integer.parseInt(s.substring(0, s.indexOf("m")));
        }

        tempo = minutos + (horas * 60);

        return tempo;
    }

    // METODO LER
    public void lerArquivo(String ARQ) {

        try {
            InputStreamReader arqu = new InputStreamReader(new FileInputStream(ARQ), "UTF-8");
            // FileReader arqu = new FileReader(ARQ, Charset.forName("utf-8"));
            BufferedReader lerArq = new BufferedReader(arqu);

            String linha = lerArq.readLine();
            // NOME
            while (!linha.contains("h2 class"))
                linha = lerArq.readLine();
            linha = lerArq.readLine();

            linha = removeTag(linha).trim();

            this.nome = linha;
            // DATA LANCAMENTO
            while (!linha.contains("span class=\"release\""))
                linha = lerArq.readLine();
            linha = lerArq.readLine();

            linha = removeTag(linha).trim();

            linha = linha.split(" ")[0];

            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            this.dataLancamento = date.parse(linha);
            // GENERO
            while (!linha.contains("span class=\"genres\""))
                linha = lerArq.readLine();
            linha = lerArq.readLine();
            linha = lerArq.readLine();
            linha = removeTag(linha).trim();

            this.genero = linha;
            // TEMPO DO FILME
            while (!linha.contains("span class=\"runtime\""))
                linha = lerArq.readLine();

            linha = lerArq.readLine();
            linha = lerArq.readLine();

            linha = removeTag(linha).trim();

            this.duracao = transformarHoras(linha);

            // TITULO ORIGINAL

            while (!linha.contains("p class=\"wrap\"") && !linha.contains("<strong><bdi>Situação</bdi></strong>")) {
                if (!linha.contains("<strong><bdi>Situação</bdi></strong>")) {
                    linha = lerArq.readLine();

                } else {
                    break;
                }

            }
            String linhaSalva;
            linhaSalva = linha;

            if (linha.contains("<strong><bdi>Situação</bdi></strong>")) {
                linha = this.nome;

            }

            String temp = "";
            int i = 16;

            if (linha != this.nome) {
                linha = removeTag(linha).trim();

                while (i < linha.length()) {
                    temp += linha.charAt(i);
                    i++;
                }
            } else {
                temp = linha;
            }

            this.tituloOriginal = temp;

            // SITUACAO

            if (!linha.contains("<strong><bdi>Situação</bdi></strong>")
                    && !linhaSalva.contains("<strong><bdi>Situação</bdi></strong>")) {
                while (!linha.contains("<strong><bdi>Situação</bdi></strong>"))
                    linha = lerArq.readLine();

            }
            if (!linha.contains("<strong><bdi>Situação</bdi></strong>")) {
                linha = linhaSalva;
            }
            linha = removeTag(linha).trim();
            temp = "";
            i = 9;
            while (i < linha.length()) {
                temp += linha.charAt(i);
                i++;
            }
            temp = temp.trim();
            this.situacao = temp;

            // IDIOMA ORIGINAL
            while (!linha.contains("Idioma original"))
                linha = lerArq.readLine();

            linha = removeTag(linha).trim();
            temp = "";
            i = 15;
            while (i < linha.length()) {
                temp += linha.charAt(i);
                i++;
            }
            temp = temp.trim();
            this.idiomaOriginal = temp;

            // ORCAMENTO
            while (!linha.contains("Orçamento"))
                linha = lerArq.readLine();

            linha = removeTag(linha).trim();
            temp = "";
            i = 11;
            while (i < linha.length()) {
                temp += linha.charAt(i);

                i++;
            }
            temp = temp.trim();
            temp = temp.replaceAll(",", "");
            float orca;
            try {
                orca = Float.parseFloat(temp);
            } catch (NumberFormatException e) {
                orca = 0.0f;
            }
            this.orcamento = orca;
            // PALAVRAS CHAVE

            while (!linha.contains("Palavras-chave"))
                linha = lerArq.readLine();

            i = 0;
            do {

                while (!linha.contains("<li>") && !linha.contains("</section>")
                        && !linha.contains("Nenhuma palavra-chave foi adicionada.")) {
                    if (!linha.contains("</section>")) {
                        linha = lerArq.readLine();
                    } else {
                        break;
                    }

                }

                if (linha.contains("</section>")) {

                    break;
                }
                if (linha.contains("Nenhuma palavra-chave foi adicionada.")) {
                    this.palavrasChaves[0] = "0";

                    break;
                }

                linha = removeTag(linha).trim();

                this.palavrasChaves[i++] = linha;

            } while (!linha.contains("</section>"));

            lerArq.close();

        } catch (Exception e) {

            System.out.println("toString(): " + e.toString());
            System.out.println("getMessage(): " + e.getMessage());
            System.out.println("StackTrace: ");
            e.printStackTrace();
        }

    }

    // METODO IMPRIMIR

    public void Imprimir() {

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print(
                nome + " " + tituloOriginal + " " + date.format(dataLancamento) + " " + duracao + " " + genero + " "
                        + idiomaOriginal + " " + situacao + " " + orcamento);
        int i = 0;

        System.out.print(" [");
        if (this.palavrasChaves[0] != "0") {
            String batata = "";
            while (this.palavrasChaves[i] != null) {
                batata += this.palavrasChaves[i] + ", ";

                i++;

            }

            batata = batata.trim();
            batata = batata.substring(0, batata.length() - 1);
            System.out.print(batata);
        }
        System.out.println("]");

    }

    // METODO CLONE

    public FILME getFILME() {
        return this;
    }
}

class LISTA {
    private FILME lista[] = new FILME[1000];
     
    private int nLISTA = 0;
    private int comparacoes = 0;
    private int movimentacoes = 0;

    public int getmovimentacoes() {
        return movimentacoes;
    }

    public int getcomparacoes() {
        return comparacoes;
    }

    public int GetnLista() {

        return nLISTA;
    }

    LISTA() {

    }

    public void OrdenarRemocao(int pos) {

        if (pos != nLISTA) {
            lista[pos] = lista[pos + 1];
            OrdenarRemocao(pos + 1);
        }
    }

    public void OrdenarInsercao(int posInf, int posSup) {
        if (posInf != posSup + 1) {
            lista[posSup + 1] = lista[posSup];
            OrdenarInsercao(posInf, posSup - 1);
        }
    }

    public FILME RemoverInicio() {
        FILME temp = lista[0];
        OrdenarRemocao(0);
        nLISTA--;
        return temp;

    }

    public FILME RemoverFinal() {
        FILME temp = lista[nLISTA];
        nLISTA--;
        return temp;
    }

    public FILME Remover(int pos) {

        FILME temp = lista[pos];
        OrdenarRemocao(pos);
        nLISTA--;
        return temp;

    }

    public void InserirInicio(FILME filme) {
        OrdenarInsercao(0, nLISTA);
        lista[0] = filme;
        nLISTA++;
    }

    public void InserirFinal(FILME filme) {
        if (lista[0] != null) {

            nLISTA++;
            lista[nLISTA] = filme;
        } else {
            lista[0] = filme;
        }
    }

    public void Inserir(FILME filme, int pos) {

        OrdenarInsercao(pos, nLISTA);
        lista[pos] = filme;
        nLISTA++;

    }

    public void ImprimirFilme(int pos) {
        // System.out.print("[" + pos + "] ");

        lista[pos].Imprimir();
    }

    public void InsercaoSort() {
        FILME x = new FILME();
        int j;
        for (int i = 1; i <= nLISTA; i++) {
           // System.out.println("-------COMECO DO FOR-------" + lista[i].getNome()+ " "+ lista[i].getDataLancamento());
         //   for(int xu = 0;xu<=nLISTA;xu++)System.out.println("[ "+ xu + " ] " + lista[xu].getNome());
            x = lista[i];
            j = i - 1;
            
            while (j >= 0 && lista[j].getDataLancamento().compareTo(x.getDataLancamento()) > 0){
                lista[j + 1] = lista[j];
                movimentacoes++;
             //   System.out.println("XXXX" + lista[j].getNome() + " " + lista[j].getDataLancamento());
                j = j - 1;
                
                comparacoes++;
            }
            comparacoes++;
            
          //  System.out.println("OOOOOOOOOO  "+ lista[j+1].getNome() + ","+ x.getNome());
            lista[j + 1] = x;
            movimentacoes++;
            

         //   System.out.println("-----FIM DO FOR-------");
        }

    }
}

class principal {

    public static boolean isFIM(String s) {

        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String NovaString(String string, int posInf, int posSup) {
        String resultado = "";
        for (int j = posInf; j <= posSup; j++) {
            resultado += string.charAt(j);
        }
        return resultado;
    }

    public static void main(String[] args) throws ParseException, IOException {
        long tInicio = System.currentTimeMillis();
        String entrada[] = new String[1000];
        int numEntrada = 0;
        LISTA lista = new LISTA();
        Scanner sc = new Scanner(System.in, "UTF-8");
        do {
            entrada[numEntrada] = sc.nextLine();

        } while (isFIM(entrada[numEntrada++]) == false);
        numEntrada--;

        for (int i = 0; i < numEntrada; i++) {

            String arquivo = "/tmp/filmes/" + entrada[i];

            FILME teste = new FILME();

            teste.lerArquivo(arquivo);

            lista.InserirFinal(teste);

        }
        lista.InsercaoSort();
        for (int i = 0; i <= lista.GetnLista(); i++) {

            lista.ImprimirFilme(i);
        }

        sc.close();
        PrintWriter writer = new PrintWriter("matrícula_insercao.txt", "UTF-8");

        long tFinal = System.currentTimeMillis();
        long tResultado = tFinal - tInicio;

        writer.println(
                "727225" + "\t" + "\t" + lista.getcomparacoes() + "\t" + lista.getmovimentacoes() + "\t" + tResultado);
        writer.close();

    }

}
