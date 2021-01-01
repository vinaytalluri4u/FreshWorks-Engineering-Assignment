package freshworks.key_value_datastore;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class FileOperationsTest {

	FileOperations store;
	@BeforeEach
	void setup()
	{
		store=new FileOperations();	
	}
	@Test
	public void ValidateKeyTest()
	{
		//value contains 2 attributes divided with ':' first part is value of key and second part is TTL in milli seconds
		//validate key return string value if TTL is valid else return empty string
		assertEquals("FreshWorks",store.validateKey("FreshWorks:1909257698163",Calendar.getInstance()));
	}
	@Test
	public void ValidateKeyTest2()
	{
		assertEquals("",store.validateKey("FreshWorks:1509257698162", Calendar.getInstance()));
	}

}
