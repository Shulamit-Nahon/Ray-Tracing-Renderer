package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface for Geometries that have a normal
 */
public abstract class Geometry implements Intersectable {

    protected Color _emission = Color.BLACK;
    private Material _material = new Material();

    public Material getMaterial() {
        return _material;
    }

    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    /**
     * set the emission light of the geometry
     *
     * @param emission The emission light of the geometry
     * @return the Geometry that has been seted
     */
    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }

    /**
     * get the emission light of the geometry
     *
     * @return the emission light of the geometry
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * @param point Point3D
     * @return normal of the geometry
     */
    abstract public Vector getNormal(Point3D point);
}
