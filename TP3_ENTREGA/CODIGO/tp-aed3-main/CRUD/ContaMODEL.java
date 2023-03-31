package CRUD;

import java.util.ArrayList;

public class ContaMODEL implements Comparable<ContaMODEL> {

    private int tam_registro;
    private int idConta;
    private boolean lapide; // True existe / False nao existe
    private short qtd_email;
    private ArrayList<String> email = new ArrayList<>();
    private String nomePessoa;
    private String nomeUsuario;
    private String senha;
    private String cpf;
    private String cidade;
    private int transferencias;
    private float saldo;

    public ContaMODEL() {

    }

    public ContaMODEL(/* int idConta, */
            String nomePessoa,
            ArrayList<String> email,
            String nomeUsuario,
            String senha,
            String cpf,
            String cidade,
            int transferencias,
            float saldo) {

        // setIdConta(idConta);
        setNomePessoa(nomePessoa);
        setEmail(email);
        setNomeUsuario(nomeUsuario);
        setSenha(senha);
        setCpf(cpf);
        setCidade(cidade);
        setTransferencias(transferencias);
        setSaldo(saldo);
        setLapide(true);
        setQtd_email((short) email.size());
        setTam_registro();

    }

    public ContaMODEL(int idConta,
            String nomePessoa,
            ArrayList<String> email,
            String nomeUsuario,
            String senha,
            String cpf,
            String cidade,
            int transferencias,
            float saldo,
            boolean lapide) {

        setIdConta(idConta);
        setNomePessoa(nomePessoa);
        setEmail(email);
        setNomeUsuario(nomeUsuario);
        setSenha(senha);
        setCpf(cpf);
        setCidade(cidade);
        setTransferencias(transferencias);
        setSaldo(saldo);
        setQtd_email((short) email.size());
        setTam_registro();
        // System.out.println("==contaMODEL construtor " + lapide);
        setLapide(lapide);
    }

    public int getTam_registro() {
        return tam_registro;
    }

    // private boolean lapide;

    // private short qtd_email;
    // private int idConta;
    // private int transferencias;
    // private float saldo;
    // private String email[];
    // private String nomePessoa;
    // private String nomeUsuario;
    // private String senha;
    // private String cpf;
    // private String cidade;

    public void setTam_registro() {
        int tam_email = 0;
        for (String s : email) {
            tam_email += s.getBytes().length;
        }
        this.tam_registro = nomePessoa.getBytes().length + 2
                + nomeUsuario.getBytes().length + 2
                + senha.getBytes().length + 2
                + cpf.getBytes().length + 2
                + cidade.getBytes().length + 2
                + tam_email
                + 2 * email.size()// short * qnt de email
                + 1 // boolean
                + 4 * 2// ints
                + 4// float
                + 2; // short qnt email
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public boolean getLapide() {
        return lapide;
    }

    public void setLapide(boolean lapide) {
        this.lapide = lapide;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public short getQtd_email() {
        return qtd_email;
    }

    public void setQtd_email(short qtd_email) {
        this.qtd_email = qtd_email;
    }

    public ArrayList<String> getEmail() {
        return email;
    }

    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getTransferencias() {
        return transferencias;
    }

    public void setTransferencias(int transferencias) {
        this.transferencias = transferencias;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    // private int tam_registro;
    // private int idConta;
    // private boolean lapide;
    // private short qtd_email;
    // private ArrayList<String> email = new ArrayList<>();
    // private String nomePessoa;
    // private String nomeUsuario;
    // private String senha;
    // private String cpf;
    // private String cidade;
    // private int transferencias;
    // private float saldo;

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n--ID: " + getIdConta() + "\n");
        s.append("--Nome: " + getNomePessoa() + "\n");
        s.append("--Username: " + getNomeUsuario() + "\n");
        s.append("--Senha: " + getSenha() + "\n");
        s.append("--Email: ");
        getEmail().forEach((e) -> s.append(e + "\n"));
        s.append("--Saldo: R$" + String.format("%.2f", getSaldo()) + "\n");

        s.append("--LAPIDE: " + getLapide() + "\n");

        return s.toString();
    }

    public String contaFormatToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getIdConta() + " ");
        sb.append(getNomePessoa() + " ");
        sb.append(getNomeUsuario() + " ");
        sb.append(getCpf() + " ");
        sb.append(getEmail() + " ");
        sb.append(getSenha() + " ");
        sb.append(getCidade() + " ");
        sb.append(String.format("%.2f", getSaldo()) + " ");
        sb.append(getTransferencias() + " ");

        return sb.toString();
    }

    @Override
    public int compareTo(ContaMODEL o) {
        if (this.getIdConta() > o.idConta) {
            return 1;
        }
        return -1;
    }

}