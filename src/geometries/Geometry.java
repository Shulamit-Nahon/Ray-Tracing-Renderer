package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface for Geometries that have a normal
 */
public abstract class Geometry extends Intersectable {

    // emission color of the Geometry
    protected Color _emission = Color.BLACK;
    // Material of the Geometry
    private Material _material = new Material();

    /**
     * @return the Material of the Geometry
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * set the Material of the Geometry
     * @param material of the geometry
     * @return the Geometry
     */
    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    /**
     * set the emission light of the geometry
     *
     * @param emission The emission light of the geometry
     * @return the Geometry that has been set
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
