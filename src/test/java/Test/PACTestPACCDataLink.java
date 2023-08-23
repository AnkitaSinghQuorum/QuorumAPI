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

public class PACTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String pacID = "PACID";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    String getSinglePACJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePAC.json";
    String addNewPACCPACJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/AddNewPACCPAC.json";
    String updatePACCPACJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/UpdatePACCPAC.json";
    String deletePACCPACJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/DeletePACCPAC.json";

    int getSinglePACId = getQueryParamFromJsonFile(getSinglePACJson,pacID);
    int updatePACCPACId = getQueryParamFromJsonFile(updatePACCPACJson,pacID);
    int deletePACCPACId = getQueryParamFromJsonFile(deletePACCPACJson,pacID);

    public PACTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfPACs() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPACs)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PACs.");
        log.info("PAC IDs extracted from response are "+ getJsonPath(response, pacID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSinglePAC() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePAC+getSinglePACId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC.");
        log.info("PAC ID extracted from response is "+ getJsonPath(response, pacID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addNewPACCPAC() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(addNewPACCPACJson)))
                .when().post(b.resourceAddNewPACCPAC)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(pacID)).extract().response();

        log.info("Request hit successfully and response is received for adding new PACC PAC.");
        log.info("The added PAC ID is " + getJsonPath(response, pacID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void updatePACCPAC() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(extractJsonToBePatched(updatePACCPACJson))
                .when().patch(b.resourceUpdatePACCPAC+updatePACCPACId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PACC PAC.");
        log.info("The updated PAC ID is " + getJsonPath(response, pacID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void deletePACCPAC() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().delete(b.resourceDeletePACCPAC+deletePACCPACId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for deleting PACC PAC.");
        log.info("The deleted PAC ID is " + getJsonPath(response, pacID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }


}
