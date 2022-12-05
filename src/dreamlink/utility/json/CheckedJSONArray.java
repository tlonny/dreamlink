package dreamlink.utility.json;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.joml.Vector4f;
import org.json.JSONArray;
import org.json.JSONException;

public class CheckedJSONArray {

    private final JSONArray array;

    public CheckedJSONArray(JSONArray array) {
        this.array = array;
    }

    public Vector4f getVector4f(Vector4f target) throws JSONAccessException {
        try {
            return target.set(
                this.array.getFloat(0),
                this.array.getFloat(1),
                this.array.getFloat(2),
                this.array.getFloat(3)
            );
        } catch (JSONException e) {
            throw new JSONAccessException();
        }
    }

    public Vector2f getVector2f(Vector2f target) throws JSONAccessException {
        try {
            return target.set(
                this.array.getFloat(0),
                this.array.getFloat(1)
            );
        } catch (JSONException e) {
            throw new JSONAccessException();
        }
    }

    public Vector3f getVector3f(Vector3f target) throws JSONAccessException {
        try {
            return target.set(
                this.array.getFloat(0),
                this.array.getFloat(1),
                this.array.getFloat(2)
            );
        } catch (JSONException e) {
            throw new JSONAccessException();
        }
    }

    public Vector3i getVector3i(Vector3i target) throws JSONAccessException {
        try {
            return target.set(
                this.array.getInt(0),
                this.array.getInt(1),
                this.array.getInt(2)
            );
        } catch (JSONException e) {
            throw new JSONAccessException();
        }
    }

    public Vector2i getVector2i(Vector2i target) throws JSONAccessException {
        try {
            return target.set(
                this.array.getInt(0),
                this.array.getInt(1)
            );
        } catch (JSONException e) {
            throw new JSONAccessException();
        }
    }
    
}
