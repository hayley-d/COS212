public class Vertex implements Comparable<Vertex>{
    LinkedList<Edge> edges;
    int xPos;
    int yPos;
    char symbol;
    static int globalCounter = 0;
    int counter = globalCounter++;

    Vertex(int x, int y, char sym) {
        this.xPos = x;
        this.yPos = y;
        this.symbol = sym;
        edges = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "(" + xPos + ":" + yPos + ")[" + symbol + "]";
    }

    String latexCode(){
        return "\\node[node] (" +counter + ") at (" + xPos + "," + (yPos) + ") {" + symbol + "};";
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        try {
            Vertex vertex = (Vertex) obj;
            if (vertex.xPos == xPos && vertex.yPos == yPos) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    Edge[] getEdges() {
       Edge[] e = new Edge[edges.size];
       Node<Edge> current = edges.head;
       int i = 0;
       while(current!=null)
       {
           e[i] = current.data;
           i++;
           current = current.next;
       }
       return e;
    }

    public void addEdge(Edge e)
    {
        edges.append(e);
    }

    public void removeEdge(Edge e)
    {
        edges.remove(e);
    }

    public boolean containsEdge(Edge e)
    {
        return edges.contains(e);
    }

    public Edge findEdge(Edge e)
    {
        return edges.find(e).data;
    }

    @Override
    public int compareTo(Vertex other) {
        if (this.xPos != other.xPos) {
            return Integer.compare(this.xPos, other.xPos);
        } else {
            return Integer.compare(this.yPos, other.yPos);
        }
    }

    public Edge addEdges()
    {
        Vertex v1 = null;
        Edge e1 = null;
        Vertex v2 = null;
        Edge e2 = null;

        Node<Edge> current = this.edges.head;
        double newWeight = 0;
        while(current != null)
        {
            newWeight += current.data.weight;
            Vertex temp = current.data.v1;
            if(temp.equals(this))
            {
                //use v2
                if(v1 == null)
                {
                    v1 = current.data.v2;
                    e1 = current.data;
                }
                else{

                    v2 = current.data.v2;
                    e2 = current.data;
                }
            }
            else{
                if(v1 == null)
                {
                    v1 = current.data.v1;
                    e1 = current.data;
                }
                else{
                    v2 = current.data.v1;
                    e2 = current.data;
                }
            }
            current = current.next;
        }

        //Create a new edge between the two vertices
        Edge newEdge = new Edge(v1,v2,newWeight);
        if(v1 !=null)
        {
            v1.addEdge(newEdge);
            v1.removeEdge(e1);
        }
        if(v2 != null)
        {
            v2.addEdge(newEdge);
            v2.removeEdge(e2);
        }
        return newEdge;
    }

    public Vertex checkPos(int x, int y)
    {
        if(xPos == x && yPos == y)
        {
            return this;
        }
        return null;
    }

    public Edge smallestEdge(boolean goUp){
        if(goUp)
        {
            Node<Edge> edge = edges.head;
            Edge smallEdge = null;
            double min = Double.POSITIVE_INFINITY;
            while(edge != null)
            {
                Vertex n = (edge.data.v1.equals(this)) ? edge.data.v2 : edge.data.v1;

                if(n.yPos < yPos)
                {
                    if(edge.data.weight < min)
                    {
                        smallEdge = edge.data;
                    }
                }
                edge = edge.next;
            }
            return smallEdge;
        }
        else{
            Node<Edge> edge = edges.head;
            Edge smallEdge = null;
            double min = Double.POSITIVE_INFINITY;
            while(edge != null)
            {
                Vertex n = (edge.data.v1.equals(this)) ? edge.data.v2 : edge.data.v1;

                if(n.yPos > yPos)
                {
                    if(edge.data.weight < min)
                    {
                        smallEdge = edge.data;
                    }
                }
                edge = edge.next;
            }
            return smallEdge;
        }
    }
}
