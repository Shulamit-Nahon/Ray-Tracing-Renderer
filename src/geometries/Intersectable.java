package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * interface for Geometries that have intersections
 */
public abstract class Intersectable extends Borderable {

    public class GeoPoint{
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
    public List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList
                .stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }


   public  List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersections(ray,Double.POSITIVE_INFINITY);
   }
    public abstract List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance);

    /**
     A function that checks whether the fund cuts the demarcation boundaries of the entities,
     then sends to the function to find intersection points,
     if not, will do nothing and save unnecessary activity
     * @param r
     */
    public  List<GeoPoint> IntersectableWithBounding(Ray r){
        findMinMaxForBounding();
        if(intersect(r)==true){
             return findGeoIntersections(r);
        }
       // else dont calculate Intersections point
        return null;
    }

    /**
     *  find 6 minimum and maximum value of the shape

     */
    public abstract void findMinMaxForBounding();

    /**
     * A function that finds whether there are intersections with the boundary that warmed the bodies
     * @param r
     * @return TRUE if there is intersection point wuth the boundary min and max value
     */
   public boolean intersect(Ray r){
       double rayPoX=r.getpOrigin().getX();
       double rayPoY=r.getpOrigin().getY();
       double rayPoZ=r.getpOrigin().getZ();
           double tmin = (minX - rayPoX) / r.getDirection().getHead().getX();
           double tmax = (maxX - rayPoX) / r.getDirection().getHead().getX();
           if (tmin > tmax){
               //swap(tmin, tmax);
               double temp=tmin;
               tmin=tmax;
               tmax=temp;
           }

           double tymin = (minY - rayPoY) / r.getDirection().getHead().getY();
           double tymax = (maxY -rayPoY) /  r.getDirection().getHead().getY();

           if (tymin > tymax){
               //swap(tymin, tymax);
               double temp=tymin;
               tymin=tymax;
               tymax=temp;
           }

           if ((tmin > tymax) || (tymin > tmax))
               return false;

           if (tymin > tmin)
               tmin = tymin;

           if (tymax < tmax)
               tmax = tymax;

           double tzmin = (minZ -rayPoZ) / r.getDirection().getHead().getZ();
           double tzmax = (maxZ - rayPoZ) /  r.getDirection().getHead().getZ();

           if (tzmin > tzmax){
               //swap(tzmin, tzmax);
               double temp=tzmin;
               tzmin=tzmax;
               tzmax=temp;
           }

           if ((tmin > tzmax) || (tzmin > tmax))
               return false;

           if (tzmin > tmin)
               tmin = tzmin;

           if (tzmax < tmax)
               tmax = tzmax;

           return true;
       }


   }

