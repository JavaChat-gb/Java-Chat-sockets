import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 */

/**
 * @author Erik
 *
 */
public class SeRe {
	private ServerSocket ss = null;
	private Socket csocket;
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
			ss = new ServerSocket(port);
		} catch (IOException e) {

		} finally {
			try {
				ss.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Sets up a standard send-recive Connection to port 9898
	 * 
	 * @param host
	 *            Chat-opponent's IP ex. "192.168.1.14"
	 * @param a
	 *            Messagehandler gets the messages delivered
	 */
	public SeRe(String host, RecClient a) {
		this(host, port, a);
	}

	/**
	 * Recives data from the InputStream(Sockets) and packs it into a
	 * Data(Class) object. This object gets transported to the RecClient
	 * specified in the Constuctor.
	 * 
	 * @category Send-Recive
	 */
	private void recive() {

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
			e.printStackTrace();
		}

	}

}
