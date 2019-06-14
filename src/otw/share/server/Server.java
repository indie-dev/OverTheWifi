package otw.share.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import otw.share.ShareData;
import otw.share.ShareData.ShareDataType;
import otw.share.client.Client;

public class Server 
{
	
	public Server()
	{
		//Code explains its self
		this.setOnConnectedToServerListener(new OnConnectedToServerListener() {
			
			@Override
			public void onFail(Throwable throwable) {
				// TODO Auto-generated method stub
				//Print out the cause and the message of the failure
				print(throwable.getCause());
				print(throwable.getMessage());
			}
			
			@Override
			public void onDataSent(ShareData shareData) {
				// TODO Auto-generated method stub
				//Print out that the data was sent
				print("Data sent!");
			}
			
			@Override
			public void onConnect(Socket socket) {
				// TODO Auto-generated method stub
				//Print out that a server was created
				print("Server created!");
			}
		});
	}
	
	//OnConnectedToServerListener for client to server connection
	private OnConnectedToServerListener onConnectedToServerListener;
	//Just set that interface using setOnConnectedToServerListener
	public void setOnConnectedToServerListener(OnConnectedToServerListener onConnectedToServerListener)
	{
		this.onConnectedToServerListener = onConnectedToServerListener;
	}
	
	//Send the data by hosting it
	public void sendData(ShareData shareData, int port)
	{
		try
		{
			//Use ServerSocket to create a temporary server
			ServerSocket serverSocket = new ServerSocket(port);
			//Accept all connections to this server
			Socket socket = serverSocket.accept();
			if(socket != null)
			{
				//Notify the sdk that the server was created
				this.onConnectedToServerListener.onConnect(socket);
				//Output the ShareData class to the client connected
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectOutputStream.writeObject(shareData);
				objectOutputStream.flush();
				objectOutputStream.close();
				//Notify the sdk that the data was sent successfully
				this.onConnectedToServerListener.onDataSent(shareData);
				//Close the server socket
				serverSocket.close();
				//Close the socket
				socket.close();
			}else
			{
				//Notify the sdk that server creation was a bust
				this.onConnectedToServerListener.onFail(new Throwable("Null socket"));
			}
			
		}catch(IOException ioe)
		{
			//ioe.printStackTrace();
			//Notify the sdk of a critical error
			this.onConnectedToServerListener.onFail(ioe);
		}
	}
	
	public void sendDataAsHTML(ShareData shareData, int port)
	{
		//This is for browser-based testing, do not actually use this
		try
		{
			//Create a ServerSocket using the given port
			ServerSocket serverSocket = new ServerSocket(port);
			//Accept the incoming connection
			Socket socket = serverSocket.accept();
			//Check if the socket was not null
			if(socket != null)
			{
				//Since the socket is not null, notify the sdk of a creation success
				this.onConnectedToServerListener.onConnect(socket);
				//Print out the html to the server
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				//Send an html response
				writer.println("HTTP/1.1 200 OK");
				writer.println("Content-Type: text/html");
				writer.println("\r\n");
				//Print out the ShareData
				writer.write(shareData.getShareData().toString());
				writer.flush();
				writer.close();
				//Notify the sdk that the data was sent
				this.onConnectedToServerListener.onDataSent(shareData);
				socket.close();
			}else
			{
				//Uh-oh! What went wrong? The sdk will find out
				this.onConnectedToServerListener.onFail(new Throwable("Null socket"));
			}
		}catch(IOException ioe)
		{
			//Notify the sdk of a critical error X(
			this.onConnectedToServerListener.onFail(ioe);
		}
	}
}
