package org.chatterai.speaker;

import org.chatterai.conversation.Conversation;
import org.chatterai.conversation.ConversationLine;

import static org.flowutils.Check.*;
import static org.flowutils.Check.notNull;

/**
 *
 */
public class PlayerSpeaker implements Speaker {

    private final String name;

    public PlayerSpeaker(String name) {
        notNull(name, "name");

        this.name = name;
    }

    @Override public void updateConversation(Conversation conversation, double secondsSinceLastUpdate) {
        // Nothing to do.
    }

    public String getName() {
        return name;
    }

    @Override public void startConversation(Conversation conversation) {
        // Nothing to do.
    }

    @Override public void endConversation(Conversation conversation) {
        // Nothing to do.
    }

    @Override public void onLineAdded(Conversation conversation, ConversationLine line) {
        // Nothing to do.
    }
}
