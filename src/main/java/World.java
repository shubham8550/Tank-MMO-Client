import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class World extends JFrame {

    CarDraw car;
    char face;
    World(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setLayout(null);
        car=new CarDraw();
        face='R';//shows where is car facing  {L,R,U,D}

        add(car);


        car.setBounds(300,500,car.getWidth(),car.getHeight());





        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
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

        setVisible(true);
    }

    private void carMoveDown() {
        car.setBounds(car.getX(),car.getY()+2,car.getWidth(),car.getHeight());
    }

    private void carMoveRight() {
        car.setBounds(car.getX()+2,car.getY(),car.getWidth(),car.getHeight());
    }

    private void carMoveLeft() {

        car.setBounds(car.getX()-2,car.getY(),car.getWidth(),car.getHeight());
    }

    private void carMoveUp() {
        car.setBounds(car.getX(),car.getY()-2,car.getWidth(),car.getHeight());

    }

}
