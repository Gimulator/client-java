import org.json.JSONObject;

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

    private GimulatorObject(){

    }

    public static GimulatorObject parse(String json){
        GimulatorObject object = new GimulatorObject();
        JSONObject jsonObject = new JSONObject(json);
        object.owner = jsonObject.getString("owner");
        object.value = jsonObject.getString("value");
        object.key = Key.KeyBuilder.newKey()
                .setName(jsonObject.getJSONObject("key").getString("name"))
                .setNamespace(jsonObject.getJSONObject("key").getString("namespace"))
                .setType(jsonObject.getJSONObject("key").getString("type"))
                .get();

        return object;
    }
}
