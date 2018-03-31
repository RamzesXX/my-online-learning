package com.khanchych.cyberdojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Roman Khanchych, [Mar 30, 2018 at 11:25:21 PM]:
 * ...имеется N видов книг,  скидка вычисляется как 30*m/N если у тебя m разных книг. Найти максимаьлную скидку для заданного набора книг
 * <p>
 * однозначно нужно в начале проанализировать самые выгодные комбинации
 * <p>
 * то есть мы рассматриваем все пары типа K и L  (К-L>1) и выстраиваем таблицу выгодности относительно пары (k-1) и (l+1)
 * <p>
 * где k и l количества разных книг в наборах элементы таблицы -  разность скидок между наборами (K,L) и (K-1,L+1)
 * <p>
 * после этого мы формируем наборы исходя и максимальной длины последовательности (то что ты делал в своем алгоритме)
 * <p>
 * но при этом мы сохраняем наши полученные последовательности
 * <p>
 * и потом пытаемся с использованием "таблицы выгодности" улучшить нашу последовательность
 * <p>
 * перебасывая внутри пары из одной последовательности в другую элемент
 */
public class Scratch {
    private int bookTypes;
    private float price;
    private float[][] matrix;

    public Scratch(int bookTypes, float price, int[] input) {
        this.bookTypes = bookTypes;
        this.price = price;
        fillMatrix();
    }


    private void fillMatrix() {
        matrix = new float[bookTypes + 1][];
        for (int i = 1; i < bookTypes + 1; i++) {
            matrix[i] = new float[i + 1];
            for (int j = 0; j < i; j++) {
                matrix[i][j] = i * discount(i) + j * discount(j) - (i - 1) * discount(i - 1) - (j + 1) * discount(j + 1);
            }
        }
    }

    private Map<Integer, List<Set<Integer>>> findFirstSolution(int[] inputData) {
        Set<Integer> set;
        Map<Integer, List<Set<Integer>>> solution = new HashMap<>();

        do {
            set = new HashSet<>();

            for (int i = 0; i < inputData.length; i++) {
                if (inputData[i] > 0) {
                    set.add(i);
                    inputData[i]--;
                }
            }

            if (!set.isEmpty()) {
                List<Set<Integer>> seriesOfTheSameSize = solution.getOrDefault(set.size(), new ArrayList<>());
                seriesOfTheSameSize.add(set);
                solution.put(set.size(), seriesOfTheSameSize);
            }
        } while (!set.isEmpty());

        return solution;
    }

    private float discount(int amountOfDistinctBook) {
        float discounts[] = {0f, 0f, 0.05f, 0.1f, 0.20f, 0.25f};

        return discounts[amountOfDistinctBook];
    }

    private float discountProgressive(int amountOfDistinctBook) {
        if (amountOfDistinctBook < 2) {
            return 0.0f;
        }

        return 0.3f * Math.min(amountOfDistinctBook, bookTypes) / bookTypes;
    }

    public static void main(String[] args) {
        Scratch scratch = new Scratch(5, 1.0f, new int[]{2, 2, 2, 1, 1});
        Map<Integer, List<Set<Integer>>> s = scratch.findFirstSolution(new int[]{2, 2, 2, 1, 1});
        for (int i = 0; i < scratch.bookTypes + 1; i++) {
            System.out.print(i + "   ");
            System.out.print(scratch.discount(i) + "   ");
            System.out.println(Arrays.toString(scratch.matrix[i]));
        }
    }
}