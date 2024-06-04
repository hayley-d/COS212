import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    LinkedList<Vertex> vertices;
    LinkedList<Edge> edges;
    Vertex start;

    Maze() {
        this.start = null;
        this.vertices = new LinkedList<>();
        this.edges = new LinkedList<>();
    }

    Maze(String fileName)  {
        this.start = null;
        this.vertices = new LinkedList<>();
        this.edges = new LinkedList<>();
        try {
            StringBuilder mazeString = new StringBuilder();
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                mazeString.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            // Process the maze string just like in the createMaze method
            String[] rows = mazeString.toString().split("\n");

            for (int k = 0; k < rows.length; k++) {
                processRow(rows[k], k);
            }
            processEdges();
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    static Maze createMaze(String mazeString)
    {
        Maze maze = new Maze();
        //Split into rows
        String[] rows = mazeString.split("\n");

        for(int k = 0 ; k < rows.length; k++)
        {
            maze.processRow(rows[k], k);
        }
        maze.processEdges();
        return maze;
    }



    String latexCode(){
        String result = "\\documentclass[hidelinks, 12pt, a4paper]{article}\r\n" + //
        "\\usepackage{tikz}\n" + //
        "\n" + //
        "\\begin{document}\n" + //
        "\n" + //
        "\\begin{tikzpicture}[node/.style={circle, draw, minimum size=1.2em},yscale=-1]\n";
        for(Vertex v: getVertices()){
            result += v.latexCode() + "\n";
        }
        result += "\n";
        for(Edge e: getEdges()){
            result += e.latexCode() + "\n";
        }
        result += "\\end{tikzpicture}\r\n" + //
                        "\r\n" + //
                        "\\end{document}";
        return result;
    }

    Vertex[] getVertices() {
        Vertex[] verArr = new Vertex[vertices.size];
        Node<Vertex> current = vertices.head;
        int i = 0;
        while(current != null)
        {
            verArr[i] = current.data;
            i++;
            current = current.next;
        }
        return verArr;
    }

    Edge[] getEdges() {
        Edge[] edgesArr = new Edge[edges.size];
        Node<Edge> current = edges.head;
        int i = 0;
        while(current != null)
        {
            edgesArr[i] = current.data;
            i++;
            current = current.next;
        }
        return edgesArr;
    }

    void stage1Reducing() {
        // Removing of redundant steps
        // Removing dead ends
        Node<Vertex> current = vertices.head;
        Vertex v = null;
        while(current != null)
        {
            if(current.data.symbol == ' ')
            {
                //check if has two edges
                if(current.data.edges.size == 2)
                {
                    //add the new edge
                    Edge e = current.data.addEdges();

                    //Add the new edge
                    this.edges.append(e);

                    //Remove edges with vertex
                    v = current.data;
                    removeEdgesWithVertex(v);

                    Node<Vertex> next = current.next;

                    //Remove the vertex
                    removeVertex(v);

                    current = next;
                    continue;
                }
            }
            current = current.next;
        }
    }

    void stage2Reducing() {
        // Removing dead ends
        Node<Vertex> current = vertices.head;
        Vertex v = null;
        while(current != null)
        {
            if(current.data.symbol == ' ')
            {
                //check if has two edges
                if(current.data.edges.size == 1)
                {
                    //Remove edges with vertex
                    v = current.data;
                    v = getVertex(v);
                    removeEdgesWithVertex(v);

                    Node<Vertex> next = current.next;

                    //Remove the vertex
                    removeVertex(v);

                    current = next;
                    continue;
                }
            }
            current = current.next;
        }
    }

    void stage3Reducing() {
        // Doubling the weight to traps
        Node<Edge> current = edges.head;
        //Go through Edges list and if contains trap double the wight
        while(current != null)
        {
            if(current.data.containsTrap())
            {
                current.data.doubleWeight();
            }
            current = current.next;
        }
    }

    public Vertex[] getAllDoors() {
        LinkedList<Vertex> doors = getDoors();

        Vertex[] array = new Vertex[doors.size];
        Node<Vertex> current = doors.head;
        int i = 0;
        while(current!=null)
        {
            array[i] = current.data;
            i++;
            current = current.next;
        }
        return array;
    }

    public Vertex[] getAllGoals()
    {
        //Go through vertices and if door add
        LinkedList<Vertex> goals = new LinkedList<>();

        Node<Vertex> curr = vertices.head;

        while(curr != null)
        {
            if(curr.data.symbol >= '0' && curr.data.symbol <= '9')
            {
                goals.append(curr.data);
            }
            curr = curr.next;
        }

        curr = goals.head;
        Vertex[] goalsArray = new Vertex[goals.size];
        int i = 0;
        while(curr != null)
        {
            goalsArray[i] = curr.data;
            i++;
            curr = curr.next;
        }

        return goalsArray;
    }

    public Vertex[] getAllKeys() {
        LinkedList<Vertex> keys = getKeys();

        Vertex[] array = new Vertex[keys.size];
        Node<Vertex> current = keys.head;
        int i = 0;
        while(current!=null)
        {
            array[i] = current.data;
            i++;
            current = current.next;
        }
        return array;
    }

    boolean isReachAble(Vertex start, Vertex goal) {

        start = getVertex(start);
        goal = getVertex(goal);

        if(start == null || goal == null)
        {
            return false;
        }

        // Check if the current vertex is a goal
        if (start.equals(goal)) {
            return true;
        }

        if (start.symbol == (goal.symbol)) {
            return true;
        }

        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<Vertex> visited = new LinkedList<>();

        queue.append(start);
        visited.append(start);

        //While the queue is not empty
        while(!queue.isEmpty())
        {
            Vertex current = queue.poll();

            for(Edge e: current.getEdges())
            {
                Vertex n = (e.v1.equals(current)) ? e.v2 : e.v1;

                if(!visited.contains(n) && n.symbol != 'D' && n.symbol != 'd')
                {

                    if(n.symbol == goal.symbol && n.xPos == goal.xPos && n.yPos == goal.yPos)
                    {

                        return true;
                    }
                    visited.append(n);
                    queue.append(n);
                }
            }
        }
        return false;
    }

    Vertex[] isReachAblePath(Vertex start, Vertex goal) {
        start = getVertex(start);
        goal = getVertex(goal);
        LinkedList<Vertex> visited = new LinkedList<>();
        LinkedList<Vertex> path = new LinkedList<>();

        if (dfs(start, goal, visited, path))
        {
            Vertex[] arr = new Vertex[path.size];
            Node<Vertex> curr = path.head;
            int index = 0;
            while (curr != null) {
                arr[index] = curr.data;

                index++;
                curr = curr.next;
            }
            return arr;
        } else {
            return new Vertex[0];
        }
    }

    private boolean dfs(Vertex current, Vertex goal, LinkedList<Vertex> visited, LinkedList<Vertex> path) {
        visited.append(current);
        path.append(current);

        if (current.equals(goal)) {
            return true;
        }

        LinkedList<Edge> edges = current.edges.insertionSort();
        Node<Edge> e = edges.head;

        while(e != null)
        {
            Vertex n = (e.data.v1.equals(current)) ? e.data.v2 : e.data.v1;

            if (!visited.contains(n) && n.symbol != 'D') {
                if (dfs(n, goal, visited, path)) {
                    return true;
                }
            }
            if(n.symbol == 'D')
            {
                path.append(n);
            }
            e = e.next;
        }

        /*path.remove(path.tail.data);*/
        return false;
    }

    double shortestPathDistanceNoDoor(Vertex start, Vertex goal) {
        start = getVertex(start);
        goal = getVertex(goal);
        if (start == null || goal == null || start.symbol == 'D')
        {
            return Double.POSITIVE_INFINITY;
        }

        // Check if the current vertex is a goal
        if (start.equals(goal)) {
            return 0;
        }

        if (start.symbol == (goal.symbol)) {
            return 0;
        }

        double[] distances = new double[vertices.size]; // Stores the shortest distance from start to that vertex


        //Fill with infinity
        for(int i = 0; i < distances.length; i++)
        {
            distances[i] = Double.POSITIVE_INFINITY;
        }

        LinkedList<Vertex> previous =  new LinkedList<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<Vertex> visited = new LinkedList<>();

        //Distance from start to itself is 0
        distances[vertices.indexOf(start)] = 0;

        //Go through all the current vertexes edges and update the distance
        queue.append(start);
        visited.append(start);


        //While the queue is not empty
        while(!queue.isEmpty())
        {
            Vertex current = queue.poll();

            for(Edge e: current.getEdges())
            {
                Vertex n = (e.v1.equals(current)) ? e.v2 : e.v1;
                Vertex n2 = (e.v1.equals(current)) ? e.v1 : e.v2;

                //Update the distances
                double distance = e.weight + distances[indexOf(n2)];
                if(distances[indexOf(n)] > distance)
                {
                    distances[indexOf(n)] = distance;
                }

                if(!visited.contains(n) && n.symbol != 'D')
                {
                    visited.append(n);
                    queue.append(n);
                }
            }
        }

        return distances[indexOf(goal)];
    }



    double shortestPathDistanceDoor(Vertex start, Vertex goal, boolean goUp) {
        start = getVertex(start);
        goal = getVertex(goal);
        if (start == null || goal == null || start.symbol != 'D')
        {
            return Double.POSITIVE_INFINITY;
        }

        // Check if the current vertex is a goal
        if (start.equals(goal)) {
            return 0;
        }

        if (start.symbol == (goal.symbol)) {
            return 0;
        }

        //Get the starting vertex

        //north -> y < yPos
        Edge smallestEdge = start.smallestEdge(goUp);

        Vertex newStart = (smallestEdge.v1.equals(start)) ? smallestEdge.v2 : smallestEdge.v1;


        double[] distances = new double[vertices.size]; // Stores the shortest distance from start to that vertex


        //Fill with infinity
        for(int i = 0; i < distances.length; i++)
        {
            distances[i] = Double.POSITIVE_INFINITY;
        }

        Vertex [] previous =  new Vertex[vertices.size];
        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<Vertex> visited = new LinkedList<>();

        //Distance from start to itself is 0
        distances[vertices.indexOf(newStart)] = smallestEdge.weight;

        //Go through all the current vertexes edges and update the distance
        queue.append(newStart);
        visited.append(newStart);
        previous[indexOf(newStart)] = null;


        //While the queue is not empty
        while(!queue.isEmpty())
        {
            Vertex current = queue.poll();

            for(Edge e: current.getEdges())
            {
                Vertex n = (e.v1.equals(current)) ? e.v2 : e.v1;
                Vertex n2 = (e.v1.equals(current)) ? e.v1 : e.v2;

                //Update the distances
                double distance = e.weight + distances[indexOf(n2)];
                if(distances[indexOf(n)] > distance)
                {
                    distances[indexOf(n)] = distance;
                    previous[indexOf(n)] = current;
                }

                if(!visited.contains(n) && n.symbol != 'D')
                {
                    visited.append(n);
                    queue.append(n);
                }
            }
        }

        if(distances[indexOf(goal)] >= Double.POSITIVE_INFINITY ||previous[indexOf(goal)] == null )
        {
            //no solution
            return Double.POSITIVE_INFINITY;
        }

        return distances[indexOf(goal)];
    }

    Vertex[] shortestPathPathDoor(Vertex start, Vertex goal, boolean goUp) {
        start = getVertex(start);
        goal = getVertex(goal);
        if (start == null || goal == null || start.symbol != 'D')
        {
            return new Vertex[0];
        }

        // Check if the current vertex is a goal
        if (start.equals(goal)) {
            return new Vertex[] {start};
        }

        if (start.symbol == (goal.symbol)) {
            return new Vertex[] {start};
        }

        //Get the starting vertex

        //north -> y < yPos
        Edge smallestEdge = start.smallestEdge(goUp);

        Vertex newStart = (smallestEdge.v1.equals(start)) ? smallestEdge.v2 : smallestEdge.v1;


        double[] distances = new double[vertices.size]; // Stores the shortest distance from start to that vertex


        //Fill with infinity
        for(int i = 0; i < distances.length; i++)
        {
            distances[i] = Double.POSITIVE_INFINITY;
        }

        Vertex [] previous =  new Vertex[vertices.size];
        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<Vertex> visited = new LinkedList<>();

        //Distance from start to itself is 0
        distances[vertices.indexOf(newStart)] = 0;

        //Go through all the current vertexes edges and update the distance
        queue.append(newStart);
        visited.append(newStart);
        previous[indexOf(newStart)] = null;


        //While the queue is not empty
        while(!queue.isEmpty())
        {
            Vertex current = queue.poll();

            for(Edge e: current.getEdges())
            {
                Vertex n = (e.v1.equals(current)) ? e.v2 : e.v1;
                Vertex n2 = (e.v1.equals(current)) ? e.v1 : e.v2;

                //Update the distances
                double distance = e.weight + distances[indexOf(n2)];
                if(distances[indexOf(n)] > distance)
                {
                    distances[indexOf(n)] = distance;
                    previous[indexOf(n)] = current;
                }

                if(!visited.contains(n) && n.symbol != 'D')
                {
                    visited.append(n);
                    queue.append(n);
                }
            }
        }

        if(distances[indexOf(goal)] >= Double.POSITIVE_INFINITY ||previous[indexOf(goal)] == null )
        {
            //no solution
            return new Vertex[0];
        }

        //Treat prev like a queue
        Vertex curr = previous[indexOf(goal)];
        LinkedList<Vertex> vPath = new LinkedList<>();
        vPath.prepend(goal);
        while(curr != null)
        {
            vPath.prepend(curr);
            curr = previous[indexOf(curr)];
        }
        vPath.prepend(start);

        Vertex[] arr = new Vertex[vPath.size];
        int i = 0;
        Node<Vertex> currNode = vPath.head;
        while(currNode != null)
        {
            arr[i] = currNode.data;
            i++;
            currNode = currNode.next;
        }
        return arr;
    }

    Vertex[] shortestPathPathNoDoor(Vertex start, Vertex goal) {
        start = getVertex(start);
        goal = getVertex(goal);
        if (start == null || goal == null || start.symbol == 'D' || start.symbol == 'T')
        {
            return new Vertex[0];
        }

        // Check if the current vertex is a goal
        if (start.equals(goal)) {
            return new Vertex[] {start};
        }

        if (start.symbol == (goal.symbol)) {
            return new Vertex[] {start};
        }

        double[] distances = new double[vertices.size]; // Stores the shortest distance from start to that vertex


        //Fill with infinity
        for(int i = 0; i < distances.length; i++)
        {
            distances[i] = Double.POSITIVE_INFINITY;
        }

        Vertex [] previous =  new Vertex[vertices.size];
        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<Vertex> visited = new LinkedList<>();

        //Distance from start to itself is 0
        distances[vertices.indexOf(start)] = 0;

        //Go through all the current vertexes edges and update the distance
        queue.append(start);
        visited.append(start);
        previous[indexOf(start)] = null;


        //While the queue is not empty
        while(!queue.isEmpty())
        {
            Vertex current = queue.poll();

            for(Edge e: current.getEdges())
            {
                Vertex n = (e.v1.equals(current)) ? e.v2 : e.v1;
                Vertex n2 = (e.v1.equals(current)) ? e.v1 : e.v2;

                //Update the distances
                double distance = e.weight + distances[indexOf(n2)];
                if(distances[indexOf(n)] > distance)
                {
                    distances[indexOf(n)] = distance;
                    previous[indexOf(n)] = current;
                }

                if(!visited.contains(n) && n.symbol != 'D')
                {
                    visited.append(n);
                    queue.append(n);
                }
            }
        }

        if(distances[indexOf(goal)] >= Double.POSITIVE_INFINITY ||previous[indexOf(goal)] == null )
        {
            //no solution
            return new Vertex[0];
        }

        //Treat prev like a queue
        Vertex curr = previous[indexOf(goal)];
        LinkedList<Vertex> vPath = new LinkedList<>();
        vPath.prepend(goal);
        while(curr != null)
        {
            vPath.prepend(curr);
            curr = previous[indexOf(curr)];
        }

        Vertex[] arr = new Vertex[vPath.size];
        int i = 0;
        Node<Vertex> currNode = vPath.head;
        while(currNode != null)
        {
            arr[i] = currNode.data;
            i++;
            currNode = currNode.next;
        }
        return arr;
    }

    Vertex getVertex(Vertex v) {
        /*Node<Vertex> node = vertices.find(v);
        if(node == null)
        {
            return null;
        }
        return node.data;*/

        Node<Vertex> current = vertices.head;
        while(current != null)
        {
            Vertex vertex = current.data;
            if(v.xPos == vertex.xPos && v.yPos == vertex.yPos)
            {
                return vertex;
            }
            current = current.next;
        }
        return null;
    }

    boolean isReachAbleThroughDoor(Vertex start, Vertex goal) {
        start = getVertex(start);
        goal = getVertex(goal);

        boolean foundKey = false;
        boolean throughDoor = false;

        if(start == null || goal == null)
        {
            return false;
        }

        LinkedList<Vertex> keys = getKeys();
        LinkedList<Vertex> doors = getDoors();

        keys.insertionSort();
        doors.insertionSort();

        int numKeys = keys.size;

        for(int i = 0; i < numKeys; i++)
        {
            Vertex currKey = getVertex(keys.poll());
            if(isReachAble(start,currKey))
            {

                for(int j = 0; j < doors.size; j++)
                {
                    Vertex currDoor = getVertex(doors.poll());
                    if(isReachAbleWithDoor(currKey,currDoor))
                    {

                        if(isReachAbleWithDoor(currDoor,goal))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    Vertex[] isReachAbleThroughDoorPath(Vertex start, Vertex goal)
    {
        start = getVertex(start);
        goal = getVertex(goal);

        boolean foundKey = false;
        boolean throughDoor = false;

        if(start == null || goal == null)
        {
            return new Vertex[0];
        }

        LinkedList<Vertex> keys = getKeys();
        LinkedList<Vertex> doors = getDoors();

        keys.insertionSort();
        doors.insertionSort();

        int numKeys = keys.size;
        for(int i = 0; i < numKeys; i++)
        {
            Vertex currKey = getVertex(keys.poll());
            if(isReachAble(start,currKey))
            {
                for(int j = 0; j < doors.size; j++)
                {
                    Vertex currDoor = getVertex(doors.poll());
                    if(isReachAbleWithDoor(currKey,currDoor))
                    {
                        if(isReachAbleWithDoor(currDoor,goal))
                        {
                            Vertex[] arr1 = isReachAblePath(start,currKey);
                            Vertex[] arr2 = isReachAblePathWithDoor(currKey,currDoor);
                            Vertex[] arr3 = isReachAblePathWithDoor(currDoor,goal);


                            Vertex[] path = new Vertex[arr1.length + arr2.length + arr3.length - 2];
                            int index = 0;
                            for(int h = 0; h < arr1.length; h++)
                            {
                                path[index] = arr1[h];
                                index++;
                            }
                            for(int h = 1; h < arr2.length; h++)
                            {
                                path[index] = arr2[h];
                                index++;
                            }
                            for(int h = 1; h < arr3.length; h++)
                            {
                                path[index] = arr3[h];
                                index++;
                            }


                            return path;
                        }
                    }
                }
            }
        }
        return new Vertex[0];
    }

    double shortestPathThroughDoor(Vertex start, Vertex goal) {
        start = getVertex(start);
        goal = getVertex(goal);

        boolean foundKey = false;
        boolean throughDoor = false;

        double smallestDistance = Double.POSITIVE_INFINITY;

        if(start == null || goal == null)
        {
            return smallestDistance;
        }

        LinkedList<Vertex> keys = getKeys();
        LinkedList<Vertex> doors = getDoors();

        keys.insertionSort();
        doors.insertionSort();

        int numKeys = keys.size;
        for(int i = 0; i < numKeys; i++)
        {
            Vertex currKey = getVertex(keys.poll());
            if(isReachAble(start,currKey))
            {
                for(int j = 0; j < doors.size; j++)
                {
                    Vertex currDoor = getVertex(doors.poll());
                    if(isReachAbleWithDoor(currKey,currDoor))
                    {
                        if(isReachAbleWithDoor(currDoor,goal))
                        {
                            double cd = shortestPathDistanceNoDoor(start,currKey) + shortestPathDistanceWithDoor(currKey,currDoor) + shortestPathDistanceWithDoor(currDoor,goal);
                            if(cd < smallestDistance)
                            {
                                if(cd < shortestPathDistanceNoDoor(start,goal))
                                {
                                    return cd;
                                }
                                return smallestDistance;
                            }
                            return smallestDistance;
                        }
                    }
                }
            }
        }
        return smallestDistance;
    }

    Vertex[] shortestPathThroughDoorPath(Vertex start, Vertex goal) {


        start = getVertex(start);
        goal = getVertex(goal);

        boolean foundKey = false;
        boolean throughDoor = false;

        double smallestDistance = Double.POSITIVE_INFINITY;

        if(start == null || goal == null)
        {
            return new Vertex[0];
        }

        LinkedList<Vertex> keys = getKeys();
        LinkedList<Vertex> doors = getDoors();

        keys.insertionSort();
        doors.insertionSort();

        int numKeys = keys.size;
        for(int i = 0; i < numKeys; i++)
        {
            Vertex currKey = getVertex(keys.poll());
            if(isReachAble(start,currKey))
            {
                for(int j = 0; j < doors.size; j++)
                {
                    Vertex currDoor = getVertex(doors.poll());
                    if(isReachAbleWithDoor(currKey,currDoor))
                    {
                        if(isReachAbleWithDoor(currDoor,goal))
                        {
                            double cd = shortestPathDistanceNoDoor(start,currKey) + shortestPathDistanceWithDoor(currKey,currDoor) + shortestPathDistanceWithDoor(currDoor,goal);
                            if(cd < smallestDistance)
                            {
                                Vertex[] arr1 = shortestPathPathNoDoor(start,currKey);
                                Vertex[] arr2 = shortestPathPathWithDoor(currKey,currDoor);
                                Vertex[] arr3 = shortestPathPathWithDoor(currDoor,goal);

                                Vertex[] path = new Vertex[arr1.length + arr2.length + arr3.length - 2];
                                LinkedList<Vertex> pat = new LinkedList<>();
                                int index = 0;
                                for(int h = 0; h < arr1.length; h++)
                                {
                                    path[index] = arr1[h];
                                    pat.append(arr1[h]);
                                    index++;
                                }
                                for(int h = 1; h < arr2.length; h++)
                                {
                                    path[index] = arr2[h];
                                    pat.append(arr2[h]);
                                    index++;
                                }
                                for(int h = 1; h < arr3.length; h++)
                                {
                                    path[index] = arr3[h];
                                    pat.append(arr3[h]);
                                    index++;
                                }



                                return path;
                            }
                            return new Vertex[0];
                        }
                    }
                }
            }
        }
        return new Vertex[0];
    }

    boolean canReachGoal(char targetGoal){
        Vertex start = getVertex('S');
        Vertex goal = getVertex(targetGoal);
        if(start == null || goal == null)
        {
            return false;
        }

        if(isReachAble(start,goal))
        {
            return true;
        }
        if(isReachAbleThroughDoor(start,goal))
        {
            return true;
        }
        return false;
    }

    Vertex[] canReachGoalPath(char targetGoal)
    {
        Vertex start = getVertex('S');
        Vertex goal = getVertex(targetGoal);
        if(start == null || goal == null)
        {
            return new Vertex[0];
        }

        if(isReachAble(start,goal))
        {
            return isReachAblePath(start,goal);
        }
        if(isReachAbleThroughDoor(start,goal))
        {
            return isReachAbleThroughDoorPath(start,goal);
        }
        return new Vertex[0];
    }

    double getRatio(Vertex goal){
        goal = getVertex(goal);
        Vertex start = getVertex('S');
        if(goal == null || start == null)
        {
            return Double.POSITIVE_INFINITY;
        }

        double goalTreasure = ((double) Character.getNumericValue(goal.symbol)) * 100.0;
        if(isReachAble(start,goal))
        {
            double pathLen = shortestPathDistanceNoDoor(start,goal);
            if(pathLen == 0)
            {
                return Double.POSITIVE_INFINITY;
            }
            double treasureRatio = goalTreasure/pathLen;
            return treasureRatio;
        }
        if(isReachAbleThroughDoor(start,goal))
        {
            double pathLen = shortestPathThroughDoor(start,goal);
            if(pathLen == 0)
            {
                return Double.POSITIVE_INFINITY;
            }
            double treasureRatio = goalTreasure/pathLen;
            return treasureRatio;
        }

        return Double.POSITIVE_INFINITY;
    }

    Vertex getRecommendedGoal(){
        Vertex[] array = getAllGoals();
        if(array.length == 0)
        {
            return null;
        }
        double min = Double.POSITIVE_INFINITY;
        Vertex smallest = null;
        for(Vertex goal : array)
        {
            double ratio = getRatio(goal);
            if(ratio < min)
            {
                min = ratio;
                smallest = goal;
            }
        }
        return smallest;
    }

    double getRecommendedRatio(){
        Vertex[] array = getAllGoals();
        if(array.length == 0)
        {
            return Double.POSITIVE_INFINITY;
        }
        double min = Double.POSITIVE_INFINITY;
        Vertex smallest = null;
        for(Vertex goal : array)
        {
            double ratio = getRatio(goal);
            if(ratio < min)
            {
                min = ratio;
                smallest = goal;
            }
        }
        return min;
    }

    Vertex[] getRecommendedPath(){
        Vertex rec = getRecommendedGoal();
        rec = getVertex(rec);
        return canReachGoalPath(rec.symbol);
    }

    private void removeEdgesWithVertex(Vertex v)
    {
        Node<Edge> current = edges.head;

        while(current != null)
        {
            if(current.data.containsVertex(v))
            {
                Vertex n = (current.data.v1.equals(v)) ? current.data.v2 : current.data.v1;

                n.edges.remove(current.data);

                //remove from list
                Node<Edge> e = current.next;

                edges.remove(current.data);

                current = e;
                continue;
            }
            current = current.next;
        }
    }

    private void removeVertex(Vertex v)
    {
        vertices.remove(v);
    }

    private void processRow(String row, int currentRowNum)
    {
        for(int i = 0; i < row.length();i++)
        {
            char c = row.charAt(i);

            if(c == '#')
            {
                continue;
            }
            else{
                Vertex newVertex = new Vertex(i,currentRowNum,c);
                vertices.append(newVertex);
            }
        }
    }

    private void processEdges()
    {
        Node<Vertex> current = vertices.head;

        while(current != null)
        {
            //Possible coordinates
            int x1 = current.data.xPos-1;
            int x2 = current.data.xPos+1;
            int y1 = current.data.yPos-1;
            int y2 = current.data.yPos+1;

            Node<Vertex> temp = vertices.head;
            while(temp != null)
            {
                    if(temp.data.checkPos(x1,current.data.yPos) != null)
                    {
                        //Create edge
                        Edge e = new Edge(current.data,temp.data,1);

                        //Add edge
                        edges.append(e);
                        current.data.edges.append(e);
                        temp.data.edges.append(e);
                    }

                    if(temp.data.checkPos(x2,current.data.yPos) != null)
                    {
                        Edge e = new Edge(current.data,temp.data,1);
                        //Add edge
                        edges.append(e);
                        current.data.edges.append(e);
                        temp.data.edges.append(e);
                    }

                if(temp.data.checkPos(current.data.xPos,y1) != null)
                {
                    Edge e = new Edge(current.data,temp.data,1);
                    //Add edge
                    edges.append(e);
                    current.data.edges.append(e);
                    temp.data.edges.append(e);
                }


                if(temp.data.checkPos(current.data.xPos,y2) != null)
                {
                    Edge e = new Edge(current.data,temp.data,1);
                    //Add edge
                    edges.append(e);
                    current.data.edges.append(e);
                    temp.data.edges.append(e);
                }

                temp = temp.next;
            }
            current = current.next;
        }
    }

    private int indexOf(Vertex v)
    {
        int x = v.xPos;
        int y = v.yPos;

        Node<Vertex> curr = vertices.head;
        int i = 0;
        while(curr != null){
            if(curr.data.xPos == x && curr.data.yPos == y)
            {
                return i;
            }
            i++;
            curr = curr.next;
        }
        return -1;
    }

    private LinkedList<Vertex> getKeys(){
        //Go through vertices and if key add
        LinkedList<Vertex> keys = new LinkedList<>();

        Node<Vertex> curr = vertices.head;

        while(curr != null)
        {
            if(curr.data.symbol == 'K')
            {
                keys.append(curr.data);
            }
            curr = curr.next;
        }
        return keys;
    }

    private LinkedList<Vertex> getDoors(){
        //Go through vertices and if door add
        LinkedList<Vertex> doors = new LinkedList<>();

        Node<Vertex> curr = vertices.head;

        while(curr != null)
        {
            if(curr.data.symbol == 'D')
            {
                doors.append(curr.data);
            }
            curr = curr.next;
        }
        return doors;
    }

    private boolean isReachAbleWithDoor(Vertex start, Vertex goal) {

        start = getVertex(start);
        goal = getVertex(goal);

        if(start == null || goal == null)
        {
            return false;
        }

        // Check if the current vertex is a goal
        if (start.equals(goal)) {
            return true;
        }

        if (start.symbol == (goal.symbol)) {
            return true;
        }

        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<Vertex> visited = new LinkedList<>();

        queue.append(start);
        visited.append(start);

        //While the queue is not empty
        while(!queue.isEmpty())
        {
            Vertex current = queue.poll();

            for(Edge e: current.getEdges())
            {
                Vertex n = (e.v1.equals(current)) ? e.v2 : e.v1;

                if(!visited.contains(n))
                {
                    if(n.symbol == goal.symbol)
                    {
                        return true;
                    }
                    visited.append(n);
                    queue.append(n);
                }
            }
        }
        return false;
    }

    private Vertex[] isReachAblePathWithDoor(Vertex start, Vertex goal) {
        start = getVertex(start);
        goal = getVertex(goal);

        LinkedList<Vertex> visited = new LinkedList<>();
        LinkedList<Vertex> path = new LinkedList<>();

        if (dfs2(start, goal, visited, path))
        {
            Vertex[] arr = new Vertex[path.size];
            Node<Vertex> curr = path.head;
            int index = 0;
            while (curr != null) {
                arr[index++] = curr.data;
                curr = curr.next;
            }
            return arr;
        } else {
            return new Vertex[0];
        }
    }

    private boolean dfs2(Vertex current, Vertex goal, LinkedList<Vertex> visited, LinkedList<Vertex> path) {
        visited.append(current);
        path.append(current);

        if (current.equals(goal)) {
            return true;
        }



        LinkedList<Edge> edges = current.edges.insertionSort();

        Node<Edge> e = edges.head;

        while(e != null)
        {
            Vertex n = (e.data.v1.equals(current)) ? e.data.v2 : e.data.v1;


            if (!visited.contains(n)) {

                if (dfs2(n, goal, visited, path)) {
                    return true;
                }
            }
            e = e.next;
        }

        //path.remove(path.tail.data);
        return false;
    }

    private double shortestPathDistanceWithDoor(Vertex start, Vertex goal) {
        start = getVertex(start);
        goal = getVertex(goal);
        if (start == null || goal == null)
        {
            return Double.POSITIVE_INFINITY;
        }

        // Check if the current vertex is a goal
        if (start.equals(goal)) {
            return 0;
        }

        if (start.symbol == (goal.symbol)) {
            return 0;
        }

        double[] distances = new double[vertices.size]; // Stores the shortest distance from start to that vertex


        //Fill with infinity
        for(int i = 0; i < distances.length; i++)
        {
            distances[i] = Double.POSITIVE_INFINITY;
        }

        LinkedList<Vertex> previous =  new LinkedList<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<Vertex> visited = new LinkedList<>();

        //Distance from start to itself is 0
        distances[vertices.indexOf(start)] = 0;

        //Go through all the current vertexes edges and update the distance
        queue.append(start);
        visited.append(start);


        //While the queue is not empty
        while(!queue.isEmpty())
        {
            Vertex current = queue.poll();

            for(Edge e: current.getEdges())
            {
                Vertex n = (e.v1.equals(current)) ? e.v2 : e.v1;
                Vertex n2 = (e.v1.equals(current)) ? e.v1 : e.v2;

                //Update the distances
                double distance = e.weight + distances[indexOf(n2)];
                if(distances[indexOf(n)] > distance)
                {
                    distances[indexOf(n)] = distance;
                }

                if(!visited.contains(n))
                {
                    visited.append(n);
                    queue.append(n);
                }
            }
        }

        return distances[indexOf(goal)];
    }

    private Vertex[] shortestPathPathWithDoor(Vertex start, Vertex goal) {
        start = getVertex(start);
        goal = getVertex(goal);
        if (start == null || goal == null)
        {

            return new Vertex[0];
        }

        // Check if the current vertex is a goal
        if (start.equals(goal)) {
            return new Vertex[] {start};
        }

        if (start.symbol == (goal.symbol)) {
            return new Vertex[] {start};
        }

        double[] distances = new double[vertices.size]; // Stores the shortest distance from start to that vertex


        //Fill with infinity
        for(int i = 0; i < distances.length; i++)
        {
            distances[i] = Double.POSITIVE_INFINITY;
        }

        Vertex [] previous =  new Vertex[vertices.size];
        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<Vertex> visited = new LinkedList<>();

        //Distance from start to itself is 0
        distances[vertices.indexOf(start)] = 0;

        //Go through all the current vertexes edges and update the distance
        queue.append(start);
        visited.append(start);
        previous[indexOf(start)] = null;


        //While the queue is not empty
        while(!queue.isEmpty())
        {
            Vertex current = queue.poll();

            for(Edge e: current.getEdges())
            {
                Vertex n = (e.v1.equals(current)) ? e.v2 : e.v1;
                Vertex n2 = (e.v1.equals(current)) ? e.v1 : e.v2;

                //Update the distances
                double distance = e.weight + distances[indexOf(n2)];
                if(distances[indexOf(n)] > distance)
                {
                    distances[indexOf(n)] = distance;
                    previous[indexOf(n)] = current;
                }

                if(!visited.contains(n))
                {
                    visited.append(n);
                    queue.append(n);
                }
            }
        }

        if(distances[indexOf(goal)] >= Double.POSITIVE_INFINITY ||previous[indexOf(goal)] == null )
        {
            //no solution
            return new Vertex[0];
        }

        //Treat prev like a queue
        Vertex curr = previous[indexOf(goal)];
        LinkedList<Vertex> vPath = new LinkedList<>();
        vPath.prepend(goal);
        while(curr != null)
        {
            vPath.prepend(curr);
            curr = previous[indexOf(curr)];
        }

        Vertex[] arr = new Vertex[vPath.size];
        int i = 0;
        Node<Vertex> currNode = vPath.head;
        while(currNode != null)
        {
            arr[i] = currNode.data;
            i++;
            currNode = currNode.next;
        }
        return arr;
    }

    private Vertex getVertex(char sym)
    {
        Node<Vertex> node = vertices.head;
        while(node!=null)
        {
            if(node.data.symbol == sym)
            {
                return node.data;
            }
            node = node.next;
        }
        return null;
    }

    /*public Maze createMaze2(String mazeString)
    {

        //Split into rows
        String[] rows = mazeString.split("\n");

        for(int k = 0 ; k < rows.length; k++)
        {
            this.processRow(rows[k], k);
        }
        this.processEdges();
        return maze;
    }*/

}
