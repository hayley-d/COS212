import java.io.*;
import java.util.Objects;

class MultiwayMergeExternalSort {
    private Memory memory;
    private int numFiles;

    private BufferedReader[] readers;
    private BufferedWriter[] writers;

    private BufferedReader currentRead;
    private BufferedWriter currentWrite;


    private int outputFileIndex;

    private String[] tempA;
    private String[] tempB;


    private String inputTemp;
    private String outputTemp;
    public MultiwayMergeExternalSort(Memory memory, int numFiles)
            throws IOException {
        this.memory = memory;
        this.numFiles = numFiles;
        this.readers = new BufferedReader[numFiles];
        this.writers = new BufferedWriter[numFiles];
        this.inputTemp = "B";
        this.outputTemp = "A";
        outputFileIndex = 0;
        tempA = new String[numFiles];
        tempB = new String[numFiles];
    }

    /**
     * Open method creates the temp files and adds them into approprate arrays
     * @param inputFile Path to the input file containing unsorted values.
     * @param outputFile Path to the output file where sorted values will be written.
     */
    public void open(String inputFile, String outputFile) throws IOException
    {
        // Set the current read and write file
        this.currentRead = new BufferedReader(new FileReader(inputFile));
        this.currentWrite = new BufferedWriter(new FileWriter(outputFile));

        // Create the temp files
        for(int i = 0; i < numFiles; i++)
        {
            // Initialize the file names
            String tempAFileName = "tmp/T_a_" + i + ".tmp";
            String tempBFileName = "tmp/T_b_" + i + ".tmp";

            //Create the files if they do not exist
            File tempAFile = new File(tempAFileName);
            if (!tempAFile.exists()) {
                tempAFile.createNewFile();
            }

            File tempBFile = new File(tempBFileName);
            if (!tempBFile.exists()) {
                tempBFile.createNewFile();
            }

            // Add the files names into the array
            tempA[i] = tempAFileName;
            tempB[i] = tempBFileName;
        }
    }


    public void close() throws IOException {
        // Close readers and writers for temporary files
        for (int i = 0; i < numFiles; i++) {
            if (readers[i] != null && !readers[i].ready())
            {
                readers[i].close();
            }
            if (writers[i] != null)
            {
                writers[i].close();
            }
        }

        // Close the currentRead and currentWrite files
        // Close the currentWrite file if it's open for writing
        if (currentWrite != null)
        {
            currentWrite.close();
        }

        // Close the currentRead file if it's open for reading
        if (currentRead != null && currentRead.ready()) {
            currentRead.close();
        }
    }

    /**
     * Performs the initial splitting of the input file moving data chunks into temp files to be merged and sorted later
     */
    public void initialSort() throws IOException
    {
        //Open all the A files for writing:
        for(int i = 0; i < numFiles; i++)
        {
            //open the file as a writer
            writers[i] = new BufferedWriter(new FileWriter(tempA[i]));
        }

        String line = "";
        int index = 0;

        while((line = currentRead.readLine()) != null)
        {
            memory.set(index++,Integer.parseInt(line));

            //Once the memory is full
            if(index == memory.getSize())
            {
                sortAndWrite(outputFileIndex);
                outputFileIndex++;
                if(outputFileIndex == numFiles)
                {
                    outputFileIndex = 0; // Reset the output file
                }
                index = 0;
            }
        }

        //Handel any data remaining in memory
        if(index > 0)
        {
            sortAndWrite(outputFileIndex);
        }

        //close the temp files for writing and open for reading
        for(int i = 0; i < numFiles; i++)
        {
            //Close the writer
            writers[i].close();
            writers[i] = null;

            // Open for reading
            readers[i] = new BufferedReader(new FileReader(tempA[i]));
        }

        //close the intial file
        currentRead.close();
    }


    /**
     * Sort the data in memory and write into an output file
     * @param index Index of the writer file in the writer array
     */
    private void sortAndWrite(int index) throws IOException{
        //Sort the memory (quicksort)
        quickSort();

        //Write the sorted data to one of the output files
        for (int value : memory.getData())
        {
            writers[index].write(Integer.toString(value));
            writers[index].newLine();
        }

        //clear memory
        memory.clear();
    }

    public boolean step() throws NumberFormatException, IOException
    {
        //Get the correct Array to read from
        String[] inputTemps = getReadArray();

        //Open the output file for writing
        String[] outTemps = getOutArray();
        BufferedWriter writer = new BufferedWriter(new FileWriter(outTemps[0]));

        int[] currentValues = new int[memory.getSize()];

        //Open the files to read from
        for(int i = 0; i < numFiles; i++)
        {
            //open the file as a writer
            readers[i] = new BufferedReader(new FileReader(inputTemps[i]));
            //Read in the initial values
            currentValues[i] = Integer.parseInt(readers[i].readLine());
        }

        while(true)
        {
            int minIndex = findMinIndex(currentValues);

            if(minIndex == -1){
                break; // No more values to merge
            }

            //Write the min value to the output file
            writer.write(currentValues[minIndex] + '\n');

            // Read the next value from the corresponding input file
            String element = readers[minIndex].readLine();
            if (element != null)
            {
                currentValues[minIndex] = Integer.parseInt(element);
            } else {
                // If no more values in the input file, set the value to infinity
                currentValues[minIndex] = Integer.MAX_VALUE;
            }
        }

        // Close input and output files
        for (int i = 0; i < numFiles; i++) {
            readers[i].close();
        }
        writer.close();

        return false;
    }

    public void writeToOutput() throws IOException
    {
        //Write the sorted data to the final output file
        for (int i = 0; i < memory.getSize();i++)
        {
            if(i < memory.getSize()-1)
            {
                currentWrite.write(Integer.toString(memory.get(i)));
                currentWrite.newLine();
            }
            else if(i == memory.getSize()-1)
            {
                // Do not write a new line for the last line of the file
                currentWrite.write(Integer.toString(memory.get(i)));
            }
        }

        //close the files
        close();

        //clear memory
        memory.clear();
    }


    /**
     * checks if the given file is empty
     * @param fileName The name of the file to check
     */
    private boolean isEmpty(String fileName)
    {
        return false;
    }

    private String[] getReadArray(){
        if(outputTemp.equals("A"))
        {
            return this.tempA;
        }
        else{
            return this.tempB;
        }
    }

    private String[] getOutArray(){
        if(outputTemp.equals("A"))
        {
            return this.tempB;
        }
        else{
            return this.tempA;
        }
    }

    // Find the index of the minimum value in the array
    private static int findMinIndex(int[] arr)
    {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * Recursive helper method to perform Quick Sort.
     * @param low The index of the lowest element of the partition.
     * @param high The index of the highest element of the partition.
     */
    private void quickSort(int low, int high)
    {
        if(low < high)
        {
            // Partition the array and get the pivot index
            int pivotIndex = partition(low,high);

            // Recursivly sort the left and right partitions
            quickSort(low,pivotIndex-1);
            quickSort(pivotIndex+1,high);
        }
    }

    /**
     * Partitions the array and returns the index of the pivot element.
     * @param low The index of the lowest element of the partition.
     * @param high The index of the highest element of the partition.
     * @return The index of the pivot element.
     */
    private int partition(int low,int high)
    {
        Integer[] data = memory.getData();
        int pivot = data[high];
        int i = low-1;

        for(int j = low;j< high;j++){
            // If the current element is smaller/equal to
            if(data[j] <= pivot)
            {
                i++;

                //swap the elemenets
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }

        int temp = data[i+1];
        data[i+1] = data[high];
        data[high] = temp;

        return i+1;
    }

    /**
     * Sorts an array using the Quick Sort algorithm.
     */
    public void quickSort()
    {
        quickSort(0, memory.getSize() - 1);
    }
}
