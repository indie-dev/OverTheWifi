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
		ShareData shareData = ShareData.setShareData("Da earf is mf flat", ShareDataType.OTHER);
		server.sendData(shareData, ShareData.DEFAULT_PORT);
	}
}
