package com.company;

public class ArrayEntry<V> {

    private int index;
    private LogicalArrayValue<V> value;

    public ArrayEntry(int index, LogicalArrayValue<V> value) {
        this.index = index;
        this.value = value;
    }

    public ArrayEntry(int index, LogicalArray<V> array) {
        this(index, array.getLogicalValueByIndex(index));
    }

    public void slideToNextValueInTheArray(int offset) {
        index += offset;
        this.value = value.getArray().getLogicalValueByIndex(index);
    }

    public void copyToArray(LogicalArray<V> array) {
        array.setByIndex(index, value.getValue());
    }

    public LogicalArrayValue<V> getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
