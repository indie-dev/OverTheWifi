package otw.share;

import otw.share.SharedData.SharedDataType;
import otw.share.server.Server;

public class Host {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Server server = new Server();
		SharedData data = SharedData.setShareData("Hello".getBytes(), SharedDataType.FILE);
		data.addMetaData(SharedData.METADATA_FILE_PATH, "test.jpg");
		data.addMetaData(SharedData.METADATA_FILE_CONTENT, new SharedData.SharedDataFileReader("hbo.jpg").read());
		data.addMetaData(SharedData.METADATA_FILE_SIZE, new SharedData.SharedDataFileReader("hbo.jpg").length());
		server.hostData(data, "localhost");
	}

}
