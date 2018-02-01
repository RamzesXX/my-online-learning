//Дан массив чисел A. Напишите метод, который упорядочит массив следующим образом: A[0]<A[1]>A[2]<A[3]>A[4]…

import java.util.Arrays;

public class SortArray {

    public static void main(String[] args) {
        int[] massive = {1, 5, 6, 3, 8, 2, 9, 7, 4};
        System.out.println("Input: " + Arrays.toString(massive));
        System.out.println("Output: " + Arrays.toString(new SortArray().sort(massive)));
    }

    int[] sort(int[] massive) {
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
