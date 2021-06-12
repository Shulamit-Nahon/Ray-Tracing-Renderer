package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.function.Predicate;
/*
class for Demarcation of geometries
 */
public abstract class Borderable {

//max point coordinate
    public double maxX;
    public double maxY;
    public double maxZ;

//min point coordinate
    public double minX;
    public double minY;
    public double minZ;

    private boolean areBorderSet = false;

    /**
     *  find 6 minimum and maximum value of the shape
     *
     */
    protected void findMinMaxForBounding(){
        maxX = Double.POSITIVE_INFINITY;
        maxY = Double.POSITIVE_INFINITY;
        maxZ = Double.POSITIVE_INFINITY;

        minX = Double.NEGATIVE_INFINITY;
        minY = Double.NEGATIVE_INFINITY;
        minZ = Double.NEGATIVE_INFINITY;
    }

    /**
     * A function that finds whether there are intersections with the boundary that warmed the bodies
     * @param r
     * @return TRUE if there is intersection point wuth the boundary min and max value
     */
    public boolean intersect(Ray r){

        if(!areBorderSet){
            findMinMaxForBounding();
            areBorderSet=true;
        }
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

    /**
     * divides the bounding volume into two halves
     * @return a predicate that decides if the point is in one side or the other
     */
    protected Predicate<Point3D> cutPredicate() {
        double lengthX = maxX -minX;
        double lengthY = maxY -minY;
        double lengthZ = maxZ -minZ;

        Point3D center = volumeCenter();

        Predicate<Point3D> zPredicate = point -> point.getZ() > center.getZ();

        //checks which length is the largest
        if(lengthX<lengthY){
            if(lengthY<lengthZ){
               return zPredicate;
            }
            else {
                return point -> point.getY() > center.getY();
            }
        }
        else{
            if(lengthX>lengthZ) {
                return point -> point.getX() > center.getX();
            }
            else {
                return zPredicate;
            }
        }
    }

    /**
     * calculates the point of the volume center
     * @return new point of the volume center
     */
    protected Point3D volumeCenter(){
        if(isInfinite()){
            return Point3D.ZERO;
        }
        return new Point3D((maxX+minX)/2, (maxY+minY)/2,(maxZ+minZ)/2);
    }

    /**
     * checks one of the values is infinity
     * @return true if one of the values is infinity
     */
    private boolean isInfinite() {
        double sum= maxX+maxY+maxZ+minX+minY+minZ;
        return sum == Double.NEGATIVE_INFINITY || sum == Double.POSITIVE_INFINITY || sum==Double.NaN;
    }
}
