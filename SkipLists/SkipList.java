import java.util.Random;

// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<T>> {
    private int maxLevel;
    private SkipListNode<T>[] root;
    private int[] powers;
    // Do not change the seed. This is used to generate the same values every run

    private int numberNodes;

    private int skipListLevel;
    private Random randomGenerator = new Random(123456);

    public SkipList(int maxLevel) {
        this.maxLevel = maxLevel;
        this.root  = new SkipListNode[maxLevel];
        for(SkipListNode<T> node : root){
            node = null;
        }

        //Initalize the powers array with a length equal to maxLevel
        this.powers = new int[maxLevel];
        //Set the first element to 1 as there is at least one level
        powers[0] = 1;
        int maxEfficient = (int)(Math.pow(2,maxLevel)); //2^maxlevel
        int n = 2;
        //For every following index in the array
        for(int i = 1; i < maxLevel;i++)
        {
            //maxEfficient/n
            powers[i] = maxEfficient/n;
            n*=2;
        }
        skipListLevel = 1;
        numberNodes = 0;
    }

    public int chooseLevel()
    {
        // Step 1: Generate Random Number
        int upperBound = (int)Math.pow(2,maxLevel);
        int randomNumber = randomGenerator.nextInt(upperBound)+1;

        //Step 2: Compare to entries in powers array
        for (int i = 0; i < powers.length; i++) {
            if (powers[i] > randomNumber) {
                return i + 1; // Return the level (index + 1)
            }
        }

        return maxLevel;// If no entry in powers array is larger, return maxLevel
    }

    public void insert(T key)
    {
        //Create the new node
        int newLevel = chooseLevel();
        SkipListNode<T> newNode = new SkipListNode<>(key,newLevel);

        if (newLevel > maxLevel) {
            maxLevel = newLevel; // Update max level if necessary
        }




        if(isEmpty())
        {
            if(newLevel > skipListLevel)
            {
                skipListLevel = newLevel;
            }
            // List is empty
            for(int i = 0; i < newLevel;i++)
            {
                root[i] = newNode;
            }
            numberNodes+=1;
            return;
        }
        // Traverse the list to find the appropriate position to insert
        SkipListNode<T> current = root[skipListLevel-1];
        SkipListNode<T> [] update = new SkipListNode[maxLevel];

        //if the new level is higher fill in the empty root nodes
        if(skipListLevel < newLevel)
        {
            for(int i = newLevel-1; i > skipListLevel-1;i--)
            {
                root[i] = newNode;
            }
            current = root[skipListLevel-1];
        }


        //if roots are larger
        if(current.key.compareTo(key)>0)
        {
            for(int i = skipListLevel-1; i >=0; i--)
            {
                
                if(root[i].key.compareTo(key)>0)
                {
                    //temp
                    SkipListNode<T> temp = root[i];
                    root[i] = newNode;
                    newNode.next[i] = temp;
                    if(i == 0)
                    {
                        return;
                    }
                }
                else{
                    current = root[i];
                    for(int j = i;  j >=0; j--)
                    {
                        while(current.next[i]!= null && current.next[i].key.compareTo(key)<0)
                        {
                            current = current.next[i];
                        }
                        //either current.next == null or current.next > key
                        SkipListNode<T> temp = current.next[i];
                        current.next[i] = newNode;
                        newNode.next[i] = temp;
                    }
                    return;
                }
            }
        }


        for(int i = skipListLevel-1; i >=0; i--)
        {
                while(current.next[i]!= null && current.next[i].key.compareTo(key) < 0)
                {
                    current = current.next[i];
                }
                while(current.next[i] != null && current.next[i].key.equals(key))
                {
                    current = current.next[i];
                }
            update[i] = current;
        }

        // Move to the next node at bottom level
        current = current.next[0];



        // Traverse to the end of duplicate values if any
        while (current != null && current.key.equals(key)) {
            current = current.next[0];
        }


        if(newLevel > skipListLevel)
        {
            skipListLevel = newLevel;
            root[skipListLevel-1] = newNode;
            for(int i = newLevel-1; i>=0;i--)
            {
                if(root[i] == null)
                {
                    root[i] = newNode;
                }
                else if(root[i].key.compareTo(key)>0)
                {
                    SkipListNode<T> temp = root[i];
                    newNode.next[i] = temp;
                    root[i] = newNode;
                }
            }
            if(root[0].key.equals(key))
            {
                return;
            }
        }

        // Insert the new node after the last node with the same value
        if(current == null || !current.key.equals(key))
        {
            // Update references
            for(int i = 0; i < newLevel;i++)
            {
                if(update[i] != null)
                {
                    SkipListNode<T> temp = update[i].next[i];
                    newNode.next[i] = temp;
                    update[i].next[i] = newNode;
                }

            }
        }

        numberNodes++;
    }

    public boolean isEmpty() {
        return numberNodes == 0;
    }

    public SkipListNode<T> search(T key) {
        //if the list is empty
        if(isEmpty())
        {
            return null;
        }

        //if there is only one node in the list
        if(numberNodes == 1)
        {
            if(root[0].key.equals(key))
            {
                return root[0];
            }
            return null;
        }

        //if list has more than 1 node

        //start from the top level
        int currentLevel = skipListLevel-1;
        SkipListNode<T> currentNode = root[currentLevel];


        //if the current node is larger
        //move down the root until it is smaller or no more levels to go down
        while(currentNode.key.compareTo(key)>0)
        {
            currentLevel --;
            //if you can go down a level
            if(currentLevel >= 0)
            {
                currentNode = root[currentLevel];
            }
            else{
                return null;
            }
        }

        if(currentNode.key.equals(key))
        {
            //found the key, end function
            return currentNode;
        }

        for(int i = currentLevel; i>=0; i--)
        {
            //while the next node is not null

            while(currentNode.next[i] != null)
            {
                if(currentNode.key.equals(key))
                {
                    //found the key, print and end function
                    return currentNode;
                }
                else{
                    if(currentNode.next[i].key.compareTo(key)>0)
                    {
                        //if the current node is larger
                        break;
                    }
                    else{
                        //if the current node is smaller then continue on this level
                        currentNode = currentNode.next[i];
                        if(currentNode.key.equals(key))
                        {
                            return currentNode;
                        }
                    }
                }
            }
        }
        return null;
    }


    @Override
    public String toString() {
        String [] levels = new String[maxLevel];
        for(int i = 0; i < maxLevel; i++)
        {
            levels[i] = "[Lvl"+i+"]";
        }
        SkipListNode<T> ptr = root[0];
        while(ptr != null)
        {
            for(int i = 0; i < maxLevel; i++)
            {
                if(i < ptr.level)
                {
                    levels[i] += "->" + ptr.toString();
                }
                else{
                    levels[i] += "--" + ptr.emptyString();
                }
            }
            ptr = ptr.next[0];
        }

        // Remove excess "-" at the end of each string
        for (int i = 0; i < maxLevel; i++) {
            levels[i] = levels[i].replaceAll("-+$", "") + "]";
        }

        // Concatenate strings and split by new line
        String result = "";
        for (int i = maxLevel - 1; i >= 0; i--) {
            result += levels[i];
            if (i != 0) {
                result += "\n";
            }
        }

        return result;
    }

    public boolean delete(T key) {
        if (isEmpty()) {
            return false;
        }

        SkipListNode<T>[] update = new SkipListNode[maxLevel]; // Adjusted size

        //get the level of the node
        SkipListNode<T> nodeToDelete = search(key);

        //if node does not exist
        if(nodeToDelete != null)
        {
            int currentLvl = nodeToDelete.level-1;
            SkipListNode<T> currentNode = root[currentLvl];



            //if the node is the root
            while(currentNode.key.equals(key))
            {
                //Assign root new value
                if(currentLvl >= 1)
                {
                    //Remove current pointers
                    SkipListNode<T> next = currentNode.next[currentLvl];
                    currentNode.next[currentLvl] = null;
                    //assign new pointer to the root
                    root[currentLvl] = next;

                    //go down the next level
                    currentLvl--;
                    currentNode = root[currentLvl];
                }
                else if(currentLvl == 0)
                {
                    //Remove current pointers
                    SkipListNode<T> next = currentNode.next[currentLvl];
                    currentNode.next[currentLvl] = null;
                    //assign new pointer to the root
                    root[currentLvl] = next;

                    //go down the next level
                    currentLvl--;
                }
                else{
                    return true;
                }
            }

            if(currentNode.key.equals(key))
            {

                while(currentLvl>=1)
                {
                    root[currentLvl] = currentNode.next[currentLvl];
                    currentLvl--;
                }
                return true;
            }



            for(int i = currentLvl; i >=0; i--)
            {
                while(currentNode.next[i] != null)
                {
                    if(currentNode.next[i] == nodeToDelete)
                    {
                        //if the next node is the one we need
                        SkipListNode<T> deleteThisOne = currentNode.next[i];
                        SkipListNode<T> temp = deleteThisOne.next[i];
                        deleteThisOne.next[i] = null;
                        currentNode.next[i] = temp;
                        System.out.println(currentNode.toString() + "->"+temp);
                        break;
                    }
                    else if(currentNode.key.compareTo(key)<0)
                    {
                        //if the current key is less
                        currentNode = currentNode.next[i];
                    }
                }
            }
            return true;

        }
        return false;
    }



    public void printSearchPath(T key){
        //if the list is empty
        if(isEmpty())
        {
            return;
        }

        //if there is only one node in the list
        if(numberNodes == 1)
        {
            if(root[0].key.equals(key))
            {
                System.out.println(root[0]);
                return;
            }
            return;
        }

        //if list has more than 1 node

        //start from the top level
        int currentLevel = skipListLevel-1;
        SkipListNode<T> currentNode = root[currentLevel];
        String path = "";

        //if the current node is larger
        //move down the root until it is smaller or no more levels to go down
        while(currentNode.key.compareTo(key)>0)
        {
            currentLevel --;
            //if you can go down a level
            if(currentLevel >= 0)
            {

                path += currentNode.toString() + " ";
                currentNode = root[currentLevel];
            }
            else{
                return;
            }
        }

        path += currentNode.toString() + " ";

        if(currentNode.key.equals(key))
        {
            //found the key, print and end function
            System.out.println(path);
            return;
        }

        for(int i = currentLevel; i>=0; i--)
        {
            //while the next node is not null

            while(currentNode.next[i] != null)
            {
                if(currentNode.key.equals(key))
                {
                    //found the key, print and end function
                    path += currentNode.toString();
                    System.out.println(path);
                    return;
                }
                else{
                    if(currentNode.next[i].key.compareTo(key)>0)
                    {
                        //if the current node is larger
                        path += currentNode.next[i].toString() + " ";
                        break;
                    }
                    else{
                        //if the current node is smaller then continue on this level
                        currentNode = currentNode.next[i];
                        path += currentNode.toString() + " ";
                        if(currentNode.key.equals(key))
                        {
                            System.out.println(path);
                            return;
                        }
                    }
                }
            }
        }

    }

    public void printRoot()
    {
        for(int i = root.length-1; i>=0;i--)
        {
            System.out.println(root[i]);
        }
    }
}
