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
        return "";
    }

    public void append(Integer value) {

    }

    public void prepend(Integer value) {

    }

    public boolean contains(Integer value) {
        return false;
    }

    public boolean isAscending() {
        return false;
    }

    public boolean isDescending() {
        return false;
    }

    public void sortAscending() {

    }

    public void sortDescending() {

    }

    //Function is used to inser the data into the correct location in the array
    private boolean addData(int index, int target, Integer data)
    {
        if(this.array.length <= index)
        {
            return false;
        }
        else if(index == target)
        {
            //at the correct index in that array
            array[target] = data;
            return true;
        }
        addData(index+1,target,data);
        return true;
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

}
