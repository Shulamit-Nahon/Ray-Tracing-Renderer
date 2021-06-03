package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

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
     *
     * @param ray
     * @return list o intersections geopoints
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistabce) {
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
        for (Intersectable item : this.intersectables){
        if(item.maxX>maxX){
            maxX=item.maxX;
        }
        if(item.maxY>maxY){
            maxY=item.maxY;
        }
        if(item.maxZ>maxZ){
            maxZ=item.maxZ;
        }
        if(item.minX<minX){
            minX=item.minX;
        }
        if(item.minY<minY){
            minY=item.minY;
        }
        if(item.minZ<minZ){
            minZ=item.minZ;
        }
    }
    }

    @Override
    public boolean intersect(Ray r) {
        return false;
    }


}
