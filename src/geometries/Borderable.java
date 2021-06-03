package geometries;

import primitives.Point3D;
import primitives.Ray;

public abstract class Borderable {
//max point coordinate
    public double maxX;
    public double maxY;
    public double maxZ;
//min point coordinate
    public double minX;
    public double minY;
    public double minZ;

    /**
     *  find 6 minimum and maximum value of the shape
     *
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
