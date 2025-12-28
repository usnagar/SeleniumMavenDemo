package automation.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
	//read JSON to String
	String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\automation\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);

	//Convert String to HashMap
	ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){ });
	
	return data;
	
	//Created one Mapper Object and reading json value using it
	//ReadValue method read a string value and convert into HashMap
	//Currently asking create two HashMaps based upon numbers of index and put into one list and give us to back(Array is having 2 indexes[{},{} in json file])

}
	
}
