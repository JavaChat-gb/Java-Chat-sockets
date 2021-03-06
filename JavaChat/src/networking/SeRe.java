package networking;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author Erik
 *
 */
public class SeRe {
	private ServerSocket ss = null;
	private Socket csocket =null;
	private RecClient cAPP;
	public static final int port = 9898;

	/**
	 * Sets up a standard send-recive Connection
	 * 
	 * @param host
	 *            Chat-opponent's IP ex. "192.168.1.14"
	 * @param port
	 *            Port to connect to
	 * @param a
	 *            Messagehandler gets the messages delivered
	 */
	public SeRe(String host, int port, RecClient a) {
		this.cAPP = a;
		try {
			if(host.equals("")){

				ss = new ServerSocket(port);
					this.csocket = ss.accept();

			}else{
				csocket = new Socket(host, port);
				System.err.println(csocket.isConnected());
			}
		} catch (IOException e) {
			System.err.println("SocketERror  "+e.getMessage());
			e.printStackTrace();
		} /**finally {
			try {
				ss.close();
			} catch (IOException e) {
			}
		}**/
	}

	/**
	 * Sets up a standard send-recive Connection to port 9898 <br>
	 * Waits that someone connects

	 * @param a
	 *            Messagehandler gets the messages delivered
	 */
	public SeRe(RecClient a) {
		this("", port, a);
	}

	/**
	 * Recives data from the InputStream(Sockets) and packs it into a
	 * Data(Class) object. This object gets transported to the RecClient
	 * specified in the Constuctor.
	 * 
	 */
	public void recive() {

		try {
			InputStream is = csocket.getInputStream();
			String msgs = "", name = "", s = "";// msgs contains the message,
			// name contains the sender
			// name, s is a temp. var.
			for (int i; (i = is.read()) != -1; s += (char) i)
				// Liest zeichen vom Stream ein und fuegt diese dem string s an

				name = s.substring(0, s.indexOf(">|") + 1);
			name.replace("<", "");
			name.replace(">", "");
			msgs = s.substring(s.indexOf(">|") + 2);
			MsgData z = new MsgData(name, msgs);

			cAPP.addMSG(z);
			is.close();

		} catch (Exception e) {
			System.err.println("Recive errror");
			e.printStackTrace();
		}

	}

	public void send(MsgData md){
		try{
			OutputStream out = csocket.getOutputStream();
			byte[] b = new byte[md.getSender().length()+md.getMsg().length()+3];
			b[0]=(byte)'<';
			for(int i = 0; i< md.getSender().length() ;i++)
				b[i+1]=(byte)md.getSender().charAt(i);
			b[md.getSender().length()+1]=(byte)'>';
			b[md.getSender().length()+2]=(byte)'|';//bisschen Brainfuck aber funktioniert :P
			int tempx = md.getSender().length()+3;
			for(int i = 0; i< md.getMsg().length() ;i++){
				b[tempx+i]=(byte)md.getMsg().charAt(i);
			}
			out.write(b);
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	public void disc(){
		try{
		this.csocket.getOutputStream().close();
		this.csocket.getInputStream().close();
		this.csocket.close();
		}catch(Exception e){
			System.out.println("Error on closing socket");
			System.out.println("All Streams ended");
		}
	}

}
