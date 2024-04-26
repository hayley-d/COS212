public class Main {

    public static void main(String[] args)
    {

    }

    public static void insertionSort(int[] array)
    {
        int len = array.length;
        int sorted = 0;
        //Loop through array
        for(int i = 1; i < len; i++)
        {
            int currentElement = array[i];
            int j = i-1;

            while(j >= 0 && array[j] > currentElement)
            {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = currentElement;
        }
    }

    public static void selectionSort(int[] array)
    {
        int length = array.length;
        int smallestIndex = 0;

        for(int i = 0; i < length-1;i++)
        {
            smallestIndex = i;
            /*Find smallest element*/
            for(int j = i+1; j < length; j++)
            {
                if(array[j] < array[smallestIndex])
                {
                    smallestIndex = j;
                }
            }
            /*swap*/
            int temp = array[smallestIndex];
            array[smallestIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void bubbleSort(int[] array)
    {
        int length = array.length;

        for(int i = length-1; i > 0; i--)
        {
            for(int j = 0; j < i; j++)
            {
                if(array[j] > array[j+1])
                {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    public static void combSort(int[] array)
    {
        int length = array.length;
        int gap = length;
        double shrink_factor = 1.3;
        boolean swapped = true;

        while(gap > 1 || swapped)
        {
            //Reset Swapped
            swapped = false;

            //Calculate the next gap
            if(gap > 1)
            {
                gap = (int) Math.floor(gap/shrink_factor);
            }
            else{
                gap = 1;
            }

            //compare elements
            int i = 0;
            while(i+gap < length)
            {
                if(array[i] > array[i + gap])
                {
                    int temp = array[i];
                    array[i] = array[i + gap];
                    array[i + gap] = temp;
                    swapped = true;
                }
                i++;
            }
        }
    }

    public static void shellSort(int [] array,int n)
    {
        //Get the length of the array
        int length = array.length;

        //define the initial gap
        int gap = 3;

        // Start with a big gap, then reduce the gap by dividing it by 2
        for (int gap = n; gap > 0; gap /= 2)
        {
            // Iterate over the array starting from the current gap value
            for (int i = gap; i < n; i++)
            {
                // Store the current element in a temporary variable
                int temp = array[i];
                int j;
                // Move elements of array that are greater than temp into their respective positions
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap)
                {
                    array[j] = array[j - gap];
                }
                // Place temp (the current element being sorted) into its correct position
                array[j] = temp;
            }
        }
    }

    public static void heapSort(int[] array)
    {
        int length = array.length;

        // Build heap (rearrange array)
        for (int i = length / 2 - 1; i >= 0; i--)
        {
            heapify(arr, length, i)
        }

        // One by one extract an element from heap
        for (int i = length - 1; i > 0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private void heapify(int [] array, int length, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest])
        {
            largest = l;
        }

        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest])
        {
            largest = r;
        }

        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }
}
