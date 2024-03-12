package HW2;
/*
Написать функцию, возвращающую разницу между самым большим и
самым маленьким элементами переданного не пустого массива.
 */
public class Task2 {
    public static void main(String[] args) {
        System.out.println(maxMinDifference(new int[]{1, 4, 154, -98, 12, 345, 9, -34}));
    }
    static int maxMinDifference(int[] array) {
        if (array.length == 1) return 0;

        int minValue = array[0];
        int maxValue = array[1];

        if (array[0] > array[1]) {
            minValue = array[1];
            maxValue = array[0];
        }

        if (array.length > 2) {
            for (int i = 2; i < array.length; i++) {
                if (array[i] > maxValue)
                    maxValue = array[i];
                else if (array[i] < minValue)
                    minValue = array[i];
            }
        }
        return maxValue - minValue;
    }
}
