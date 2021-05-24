package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for point light
 */
public class PointLight extends Light implements LightSource{

    private Point3D _position;
    /**
     * Factors (kc
     * , kl
     * , kq
     * ) for attenuation with distance (d)
     */
    private double _Kc=1;
    private double _Kl=0;
    private double _Kq=0;

    /**
     * set Kc
     * @param kc
     * @return point light
     */
    public PointLight setKc(double kc) {
        _Kc = kc;
        return this;
    }
    /**
     * set kl
     * @param kl
     * @return point light
     */
    public PointLight setKl(double kl) {
        _Kl = kl;
        return this;
    }

    /**
     * set Kq
     * @param kq
     * @return point light
     */
    public PointLight setKq(double kq) {
        _Kq = kq;
        return this;
    }

    /**
     * constructor for
     * @param intensity
     * @param position

     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        this._position=position;
       // this._Kc=kc;
        //this._Kl=kl;
        //this._Kq=kq;
    }

    /**
     * get Intensity of Point Light
     * @param p
     * @return color Intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        double distance=_position.distance(p);
        double denominator=_Kc+_Kl*distance+_Kq*distance*distance;
        return _intensity.reduce(denominator);
    }

    /**
     * get L
     * @param p
     * @return Lighting direction value
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalized();
    }

    @Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }
}
