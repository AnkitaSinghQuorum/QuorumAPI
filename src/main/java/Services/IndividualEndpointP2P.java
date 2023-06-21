package Services;

import Utils.UtilityFile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

public class IndividualEndpointP2P extends UtilityFile {

    // created for perform CRUD operation on Individual for P2P module

    private static Logger log = UtilityFile.getLogger(UtilityFile.class);
        public static RequestSpecification req;
        public RequestSpecification requestSpecification(){
        BaseEndpoint b = new BaseEndpoint();
            if(req==null){
                req = new RequestSpecBuilder().setBaseUri(b.baseP2PApiUrl).setContentType(ContentType.JSON).build();
                log.info("Request Specification ran successfully.");
                return req;
            }
            return req;
        }

}
