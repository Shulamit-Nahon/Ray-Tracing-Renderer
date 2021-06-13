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

/**
 * class for RayTracer .
 *
 * @author shulamit nahon
 * @author le haimovich
 */
public class RayTracerBasic extends RayTracerBase {


    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;//Recursion depth
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * Construct a new RayTracerBasic for the given scene.
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Trace the given ray to calculate the {@link Color} that ray results in.
     *
     * @param ray The ray to trace in the scene.
     * @return The {@link Color} resulting from the trace of the ray.
     */
    @Override
    public Color traceRay(Ray ray) {
        //List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(ray);
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
    /**
     * find the color of a given intersection point
     * @param geopoint the point on the object
     * @param ray the ray intersecting with the point
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    /**
     *
     * @param intersection
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     *
     * @param geopoint
     * @param inRay
     * @param k
     * @return
     */
    private Color calcLocalEffects(GeoPoint geopoint, Ray inRay, double k) {
        Color color = geopoint.geometry.getEmission();

        Vector v = inRay.getDirection();
        Vector n = geopoint.geometry.getNormal(geopoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            return Color.BLACK;
        }

        Material material = geopoint.geometry.getMaterial();
        int nShininess = material.Shininess;

        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(geopoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                double ktr = transparency(lightSource, l, n, geopoint);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(ktr);
                    color = color.add(calcDiffusive(material.diffuse, l, n, lightIntensity),
                            calcSpecular(material.specular, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

//    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
//
//        Vector v = ray.getDirection();
//        Vector n = intersection.geometry.getNormal(intersection.point);
//        double nv = alignZero(n.dotProduct(v));
//        if (nv == 0) return Color.BLACK;
//        Material material = intersection.geometry.getMaterial();
//        int nShininess = material.Shininess;
//        double kd = material.diffuse;
//        double ks = material.specular;
//        Color color = Color.BLACK;
//        for (LightSource lightSource : _scene.lights) {
//            Vector l = lightSource.getL(intersection.point);
//            double nl = alignZero(n.dotProduct(l));
//            if (nl * nv > 0) { // sign(nl) == sing(nv)
//                if (transparency(lightSource,l,n, intersection)) {
//                    Color lightIntensity = lightSource.getIntensity(intersection.point);
//                    color = color
//                            .add(calcDiffusive(kd, l, n, lightIntensity),
//                                    calcSpecular(ks, l, n, v, nShininess, lightIntensity));
//                }
//            }
//        }
//        return color;
//    }

    /**
     * Checks if the body is hidden from the light by another body
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = _scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().kt;
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
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
    private Color calcGlobalEffects(GeoPoint gp, Ray v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kr;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v.getDirection(), n), level, material.kr, kkr);
        double kkt = k * material.kt;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v.getDirection(), n), level, material.kt, kkt));
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
        return new Ray(point,v,n);
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
        return new Ray(point,r,n);
    }

    /**
     *
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = ray.findClosestGeoPoint(_scene.geometries.findGeoIntersections(ray));
        return (gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }
}