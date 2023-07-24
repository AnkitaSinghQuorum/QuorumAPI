package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.*;


//import org.slf4j.Logger;

public class UtilityFile {
    public static ResponseSpecification res;
    private static boolean root = false;


    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    public static Logger getLogger(Class cls) {
        if (root) {
            return Logger.getLogger(cls);
        } else {
            PropertyConfigurator.configure("src/test/resources/log4j.properties");
            root = true;
            return Logger.getLogger(cls);
        }

    }

    public String getJsonPath(Response response, String key){

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }

    public int getQueryParamFromJsonFile(String path, String key) throws IOException {

        JsonPath js = new JsonPath(new String(Files.readAllBytes(Path.of(path))));
        return js.getInt(key);
    }

    public String extractJsonToBePatched(String path) throws IOException {

        JsonPath js = new JsonPath((Files.readString(Paths.get(path))));

        List<String> patchJsonAsList = js.getList("patch");

        ObjectMapper objectMapper = new ObjectMapper();
        String patchJson = objectMapper.writeValueAsString(patchJsonAsList);
        log.info("The json to be patched is "+patchJson);
        return patchJson;

    }

    public ResponseSpecification responseSpecificationForStatusCode() throws IOException{

        res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        return res;
    }

    public ResponseSpecification responseSpecificationForID(String ID) throws IOException{

        res = new ResponseSpecBuilder().expectBody(ID,notNullValue()).build();
        return res;
    }

}