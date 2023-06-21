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

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    public PACTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfPACs() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPACs)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PACs.");

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("PAC IDs extracted from response are "+ getJsonPath(response, "PACID"));

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSinglePAC() throws IOException, ParseException {

        int id = getQueryParamFromJsonFile(System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePAC.json","PACID");

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePAC+id)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC.");

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("PAC ID extracted from response is "+ getJsonPath(response, "PACID"));

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addNewPACCPAC() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/JsonData/AddNewPACCPAC.json")))
                .when().post(b.resourceAddNewPACCPAC)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForPACID()).extract().response();

        log.info("Request hit successfully and response is received for adding new PACC PAC.");
        log.info("The added PAC ID is " + getJsonPath(response, "PACID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void updatePACCPAC() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/JsonData/UpdatePACCPAC.json")))
                .when().patch(b.resourceUpdatePACCPAC)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PACC PAC.");
        log.info("The updated PAC ID is " + getJsonPath(response, "PACID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void deletePACCPAC() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/JsonData/DeletePACCPAC.json")))
                .when().post(b.resourceDeletePACCPAC)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for deleting PACC PAC.");
        log.info("The deleted PAC ID is " + getJsonPath(response, "PACID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }


}
