package DataSource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class LoginP2P {

    private String username;
    private String password;
    private String grant_type;
    private String site;
    private String client_id;
    private JSONObject jsonObject;
    private String path;
    private Object obj;

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        jsonObject = (JSONObject) obj;
        return jsonObject;
    }
    public Object getParser() throws IOException, ParseException {
        obj = new JSONParser().parse(new FileReader(path));
        return obj;
    }

    public void setParser(String path) {
        this.path = path;
    }

    public String getUsername() {
        username = (String) jsonObject.get("username");
        return username;
    }

    public String getPassword() {
         password = (String) jsonObject.get("password");
         return password;
    }

    public String getGrant_type() {
        grant_type = (String) jsonObject.get("grant_type");
        return grant_type;
    }

    public String getSite() {
        site = (String) jsonObject.get("site");
        return site;
    }

    public String getClient_id() {
        client_id = (String) jsonObject.get("client_id");
        return client_id;
    }








}
