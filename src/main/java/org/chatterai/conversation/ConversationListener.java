package org.chatterai.conversation;

/**
 *
 */
public interface ConversationListener {

    /**
     * Called when a line was contributed to a conversation.
     */
    void onLineAdded(Conversation conversation, ConversationLine line);
}
