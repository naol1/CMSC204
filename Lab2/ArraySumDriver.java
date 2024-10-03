public class ArraySumDriver {
    public static void main(String[] args) {
         ArraySum arraySum = new ArraySum();
        
         Integer[] array = {1, 2, 3, 4, 5};

         int totalSum = arraySum.sumOfArray(array, 0);

         System.out.println("The sum of the array is: " + totalSum);
    }
}
