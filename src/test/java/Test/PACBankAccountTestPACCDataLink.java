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

public class PACBankAccountTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String pacBankAccountID = "PACBankAccountID";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();
    String getSinglePACBankAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePACBankAccount.json";
    String addSinglePACBankAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/AddSinglePACBankAccount.json";
    String updatePACBankAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/UpdatePACBankAccount.json";
    String deletePACCPACBankAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/DeletePACCPACBankAccount.json";

    int getSinglePACBankAccountId = getQueryParamFromJsonFile(getSinglePACBankAccountJson,pacBankAccountID);
    int updatePACBankAccountId = getQueryParamFromJsonFile(updatePACBankAccountJson,pacBankAccountID);
    int deletePACCPACBankAccountId = getQueryParamFromJsonFile(deletePACCPACBankAccountJson,pacBankAccountID);

    public PACBankAccountTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfPACBankAccounts() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPACBankAccounts)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PAC Bank Accounts.");
        log.info("PAC Bank Account IDs extracted from response are "+ getJsonPath(response, pacBankAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSinglePACBankAccount() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePACBankAccount+getSinglePACBankAccountId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC Bank Account.");
        log.info("PAC Bank Account ID extracted from response is "+ getJsonPath(response, pacBankAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addSinglePACBankAccount() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(addSinglePACBankAccountJson)))
                .when().post(b.resourceAddSinglePACBankAccount)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(pacBankAccountID)).extract().response();

        log.info("Request hit successfully and response is received for adding single PAC Account.");
        log.info("The added PAC Bank Account ID is " + getJsonPath(response, pacBankAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void updatePACBankAccount() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(extractJsonToBePatched(updatePACBankAccountJson))
                .when().patch(b.resourceUpdatePACBankAccount+updatePACBankAccountId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PAC Bank Account.");
        log.info("The updated PAC Bank Account ID is " + getJsonPath(response, pacBankAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());

    }

    @Test(groups = {"PACCDataLink"})
    public void deletePACCPACBankAccount() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().delete(b.resourceDeletePACCPACBankAccount+deletePACCPACBankAccountId)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for deleting PACC PAC Bank Account.");
        log.info("The deleted PACBankAccount Id is " + getJsonPath(response, pacBankAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }
}
