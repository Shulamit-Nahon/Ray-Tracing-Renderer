package primitives;

/**
 * class for the material of the geometry
 */
public class Material {
    public double diffuse =0;//diffuse coefficient
    public double specular =0;//specular coefficient
    public int Shininess =0;//shininess exponent
    public double kr=0;//Discount coefficients
    public double kt=0;//Discount coefficients

    /**
     * set Kr-Discount coefficients
     * @param kr1
     * @return the material
     */
    public Material setKr(double kr1) {
        kr = kr1;
        return this;
    }
    /**
     * set Kt-Discount coefficients
     * @param kt1
     * @return the material
     */
    public Material setKt(double kt1) {
        kt = kt1;
        return this;
    }

    /**
     * set the diffuse coefficient
     *
     * @param diffuse the diffuse coefficient
     * @return this
     */
    public Material setDiffuse(double diffuse){
        this.diffuse = diffuse;
        return this;
    }

    /**
     * set the specular coefficient
     *
     * @param specular the specular coefficient
     * @return this
     */
    public Material setSpecular(double specular){
        this.specular = specular;
        return this;
    }

    /**
     * set the shininess exponent
     *
     * @param shininess the shininess exponent
     * @return this
     */
    public Material setShininess(int shininess){
        this.Shininess = shininess;
        return this;
    }


}
