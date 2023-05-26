package Services;

import DataSource.Login;
import Utils.UtilityFile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import static io.restassured.RestAssured.*;

public class LoginEndpoint extends UtilityFile {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    public static RequestSpecification req;
    Object obj;
    JSONObject jsonObject;
    Login l;
    BaseEndpoint b = new BaseEndpoint();
    public RequestSpecification requestSpecification() throws IOException, ParseException {

        if(req==null)
        {
            String path = "src/test/resources/JsonData/Login.json";
            l = new Login();
            l.setParser(path);
            obj = l.getParser();
            l.setJsonObject((JSONObject) obj);
            jsonObject = l.getJsonObject();
            req = new RequestSpecBuilder().setBaseUri(b.base_IdentityServerUrl).setContentType(ContentType.URLENC).build();
            req = given().spec(requestSpecification())
                    .formParam("username",l.getUsername())
                    .formParam("password",l.getPassword())
                    .formParam("grant_type",l.getGrant_type())
                    .formParam("site",l.getSite())
                    .formParam("client_id",l.getClient_id());
            log.info("Request Specification ran successfully and form parameters are fetched from json.");
            return req;
        }
        return req;
    }







}
