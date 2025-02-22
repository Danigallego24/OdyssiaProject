package com.example.odyssiaproject.entidad;

import java.util.Map;

public class FirestoreDocument {
    private String name;
    private Map<String, FirestoreField> fields;

    public String getName() {
        return name;
    }

    public Map<String, FirestoreField> getFields() {
        return fields;
    }
}
