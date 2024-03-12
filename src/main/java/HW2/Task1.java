package HW2;
/*
Написать метод, возвращающий количество чётных элементов массива.
countEvens([2, 1, 2, 3, 4]) → 3 countEvens([2, 2, 0]) → 3 countEvens([1, 3, 5]) → 0
 */
public class Task1 {
    public static void main(String[] args) {
        int[] array1 = {2, 1, 2, 3, 4, 5, 8, 12};
        System.out.println(countEvens(array1));
    }
    static int countEvens(int[] array) {
        int counter = 0;
        for (int i : array) if (i % 2 == 0) counter++;
        return counter;
    }

}
