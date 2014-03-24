package org.chatterai.speaker;

import org.chatterai.conversation.Conversation;
import org.chatterai.conversation.ConversationListener;

/**
 * Someone or something that participates in a conversation.
 */
public interface Speaker extends ConversationListener {

    String getName();

    /**
     * Called when a new conversation is started.
     */
    void startConversation(Conversation conversation);

    /**
     * Called when a conversation is ended.
     */
    void endConversation(Conversation conversation);

    /**
     * Called regularly to allow the speaker to say something.
     * @param conversation conversation the speaker should update itself in.
     * @param secondsSinceLastUpdate seconds since last call of this method.
     */
    void updateConversation(Conversation conversation, double secondsSinceLastUpdate);
}
