import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class World extends JFrame {

    CarDraw car;
    char face;

    World(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Res.windowWidth,Res.windowHeight);
        setLayout(null);
        setResizable(false);
        car=new CarDraw();
        face='n';//shows where is car facing  {L,R,U,D}

        add(car);
        //extra logic

        //JButton b=new JButton("hii");
        //b.setBounds(30,30,300,50);
        ///add(b);
        //Boundaries.addRistriction(b.getX(),b.getY(),b.getWidth(),b.getHeight());

        design();
        //extra logic end

        //spwan location
        car.setBounds(300,500,car.getWidth(),car.getHeight());



        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if(Boundaries.check(car))
                {
                    Boundaries.bounceBack(car, face);
                    return;
                }


                if(e.getKeyCode()==KeyEvent.VK_UP)
                    {

                        //up pressed
                        if(face != 'U'){
                            car.carFaceUp();
                            face='U';
                        }
                        carMoveUp();

                    }
                else if(e.getKeyCode()==KeyEvent.VK_DOWN)
                    {
                        if(face != 'D'){
                            car.carFaceDown();
                            face='D';
                        }

                        carMoveDown();
                        //down pressed
                    }
                else if(e.getKeyCode()==KeyEvent.VK_LEFT)
                    {
                        //left pressed
                        if(face != 'L'){
                            car.carFaceLeft();
                            face='L';

                        }
                        carMoveLeft();
                    }
                else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                    {
                        if(face != 'R'){
                            car.carFaceRight();
                            face='R';
                        }
                        //right pressed
                        carMoveRight();

                    }
            }
        });
        setFocusable(true);
        setVisible(true);
    }

    private void carMoveDown() {
        car.setBounds(car.getX(),car.getY()+Res.speedOfCar,car.getWidth(),car.getHeight());
    }

    private void carMoveRight() {
        car.setBounds(car.getX()+Res.speedOfCar,car.getY(),car.getWidth(),car.getHeight());
    }

    private void carMoveLeft() {

        car.setBounds(car.getX()-Res.speedOfCar,car.getY(),car.getWidth(),car.getHeight());
    }

    private void carMoveUp() {
        car.setBounds(car.getX(),car.getY()-Res.speedOfCar,car.getWidth(),car.getHeight());

    }

    public void design()
    {
        //here goes design

        JButton b=new JButton();
        b.setBounds(30,100,500,30);
        b.setEnabled(false);
        add(b);
        Boundaries.addRistriction(b.getX(),b.getY(),b.getWidth(),b.getHeight());

        JButton b1=new JButton();
        b1.setBounds(30,200,500,30);
        add(b1);
        b1.setEnabled(false);
        Boundaries.addRistriction(b1.getX(),b1.getY(),b1.getWidth(),b1.getHeight());

        JButton b2=new JButton();
        b2.setBounds(30,300,500,30);
        add(b2);
        b2.setEnabled(false);
        Boundaries.addRistriction(b2.getX(),b2.getY(),b2.getWidth(),b2.getHeight());

    }




}
