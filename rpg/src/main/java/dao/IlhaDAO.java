package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.IlhaMODEL;

public class IlhaDAO {
	private static final IlhaDAO INSTANCE = new IlhaDAO();
	private Connection conexao;

	public IlhaDAO() {
		conexao = null;
	}

	public static IlhaDAO getConnectedInstance() {
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
			System.out.println("Conexão efetuada com o postgres!IlhaMODELDAO");
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

	public boolean inserirIlhaMODEL(IlhaMODEL IlhaMODEL) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();

			st.executeUpdate("INSERT INTO rpg.Ilha (nome,descricao,localizacaox,localizacaoy,img) "
					+ "VALUES ('" + IlhaMODEL.getNome() + "', '" + IlhaMODEL.getDescricao() + "', "
					+ IlhaMODEL.getX() + ", " + IlhaMODEL.getY() +",'" + IlhaMODEL.getImg() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarIlhaMODEL(IlhaMODEL IlhaMODEL) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE rpg.Ilha SET nome = '"
					+ IlhaMODEL.getNome() + "', descricao = '" + IlhaMODEL.getDescricao() + "', localizacaox = "
					+ IlhaMODEL.getX() + ", localizacaoy = " + IlhaMODEL.getY()
					+ " WHERE id = " + IlhaMODEL.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
			
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	

	public boolean excluirIlhaMODEL(int id) {
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

	public IlhaMODEL[] getALLIlhaMODELs() {
		IlhaMODEL[] IlhaMODELs = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM rpg.Ilha");
			if (rs.next()) {
				rs.last();
				IlhaMODELs = new IlhaMODEL[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					IlhaMODELs[i] = new IlhaMODEL(rs.getString("nome"), rs.getString("descricao"),
							rs.getInt("localizacaox"), rs.getInt("localizacaoy"),rs.getString("img"),rs.getInt("id"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return IlhaMODELs;
	}

	

	public IlhaMODEL getIlhaMODEL(int id) {
		IlhaMODEL IlhaMODEL = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = st.executeQuery("SELECT * FROM rpg.ilha WHERE id =" + id);
			if (rs.next()) {
				IlhaMODEL = new IlhaMODEL(rs.getString("nome"), rs.getString("descricao"), rs.getInt("localizacaox"),
						rs.getInt("localizacaoy"),rs.getString("img"),rs.getInt("id"));
			}

			
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return IlhaMODEL;

	}
	
}
