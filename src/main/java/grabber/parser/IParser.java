package grabber.parser;

import java.io.InputStream;

public interface IParser {
	public String parser(InputStream in);
	public String jsonCreate(String string);
}
