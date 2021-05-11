package primitives;

/**
 * class for the material of the geometry
 */
public class Material {
    public double _kD=0;//diffuse
    public double _kS=0;//specular
    public int _nShininess=0;// Shininess

    /**
     * set the kD
     * @param kD
     * @return this material
     */
    public Material setkD(double kD){
        this._kD=kD;
        return this;
    }

    /**
     * set the kS
     * @param ks
     * @return  this material
     */
    public Material setkS(double ks){
        this._kS=ks;
        return this;
    }

    public Material setnShininess(int nShininess){
        this._nShininess=nShininess;
        return this;
    }
}
