import java.lang.Math;


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

    public Hashmap(String inp)
    {
        String currentPrime = "";
        String studentNum = "";
        String markStr = "";

        int indexOpenBracket = inp.indexOf('[');
        currentPrime = inp.substring(0, indexOpenBracket);


        String content = inp.substring(indexOpenBracket + 1, inp.length() - 1);


        // Split the content by ','
        String[] parts = content.split(",");
        int len = parts.length;
        this.array = new KeyValuePair[len];

        for (int i = 0; i < len; i++) {
            if(parts[i].equals("-"))
            {
                array[i] = null;
            }
            else{
                String[] keyValue = parts[i].split(":");
                studentNum = keyValue[0].substring(1);
                markStr = keyValue[1].replaceAll("[^0-9]", ""); // Remove non-numeric characters from mark
                array[i] = new KeyValuePair(Integer.parseInt(studentNum),Integer.parseInt(markStr));
            }

        }

        while(prime.currentPrime() < Integer.parseInt(currentPrime))
        {
            prime.nextPrime();
        }

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

        if(array[hash] != null && array[hash].studentNumber == studentNumber)
        {
            return array[hash];
        }
        else{
            int i = 1;
            int newHash = hash;
            int maxProbes = 0; // Maximum number of probes allowed
            int[] quadratics = new int[] {1, -1, 4, -4, 9, -9};
            while(array[newHash] != null && array[newHash].studentNumber != studentNumber /*&& maxProbes < size()*/ && i < quadratics.length)
            {
                //int p = (int) (Math.pow(((i+1)/2),2));
                //newHash = (hash +((int) (Math.pow(-1,i) * Math.pow(p,2))) )% size();
                newHash = (hash + quadratics[i])% size();
                newHash = Math.abs(newHash);
                i++;
                maxProbes++; // Increment the number of probes made

            }

            if (array[newHash] != null && array[newHash].studentNumber == studentNumber)
            {
                return array[newHash];
            }
            else{
                //came back as null
                for(KeyValuePair key : array)
                {
                    if(key != null)
                    {
                        if(key.studentNumber == studentNumber)
                        {
                            return key;
                        }
                    }

                }
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
                int i = 0;
                int newHash = hash;
                //int maxProbes = 0; // Maximum number of probes allowed
                int[] quadratics = new int[] {1, -1, 4, -4, 9, -9};
                while(array[newHash] != null && array[newHash].studentNumber != studentNumber /*&& maxProbes < size()*/ && i < quadratics.length)
                {
                    //int p = (int) (Math.pow(((i+1)/2),2));
                    //newHash = (hash +((int) (Math.pow(-1,i) * Math.pow(p,2))) )% size();
                    newHash = (hash - quadratics[i])% size();
                    newHash = Math.abs(newHash);
                    i++;
                    //maxProbes++; // Increment the number of probes made

                }

                if (array[newHash] == null || array[newHash].studentNumber == studentNumber)
                {
                    array[newHash] = new KeyValuePair(studentNumber, mark);
                }
                else {
                    // Resize the hashmap if all slots are full
                    resizeAndRehash();
                    // Insert the key-value pair into the resized hashmap
                    insert(studentNumber,mark);
                }
            }
        }
    }

    public void remove(int studentNumber) {
        int hash = hash(studentNumber);

        if(array[hash] != null && array[hash].studentNumber == studentNumber)
        {
            array[hash] = null;
            return;
        }
        else{
            /*int i = 1;
            int newHash = hash;
            int maxProbes = 0; // Maximum number of probes allowed

            while((array[newHash] == null || array[newHash].studentNumber != studentNumber) && maxProbes < size())
            {
                int p = (int) (Math.pow(((i+1)/2),2));
                newHash = (hash -((int) (Math.pow(-1,i) * Math.pow(p,2))) )% size();
                newHash = Math.abs(newHash);
                i++;
                maxProbes++; // Increment the number of probes made

            }*/
            int i = 0;
            int newHash = hash;
            //int maxProbes = 0; // Maximum number of probes allowed
            int[] quadratics = new int[] {1, -1, 4, -4, 9, -9};
            while(array[newHash] != null && array[newHash].studentNumber != studentNumber /*&& maxProbes < size()*/ && i < quadratics.length)
            {
                //int p = (int) (Math.pow(((i+1)/2),2));
                //newHash = (hash +((int) (Math.pow(-1,i) * Math.pow(p,2))) )% size();
                newHash = (hash - quadratics[i])% size();
                newHash = Math.abs(newHash);
                i++;
                //maxProbes++; // Increment the number of probes made

            }

            if (array[newHash] != null && array[newHash].studentNumber == studentNumber)
            {
                array[newHash] = null;
                return;
            }
            else{
                //came back as null
                for(int j = 0; j < size(); j++)
                {
                    if(array[j] != null)
                    {
                        if(array[j].studentNumber == studentNumber)
                        {
                            array[j] = null;
                            return;
                        }
                    }

                }
                return;
            }
        }
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
