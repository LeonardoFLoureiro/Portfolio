package service;



import dao.IlhaDAO;
import model.IlhaMODEL;

public class IlhaSERVICE {
	private IlhaDAO ilhadao = new IlhaDAO();

	public IlhaSERVICE() {

		ilhadao.conectar();
	}

	public void conectar() {
		ilhadao.conectar();
	}

	public void desconectar() {
		ilhadao.close();
	}

	public void add(IlhaMODEL ilha) {
		
			ilhadao.inserirIlhaMODEL(ilha);

			System.out.println("Ilha Criada");

	}

	public IlhaMODEL get(IlhaMODEL ilha) {

		IlhaMODEL ilhamodel = (IlhaMODEL) ilhadao.getIlhaMODEL(ilha.getId());

		if (ilhamodel != null) {
			System.out.println("Existe");
			return ilhamodel;

		} else {
			System.out.println("NAO EXISTE");
			return ilhamodel;
		}

	}

	public IlhaMODEL[] getall() {

		return ilhadao.getALLIlhaMODELs();
	}


	public void update(IlhaMODEL ilha) {

		IlhaMODEL ilhamodel = (IlhaMODEL) ilhadao.getIlhaMODEL(ilha.getId());

		if (ilhamodel != null) {

			ilhadao.atualizarIlhaMODEL(ilha);

		} else {

			System.out.println("Não encontrado");
		}

	}

	public void remove(IlhaMODEL ilha) {

		IlhaMODEL ilhamodel = (IlhaMODEL) ilhadao.getIlhaMODEL(ilha.getId());

		if (ilhamodel != null) {

			ilhadao.excluirIlhaMODEL(ilha.getId());

		} else {

			System.out.println("Não encontrado");
		}
	}

}
