package org.chatterai.conversation;

import org.chatterai.sentencetypes.SentenceType;
import org.chatterai.speaker.Speaker;

/**
 *
 */
public class ConversationLineImpl implements ConversationLine {

    private final Speaker source;
    private final Speaker recipient;
    private final SentenceType sentenceType;

    public ConversationLineImpl(Speaker source, Speaker recipient, SentenceType sentenceType) {
        this.source = source;
        this.recipient = recipient;
        this.sentenceType = sentenceType;
    }

    @Override public Speaker getSource() {
        return source;
    }

    @Override public Speaker getRecipient() {
        return recipient;
    }

    @Override public SentenceType getSentenceType() {
        return sentenceType;
    }

    @Override public String renderToText() {
        return sentenceType.renderToText(source, recipient);
    }

    @Override public double getDuration() {
        return sentenceType.getDuration();
    }
}
