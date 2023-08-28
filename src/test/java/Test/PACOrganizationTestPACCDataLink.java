package Test;

import Services.BaseEndpoint;
import Services.PACCDataLinkEndpoint;
import Utils.UtilityFile;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class PACOrganizationTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String pacOrganizationID = "PACOrganizationID";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    String addPACOrgsJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/AddNewPACOrgs.json";
    String updatePACOrgsJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/UpdatePACOrgs.json";
    String getSinglePACOrgsJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePACOrgs.json";

    int getSinglePACOrgsId = getQueryParamFromJsonFile(getSinglePACOrgsJson,pacOrganizationID);
    int updatePACOrgsId = getQueryParamFromJsonFile(updatePACOrgsJson,pacOrganizationID);

    public PACOrganizationTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfAllPACOrgs() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPACOrgs)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PAC Orgs.");
        log.info("PAC Organization ID extracted from response are " + getJsonPath(response, "PACOrganizationID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getPACOrgsByID() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePACOrgs+getSinglePACOrgsId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC Org.");
        log.info("PAC Organization ID extracted from response is "+ getJsonPath(response, pacOrganizationID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addNewPACOrgs() throws IOException, ParseException {

      response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(addPACOrgsJson)))
                .when().post(b.resourceAddNewPACOrgs)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(pacOrganizationID)).extract().response();

        log.info("Request hit successfully and response is received for adding new PAC Orgs.");
        log.info("The added PAC Organization ID is " + getJsonPath(response, pacOrganizationID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void updatePACOrgs() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(extractJsonToBePatched(updatePACOrgsJson))
                .when().patch(b.resourceUpdatePACOrgs+updatePACOrgsId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PAC orgs.");
        log.info("The updated PAC ID is " + getJsonPath(response, pacOrganizationID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

}
