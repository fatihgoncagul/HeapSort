
import java.util.*;

/**
 * @author fatih goncagül
 */
public class heapSortk {

    public static void main(String[] args) {

        int[] array = new int[1000];
        Random random = new Random(20118681214L);

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
            for (int j = 0;j<i;j++){
                if (array[i]==array[j]){
                i--;
               break;
                }
            }
        }
        int k = findTheK(array);
        long start = System.nanoTime();
        kHeapSort(array, array.length - 1, k);
        //quickSort(array,0, array.length-1);
        long duration = System.nanoTime() - start;
        System.out.println("Execution time is " + duration + " nanoseconds for k = "+k + " and array size of " + array.length);

    }


    public static void kHeapSort(int[] arr, int n, int k) {

        /**
         * •Create a min heap
         */
        PriorityQueue<Integer> priorityQueue
                = new PriorityQueue<>();

        /**
         * •Add k+1 number of values of the array to the min heap you created
         */
        for (int i = 0; i < k + 1; i++) {
            if (i < arr.length) {
                priorityQueue.add(arr[i]);
            }
        }

        /**
         * •One by one, remove min element from heap and add them to array, and add remaining element to the heap
         */
        int counter = 0;
        for (int i = k + 1; i < n; i++) {
            arr[counter++] = priorityQueue.peek();
            priorityQueue.poll();
            priorityQueue.add(arr[i]);
        }

        /**
         * •Pop all remaining elements from the min heap and assign them to the next available array index
         */
        Iterator<Integer> itr = priorityQueue.iterator();

        while (itr.hasNext()) {
            arr[counter++] = priorityQueue.peek();
            priorityQueue.poll();
        }
    }

    /**
     * it finds k for k sorted arrays
     *
     * @param
     * @return
     */

    public static int findTheK(int[] arr) {

        int[] arrayCopy = new int[arr.length];
        System.arraycopy(arr, 0, arrayCopy, 0, arr.length);
        quickSort(arr, 0, arr.length - 1);

        int max=0;
        int distance=0;
        for(int i=0;i<arrayCopy.length;i++){
            for(int j=0;j<arrayCopy.length;j++){
                if(arrayCopy[i]==arr[j]){
                    distance=Math.abs(i-j);
                    if(distance>max){
                        max=distance;
                    }
                }
            }
        }
        return max;
    }


    //  QuickSort algorithm is below.
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }
}


