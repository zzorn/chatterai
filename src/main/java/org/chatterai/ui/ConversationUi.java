package org.chatterai.ui;

import org.chatterai.conversation.Conversation;
import org.chatterai.conversation.ConversationLine;
import org.chatterai.conversation.ConversationListener;

import javax.swing.*;
import java.awt.*;

import static org.flowutils.Check.*;
import static org.flowutils.Check.notNull;

/**
 *
 */
public class ConversationUi implements ConversationListener {

    private Conversation conversation;

    private JTextArea historyView;
    private JPanel ui;

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        if (conversation != null) conversation.removeListener(this);

        this.conversation = conversation;
        if (historyView != null) historyView.setText("");

        if (conversation != null) conversation.addListener(this);
    }

    public JComponent getUi() {
        if (ui == null) {
            ui = createUi();
        }

        notNull(ui, "ui");

        return ui;
    }

    private JPanel createUi() {
        JPanel panel = new JPanel(new BorderLayout());

        historyView = new JTextArea();
        historyView.setEditable(false);
        historyView.setFont(new Font("sans", Font.PLAIN, 16));
        historyView.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(historyView);
        panel.add(scrollPane, BorderLayout.CENTER);

        panel.add(new JLabel("History"), BorderLayout.NORTH);

        return panel;
    }


    @Override public void onLineAdded(Conversation conversation, ConversationLine line) {
        if (historyView != null) {
            historyView.append(line.renderToText());
            historyView.append("\n");

            // Move to end
            historyView.setCaretPosition(historyView.getDocument().getLength());

            ui.repaint();
            historyView.repaint();
        }
    }
}
