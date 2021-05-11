package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for point light
 */
public class PointLight extends Light implements LightSource{

    private Point3D _position;
    private double _Kc=1;
    private double _Kl=0;
    private double _Kq=0;

    public PointLight setKc(double kc) {
        _Kc = kc;
        return this;
    }

    public PointLight setKl(double kl) {
        _Kl = kl;
        return this;
    }

    public PointLight setKq(double kq) {
        _Kq = kq;
        return this;
    }

    /**
     * constructor for
     * @param intensity
     * @param position

     */
    protected PointLight(Color intensity,Point3D position) {
        super(intensity);
        this._position=position;
       // this._Kc=kc;
        //this._Kl=kl;
        //this._Kq=kq;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double distance=_position.distance(p);
        double denominator=_Kc+_Kl*distance+_Kq*distance*distance;
        return _intensity.reduce(denominator);
    }

    @Override
    public Vector getL(Point3D p) {
        return _position.subtract(p);
    }
}
