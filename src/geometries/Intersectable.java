package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * interface for Geometries that have intersections
 */
public abstract class Intersectable extends Borderable {
    /**
     * class for geometry point
     */
    public class GeoPoint{
        public Geometry geometry;
        public Point3D point;

        /**
         * equals function
         * @param o
         * @return true if o equals,false else
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        /**
         * GeoPoint constructor
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry=geometry;
            this.point=point;
        }
    }

    /**
     * find Intersections point with the ray
     * @param ray
     * @return
     */
    public List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList
                .stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }

    /**
     * find Intersections point with the ray
     * @param ray
     * @return Intersections point with the ray
     */
   public  List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersections(ray,Double.POSITIVE_INFINITY);
   }

    /**
     * find Geometry Intersections points
     * @param ray
     * @param maxDistance
     * @return Geometry Intersections points
     */
    public abstract List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance);

    /**
     A function that checks whether the fund cuts the demarcation boundaries of the entities,
     then sends to the function to find intersection points,
     if not, will do nothing and save unnecessary activity
     * @param r -ray
     */
    public  List<GeoPoint> IntersectableWithBounding(Ray r){
        
        if(intersect(r)){
             return findGeoIntersections(r);
        }
        return null;   // else doesn't calculate Intersections point
    }

   }

