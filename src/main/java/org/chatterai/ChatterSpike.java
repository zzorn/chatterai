package org.chatterai;

import org.chatterai.conversation.Conversation;
import org.chatterai.speaker.AiSpeakerBase;
import org.chatterai.speaker.PlayerSpeaker;
import org.chatterai.speaker.SimpleAiSpeaker;
import org.chatterai.ui.ConversationUi;
import org.flowutils.SimpleFrame;

import javax.swing.*;

/**
 * Spike testing out the conversation system.
 *
 */
public class ChatterSpike {

    private static final double SECONDS_BETWEEN_UPDATES = 0.1;

    public static void main( String[] args ) {

        // Create player and NPCs
        PlayerSpeaker player = new PlayerSpeaker("Hero");
        AiSpeakerBase bartender = new SimpleAiSpeaker("Igor");
        AiSpeakerBase barmaid = new SimpleAiSpeaker("Isabella");

        // Create conversation
        Conversation conversation = new Conversation();
        conversation.addPerson(player);
        conversation.addPerson(bartender);
        conversation.addPerson(barmaid);

        // Create UI to view and participate in conversation
        final ConversationUi conversationUi = new ConversationUi();
        conversationUi.setConversation(conversation);

        // Show the UI
        final JComponent ui = conversationUi.getUi();
        new SimpleFrame("Conversation Spike", ui);

        // Update loop
        while (true) {
            conversation.update(SECONDS_BETWEEN_UPDATES);

            delay(SECONDS_BETWEEN_UPDATES);
        }
    }

    private static void delay(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            // Ignore
        }
    }
}
