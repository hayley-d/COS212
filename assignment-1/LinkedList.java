public class LinkedList {
    public CoordinateNode head;

    public LinkedList() {
        this.head = null;
    }

    public LinkedList(int x, int y) {
        this.head = new CoordinateNode(x,y);
    }

    public void append(int x, int y) {
        CoordinateNode newNode = new CoordinateNode(x,y);

        if(head == null)
        {
            this.head = newNode;
            return;
        }

        CoordinateNode tail = findTail(head);
        tail.next = newNode;
    }

    public void appendList(LinkedList other) {
        //Check id other is null
        if(other.head == null)
        {
            return;
        }
        if(this.head == null)
        {
            this.head = other.head;
            return;
        }

        CoordinateNode tail = findTail(this.head);
        tail.next = other.head;
    }

    public boolean contains(int x, int y) {
        if(this.head == null)
        {
            return false;
        }
        return isContained(x,y,head);
    }

    @Override
    public String toString() {
        if(this.head == null)
        {
            return "Empty List";
        }

        String myList = this.head.toString();

        return buildString(myList,head.next);
    }

    public int length() {
        if(this.head == null)
        {
            return 0;
        }
        int length = 0;
        return countNodes(length,head);
    }

    public LinkedList reversed() {
        if(this.head == null)
        {
            return new LinkedList();
        }

        LinkedList copyList = new LinkedList();

        return reverseCopyList(copyList,head);
    }

    //HELPER METHODS

    //Function to find and return the tail node
    private CoordinateNode findTail(CoordinateNode current){
        //Base Case
        if(current == null){
            return null;
        }
        if(current.next == null)
        {
            return current;
        }
        return findTail(current.next);
    }

    //Function used to see if node is in the list
    private boolean isContained(int x, int y, CoordinateNode current)
    {
        if(current == null)
        {
            return false;
        }
        if(current.x == x && current.y == y)
        {
            return true;
        }
        return isContained(x,y,current.next);
    }

    private String buildString(String myList,CoordinateNode current)
    {
        if(current == null)
        {
            return myList;
        }

        myList += " -> " + current.toString();

        return buildString(myList,current.next);
    }

    private int countNodes(int length,CoordinateNode current)
    {
        if(current == null)
        {
            return length;
        }
        length += 1;
        return countNodes(length,current.next);
    }

    private LinkedList reverseCopyList(LinkedList copyList,CoordinateNode current)
    {
        if(current == null)
        {
            return copyList;
        }

        if(current.next == null)
        {
            copyList.append(current.x, current.y);
            return copyList;
        }

        copyList = reverseCopyList(copyList,current.next);
        copyList.append(current.x, current.y);
        return copyList;
    }

    public CoordinateNode getTail(){
        if(head == null)
        {
            return null;
        }
        return findTail(head);
    }

    //checks the column for y outliers
    private boolean checkOutliers(int maxY,CoordinateNode current)
    {
        if(current == null)
        {
            return true;
        }

        if(current.y >= maxY || current.y < 0 || current.x < 0)
        {
            return false;
        }

        return checkOutliers(maxY,current.next);
    }

    //returns false if diagonal was found
    private boolean diagonalCheck(CoordinateNode current, CoordinateNode prev)
    {
        if(current == null || prev == null)
        {
            return true;
        }


        if((prev.x + 1) == current.x && (prev.y+1) == current.y)
        {
            //diagonal up
            return false;
        }
        else if((prev.x -1) == current.x && (prev.y-1) == current.y)
        {
            //diagonal down
            return false;
        }

        if(current.next == null)
        {
            return true;
        }
        prev = current;
        current = current.next;
        return diagonalCheck(current,prev);
    }

    private boolean oneStep(CoordinateNode current,CoordinateNode prev)
    {
        if(current == null || prev == null)
        {
            return true;
        }

        if((current.x - prev.x) > 1 || (current.x - prev.x) < -1)
        {
            return false;
        }
        else if((current.y - prev.y) > 1 || (current.y - prev.y) < -1)
        {
            return false;
        }
        if(current.next == null)
        {
            return true;
        }
        prev = current;
        current = current.next;
        return oneStep(current,prev);
    }

    private boolean noRepeats(CoordinateNode node)
    {
        if(node == null)
        {
            return true;
        }
        if(!checkRepeats(node,node.next))
        {
            return false;
        }
        return noRepeats(node.next);
    }

    private boolean checkRepeats(CoordinateNode target, CoordinateNode current)
    {
        if(current == null)
        {
            return true;
        }
        if(current.x == target.x && current.y == target.y)
        {
            //duplicate
            return false;
        }
        return checkRepeats(target,current.next);
    }

    public boolean validateMovements(int mapSize, String[] map)
    {
        if(!checkOutliers(mapSize,head))
        {
            return false;
        }

        if(!diagonalCheck(head.next,head)){
            return false;
        }

        if(!oneStep(head.next,head)){
            return false;
        }

        if(!noRepeats(head))
        {
            return false;
        }

        if(!noWalls(head,map))
        {
            return false;
        }

        return true;
    }

    private boolean noWalls(CoordinateNode current, String[] map)
    {
        if(current == null){
            return true;
        }
        if(current.y < map.length)
        {
            if(map[current.y].charAt(current.x) == 'X' || map[current.y].charAt(current.x) == 'x')
            {
                return false;
            }
            return noWalls(current.next,map);
        }
        return false;
    }


}
