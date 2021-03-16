package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * interface for Geometries that have a normal
 */
public interface Geometry extends Intersectable {
    /**
     * @param point Point3D
     * @return normal of the geometry
     */
    Vector getNormal(Point3D point);
}
