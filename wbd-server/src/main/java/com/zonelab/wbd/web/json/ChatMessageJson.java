package com.zonelab.wbd.web.json;

public class ChatMessageJson {
    public enum Command {
        LOAD, ERROR
    }
    private Command command;
    private long chatId;
    private String author;
    private String text;
    private long timestamp;

    public ChatMessageJson() {
    }

    public ChatMessageJson(Command command, long chatId, String text) {
        this.command = command;
        this.chatId = chatId;
        this.text = text;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
