/**
 * 
 */
package core;

import networking.MsgData;
import networking.RecClient;
import networking.SeRe;
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

	public JavaChat() {
		
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
}
