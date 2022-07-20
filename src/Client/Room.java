/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Nguyen Van Toan
 */
public class Room extends javax.swing.JFrame {

    /**
     * Creates new form Room
     */
    private String name;
    private DataOutputStream dos_tcp;
    private DataInputStream dis_tcp;
    private Socket TCP_client;
    private Webcam webcam;
    private boolean video;
    private boolean sound;
    private String nameCam;
    private ArrayList<JLabel> labels;

    public Room(String name, String nameCam) {
        initComponents();
        labels = new ArrayList<>();
        labels.add(jLabel1);
        labels.add(jLabel2);
        labels.add(jLabel3);
        labels.add(jLabel4);
        this.name = name;
        this.nameCam = nameCam;
        //ket noi to server
        connect();
        video = true;
        sound = false;
        webcam = Webcam.getWebcamByName(nameCam);
        webcam.setViewSize(new Dimension(320, 240));
        //nhan tin nhan
        new TCP_receive_mess().start();
        new Send_UDP_Video().start();
        new Send_UDP_Voice().start();
        new Receive_UDP_Video().start();
        new Receive_UDP_Voice().start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        sendMess = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        open_closeCam = new javax.swing.JButton();
        on_offMic = new javax.swing.JButton();
        exit_client = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        sendMess.setText("Gửi");
        sendMess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendMess, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(sendMess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        open_closeCam.setText("Tắt Cam");
        open_closeCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open_closeCamActionPerformed(evt);
            }
        });

        on_offMic.setText("Bật Mic");
        on_offMic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                on_offMicActionPerformed(evt);
            }
        });

        exit_client.setText("Thoat");
        exit_client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_clientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(open_closeCam, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(on_offMic, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit_client, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(open_closeCam, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(on_offMic, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exit_client, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void connect() {
        try {
            TCP_client = new Socket("113.186.20.101", 3284);
            dos_tcp = new DataOutputStream(TCP_client.getOutputStream());
            dos_tcp.writeUTF(name);
        } catch (IOException ex) {
            System.out.println("err: " + ex);
        }
    }

    private void sendMessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessActionPerformed
        // TODO add your handling code here:
        //gui tin nhan
        String mess = jTextField1.getText();
        mess = "mess " + name + ": " + mess + "\n";
        jTextField1.setText("");
        TCP_send_mess(mess);
    }//GEN-LAST:event_sendMessActionPerformed

    private void exit_clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_clientActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            DataOutputStream dos = new DataOutputStream(TCP_client.getOutputStream());
            dos.writeUTF("exit");
            dos.writeUTF(name);
            //video= false;
            sleep(2000);
            dos.close();
            TCP_client.close();
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exit_clientActionPerformed

    private void open_closeCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_open_closeCamActionPerformed
        // TODO add your handling code here:

        if (!video) {
            video = !video;
            open_closeCam.setText("tắt cam");
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
            //webcam.close();
        } else {
            //webcam.open();
            open_closeCam.setText("bật cam");
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
            video = !video;
        }
    }//GEN-LAST:event_open_closeCamActionPerformed

    private void on_offMicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_on_offMicActionPerformed
        // TODO add your handling code here:
        sound = !sound;
        if (sound) {
            on_offMic.setText("tắt mic");
        } else {
            on_offMic.setText("bật mic");
        }
    }//GEN-LAST:event_on_offMicActionPerformed

    /**
     * @param args the command line arguments
     */
    public void TCP_send_mess(String mess) {
        try {
            dos_tcp = new DataOutputStream(TCP_client.getOutputStream());
            dos_tcp.writeUTF(mess);
        } catch (IOException ex) {
            System.out.println("err: " + ex);
        }
    }

    public class TCP_receive_mess extends Thread {

        @Override
        public void run() {
            try {
                dis_tcp = new DataInputStream(TCP_client.getInputStream());
                boolean f = true;
                while (f) {
                    String a = dis_tcp.readUTF();
                    String[] str = a.split("\\s", 2);
                    switch (str[0]) {
                        case "mess":
                            jTextArea1.append(str[1]);
                            jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
                            break;
                        case "OK":
                            f = false;
                            break;
                        case "NAME":
                            String[] names = str[1].split("[+]");
                            jLabel1.setText("");
                            jLabel2.setText("");
                            jLabel3.setText("");
                            jLabel4.setText("");
                            jLabel1.setIcon(null);
                            jLabel2.setIcon(null);
                            jLabel3.setIcon(null);
                            jLabel4.setIcon(null);
                            jLabel1.setText(names[0].split("[/]")[0]);
                            if (names.length >= 2) {
                                jLabel2.setText(names[1].split("[/]")[0]);
                            }
                            if (names.length >= 3) {
                                jLabel3.setText(names[2].split("[/]")[0]);
                            }
                            if (names.length == 4) {
                                jLabel4.setText(names[3].split("[/]")[0]);
                            }
                            break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public class Send_UDP_Video extends Thread {

        @Override
        public void run() {
            //webcam = Webcam.getWebcamByName("e2eSoft iVCam 1");
            //webcam= Webcam.getWebcamByName("USB2.0 VGA UVC WebCam 0");

            webcam.open();

            try {

                DatagramSocket clientSenderVideo = new DatagramSocket();
                InetAddress address = InetAddress.getByName("113.186.20.101");
                Data obj= new Data();
                
                while (true) {

                    obj = new Data(name, video, sound);
                    
                    if (video) {
                        BufferedImage bImage = webcam.getImage();
                        ByteArrayOutputStream dos = new ByteArrayOutputStream();
                        ImageIO.write(bImage, "jpg", dos);
//                dos.flush();      
                        byte[] img = dos.toByteArray();
                        dos.reset();
                        obj.setImg(img);
                        //jLabel3.setIcon(new ImageIcon(bImage));
                    }else   sleep(180);
                    
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    ObjectOutputStream os = new ObjectOutputStream(out);
                    os.writeObject(obj);
//                dos.flush();      
                    byte[] data = out.toByteArray();
                    out.reset();
                    DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, 2204);

                    clientSenderVideo.send(sendPacket);

                }
            } catch (IOException e) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, e);
            } catch (InterruptedException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public class Send_UDP_Voice extends Thread {

        @Override
        public void run() {
            
            

            try {

                DatagramSocket clientSenderVoice = new DatagramSocket();

                InetAddress address = InetAddress.getByName("113.186.20.101");
                Data obj= new Data();
                
                TargetDataLine line;
                AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100.0f,
                16, 2, 4, 44100.0f, false);
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                line = (TargetDataLine) AudioSystem.getLine(info);
                line.open(format);
                line.start();
                
                while (true) {

                    obj = new Data(name, video, sound);
                    System.out.println("start:"+new SimpleDateFormat("HH-mm-ss.SSS").format(Calendar.getInstance().getTime()));
                    if (sound) {
                        byte[] voice= new byte[4096];
                        line.read(voice, 0, voice.length);
                        obj.setVoice(voice);

                    }else sleep(1);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    ObjectOutputStream os = new ObjectOutputStream(out);
                    os.writeObject(obj);
//                dos.flush();      
                    byte[] data = out.toByteArray();
                    out.reset();
                    DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, 2206);

                    clientSenderVoice.send(sendPacket);

                }
            } catch (IOException e) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, e);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public class Receive_UDP_Video extends Thread {

        @Override
        public void run() {
            try {
                MulticastSocket clientSocket = new MulticastSocket(2205);
                InetAddress address = InetAddress.getByName("228.2.0.1");
                clientSocket.joinGroup(address);

                byte[] receiveData = new byte[35000];

                AudioFormat format;
                DataLine.Info dataLineInfo;
                SourceDataLine sourceDataLine;
                format = new AudioFormat(44100, 16, 2, true, false);
                dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(format);
                sourceDataLine.start();
                
                while (true) {
                    //nhan du lieu
                    DatagramPacket receiviPacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receiviPacket);
                    byte[] data = receiviPacket.getData();
                    //chuyen ByteArray -> Obj
                    ByteArrayInputStream in = new ByteArrayInputStream(data);
                    ObjectInputStream is = new ObjectInputStream(in);
                    Data obj= new Data();
                    try {
                        obj = (Data) is.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    new PlayCam(obj).start();
                }
            } catch (IOException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public class Receive_UDP_Voice extends Thread {

        @Override
        public void run() {
            try {
                MulticastSocket clientSocket = new MulticastSocket(2207);
                InetAddress address = InetAddress.getByName("228.2.0.1");
                clientSocket.joinGroup(address);

                byte[] receiveData = new byte[35000];

                AudioFormat format;
                DataLine.Info dataLineInfo;
                SourceDataLine sourceDataLine;
                format = new AudioFormat(44100, 16, 2, true, false);
                dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(format);
                sourceDataLine.start();
                
                while (true) {
                    //nhan du lieu
                    DatagramPacket receiviPacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receiviPacket);
                    byte[] data = receiviPacket.getData();
                    //chuyen ByteArray -> Obj
                    ByteArrayInputStream in = new ByteArrayInputStream(data);
                    ObjectInputStream is = new ObjectInputStream(in);
                    Data obj= new Data();
                    try {
                        obj = (Data) is.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(obj.isSound() && !name.equalsIgnoreCase(obj.getName())){
                        sourceDataLine.write(obj.getVoice(), 0, obj.getVoice().length);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public class PlayCam extends Thread {

        private Data obj;

        public PlayCam(Data obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            if (obj.isVideo()) {
                byte[] img = obj.getImg();
                ImageIcon imageIcon = new ImageIcon(img);
                for (JLabel i : labels) {
                    if (i.getText().equalsIgnoreCase(obj.getName())) {
                        i.setIcon(imageIcon);
                        break;
                    }
                }
            } else {
                for (JLabel i : labels) {
                    if (i.getText().equalsIgnoreCase(obj.getName())) {
                        i.setIcon(null);
                        break;
                    }
                }
            }
        }
    }

    public class PlayVoice extends Thread {

        private Data obj;

        public PlayVoice(Data obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            try {
                AudioFormat format;
                int sampleRate = 44100;

                DataLine.Info dataLineInfo;
                SourceDataLine sourceDataLine;
                format = new AudioFormat(sampleRate, 16, 2, true, false);
                dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(format);
                sourceDataLine.start();
                while (true) {
                    try {
                        byte soundbytes[] = obj.getVoice();
                        sourceDataLine.write(soundbytes, 0, soundbytes.length);
                    } catch (Exception e) {
                        System.out.println("Not working in speakers...");
                        e.printStackTrace();
                    }
                }
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /*
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
 /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
 /*java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Room("toan").setVisible(true);
            }
        });
    }*/


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit_client;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton on_offMic;
    private javax.swing.JButton open_closeCam;
    private javax.swing.JButton sendMess;
    // End of variables declaration//GEN-END:variables
}