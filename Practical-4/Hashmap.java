import java.lang.Math;;

public class Hashmap {
    public KeyValuePair[] array;
    public PrimeNumberGenerator prime = new PrimeNumberGenerator();

    @Override
    public String toString() {
        String res = String.valueOf(prime.currentPrime()) + "\n";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += "\n";
            }
            res += i + "\t";
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res;
    }

    public String toStringOneLine() {
        String res = String.valueOf(prime.currentPrime()) + "[";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += ",";
            }
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res + "]";
    }

    public Hashmap() {
        array = new KeyValuePair[1];
    }

    public Hashmap(String inp) {
        
    }

    public int hash(int studentNumber) {
        String studentNumberStr = studentNumber + "";
        char[] studentNumArr = studentNumberStr.toCharArray();
        String[] arr = new String[studentNumArr.length];
        for(int i = 0; i < studentNumArr.length; i++)
        {
            arr[i] =  studentNumArr[i]+"";
        }

        int[] myArray = new int[arr.length];

        for(int i = 0; i < studentNumArr.length; i++)
        {
            myArray[i] =  Integer.parseInt(arr[i]);
        }

        //arr is an array with each digit seporated into its own index
        int hashValue = 0;
        for(int i = 0; i < arr.length; i++)
        {
            hashValue = prime.currentPrime() * hashValue + myArray[i];
        }



        if(hashValue<0)
        {
            hashValue = Math.abs(hashValue);
        }

        hashValue %= size();

        return hashValue;
    }

    public KeyValuePair search(int studentNumber) {
        int hash = hash(studentNumber);

        if(array[hash].studentNumber == studentNumber)
        {
            return array[hash];
        }
        else{
            int i = 1;
            int newHash = hash;
            int maxProbes = 0; // Maximum number of probes allowed
            while(array[newHash] != null && array[newHash].studentNumber != studentNumber && maxProbes < array.length)
            {
                int p = (int) (Math.pow(-1,i-1) * Math.pow(((i+1)/2),2));
                newHash = (hash + (int) Math.pow(p,2)) % size();
                i++;
                maxProbes++; // Increment the number of probes made
            }

            if (array[newHash].studentNumber == studentNumber)
            {
                return array[newHash];
            }
            else{
                return null;
            }
        }
    }

    public void insert(int studentNumber, int mark) {
        //Step 1: Check if already in the hashmap
        int hash = hash(studentNumber);
        if(array[hash] != null && array[hash].studentNumber == studentNumber)
        {
            array[hash].mark = mark;
            return;
        }
        else{
            //Step 2: Check if the index is empty
            if(array[hash] == null)
            {
                array[hash] = new KeyValuePair(studentNumber,mark);
                return;
            }
            else{
                //Step 3: Collision Resolution
                int i = 1;
                int newHash = hash;
                int maxProbes = 0; // Maximum number of probes allowed
                while(array[newHash] != null && array[newHash].studentNumber != studentNumber && maxProbes < size())
                {
                    int p = (int) (Math.pow(-1,i-1) * Math.pow(((i+1)/2),2));
                    newHash = (hash +((int) (Math.pow(-1,i-1) * Math.pow(p,2))) )% size();
                    newHash = Math.abs(newHash);
                    i++;
                    maxProbes++; // Increment the number of probes made
                }

                if (array[newHash] == null || array[newHash].studentNumber == studentNumber)
                {
                    array[newHash] = new KeyValuePair(studentNumber, mark);
                }
                else {
                    // Resize the hashmap if all slots are full
                    resizeAndRehash();
                    // Insert the key-value pair into the resized hashmap
                    insert(studentNumber, mark);
                }
            }
        }
    }

    public void remove(int studentNumber) {
        int hash = hash(studentNumber);
        array[hash] = null;
        return;
    }

    private int size()
    {
        return this.array.length;
    }


    private void resizeAndRehash(){
        int size = size() * 2;
        int oldSize = size();
        KeyValuePair [] temp = new KeyValuePair[oldSize];
        //Copy over into temp array
        for(int i = 0; i < oldSize; i++)
        {
            if(array[i] != null)
            {
                temp[i] = new KeyValuePair(array[i].studentNumber,array[i].mark);
            }
        }

        this.array = new KeyValuePair[size];
        prime.nextPrime();
        for(int i = 0; i < oldSize;i++)
        {
            if(temp[i] != null)
            {
                insert(temp[i].studentNumber,temp[i].mark);
            }
        }
    }
}
