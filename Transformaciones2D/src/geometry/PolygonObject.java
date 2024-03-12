package geometry;

import math.Matrix3x3;

import java.util.ArrayList;
import java.util.Scanner;

import math.TranslScalRot3x3;
import math.Vector3;
import java.io.File;
import display.Main;

public class PolygonObject {
    ArrayList<Vector3> vertices;
    public ArrayList<Vector3> transformedVertices; // vertices after transformation
    public ArrayList<Edge> edges;
    Main canvas;
    public ObjectTransformation ot;

    public PolygonObject() {
        vertices = new ArrayList<Vector3>();
        transformedVertices = new ArrayList<Vector3>();
        edges = new ArrayList<Edge>();
        ot = new ObjectTransformation();
    }

    public void readObject(String filename) {
        try {
            // read the number of vertices and then the vertices
            Scanner in = new Scanner(new File(filename));
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                double x = in.nextDouble();
                double y = in.nextDouble();
                vertices.add(new Vector3(x, y));
            }
            // read the number of edges and then the edge indices
            n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int start = in.nextInt();
                int end = in.nextInt();
                edges.add(new Edge(start, end));
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
        resetVertices();
    }

    public void setCanvas(Main canvas) {
        this.canvas = canvas;
    }

    public void draw() {
        if (this.canvas != null) {
            for (Edge e : edges) {
                // draw the transformed edges
                Vector3 v1 = transformedVertices.get(e.start);
                Vector3 v2 = transformedVertices.get(e.end);
                int x1 = (int) v1.vector[0];
                int y1 = (int) v1.vector[1];
                int x2 = (int) v2.vector[0];
                int y2 = (int) v2.vector[1];
                canvas.drawOneLine(x1, y1, x2, y2);
            }
        }
    }

    public void resetVertices() {
        ot.reset();
        transformedVertices.clear();
        for (Vector3 v : vertices) {
            Vector3 newVertex = new Vector3(v.vector[0], v.vector[1]);
            transformedVertices.add(newVertex);
        }
    }

    public void transformObject() {
        transformedVertices.clear();
        TranslScalRot3x3 tsr = ot.createTransformation();
        for (Vector3 v : vertices) {
            Vector3 newVertex = Matrix3x3.times(tsr, v);
            transformedVertices.add(newVertex);
        }
    }
}