package org.chatterai.conversation;

import org.chatterai.speaker.Speaker;

import java.util.*;

/**
 *
 */
public class Conversation  {

    // TODO: Make conversation be between two people only?

    private Set<Speaker> speakers = new LinkedHashSet<Speaker>();
    private Set<ConversationListener> listeners = new HashSet<ConversationListener>();
    private List<ConversationLine> history = new ArrayList<ConversationLine>();
    private double totalDuration;

    /**
     * Allows NPC:s to say things or otherwise update their status regarding the conversation.
     *
     * @param secondsSince seconds since the last update call.
     */
    public void update(double secondsSince) {
        totalDuration += secondsSince;

        for (Speaker speaker : speakers) {
            speaker.updateConversation(this, secondsSince);
        }
    }

    // TODO: Method for updating state (e.g. mental stance, interest, etc)

    public void addLine(ConversationLine line) {
        history.add(line);

        // Notify listeners
        for (ConversationListener listener : listeners) {
            listener.onLineAdded(this, line);
        }

        // Notify other speakers of the new line
        for (Speaker speaker : speakers) {
            if (line.getSource() != speaker) {
                speaker.onLineAdded(this, line);
            }
        }
    }

    public void addPerson(Speaker speaker) {
        speakers.add(speaker);
        speaker.startConversation(this);
    }

    public void removePerson(Speaker speaker) {
        speaker.endConversation(this);
        speakers.remove(speaker);
    }

    public void addListener(ConversationListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ConversationListener listener) {
        listeners.remove(listener);
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public Set<Speaker> getSpeakers() {
        return speakers;
    }
}
