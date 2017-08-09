package com.zonelab.wbd.web.json;

import com.zonelab.wbd.core.api.Chat;
import com.zonelab.wbd.core.api.ChatMessage;
import com.zonelab.wbd.core.api.Id;

public final class Json {
    private Json() {
    }

    public static Chat fromJson(JsonChat json) {
        return null;
    }
    public static ChatMessage fromJson(final JsonChatMessage json) {
        final Id chatId = Id.create(json.getChatId());
        final Id authorId = Id.create(json.getAuthorId());
        return ChatMessage.builder()
                .setAuthorId(authorId)
                .setText(json.getText())
                .setTimestamp(System.currentTimeMillis())
                .build();
    }

    public static JsonChatMessage toJson(final ChatMessage object) {
        final JsonChatMessage json = new JsonChatMessage();
        json.setAuthorId(object.getAuthorId().asLong());
        json.setText(object.getText());
        json.setTimestamp(object.getTimestamp());
        return json;
    }
}
