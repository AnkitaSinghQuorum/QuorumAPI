package Test;

import Services.BaseEndpoint;
import Services.PACCDataLinkEndpoint;
import Utils.UtilityFile;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CompanyTestPACCDataLink extends PACCDataLinkEndpoint {

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
    LoginTestGRAPIServices login = new LoginTestGRAPIServices();
    BaseEndpoint b = new BaseEndpoint();
    Response response;

    String bearerTokenGRAPIServices = login.generateAccessTokenGRAPIServices();

    public CompanyTestPACCDataLink() throws IOException, ParseException {
    }

    @Test(groups ={"PACCDataLink"})
    public void getPACCCompany() throws IOException, ParseException {

        response = given().spec(requestSpecification()).header("Authorization", "Bearer " + bearerTokenGRAPIServices)
                .when().get(b.resourceGetCompany)
                .then().spec(responseSpecificationForStatusCode()).extract().response();


        log.info("Request hit successfully and response is received for get PACC company.");

        log.info(response.asPrettyString());
        log.info("Response json converted to String successfully.");

        log.info("CompanyID extracted from response is "+ getJsonPath(response, "CompanyID"));

        log.info("Status code is " + response.getStatusCode());
    }

//    @Test(groups ={"PACCDataLink"})
//    public void addNewPACCCompany(){
//
//    }
}
