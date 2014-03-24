package org.chatterai.util;

import org.flowutils.Check;

import java.util.Random;

/**
 * A random variable with a specified average and a linear distribution around that with a specified total range.
 */
public final class RangeRandomVar extends RandomVarBase {

    private double average;
    private double range;

    public RangeRandomVar(double average, double range) {
        setAverage(average);
        setRange(range);
    }

    @Override public double getNext(Random random) {
        return average + (random.nextDouble() * range) - range * 0.5;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        Check.positiveOrZero(range, "range");

        this.range = range;
    }
}
