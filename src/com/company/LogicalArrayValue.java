package com.company;

public interface LogicalArrayValue<V> {

    void set(int index, V v);

    V getValue();
    int getIndex();

    LogicalArray<V> getArray();

}
