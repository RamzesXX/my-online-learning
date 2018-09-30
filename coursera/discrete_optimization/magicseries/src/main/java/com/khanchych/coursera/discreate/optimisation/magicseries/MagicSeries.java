package com.khanchych.coursera.discreate.optimisation.magicseries;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MagicSeries {

    boolean checkIfSeriesIsMagic(int[] seriesToCheck) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < seriesToCheck.length; i++) {
            map.compute(i, (key, oldValue) -> oldValue == null ? 0 : oldValue);
            map.compute(seriesToCheck[i], (key, oldValue) -> oldValue == null ? 1 : ++oldValue);
        }

        for (int i = 0; i < seriesToCheck.length; i++) {
            if (seriesToCheck[i] != map.get(i)) {
                return false;
            }
        }

        return true;
    }

    public int[] solve(int size) {
        int[] series = new int[size];

        return series;
    }


}

// X[0] = X[0] == 0 ? 1 :0
// X

class EquationMeta {
    private int index;
    private boolean isLeftSideDefined;
    private boolean isRightSideDefined;
    private int rightSideValue; // sum of defined values
    private List<Integer> currentValues;
    private Set<Integer> undefinedValues;

    EquationMeta(int index, List<Integer> currentValues) {
        this.index = index;
        this.currentValues = currentValues;
        this.undefinedValues = new HashSet<>();
        process();
    }

    void process() {
        undefinedValues.clear();
        isLeftSideDefined = true;
        isRightSideDefined = true;
        rightSideValue = 0;

        for (int i = 0; i < currentValues.size(); i++) {
            if (Objects.isNull(currentValues.get(i))) {
                undefinedValues.add(i);
                isRightSideDefined = false;
            } else {
                rightSideValue += currentValues.get(i) == index ? 1 : 0;
            }
        }
        isLeftSideDefined = !Objects.isNull(currentValues.get(index));
    }
}

class EquationSystem {
    private int size;
    private List<Set<Integer>> domain;
    private List<Integer> currentValues;

    private List<EquationMeta> equations;

    EquationSystem(int size) {
        this.size = size;
        this.domain = IntStream.range(0, size)
                .mapToObj((index) -> new HashSet<Integer>())
                .collect(Collectors.toList());
        this.currentValues = IntStream.range(0, size)
                .mapToObj((index) -> (Integer) null)
                .collect(Collectors.toList());
        this.equations = IntStream.range(0, size)
                .mapToObj((index) -> new EquationMeta(index, currentValues))
                .collect(Collectors.toList());
    }

    void process() {
        equations.forEach(EquationMeta::process);
    }

    List<Integer> getCurrentValues() {
        return currentValues;
    }
}