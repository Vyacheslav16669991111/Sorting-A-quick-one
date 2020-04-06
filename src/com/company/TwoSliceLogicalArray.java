package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSliceLogicalArray<V> implements LogicalArray<V> {

    private LogicalArray<V> first;
    private LogicalArray<V> second;
    private int size;
    private Class<V[]> clazz;

    public TwoSliceLogicalArray(Class<V[]> clazz, LogicalArray<V> first, LogicalArray<V> second) {
        this.first = first;
        this.second = second;
        this.size = first.size() + second.size();
    }

    @Override
    public int size() {
        return first.size() + second.size();
    }

    @Override
    public V getByIndex(int index) {
        if(first.size() > index) {
            return first.getByIndex(index);
        } else {
            return second.getByIndex(index - first.size());
        }
    }

    @Override
    public void setByIndex(int index, V value) {
        if(first.size() > index) {
            first.setByIndex(index, value);
        } else {
            second.setByIndex(index - first.size(), value);
        }
    }

    @Override
    public LogicalArray<V> cloneArray() {
        V[] result = asArray();
        return new LogicalArrayImpl<V>(clazz, result, result.length);
    }

    @Override
    public V[] clonePhysicalArray() {
        return asArray();
    }

    @Override
    public LogicalArray<V> without(ArrayEntry<V> entry) {
        if(entry.getIndex() < first.size()) {
            return new TwoSliceLogicalArray<V>(clazz, first.without(entry), second);
        } else {
            return new TwoSliceLogicalArray<V>(clazz, first, second.without(entry));
        }
    }

    @Override
    public V[] asArray() {
        V[] result = null;
        try {
            result = clazz.getConstructor(new Class[] {int.class}).newInstance(first.size() + second.size());
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        int i = 0;
        for(V object: first.asArray()) {
            result[i] = object;
            i++;
        }
        for(V object: second.asArray()) {
            result[i] = object;
            i++;
        }
        return result;
    }

    @Override
    public void lowerSize(int size) {
        this.size = size;
    }

    @Override
    public LogicalArrayValue<V> getLogicalValueByIndex(int index) {
        return new LogicalArrayValueImpl<V>(this, index);
    }
}
