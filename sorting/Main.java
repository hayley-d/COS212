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
}
