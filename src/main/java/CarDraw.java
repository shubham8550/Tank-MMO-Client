import javax.swing.*;
import java.awt.*;

class CarDraw extends JPanel {

    private Image img;
    public String carID;

    public CarDraw() {
        this.img = new ImageIcon("Car.png").getImage();
        //Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }
    public void carFaceDown(){
        this.img = new ImageIcon("CarDown.png").getImage();

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);

        repaint();
    }
    public void carFaceUp(){
        this.img = new ImageIcon("Car.png").getImage();

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);

        repaint();
    }
    public void carFaceLeft(){
        this.img = new ImageIcon("CarLeft.png").getImage();

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);

        repaint();
    }
    public void carFaceRight(){
        this.img = new ImageIcon("CarRight.png").getImage();

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);

        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

}