package com.proje.mobilesales.core.netsis;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NetsisDataDeserializer implements JsonDeserializer<NetsisData> {
    public NetsisData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<NetsisDataPrimitive> netsisDataPrimitivesRecursive;
        NetsisData netsisData = new NetsisData();
        Set<Map.Entry<String, JsonElement>> entrySet = jsonElement.getAsJsonObject().entrySet();
        ArrayList arrayList = new ArrayList();
        new ArrayList();
        if (entrySet != null && (netsisDataPrimitivesRecursive = getNetsisDataPrimitivesRecursive(jsonElement.getAsJsonObject().entrySet())) != null) {
            arrayList.addAll(netsisDataPrimitivesRecursive);
        }
        netsisData.setNetsisDataPrimitives(arrayList);
        return netsisData;
    }
    private List<NetsisDataPrimitive> getNetsisDataPrimitivesRecursive(Set<Map.Entry<String, JsonElement>> set) {
        ArrayList arrayList = new ArrayList();
        if (set == null) {
            return null;
        }
        for (Map.Entry<String, JsonElement> entry : set) {
            if (entry.getValue().isJsonObject()) {
                if (entry.getValue().getAsJsonObject().size() > 0) {
                    arrayList.addAll(getNetsisDataPrimitivesRecursive(entry.getValue().getAsJsonObject().entrySet()));
                }
            } else {
                arrayList.add(new NetsisDataPrimitive(entry.getKey(), entry.getValue().isJsonNull() ? "" : entry.getValue().getAsString()));
            }
        }
        return arrayList;
    }
}
