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

}
