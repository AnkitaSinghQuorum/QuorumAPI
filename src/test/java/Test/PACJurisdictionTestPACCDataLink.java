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

public class PACJurisdictionTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String pacJurisdictionID = "PACJurisdictionID";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    String getSinglePACJurisdictionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePACJurisdiction.json";
    String addSinglePACJurisdictionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/AddSinglePACJurisdiction.json";
    String editPACJurisdictionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/EditPACJurisdiction.json";
    String deletePACJurisdictionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/DeletePACJurisdiction.json";

    int getSinglePACJurisdictionId = getQueryParamFromJsonFile(getSinglePACJurisdictionJson,pacJurisdictionID);
    int editPACJurisdictionId = getQueryParamFromJsonFile(editPACJurisdictionJson,pacJurisdictionID);
    int deletePACJurisdictionId = getQueryParamFromJsonFile(deletePACJurisdictionJson,pacJurisdictionID);

    public PACJurisdictionTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfPACJurisdictions() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPACJurisdictions)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PAC Jurisdictions.");
        log.info("PAC Jurisdiction IDs extracted from response are "+ getJsonPath(response, pacJurisdictionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSinglePACJurisdiction() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePACJurisdiction+getSinglePACJurisdictionId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC Jurisdiction.");
        log.info("PAC Jurisdiction ID extracted from response is "+ getJsonPath(response, pacJurisdictionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addSinglePACJurisdiction() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(addSinglePACJurisdictionJson)))
                .when().post(b.resourceAddSinglePACJurisdiction)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(pacJurisdictionID)).extract().response();

        log.info("Request hit successfully and response is received for adding single PAC Jurisdiction.");
        log.info("The added PAC Jurisdiction ID is " + getJsonPath(response, pacJurisdictionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void editPACJurisdiction() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(extractJsonToBePatched(editPACJurisdictionJson))
                .when().patch(b.resourceEditPACJurisdiction+editPACJurisdictionId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PAC Jurisdiction.");
        log.info("The updated PAC Jurisdiction ID is " + getJsonPath(response, pacJurisdictionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());

    }

    @Test(groups = {"PACCDataLink"})
    public void deletePACJurisdiction() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().delete(b.resourceDeleteSinglePACJurisdiction+deletePACJurisdictionId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for deleting PAC Jurisdiction.");
        log.info("The deleted PACJurisdiction Id is " + getJsonPath(response, pacJurisdictionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }
}
