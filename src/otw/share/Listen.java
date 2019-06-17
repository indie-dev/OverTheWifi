package otw.share;

import java.net.UnknownHostException;
import java.util.List;

import otw.share.client.Client;

public class Listen {

	public static void main(String[] args) throws UnknownHostException 
	{
		Client client = new Client(SharedData.DEFAULT_PORT);
		client.downloadFromServer("localhost");
	}

}
