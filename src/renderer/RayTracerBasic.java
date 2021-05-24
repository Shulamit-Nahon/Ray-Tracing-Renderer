package renderer;

import elements.LightSource;
import primitives.Color;
import primitives.*;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1;
    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;//עומק רקורסיה
    private static final double MIN_CALC_COLOR_K = 0.001;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(ray);
        if (intersections != null) {
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
            return calcColor(closestPoint,ray);
                    //.add(_scene.ambientLight.getIntensity())
                   // .add(closestPoint.geometry.getEmission());
        }
        //ray did not intersect any geometrical object
        return _scene.background;
    }

    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint geopoint, Ray ray, int level, double k) {

            Color color = _scene.ambientLight.getIntensity()
                    .add(geopoint.geometry.getEmission());
            color = color.add(calcLocalEffects(geopoint, ray));
            return 1 == level ? color : color.add(calcGlobalEffects(geopoint, ray.getDirection(), level, k));
        }

    //  private Color calcColor(GeoPoint point,Ray ray) {
  //      return calcLocalEffects(point,ray);
  //  }

    /**
     *calculate local effect
     * @param intersection
     * @param ray
     * @return color of the local effect
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {

        Vector v = ray.getDirection();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.Shininess;
        double kd = material.diffuse;
        double ks = material.specular;
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(lightSource,l,n, intersection)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color
                            .add(calcDiffusive(kd, l, n, lightIntensity),
                                    calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * Checks if the body is hidden from the light by another body
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
      //ray from point toward light direction offset by delta
        Ray lightRay = new Ray(geopoint.point,lightDirection,n,DELTA);
        List<GeoPoint> intersections = _scene.geometries
                .findGeoIntersections(lightRay, light.getDistance(lightRay.getpOrigin()));
        return intersections ==null;
    }

    /**
     * calculate the specular light according to phong model
     * @param ks coefficient from specular normally by 0<=ks<=1
     * @param l vector from light source to the point
     * @param n the normal to the point
     * @param v vector from camera to the point
     * @param nShininess the exponent
     * @param lightIntensity light Intensity
     * @return specular color of the point
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

           Vector r=l.substract(n.scale(alignZero(l.dotProduct(n)*2)));
          double minus_vr=alignZero(v.dotProduct(r)*-1);
        return lightIntensity.scale(ks*Math.pow(Math.max(0,minus_vr),nShininess));
    }

    /**
     * calculate the diffuse light according phong model
     * @param kd coefficient from diffuse normally by 0<=kd<=1
     * @param l  vector from light source to the point
     * @param n the normal to the point
     * @param lightIntensity light Intensity
     * @return color
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        return lightIntensity.scale(kd*Math.abs(alignZero(l.dotProduct(n))));}

    /**
     *
     * @param gp
     * @param v
     * @param level
     * @param k
     * @return
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kr;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kr, kkr);
        double kkt = k * material.kt;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kt, kkt));
        return color;
    }

    /**
     *
     * @param point
     * @param v
     * @param n
     * @return
     */
    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point,v);
    }

    /**
     *
     * @param point
     * @param v
     * @param n
     * @return
     */
    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        Vector r=v.substract(n.scale(2*v.dotProduct(n)));
        return new Ray(point,r);
    }


    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = ray.findClosestGeoPoint(_scene.geometries.findGeoIntersections(ray));
        return (gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }


}