import javax.swing.*;
import java.util.ArrayList;

public class Boundaries {

    static ArrayList<Integer> X=new ArrayList<Integer>();
    static ArrayList<Integer> Y=new ArrayList<Integer>();
    static ArrayList<Integer> Height=new ArrayList<Integer>();
    static ArrayList<Integer> Width=new ArrayList<Integer>();
    public static boolean check(CarDraw car)
    {

        wallCheck(car);
        return areaRistrict(car);

        //System.out.println(Boundaries.X.size());

    }
    public static void addRistriction(int x,int y,int width,int height)
    {
        Boundaries.X.add(new Integer(x));
        Boundaries.Y.add(new Integer(y));
        Boundaries.Height.add(new Integer(height));
        Boundaries.Width.add(new Integer(width));
    }
    static boolean areaRistrict(CarDraw car)
    {

        int x1;
        int x2;
        int y1;
        int y2;


        for(int i=0; i < Boundaries.X.size() ; i++)
        {
            x1=Boundaries.X.get(i);
            x2=Boundaries.X.get(i)+Boundaries.Width.get(i);
            y1=Boundaries.Y.get(i);
            y2=Boundaries.Y.get(i)+Boundaries.Height.get(i);

            if( car.getX() >= x1 && car.getX() <= x2 && car.getY() >= y1 && car.getY() <= y2 )
            {
                return true;
            }
        }
        return false;

    }
    public static void bounceBack(CarDraw car, char face)
    {
        if(face=='L')
        {
            car.setBounds(car.getX()+Res.carBounceToGodOBJ,car.getY(),car.getWidth(),car.getHeight());
        }else if(face=='R')
        {
            car.setBounds(car.getX()-Res.carBounceToGodOBJ,car.getY(),car.getWidth(),car.getHeight());
        }else if(face=='U')
        {
            car.setBounds(car.getX(),car.getY()+Res.carBounceToGodOBJ,car.getWidth(),car.getHeight());
        }else if(face=='D')
        {
            car.setBounds(car.getX(),car.getY()-Res.carBounceToGodOBJ,car.getWidth(),car.getHeight());
        }
    }

    static void wallCheck(CarDraw car)
    {
        //check for left wall
        if(car.getX() < 0  )
        {
            car.setBounds(car.getX()+Res.carBounceToGodOBJ,car.getY(),car.getWidth(),car.getHeight());
           // System.out.println(car.getX());
            return;
        }

        //check for right wall
        if(car.getX() > (Res.windowWidth-car.getWidth())  )
        {
            car.setBounds(car.getX()-Res.carBounceToGodOBJ,car.getY(),car.getWidth(),car.getHeight());
            //System.out.println(car.getX());
            return;
        }

        //check for top wall
        if(car.getY() < 0  )
        {
            car.setBounds(car.getX(),car.getY()+Res.carBounceToGodOBJ,car.getWidth(),car.getHeight());
            //System.out.println(car.getY());
            return;
        }

        //check for bottum wall
        if(car.getY() > (Res.windowHeight-car.getHeight()-40)  )
        {
            car.setBounds(car.getX(),car.getY()-Res.carBounceToGodOBJ,car.getWidth(),car.getHeight());
            //System.out.println(car.getY());
            return;
        }


    }

}
