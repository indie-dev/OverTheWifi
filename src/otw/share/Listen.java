package otw.share;

import java.util.List;

import otw.share.client.Client;

public class Listen 
{
	public static void main(String[] args)
	{
		Client client = new Client();
		Runnable runnable = new Runnable()
				{

					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						for(String ip : client.listAllConnectedHosts(100))
						{
							System.out.println(ip);
						}
					}
			
				};
				
		runnable.run();
		
	}
}
