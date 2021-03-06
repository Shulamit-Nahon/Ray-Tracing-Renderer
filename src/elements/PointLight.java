package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for point light
 */
public class PointLight extends Light implements LightSource{

    private final Point3D _position;
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
     * @param kc constant
     * @return point light
     */
    public PointLight setKc(double kc) {
        _Kc = kc;
        return this;
    }

    /**
     * set kl
     * @param kl linear
     * @return point light
     */
    public PointLight setKl(double kl) {
        _Kl = kl;
        return this;
    }

    /**
     * set Kq
     * @param kq quadratic
     * @return point light
     */
    public PointLight setKq(double kq) {
        _Kq = kq;
        return this;
    }

    /**
     * constructor for the Point Light
     * @param intensity the intensity of the point light
     * @param position the position of the point light

     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        this._position=position;
    }

    /**
     * get Intensity of Point Light
     * @param p the point of the point light
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
     * @param p the point of the point light
     * @return Lighting direction value
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subtract(_position).normalized();
    }

    /**
     * get the Distance between light source to point
     * @param point the point that we want the distance from it
     * @return Distance between light source to point
     */
    @Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }
}
