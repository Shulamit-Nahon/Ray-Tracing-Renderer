package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * A Geometries class
 * defined by its a list intersectables
 */
public class Geometries implements Intersectable {

    List<Intersectable> intersectables = null;

    /**
     * @param intersectables_
     */
    public void add(Intersectable... intersectables_) {
        for (Intersectable item : intersectables_) {
            this.intersectables.add(item);
        }
    }

    /**
     * constructor that initialize the intersections list
     */
    public Geometries() {
        intersectables = new LinkedList<>();
    }

    /**
     * @param intersectables
     */
    public Geometries(Intersectable... intersectables) {
        this.intersectables = new LinkedList<>();
        add(intersectables);
    }

    /**
     *
     * @param ray
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> result = null;
        for (Intersectable item : this.intersectables) {
            List<GeoPoint> itemPoints = item.findGeoIntersections(ray);
            if (itemPoints != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemPoints);
            }
        }
        return result;
    }
}
