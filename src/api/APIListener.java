package api;

public interface APIListener {

	void onContentReceived(Object content);
	void onContentUpdated(String reponse);
	void onError(String reason);

}
