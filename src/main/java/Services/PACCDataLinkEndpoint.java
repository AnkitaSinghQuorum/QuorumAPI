package Services;

import DataSource.LoginGRAPIServices;
import Utils.UtilityFile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PACCDataLinkEndpoint extends UtilityFile {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    public static RequestSpecification req;
    Object obj;
    JSONObject jsonObject;
    LoginGRAPIServices l_gr;
    String path = "src/test/resources/JsonData/LoginGRAPIServices.json";

    public RequestSpecification requestSpecification() throws IOException, ParseException {
        BaseEndpoint b = new BaseEndpoint();
        l_gr = new LoginGRAPIServices();
        l_gr.setParser(path);
        obj = l_gr.getParser();
        l_gr.setJsonObject((JSONObject) obj);
        jsonObject = l_gr.getJsonObject();
        if(req==null){
            req = new RequestSpecBuilder().setBaseUri(b.basePACCDataLinkUrl).setContentType(ContentType.JSON).build();
            req = given().spec(requestSpecification()).header("CompanyID", l_gr.getCompanyID());
            log.info("Request Specification ran successfully.");
            return req;
        }
        return req;
    }
}
