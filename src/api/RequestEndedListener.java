package api;

import org.json.JSONObject;

public interface RequestEndedListener {
	void onSuccess(JSONObject obj);
	void onError(String reason);
}
