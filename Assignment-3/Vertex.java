public class Vertex {
    Edge edges;
    int xPos;
    int yPos;
    char symbol;
    static int globalCounter = 0;
    int counter = globalCounter++;

    Vertex(int x, int y, char sym) {
        
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
        return null;
    }
}
