import org.junit.Assert;
import org.junit.Test;

import pattern01.FileParser;

public class FileParserTest {

	@Test
	public void test() {
		FileParser parser = new FileParser();
		Assert.assertTrue(parser.getClassMethodsCollection().size() > 0);
	}

}
