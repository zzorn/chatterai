package org.chatterai.util;

import java.util.Random;

/**
 *
 */
public class SentenceUtils {

    private static Random random = new Random();

    public static String pickRandomly(String ... alternatives) {
        if (alternatives.length > 0) {
            int i = random.nextInt(alternatives.length);
            return alternatives[i];
        }
        else {
            return "";
        }
    }

}
