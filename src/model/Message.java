package model;

import java.time.LocalDateTime;

public class Message {

    private int id;
    private int userId;
    private String userMessage;
    private String botResponse;
    private LocalDateTime createdAt;

    // Default constructor
    public Message() {
    }

    // Constructor
    public Message(int id, int userId, String userMessage,
                   String botResponse, LocalDateTime createdAt) {

        this.id = id;
        this.userId = userId;
        this.userMessage = userMessage;
        this.botResponse = botResponse;
        this.createdAt = createdAt;
    }

    // Constructor without ID and timestamp
    public Message(int userId, String userMessage, String botResponse) {

        this.userId = userId;
        this.userMessage = userMessage;
        this.botResponse = botResponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getBotResponse() {
        return botResponse;
    }

    public void setBotResponse(String botResponse) {
        this.botResponse = botResponse;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userId=" + userId +
                ", userMessage='" + userMessage + '\'' +
                ", botResponse='" + botResponse + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}