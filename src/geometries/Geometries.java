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
     * @param ray
     * @return list o intersections geopoints
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistabce) {
        if(!intersect(ray)) {
            return null;
        }
        List<GeoPoint> result = null;
        for (Intersectable item : this.intersectables) {
            List<GeoPoint> itemPoints = item.findGeoIntersections(ray, maxDistabce);
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
     * A function that passes the maximum and minimum points in all the bodies in the geometries and finds the maximum and minimum values
     */
    @Override
    public void findMinMaxForBounding() {

         maxX=Double.NEGATIVE_INFINITY;
         maxY=Double.NEGATIVE_INFINITY;
         maxZ=Double.NEGATIVE_INFINITY;
         minX=Double.POSITIVE_INFINITY;
         minY=Double.POSITIVE_INFINITY;
         minZ=Double.POSITIVE_INFINITY;

        for (Intersectable item : this.intersectables) {
            item.findMinMaxForBounding();

            if (item.maxX > maxX) {
                maxX = item.maxX;
            }
            if (item.maxY > maxY) {
                maxY = item.maxY;
            }
            if (item.maxZ > maxZ) {
                maxZ = item.maxZ;
            }
            if (item.minX < minX) {
                minX = item.minX;
            }
            if (item.minY < minY) {
                minY = item.minY;
            }
            if (item.minZ < minZ) {
                minZ = item.minZ;
            }
        }
    }

    /**
     * recursively construct the hierarchy of the geometries
     */
    public void constructHierarchy(){

        if(intersectables.size()<=2){ //base case - if the list has two geometries
            return;
        }

        Geometries left = new Geometries(); //the left group of geometries
        Geometries right = new Geometries(); //the right group of geometries

        Predicate<Point3D> side = cutPredicate();

        //adds each geometry to a group
        for (Intersectable intersectable: intersectables) {
            if(side.test(intersectable.volumeCenter())){
                left.add(intersectable);
            }
            else {
                right.add(intersectable);
            }
        }

        //if one of the group is empty return
        if(left.intersectables.isEmpty() || right.intersectables.isEmpty()) {
            return;
        }

        left.constructHierarchy(); //recursive
        right.constructHierarchy(); //recursive

        //creates the new intersectables list just with the left and right lists
        intersectables= new ArrayList<>(2);
        add( //if one of the group has less than one geometry, add the geometry
                left.intersectables.size() > 1 ?
                        left :
                        left.intersectables.get(0),
                right.intersectables.size() > 1 ?
                        right :
                        right.intersectables.get(0)
        );
    }
}
