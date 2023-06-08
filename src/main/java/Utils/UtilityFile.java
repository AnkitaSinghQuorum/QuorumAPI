package Utils;

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

    public ResponseSpecification responseSpecification() throws IOException{

        res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        return res;
    }
}
