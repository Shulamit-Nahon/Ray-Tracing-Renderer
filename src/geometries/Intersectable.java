package geometries;

import primitives.*;

import java.util.List;

/**
 * interface for Geometries that have intersections
 */
public interface Intersectable {

   public List<Point3D> findIntersections(Ray ray);
}