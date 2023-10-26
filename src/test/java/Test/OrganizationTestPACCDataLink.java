package Test;

import Services.BaseEndpoint;
import Services.PACCDataLinkEndpoint;
import Utils.UtilityFile;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class OrganizationTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String organizationID = "OrganizationID";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();
    String addNewPACCOrganizationJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/AddNewPACCOrganization.json";
    String updatePACCOrganizationJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/UpdatePACCOrganization.json";
    String deletePACCOrganizationJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/DeletePACCOrganization.json";
    String getSinglePACCOrganizationJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePACCOrganization.json";

    int updatePACCOrganizationId = getQueryParamFromJsonFile(updatePACCOrganizationJson,organizationID);
    int deletePACCOrganizationId = getQueryParamFromJsonFile(deletePACCOrganizationJson,organizationID);
    int getSinglePACCOrganizationId = getQueryParamFromJsonFile(getSinglePACCOrganizationJson,organizationID);

    public OrganizationTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups = {"PACCDataLink"})
    public void getPACCOrganizations() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetPACCOrganizations)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for get PACC organizations.");
        log.info("Organization IDs extracted from response are " + getJsonPath(response, organizationID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink1"})
    public void getSinglePACCOrganization() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePACCOrganization+ getSinglePACCOrganizationId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PACC organization.");
        log.info("Organization Id extracted from response is "+ getJsonPath(response, organizationID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups = {"PACCDataLink"})
    public void addNewPACCOrganization() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(addNewPACCOrganizationJson)))
                .when().post(b.resourceAddNewPACCOrganization)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(organizationID)).extract().response();

        log.info("Request hit successfully and response is received for adding new PACC organization.");
        log.info("The added Organization Id is " + getJsonPath(response, organizationID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups = {"PACCDataLink"})
    public void updatePACCOrganization() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(extractJsonToBePatched(updatePACCOrganizationJson))
                .when().patch(b.resourceUpdatePACCOrganization+updatePACCOrganizationId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PACC organization.");
        log.info("The updated Organization Id is " + getJsonPath(response, organizationID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups = {"PACCDataLink"})
    public void deletePACCOrganization() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().delete(b.resourceDeletePACCOrganization+deletePACCOrganizationId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for deleting PACC organization.");
        log.info("The deleted Organization Id is " + getJsonPath(response, organizationID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

}
