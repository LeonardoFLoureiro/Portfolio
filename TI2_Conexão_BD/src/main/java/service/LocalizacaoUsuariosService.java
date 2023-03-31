package service;

import java.sql.SQLException;

import dao.LocalizacaoUsuariosDao;

import model.LocalizacaoUsuarios;
import spark.Request;
import spark.Response;

public class LocalizacaoUsuariosService {
	private LocalizacaoUsuariosDao locusdao = new LocalizacaoUsuariosDao();
	public LocalizacaoUsuariosService() {
		locusdao.conectar();
	}
	
	public Object add(Request request, Response response) throws SQLException {
		String usuario = "15698550646";
		String cidade = request.queryParams("cidade");
		String estado = request.queryParams("estado");
		String bairro =  request.queryParams("bairro");
		String rua =  request.queryParams("rua");
		int numero = Integer.parseInt(request.queryParams("numero"));
		
		
		if(locusdao.getLocalizacaoUsuarios(usuario) != null) {
			
			locusdao.excluirLocalizacaoUsuario(usuario);
			
		}
		
		
		int id_localizacao = locusdao.getMaxId() + 1;
		
		System.out.println("caiu aqui" + id_localizacao);
		
		LocalizacaoUsuarios locus = new LocalizacaoUsuarios(usuario,id_localizacao,cidade,estado,bairro,rua,numero);
		
        locusdao.inserirLocalizacaoUsuario(locus);
       
		
		response.status(201); // 201 Created
		return "<script>window.location = '/'</script>";
	}
	
	
	public Object get(Request request, Response response) {
		String usuario = request.params(":id");
		
		LocalizacaoUsuarios locus = (LocalizacaoUsuarios) locusdao.getLocalizacaoUsuarios(usuario);
		
		if (locus != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

    	    return "LocalizacaoPostos\n" + 
    		"\t usuario =" + locus.getusuario() + "\n" +
    		"\t id_localizacao =" + locus.getid_localizacao() +"\n" +
    		"\t cidade =" + locus.getcidade() + "\n" +
    		"\t estado = " + locus.getestado() + "\n" +
    		"\t bairro = " + locus.getbairro() + "\n" +
    		"\t rua = " + locus.getrua() + "\n" +
    		"\t numero" + locus.getnumero() + "\n" 
    		;
        } else {
            response.status(404); // 404 Not found
            return "Localizacao " + usuario + " não encontrado.";
        }

	}
	public Object update(Request request, Response response) {
		String usuario = request.params(":id");
        
		LocalizacaoUsuarios locus = (LocalizacaoUsuarios) locusdao.getLocalizacaoUsuarios(usuario);

        if (locus != null) {
        	locus.setusuario(request.queryParams("usuario"));
        	locus.setcidade(request.queryParams("cidade"));
        	locus.setestado(request.queryParams("estado"));
        	locus.setbairro(request.queryParams("bairro"));
        	locus.setrua(request.queryParams("rua"));
        	locus.setnumero(Integer.parseInt(request.queryParams("numero")));

        	locusdao.atualizarLocalizacaoUsuario(locus);
        	
            return usuario;
        } else {
            response.status(404); // 404 Not found
            return "Localizacao não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
		String usuario = request.params(":id");

        LocalizacaoUsuarios locus = (LocalizacaoUsuarios) locusdao.getLocalizacaoUsuarios(usuario);

        if (locus != null) {
           String usuario1 = locus.getusuario();
            locusdao.excluirLocalizacaoUsuario(usuario1);

            response.status(200); // success
        	return usuario;
        } else {
            response.status(404); // 404 Not found
            return "Localizacao não encontrado.";
        }
	}
	
	
	
	
	
}
