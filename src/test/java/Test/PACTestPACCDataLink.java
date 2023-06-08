package Test;

import Services.BaseEndpoint;
import Services.PACCDataLinkEndpoint;
import Utils.UtilityFile;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PACTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    public PACTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfPACs() throws IOException, ParseException {
        Response response;

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPacs)
                .then().spec(responseSpecification()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PACs.");

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("PAC IDs extracted from response are "+ getJsonPath(response, "PACID"));

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSinglePAC() throws IOException, ParseException {
        Response response;
        int id = getQueryParamFromJsonFile("D:\\Ankita\\Quorum Prod Dev\\QuorumAPI\\src\\test\\resources\\JsonData\\GetSinglePAC.json","PACID");

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePac+id)
                .then().spec(responseSpecification()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC.");

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("PAC ID extracted from response is "+ getJsonPath(response, "PACID"));

        log.info("Status code is " + response.getStatusCode());
    }

}
