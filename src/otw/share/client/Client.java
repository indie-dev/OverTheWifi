package otw.share.client;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import otw.share.ShareData;
import otw.share.ShareData.ShareDataType;

public class Client 
{
	//NOTICE: All methods that automate listening methods are EXPERIMENTAL.
	//This is because the socket server keeps closing on every connection.
	public Client()
	{
		//Automatically create an on connect listener
		this.setOnClientConnectedToServerListener(new OnClientConnectedToServerListener() {
			
			@Override
			public void onFail(Throwable throwable) {
				// TODO Auto-generated method stub
				//Log the cause of failure and message
				print(throwable.getCause());
				print(throwable.getMessage());
			}
			
			@Override
			public void onDataRecieved(ShareData shareData) {
				// TODO Auto-generated method stub
				//Log the data that was recieved
				print(shareData.getShareData().toString());
			}
			
			@Override
			public void onConnect(Socket socket) {
				// TODO Auto-generated method stub
				//Notify that the client connected successfully to the server
				print("Client successfully connected to server");
			}
		});
	}
	
	private OnClientConnectedToServerListener onClientConnectedToServerListener;
	private List<String> ipAddressesOnWifi = new ArrayList<>();
	private List<ShareData> shareDataArray = new ArrayList<>();
	public void setOnClientConnectedToServerListener(OnClientConnectedToServerListener onClientConnectedToServerListener)
	{
		this.onClientConnectedToServerListener = onClientConnectedToServerListener;
	}
	
	public ShareData listen(String host, int port)
	{
		try
		{
			//Create a connection to the given host and port
			Socket socket = new Socket(host, port);
			//Check if the socket is not null
			if(socket != null)
			{
				//Notify the sdk that there was a successful connection
				this.onClientConnectedToServerListener.onConnect(socket);
				//Rip the ShareData from the ObjectInputStream given from the socket
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				//Tear out the object being sent
				ShareData shareData = (ShareData) objectInputStream.readObject();
				//Notify the sdk that the data was recieved
				this.onClientConnectedToServerListener.onDataRecieved(shareData);
				//Close the socket
				socket.close();
				//Return the shareData
				return shareData;
			}
		}catch(IOException ioe)
		{
			//Fucking hell, what went wrong? Well, the sdk will find out
			this.onClientConnectedToServerListener.onFail(ioe);
		} catch (ClassNotFoundException e) {
			//Why i was forced to create this clause i will never know, ORACLE!
			//Notify the sdk of this silly error
			this.onClientConnectedToServerListener.onFail(e);
		}
		
		//Return null, it explains itself... Come on, people!
		return null;
	}
	
	public void listenForAllDataTypes(String host, int port)
	{
		this.listenAndCopy(host, port);
		this.listenOnAllAndCopy(port);
	}
	
	public void listenForAllDataTypes(int port)
	{
		//Experimental, for SDK-based automation
		//Experimental because the socket server keeps on closing early
		//Until that is patched, this will stay experimental
		this.listenAndCopy("localhost", port);
		this.listenOnAllAndCopy(port);
	}
	
	public List<String> listAllConnectedHosts(String subnet, int maxIP, int timeout)
	{
		//Loop through maxIP
		for( int i = 1; i < maxIP; i++)
		{
			//Set the host with the subnet
			String host = subnet + "." + i;
			try {
				//Check if the ip address is reachable
				if(InetAddress.getByName(host).isReachable(timeout))
				{
					//If it is, add the host to ipAddressesOnWifi
					this.ipAddressesOnWifi.add(host);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Return ipAddressesOnWifi
		return this.ipAddressesOnWifi;
	}
	
	public List<String> listAllConnectedHosts(int maxIP, int timeout)
	{
		return this.listAllConnectedHosts("192.168.0", maxIP, timeout);
	}
	
	public List<String> listAllConnectedHosts(int maxIP)
	{
		return this.listAllConnectedHosts(maxIP, 1000);
	}
	
	public List<String> listAllConnectedHosts()
	{
		return this.listAllConnectedHosts(100);
	}
	
	
	public List<ShareData> listenOnAllConnectedDevices(String subnet, int maxIP, int timeout, int port)
	{
		//Loop through maxIP
		for( int i = 1; i < maxIP; i++)
		{
			//Set the host with the subnet
			String host = subnet + "." + i;
			try {
				//Check if the ip address is reachable
				if(InetAddress.getByName(host).isReachable(timeout))
				{
					ShareData data = listen(host, port);
					shareDataArray.add(data);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Return the share data array
		return this.shareDataArray;
	}
	
	public List<ShareData> listenOnAllConnectedDevices(int maxIP, int timeout, int port)
	{
		return this.listenOnAllConnectedDevices("192.168.0", maxIP, timeout, port);
	}
	
	public List<ShareData> listenOnAllConnectedDevices(int maxIP, int port)
	{
		return this.listenOnAllConnectedDevices(maxIP, 100, port);
	}
	
	public List<ShareData> listenOnAllConnectedDevices(int port)
	{
		return this.listenOnAllConnectedDevices(100, port);
	}
	
	public void copySharedText(ShareData data)
	{
		if(data.getShareDataType().equals(ShareDataType.CLIPBOARD_DATA))
		{
			//Copy the shared text
			//Might not work on mobile
			StringSelection selection = new StringSelection(data.getShareData().toString());
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, null);
		}
	}
	
	public void listenAndCopy(String host, int port)
	{
		ShareData shareData = listen(host, port);
		this.copySharedText(shareData);
	}
	
	public void listenOnAllAndCopy(int port)
	{
		List<ShareData> shareDataList = this.listenOnAllConnectedDevices(port);
		String textToCopy = new String();
		for(ShareData data : shareDataList)
		{
			if(data.getShareDataType().equals(ShareDataType.CLIPBOARD_DATA))
			{
				textToCopy += data.getShareData().toString();
			}
		}
		
		StringSelection selection = new StringSelection(textToCopy);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);
	}
}
