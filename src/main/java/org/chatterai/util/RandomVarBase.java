package org.chatterai.util;

import java.util.Random;

/**
 *
 */
public abstract class RandomVarBase implements RandomVar {

    private static Random random = new Random();

    @Override public final double getNext() {
        return getNext(random);
    }

}
