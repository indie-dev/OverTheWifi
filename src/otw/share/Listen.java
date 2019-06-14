package otw.share;

import java.util.List;

import otw.share.client.Client;

public class Listen 
{
	public static void main(String[] args)
	{
		Client client = new Client();
		client.listen("localhost", ShareData.DEFAULT_PORT);
	}
}
