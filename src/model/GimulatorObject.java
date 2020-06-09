package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GimulatorObject {
    private String owner;
    private Key key;
    private String value;

    public Key getKey() {
        return key;
    }

    public String getOwner() {
        return owner;
    }

    public String getValue() {
        return value;
    }

    private GimulatorObject() {

    }

    public static List<GimulatorObject> parseArray(String json) {
        List<GimulatorObject> objects = new ArrayList<>();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            objects.add(parse(array.getJSONObject(i).toString()));
        }
        return objects;
    }

    public static GimulatorObject parse(String json) {
        GimulatorObject object = new GimulatorObject();
        JSONObject jsonObject = new JSONObject(json);
        object.owner = jsonObject.getString("Owner");
        object.value = jsonObject.getString("Value");
        object.key = Key.KeyBuilder.newKey()
                .setName(jsonObject.getJSONObject("Key").getString("Name"))
                .setNamespace(jsonObject.getJSONObject("Key").getString("Namespace"))
                .setType(jsonObject.getJSONObject("Key").getString("Type"))
                .get();

        return object;
    }
}
