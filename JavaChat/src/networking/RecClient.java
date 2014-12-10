package networking;

import Filter.Filter;

public interface RecClient {
	public void addMSG(MsgData z);
	public void connect(String s,String a);
	public void disconnect();
	public void write(MsgData a);
	public void enableFilter(Filter z);
}
