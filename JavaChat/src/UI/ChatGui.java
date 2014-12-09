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
import javax.swing.text.StyledEditorKit.BoldAction;

import core.JavaChat;
import networking.MsgData;
import networking.RecClient;

public class ChatGui extends JFrame {
	private JPanel connectSettings, sendBar, chatHist, connectbar;
	private JButton send, options, connectb;
	private JTextField sip, port, uname, smsg;
	private JTextArea chatLog;
	private AList acl = new AList();
	private RecClient mother;
	private boolean connected = false;

	public ChatGui(RecClient mother) {
		this.mother = mother;
		this.setSize(400, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.init();
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(this.getWidth(), 500);
	}

	public void init() {
		connectSettings = new JPanel();
		sendBar = new JPanel();
		chatHist = new JPanel();
		connectbar = new JPanel();

		send = new JButton(Messages.getString("ChatGui.0")); //$NON-NLS-1$
		send.addActionListener(acl);
		options = new JButton(Messages.getString("ChatGui.1")); //$NON-NLS-1$
		options.addActionListener(acl);
		connectb = new JButton(Messages.getString("ChatGui.2")); //$NON-NLS-1$
		connectb.addActionListener(acl);

		sip = new JTextField(15);
		sip.setText(Messages.getString("ChatGui.3")); //$NON-NLS-1$
		port = new JTextField(7);
		port.setText(Messages.getString("ChatGui.4")); //$NON-NLS-1$
		uname = new JTextField(15);
		uname.setText(Messages.getString("ChatGui.5")); //$NON-NLS-1$
		smsg = new JTextField(Messages.getString("ChatGui.6")); //$NON-NLS-1$

		chatLog = new JTextArea();
		chatLog.setEditable(false);
		chatHist.add(chatLog);

		connectSettings.setLayout(new FlowLayout(FlowLayout.CENTER));
		connectSettings.add(sip);
		connectSettings.add(port);
		connectSettings.add(uname);

		connectbar.setLayout(new BorderLayout());
		connectbar.add(connectSettings, Messages.getString("ChatGui.7")); //$NON-NLS-1$
		connectbar.add(connectb, Messages.getString("ChatGui.8")); //$NON-NLS-1$

		sendBar.setLayout(new BorderLayout());
		sendBar.add(options, Messages.getString("ChatGui.9")); //$NON-NLS-1$
		sendBar.add(smsg, Messages.getString("ChatGui.10")); //$NON-NLS-1$
		sendBar.add(send, Messages.getString("ChatGui.11")); //$NON-NLS-1$
		/**
		 * Put it all together
		 */
		this.setLayout(new BorderLayout());
		this.add(chatLog);
		this.add(sendBar, Messages.getString("ChatGui.12")); //$NON-NLS-1$
		this.add(connectbar, Messages.getString("ChatGui.13")); //$NON-NLS-1$

	}

	public void addMsg(MsgData m) {
		chatLog.setText(chatLog.getText() + System.lineSeparator()
				+ m.getSender() + " : " + m.getMsg());
	}

	/**
	 * public static void main(String[] args) { new ChatGui(null); }
	 **/
	public class AList implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == connectb) {
				if (!connected) {
					mother.connect(sip.getText(), port.getText());
					connectb.setText("Disconnect");
					connected = true;
				} else {
					mother.disconnect();
					connectb.setText("Connect");
					connected = false;
				}

			}
			if (e.getSource() == send) {
				mother.write(new MsgData(uname.getText(), smsg.getText()));
				smsg.setText("");
			}

		}

	}
}
