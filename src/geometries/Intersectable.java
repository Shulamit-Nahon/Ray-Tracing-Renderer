package geometries;

import primitives.*;

import java.util.List;

/**
 * interface for Geometries that have intersections
 */
public interface Intersectable {

    /**
     * find intersections
     * @param ray
     * @return
     */
    public List<Point3D> findIntersections(Ray ray);
}