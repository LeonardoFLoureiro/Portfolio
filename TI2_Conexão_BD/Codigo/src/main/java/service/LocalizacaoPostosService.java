package service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

import org.json.*;



import dao.LocalizacaoPostosDao;
import dao.PostoDao;
import model.LocalizacaoPostos;
import model.Posto;
import spark.Request;
import spark.Response;
import spark.Session;

import java.text.Normalizer;
public class LocalizacaoPostosService {
	private static final String API_KEY = "AIzaSyDzxLnfgJLirR4k1Jm9vuFIo5khhTxTqLE";
	private LocalizacaoPostosDao locusdao = new LocalizacaoPostosDao();
	public LocalizacaoPostosService() {
		locusdao.conectar();
	}
	
	public Object add(Request request, Response response) {
		int posto = Integer.parseInt(request.queryParams("posto"));
		String cidade = request.queryParams("cidade");
		String estado = request.queryParams("estado");
		String bairro =  request.queryParams("bairro");
		String rua =  request.queryParams("rua");
		int numero = Integer.parseInt(request.queryParams("numero"));
		
		int id_localizacao = locusdao.getMaxId() + 1;
		
		LocalizacaoPostos locus = new LocalizacaoPostos(posto,id_localizacao,cidade,estado,bairro,rua,numero);
       locusdao.inserirLocalizacaoPostos(locus);
       
		
		response.status(201); // 201 Created
		return id_localizacao;
	}
	
public Object getALL(Request request, Response response) throws Exception, InterruptedException {
//		Session session = request.session();
//		if(session.attribute("logado") == null || (Boolean) session.attribute("logado") == false) {
//			response.status(401);
//		}
	
		float latitude = Float.parseFloat(request.queryParams("latitude"));
		float longitude = Float.parseFloat(request.queryParams("longitude"));
		
		LocalizacaoPostos[] locus = (LocalizacaoPostos[]) locusdao.getLocalizacaoPostos();
		String enderecos = "";
		String endereco;
		
		
		var client = HttpClient.newHttpClient();

		Map<String, LocalizacaoPostos> mapa_endereco = new HashMap<String, LocalizacaoPostos>();
		Map<String, JSONObject> mapa_coordenada = new HashMap<String, JSONObject>();
		
		for(int i = 0; i < locus.length;i++) {
			
			endereco = locus[i].getrua() + "," + locus[i].getnumero() + "," + locus[i].getbairro() + "," + locus[i].getcidade();
			enderecos += endereco + "|";
			
			
			
			
			var requestAPI = HttpRequest.newBuilder(
			       java.net.URI.create("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=" + java.net.URLEncoder.encode(endereco, "UTF-8") + "&inputtype=textquery&fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry&key="+API_KEY))
			   .header("accept", "application/json")
			   .build();

			
			var responseAPI = client.send(requestAPI, BodyHandlers.ofString());

			JSONObject resultado = new JSONObject(responseAPI.body());
			
			System.out.println(resultado.getJSONArray("candidates").getJSONObject(0).getString("formatted_address"));
			
			mapa_endereco.put(resultado.getJSONArray("candidates").getJSONObject(0).getString("formatted_address"), locus[i]);
			mapa_coordenada.put(resultado.getJSONArray("candidates").getJSONObject(0).getString("formatted_address"), resultado.getJSONArray("candidates").getJSONObject(0));
		}
		
		
		String enderecoQuery = "";
		
		for(String key: mapa_endereco.keySet()){
		  enderecoQuery += key + "|";
		}
		enderecoQuery = Normalizer.normalize(enderecoQuery, Normalizer.Form.NFD);
		enderecoQuery = enderecoQuery.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		enderecoQuery = java.net.URLEncoder.encode(enderecoQuery);
		String endpoint = "https://maps.googleapis.com/maps/api/distancematrix/json?destinations=" + enderecoQuery + "&origins=" + latitude + "," + longitude + "&units=metric&key=" + API_KEY + "&language=pt-BR&mode=driving";
		System.out.println(endpoint);
		var requestAPI = HttpRequest.newBuilder(
			       java.net.URI.create(endpoint))
			   .header("accept", "application/json")
			   .build();

			
		var responseAPI = client.send(requestAPI, BodyHandlers.ofString());
		JSONObject resultado = new JSONObject(responseAPI.body());
		JSONObject retorno = new JSONObject();
		PostoDao postodao = new PostoDao();
		postodao.conectar();
		JSONArray postos = new JSONArray();
		
		
		
		for (int contador = 0; contador < resultado.getJSONArray("destination_addresses").length(); contador++) {
		    String enderecoPosto = resultado.getJSONArray("destination_addresses").getString(contador);
		    LocalizacaoPostos locPosto = mapa_endereco.get(enderecoPosto);
			  System.out.println(enderecoPosto);  
			  System.out.println(locPosto.getposto()); 
			  
			  Posto posto = postodao.getPosto(locPosto.getposto());
			  System.out.println(posto.getmarca());
			  JSONObject postoJSON = new JSONObject();
			  postoJSON.put("precoEtanol", posto.getvaloretanol());
			  postoJSON.put("precoGasolina", posto.getvalorgasolina());
			  postoJSON.put("marca", posto.getmarca());
			  postoJSON.put("id_posto", posto.getid_posto());
			  postoJSON.put("enderecoPosto", enderecoPosto);
			  postoJSON.put("resultadoBuscaDistancia", resultado.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(contador));
			  postoJSON.put("resultadoBuscaCoordenada", mapa_coordenada.get(enderecoPosto));
			  postos.put(postoJSON);
		}
		  retorno.put("resultado", postos);


		
		System.out.println(retorno.toString());
		
		
		
		
		return retorno.toString();
	}
	
	
	public Object get(Request request, Response response) {
		int id_localizacao = Integer.parseInt(request.params(":id"));
		
		LocalizacaoPostos locus = (LocalizacaoPostos) locusdao.getLocalizacaoPostos(id_localizacao);
		
		if (locus != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "LocalizacaoPostos\n" + 
            		"\t posto =" + locus.getposto() + "\n" +
            		"\t id_localizacao =" + locus.getid_localizacao() +"\n" +
            		"\t cidade =" + locus.getcidade() + "\n" +
            		"\t estado = " + locus.getestado() + "\n" +
            		"\t bairro = " + locus.getbairro() + "\n" +
            		"\t rua = " + locus.getrua() + "\n" +
            		"\t numero" + locus.getnumero() + "\n" 
            		;
        } else {
            response.status(404); // 404 Not found
            return "Localizacao " + id_localizacao + " não encontrado.";
        }

	}
	public Object update(Request request, Response response) {
		int id_localizacao = Integer.parseInt(request.params(":id"));
        
		LocalizacaoPostos locus = (LocalizacaoPostos) locusdao.getLocalizacaoPostos(id_localizacao);

        if (locus != null) {
        	locus.setposto(Integer.parseInt(request.queryParams("posto")));
        	locus.setcidade(request.queryParams("cidade"));
        	locus.setestado(request.queryParams("estado"));
        	locus.setbairro(request.queryParams("bairro"));
        	locus.setrua(request.queryParams("rua"));
        	locus.setnumero(Integer.parseInt(request.queryParams("numero")));

        	locusdao.atualizarLocalizacaoPostos(locus);
        	
            return id_localizacao;
        } else {
            response.status(404); // 404 Not found
            return "Localizacao não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
		int id_localizacao = Integer.parseInt(request.params(":id"));

        LocalizacaoPostos locus = (LocalizacaoPostos) locusdao.getLocalizacaoPostos(id_localizacao);

        if (locus != null) {

            locusdao.excluirLocalizacaoPostos(id_localizacao);

            response.status(200); // success
        	return id_localizacao;
        } else {
            response.status(404); // 404 Not found
            return "Localizacao não encontrado.";
        }
	}
	
	
	
	
	
}
