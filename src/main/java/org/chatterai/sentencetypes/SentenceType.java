package org.chatterai.sentencetypes;

import org.chatterai.speaker.Speaker;

/**
 *
 */
public interface SentenceType {

    String renderToText(Speaker source, Speaker recipient);

    double getDuration();
}
