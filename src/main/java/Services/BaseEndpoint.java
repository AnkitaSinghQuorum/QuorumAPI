package Services;

public class BaseEndpoint {

    //for individual get, update, delete, post url
    // we can declare all the baseurl+resources;
    //other modules SIte settings etc

    // AWS Non Prod for P2P
    public String baseIdentityServerUrl = "http://10.18.5.149/GRAPIServices.IdentityServer_deploy";
    public String baseP2PApiUrl = "http://10.18.5.149/GRAPIServices.Peer2Peer_deploy";
    public String ClientId = "099153c2625149bc8ecb3e85e03f0022";
    public String resourceLoginAndGetJWT = "/grsecurity/token";
    public String resourceGetIndividuals = "/api/Individuals/";

    // GRAPIServices.PACCDataLink
    public String basePACCDataLinkUrl = "http://10.18.5.149/GRAPIServices.PACCDataLink_deploy";

    // GRAPIServices.PACCDataLink Company
    public String resourceGetCompany = "/api/datalink/getcompany";
    public String resourceAddCompany = "/api/datalink/addcompany";

    // GRAPIServices.PACCDataLink Organization
    public String resourceGetPACCOrganizations ="/api/datalink/organization/get";
    public String resourceAddNewPACCOrganization ="/api/datalink/organization/add";
    public String resourceUpdatePACCOrganization ="/api/datalink/organization/update";
    public String resourceDeletePACCOrganization ="/api/datalink/organization/delete";

    // GRAPIServices.PACCDataLink PAC
    public String resourceGetListOfPACs = "/api/datalink/pac/getall";
    public String resourceGetSinglePAC = "/api/datalink/pac/get/";
    public String resourceAddNewPACCPAC = "/api/datalink/pac/add";
    public String resourceUpdatePACCPAC = "/api/datalink/pac/update";
    public String resourceDeletePACCPAC = "/api/datalink/pac/delete";

    // GRAPIServices.PACCDataLink PACAccount
    public String resourceGetListOfPACAccounts = "/api/datalink/pacaccount/getall";
    public String resourceGetSinglePACAccount = "/api/datalink/pacaccount/get/";
    public String resourceAddSinglePACAccount = "/api/datalink/pacaccount/add";
    public String resourceUpdatePACAccount = "/api/datalink/pacaccount/edit";

    // GRAPIServices.PACCDataLink PACReportMaster
    public String resourceGetListOfPACReportMasterRecords = "/api/pacreportmaster/pac/getall";
    public String resourceGetSinglePACReportMasterInstance = "/api/pacreportmaster/pac/get/{id}";


}

