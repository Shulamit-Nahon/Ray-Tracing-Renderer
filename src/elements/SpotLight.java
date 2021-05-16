package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class SpotLight extends PointLight  {

    private Vector _direction;
    /**
     * constructor for Spot Light
     *
     * @param intensity
     * @param position
     */
    protected SpotLight(Color intensity, Point3D position,Vector direction) {
        super(intensity, position);
        this._direction=direction.normalized();
    }
    /**
     * get Intensity of Spot Light
     * @param p
     * @return color Intensity
     */
    @Override
    public Color getIntensity(Point3D p){
        double factor =alignZero(Math.max(0,_direction.dotProduct(getL(p))));
        //if(!isZero(factor)){
            return super.getIntensity().scale(factor);
       // }
       // throw  new IllegalArgumentException("the__is zero");
    }

    /**
     * get L
     * @param p
     * @return Lighting direction value
     */
    public Vector getL(Point3D p){

        return super.getL(p);
    }
}
