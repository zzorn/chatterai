package org.chatterai.util;

import java.util.Random;

/**
 * Represents a random value with some distribution.
 */
public interface RandomVar {

    double getNext();

    double getNext(Random random);
}
