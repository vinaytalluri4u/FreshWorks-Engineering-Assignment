package freshworks.key_value_datastore;
import java.util.*;
import org.json.simple.JSONObject;
public class DataStore
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws KeySizeExceededException, FileSizeExceededException
	{
		
		Scanner sc=new Scanner(System.in);
        System.out.println("*-*-*-*-*-Welcome to Freshworks Key-Value DataStore*-*-*-*-*-");
        String key = null;
        String filepath=null;
        System.out.println("Do you want to provide DataStore directory:[Y/N]:");
        char choice=sc.next().charAt(0);
        if(choice=='Y'||choice=='y')
        {
        	System.out.println("Enter file path to create file eg:[D:\\users\\Documents\\]");
        	filepath=sc.next();
        }
        filepath=FileOperations.validateFilePath(filepath);
        String value = null;
        Long dateInMilliSecs = 0L;
        while(true)
        {
        System.out.println("Press 1 : Create Key-Value pair");
        System.out.println("Press 2 : Read Key-Value pair");
        System.out.println("Press 3 : Delete by Key");
        System.out.println("Press 4 : Exit");
        Calendar calendar = Calendar.getInstance();
        int option = sc.nextInt();
        if(option==1)
        {
        	System.out.print("Enter the key : ");
            key = sc.next();
            if (key.equals("")) 
            {
              System.err.println("FAILED : Key cannot be empty");
              continue;
            }
            if(!FileOperations.checkKeySize(key))//If key size > 32 chars, It is Invalid
            {
            continue;
            }
            JSONObject jsonObject = FileOperations.readFromFile(filepath);
            if (jsonObject.containsKey(key)) 
            {
              System.out.println("FAILED: Key already exists in DataStore. Please change the key and try again");
              continue;
            }
            System.out.println("Give the value : ");
            value = sc.next();
            if (value.equals(""))
            {
              System.err.println("FAILED: Value cannot be empty");
              continue;
            }
            System.out.println("Give Time To Live(TTL) in seconds : ");
            int TTL = sc.nextInt();
            //TTL in seconds is converted to milli seconds and added with present time in milliseconds
            //dateInMillisecs is stored in file along with key-value pair seperated by delimiter(:)
            dateInMilliSecs = calendar.getTimeInMillis() + (TTL * 1000);
            value=value+":"+dateInMilliSecs.toString();
            jsonObject.put(key,value);
            if(FileOperations.writeToFile(filepath, jsonObject))
            {
            	System.out.println("Creation of Key-Value pair in to data store is successfull");
            }
            else
            {
            	System.err.println("Creation of Key-Value pair Failed");
            }
        }
        else if(option==2)
        {
        	System.out.println("Enter the key to retreive it's value");
        	key=sc.next();
        	JSONObject jsonObject = FileOperations.readFromFile(filepath);
        	if(!jsonObject.containsKey(key))
        	{
        		System.err.println("Key not found in DataStore try again");
        		continue;
        	}
        	value=FileOperations.validateKey((String) jsonObject.get(key),calendar);
        	if(value.length()==0)
        	{
        		jsonObject.remove(key);
                if(FileOperations.writeToFile(filepath, jsonObject))
                System.out.println("FAILED: Key expired and it has been removed from DataStore");
                continue;
             }
        	System.out.println("Key found, key value pair is:");
        	System.out.println(key+" : "+value);
        	
        }
        else if(option==3)
        {
        	System.out.println("Enter Key to delete");
        	key=sc.next();
        	JSONObject jsonObject = FileOperations.readFromFile(filepath);
        	if(!jsonObject.containsKey(key))
        	{
        		System.out.println("Key doesn't exsist try with another key");
        		continue;
        	}
        	value=FileOperations.validateKey((String) jsonObject.get(key),calendar);
        	if(value.length()==0)
        	{
        		jsonObject.remove(key);
                if(FileOperations.writeToFile(filepath, jsonObject))
                System.out.println("FAILED: Key expired and it has been removed from DataStore");
                continue;
             }
        	jsonObject.remove(key);
        	if(FileOperations.writeToFile(filepath, jsonObject))
            {
        		System.out.println("Key : "+key+" deleted successfully");
            }
           
        }
        else if(option==4)
        {
        	break;
        }
        else
        {
        	System.out.println("Invalid Input try again");
        }
        }
	}
}


