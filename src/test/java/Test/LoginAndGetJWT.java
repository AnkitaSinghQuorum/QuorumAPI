package Test;

import Services.BaseEndpoint;
import Services.LoginEndpoint;
import Utils.UtilityFile;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class LoginAndGetJWT extends LoginEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    Response response;
    BaseEndpoint b = new BaseEndpoint();

    public String generateAccessTokenP2P() throws IOException, ParseException {

      response = requestSpecification().when().post(b.resourceLoginAndGetJWT).then().spec(responseSpecification()).extract().response();
      log.info("Access Token for P2P generated successfully.");


         String accessTokenP2P = getJsonPath(response, "access_token");

        log.info("Access Token for P2P is "+ accessTokenP2P);
        return accessTokenP2P;

    }

}
