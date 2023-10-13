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

public class PACElectionTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String pacElectionID = "PACElectionID";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    String getSinglePACElectionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePACElection.json";
    String addSinglePACElectionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/AddSinglePACElection.json";
    String editSinglePACElectionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/EditSinglePACElection.json";
    String deleteSinglePACElectionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/DeleteSinglePACElection.json";

    int getSinglePACElectionId = getQueryParamFromJsonFile(getSinglePACElectionJson,pacElectionID);
    int editSinglePACElectionId = getQueryParamFromJsonFile(editSinglePACElectionJson,pacElectionID);
    int deleteSinglePACElectionId = getQueryParamFromJsonFile(deleteSinglePACElectionJson,pacElectionID);

    public PACElectionTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfPACElections() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPACElections)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PAC Elections.");
        log.info("PAC Election IDs extracted from response are "+ getJsonPath(response, pacElectionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSinglePACElection() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePACElection+getSinglePACElectionId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC Election.");
        log.info("PAC Election ID extracted from response is "+ getJsonPath(response, pacElectionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addSinglePACElection() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(addSinglePACElectionJson)))
                .when().post(b.resourceAddSinglePACElection)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(pacElectionID)).extract().response();

        log.info("Request hit successfully and response is received for adding single PAC Election.");
        log.info("The added PAC Election ID is " + getJsonPath(response, pacElectionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void editPACElection() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(extractJsonToBePatched(editSinglePACElectionJson))
                .when().patch(b.resourceEditPACElection+editSinglePACElectionId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PAC Election.");
        log.info("The updated PAC Election ID is " + getJsonPath(response, pacElectionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups = {"PACCDataLink"})
    public void deletePACElection() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().delete(b.resourceDeleteSinglePACElection+deleteSinglePACElectionId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for deleting PAC Jurisdiction.");
        log.info("The deleted PACJurisdiction Id is " + getJsonPath(response, pacElectionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }
}
