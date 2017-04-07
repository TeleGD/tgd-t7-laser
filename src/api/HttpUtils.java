package api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Faire des POST/GET HTTP, à des php situé sur un serveur distant
 * @author Jerome
 *
 */

public class HttpUtils {


	private static final boolean LOG_ENABLED = false;
	public static void postHttp(final String urlStr,final String params, final RequestEndedListener listener){
		new Thread(new Runnable(){

			@Override
			public void run() {
				URL url;
				try {
					url = new URL(urlStr);
					HttpURLConnection connexion=((HttpURLConnection)url.openConnection());
					connexion.setRequestMethod("POST");
					connexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
					connexion.setRequestProperty("charset", "UTF-8");
					connexion.setDoOutput(true);
					DataOutputStream dos=new DataOutputStream(connexion.getOutputStream());
					dos.writeBytes(params);
					dos.flush();
					dos.close();
					connexion.connect();
					
					parseReponseConnexion(connexion,listener);

				} catch (MalformedURLException e) {
					listener.onError("URL MAL FORME");
					e.printStackTrace();
				} catch (ProtocolException e) {
					listener.onError("ERREUR DE PROTOCOLE");
					e.printStackTrace();
				}catch (JSONException e) {
					listener.onError("LA REPONSE N'est PAS DU JSON ?");
					e.printStackTrace();
				}catch (IOException e) {
					listener.onError("ERREUR DE ENTREE/SORTIES PARAMS ?");
					e.printStackTrace();
				} 
					
				
				
			}}).start();
	}
	public static void getHttp(final String urlStr, final RequestEndedListener listener){
		if(LOG_ENABLED)System.out.println("HttpUtils >> getHttp >>"+urlStr);

		new Thread(new Runnable(){

			@Override
			public void run() {
				URL url;
				try {
					url = new URL(urlStr);
					HttpURLConnection connexion=((HttpURLConnection)url.openConnection());
					connexion.setRequestMethod("GET");
					connexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
					connexion.setRequestProperty("charset", "UTF-8");
					connexion.connect();
					
					parseReponseConnexion(connexion,listener);
				} catch (MalformedURLException e) {
					listener.onError("URL MAL FORME");
					e.printStackTrace();
				} catch (ProtocolException e) {
					listener.onError("ERREUR DE PROTOCOLE");
					e.printStackTrace();
				} catch (JSONException e) {
					listener.onError("LA REPONSE N'est PAS DU JSON ?");
					e.printStackTrace();
				} catch (IOException e) {
					listener.onError("ERREUR DE ENTREE/SORTIES PARAMS ?");
					e.printStackTrace();
				}
				
				
				
			}}).start();
	}
	protected static void parseReponseConnexion(HttpURLConnection connexion, RequestEndedListener listener) throws IOException, JSONException {
		int httpCode=connexion.getResponseCode();
		if(httpCode>=300){
			listener.onError(httpCode+"- "+connexion.getResponseMessage());
		}else{
			BufferedReader reader=new BufferedReader(new InputStreamReader(connexion.getInputStream(),"UTF-8"));
		    String ligne,chaine="";
		   
		    while((ligne=reader.readLine())!=null){
		    	chaine+=ligne+"\n";
		    }
		   
		    reader.close();
		    if(LOG_ENABLED)System.out.println("HttpUtils >> parseReponseConnexion >> "+chaine);
		    JSONObject obj=new JSONObject(chaine);
		    if(obj.getBoolean("success")){
				listener.onSuccess(obj);
		    }else{
				listener.onError(obj.getString("reason"));
		    }
		}		
	}
}
