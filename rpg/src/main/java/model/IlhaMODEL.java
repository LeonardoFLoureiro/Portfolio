package model;

public class IlhaMODEL {
    String Nome;
    String Descricao;
    int x;
    int y;
    String img;
    int id;

    public IlhaMODEL() {

    }

    public IlhaMODEL(String Nome, String Descricao, int x, int y,String img,int id) {
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.x = x;
        this.y = y;
        this.img = img;
        this.id = id;
    }
    public IlhaMODEL(IlhaMODEL novailha){
        this.Nome = novailha.getNome();
        this.Descricao = novailha.getDescricao();
        this.x = novailha.getX();
        this.y = novailha.getY();
        this.img = novailha.getImg();
        this.id = novailha.getId();
        

    }
    public IlhaMODEL(String Nome, String Descricao, int x, int y,String img){
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.x = x;
        this.y = y;
        this.img = img;
        
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}
