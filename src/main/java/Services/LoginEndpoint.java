package Services;

import DataSource.LoginGRAPIServices;
import DataSource.LoginP2P;
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
    LoginP2P l;
    LoginGRAPIServices l_gr;
    BaseEndpoint b = new BaseEndpoint();
    public RequestSpecification requestSpecification() throws IOException, ParseException {

        if(req==null)
        {
            String path = "src/test/resources/JsonData/LoginAndGetJWTP2P.json";
            l = new LoginP2P();
            l.setParser(path);
            obj = l.getParser();
            l.setJsonObject((JSONObject) obj);
            jsonObject = l.getJsonObject();
            req = new RequestSpecBuilder().setBaseUri(b.baseIdentityServerUrl).setContentType(ContentType.URLENC).build();
            req = given().spec(requestSpecification())
                    .formParam("username",l.getUsername())
                    .formParam("password",l.getPassword())
                    .formParam("grant_type",l.getGrant_type())
                    .formParam("site",l.getSite())
                    .formParam("client_id",l.getClient_id());
            log.info("Request Specification ran successfully and form parameters are fetched from json for P2P.");
            return req;
        }
        return req;
    }

    public RequestSpecification requestSpecificationForGRAPIServices() throws IOException, ParseException {

        if(req==null)
        {
            String path = "src/test/resources/JsonData/LoginGRAPIServices.json";
            l_gr = new LoginGRAPIServices();
            l_gr.setParser(path);
            obj = l_gr.getParser();
            l_gr.setJsonObject((JSONObject) obj);
            jsonObject = l_gr.getJsonObject();
            req = new RequestSpecBuilder().setBaseUri(b.baseIdentityServerUrl).setContentType(ContentType.URLENC).build();
            req = given().spec(requestSpecificationForGRAPIServices())
                    .formParam("grant_type",l_gr.getGrant_type())
                    .formParam("secret",l_gr.getSecret())
                    .formParam("client_id",l_gr.getClient_id());
            log.info("Request Specification ran successfully and form parameters are fetched from json for GRAPIServices.");
            return req;
        }
        return req;
    }







}
