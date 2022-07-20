package Server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chien
 */
public class ServerReviece {

    public ServerReviece() {
        new TCP_listen().start();
        new Receive(2204, 2205).start();
        new Receive(2206, 2207).start();
        
    }

    public static void main(String[] args) {
        new ServerReviece();
    }

    //danh sách socket
    private ArrayList<Socket> listSk;
    
    //danh sách tên người tham gia cuộc họp
    private String names;
    //server nghe các kết nối TCP
    public class TCP_listen extends Thread{
        ServerSocket server;
        @Override
        public void run(){
            listSk= new ArrayList<>();
            names="NAME ";
            try {
                server= new ServerSocket(3284);
                while (true) {
                    Socket socket = server.accept();
                    DataInputStream dis= new DataInputStream(socket.getInputStream());
                    names= names + dis.readUTF()+socket.getInetAddress()+"+";
                    System.out.println(names);
                    listSk.add(socket);
                    for(Socket i: listSk){
                        DataOutputStream dos= new DataOutputStream(i.getOutputStream());
                        dos.writeUTF(names);
                    }
                    //sendConn();
                    new Server_mess(socket).start();
                }
            } catch (IOException ex) {
                System.out.println("err: "+ex);
            }
        }
        
    }
    public class Server_mess extends Thread{
        Socket socket;

        public Server_mess(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run(){
            DataInputStream dis= null;
            try {
                dis= new DataInputStream(socket.getInputStream());
                boolean f= true;
                while (f) {
                    String sms = dis.readUTF();
                    if (sms.equalsIgnoreCase("exit")) {
                        listSk.remove(socket);
                        DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
                        String name= dis.readUTF();
                        sms= "mess "+name+": da thoat\n";
                        name= name+socket.getInetAddress()+"[+]";
                        dos.writeUTF("OK bye");
                        dis.close();
                        socket.close();
                        names= names.replaceAll(name, "");
                        for (Socket i : listSk) {
                            DataOutputStream dos1 = new DataOutputStream(i.getOutputStream());
                            dos1.writeUTF(names);
                        }
                        System.out.println(names);
                        f= false;
                    }
                    for (Socket item : listSk) {
                        DataOutputStream dos = new DataOutputStream(item.getOutputStream());
                        dos.writeUTF(sms);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerReviece.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}

class Receive extends Thread{
    protected int portReceive;
    protected int portSend;

    public Receive(int portReceive, int portSend) {
        this.portReceive = portReceive;
        this.portSend = portSend;
    }
    
    @Override
    public void run(){
        DatagramSocket receiveSocket= null;
        try {
            receiveSocket = new DatagramSocket(portReceive); 
        } catch (SocketException ex) {
            Logger.getLogger(ServerReviece.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
            
            
            while (true) {
                byte[] revieceData = new byte[35000];
                DatagramPacket recePacket = new DatagramPacket(revieceData,revieceData.length);
                receiveSocket.receive(recePacket);
                new Send(revieceData, recePacket, portSend).start();
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServerReviece.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerReviece.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

//gửi gói tin đến groupAddress
class Send extends Thread {

    protected byte[] data;
    protected DatagramPacket recePacket;
    protected DatagramSocket sendSocket;
    protected int port;

    public Send(byte[] data, DatagramPacket recePacket, int port) throws SocketException {
        this.data = data;
        this.recePacket = recePacket;
        this.sendSocket = new DatagramSocket();
        this.port = port;
    }

    @Override

    public void run() {
        try {
            data = recePacket.getData();

//                ImageIcon imgIcon = new ImageIcon(data);
            InetAddress groupAddress = InetAddress.getByName("228.2.0.1");
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, groupAddress, port);
            sendSocket.send(sendPacket);
//                label.setIcon(imgIcon);
            //System.out.println(data.toString());
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerReviece.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            System.out.println("err: " + e);
        }
    }

}
