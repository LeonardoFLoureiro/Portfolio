package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.LocalizacaoPostos;
import model.LocalizacaoUsuarios;


public class LocalizacaoUsuariosDao {
private Connection conexao;

	public LocalizacaoUsuariosDao() {
		conexao = null;
	}
	public int getMaxId() throws SQLException {
		LocalizacaoUsuarios locus = null;
		LocalizacaoUsuarios[] localizacaousuarios = null;
		int teste = 0;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT MAX(id_localizacao) FROM easygas.localizacaousuarios");
			
			if(rs.next()){
				rs.last();
				teste = rs.getInt(1);
				
			}
		} catch (Exception e) {
			System.out.println("toString(): " + e.toString());
	         System.out.println("getMessage(): " + e.getMessage());
	         System.out.println("StackTrace: ");
	         e.printStackTrace();
		}
		return teste;
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
			System.out.println("Conexão efetuada com o postgres!LOCALUSUDAO");
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
	
	public boolean inserirLocalizacaoUsuario(LocalizacaoUsuarios LocUsu) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO easygas.localizacaousuarios (usuario, id_localizacao, cidade, estado, bairro, rua, numero) "
					       + "VALUES ("+ LocUsu.getusuario()+ ", '" + LocUsu.getid_localizacao() + "', '"  
					       + LocUsu.getcidade() + "', '" + LocUsu.getestado()+ "', '" +LocUsu.getbairro()+ "', '" +LocUsu.getrua()+ "', '" +LocUsu.getnumero() +  "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarLocalizacaoUsuario(LocalizacaoUsuarios LocUsu) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE localizacaousuarios SET  usuario ='" +LocUsu.getusuario()+ "', cidade = '" + LocUsu.getcidade()+"', estado" + LocUsu.getestado()+"', bairro" + LocUsu.getbairro()+"', rua" + LocUsu.getrua() +"', numero" + LocUsu.getnumero()+"'"
					   + " WHERE id_localizaccao = " + LocUsu.getid_localizacao();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirLocalizacaoUsuario(String usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM easygas.localizacaousuarios WHERE usuario = '" + usuario+"'");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public LocalizacaoUsuarios[] getLocalizacaoUsuarios() {
		LocalizacaoUsuarios[] localizacaousuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM easygas.localizacaousuarios");		
	         if(rs.next()){
	             rs.last();
	             localizacaousuarios = new LocalizacaoUsuarios[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                localizacaousuarios[i] = new LocalizacaoUsuarios(rs.getString("usuario"),rs.getInt("id_localizacao"),rs.getString("cidade"),rs.getString("estado"),rs.getString("bairro"),rs.getString("rua"),rs.getInt("numero"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return localizacaousuarios;
}
	public LocalizacaoUsuarios getLocalizacaoUsuarios(String usuario) {
		LocalizacaoUsuarios locus = null;
		LocalizacaoUsuarios[] localizacaousuarios = new LocalizacaoUsuarios[0];
		try {
		Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery("SELECT * FROM easygas.localizacaousuarios WHERE usuario = '" + usuario + "'" );		
         if(rs.next()){
             rs.last();
             localizacaousuarios = new LocalizacaoUsuarios[rs.getRow()];
             rs.beforeFirst();

             for(int i = 0; rs.next(); i++) {
            	 
            	 localizacaousuarios[i] = new LocalizacaoUsuarios(rs.getString("usuario"), rs.getInt("id_localizacao"),rs.getString("cidade"),rs.getString("estado"),rs.getString("bairro"),rs.getString("rua"),rs.getInt("numero"));
                if(localizacaousuarios[i].getusuario() == usuario) {
                	locus = localizacaousuarios[i];
                	System.out.println("USUARIO POTATO LALALA" + locus.getusuario());
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
	return locus;
		
		
	
	
	}
}
