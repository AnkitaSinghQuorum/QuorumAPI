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
import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;

public class PACAccountTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String pacAccountID = "PACAccountID";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    String getSinglePACAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePACAccount.json";
    String addSinglePACAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/AddSinglePACAccount.json";
    String updatePACAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/UpdatePACAccount.json";
    String deletePACCPACAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/DeletePACCPACAccount.json";

    int deletePACCPACAccountId = getQueryParamFromJsonFile(deletePACCPACAccountJson,pacAccountID);
    int getSinglePACAccountId = getQueryParamFromJsonFile(getSinglePACAccountJson,pacAccountID);
    int updatePACAccountId = getQueryParamFromJsonFile(updatePACAccountJson,pacAccountID);

    public PACAccountTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfPACAccounts() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPACAccounts)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PAC Accounts.");
        log.info("PAC Account IDs extracted from response are "+ getJsonPath(response, pacAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSinglePACAccount() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePACAccount+getSinglePACAccountId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC Account.");
        log.info("PAC Account ID extracted from response is "+ getJsonPath(response, pacAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addSinglePACAccount() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(addSinglePACAccountJson)))
                .when().post(b.resourceAddSinglePACAccount)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(pacAccountID)).extract().response();

        log.info("Request hit successfully and response is received for adding single PAC Account.");
        log.info("The added PAC Account ID is " + getJsonPath(response, pacAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void updatePACAccount() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(extractJsonToBePatched(updatePACAccountJson))
                .when().patch(b.resourceUpdatePACAccount+updatePACAccountId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PACCAccount.");
        log.info("The updated PACAccount ID is " + getJsonPath(response, pacAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());

    }

    @Test(groups = {"PACCDataLink"})
    public void deletePACCPACAccount() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().delete(b.resourceDeletePACCPACAccount+deletePACCPACAccountId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for deleting PACC PACAccount.");
        log.info("The deleted PACAccount Id is " + getJsonPath(response, pacAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }


}
