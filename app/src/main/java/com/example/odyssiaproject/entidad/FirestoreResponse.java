package com.example.odyssiaproject.entidad;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FirestoreResponse {
    @SerializedName("documents")
    private List<FirestoreDocument> documents;

    public List<FirestoreDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<FirestoreDocument> documents) {
        this.documents = documents;
    }
}
