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
    String PACAccountID = "PACAccountID";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    public PACAccountTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfPACAccounts() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPACAccounts)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PAC Accounts.");
        log.info("PAC Account IDs extracted from response are "+ getJsonPath(response, "PACAccountID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSinglePACAccount() throws IOException, ParseException {

        int id = getQueryParamFromJsonFile(System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePACAccount.json","PACAccountID");

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePACAccount+id)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC Account.");
        log.info("PAC Account ID extracted from response is "+ getJsonPath(response, "PACAccountID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addSinglePACAccount() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/JsonData/AddSinglePACAccount.json")))
                .when().post(b.resourceAddSinglePACAccount)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(PACAccountID)).extract().response();

        log.info("Request hit successfully and response is received for adding single PAC Account.");
        log.info("The added PAC Account ID is " + getJsonPath(response, "PACAccountID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void updatePACAccount() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/JsonData/UpdatePACAccount.json")))
                .when().post(b.resourceUpdatePACAccount)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating PACCAccount.");
        log.info("The updated PACAccount ID is " + getJsonPath(response, "PACAccountID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());

    }

}
