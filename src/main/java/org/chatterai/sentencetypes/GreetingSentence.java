package org.chatterai.sentencetypes;

import org.chatterai.speaker.Speaker;

import static org.chatterai.util.SentenceUtils.*;

/**
 *
 */
public class GreetingSentence extends SentenceTypeBase {

    public static GreetingSentence SENTENCE = new GreetingSentence();

    public GreetingSentence() {
        super(0.5);
    }

    @Override protected String generateSentence(Speaker source, Speaker recipient) {
        // NOTE: We could have something that picks with a bias, allowing e.g. current level of familiarity affecting a word choice.
        return pickRandomly("Greetings", "Hello", "Hello", "Hi", "Howdy") + " " + recipient.getName() + "!";
    }
}
