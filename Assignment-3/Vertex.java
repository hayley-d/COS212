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
}
