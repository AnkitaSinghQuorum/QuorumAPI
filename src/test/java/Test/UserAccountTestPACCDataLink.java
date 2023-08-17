package Test;

import Services.BaseEndpoint;
import Services.PACCDataLinkEndpoint;
import Utils.UtilityFile;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class UserAccountTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();
    String addUserAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/AddNewUserAccounts.json";
    String deleteUserAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/DeleteUserAccount.json";
    String updateUserAccountJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/UpdateUserAccount.json";
    String userAccountID = "UserAccountID";

    public UserAccountTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfAllUserAccounts() throws IOException, ParseException {


        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfUserAccounts)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of User Accounts.");

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("User Accounts ID extracted from response are " + getJsonPath(response, "UserAccountID"));

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addNewUserAccounts() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(addUserAccountJson)))
                .when().post(b.resourceAddNewUserAccount)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(userAccountID)).extract().response();

        log.info("Request hit successfully and response is received for adding new User Accounts.");
        log.info("The added User Account ID is " + getJsonPath(response, userAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

//    @Test(groups ={"PACCDataLink"})
//    public void deleteUserAccounts() throws IOException, ParseException {
//
//        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
//                .body(Files.readAllBytes(Paths.get(deleteUserAccountJson)))
//                .when().post(b.resourceDeleteUserAccount)
//                .then().spec(responseSpecificationForStatusCode()).extract().response();
//
//        log.info("Request hit successfully and response is received for deleting user account.");
//        log.info("The deleted user account ID is " + getJsonPath(response, userAccountID));
//
//        log.info(response.asPrettyString());
//        log.info("Response json converted to String successfully.");
//
//        log.info("Status code is " + response.getStatusCode());
//    }

    @Test(groups ={"PACCDataLink"})
    public void updatePACCPAC() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(updateUserAccountJson)))
                .when().patch(b.resourceUpdateUserAccount)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating User Account.");
        log.info("The updated User Account ID is " + getJsonPath(response, userAccountID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }
}
