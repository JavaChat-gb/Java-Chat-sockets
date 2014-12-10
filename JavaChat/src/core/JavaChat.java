/**
 * 
 */
package core;

import networking.MsgData;
import networking.OReciver;
import networking.OSender;
import networking.RecClient;
import networking.SeRe;
import Filter.Doubleizer;
import Filter.Filter;
import UI.ChatGui;

/**
 * @author Erik
 *
 */
public class JavaChat implements RecClient {
	private SeRe net;
	private ChatGui gui;
	private Filter z;
	public boolean connected = false;
	
	private OSender sendd = new OSender(9999);
	private OReciver reciv;
	
	public JavaChat() {
		z = new Doubleizer();
		gui = new ChatGui(this);
	}

	@Override
	public void addMSG(MsgData md) {
		z.setText(md.getMsg());
		MsgData dm = new MsgData(md.getSender(), z.performFilter());
		gui.addMsg(dm);
	}

	public static void main(String[] args) {
		new JavaChat();
	}
	
	@Override
	public void connect(String s, String a) {
		/**
		if(!s.equals("")){
			net = new SeRe(s, Integer.parseInt(a),this);
		}else{
			net = new SeRe(this);
		}
		*/
		 reciv= new OReciver(s, 9999,this);
		 reciv.start();
	}

	@Override
	public void write(MsgData a) {
		//net.send(a);
		sendd.run(a.getMsg());
	}
	
	public void enableFilter(Filter z){
		this.z = z;
	}
	
	
	public void disconnect(){
		reciv.stop=true;
		sendd.destroy();
	}
}
