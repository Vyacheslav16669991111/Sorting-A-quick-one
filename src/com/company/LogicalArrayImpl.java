package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public class LogicalArrayImpl<V> implements LogicalArray<V> {
    private V[] physicalArray;
    private int size;
    private Class<V[]> clazz;

    public LogicalArrayImpl(Class<V[]> clazz, V[] physicalArray, int size) {
        this.physicalArray = physicalArray;
        this.size = size;
        this.clazz = clazz;
    }

    public int size() {
        return size;
    }

    public V getByIndex(int index) {
        return physicalArray[index];
    }

    public void setByIndex(int index, V value) {
        physicalArray[index] = value;
    }

    public void setByIndex(int index, LogicalArrayValue<V> value) {
        physicalArray[index] = value.getValue();
    }

    public V[] asArray() {
        return physicalArray;
    }

    @Override
    public void lowerSize(int index) {
        this.size = size;
    }

    @Override
    public LogicalArrayValue<V> getLogicalValueByIndex(int index) {
        return new LogicalArrayValueImpl<>(this, index);
    }

    @Override
    public LogicalArray<V> cloneArray() {
        return new LogicalArrayImpl<V>(clazz, clonePhysicalArray(), size);
    }

    @Override
    public V[] clonePhysicalArray() {
        V[] result = null;
        try {
            result = clazz.getConstructor(new Class[]{int.class}).newInstance(this.size() - 1);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public LogicalArray<V> after(ArrayEntry<V> entry) {
        LogicalArrayValue<V> value = entry.getValue();
        LogicalArray<V> array = value.getArray();
        int offset = value.getIndex();
        V[] result = asArray();
        for(int i = offset + 1; i < array.size(); i++) {
            result[i] = array.getByIndex(i);
        }
        return new LogicalArrayImpl<V>(clazz, result, result.length);
    }

    public LogicalArray<V> before(ArrayEntry<V> entry) {
        LogicalArrayValue<V> value = entry.getValue();
        LogicalArray<V> array = value.getArray();

        int offset = value.getIndex();
        V[] result = asArray();
        for(int i = 0; i < offset; i++) {
            result[i] = array.getByIndex(i);
        }
        return new LogicalArrayImpl<V>(clazz, result, result.length);
    }

    public LogicalArray<V> without(ArrayEntry<V> pivot) {
        return new TwoSliceLogicalArray<V>(clazz, this.before(pivot), this.after(pivot));
    }
}
