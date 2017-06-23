/**
 * ProcessMaker API
 * # Introduction  This ProcessMaker I/O API provides the access to a BPMN 2.0 compliant workflow engine API that is designed to be used as a microservice to support enterprise cloud applications.  The current Alpha 1.0 version supports most of the descriptive class of the BPMN 2.0 specification.    You can use your favorite HTTP/REST library for your programming language to use PMIO API or you can use one of our SDKs:  Language | GitHub Link | Download Link  --- | --- | ---  JAVA | [JAVA SDK](https://github.com/ProcessMaker/pmio-sdk-java) | [Download JAVA SDK](https://github.com/ProcessMaker/pmio-sdk-java/archive/master.zip)  JavaScript | [JavaScript SDK](https://github.com/ProcessMaker/pmio-sdk-javascript) | [Download JavaScript SDK](https://github.com/ProcessMaker/pmio-sdk-javascript/archive/master.zip)  Perl | [Perl SDK](https://github.com/ProcessMaker/pmio-sdk-perl) | [Download Perl SDK](https://github.com/ProcessMaker/pmio-sdk-perl/archive/master.zip)  C++ | [C++ SDK](https://github.com/ProcessMaker/pmio-sdk-cpprest) | [Download C++ SDK](https://github.com/ProcessMaker/pmio-sdk-cpprest/archive/master.zip)  PHP | [PHP SDK](https://github.com/ProcessMaker/pmio-sdk-php) | [Download PHP SDK](https://github.com/ProcessMaker/pmio-sdk-php/archive/master.zip)  C# | [C#](https://github.com/ProcessMaker/pmio-sdk-csharp) | [Download C# SDK](https://github.com/ProcessMaker/pmio-sdk-csharp/archive/master.zip)  Python | [Python](https://github.com/ProcessMaker/pmio-sdk-python) | [Download Python SDK](https://github.com/ProcessMaker/pmio-sdk-python/archive/master.zip) # How to create a new user  Use [addUser](#operation/addUser) API call to create a User. Oauth client and its `client_id` will be returned back along with the User details  ## Retrieving client_secret You may retrieve `client_secret` for the User via [findOauthClientById](#operation/findOauthClientById) API call  ## Getting authorization key  Having both `client_id` and `client_secret` you may retrieve `access_token` and `refresh_token` using password grant. Additionally username and password are required to perform the operation.  ### PHP Sample to retrieve Oauth tokens  ```php    $args_for_bob = [      'grant_type' => 'password',      'client_id' => $bobCredentials->getData()->getId(),      'client_secret' => $bobCredentials->getData()->getAttributes()->getSecret(),      'username' => $bobAttr->getUsername(),      'password' => $bobAttr->getPassword()  ];    print_r(getCredentials($args_for_bob, $host));    /_**   * @param array $args Oauth request data   * @param string $host API HOST   * @return mixed   *_/  function getCredentials($args, $host)  {      $ch = curl_init();      curl_setopt($ch, CURLOPT_URL, \"https://$host/oauth/access_token\");      curl_setopt($ch, CURLOPT_POST, 1);      curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($args));      curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);      $serverResponse = json_decode(curl_exec($ch));      curl_close($ch);      return $serverResponse;  }  ```    Here you will get `access_token` and `refresh_token` to perform Oauth authorization for specific user. # How to import BPMN file    The following API call will allow you to import BPMN file: [importBpmnFile](#operation/importBpmnFile). Resulting variable $process will contain an array of created Process(es) objects. # How to create and launch a new Process    Use [addProcess](#operation/addProcess) API call to create a new Process. As result you will get `process_id`, which can be used to add objects to the Process.    ## How to assign User to Group    You may want to delegate Tasks not just to a User, but a Group of Users.   Use [addUsersToGroup](#operation/addUsersToGroup) API call to add some Users to the Group.    ## How to add objects to Process    Also we should add objects to our process, such as Start event and End event.  (use [addEvent](#operation/addEvent) API call to add these), and at least one Task object (use [addTask](#operation/addTask) API call).    ## How to add flows between process objects    All objects in Process need to be joined by SEQUENTIAL Flows. Use [addFlow](#operation/addFlow) API call to connect the objects with each other.    ## How to delegate Group of Users to Task    Whe you have `process_id`, `task_id` and `group_id` you can assign Group as delegate for User Task with the following API method: [AddGroupsToTask](#operation/addGroupsToTask).    ## How to run process    To run process we just need to trigger Start event object by using [eventTrigger](#operation/eventTrigger) API call. Just pass `process_id`, Start Event `event_id`, and an input data we need for the Process as Data model attributes. Content of Data Model can be associative array keys and values.    As result, our engine creates Process instance with status **RUNNING**. To get all Process Instances belonging to Process you can retrieve using [findInstances](#operation/findInstances) API call. # Cross-Origin Resource Sharing This API features Cross-Origin Resource Sharing (CORS) implemented in compliance with  [W3C spec](https://www.w3.org/TR/cors/). And that allows cross-domain communication from the browser. All responses have a wildcard same-origin which makes them completely public and accessible to everyone, including any code on any site. # Authentication PM.IO offers OAuth2 authentication.  OAuth2 - an open protocol to allow secure authorization in a simple and standard method from web, mobile and desktop applications. <!-- ReDoc-Inject: <security-definitions> --> 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: support@processmaker.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package processmaker.pmio.api;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import processmaker.pmio.ApiException;
import processmaker.pmio.api.initalizer.ApiInitalizer;
import processmaker.pmio.model.*;
import org.junit.Test;
import processmaker.pmio.model.Process;
import processmaker.pmio.model.Task;
import sun.misc.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * API tests for Client
 */
public class ClientTest {

    private final Client api = new Client();
    private List<String> ids = new LinkedList<>();

    @BeforeClass
    public static void init(){
        ApiInitalizer.initApi();
    }

    @After
    public void clear() throws ApiException {
        for(String id :ids){
            api.deleteProcess(id);
        }
    }
    /**
     * 
     *
     * This method creates the new event.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addEventTest() throws ApiException {
        String processId = null;
        EventCreateItem eventCreateItem = null;
        // EventItem response = api.addEvent(processId, eventCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method is intended for creating a new Event connector.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addEventConnectorTest() throws ApiException {
        String processId = null;
        String eventId = null;
        EventConnectorCreateItem eventConnectorCreateItem = null;
        // EventConnector1 response = api.addEventConnector(processId, eventId, eventConnectorCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method creates a new Sequence flow
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addFlowTest() throws ApiException {
        String processId = null;
        FlowCreateItem flowCreateItem = null;
        // FlowItem response = api.addFlow(processId, flowCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method creates a new gateway.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addGatewayTest() throws ApiException {
        String processId = null;
        GatewayCreateItem gatewayCreateItem = null;
        // GatewayItem response = api.addGateway(processId, gatewayCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method creates a new group.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addGroupTest() throws ApiException {
        GroupCreateItem groupCreateItem = null;
        // GroupItem response = api.addGroup(groupCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method assigns group(s) to the choosen task
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addGroupsToTaskTest() throws ApiException {
        String processId = null;
        String taskId = null;
        TaskAddGroupsItem taskAddGroupsItem = null;
        // ResultSuccess response = api.addGroupsToTask(processId, taskId, taskAddGroupsItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method creates a new Input/Output object
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addInputOutputTest() throws ApiException {
        String processId = null;
        String taskId = null;
        InputOutputCreateItem inputOutputCreateItem = null;
        // InputOutputItem response = api.addInputOutput(processId, taskId, inputOutputCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method creates a new instance.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addInstanceTest() throws ApiException {
        String processId = null;
        InstanceCreateItem instanceCreateItem = null;
        // InstanceItem response = api.addInstance(processId, instanceCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method creates a new Oauth client for the user
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addOauthClientTest() throws ApiException {
        String userId = null;
        OauthClientCreateItem oauthClientCreateItem = null;
        // OauthClientItem response = api.addOauthClient(userId, oauthClientCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method creates a new process
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addProcessTest() throws ApiException {
        ProcessItem response = addProcessItem("ProcessName");
        ids.add(response.getData().getId());
        Assert.assertNotNull(response.getData().getId());
        Assert.assertEquals(response.getData().getAttributes().getName(), "ProcessName");
    }

    private ProcessItem addProcessItem(String name) throws ApiException {
        ProcessCreateItem processCreateItem = new ProcessCreateItem();
        Process process = new Process();
        ProcessAttributes processAttributes = new ProcessAttributes();
        processAttributes.setStatus(ProcessAttributes.StatusEnum.ACTIVE);
        processAttributes.setName(name);
        processAttributes.setDurationBy(ProcessAttributes.DurationByEnum.WORKING_DAYS);
        processAttributes.setType(ProcessAttributes.TypeEnum.NORMAL);
        processAttributes.setDesignAccess(ProcessAttributes.DesignAccessEnum.PUBLIC);
        process.setAttributes(processAttributes);
        processCreateItem.setData(process);
        return api.addProcess(processCreateItem);
    }

    /**
     * 
     *
     * This method creates a new task.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addTaskTest() throws ApiException {
        String processId = addProcessItem("ProcessForTask").getData().getId();
        TaskCreateItem taskCreateItem = new TaskCreateItem();
        Task task = new Task();
        TaskAttributes attributes = new TaskAttributes();
        attributes.setName("Name");
        attributes.setType(TaskAttributes.TypeEnum.USER_TASK);
        attributes.setProcessId(processId);
        attributes.setAssignType(TaskAttributes.AssignTypeEnum.CYCLIC);
        attributes.setTransferFly(true);
        attributes.setCanUpload(true);
        attributes.setViewUpload(true);
        attributes.setViewAdditionalDocumentation(true);
        attributes.setStart(false);
        attributes.setSendLastEmail(true);
        attributes.setSelfserviceTimeout(10);
        task.setAttributes(attributes);
        taskCreateItem.setData(task);
        TaskItem response = api.addTask(processId, taskCreateItem);

        Assert.assertNotNull(response.getData().getId());
        Assert.assertEquals("Name", response.getData().getAttributes().getName());
    }
    
    /**
     * 
     *
     * This method is intended for creating a new task connector.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addTaskConnectorTest() throws ApiException {
        String processId = null;
        String taskId = null;
        TaskConnectorCreateItem taskConnectorCreateItem = null;
        // TaskConnector1 response = api.addTaskConnector(processId, taskId, taskConnectorCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method creates a new user in the system. From the result you may retrieve &#x60;client_id&#x60;  This &#x60;client_id&#x60; required to obtain &#x60;client_secret&#x60; and then you will be able to perform Oauth authorization key. Refer to [Oauth Client APIs](#tag/oauth)
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addUserTest() throws ApiException {
        UserCreateItem userCreateItem = null;
        // UserItem response = api.addUser(userCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method adds one or more new users to a group.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addUsersToGroupTest() throws ApiException {
        String id = null;
        GroupAddUsersItem groupAddUsersItem = null;
        // ResultSuccess response = api.addUsersToGroup(id, groupAddUsersItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method deletes an event using the event ID and process ID
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteEventTest() throws ApiException {
        String processId = null;
        String eventId = null;
        // ResultSuccess response = api.deleteEvent(processId, eventId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method is intended for deleting a single Event connector based on Event ID, Process ID and Connector ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteEventConnectorTest() throws ApiException {
        String processId = null;
        String eventId = null;
        String connectorId = null;
        // ResultSuccess response = api.deleteEventConnector(processId, eventId, connectorId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method deletes a sequence flow using the flow ID and process ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteFlowTest() throws ApiException {
        String processId = null;
        String flowId = null;
        // ResultSuccess response = api.deleteFlow(processId, flowId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method is deletes a single item using the gateway ID and process ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteGatewayTest() throws ApiException {
        String processId = null;
        String gatewayId = null;
        // ResultSuccess response = api.deleteGateway(processId, gatewayId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method deletes a group using the group ID
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteGroupTest() throws ApiException {
        String id = null;
        // ResultSuccess response = api.deleteGroup(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method deletes the Input/Output based on the Input/Output ID, process ID and task ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteInputOutputTest() throws ApiException {
        String processId = null;
        String taskId = null;
        String inputoutputUid = null;
        // ResultSuccess response = api.deleteInputOutput(processId, taskId, inputoutputUid);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method deletes an instance using the instance ID and process ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteInstanceTest() throws ApiException {
        String processId = null;
        String instanceId = null;
        // ResultSuccess response = api.deleteInstance(processId, instanceId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method deletes an Oauth client using the Oauth client and user IDs.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteOauthClientTest() throws ApiException {
        String userId = null;
        String clientId = null;
        // ResultSuccess response = api.deleteOauthClient(userId, clientId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method deletes a process using the process ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteProcessTest() throws ApiException {
        ProcessItem process = addProcessItem("Process");
        ResultSuccess response = api.deleteProcess(process.getData().getId());
        Assert.assertEquals("1202", response.getMeta().getCode());
    }
    
    /**
     * 
     *
     * This method deletes a task using the task ID and process ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteTaskTest() throws ApiException {
        String processId = null;
        String taskId = null;
        // ResultSuccess response = api.deleteTask(processId, taskId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method is intended for deleting a single Task connector based on Task ID, Process ID and Connector ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteTaskConnectorTest() throws ApiException {
        String processId = null;
        String taskId = null;
        String connectorId = null;
        // ResultSuccess response = api.deleteTaskConnector(processId, taskId, connectorId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method deletes a user from the system.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteUserTest() throws ApiException {
        String id = null;
        // ResultSuccess response = api.deleteUser(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method starts/triggers an event.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventTriggerTest() throws ApiException {
        String processId = null;
        String eventId = null;
        TriggerEventCreateItem triggerEventCreateItem = null;
        // DataModelItem1 response = api.eventTrigger(processId, eventId, triggerEventCreateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This webhook method triggers given Event object.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void eventWebhookTest() throws ApiException {
        String processId = null;
        String eventId = null;
        String triggerBody = null;
        // String response = api.eventWebhook(processId, eventId, triggerBody);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method returns the instance DataModel and lets the user work with it directly
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findDataModelTest() throws ApiException {
        String processId = null;
        String instanceId = null;
        Integer page = null;
        Integer perPage = null;
        // DataModelItem1 response = api.findDataModel(processId, instanceId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves an event using its ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findEventByIdTest() throws ApiException {
        String processId = null;
        String eventId = null;
        // EventItem response = api.findEventById(processId, eventId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method returns all Event connectors related to the run Process and Event.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findEventConnectorByIdTest() throws ApiException {
        String processId = null;
        String eventId = null;
        String connectorId = null;
        // EventConnector1 response = api.findEventConnectorById(processId, eventId, connectorId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method returns all Event connectors related to the run Process and Event.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findEventConnectorsTest() throws ApiException {
        String processId = null;
        String eventId = null;
        Integer page = null;
        Integer perPage = null;
        // EventConnectorsCollection response = api.findEventConnectors(processId, eventId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method returns all events related to the runned process
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findEventsTest() throws ApiException {
        String processId = null;
        Integer page = null;
        Integer perPage = null;
        // EventCollection response = api.findEvents(processId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves a flow based on its ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findFlowByIdTest() throws ApiException {
        String processId = null;
        String flowId = null;
        // FlowItem response = api.findFlowById(processId, flowId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves all existing flows.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findFlowsTest() throws ApiException {
        String processId = null;
        Integer page = null;
        Integer perPage = null;
        // FlowCollection response = api.findFlows(processId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves a gateway based on its ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findGatewayByIdTest() throws ApiException {
        String processId = null;
        String gatewayId = null;
        // GatewayItem response = api.findGatewayById(processId, gatewayId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves all existing gateways.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findGatewaysTest() throws ApiException {
        String processId = null;
        Integer page = null;
        Integer perPage = null;
        // GatewayCollection response = api.findGateways(processId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves a group using its ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findGroupByIdTest() throws ApiException {
        String id = null;
        // GroupItem response = api.findGroupById(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves all existing groups.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findGroupsTest() throws ApiException {
        Integer page = null;
        Integer perPage = null;
        // GroupCollection response = api.findGroups(page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves an Input/Output object using its ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findInputOutputByIdTest() throws ApiException {
        String processId = null;
        String taskId = null;
        String inputoutputUid = null;
        // InputOutputItem response = api.findInputOutputById(processId, taskId, inputoutputUid);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves all existing Input/Output objects in the related task instance.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findInputOutputsTest() throws ApiException {
        String processId = null;
        String taskId = null;
        Integer page = null;
        Integer perPage = null;
        // InputOutputCollection response = api.findInputOutputs(processId, taskId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves an instance using its ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findInstanceByIdTest() throws ApiException {
        String processId = null;
        String instanceId = null;
        // InstanceItem response = api.findInstanceById(processId, instanceId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves related to the process using  the Process ID
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findInstancesTest() throws ApiException {
        String processId = null;
        Integer page = null;
        Integer perPage = null;
        // InstanceCollection response = api.findInstances(processId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method is retrieves an Oauth client for the User based on its ID.  Response contains &#x60;client_secret&#x60; required to obtain &#x60;access_token&#x60;.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findOauthClientByIdTest() throws ApiException {
        String userId = null;
        String clientId = null;
        // OauthClientItem response = api.findOauthClientById(userId, clientId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves all existing Oauth clients belonging to an user.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findOauthClientsTest() throws ApiException {
        String userId = null;
        Integer page = null;
        Integer perPage = null;
        // OauthClientCollection response = api.findOauthClients(userId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves a process using its ID
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findProcessByIdTest() throws ApiException {
        String id = null;
        // ProcessItem response = api.findProcessById(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves all existing processes.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findProcessesTest() throws ApiException, IOException {
        addProcessItem("ProcessName1");
        addProcessItem("ProcessName2");
        addProcessItem("ProcessName3");
        addProcessItem("ProcessName4");
        ProcessCollection response = api.findProcesses(1, 3);
        Assert.assertTrue(response.getData().size() > 0);
    }
    
    /**
     * 
     *
     * This method is retrieves a task using its ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findTaskByIdTest() throws ApiException {
        String processId = null;
        String taskId = null;
        // TaskItem response = api.findTaskById(processId, taskId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method is intended for retrieving an Task connector based on it&#39;s ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findTaskConnectorByIdTest() throws ApiException {
        String processId = null;
        String taskId = null;
        String connectorId = null;
        // TaskConnector1 response = api.findTaskConnectorById(processId, taskId, connectorId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method returns all Task connectors related to the run Process and Task.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findTaskConnectorsTest() throws ApiException {
        String processId = null;
        String taskId = null;
        Integer page = null;
        Integer perPage = null;
        // TaskConnectorsCollection response = api.findTaskConnectors(processId, taskId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves a task instance based on its ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findTaskInstanceByIdTest() throws ApiException {
        String taskInstanceId = null;
        Integer page = null;
        Integer perPage = null;
        // InlineResponse200 response = api.findTaskInstanceById(taskInstanceId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method retrieves all existing task instances
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findTaskInstancesTest() throws ApiException {
        Integer page = null;
        Integer perPage = null;
        // TaskInstanceCollection response = api.findTaskInstances(page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method is intended for returning a list of all Tasks related to the process
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findTasksTest() throws ApiException {
        String processId = null;
        Integer page = null;
        Integer perPage = null;
        // TaskCollection response = api.findTasks(processId, page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method returns a user using its ID.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findUserByIdTest() throws ApiException {
        String id = null;
        // UserItem response = api.findUserById(id);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method returns all existing users in the system.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findUsersTest() throws ApiException {
        Integer page = null;
        Integer perPage = null;
        // UserCollection response = api.findUsers(page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method imports BPMN 2.0 files. A new process(es) is/are created and its object returned back when import is successful.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void importBpmnFileTest() throws ApiException, IOException {
        BpmnImportItem bpmnImportItem = new BpmnImportItem();
        BpmnFile bpmnFile = new BpmnFile();
        BpmnFileAttributes attributes = new BpmnFileAttributes();
        String filePath = "src/test/resources/message_startevent.bpmn";
        byte[] fileData = IOUtils.readFully(new FileInputStream(filePath), -1, false);
        attributes.setBpmn(new String(fileData));
        bpmnFile.setAttributes(attributes);
        bpmnImportItem.setData(bpmnFile);
        ProcessCollection1 response = api.importBpmnFile(bpmnImportItem);
        for(Process process : response.getData()){
            ids.add(process.getId());
        }
        Assert.assertEquals(2, response.getData().size());
        Assert.assertNotNull(response.getMeta());
    }
    
    /**
     * 
     *
     * This method returns user information using a token
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void myselfUserTest() throws ApiException {
        Integer page = null;
        Integer perPage = null;
        // UserItem response = api.myselfUser(page, perPage);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method removes groups from a task
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeGroupsFromTaskTest() throws ApiException {
        String processId = null;
        String taskId = null;
        TaskRemoveGroupsItem taskRemoveGroupsItem = null;
        // ResultSuccess response = api.removeGroupsFromTask(processId, taskId, taskRemoveGroupsItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method removes one or more users from a group.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeUsersFromGroupTest() throws ApiException {
        String id = null;
        GroupRemoveUsersItem groupRemoveUsersItem = null;
        // ResultSuccess response = api.removeUsersFromGroup(id, groupRemoveUsersItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method synchronizes a one or more groups with a task.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void syncGroupsToTaskTest() throws ApiException {
        String processId = null;
        String taskId = null;
        TaskSyncGroupsItem taskSyncGroupsItem = null;
        // ResultSuccess response = api.syncGroupsToTask(processId, taskId, taskSyncGroupsItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method synchronizes one or more users with a group.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void syncUsersToGroupTest() throws ApiException {
        String id = null;
        GroupSyncUsersItem groupSyncUsersItem = null;
        // ResultSuccess response = api.syncUsersToGroup(id, groupSyncUsersItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method updates an existing event
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateEventTest() throws ApiException {
        String processId = null;
        String eventId = null;
        EventUpdateItem eventUpdateItem = null;
        // EventItem response = api.updateEvent(processId, eventId, eventUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method lets update the existing Event connector with new parameters values
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateEventConnectorTest() throws ApiException {
        String processId = null;
        String eventId = null;
        String connectorId = null;
        EventConnectorUpdateItem eventConnectorUpdateItem = null;
        // EventConnector1 response = api.updateEventConnector(processId, eventId, connectorId, eventConnectorUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method updates an existing flow.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateFlowTest() throws ApiException {
        String processId = null;
        String flowId = null;
        FlowUpdateItem flowUpdateItem = null;
        // FlowItem response = api.updateFlow(processId, flowId, flowUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method updates an existing gateway.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateGatewayTest() throws ApiException {
        String processId = null;
        String gatewayId = null;
        GatewayUpdateItem gatewayUpdateItem = null;
        // GatewayItem response = api.updateGateway(processId, gatewayId, gatewayUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method updates an existing group.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateGroupTest() throws ApiException {
        String id = null;
        GroupUpdateItem groupUpdateItem = null;
        // GroupItem response = api.updateGroup(id, groupUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method updates an existing Input/Output object.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateInputOutputTest() throws ApiException {
        String processId = null;
        String taskId = null;
        String inputoutputUid = null;
        InputOutputUpdateItem inputOutputUpdateItem = null;
        // InputOutputItem response = api.updateInputOutput(processId, taskId, inputoutputUid, inputOutputUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method updates  an existing instance.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateInstanceTest() throws ApiException {
        String processId = null;
        String instanceId = null;
        InstanceUpdateItem instanceUpdateItem = null;
        // InstanceItem response = api.updateInstance(processId, instanceId, instanceUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method updates an existing Oauth client.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateOauthClientTest() throws ApiException {
        String userId = null;
        String clientId = null;
        OauthClientUpdateItem oauthClientUpdateItem = null;
        // OauthClientItem response = api.updateOauthClient(userId, clientId, oauthClientUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method updates an existing process.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateProcessTest() throws ApiException {
        String id = null;
        ProcessUpdateItem processUpdateItem = null;
        // ProcessItem response = api.updateProcess(id, processUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method is intended for updating an existing task.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateTaskTest() throws ApiException {
        String processId = null;
        String taskId = null;
        TaskUpdateItem taskUpdateItem = null;
        // TaskItem response = api.updateTask(processId, taskId, taskUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method lets update the existing Task connector with new parameters values
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateTaskConnectorTest() throws ApiException {
        String processId = null;
        String taskId = null;
        String connectorId = null;
        TaskConnectorUpdateItem taskConnectorUpdateItem = null;
        // TaskConnector1 response = api.updateTaskConnector(processId, taskId, connectorId, taskConnectorUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method updates an existing task instance.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateTaskInstanceTest() throws ApiException {
        String taskInstanceId = null;
        TaskInstanceUpdateItem taskInstanceUpdateItem = null;
        // InlineResponse200 response = api.updateTaskInstance(taskInstanceId, taskInstanceUpdateItem);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * This method updates an existing user.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateUserTest() throws ApiException {
        String id = null;
        UserUpdateItem userUpdateItem = null;
        // UserItem response = api.updateUser(id, userUpdateItem);

        // TODO: test validations
    }
    
}
