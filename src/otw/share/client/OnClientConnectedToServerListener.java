package otw.share.client;

import java.net.Socket;

import otw.share.ShareData;

public interface OnClientConnectedToServerListener 
{
	public void onConnect(Socket socket);
	public void onDataRecieved(ShareData shareData);
	public void onFail(Throwable throwable);
	public default void print(Object object)
	{
		System.out.println(object);
	}
}
