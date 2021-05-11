package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class SpotLight extends PointLight  {

    private Vector _direction;
    /**
     * constructor for
     *
     * @param intensity
     * @param position
     */
    protected SpotLight(Color intensity, Point3D position,Vector direction) {
        super(intensity, position);
        this._direction=direction;
    }

    @Override
    public Color getIntensity(Point3D p){
        double factor =alignZero(Math.max(0,_direction.dotProduct(getL(p))));
        if(!isZero(factor)){
            return super.getIntensity().scale(factor);
        }
        throw  new IllegalArgumentException("the__is zero");
    }

    public Vector getL(Point3D p){
        return _direction;
    }
}
