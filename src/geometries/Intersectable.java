package geometries;

import primitives.*;

import java.util.List;

/**
 * interface Intersectable
 */
public interface Intersectable {

   public List<Point3D> findIntersections(Ray ray);
}