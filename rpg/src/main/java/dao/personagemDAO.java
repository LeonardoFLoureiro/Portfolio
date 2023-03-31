/*package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.personagemMODEL;

public class personagemDAO {
    private static final personagemDAO INSTANCE = new personagemDAO();
    private Connection conexao;

    public personagemDAO() {
        conexao = null;
    }

    public static personagemDAO getConnectedInstance() {
        INSTANCE.conectar();
        return INSTANCE;
    }

    public boolean conectar() {
        if (conexao != null)
            return true;
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "postgres";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "postgres";
        String password = "postgres";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao == null);
            System.out.println("Conexão efetuada com o postgres!personagemMODELDAO");
        } catch (ClassNotFoundException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }

    public boolean close() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public boolean inserirpersonagemMODEL(personagemMODEL personagemMODEL) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();

            st.executeUpdate("INSERT INTO rpg.Ilha (nome,descricao,localizacaox,localizacaoy,img) "
                    + "VALUES ('" + personagemMODEL.getNome() + "', '" + personagemMODEL.getDescricao() + "', "
                    + personagemMODEL.getX() + ", " + personagemMODEL.getY() + ",'" + personagemMODEL.getImg() + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean atualizarpersonagemMODEL(personagemMODEL personagemMODEL) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE rpg.Ilha SET nome = '"
                    + personagemMODEL.getNome() + "', descricao = '" + personagemMODEL.getDescricao() + "', localizacaox = "
                    + personagemMODEL.getX() + ", localizacaoy = " + personagemMODEL.getY()
                    + " WHERE id = " + personagemMODEL.getId();
            st.executeUpdate(sql);
            st.close();
            status = true;

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean excluirpersonagemMODEL(int id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM rpg.Ilha WHERE id =" + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public personagemMODEL[] getALLpersonagemMODELs() {
        personagemMODEL[] personagemMODELs = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM rpg.Ilha");
            if (rs.next()) {
                rs.last();
                personagemMODELs = new personagemMODEL[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    personagemMODELs[i] = new personagemMODEL(rs.getString("nome"), rs.getString("descricao"),
                            rs.getInt("localizacaox"), rs.getInt("localizacaoy"), rs.getString("img"), rs.getInt("id"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return personagemMODELs;
    }

    public personagemMODEL getpersonagemMODEL(int id) {
        personagemMODEL personagemMODEL = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = st.executeQuery("SELECT * FROM rpg.ilha WHERE id =" + id);
            if (rs.next()) {
                personagemMODEL = new personagemMODEL(rs.getString("nome"), rs.getString("descricao"), rs.getInt("localizacaox"),
                        rs.getInt("localizacaoy"), rs.getString("img"), rs.getInt("id"));
            }

            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return personagemMODEL;

    }

}*/
