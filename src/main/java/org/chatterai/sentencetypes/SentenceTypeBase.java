package org.chatterai.sentencetypes;

import org.chatterai.speaker.Speaker;

/**
 *
 */
public abstract class SentenceTypeBase implements SentenceType {

    private static final int TYPICAL_DURATION = 2;

    private double typicalDuration;
    private boolean emote;

    protected SentenceTypeBase() {
        this(TYPICAL_DURATION);
    }

    protected SentenceTypeBase(double typicalDuration) {
        this(typicalDuration, false);
    }

    protected SentenceTypeBase(double typicalDuration, boolean emote) {
        this.typicalDuration = typicalDuration;
        this.emote = emote;
    }

    @Override public String renderToText(Speaker source, Speaker recipient) {
        final String sentence = generateSentence(source, recipient);

        if (emote) {
            return source.getName() + " " + sentence;
        }
        else {
            return source.getName() + " says \"" + sentence + "\"";
        }
    }

    protected String generateSentence(Speaker source, Speaker recipient) { return ""; }

    @Override public double getDuration() {
        return typicalDuration;
    }
}
