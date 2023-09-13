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

    public String generateAccessTokenGRAPIServices() {

        String accessTokenGRAPIServices = null;
        try {
            response = requestSpecificationForGRAPIServices().when().post(b.resourceLoginAndGetJWT).then().spec(responseSpecificationForStatusCode()).extract().response();
            log.info("Access Token for GRAPIServices generated successfully.");

            accessTokenGRAPIServices = getJsonPath(response, "access_token");

            log.info("Access Token for GRAPIServices is " + accessTokenGRAPIServices);
        }catch (Exception e) {
            System.out.println("VPN should be connected.." + e.getMessage());
            log.error("VPN should be connected..");
        }

        return accessTokenGRAPIServices;


    }

}
