package helloservlet.function;

import java.nio.charset.StandardCharsets;

public class SetUTF8 {
	public String setUTF8(String string) {
		byte[] bytes = string.getBytes(StandardCharsets.ISO_8859_1);
		string = new String(bytes,StandardCharsets.UTF_8);
		return string;
	}
}
