package geometries;

import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 */
public class Polygon extends Geometry {

    protected List<Point3D> vertices; // List of polygon's vertices

    protected Plane plane; //Associated plane in which the polygon lays

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point3D... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
           // if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                //throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    /**
     * @param point Point3D
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {

        return plane.getNormal();
    }

    /**
     *  find geometry Intersections points with the ray and polygon
     * @param ray
     * @return geometry Intersections points with the ray and polygon
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> planeIntersections = this.plane.findGeoIntersections(ray,maxDistance);
        //check if there is intersection with the plane
        if (planeIntersections == null) {
            return null;
        }
        Vector v = ray.getDirection();
        int i = vertices.size();
        int count1 = 0; //count for 𝒗 ∙ 𝑵𝒊  with the sign -
        int count2 = 0; //count for 𝒗 ∙ 𝑵𝒊  with the sign +
        //The point is inside the polygon if all 𝒗 ∙ 𝑵𝒊 have the same sign (+/-)
        Vector v2 = null;
        while (i != 1) {
            int j = vertices.size() - i;
            Vector v1 = (this.vertices.get(j)).subtract(ray.getpOrigin());  //𝑣1 = 𝑃i − 𝑃0
            v2 = (this.vertices.get(j + 1)).subtract(ray.getpOrigin()); //𝑣2 = 𝑃i+1 − 𝑃0
            Vector N1 = v1.crossProduct(v2).normalized();   //𝑁1 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 𝑣1 × 𝑣2
            double dot = v.dotProduct(N1);
            if (!isZero(dot)) {
                if (dot < 0) count1++;
                else count2++;
            }
            i--;
        }
        Vector v1 = (this.vertices.get(0)).subtract(ray.getpOrigin());  //𝑣1 = 𝑃1 − 𝑃0
        Vector Ni = v2.crossProduct(v1).normalized();
        double dot = v.dotProduct(Ni);
        if (!isZero(dot)) {
            if (dot < 0) count1++;
            else count2++;
        }
        if ((count1 == vertices.size()) || (count2 == vertices.size())) {
            return List.of(new GeoPoint(this, planeIntersections.get(0).point));
        }
        return null;
    }


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


    /**
     *  find 6 minimum and maximum value of the shape
     */
    public void findMinMaxForBounding()
    {
         maxX=Double.NEGATIVE_INFINITY;
         maxY=Double.NEGATIVE_INFINITY;
         maxZ=Double.NEGATIVE_INFINITY;
         minX=Double.POSITIVE_INFINITY;
         minY=Double.POSITIVE_INFINITY;
         minZ=Double.POSITIVE_INFINITY;

        for(int i=0;i<vertices.size();i++)
        {
          if(vertices.get(i).getX()<minX)  {
              minX=vertices.get(i).getX();
          }
          if(vertices.get(i).getX()>maxX){
              maxX=vertices.get(i).getX();
          }
          if(vertices.get(i).getY()<minY){
              minY=vertices.get(i).getY();
          }
          if(vertices.get(i).getY()>maxY){
                maxY=vertices.get(i).getY();
          }
          if(vertices.get(i).getZ()<minZ){
                minZ=vertices.get(i).getY();
          }
          if(vertices.get(i).getZ()>maxZ){
                maxZ=vertices.get(i).getY();
          }
        }
    }
}


