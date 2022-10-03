package UI.AsteroidsApplication;

import java.util.Random;

import javafx.scene.shape.Polygon;


//to do: choose between 5-10 sided polygons (use switch statment for number of sides)
public class PolygonFactory {
    
    public Polygon createPolygon() {
        Random rand = new Random();
        double size = 10 + rand.nextInt(10);
        int numSides = 5 + rand.nextInt(5);

        Polygon polygon = new Polygon();

    switch (numSides) {
        case 5:
            double p1 = Math.cos(Math.PI/5);
            double p2 = Math.cos(2*Math.PI/5);
            double p3 = Math.sin(2*Math.PI/5);
            double p4 = Math.sin(4*Math.PI/5);

            polygon.getPoints().addAll(new Double[] {
                size, 0.0,
                size*p1, -size*p3,
                -size*p2, -size*p4,
                -size*p2, size*p4,
                size*p1, size*p3
        });
            break;
        case 6:
            double q1 = Math.cos(Math.PI/6);
            double q2 = Math.cos(Math.PI/3);
            double q3 = Math.sin(Math.PI/6);
            double q4 = Math.sin(Math.PI/3);

            polygon.getPoints().addAll(new Double[] {
                size, 0.0,
                size*q1, -size*q4,
                -size*q2, -size*q3,
                -size, 0.0,
                -size*q2, size*q3,
                size*q1, size*q4
            });
            break;
        case 7:
            double r1 = Math.cos(Math.PI/7);
            double r2 = Math.cos(2*Math.PI/7);
            double r3 = Math.cos(3*Math.PI/7);
            double r4 = Math.sin(Math.PI/7);
            double r5 = Math.sin(2*Math.PI/7);
            double r6 = Math.sin(3*Math.PI/7);

            polygon.getPoints().addAll(new Double[] {
                size, 0.0,
                size*r1, -size*r4,
                size*r3, -size*r6,
                -size*r2, -size*r5,
                -size*r3, size*r6,
                -size*r1, size*r4,
                size*r2, size*r5
            });
            break;
        case 8:
            double s1 = Math.cos(Math.PI/8);
            double s2 = Math.cos(Math.PI/4);
            double s3 = Math.cos(3*Math.PI/8);
            double s4 = Math.sin(Math.PI/8);
            double s5 = Math.sin(Math.PI/4);
            double s6 = Math.sin(3*Math.PI/8);

            polygon.getPoints().addAll(new Double[] {
                size, 0.0,
                size*s3, -size*s5,
                -size*s3, -size*s6,
                -size*s1, -size*s4,
                -size*s1, size*s5,
                -size*s3, size*s6,
                size*s2, size*s4,
                size*s3, size*s5
            });
            break;
        case 9:
            double t1 = Math.cos(Math.PI/9);
            double t2 = Math.cos(2*Math.PI/9);
            double t3 = Math.cos(Math.PI/3);
            double t4 = Math.cos(4*Math.PI/9);
            double t5 = Math.sin(Math.PI/9);
            double t6 = Math.sin(2*Math.PI/9);
            double t7 = Math.sin(Math.PI/3);
            double t8 = Math.sin(4*Math.PI/9);

            polygon.getPoints().addAll(new Double[]{ 
                size, 0.0,
                size*t1, -size*t5,
                size*t3, -size*t7,
                -size*t4, -size*t7,
                -size*t2, -size*t5,
                -size*t1, size*t6,
                -size*t4, size*t8,
                size*t3, size*t7,
                size*t2, size*t6
            });
            break;
        case 10:
            double u1 = Math.cos(Math.PI/10);
            double u2 = Math.cos(Math.PI/5);
            double u3 = Math.cos(3*Math.PI/10);
            double u4 = Math.cos(2*Math.PI/5);
            double u5 = Math.sin(Math.PI/10);
            double u6 = Math.sin(Math.PI/5);
            double u7 = Math.sin(3*Math.PI/10);
            double u8 = Math.sin(2*Math.PI/5);
            
            polygon.getPoints().addAll(new Double[] {
                size, 0.0,
                size*u2, -size*u5,
                size*u3, -size*u7,
                -size*u4, -size*u7,
                -size*u1, -size*u6,
                -size, 0.0,
                -size*u2, size*u6,
                -size*u4, size*u8,
                size*u4, size*u6,
                size*u2, size*u5
            });
            break;
    }
    

        for (int i = 0; i < polygon.getPoints().size(); i++) {
            int change = rand.nextInt(5) - 2;
            polygon.getPoints().set(i, polygon.getPoints().get(i) + change);
        }

        return polygon;
    }
}
