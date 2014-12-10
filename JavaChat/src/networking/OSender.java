package networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Kann theoretisch ganze objekt versenden wird jedoch nur für strings verwendet
 * 
 * @Author Erik Legenstein
 * @Version 1.01b 2012
 */
public class OSender {
 
        ServerSocket serverSocket;
        String msg;
        ObjectOutputStream objectOutputStream;
        Socket socket;
        
        public OSender(int port) {
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
        public void run(String bcc) {
            try {
 
                socket = serverSocket.accept();
 
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ///for (int i = 0; i < 10; i++) {
                    ///System.out.println(this + " sending: " + bcc);
                    objectOutputStream.writeObject(bcc);
                    objectOutputStream.flush();
                    Thread.sleep(1000L);
                ///}
                objectOutputStream.close();
                ///System.out.println("Server closed.");
 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void destroy(){
        	try {
				socket.close();
			} catch (Exception e) {
				//shit happens
			}
        }
        /**
        public void run() {
            try {
                
                
                
                Socket socket = serverSocket.accept();
 
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                        socket.getOutputStream());
                for (int i = 0; i < 10; i++) {
                    Date date = new Date();
                    ///System.out.println(this + " sending: " + date);
                    ///objectOutputStream.writeObject("HALLO");
                    objectOutputStream.flush();
                    Thread.sleep(1000L);
                }
                objectOutputStream.close();
                socket.close();
                ///System.out.println("Server closed.");
 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        */
    }