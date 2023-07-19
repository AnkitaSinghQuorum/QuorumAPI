package Test;

import Services.BaseEndpoint;
import Services.PACCDataLinkEndpoint;
import Utils.UtilityFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.cucumber.datatable.internal.difflib.StringUtills;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PACReportMasterTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String PACReportMasterID ="PACReportMasterID";
    String pathForJsonPatch ="/src/test/resources/JsonData/EditSinglePACReport.json";

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    public PACReportMasterTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getListOfPACReportMasterRecords() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetListOfPACReportMasterRecords)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting list of PACReportMaster Records.");
        log.info("PACReportMaster IDs extracted from response are "+ getJsonPath(response, "PACReportMasterID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void getSinglePACReport() throws IOException, ParseException {

        int id = getQueryParamFromJsonFile(System.getProperty("user.dir") + "/src/test/resources/JsonData/GetSinglePACReport.json","PACReportMasterID");

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetSinglePACReport+id)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting a single PAC Report.");
        log.info("PACReportMaster ID extracted from response is "+ getJsonPath(response, "PACReportMasterID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void editSinglePACReport() throws IOException, ParseException {

        int id = getQueryParamFromJsonFile(System.getProperty("user.dir") + "/src/test/resources/JsonData/EditSinglePACReport.json","PACReportMasterID");

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(extractJsonToBePatched(pathForJsonPatch))
                .when().patch(b.resourceEditSinglePACReport+id)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for updating single PAC Report.");
        log.info("The updated PACReportMasterID is " + getJsonPath(response, "PACReportMasterID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups ={"PACCDataLink"})
    public void addNewPACCPACReport() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/JsonData/AddNewPACCPACReport.json")))
                .when().post(b.resourceAddNewPACCPACReport)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(PACReportMasterID)).extract().response();

        log.info("Request hit successfully and response is received for adding new PACC PACReport.");
        log.info("The added PACReportMasterID is " + getJsonPath(response, "PACReportMasterID"));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }
}
