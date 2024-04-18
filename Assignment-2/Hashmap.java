
public class Hashmap<K, V> {

    public HashNode<K, V>[] data;
    public int numValues;
    public int capacity;

    public Hashmap()
    {
        this.capacity = 2;
        this.data = new HashNode[capacity];
        this.numValues = 0;
    }

    public boolean insert(K key, V value) {
        //Step 1: Generate the Hash
        int HASH = hornerHash(key);

        //Step 2: Check if the index is open
        if(data[HASH] == null){
            data[HASH] = new HashNode<>(key,value);
            this.numValues++;
            if(numValues  == capacity)
            {
                resize();
            }
            return true;
        }
        else if(data[HASH] != null && data[HASH].key.equals(key))
        {
            return false;
        }
        else{
            //Pair not in the table and index was taken
            int offset = secondaryHash(key);
            int currentHash = HASH;
            int i = 1;

            while(true)
            {
                //Calculate the new index using the double hash
                int newIndex = (currentHash + (i * offset)) % data.length;

                //check the index
                if(data[newIndex] == null)
                {
                    //empty slot
                    data[newIndex] = new HashNode<>(key,value);
                    this.numValues++;
                    break;
                }
                else if(data[newIndex] != null && data[newIndex].key.equals(key)){
                    return false;
                }

                i++;
            }

            if(numValues  == capacity)
            {
                resize();
            }
            return true;
        }
    }

    public void delete(K key)
    {
        //Step 1: Generate the Hash
        int HASH = hornerHash(key);

        //Step 2: Check if the index is open
        if(data[HASH] != null && data[HASH].key.equals(key))
        {

            data[HASH] = null;
            numValues--;
            return;
        }
        else{
            //Pair not in the table and index was taken
            int offset = secondaryHash(key);
            int currentHash = HASH;
            int i = 1;

            while(i < data.length+1)
            {
                //Calculate the new index using the double hash
                int newIndex = (currentHash + (i * offset)) % data.length;

                //check the index
                if(data[newIndex] != null && data[newIndex].key.equals(key)){

                    data[newIndex] = null;
                    numValues--;
                    return;
                }

                i++;
            }
        }
    }

    public V get(K key) {
        //Step 1: Generate the Hash
        int HASH = hornerHash(key);

        //Step 2: Check if the index is open
        if(data[HASH] != null && data[HASH].key.equals(key))
        {
            return data[HASH].value;
        }
        else{
            //Pair not in the table and index was taken
            int offset = secondaryHash(key);
            int currentHash = HASH;
            int i = 1;

            while(i < data.length+1)
            {
                //Calculate the new index using the double hash
                int newIndex = (currentHash + (i * offset)) % data.length;

                //check the index
                if(data[newIndex] != null && data[newIndex].key.equals(key)){
                    return data[newIndex].value;
                }

                i++;
            }
        }
        return null;
    }


    public Object[] getKeys() {
        return null;
    }

    public void clear() {
        this.numValues = 0;
        this.capacity = 2;
        this.data = new HashNode[2];
    }

    private void resize()
    {
        int size = capacity;
        int newSize = size * 2;
        HashNode<K, V>[] temp = new HashNode[size];
        //copy into temp
        for(int i = 0; i < size;i++)
        {
            temp[i] = data[i];
        }
        //resize array
        this.data = new HashNode[newSize];

        for(int i = 0; i < size;i++)
        {
            insert(temp[i].key,temp[i].value);
        }
        this.numValues = size;
        this.capacity = newSize;
    }

    /* -------------------------------------------------------------------------- */
    /* Please don't change this toString, I tried to make it pretty. */
    /* -------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------- */
    /* Also we may test against it */
    /* -------------------------------------------------------------------------- */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s | %-20s | %-20s%n", "Index", "Key", "Value");
        sb.append(header);
        for (int i = 0; i < header.length() - 1; i++) {
            sb.append("-");
        }
        sb.append("\n");
        for (int i = 0; i < capacity; i++) {
            if (data[i] != null) {
                String row = String.format("%-10d | %-20s | %-20s%n", i, data[i].key.toString(),
                        data[i].value.toString());
                sb.append(row);
            } else {
                String row = String.format("%-10d | %-20s | %-20s%n", i, "null", "null");
                sb.append(row);
            }
        }

        return sb.toString();
    }

    public int hornerHash(K key) {
        String keyStr = key.toString();
        int hashVal = 0;
        for (int i = 0; i < keyStr.length(); i++)
            hashVal = 37 * hashVal + keyStr.charAt(i);
        hashVal %= capacity;
        if (hashVal < 0)
            hashVal += capacity;
        return hashVal;
    }

    public int secondaryHash(K key) {
        int hash = key.hashCode();
        // Ensure the step size is odd to ensure it's coprime with capacity, since
        // capacity is a power of 2.
        int step = (hash & (capacity - 1)) | 1; // This ensures the step size is always odd.
        return step;
    }

}
