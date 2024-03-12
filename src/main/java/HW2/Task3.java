package HW2;

import java.util.Arrays;

/*
Написать функцию, возвращающую истину, если в переданном массиве есть два соседних элемента, с нулевым значением.
 */
public class Task3 {
    public static void main(String[] args) {
        int[] array1 = {0, 1, 1, 76, 0, -89, 2, 0, 0, 3};

        System.out.println(doubleZeroCheck(array1));
        System.out.println(doubleZeroCheckAlt(array1));
    }
    static boolean doubleZeroCheck(int[] array) {
        for (int i = 0; i < array.length - 1;)
            if (array[i++] == 0 && array[i] == 0) return true;

        return false;
    }

    static boolean doubleZeroCheckAlt(int[] array) {
        String arrayStr = Arrays.toString(array);
        if (arrayStr.indexOf("0, 0") != -1) return true;
        return false;
    }
}
