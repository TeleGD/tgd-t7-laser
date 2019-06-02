package api;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.Person;
/**
 * Utilise HttpUtils.
 *
 * API TGD, permettant de mettre a jour la base de donnée en ligne, par l'intermédiaire de requetes Http
 *Pour l'instant, on peut;
 * - update un score: updateScore
 * - recuperer les scores: getScoreForGame
 * - ...
 *
 * Actuellement serveur hébergé sur http://jerome.gauzins.com/TGD/,
 * Si vous avez un truc, je suis preneur, on peut migré facilement
 *
 * Lorsque on appelle une de ces méthodes, vous pouvez spécifiez (pas obligatoire), le listener, qui permettra d'obtenir
 * la réponse à la requète. via TGDApi.setApiListener(...);
 * @author Jerome
 *
 */
public class TGDApi {

	private static final String URL_API="http://jerome.gauzins.com/TGD/";
	private static final String URL_UPDATE_SCORE=URL_API+"update_score.php";
	private static final String URL_GET_SCORES=URL_API+"get_scores.php?";
	protected static final boolean LOG_ENABLED = true;
	private static APIListener listener;


	public static void setApiListener(APIListener listener){
		TGDApi.listener=listener;
	}
	public static void getScoreForGame(int gameId,APIListener listener){
		setApiListener(listener);
		getScoreForGame(gameId,10);
	}

	/**
	 * Récupère les scores !
	 * @param gameId :  le numero du jeu dont vous voulez récuperer les scores
	 * @param limit : nombre de resultats
	 */
	public static void getScoreForGame(final int gameId,final int limit){
		String url=URL_GET_SCORES+"game_id="+gameId+"&limit="+limit;

		HttpUtils.getHttp(url, new RequestEndedListener(){

			@Override
			public void onSuccess(JSONObject obj) {
				if(TGDApi.listener==null)return;
				if(LOG_ENABLED)System.out.println("TGDApi >> onSuccess >>"+obj.toString());

				try {
					if(obj.has("infos")){
						listener.onError(obj.getString("infos"));
					}else{
						JSONArray array=obj.getJSONArray("scores");
						ArrayList<Person> persons=new ArrayList<Person>();
						for(int i=0;i<array.length();i++){
							JSONObject objLoc=array.getJSONObject(i);
							Person person=new Person(objLoc.getString("Name"));
							person.setScoreAtGame(objLoc.getInt("Game"), objLoc.getInt("Score"));
							persons.add(person);
						}

						listener.onContentReceived(persons);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					listener.onError("Erreur de parsage JSON");
				}


			}

			@Override
			public void onError(String reason) {
				if(LOG_ENABLED)System.out.println("TGDApi >> onError >>"+reason);
				if(TGDApi.listener==null)return;
				listener.onError(reason);
			}});

	}
	public static void updateScoreForGame(final String name,final int gameId, final int score,APIListener listener){
		setApiListener(listener);
		updateScoreForGame(name,gameId,score);
	}
	public static void updateScoreForGame(final String name,final int gameId, final int score){
		String url=URL_UPDATE_SCORE;

		String params="game_id="+gameId+
					  "&name="+name+
				      "&score="+score;

		HttpUtils.postHttp(url,params, new RequestEndedListener(){

			@Override
			public void onSuccess(JSONObject obj) {
				if(LOG_ENABLED)System.out.println("TGDApi >> onSuccess >>"+obj.toString());
				if(TGDApi.listener==null)return;

				listener.onContentUpdated("Score updated with Success");
			}

			@Override
			public void onError(String reason) {
				if(LOG_ENABLED)System.out.println("TGDApi >> onError >>"+reason);
				if(TGDApi.listener==null)return;
				listener.onError(reason);
			}});

	}
	public static void getScoresForPlayer(final String namePlayer) {
		String url=URL_GET_SCORES+"player="+namePlayer;

		HttpUtils.getHttp(url, new RequestEndedListener(){

			@Override
			public void onSuccess(JSONObject obj) {
				if(TGDApi.listener==null)return;
				if(LOG_ENABLED)System.out.println("TGDApi >> onSuccess >>"+obj.toString());

				try {
					if(obj.has("infos")){
						listener.onError(obj.getString("infos"));
					}else{

						JSONArray array=obj.getJSONArray("scores");
						Person person=new Person(namePlayer);
						for(int i=0;i<array.length();i++){
							JSONObject objLoc=array.getJSONObject(i);
							person.setScoreAtGame(objLoc.getInt("Game"), objLoc.getInt("Score"));
						}

						listener.onContentReceived(person);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					listener.onError("Erreur de parsage JSON");
				}
			}

			@Override
			public void onError(String reason) {
				if(LOG_ENABLED)System.out.println("TGDApi >> onError >>"+reason);
				if(TGDApi.listener==null)return;
				listener.onError(reason);
			}});
	}
}
