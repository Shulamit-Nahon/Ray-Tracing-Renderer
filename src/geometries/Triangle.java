package geometries;
import primitives.Vector;
import primitives.*;
import java.util.List;
//import java.util.Vector;

/**
 * A Trianglem class registered from a polygon
 * defined by a polygon with 3 vertices
 */
public class Triangle extends Polygon{
    /**
     * constructor for the Triangle
     * @param p1 Point3D Vertex number 1 of the triangle
     * @param p2 Point3D Vertex number 2 of the triangle
     * @param p3 Point3D Vertex number 3 of the triangle
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {

        super(p1,p2,p3);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
       Vector v=ray.getDirection();
       Vector v1= (this.vertices.get(0)).subtract(ray.getpOrigin());//𝑣1 = 𝑃1 − 𝑃0
       Vector v2= (this.vertices.get(1)).subtract(ray.getpOrigin());//𝑣2 = 𝑃2 − 𝑃0
       Vector v3= (this.vertices.get(2)).subtract(ray.getpOrigin());//𝑣3 = 𝑃3 − 𝑃0
       Vector N1= v1.crossProduct(v2).normalized();//𝑁1 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 𝑣1 × 𝑣2
       Vector N2= v2.crossProduct(v3).normalized();//𝑁2 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 𝑣2 × 𝑣3
       Vector N3= v3.crossProduct(v1).normalized();//𝑁3 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 𝑣3 × 𝑣1
        //The point is inside the triangle if all 𝒗 ∙ 𝑵𝒊 have the same sign (+/-)
        if(v.dotProduct(N1)<0&&v.dotProduct(N2)<0&&v.dotProduct(N3)<0){
            return  this.plane.findIntersections(ray);
        }



        return null;
    }
}
