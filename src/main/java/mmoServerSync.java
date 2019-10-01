import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class mmoServerSync extends Thread{
    JFrame frame;
    CarDraw car;
    WorldStat worldStat;
    char face;
    public mmoServerSync(JFrame frame,CarDraw car,char face) {
        this.frame=frame;
        this.car=car;
        this.face=face;

    }
    private Socket socket            = null;
    private DataInputStream  dis   = null;
    private DataOutputStream dos     = null;

    @Override
    public void run() {
        super.run();
        worldStat=new WorldStat();

        System.out.println("Connectiong To server ..");
        // establish a connection
        try
        {
            socket = new Socket(Res.serverAddress, Res.serverPort);
            System.out.println("Connected");

            // takes input from terminal
            dis  = new DataInputStream(socket.getInputStream());

            // sends output to the socket
            dos    = new DataOutputStream(socket.getOutputStream());
        }

        catch(IOException i)
        {
            System.out.println(i);
        }
        // initialization of socet end

        //ID assigning procedure
        String id = "";
        try {
            id = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Assigned id for client is :"+id);
        Res.clientID=Integer.parseInt(id);

        worldStat.id.add(Res.clientID);
        //ID assigning procedure end






        while (true)
        {
            try
            {

                //download updated data
                System.out.println("downloading");
                ObjectInputStream ise=new ObjectInputStream(socket.getInputStream());
                worldStat=(WorldStat) ise.readObject();
                System.out.println("writing world");
                //download updated data
                System.out.println(worldStat.name.get(0)+"  aaaaaaaaa");

                worldStat.X.add(Res.clientID,car.getX());
                worldStat.Y.add(Res.clientID,car.getY());
                worldStat.face.add(Res.clientID,face);

                System.out.println("ploading stat");


                //upload data
                ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
                os.writeObject(worldStat);
                //upload data end
                System.out.println("upload stat done done");






                //example for sending data
//                line = dis .readLine();
//                dos.writeUTF(line);
            if(true){
                break;
            }


            }
            catch(IOException i)
            {
                System.out.println(i);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // close the connection
        try
        {
            dis.close();
            dos.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }




    }
}
