package model;

import org.json.JSONObject;

public class Key {

    private String type;
    private String namespace;
    private String name;

    public String getName() {
        return name;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("namespace", namespace);
        object.put("type", type);
        return object.toString();
    }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("namespace", namespace);
        object.put("type", type);
        return object;
    }

    public static class KeyBuilder {
        public static KeyBuilder newKey() {
            return new KeyBuilder();
        }

        private final Key key;

        private KeyBuilder() {
            key = new Key();
        }

        public KeyBuilder setType(String type) {
            key.type = type;
            return this;
        }

        public KeyBuilder setNamespace(String namespace) {
            key.namespace = namespace;
            return this;
        }

        public KeyBuilder setName(String name) {
            key.name = name;
            return this;
        }

        public Key get() {
            return key;
        }

    }


}
