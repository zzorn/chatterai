package org.chatterai.speaker;

import org.chatterai.conversation.Conversation;
import org.chatterai.conversation.ConversationLine;
import org.chatterai.sentencetypes.GreetingSentence;
import org.chatterai.sentencetypes.ListeningSentenceType;
import org.chatterai.sentencetypes.LullSentenceType;
import org.chatterai.sentencetypes.SentenceType;
import org.chatterai.util.RandomVar;
import org.chatterai.util.RangeRandomVar;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class SimpleAiSpeaker extends AiSpeakerBase {

    private Set<Speaker> greetedSpeakers = new HashSet<Speaker>();

    private RandomVar talkativeness = new RangeRandomVar(0.7, 0.3);


    public SimpleAiSpeaker(String name) {
        super(name);
    }

    @Override protected ConversationLine determineSentence(Conversation conversation) {

        // Greet any new people
        for (Speaker speaker : conversation.getSpeakers()) {
            if (speaker != this && !greetedSpeakers.contains(speaker)) {
                queueLine(conversation, speaker, GreetingSentence.SENTENCE);
                greetedSpeakers.add(speaker);
            }
        }

        // Fill emptiness with sound
        if (getQueuedLines().isEmpty() && isTalkative()) {
            queueLine( conversation, null, LullSentenceType.SENTENCE);
        }

        return null;
    }

    @Override protected ConversationLine determineReply(Conversation conversation, ConversationLine line) {
        // Inject generic reply if we are talkative
        final SentenceType sentenceType = line.getSentenceType();
        if (isTalkative() &&
            !ListeningSentenceType.class.isInstance(sentenceType) &&
            !GreetingSentence.class.isInstance(sentenceType) ) {
            queueLine(conversation, line.getSource(), ListeningSentenceType.SENTENCE);
        }

        return null;
    }

    private boolean isTalkative() {
        return talkativeness.getNext() > 0.5;
    }


}
