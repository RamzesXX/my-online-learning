package com.khanchych.fridaytasks;
//Дан массив чисел A. Напишите метод, который упорядочит массив следующим образом: A[0]<A[1]>A[2]<A[3]>A[4]…

import java.util.Arrays;

public class SortArray {

    public int[] sort(int[] massive) {
        Arrays.sort(massive);

        for (int i = 1; i < massive.length; i += 2) {
            if (i != massive.length - 1) {
                int tmp = massive[i];
                massive[i] = massive[i + 1];
                massive[i + 1] = tmp;
            }
        }

        return massive;
    }

}
