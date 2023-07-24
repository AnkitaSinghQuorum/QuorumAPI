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

public class IndividualTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String individualId = "IndividualId";
    String getSingleIndividualJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSingleIndividual.json";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    public IndividualTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfIndividualRecords() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfIndividualRecords)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of Individual Records.");
        log.info("Individual IDs extracted from response are "+ getJsonPath(response, individualId));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSingleIndividual() throws IOException, ParseException {

        int id = getQueryParamFromJsonFile(getSingleIndividualJson,individualId);

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSingleIndividual+id)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single individual.");
        log.info("Individual  Id extracted from response is "+ getJsonPath(response, individualId));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }
}
