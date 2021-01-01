package freshworks.key_value_datastore;

public class FileSizeExceededException extends Exception
{
	public String toString()
	{
		return "File size exceeds maximum size, cannot create Key";
	}

}
