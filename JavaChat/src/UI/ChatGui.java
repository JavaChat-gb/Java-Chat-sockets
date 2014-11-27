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
		this.setSize(400, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.init();
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(this.getWidth(), 500);
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
		
		sip = new JTextField(15);
		sip.setText("IP");
		port = new JTextField(7);
		port.setText("Port");
		uname = new JTextField(15);
		uname.setText("Username");
		smsg = new JTextField("Nachricht");
		
		chatLog = new JTextArea();
		chatLog.setEditable(false);
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
		this.add(chatLog);
		this.add(sendBar,"South");
		this.add(connectbar,"North");
		
	}
	
	public void addMsg(String s){
		chatLog.setText(chatLog.getText()+System.lineSeparator()+s);
	}
	
	public static void main(String[] args) {
		new ChatGui();
	}
	public class AList implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
}
