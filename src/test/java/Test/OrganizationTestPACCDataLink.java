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

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    public OrganizationTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getPACCOrganizations() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetPACCOrganizations)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for get PACC organization.");

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Organization IDs extracted from response are "+ getJsonPath(response, "OrganizationID"));

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addNewPACCOrganization() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/JsonData/AddNewPACCOrganization.json")))
                .when().post(b.resourceAddNewPACCOrganization)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForOrganizationID()).extract().response();

        log.info("Request hit successfully and response is received for adding new PACC organization.");
        log.info("The added Organization Id is " + getJsonPath(response, "OrganizationID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

   @Test(groups ={"PACCDataLink"})
    public void updatePACCOrganization() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/JsonData/UpdatePACCOrganization.json")))
                .when().patch(b.resourceUpdatePACCOrganization)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PACC organization.");
        log.info("The updated Organization Id is " + getJsonPath(response, "OrganizationID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void deletePACCOrganization() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/JsonData/DeletePACCOrganization.json")))
                .when().post(b.resourceDeletePACCOrganization)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for deleting PACC organization.");
        log.info("The deleted Organization Id is " + getJsonPath(response, "OrganizationID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

}
