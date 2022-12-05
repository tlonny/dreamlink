package dreamlink.utility.json;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckedJSONObject {

    private final JSONObject object;

    public CheckedJSONObject(String data) throws JSONDecodeException {
        try {
            this.object = new JSONObject(data);
        } catch (JSONException e) {
            throw new JSONDecodeException();
        }
    }

    public int getInt(String key) throws JSONAccessException {
        try {
            return this.object.getInt(key);
        } catch (JSONException e) {
            throw new JSONAccessException();
        }
    }

    public boolean getBoolean(String key) throws JSONAccessException {
        try {
            return this.object.getBoolean(key);
        } catch (JSONException e) {
            throw new JSONAccessException();
        }
    }

    public String getString(String key) throws JSONAccessException {
        try {
            return this.object.getString(key);
        } catch (JSONException e) {
            throw new JSONAccessException();
        }
    }

    public CheckedJSONArray getJSONArray(String key) throws JSONAccessException {
        try {
            var array = this.object.getJSONArray(key);
            return new CheckedJSONArray(array);
        } catch (JSONException e) {
            throw new JSONAccessException();
        }
    }

    public CheckedJSONArray optJSONArray(String key) {
        var object = this.object.optJSONArray(key);
        return object == null ? null : new CheckedJSONArray(object);
    }

    public int optInt(String key, int defaultValue) {
        return this.object.optInt(key, defaultValue);
    }

    public boolean optBoolean(String key, boolean defaultValue) {
        return this.object.optBoolean(key, defaultValue);
    }

    public float optFloat(String key, float defaultValue) {
        return this.object.optFloat(key, defaultValue);
    }

    public String optString(String key, String defaultValue) {
        return this.object.optString(key, defaultValue);
    }
    
}
