import javax.swing.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.*;
import java.util.ArrayList;


public class mmoServerSync extends Thread{
    DatagramSocket socket = null;
    JFrame frame;
    CarDraw car;
    WorldStat worldStat;

    String PlayerID;
    //char face;
    public mmoServerSync(JFrame frame,CarDraw car) {
        this.frame=frame;
        this.car=car;

    }


    @Override
    public void run() {

        WorldStat temp=null;
        PlayerID=generateID();
        byte[] incomingData;
        try {
            while (true) {
                socket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName("localhost");
                incomingData = new byte[100000];
                temp = new WorldStat();

                temp.id.add(PlayerID);
                temp.name.add("Subham");
                temp.face.add(Res.face);
                temp.carModle.add("Default");
                temp.X.add(car.getX());
                temp.Y.add(car.getY());

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(outputStream);
                os.writeObject(temp);
                byte[] data = outputStream.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, Res.serverPort);
                socket.send(sendPacket);
                System.out.println("Message sent from client");


                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);


                byte[] data1 = incomingPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data1);
                ObjectInputStream is = new ObjectInputStream(in);
                try {
                    worldStat = (WorldStat) is.readObject();
                    updateplayers();
                    System.out.println("Worldstat object received = " + worldStat);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                outputStream.flush();
                os.flush();
                data=null;
                data1=null;

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    String generateID() {

        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int count = 10;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    ArrayList<CarDraw> cars=new ArrayList<CarDraw>();
    void updateplayers(){
        try {
            if(cars.size()!=0){
                for(int i = 0 ; i < cars.size();i++){
                    cars.get(i).setVisible(false);

                }
                cars.removeAll(cars);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        int j=0;
        for(int i=0 ;i< worldStat.id.size(); i++){
            if (!worldStat.id.get(i).equals(PlayerID)){

                cars.add(new CarDraw());
                cars.get(j).carID=worldStat.id.get(i);
                setCarFace(cars.get(j),worldStat.face.get(i));
                cars.get(j).setBounds(worldStat.X.get(i),worldStat.Y.get(i),cars.get(j).getWidth(),cars.get(j).getHeight());
                frame.add(cars.get(j));

                j++;
            }
        }
        frame.repaint();
    }


    void setCarFace(CarDraw ca,char fa){
        if(fa=='U'){
            ca.carFaceUp();
        }else if (fa=='D'){
            ca.carFaceDown();
        }else if (fa=='L'){
            ca.carFaceLeft();
        }else if (fa=='R'){
            ca.carFaceRight();
        }

    }
}
