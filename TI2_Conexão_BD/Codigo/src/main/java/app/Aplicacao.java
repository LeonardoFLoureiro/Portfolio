package app;
import static spark.Spark.*;

import service.UsuarioService;
import service.PostoService;
import service.LocalizacaoPostosService;
import service.LocalizacaoUsuariosService;
import model.Usuario;
import dao.UsuariosDao;

public class Aplicacao {
	private static UsuarioService usuarioService = new UsuarioService();
	private static PostoService postoService = new PostoService();
	private static LocalizacaoPostosService localizacaopostoService = new LocalizacaoPostosService();
	private static LocalizacaoUsuariosService localizacaousuariosService = new LocalizacaoUsuariosService();

    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/usuario", (request, response) -> usuarioService.add(request, response));
        
        post("/login", (request, response) -> usuarioService.login(request, response));
        
        get("/logout", (request, response) -> usuarioService.logout(request, response));

        get("/postos", (request, response) -> localizacaopostoService.getALL(request, response));

        get("/locusu", (request, response) -> localizacaousuariosService.add(request, response));

       

       
 
        
        
        
    	/*UsuariosDao usudao = new UsuariosDao();
    	usudao.conectar();
    	
    	Usuario usuario = new Usuario("15698550646","Leo","Loureiro","lala", "093010",4);
    	if(usudao.inserirUsuario(usuario) == true) {
    		
    		System.out.println("FEITA COM SUCESSO");
    	}
    	*/
    	
    	
    	
    	
    	
    }

}
