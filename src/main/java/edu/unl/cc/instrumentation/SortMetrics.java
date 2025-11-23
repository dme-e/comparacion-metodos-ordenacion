package edu.unl.cc.instrumentation;

public class SortMetrics {
    public long comparisons = 0;
    public long swaps = 0;
    public long timeNs = 0;

    public void reset() {
        comparisons = 0;
        swaps = 0;
        timeNs = 0;
    }

    @Override
    public String toString() {
        return "comparisons=" + comparisons +
                ", swaps=" + swaps +
                ", time=" + timeNs + "ns";
    }
}
