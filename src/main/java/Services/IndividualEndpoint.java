package Services;

import Utils.UtilityFile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

public class IndividualEndpoint extends UtilityFile {

    // created for perform CRUD operation on Individual module

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
        public static RequestSpecification req;
        public RequestSpecification requestSpecification(){
        BaseEndpoint b = new BaseEndpoint();
            if(req==null){
                req = new RequestSpecBuilder().setBaseUri(b.base_P2PApiUrl).setContentType(ContentType.JSON).build();
                log.info("Request Specification ran successfully.");
                return req;
            }
            return req;
        }

//        int individualId = js.getInt("Id[2]");
//        return individualId;
//
//    }
//    @Test
//    public static void getIndividualDetails()
//    {
//        int id = GetIndividuals.getIndividuals();
//        System.out.println("Individual id is "+id);
//        String bearerToken = LoginAndGenerateAccessToken.generateAccessToken();
//
//        RestAssured.baseURI = "http://10.18.5.149/GRAPIServices.Peer2Peer_deploy";
//        String individualDetails = given().log().all().header("Authorization", "Bearer "+bearerToken)
//                .when().get("/api/Individuals/"+id)
//                .then().assertThat().statusCode(200).extract().response().asString();
//        System.out.println(individualDetails);
//
//        JsonPath js = new JsonPath(individualDetails);
//
//        Assert.assertEquals(id,js.getInt("Id"));
//    }
}
