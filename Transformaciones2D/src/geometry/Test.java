package geometry;

public class Test {
    // Main program to test the PolygonObject class
    /**
     * @param args
     */
    public static void main(String[] args) {
        PolygonObject po = new PolygonObject();
        po.readObject("casita2D.txt");
        System.out.println(po.vertices);
        System.out.println(po.edges);

        po.resetVertices();
        po.ot.dx += ObjectTransformation.DELTA_TRANSL;
        po.transformObject();
        System.out.println("Translated vertices:");
        System.out.println(po.transformedVertices);

        po.resetVertices();
        po.ot.sx += ObjectTransformation.DELTA_SCAL;
        po.ot.sy += ObjectTransformation.DELTA_SCAL;
        po.transformObject();
        System.out.println("Scaled vertices:");
        System.out.println(po.transformedVertices);
        po.resetVertices();

    }   
}
