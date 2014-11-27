import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import javax.xml.bind.DataBindingException;

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

	public SeRe(String host, RecClient a) {
		this(host, port, a);
	}

	private void recive() {

		try{
		InputStream is = csocket.getInputStream();
		String msgs="", name="", s = "";
		for(int i ; (i = is.read())!=-1  ;s+=(char)i )
		
	
		name = s.substring(0, s.indexOf(">|") + 1);
		name.replace("<", "");
		name.replace(">", "");
		msgs = s.substring(s.indexOf(">|") + 2);
		MsgData z = new MsgData(name, msgs);

		cAPP.addMSG(z);
		is.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
