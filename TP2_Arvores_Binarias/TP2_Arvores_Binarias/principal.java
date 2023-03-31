import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

class No {

    FILME elemento;
    No esq;
    No dir;

    No(FILME elemento) {
        this.elemento = elemento;
        esq = null;
        dir = null;
    }

    No(FILME elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }

}

class Arvore {

    No raiz;
    int comparacoes;

    Arvore() {
        comparacoes = 0;
        raiz = null;
    }

    void inserir(FILME x) throws Exception {
        raiz = inserir(x, raiz);
    }

    No inserir(FILME x, No i) throws Exception {

        if (i == null) {
            i = new No(x);
            comparacoes++;
        } else if (x.getTituloOriginal().compareTo(i.elemento.getTituloOriginal()) < 0) {
            i.esq = inserir(x, i.esq);
            comparacoes+=2;
        } else if (x.getTituloOriginal().compareTo(i.elemento.getTituloOriginal()) > 0) {
            i.dir = inserir(x, i.dir);
            comparacoes+=3;
        } else {
            comparacoes+=4;
            throw new Exception("Erro");
        }
        return i;
    }

    No inserir(FILME x, No i, No pai) throws Exception {

        if (i == null) {
            comparacoes+=1;
            if (x.getTituloOriginal().compareTo(pai.elemento.getTituloOriginal()) < 0) {
                pai.esq = new No(x);
                comparacoes+=1;
            } else {
                pai.dir = new No(x);
                comparacoes+=2;
            }
        } else if (x.getTituloOriginal().compareTo(i.elemento.getTituloOriginal()) < 0) {
            comparacoes+=2;
            inserir(x, i.esq, i);
        } else if (x.getTituloOriginal().compareTo(i.elemento.getTituloOriginal()) > 0) {
            comparacoes+=3;
            inserir(x, i.dir, i);
        } else {
            comparacoes+=4;
            throw new Exception("Erro");
        }
        return i;
    }

    boolean pesquisar(String x) {

        System.out.print("=>raiz ");
        return pesquisar(x, raiz);
    }

    boolean pesquisar(String x, No i) {

        boolean resp;
        if (i == null) {
            comparacoes+=1;
            resp = false;
        } else if (x.trim().contains(i.elemento.getTituloOriginal().trim())) {
            comparacoes+=2;
            resp = true;
        } else if (x.trim().compareTo(i.elemento.getTituloOriginal().trim()) < 0) {
            comparacoes+=3;
            System.out.print("esq ");
            resp = pesquisar(x, i.esq);
        } else if (x.trim().compareTo(i.elemento.getTituloOriginal().trim()) > 0) {
            comparacoes+=4;
            System.out.print("dir ");
            resp = pesquisar(x, i.dir);
        } else {
            comparacoes+=5;
            resp = pesquisar(x, i.dir);
        }
        /*
         * if(i != null && x != null){
         * 
         * System.out.println("LALA" + x + "  " + i.elemento.getTituloOriginal());
         * }
         */
        return resp;
    }

    void remover(String x) throws Exception {
        raiz = remover(x, raiz);
    }

    No remover(String x, No i) throws Exception {
        if (i == null) {
            comparacoes+=1;
            throw new Exception("Erro");
        } else if (x.compareTo(i.elemento.getTituloOriginal()) < 0) {
            comparacoes+=2;
            i.esq = remover(x, i.esq);
        } else if (x.compareTo(i.elemento.getTituloOriginal()) > 0) {
            comparacoes+=3;
            i.dir = remover(x, i.dir);
        } else if (i.dir == null) {
            comparacoes+=4;
            i = i.esq;
        } else if (i.esq == null) {
            comparacoes+=5;
            i = i.dir;
        } else {
            comparacoes+=6;
            i.esq = maiorEsq(i, i.esq);
        }
        return i;
    }

    No maiorEsq(No i, No j) {
        if (j.dir == null) {
            comparacoes+=1;
            i.elemento = j.elemento;
            j = j.esq;

        } else {
            comparacoes+=2;
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

}

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

    public static void main(String[] args) throws Exception {
        long tInicio = System.currentTimeMillis();
        Arvore arvore = new Arvore();
        Scanner sc = new Scanner(System.in, "UTF-8");
        String entrada[] = new String[1000];
        int numEntrada = 0;
        int Nlimite = 0;

        do {
            entrada[numEntrada] = sc.nextLine();

        } while (isFIM(entrada[numEntrada++]) == false);
        numEntrada--;

        for (int i = 0; i < numEntrada; i++) {

            String arquivo = "./tmp/filmes/" + entrada[i];

            FILME teste = new FILME();

            teste.lerArquivo(arquivo);

            arvore.inserir(teste);

        }

        Nlimite = Integer.parseInt(sc.nextLine());
        numEntrada = 0;
        String entrada2 = "";
        do {

            entrada2 = sc.nextLine();
            if (entrada2.charAt(0) == 'I') {

                entrada2 = NovaString(entrada2, 2, entrada2.length() - 1);
                String arquivo = "./tmp/filmes/" + entrada2;
                FILME teste = new FILME();
                teste.lerArquivo(arquivo);
                arvore.inserir(teste);

            } else if (entrada2.charAt(0) == 'R') {
                entrada2 = NovaString(entrada2, 2, entrada2.length() - 1);

                arvore.remover(entrada2);
            }
            numEntrada++;

        } while (numEntrada < Nlimite);

        numEntrada = 0;
        entrada[0] = sc.nextLine();
        do {
            

            System.out.println(entrada[0]);
            if (arvore.pesquisar(entrada[0])) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            entrada[0] = sc.nextLine();
        } while (isFIM(entrada[0]) == false);

        sc.close();
        PrintWriter writer = new PrintWriter("matrícula_arvoreBinaria.txt", "UTF-8");

        long tFinal = System.currentTimeMillis();
        long tResultado = tFinal - tInicio;

        writer.println(
                "727225" + "\t" + tResultado + "\t" + arvore.comparacoes);
        writer.close();
    }
}