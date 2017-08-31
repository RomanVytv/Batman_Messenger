package com.romanvytv.webapiandroidtest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class MessageViewModel {

    @SerializedName("Id")
    @Expose
    private int id;

    @SerializedName("Text")
    @Expose
    private String text;

    @SerializedName("UserId")
    @Expose
    private String userId;

    @SerializedName("ChatId")
    @Expose
    private int chatId;

    @SerializedName("Time")
    @Expose
    private String time;

    public MessageViewModel(int id, String text, String userId, int chatId, String time) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.chatId = chatId;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageViewModel{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", userId='" + userId + '\'' +
                ", chatId=" + chatId +
                ", time='" + time + '\'' +
                '}';
    }
}
