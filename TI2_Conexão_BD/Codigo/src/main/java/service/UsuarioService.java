package service;

import static spark.Spark.get;

import org.json.JSONObject;

import dao.UsuariosDao;

import model.Usuario;
import spark.Request;
import spark.Response;
import spark.Session;

public class UsuarioService {
	private UsuariosDao usuariodao = new UsuariosDao();
	public UsuarioService() {
		usuariodao.conectar();
	}
	
	public Object login(Request request, Response response) {
		String cpfRequest = request.queryParams("cpf");
		String senhaRequest = request.queryParams("senha");
		
		UsuariosDao usuarioDao = UsuariosDao.getConnectedInstance();
		Usuario usuario = usuarioDao.getUsuario(cpfRequest);
		
		JSONObject retorno = new JSONObject();
		
		if(usuario != null) {
			String senhaOriginal = usuario.getSenha();
			System.out.println(senhaOriginal);
			System.out.println(senhaRequest);
			if(senhaOriginal.equals(senhaRequest)) {
				retorno.put("mensagem", "Logado");
				retorno.put("logado", true);
				Session session = request.session(true);
				session.attribute("logado", true);
			}else {
				retorno.put("mensagem", "Senha incorreta");
				retorno.put("logado", false);
			}
		}else {
			retorno.put("mensagem", "Usu·rio n„o encontrado");
			retorno.put("logado", false);
		}
		
		return retorno.toString();
//		request.session(true);
		
//		request.session().attribute(, response);;
		//return "test";
	}
	
	public Object logout(Request request, Response response) {
		Session session = request.session();
		session.attribute("logado", false);
		return "";
	}
	
	public Object add(Request request, Response response) {
		String cpf = request.queryParams("cpf");
		String primeironome = request.queryParams("primeironome");
		String segundonome = request.queryParams("segundonome");
		String senha =  request.queryParams("senha");
		String datanascimento =  request.queryParams("datanascimento");
		int confiabilidade = 5;
		Usuario teste = (Usuario) usuariodao.getUsuario(cpf);
		if(teste != null) {
			response.status(404); // 404 Not found
            return "Usuario " + cpf + " J· existe";
			
		}else {
			Usuario usuario = new Usuario(cpf, primeironome, segundonome, senha, datanascimento, confiabilidade);
		    usuariodao.inserirUsuario(usuario);
		       
			//response.status(201); // 201 Created
		    System.out.println("lalala");
			
			return "<script>window.location = '/'</script>";
			
			
			
			
		}
		

		
	}
	
	
	public Object get(Request request, Response response) {
		String cpf = request.params("cpf");
		
		Usuario usuario = (Usuario) usuariodao.getUsuario(cpf);
		
		if (usuario != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "USUARIO\n" + 
            		"\t CPF =" + usuario.getCpf() + "\n" +
            		"\t NOME =" + usuario.getPrimeiroNome() + " " + usuario.getSegundoNome() + "\n" +
            		"\t SENHA =" + usuario.getSenha() + "\n" +
            		"\t Data de Nascimento = " + usuario.getDataNascimento() + "\n" +
            		"\t Confiabilidade" + usuario.getConfiabilidade() + "\n" 
            		;
        } else {
            response.status(404); // 404 Not found
            return "Usuario " + cpf + " n√£o encontrado.";
        }

	}
	public Object update(Request request, Response response) {
        String cpf = request.params("cpf");
        
		Usuario usuario = (Usuario) usuariodao.getUsuario(cpf);

        if (usuario != null) {
        	usuario.setCpf(request.queryParams("cpf"));
        	usuario.setPrimeiroNome(request.queryParams("primeironome"));
        	usuario.setSegundoNome(request.queryParams("segundo nome"));
        	usuario.setSenha(request.queryParams("senha"));
        	usuario.setDataNascimento(request.queryParams("datanascimento"));
        	usuario.setConfiabilidade(Integer.parseInt(request.queryParams("confiabilidade")));

        	usuariodao.atualizarUsuario(usuario);
        	
            return cpf;
        } else {
            response.status(404); // 404 Not found
            return "Usuario n√£o encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        String cpf = request.params("cpf");

        Usuario usuario = (Usuario) usuariodao.getUsuario(cpf);

        if (usuario != null) {

            usuariodao.excluirUsuario(cpf);

            response.status(200); // success
        	return cpf;
        } else {
            response.status(404); // 404 Not found
            return "Usuario n√£o encontrado.";
        }
	}
	
	
	
	
	
}
