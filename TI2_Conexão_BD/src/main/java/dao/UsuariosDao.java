package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import model.Usuario;

public class UsuariosDao {
	private static final UsuariosDao INSTANCE = new UsuariosDao();
	private Connection conexao;
	
	public UsuariosDao() {
		conexao = null;
	}
	
	public static UsuariosDao getConnectedInstance() {
		INSTANCE.conectar();
		return INSTANCE;
	}
	
	public boolean conectar() {
		if(conexao != null) return true;
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
			System.out.println("Conexão efetuada com o postgres!USUARIODAO");
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
	
	public boolean inserirUsuario(Usuario usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			
			st.executeUpdate("INSERT INTO easygas.usuarios (cpf, primeironome, segundonome, senha, datanascimento, confiabilidade) "
					       + "VALUES ('"+ usuario.getCpf()+ "', '" + usuario.getPrimeiroNome() + "', '"  
					       + usuario.getSegundoNome() + "', '" + usuario.getSenha() + "', '"+usuario.getDataNascimento()+ "', " + usuario.getConfiabilidade() + ");");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarUsuario(Usuario usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE usuarios SET primeironome = '"  
				       + usuario.getPrimeiroNome() + "', segundonome = '" + usuario.getSegundoNome()+ "', senha = '" + usuario.getSenha()+ "', datanascimento = '" + usuario.getDataNascimento()+ "', confiabilidade = '" + usuario.getConfiabilidade() + "'"
					   + " WHERE cpf = " + usuario.getCpf();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirUsuario(String cpf) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE cpf = " + cpf);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	public Usuario[] getUsuarios() {
		Usuario[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios");		
	         if(rs.next()){
	             rs.last();
	             usuarios = new Usuario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                usuarios[i] = new Usuario(rs.getString("cpf"), rs.getString("primeironome"),rs.getString("segundonome"),rs.getString("senha"),rs.getString("datanascimento") 
	                		                  , rs.getInt("confiabilidade"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	public Usuario getUsuario(String cpf) {
		Usuario usuario = null;
		Usuario[] usuarios = null;
		try {
		Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery("SELECT * FROM easygas.usuarios WHERE cpf = '" + cpf + "'");
		
		if(rs.next()) {
			usuario = new Usuario(rs.getString("cpf"), rs.getString("primeironome"),rs.getString("segundonome"),rs.getString("senha"),rs.getString("datanascimento") 
					, rs.getInt("confiabilidade"));			
		}
		
		
//		System.out.println(rs.getRow());
//		if(rs.next()){
//             rs.last();
//             usuarios = new Usuario[rs.getRow()];
//             rs.beforeFirst();
//             
//             for(int i = 0; rs.next(); i++) {
//                usuarios[i] = new Usuario(rs.getString("cpf"), rs.getString("primeironome"),rs.getString("segundonome"),rs.getString("senha"),rs.getString("datanascimento") 
//		                  , rs.getInt("confiabilidade"));
//                if(usuarios[i].getCpf() == cpf) {
//                	System.out.println("usuario achado");
//                	usuario = usuarios[i];
//                	break;
//                }
//             }
//          }
          st.close();
	} catch (Exception e) {
		System.err.println(e.getMessage());
	}
	return usuario;
		
		
	
	
	}

	
	/*public Usuario[] getUsuariosMasculinos() {
		Usuario[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE usuario.sexo LIKE 'M'");		
	         if(rs.next()){
	             rs.last();
	             usuarios = new Usuario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                usuarios[i] = new Usuario(rs.getInt("codigo"), rs.getString("login"), 
                         		                  rs.getString("senha"), rs.getString("sexo").charAt(0));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	*/
}
	