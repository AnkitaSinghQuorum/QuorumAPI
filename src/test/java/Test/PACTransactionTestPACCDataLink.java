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

public class PACTransactionTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String pacTransactionID = "PACTransactionID";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    String getSinglePACTransactionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePACTransaction.json";
    String addSinglePACTransactionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/AddSinglePACTransaction.json";
    String editPACTransactionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/EditPACTransaction.json";
    String deletePACTransactionJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/DeletePACTransaction.json";

    int getSinglePACTransactionId = getQueryParamFromJsonFile(getSinglePACTransactionJson,pacTransactionID);
    int editPACTransactionId = getQueryParamFromJsonFile(editPACTransactionJson,pacTransactionID);
    int deletePACTransactionId = getQueryParamFromJsonFile(deletePACTransactionJson,pacTransactionID);

    public PACTransactionTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfPACTransactions() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPACTransactions)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PAC Transactions.");
        log.info("PAC Transaction IDs extracted from response are "+ getJsonPath(response, pacTransactionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSinglePACTransaction() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePACTransaction+getSinglePACTransactionId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC Transaction.");
        log.info("PAC Transaction ID extracted from response is "+ getJsonPath(response, pacTransactionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addSinglePACTransaction() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(addSinglePACTransactionJson)))
                .when().post(b.resourceAddSinglePACTransaction)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(pacTransactionID)).extract().response();

        log.info("Request hit successfully and response is received for adding single PAC Transaction.");
        log.info("The added PAC Transaction ID is " + getJsonPath(response, pacTransactionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void editPACTransaction() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(extractJsonToBePatched(editPACTransactionJson))
                .when().patch(b.resourceEditPACTransaction+editPACTransactionId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PAC Transaction.");
        log.info("The updated PAC Transaction ID is " + getJsonPath(response, pacTransactionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());

    }

    @Test(groups = {"PACCDataLink"})
    public void deletePACTransaction() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().delete(b.resourceDeleteSinglePACTransaction+deletePACTransactionId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for deleting PAC Transaction.");
        log.info("The deleted PACTransaction Id is " + getJsonPath(response, pacTransactionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }
}
