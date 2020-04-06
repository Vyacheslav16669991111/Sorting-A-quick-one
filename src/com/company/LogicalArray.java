package com.company;

public interface LogicalArray<V> {

    int size();
    V getByIndex(int index);
    void setByIndex(int index, V value);
    V[] asArray();
    void lowerSize(int index);

    LogicalArrayValue<V> getLogicalValueByIndex(int index);

    LogicalArray<V> cloneArray();

    V[] clonePhysicalArray();

    LogicalArray<V> without(ArrayEntry<V> entry);
}
