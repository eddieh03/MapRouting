public class DirectedEdge {

    private final int v;//the tail vertex
    private final int w; // w the head vertex
    private final double weight; //the weight of the directed edge
    private boolean rushHour;

    public DirectedEdge(int v, int w, double weight,boolean rushHour) {
        if (v < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (w < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
        this.rushHour = rushHour;
        
    }
    
    public boolean rushHour(){
        return rushHour;
    }

    /**
     * Returns the tail vertex of the directed edge.
     */
    public int from() {
        return v;
    }

    /**
     * Returns the head vertex of the directed edge.
     */
    public int to() {
        return w;
    }

    /**
     * Returns the weight of the directed edge.
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns a string representation of the directed edge.
     */
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }  
}
