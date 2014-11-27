package networking;
/**
 * 
 */

/**
 * Dataset for Messages it contains a sendername and a message
 * 
 * @author Erik
 *
 */
public class MsgData {

	private String sender;
	private String msg;

	/**
	 *
	 * @param sender
	 *            Sendername
	 * @param msg
	 *            Message
	 */
	public MsgData(String sender, String msg) {
		super();
		this.sender = sender;
		this.msg = msg;
	}

	/**
	 * @return the sendername
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @return the Message
	 */
	public String getMsg() {
		return msg;
	}

}
