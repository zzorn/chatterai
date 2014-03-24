package org.chatterai.sentencetypes;

import org.chatterai.speaker.Speaker;
import org.chatterai.util.SentenceUtils;

import static org.chatterai.util.SentenceUtils.*;

/**
 *
 */
public class LullSentenceType extends SentenceTypeBase {
    public static final LullSentenceType SENTENCE = new LullSentenceType();

    protected LullSentenceType() {
        super(4);
    }

    @Override protected String generateSentence(Speaker source, Speaker recipient) {
        return pickRandomly("So...", "Well...", "Did you want anything?", "Hrm", "Can I help you?", "...", "So, well..");
    }
}
