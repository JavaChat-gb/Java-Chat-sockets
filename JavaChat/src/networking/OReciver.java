package networking;


import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
/**
 * Die Klasse dient um Object Typen zu empfangen
 * 
 * @Author Erik Legenstein
 * @version 1.1c 2012
 */
public class OReciver extends Thread {
    int port;
    String hostname;
    public boolean stop=false;
    
    RecClient zz;
    public OReciver(String hostname, int port, RecClient zz) {
        this.hostname = hostname;
        this.port = port;
        this.zz=zz;
    }
    /**
    public void start() {
        ObjectInputStream objectInputStream=null;
        Socket socket=null;
        try {
            socket = new Socket(hostname, port);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object receivedObject = null;
            do {
                receivedObject = objectInputStream.readObject();
                System.out.println(this + " received: " + receivedObject);
            } while (receivedObject != null);
        } catch (EOFException e) {
            try {
                objectInputStream.close();
                socket.close();
                System.out.println("Client closed.");
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    **/
    ///Object empfangen
    public Object rec(boolean hc) {
        ObjectInputStream objectInputStream=null;
        Socket socket=null;
        Object receivedObject = null;
        boolean gotmsg = false;
        try {
            socket = new Socket(hostname, port);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            do {
                receivedObject = objectInputStream.readObject();
                //System.out.println(this + " received: " + receivedObject);
            } while (receivedObject != null&&!gotmsg);

        } catch (EOFException e) {
            try {
                objectInputStream.close();
                socket.close();
                //System.out.println("Client closed.");
                return receivedObject;
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receivedObject;
    }
    
    @Override
    public void run() {
    	while(!stop){
    		zz.addMSG(new MsgData(hostname,(String)rec(true)));
    	}
    }
    
}