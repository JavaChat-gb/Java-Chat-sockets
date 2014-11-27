package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGui extends JFrame {
	private JPanel connectSettings, sendBar, chatHist, connectbar;
	private JButton send, options, connectb;
	private JTextField sip, port, uname, smsg;
	private JTextArea chatLog;
	private AList acl= new AList();
	
	public ChatGui() {
		this.setSize(300, 500);
		this.init();
	}
	public void init(){
		connectSettings = new JPanel();
		sendBar =new JPanel();
		chatHist = new JPanel();
		connectbar = new JPanel();
		
		send = new JButton("Send");
		send.addActionListener(acl);
		options = new JButton("Options");
		options.addActionListener(acl);
		connectb = new JButton("Connect");
		connectb.addActionListener(acl);
		
		sip = new JTextField("IP");
		port = new JTextField("Port");
		uname = new JTextField("Username");
		smsg = new JTextField("Nachricht");
		
		chatLog = new JTextArea();
		chatHist.add(chatLog);
		
		connectSettings.setLayout(new FlowLayout(FlowLayout.CENTER));
		connectSettings.add(sip);
		connectSettings.add(port);
		connectSettings.add(uname);
		
		connectbar.setLayout(new BorderLayout());
		connectbar.add(connectSettings,"Center");
		connectbar.add(connectb,"South");
		
		sendBar.setLayout(new BorderLayout());
		sendBar.add(options,"West");
		sendBar.add(smsg,"Center");
		sendBar.add(send,"East");
		/**
		 * Put it all together
		 */
		this.setLayout(new BorderLayout());
		this.add(chatHist);
		this.add(sendBar,"South");
		this.add(connectbar,"North");
		
	}
	
	
	
	
	public class AList implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
}
