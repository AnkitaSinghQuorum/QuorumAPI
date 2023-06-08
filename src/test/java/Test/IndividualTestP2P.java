package Test;

import Services.BaseEndpoint;
import Services.IndividualEndpointP2P;
import Services.IndividualEndpointP2P;
import Utils.UtilityFile;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class IndividualTestP2P extends IndividualEndpointP2P {
    LoginAndGetJWT login = new LoginAndGetJWT();
    BaseEndpoint b = new BaseEndpoint();

    String bearerToken = login.generateAccessTokenP2P();
    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    public IndividualTestP2P() throws IOException, ParseException {
    }

    public  void setup()
    {
        //create data
    }
    // write a method eg. create individual by calling the methods from IndividualEndpoints class

    @Test(groups ={"P2P"})
    public void getIndividuals() throws IOException, ParseException {
        Response response;

        response = given().spec(requestSpecification()).header("Authorization","Bearer " + bearerToken)
                .when().get(b.resourceGetIndividuals)
                .then().spec(responseSpecification()).extract().response();
        log.info("Request hit successfully and response is received.");

        log.info(getJsonPath(response,"Id"));
        log.info("All ids are extracted from response.");

        log.info(response.asPrettyString());
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
