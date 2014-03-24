package org.chatterai.speaker;

import org.chatterai.conversation.Conversation;
import org.chatterai.conversation.ConversationLine;
import org.chatterai.conversation.ConversationLineImpl;
import org.chatterai.sentencetypes.SentenceType;
import org.chatterai.util.RandomVar;
import org.chatterai.util.RangeRandomVar;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 */
public abstract class AiSpeakerBase implements Speaker {

    private double timeUntilNextSentence;
    private Deque<ConversationLine> queuedLines = new ArrayDeque<ConversationLine>();

    private String name;

    private RandomVar speakingSpeed = new RangeRandomVar(1, 0.2);
    private RandomVar lullDuration = new RangeRandomVar(10, 2);
    private RandomVar minimalPause = new RangeRandomVar(1, 0.5);
    private RandomVar initialPause = new RangeRandomVar(0.7, 0.3);

    protected AiSpeakerBase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public void startConversation(Conversation conversation) {
        timeUntilNextSentence = getInitialPause(conversation);
    }

    @Override public void endConversation(Conversation conversation) {
    }

    @Override public void updateConversation(Conversation conversation, double secondsSinceLastUpdate) {

        // Wait until we are ready to say something
        timeUntilNextSentence -= secondsSinceLastUpdate;
        if (timeUntilNextSentence <= 0) {

            ConversationLine conversationLine;

            // If we have something to say that we hadn't said yet, do it
            if (!queuedLines.isEmpty()) {
                conversationLine = queuedLines.removeFirst();
            }
            else {
                // Come up with something new
                conversationLine = determineSentence(conversation);
            }

            // If we said something, notify others
            if (conversationLine != null) {
                conversation.addLine(conversationLine);
            }

            // Pause a bit so that we don't rapid fire
            timeUntilNextSentence = calculateTimeUntilNextSentence(conversation, conversationLine);
        }
    }

    private double calculateTimeUntilNextSentence(Conversation conversation, ConversationLine conversationLine) {
        // Pause for roughly the time it took to say it, also depending on the speed / flow of the conversation
        // Could also pause to wait for response

        double speechTime = getMinimalPause(conversation);

        if (conversationLine != null) {
            speechTime += conversationLine.getDuration() * getSpeakingSpeed(conversation);
        }
        else if (queuedLines.isEmpty()) {
            // If we have nothing queued up, leave a lull in the conversation
            speechTime += getLullDuration(conversation);
        }

        return speechTime;
    }

    protected double getSpeakingSpeed(Conversation conversation) { return speakingSpeed.getNext(); }
    protected double getLullDuration(Conversation conversation)  { return lullDuration.getNext();  }
    protected double getMinimalPause(Conversation conversation)  { return minimalPause.getNext();  }
    protected double getInitialPause(Conversation conversation)  { return initialPause.getNext();  }

    @Override public void onLineAdded(Conversation conversation, ConversationLine line) {
        // Formulate some kind of response to the line, say it on the next update
        ConversationLine reply = determineReply(conversation, line);
        queueLine(conversation, reply);
    }

    protected void queueLine(Conversation conversation, Speaker target, SentenceType sentenceType) {
        queueLine(conversation, new ConversationLineImpl(this, target, sentenceType));
    }

    protected void queueLine(Conversation conversation, ConversationLine queuedLine) {
        if (queuedLine != null) {
            queuedLines.addLast(queuedLine);
            timeUntilNextSentence = getMinimalPause(conversation);
        }
    }

    protected abstract ConversationLine determineSentence(Conversation conversation);

    protected abstract ConversationLine determineReply(Conversation conversation, ConversationLine line);

    public RandomVar getSpeakingSpeed() {
        return speakingSpeed;
    }

    public void setSpeakingSpeed(RandomVar speakingSpeed) {
        this.speakingSpeed = speakingSpeed;
    }

    public RandomVar getLullDuration() {
        return lullDuration;
    }

    public void setLullDuration(RandomVar lullDuration) {
        this.lullDuration = lullDuration;
    }

    public RandomVar getMinimalPause() {
        return minimalPause;
    }

    public void setMinimalPause(RandomVar minimalPause) {
        this.minimalPause = minimalPause;
    }

    public RandomVar getInitialPause() {
        return initialPause;
    }

    public void setInitialPause(RandomVar initialPause) {
        this.initialPause = initialPause;
    }

    protected double getTimeUntilNextSentence() {
        return timeUntilNextSentence;
    }

    protected void setTimeUntilNextSentence(double timeUntilNextSentence) {
        this.timeUntilNextSentence = timeUntilNextSentence;
    }

    public Deque<ConversationLine> getQueuedLines() {
        return queuedLines;
    }
}
