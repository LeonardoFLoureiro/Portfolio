package model;

public class personagemMODEL {
    int id;
    String nome;
    String descricao;
    int forca;
    int constituicao;
    int destreza;
    int furtividade;
    int resistencia;
    int percepcao;
    //
    int espadalonga;
    int espadacurta;
    int arremesso;
    int arcoeflecha;
    int armasdemira;
    int martelo_machadogrande;
    int lanca;
    int artesmarciais;
    //
    int canhoes;
    int balistas;
    //
    int culinaria;
    int medicina;
    int navegacao;
    int construcaonaval;
    int biologia;
    int carpintaria;
    int pesca;
    int ferraria;
    int sabedoria_livros;
    int explosivos;

    public personagemMODEL() {

    }

    public personagemMODEL(int id, String nome, String descricao, int forca, int constituicao, int destreza,
            int furtividade, int resistencia,
            int percepcao,
            int espadalonga, int espadacurta, int arremesso, int arcoeflecha, int armasdemira,
            int martelo_machadogrande, int lanca, int artesmarcias, int canhoes, int balistas, int culinaria,
            int medicina, int navegacao, int construcaonaval, int biologia, int carpintaria, int pesca,
            int ferraria, int sabedoria_livros, int explosivos) {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
        this.forca = forca;
        this.constituicao = constituicao;
        this.destreza = destreza;
        this.furtividade = furtividade;
        this.resistencia = resistencia;
        this.percepcao = percepcao;
        this.espadalonga = espadalonga;
        this.espadacurta = espadacurta;
        this.arremesso = arremesso;
        this.arcoeflecha = arcoeflecha;
        this.armasdemira = armasdemira;
        this.martelo_machadogrande = martelo_machadogrande;
        this.lanca = lanca;
        this.artesmarciais = artesmarcias;
        this.canhoes = canhoes;
        this.balistas = balistas;
        this.culinaria = culinaria;
        this.medicina = medicina;
        this.navegacao = navegacao;
        this.construcaonaval = construcaonaval;
        this.biologia = biologia;
        this.carpintaria = carpintaria;
        this.pesca = pesca;
        this.ferraria = ferraria;
        this.sabedoria_livros = sabedoria_livros;
        this.explosivos = explosivos;

    }

    public int getArcoeflecha() {
        return arcoeflecha;
    }

    public int getArmasdemira() {
        return armasdemira;
    }

    public int getArremesso() {
        return arremesso;
    }

    public int getArtesmarciais() {
        return artesmarciais;
    }

    public int getBalistas() {
        return balistas;
    }

    public int getBiologia() {
        return biologia;
    }

    public int getCanhoes() {
        return canhoes;
    }

    public int getCarpintaria() {
        return carpintaria;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public int getConstrucaonaval() {
        return construcaonaval;
    }

    public int getCulinaria() {
        return culinaria;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getEspadacurta() {
        return espadacurta;
    }

    public int getEspadalonga() {
        return espadalonga;
    }

    public int getExplosivos() {
        return explosivos;
    }

    public int getFerraria() {
        return ferraria;
    }

    public int getForca() {
        return forca;
    }

    public int getFurtividade() {
        return furtividade;
    }

    public int getLanca() {
        return lanca;
    }

    public int getMartelo_machadogrande() {
        return martelo_machadogrande;
    }

    public int getMedicina() {
        return medicina;
    }

    public int getNavegacao() {
        return navegacao;
    }

    public int getPercepcao() {
        return percepcao;
    }

    public int getPesca() {
        return pesca;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getSabedoria_livros() {
        return sabedoria_livros;
    }

    public void setArcoeflecha(int arcoeflecha) {
        this.arcoeflecha = arcoeflecha;
    }

    public void setArmasdemira(int armasdemira) {
        this.armasdemira = armasdemira;
    }

    public void setArremesso(int arremesso) {
        this.arremesso = arremesso;
    }

    public void setArtesmarciais(int artesmarciais) {
        this.artesmarciais = artesmarciais;
    }

    public void setBalistas(int balistas) {
        this.balistas = balistas;
    }

    public void setBiologia(int biologia) {
        this.biologia = biologia;
    }

    public void setCanhoes(int canhoes) {
        this.canhoes = canhoes;
    }

    public void setCarpintaria(int carpintaria) {
        this.carpintaria = carpintaria;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public void setConstrucaonaval(int construcaonaval) {
        this.construcaonaval = construcaonaval;
    }

    public void setCulinaria(int culinaria) {
        this.culinaria = culinaria;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setEspadacurta(int espadacurta) {
        this.espadacurta = espadacurta;
    }

    public void setEspadalonga(int espadalonga) {
        this.espadalonga = espadalonga;
    }

    public void setExplosivos(int explosivos) {
        this.explosivos = explosivos;
    }

    public void setFerraria(int ferraria) {
        this.ferraria = ferraria;
    }

    public void setFurtividade(int furtividade) {
        this.furtividade = furtividade;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setLanca(int lanca) {
        this.lanca = lanca;
    }

    public void setMartelo_machadogrande(int martelo_machadogrande) {
        this.martelo_machadogrande = martelo_machadogrande;
    }

    public void setMedicina(int medicina) {
        this.medicina = medicina;
    }

    public void setNavegacao(int navegacao) {
        this.navegacao = navegacao;
    }

    public void setPercepcao(int percepcao) {
        this.percepcao = percepcao;
    }

    public void setPesca(int pesca) {
        this.pesca = pesca;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setSabedoria_livros(int sabedoria_livros) {
        this.sabedoria_livros = sabedoria_livros;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
