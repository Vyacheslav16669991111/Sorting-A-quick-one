package com.company;

public class NSliceLogicalArray<V> implements LogicalArray<V> {

    private LogicalArray<V>[] arrays;

    @Override
    public int size() {
        int sum = 0;
        for (LogicalArray<V> array: arrays) {
            sum += array.size();
        }
        return sum;
    }

    @Override
    public V getByIndex(int index) {
        return null;
    }

    @Override
    public void setByIndex(int index, V value) {

    }

    @Override
    public V[] asArray() {
        return null;
    }

    @Override
    public void lowerSize(int index) {

    }

    @Override
    public LogicalArrayValue<V> getLogicalValueByIndex(int index) {
        return null;
    }

    @Override
    public LogicalArray<V> cloneArray() {
        return null;
    }

    @Override
    public V[] clonePhysicalArray() {
        return null;
    }

    @Override
    public LogicalArray<V> without(ArrayEntry<V> entry) {
        return null;
    }
}
