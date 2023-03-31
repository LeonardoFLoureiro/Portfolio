package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.LocalizacaoPostos;


public class LocalizacaoPostosDao {
private Connection conexao;
	int maxId = 0;
	public LocalizacaoPostosDao() {
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
			System.out.println("Conexão efetuada com o postgres!LOCALIZACAOPOSTODAO");
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
	
	public boolean inserirLocalizacaoPostos(LocalizacaoPostos LocPos) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO localizacaopostos (posto, id_localizacao, cidade, estado, bairro, rua, numero) "
					       + "VALUES ("+ LocPos.getposto()+ ", '" + LocPos.getid_localizacao() + "', '"  
					       + LocPos.getcidade() + "', '" + LocPos.getestado()+ "', '" +LocPos.getbairro()+ "', '" +LocPos.getrua()+ "', '" +LocPos.getnumero() +  "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarLocalizacaoPostos(LocalizacaoPostos LocPos) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE localizacaousuarios SET  posto ='" +LocPos.getposto()+ "', cidade = '" + LocPos.getcidade()+"', estado" + LocPos.getestado()+"', bairro" + LocPos.getbairro()+"', rua" + LocPos.getrua() +"', numero" + LocPos.getnumero()+"'"
					   + " WHERE id_localizaccao = " + LocPos.getid_localizacao();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirLocalizacaoPostos(int id_localizacao) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM localizacaopostos WHERE id_localizacao = " + id_localizacao);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public LocalizacaoPostos[] getLocalizacaoPostos() {
		LocalizacaoPostos[] localizacaopostos = new LocalizacaoPostos[0];
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM easygas.localizacaopostos");		
	         if(rs.next()){
	             rs.last();
	             localizacaopostos = new LocalizacaoPostos[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                localizacaopostos[i] = new LocalizacaoPostos(rs.getInt("posto"),rs.getInt("id_localizacao"),rs.getString("cidade"),rs.getString("estado"),rs.getString("bairro"),rs.getString("rua"),rs.getInt("numero"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return localizacaopostos; 
}
	public LocalizacaoPostos getLocalizacaoPostos(int id_localizacao) {
		LocalizacaoPostos locus = null;
		LocalizacaoPostos[] localizacaopostos = new LocalizacaoPostos[0];
		try {
		Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery("SELECT * FROM easygas.localizacaopostos");		
         if(rs.next()){
             rs.last();
             localizacaopostos = new LocalizacaoPostos[rs.getRow()];
             rs.beforeFirst();

             for(int i = 0; rs.next(); i++) {
            	 localizacaopostos[i] = new LocalizacaoPostos(rs.getInt("posto"), rs.getInt("id_localizacao"),rs.getString("cidade"),rs.getString("estado"),rs.getString("bairro"),rs.getString("rua"),rs.getInt("numero"));
                if(localizacaopostos[i].getid_localizacao() == id_localizacao) {
                	locus = localizacaopostos[i];
                	break;
                }
             }
          }
          st.close();
	} catch (Exception e) {
		System.err.println(e.getMessage());
	}
	return locus;
		
		
	
	
	}
}
