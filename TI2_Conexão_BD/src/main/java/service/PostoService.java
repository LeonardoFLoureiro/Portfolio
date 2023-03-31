package service;


import model.Posto;
import spark.Request;
import spark.Response;
import dao.PostoDao;

public class PostoService {
	private PostoDao postodao = new PostoDao();
	public PostoService() {
		postodao.conectar();
	}
	
	public Object add(Request request, Response response) {
		String marca = request.queryParams("marca");
		
		String valorgasolina = request.queryParams("valorgasolina");
		String valoretanol = request.queryParams("valoretanol");
		
		
		
		
		int id_posto = postodao.getMaxId() + 1;

		Posto posto = new Posto(id_posto, marca, valorgasolina, valoretanol);
       postodao.inserirPosto(posto);
       
		
		response.status(201); // 201 Created
		return id_posto;
	}
	
	
	public Object get(Request request, Response response) {
		int id_posto = Integer.parseInt(request.params(":id"));
		
		Posto posto = (Posto) postodao.getPosto(id_posto);
		
		if (posto != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "POSTO\n" + 
            		"\t id_posto =" + posto.getid_posto() + "\n" +
            		"\t Marca =" + posto.getmarca() + "\n" +
            		"\t Valor Gasolina =" + posto.getvalorgasolina() + "\n" +
            		"\t Valor Etanol = " + posto.getvaloretanol() + "\n"
            		
            		;
        } else {
            response.status(404); // 404 Not found
            return "Posto " + id_posto + " não encontrado.";
        }

	}
	
	public Object update(Request request, Response response) {
		int id_posto = Integer.parseInt(request.params(":id"));
        
		Posto posto = (Posto) postodao.getPosto(id_posto);

        if (posto != null) {
        	

            posto.setmarca(request.queryParams("marca"));
        	posto.setvalorgasolina(request.queryParams("valorgasolina"));
        	posto.setvaloretanol(request.queryParams("valoretanol"));

        	postodao.atualizarPosto(posto);
        	
            return id_posto;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
		int id_posto = Integer.parseInt(request.params(":id"));

        Posto posto = (Posto) postodao.getPosto(id_posto);

        if (posto != null) {

            postodao.excluirPosto(id_posto);

            response.status(200); // success
        	return id_posto;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }
	}

}
