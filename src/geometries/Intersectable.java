package geometries;

import primitives.*;

import java.util.List;

/**
 * interface Intersectable
 */
public interface Intersectable {

    List<Point3D> findIntersections(Ray ray);
}