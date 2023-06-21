package Test;

import Services.BaseEndpoint;
import Services.IndividualEndpoint;
import Utils.UtilityFile;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class IndividualTest extends IndividualEndpoint {
    LoginAndGetJWT login = new LoginAndGetJWT();
    BaseEndpoint b = new BaseEndpoint();

    String bearerToken = login.generateAccessToken();
    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    public IndividualTest() throws IOException, ParseException {
    }

    public  void setup()
    {
        //create data
    }
    // write a method eg. create individual by calling the methods from IndividualEndpoints class

    @Test
    public void getIndividuals() throws IOException, ParseException {
        Response response;

        response = given().spec(requestSpecification()).header("Authorization","Bearer " + bearerToken)
                .when().get(b.resource_GetIndividuals)
                .then().spec(responseSpecification()).extract().response();
        log.info("Request hit successfully and response is received.");

        log.info(getJsonPath(response,"Id"));
        log.info("All ids are extracted from response.");

        log.info(response.asString());
        log.info("Json converted to String successfully.");

        log.info("Status code is "+response.getStatusCode());

    }

//    @Test
//    public void getIndividual(){
//
//        Response response;
//
//        response =
//
//
//    }
}
