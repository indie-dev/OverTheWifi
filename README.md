# OverTheWiFi

What is OverTheWifi?
OverTheWifi is an open source project that uses java to transmit 
information from one device to another. The transmitting device is called 
the server, and the recieving device is called the client.

What can OverTheWifi be used for?
OverTheWifi can be used to allow users to share files, copied / cut text, 
and much more over their wireless network. This also works if you know your 
device's public ip address. I highly recommend using ShareData.DEFAULT_PORT 
for the port on server and client.

How can it be run?

Server:

	```
	Server server = new Server();
	
	ShareData data = ShareData.setShareData("text or other object to be shared", ShareDataType.OTHER);
	
	server.sendData(data, ShareData.DEFAULT_PORT);
	```


Client:

	```
	Client client = new Client();
	
	ShareData data = client.listen("localhost", ShareData.DEFAULT_PORT);
	```

Make sure that localhost is replaced with whichever IP address you want to 
use, and replace ShareData.DEFAULT_PORT with your own value. As you may have noticed, the Client class has aleady several functions 
for listening on all ip addresses connected to the wireless network. Edit 
that to your heart's desire :)
