package com.romanvytv.webapiandroidtest.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatViewModel {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("CreationDate")
    @Expose
    private String creationDate;

    public ChatViewModel(String id, String name, String creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    @Override
    public String toString() {
        return "ChatViewModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }

}
