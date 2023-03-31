package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 import model.Posto;


public class PostoDao {
	private Connection conexao;
	private int maxId = 0;
	
	public PostoDao() {
		conexao = null;
	}
	public int getMaxId() {
		return maxId;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "postgres";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "postgres";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!POSTODAO");
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
	
	public boolean inserirPosto(Posto posto) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO easygas.postos (cpf, primeironome, segundonome, senha, datanascimento, confiabilidade) "
					       + "VALUES ("+ posto.getid_posto()+ ", '" + posto.getmarca() + "', '"  
					       + posto.getvalorgasolina() + "', '" + posto.getvaloretanol() +  "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarPosto(Posto posto) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE postos SET  marca = '" + posto.getmarca()+ "', valorgasolina = '" + posto.getvalorgasolina()+"'"
					   + " WHERE id_posto = " + posto.getid_posto();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirPosto(int id_posto) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM postos WHERE id_posto = " + id_posto);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Posto[] getPosto() {
		Posto[] postos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM postos");		
	         if(rs.next()){
	             rs.last();
	             postos = new Posto[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                postos[i] = new Posto(rs.getInt("id_posto"),rs.getString("marca"),rs.getString("valorgasolina"),rs.getString("valoretanol"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return postos;
	}
	public Posto getPosto(int id_posto) {
		Posto posto = null;
		Posto[] postos = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM easygas.postos");		
			if(rs.next()){
				rs.last();
				postos = new Posto[rs.getRow()];
				rs.beforeFirst();

				for(int i = 0; rs.next(); i++) {
					postos[i] = new Posto(rs.getInt("id_posto"), rs.getString("marca"),rs.getString("valorgasolina"),rs.getString("valoretanol"));
					if(postos[i].getid_posto() == id_posto) {
                	posto = postos[i];
                	break;
					}
				}
			}
          st.close();
		} catch (Exception e) {
		System.out.println("toString(): " + e.toString());
        System.out.println("getMessage(): " + e.getMessage());
        System.out.println("StackTrace: ");
        e.printStackTrace();
	}
	return posto;
		
		
	
	
	}
}
