package com.company;

public class QuickSorter {

    public String[] sort(LogicalArray<String> array) {
        ArrayEntry<String> pivot = choosePivot(array);
        Split<String> split = splitArray(array.without(pivot), pivot);

        sort(split.before);
        sort(split.after);

        LogicalArray<String> result = new TwoSliceLogicalArray<String>(String[].class, split.before, split.after);
        split.mergeToArray(result);
        return result.asArray();
    }

    private Split<String> splitArray(LogicalArray<String> array, ArrayEntry<String> pivot) {
        LogicalArray<String> before = array.cloneArray();
        ArrayEntry<String> beforeEntry = new ArrayEntry<String>(0, array);
        LogicalArray<String> after = array.cloneArray();
        ArrayEntry<String> afterEntry = new ArrayEntry<String>(0, array);
        ArrayEntry currentEntry = null;
        LogicalArray<String> currentArray = null;
        for (String s: array.asArray()) {
            try {
                if (s.compareTo(pivot.getValue().getValue()) == 1) {
                    currentEntry = beforeEntry;
                    currentArray = before;
                } else {
                    currentEntry = afterEntry;
                    currentArray = after;
                }
                currentEntry.copyToArray(currentArray);
                currentEntry.slideToNextValueInTheArray(1);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return new Split<String>(before, pivot, after);
    }

    private ArrayEntry<String> choosePivot(LogicalArray<String> array) {
        return new ArrayEntry<String>(0, array.getLogicalValueByIndex(0));
    }
}
