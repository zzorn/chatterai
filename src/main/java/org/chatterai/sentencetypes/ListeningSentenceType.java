package org.chatterai.sentencetypes;

import org.chatterai.speaker.Speaker;
import org.chatterai.util.SentenceUtils;

/**
 *
 */
public class ListeningSentenceType extends SentenceTypeBase {

    public static final ListeningSentenceType SENTENCE = new ListeningSentenceType();

    public ListeningSentenceType() {
        super(1);
    }

    @Override protected String generateSentence(Speaker source, Speaker recipient) {
        return SentenceUtils.pickRandomly("Really?", "Mm-m", "Go on", "Hmm", "You don't say?", "Mm-hmm", "...");
    }
}
