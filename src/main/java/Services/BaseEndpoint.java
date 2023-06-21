package Services;

public class BaseEndpoint {

    //for individual get, update, delete, post url
    // we can declare all the baseurl+resources;
    //other modules SIte settings etc

    // AWS Non Prod
    public String base_IdentityServerUrl = "http://10.18.5.149/GRAPIServices.IdentityServer_deploy";
    public String base_P2PApiUrl = "http://10.18.5.149/GRAPIServices.Peer2Peer_deploy";
    public String ClientId = "099153c2625149bc8ecb3e85e03f0022";
    public String resource_LoginAndGetJWT = "/grsecurity/token";
    public String resource_GetIndividuals = "/api/Individuals/";

}

