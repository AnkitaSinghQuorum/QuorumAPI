package DataSource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class LoginGRAPIServices {

    private String grant_type;
    private String client_id;
    private String secret;
    private String companyId;
    private JSONObject jsonObject;
    private String path;
    private Object obj;

    public JSONObject getJsonObject() {
        jsonObject = (JSONObject) obj;
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Object getParser() throws IOException, ParseException {
        obj = new JSONParser().parse(new FileReader(path));
        return obj;
    }

    public void setParser(String path) {
        this.path = path;
    }

    public String getGrant_type() {
        grant_type = (String) jsonObject.get("grant_type");
        return grant_type;
    }

    public String getClient_id() {
        client_id = (String) jsonObject.get("client_id");
        return client_id;
    }

    public String getSecret() {
        secret = (String) jsonObject.get("secret");
        return secret;
    }

    public String getCompanyID() {
        companyId = (String) jsonObject.get("CompanyID");
        return companyId;
    }


}
