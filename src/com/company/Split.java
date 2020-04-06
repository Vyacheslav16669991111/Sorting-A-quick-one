package com.company;

public class Split<V> implements LogicalArray<V> {

    public LogicalArray<V> before;
    public LogicalArray<V> after;
    public ArrayEntry<V> pivot;

    public Split(LogicalArray<V> before, ArrayEntry<V> pivot, LogicalArray<V> after) {
        this.before = new LogicalArrayImpl<V>((Class<V[]>) before.asArray().getClass(), before.asArray(), before.size());
        this.pivot = pivot;
        this.after = new LogicalArrayImpl<V>((Class<V[]>) before.asArray().getClass(), after.asArray(), after.size());
    }

    public int size() {
        return before.size() + 1 + after.size();
    }

    public V getByIndex(int i) {
        if (i < before.size()) {
            return before.asArray()[i];
        } else if (i == before.size()) {
            return pivot.getValue().getValue();
        } else {
            i -= before.size();
            return after.asArray()[i];
        }
    }

    @Override
    public void setByIndex(int index, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V[] asArray() {
        return null;
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

    public void mergeToArray(LogicalArray<V> result) {
            for (int i = 0; i < size(); i++) {
                result.asArray()[i] = getByIndex(i);
            }
    }
}
