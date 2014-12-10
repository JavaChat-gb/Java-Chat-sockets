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

import Filter.Boese;
import Filter.Doubleizer;
import Filter.Filter;
import core.JavaChat;
import networking.MsgData;
import networking.RecClient;

public class ChatGui extends JFrame {
	private JPanel connectSettings, sendBar, chatHist, connectbar;
	private JButton send, options, connectb;
	private JTextField sip, port, uname, smsg;
	private JTextArea chatLog;
	private AList acl = new AList(this);
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
				+ m.getSender() + Messages.getString("ChatGui.14") + m.getMsg()); //$NON-NLS-1$
	}

	/**
	 * public static void main(String[] args) { new ChatGui(null); }
	 **/
	public class AList implements ActionListener {
		ChatGui mom;
		public AList(ChatGui m) {
			mom = m;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == connectb) {
				if (!connected) {
					mother.connect(sip.getText(), port.getText());
					connectb.setText(Messages.getString("ChatGui.15")); //$NON-NLS-1$
					connected = true;
				} else {
					mother.disconnect();
					connectb.setText(Messages.getString("ChatGui.16")); //$NON-NLS-1$
					connected = false;
				}

			}
			if (e.getSource() == send) {
				mother.write(new MsgData(uname.getText(), smsg.getText()));
				smsg.setText(Messages.getString("ChatGui.17")); //$NON-NLS-1$
			}
			if(e.getSource()==options){
				new JOptions(mom);
			}

		}

	}
	public class JOptions extends JFrame implements ActionListener{
		private JPanel cent;
		private JButton[] butts = new JButton[3];
		private boolean[] options=new boolean[]{false, false}; 
		ChatGui back;
		Filter f;
		public JOptions(ChatGui back) {
			this.back = back;
			this.setSize(300, 300);
			cent = new JPanel(new BorderLayout());
			butts[0]=new JButton("Doubleizer: off");
			butts[0].addActionListener(this);
			butts[1]=new JButton("boese: off");
			butts[1].addActionListener(this);
			butts[2]=new JButton("Exit");
			butts[2].addActionListener(this);
			cent.add(butts[0], BorderLayout.NORTH);
			cent.add(butts[1], BorderLayout.CENTER);
			cent.add(butts[2],"South");
			this.add(cent);
			this.setVisible(true);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==butts[0]){
				butts[0].setText("Doubleizer: on");
				options[0]=true;
			}
			if(e.getSource()==butts[1]){
				butts[1].setText("Boese: on");
				options[1]=true;
			}
			if(e.getSource()==butts[2]){
				if(options[1]){
					f =new Boese();
					if(options[0]){
						f.setNextf(new Doubleizer());
					}
				}else if (options[0]) {
					f = new Doubleizer();
				}
				mother.enableFilter(f);
				this.setVisible(false);
				this.dispose();
			}
		}
	}
}
