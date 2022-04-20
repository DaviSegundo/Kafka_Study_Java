package br.com.ecommerce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class GsonDeserializer<T> implements Deserializer<T> {
    private Class<T> type;
    private final Gson gson = new GsonBuilder().create();
    public static final String TYPE_CONFIG = "br.com.ecommerce.type_config";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String typeName =  String.valueOf(configs.get(TYPE_CONFIG));
        try {
            this.type =  (Class<T>) Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw  new RuntimeException("Type for deserialization does not exists!", e);
        }
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes), type);
    }
}
