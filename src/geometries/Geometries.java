package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * A Geometries class
 * defined by its a list intersectables
 */
public class Geometries implements Intersectable{

    List<Intersectable> intersectables = null;

    /**
     *
     * @param intersectables_
     */
    public void add(Intersectable... intersectables_){
        for (Intersectable item : intersectables_) {
            this.intersectables.add(item);
        }
    }

    /**
     * constructor that initialize the intersections list
     */
    public Geometries() {
        intersectables= new LinkedList<>();
    }

    /**
     *
     * @param intersectables
     */
    public Geometries(Intersectable... intersectables) {
        this.intersectables = new LinkedList<>();
            add(intersectables);
    }

    /**
     * //////////////////////////////////
     *
     * @param ray
     * @return the intersections list
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result = null;
        for (Intersectable item : this.intersectables) {
            List<Point3D> itemPoints = item.findIntersections(ray);
            if(itemPoints!=null){
                if (result == null){
                    result = new LinkedList<>();
                }
                result.addAll(itemPoints);
            }
        }
        return result;
    }
}
