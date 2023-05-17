package Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;


public class GenerateToken {

    @Test
    public void AuthenticateUser()

    {
        RestAssured.baseURI = "http://bookstore.toolsqa.com";
        RequestSpecification request = RestAssured.given();
        String payload =  "{\r\n" +
                "  \"userName\": \"TOOLSQA-Test\",\r\n" +
                "  \"password\": \"Test@@123\"\r\n" +
                "}";;

        request.header("ContentType", "application.json");
      Response response= request.body(payload).post("/Account/v1/GenerateToken");
        System.out.println(response);
    }

}
