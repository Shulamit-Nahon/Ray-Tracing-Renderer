package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * interface for Geometries that have intersections
 */
public interface Intersectable {

    class GeoPoint{
        public Geometry geometry;
        public Point3D point;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }


        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry=geometry;
            this.point=point;
        }
    }
    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList
                .stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }


   default List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersections(ray,Double.POSITIVE_INFINITY);
   }
    List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance);

}