package freshworks.key_value_datastore;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Calendar;
public class FileOperations 
{
	
	public static boolean writeToFile(String filePath,JSONObject jsonObject) throws FileSizeExceededException 
	{
		File f=new File(filePath);
		try
		{
        if((f.length() + (16 * 1024)) > 1073741824L) //if File size + current data(16KB) exceeds 1 GB or File is not writable error
	    {
          throw new FileSizeExceededException();
	    }
		}
		catch(FileSizeExceededException e)
		{
			System.out.println(e);
			return false;
		}
	    try
	    {
	    	FileWriter file=new FileWriter(f);
            file.write(jsonObject.toJSONString());
            file.flush();
            return true;
        } 
	    catch(IOException e)
	    {
            e.printStackTrace();
        }
	    return false;
	  }
	
	public static JSONObject readFromFile(String filePath)
	{
		JSONParser parser = new JSONParser();
        Object obj;
        JSONObject jsonObject = null;
        File file=new File(filePath);
		try 
		{
			if(file.exists())
			{
			if(file.length()==0)
			{
				return new JSONObject();
			}
			}
			else
			{
				file.createNewFile();
				return new JSONObject();
			}
			obj = parser.parse(new FileReader(filePath));
			jsonObject = (JSONObject)obj;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return jsonObject;
	}
	public static boolean checkKeySize(String key) throws KeySizeExceededException
	{
		try
		{
		if (key.length()>32) // Check if Key is more than 32 Char
        {
        throw new KeySizeExceededException();
        }
		}
		catch(KeySizeExceededException e)
		{
		System.out.println(e);
		return false;
	    }
		return true;
	}
	public static String validateFilePath(String filePath)
	{
		if(filePath == null)
		{
			filePath="D:\\datastore.json";
		}
		else if (new File(filePath).exists())
		{
			filePath=filePath+"datastore.json";
			System.out.println("datastore created successfulyy in "+filePath);
		}
		else
		{
			filePath="D:\\datastore.json";
			System.out.println("Directory does not exists datastore created in "+filePath);
		}
		return filePath;
	}
	public static String validateKey(String value,Calendar calendar)
	{
		String[] str=value.split(":");
    	value=str[0];
    	long dateInMilliSecs = Long.valueOf(str[1]);
    	if(calendar.getTimeInMillis() > dateInMilliSecs)
    	{
            return "";
        }
    	return value;
	}
}
