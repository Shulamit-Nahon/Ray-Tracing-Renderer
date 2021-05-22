package primitives;

/**
 * class for the material of the geometry
 */
public class Material {
    public double diffuse =0;
    public double specular =0;
    public int Shininess =0;

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
