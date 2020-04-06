package com.company;

public class LogicalArrayValueImpl<V> implements LogicalArrayValue<V> {

    private LogicalArray<V> array;
    private int index;

    public LogicalArrayValueImpl(LogicalArray<V> array, int index) {
        this.array = array;
        this.index = index;
    }

    @Override
    public void set(int index, V value) {
        this.index = index;
        array.setByIndex(index, value);
    }

    @Override
    public V getValue() {
        return array.getByIndex(index);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public LogicalArray<V> getArray() {
        return array;
    }
}
