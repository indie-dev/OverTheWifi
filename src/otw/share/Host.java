package otw.share;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import otw.share.ShareData.ShareDataType;
import otw.share.client.Client;
import otw.share.client.OnClientConnectedToServerListener;
import otw.share.server.OnConnectedToServerListener;
import otw.share.server.Server;

public class Host {

	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		Server server = new Server();
		Runnable runnable = new Runnable()
				{

					@Override
					public void run() {
						// TODO Auto-generated method stub
						ShareData shareData = ShareData.setShareData("Test text", ShareDataType.CLIPBOARD_DATA);
						server.sendData(shareData, ShareData.DEFAULT_PORT);
					}
			
				};
		runnable.run();
	}
}
