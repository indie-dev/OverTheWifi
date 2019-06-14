package otw.share.server;

import java.net.Socket;

import otw.share.ShareData;

public interface OnConnectedToServerListener 
{
	public void onConnect(Socket socket);
	public void onDataSent(ShareData shareData);
	public void onFail(Throwable throwable);
	public default void print(Object object)
	{
		System.out.println(object);
	}
}
