package Test;

import Utils.UtilityFile;
import io.restassured.response.Response;
import Services.*;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import java.io.IOException;


public class LoginAndGetJWT extends LoginEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    Response response;
    BaseEndpoint b = new BaseEndpoint();

    @BeforeTest()
    public String generateAccessToken() throws IOException, ParseException {

      response = requestSpecification().when().post(b.resource_LoginAndGetJWT).then().spec(responseSpecification()).extract().response();
      log.info("Access Token generated successfully.");


         String accessToken = getJsonPath(response, "access_token");

        log.info("Access Token is "+ accessToken);
        return accessToken;

    }

}
