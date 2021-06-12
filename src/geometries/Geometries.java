package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * A Geometries class
 * defined by its a list intersectables
 */
public class Geometries extends Intersectable {

    List<Intersectable> intersectables;

    /**
     * @param intersectables_
     */
    public void add(Intersectable... intersectables_) {
        for (Intersectable item : intersectables_) {
            this.intersectables.add(item);
            border=item.border.union(border);
        }
    }

    /**
     * constructor that initialize the intersections list & the border
     */
    public Geometries() {
        intersectables = new LinkedList<>();
        border=Border.EMPTY;
    }

    /**
     * @param intersectables
     */
    public Geometries(Intersectable... intersectables) {
        this();
        add(intersectables);

    }

    /**
     * @param ray
     * @return list o intersections geopoints
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        if (!border.intersect(ray)) {
            return null;
        }
        List<GeoPoint> result = null;
        for (Intersectable item : this.intersectables) {
            List<GeoPoint> itemPoints = item.findGeoIntersections(ray, maxDistance);
            if (itemPoints != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemPoints);
            }
        }
        return result;
    }

    /**
     * recursively construct the hierarchy of the geometries
     */
    public void constructHierarchy() {

        if (intersectables.size() <= 2) { //base case - if the list has two geometries
            return;
        }

        Geometries left = new Geometries(); //the left group of geometries
        Geometries right = new Geometries(); //the right group of geometries

        //adds each geometry to a group
        for (Intersectable intersectable : intersectables) {
            if (border.chooseSide(intersectable.border.getCenter())) {
                left.add(intersectable);
            } else {
                right.add(intersectable);
            }
        }

        //if one of the group is empty return
        if (left.intersectables.isEmpty() || right.intersectables.isEmpty()) {
            return;
        }

        left.constructHierarchy(); //recursive
        right.constructHierarchy(); //recursive

        //creates the new intersectables list just with the left and right lists
        intersectables = new ArrayList<>(2);
        intersectables.add( //if one of the group has less than one geometry, add the geometry
                left.intersectables.size() > 1 ?
                        left :
                        left.intersectables.get(0)
                );
        intersectables.add(
                right.intersectables.size() > 1 ?
                        right :
                        right.intersectables.get(0)
        );
    }
}
