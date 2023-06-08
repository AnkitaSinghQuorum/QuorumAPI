package Test;

import Services.BaseEndpoint;
import Services.LoginEndpoint;
import Utils.UtilityFile;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class LoginTestGRAPIServices extends LoginEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    Response response;
    BaseEndpoint b = new BaseEndpoint();

    public String generateAccessTokenGRAPIServices() throws IOException, ParseException {

        response = requestSpecificationForGRAPIServices().when().post(b.resourceLoginAndGetJWT).then().spec(responseSpecification()).extract().response();
        log.info("Access Token for GRAPIServices generated successfully.");


        String accessTokenGRAPIServices = getJsonPath(response, "access_token");

        log.info("Access Token for GRAPIServices is "+ accessTokenGRAPIServices);
        return accessTokenGRAPIServices;

    }

}
