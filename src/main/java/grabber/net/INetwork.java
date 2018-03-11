package grabber.net;

import java.io.IOException;

public interface INetwork {
	public String connect() throws IOException;
	public void sendJson(String json) throws IOException;
}
