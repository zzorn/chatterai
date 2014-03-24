package org.chatterai.conversation;

import org.chatterai.sentencetypes.SentenceType;
import org.chatterai.speaker.Speaker;

/**
 * Line or emote said in a conversation.
 */
public interface ConversationLine {

    Speaker getSource();

    Speaker getRecipient();

    /**
     * @return what was said (e.g. question, statement, greeting, injection, emote, etc)
     *         Also how it was said?
     */
    SentenceType getSentenceType();

    /**
     * @return convert to a textual representation.
     */
    String renderToText();

    /**
     * @return rough duration in seconds it would take to say this line.
     */
    double getDuration();
}
