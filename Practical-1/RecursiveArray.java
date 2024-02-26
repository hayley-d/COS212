import java.util.Objects;

public class RecursiveArray {
    public Integer[] array;

    public RecursiveArray() {
        //Default constructor
        this.array = new Integer[0];
    }

    public RecursiveArray(String input) {
        if(input.length() == 0)
        {
            this.array = new Integer[0];
            return;
        }
        //Convert into a string without commas
        String[] temp = input.split(",");

        //set the size of the array
        this.array = new Integer[temp.length];

        //add elements
        populateInitialArray(temp,0);
    }

    @Override
    public String toString() {
        if(array.length == 0)
        {
            return "Empty Array";
        }
        else{
            String arrayString = "[";
            return toStringHelper(0,arrayString);
        }
    }

    public void append(Integer value) {
        if(value == null)
        {
            return;
        }
        int length = array.length+1;
        Integer[] newArray = new Integer[length];
        populateArray(newArray,0,0); //create new arry with populated data fro existing array
        this.array = newArray;
        int target = array.length-1;
        addData(0,target,value);
    }

    public void prepend(Integer value) {
        if(value == null)
        {
            return;
        }
        int length = this.array.length+1;
        Integer[] newArray = new Integer[length];

        if(length == 1)
        {
            newArray[0] = value;
            this.array = newArray;
            return;
        }

        populateArray(newArray,1,0);
        this.array = newArray;
        int target = 0;
        addData(0,target,value);
    }

    public boolean contains(Integer value) {
        if(value == null || array.length == 0)
        {
            return false;
        }
        Integer dataIndex = findData(0,value);
        if(dataIndex != null)
        {
            return true;
        }
        return false;
    }

    public boolean isAscending() {
        if(array.length == 0)
        {
            return true;
        }
        boolean isAsc = isAcendingHelper(0);
        if(isAsc)
        {
            return true;
        }
        return false;
    }

    public boolean isDescending() {
        if(array.length == 0)
        {
            return true;
        }
        boolean isDesc = isDescendingHelper(0);
        if(isDesc)
        {
            return true;
        }
        return false;
    }

    public void sortAscending() {
            if(array.length == 0){
                return;
            }
            insertionSort(0,true);
    }

    public void sortDescending() {
        if(array.length == 0){
            return;
        }
        insertionSort(0,false);
    }

    //Function is used to inser the data into the correct location in the array
    private void addData(int index, int target, Integer data)
    {
        if(this.array.length <= index)
        {
            return;
        }
        else if(index == target)
        {
            //at the correct index in that array
            array[target] = data;
            return;
        }
        addData(index+1,target,data);
    }

    //used for the prepend and append functions to add data into the array

    private void populateArray(Integer[] newArray, int newIndex, int oldIndex){
        if(newIndex >= newArray.length || oldIndex >= array.length)
        {
            return;
        }
        newArray[newIndex] = array[oldIndex];
        newIndex += 1;
        oldIndex += 1;
        populateArray(newArray,newIndex,oldIndex);
    }

    public void populateInitialArray(String[] temp, int index){
        if(index >= array.length || index >= temp.length)
        {
            return;
        }
        array[index] = Integer.parseInt(temp[index]);
        index += 1;
        populateInitialArray(temp,index);
    }

    /*Used to help construct the toString String variable*/

    private String toStringHelper(int index,String arrayString)
    {
        if(index >= array.length)
        {
            return arrayString;
        }
        else if(index == array.length-1)
        {
            arrayString += array[index] + "]";
            return arrayString;
        }
        else{
            arrayString += array[index] + ",";
            return toStringHelper(index+1,arrayString);
        }
    }

    /*helper method for the contains*/
    private Integer findData(int index,Integer target)
    {
        if(index >= array.length)
        {
            return null;
        }
        else if(Objects.equals(array[index], target))
        {
            return index;
        }
        else{
            return findData(index+1,target);
        }
    }

    //helper for isAcending method

    private boolean isAcendingHelper(int index)
    {
        if(index >= array.length)
        {
            return true;
        }
        boolean ans = isSmaller(index+1,array[index]);
        if(ans)
        {
            return isAcendingHelper(index+1);
        }
        else{
            return false;
        }
    }

    private boolean isSmaller(int index, Integer current)
    {
        if(index >= array.length)
        {
            return true;
        }
        if(array[index] >= current)
        {
            return isSmaller(index+1,current);
        }
        else{
            return false;
        }
    }

    private boolean isDescendingHelper(int index)
    {
        if(index >= array.length)
        {
            return true;
        }
        boolean ans = isLarger(index+1,array[index]);
        if(ans)
        {
            return isDescendingHelper(index+1);
        }
        else{
            return false;
        }
    }

    private boolean isLarger(int index, Integer current)
    {
        if(index >= array.length)
        {
            return true;
        }
        if(array[index] <= current)
        {
            return isLarger(index+1,current);
        }
        else{
            return false;
        }
    }

    //Insertion Sort functions
    private void insertionSort(int index,boolean ascending)
    {
        if(index>=array.length)
        {
            return;
        }
        if(ascending)
        {
            insertionSorterAsc(array[index],index,index-1);
        }
        else{
            insertionSorterDesc(array[index],index,index-1);
        }
        index+=1;
        insertionSort(index,ascending);
    }

    private void insertionSorterAsc(Integer value,int index,int current)
    {
        if(current < 0)
        {
            //Base Case
            return;
        }
        if(value < array[current])
        {
            //swap
            Integer temp = array[current];
            array[current] = value;
            array[index] = temp;
            index =  current;
            current = index-1;
            insertionSorterAsc(value,index,current);
        }
        else{
            current-=1;
            insertionSorterAsc(value,index,current);
        }
    }

    private void insertionSorterDesc(Integer value,int index,int current)
    {
        if(current < 0)
        {
            //Base Case
            return;
        }
        if(value > array[current])
        {
            //swap
            Integer temp = array[current];
            array[current] = value;
            array[index] = temp;
            index =  current;
            current = index-1;
            insertionSorterAsc(value,index,current);
        }
        else{
            current-=1;
            insertionSorterAsc(value,index,current);
        }
    }

}
