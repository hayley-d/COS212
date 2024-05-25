public class Edge implements Comparable<Edge> {
    Vertex v1;
    Vertex v2;
    double weight;

    Edge(Vertex v1, Vertex v2, double weight)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public String toString() {
        String v1Str = (v1 == null ? "NULL" : v1.toString());
        String v2Str = (v2 == null ? "NULL" : v2.toString());
        if(v1Str.compareTo(v2Str) >= 1){
            return v1Str + "<-[" + weight + "]->" + v2Str;
        } else {
            return v2Str + "<-[" + weight + "]->" + v1Str;
        }
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        try{
            Edge other = (Edge)obj;
            if(other.v1.equals(v2) && other.v2.equals(v1)){
                result = true;
            }

            if(other.v1.equals(v1) && other.v2.equals(v2)){
                result = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    String latexCode(){
        return "\\draw (" +v1.counter + ") -- node[midway, above] {" + weight + "} ("+v2.counter+");";
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }

    public boolean containsTrap(){
        if(v1 != null)
        {
            if(v1.symbol == 'T')
            {
                return true;
            }
        }
        if(v2 != null)
        {
            if(v2.symbol == 'T')
            {
                return true;
            }
        }
        return false;
    }

    public void doubleWeight(){
        this.weight *= 2;
    }

    public boolean containsDeadEnd(){
        if(v1 != null)
        {
            if(v1.symbol == ' ')
            {
                return true;
            }
        }
        if(v2 != null)
        {
            if(v2.symbol == ' ')
            {
                return true;
            }
        }
        return false;
    }

    public Vertex findDeadEnd(){
        if(v1 != null)
        {
            if(v1.symbol == ' ')
            {
                return v1;
            }
        }
        if(v2 != null)
        {
            if(v2.symbol == ' ')
            {
                return v2;
            }
        }
        return null;
    }

    public boolean containsVertex(Vertex v)
    {
        if(v1.equals(v))
        {
            return true;
        }
        if(v2.equals(v))
        {
            return true;
        }
        return false;
    }

}
