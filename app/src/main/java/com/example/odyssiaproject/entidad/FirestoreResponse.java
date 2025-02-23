package com.example.odyssiaproject.entidad;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FirestoreResponse {

    // La anotación @SerializedName indica que el campo "documents" del JSON
    // se va a mapear a la lista "documents" de la clase.
    @SerializedName("documents")
    private List<FirestoreDocument> documents;

    public List<FirestoreDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<FirestoreDocument> documents) {
        this.documents = documents;
    }
}

