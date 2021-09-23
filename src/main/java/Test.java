
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test
{
    public static void main(String[] args)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();

            TypeReference<HashMap<String,Object>> typeReference = new TypeReference<HashMap<String, Object>>(){};

            Map<String,Object> document = objectMapper.readValue(new File("welcome.json"),typeReference);

            Map<String,Object> dataSource = objectMapper.readValue(new File("welcomeTemplate.json"),typeReference);

            System.out.print("Document :::: " + document);
            System.out.print("DataSource :::: " + dataSource);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
