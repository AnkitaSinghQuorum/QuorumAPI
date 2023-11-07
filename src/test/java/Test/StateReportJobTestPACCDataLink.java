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

public class StateReportJobTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(StateReportJobTestPACCDataLink.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;
    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();
    String jobActionID = "JobActionID"; // we are passing it to the GET request by JobActionID from Json
    String getJobStatusJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/GetJobStatus.json";
    String executeJobByParameterJson = System.getProperty("user.dir") + "/src/test/resources/JsonData/ExecuteJob.json";
    int jobActionIDValue = getQueryParamFromJsonFile(getJobStatusJson, jobActionID);


    public StateReportJobTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups = {"PACCDataLink"})
    public void getJobStatusByJobActionID() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetJobStatusByJobActionID + jobActionIDValue)
                .then().spec(responseSpecificationForStatusCode()).extract().response();

        log.info("Request hit successfully and response is received for getting job action ID");

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");
        log.info("Status code is " + response.getStatusCode());
    }

    @Test(groups = {"PACCDataLink"})
    public void executeJob() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .body(Files.readAllBytes(Paths.get(executeJobByParameterJson)))
                .when().post(b.resourceExecuteJob)
                .then().spec(responseSpecificationForStatusCode()).spec(responseSpecificationForID(jobActionID)).extract().response();

        log.info("Request hit successfully and response is received for executing Job..");
        log.info("The added JobActionID is " + getJsonPath(response, jobActionID));

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("Status code is " + response.getStatusCode());
    }
}
