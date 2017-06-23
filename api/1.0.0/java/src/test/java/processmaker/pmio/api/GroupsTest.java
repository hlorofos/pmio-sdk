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

import org.junit.*;
import processmaker.pmio.ApiException;
import processmaker.pmio.api.initalizer.ApiInitalizer;
import processmaker.pmio.model.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * API tests for Groups
 */
public class GroupsTest {

    private final Groups api = new Groups();
    private Users userApi = new Users();
    private UserItem user;
    private List<String> createdGroups = new LinkedList<>();

    @BeforeClass
    public static void init(){
        ApiInitalizer.initApi();
    }

    @Before
    public void createUser() throws ApiException {
        UserAttributes userAttributes = new UserAttributes();
        userAttributes.setUsername("testUser");
        userAttributes.setPassword("123");
        userAttributes.setLastname("testLast");
        userAttributes.setFirstname("testFirst");
        userAttributes.setEmail("testEmail@email.com");

        User user = new User();
        user.setAttributes(userAttributes);
        UserCreateItem userCreateItem = new UserCreateItem();
        userCreateItem.setData(user);
        this.user = userApi.addUser(userCreateItem);
    }

    @After
    public void clear() throws ApiException {
        userApi.deleteUser(user.getData().getId());
        for(String groupId : createdGroups){
            api.deleteGroup(groupId);
        }
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
        GroupCreateItem groupCreateItem = new GroupCreateItem();
        Group group = new Group();
        GroupAttributes groupAttributes = new GroupAttributes();
        groupAttributes.setDescription("mySuperGroup");
        groupAttributes.setTitle("SuperGroup");
        groupAttributes.setStatus(GroupAttributes.StatusEnum.ACTIVE);
        groupAttributes.setCode("A3");
        group.setAttributes(groupAttributes);
        groupCreateItem.setData(group);

        GroupItem response = api.addGroup(groupCreateItem);
        createdGroups.add(response.getData().getId());
        assertNotNull(response.getData().getId());
        assertEquals(response.getData().getAttributes().getCode(), "A3");
        assertNotNull(response.getData().getAttributes().getCreatedAt());
        assertEquals(response.getData().getAttributes().getDescription(), "mySuperGroup");
        assertEquals(response.getData().getAttributes().getStatus(), GroupAttributes.StatusEnum.ACTIVE);
        assertEquals(response.getData().getAttributes().getTitle(), "SuperGroup");
        assertNotNull(response.getData().getAttributes().getUpdatedAt());
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
        GroupCreateItem groupCreateItem = new GroupCreateItem();
        Group group = new Group();
        GroupAttributes groupAttributes = new GroupAttributes();
        groupAttributes.setDescription("mySuperGroup");
        groupAttributes.setTitle("SuperGroup");
        groupAttributes.setStatus(GroupAttributes.StatusEnum.ACTIVE);
        groupAttributes.setCode("A3");
        group.setAttributes(groupAttributes);
        groupCreateItem.setData(group);

        GroupItem groupItem = api.addGroup(groupCreateItem);
        createdGroups.add(groupItem.getData().getId());

        GroupAddUsersItem groupAddUsersItem = new GroupAddUsersItem();
        UserIds userIds = new UserIds();
        userIds.addUsersItem(user.getData().getId());
        groupAddUsersItem.setData(userIds);
        ResultSuccess response = api.addUsersToGroup(groupItem.getData().getId(), groupAddUsersItem);

        System.out.println(response.getMeta().getCode());
        System.out.println(response.getMeta().getTitle());
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
    
}
