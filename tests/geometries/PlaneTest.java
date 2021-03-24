package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    Point3D p1= new Point3D(1d,1d,2d);
    Point3D p2= new Point3D(4d,0d,2d);
    Point3D p3= new Point3D(0d,-2d,2d);
    @Test
    void getNormal() {
        try{
            Plane pl=new Plane(p1,p2,p3);
            assertEquals(new Vector(0d,0d,-1d), pl.getNormal(null),"ERROR: the actual normal is incorrect");}
        catch(Exception e){
          fail("ERROR: two points can not be similiar");
        }

            //missimng test three points in one line



    }



}