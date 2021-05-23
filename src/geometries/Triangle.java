package geometries;

import primitives.Vector;
import primitives.*;

import java.util.List;
//import java.util.Vector;

/**
 * A Triangle class registered from a polygon
 * defined by a polygon with 3 vertices
 */
public class Triangle extends Polygon {
    /**
     * constructor for the Triangle
     *
     * @param p1 Point3D Vertex number 1 of the triangle
     * @param p2 Point3D Vertex number 2 of the triangle
     * @param p3 Point3D Vertex number 3 of the triangle
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {

        super(p1, p2, p3);
    }

}
