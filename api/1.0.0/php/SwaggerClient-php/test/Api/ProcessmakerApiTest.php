<?php
/**
 * ProcessmakerApiTest
 * PHP version 5
 *
 * @category Class
 * @package  Swagger\Client
 * @author   http://github.com/swagger-api/swagger-codegen
 * @license  http://www.apache.org/licenses/LICENSE-2.0 Apache Licene v2
 * @link     https://github.com/swagger-api/swagger-codegen
 */

/**
 * ProcessMaker API
 *
 * An API to access ProcessMaker core functionality
 *
 * OpenAPI spec version: 1.0.0
 * Contact: oleg@processmaker.com
 * Generated by: https://github.com/swagger-api/swagger-codegen.git
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * Please update the test case below to test the endpoint.
 */

namespace Swagger\Client;

use Swagger\Client\Api\ProcessmakerApi;
use Swagger\Client\Model\Error;
use Swagger\Client\Model\Group;
use Swagger\Client\Model\GroupAddUsersItem;
use Swagger\Client\Model\GroupAttributes;
use Swagger\Client\Model\GroupCreateItem;
use Swagger\Client\Model\GroupItem;
use Swagger\Client\Model\GroupRemoveUsersItem;
use Swagger\Client\Model\InstanceUpdateItem;
use Swagger\Client\Model\MetaResult;
use Swagger\Client\Model\ResultSuccess;
use Swagger\Client\Model\User;
use Swagger\Client\Model\UserAttributes;
use Swagger\Client\Model\UserCreateItem;
use Swagger\Client\Model\UserIds;
use Swagger\Client\Model\UserItem;
use Swagger\Client\Model\Process;
use Swagger\Client\Model\ProcessCreateItem;
use Swagger\Client\Model\ProcessItem;
use Swagger\Client\Model\ProcessAttributes;
use Swagger\Client\Model\Task;
use Swagger\Client\Model\TaskCreateItem;
use Swagger\Client\Model\TaskAttributes;
use Swagger\Client\Model\TaskAddGroupsItem;
use Swagger\Client\Model\TaskSyncGroupsItem;
use Swagger\Client\Model\TaskRemoveGroupsItem;
use Swagger\Client\Model\GroupIds;
use Swagger\Client\Model\Event;
use Swagger\Client\Model\EventCreateItem;
use Swagger\Client\Model\EventAttributes;
use Swagger\Client\Model\Gateway;
use Swagger\Client\Model\GatewayCreateItem;
use Swagger\Client\Model\GatewayAttributes;
use Swagger\Client\Model\Flow;
use Swagger\Client\Model\FlowCreateItem;
use Swagger\Client\Model\FlowAttributes;
use Swagger\Client\Model\Instance;
use Swagger\Client\Model\InstanceCreateItem;
use Swagger\Client\Model\InstanceAttributes;
/**
 * ProcessmakerApiTest Class Doc Comment
 *
 * @category Class
 * @package  Swagger\Client
 * @author   http://github.com/swagger-api/swagger-codegen
 * @license  http://www.apache.org/licenses/LICENSE-2.0 Apache Licene v2
 * @link     https://github.com/swagger-api/swagger-codegen
 */
class ProcessmakerApiTest extends \PHPUnit_Framework_TestCase
{
    /** @var ProcessmakerApi $apiInstance */
    private $apiInstance;

    /**
     * Setup before running any test cases
     */
    public static function setUpBeforeClass()
    {

    }

    /**
     * Setup before running each test case
     */
    public function setUp()
    {
        $this->apiInstance = new Api\ProcessmakerApi();
        if (in_array('--debug', $_SERVER['argv'])) {
            $this->apiInstance->getApiClient()->getConfig()->setDebug(true);
            /** Try to set accessToken to get Process */
            $this->apiInstance->getApiClient()->getConfig()->setAccessToken('eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijg5ZjhjOGIzNzQ1Y2E2YjlhYTAyNzRlNDVmZmM3Nzk0MzYwNTE5MzVmYjkwYWYyMzhmZWRmMWI1NTQxYjY3MGZjOTJhZjljOTgxZWM0N2YxIn0.eyJhdWQiOiI0IiwianRpIjoiODlmOGM4YjM3NDVjYTZiOWFhMDI3NGU0NWZmYzc3OTQzNjA1MTkzNWZiOTBhZjIzOGZlZGYxYjU1NDFiNjcwZmM5MmFmOWM5ODFlYzQ3ZjEiLCJpYXQiOjE0ODIxNjgxNzAsIm5iZiI6MTQ4MjE2ODE3MCwiZXhwIjoxNTEzNzA0MTcwLCJzdWIiOiIzNSIsInNjb3BlcyI6W119.S2dCO3jVXKX3a-k4lQqpmnEcxcb5EHaZ-94sO5iE6OZSK0b44IRRAIgTfLtaziFOiaIBT1Nj3bCYrijh5Um2ipQuJ1mIur3aOoszHGV7XFuaMU4oPXEpsMGRZjRAQoi3YuvZmBjg4yhqC9JRy-Q672gAdxHAD9IL0d0taYV8eCoDbYzcxz4TBYPkxIv6M3W9wA9o8b91l6HmQQ4iEqIX07Awu1U-2hHBdB8OlFao6_31y-O9FZPUgNByvqtKZ76o2PbaRTm4BQ7nFWF7JHz8jfaOtQVFp32TATc7DzW8Sec4RkXMsvlJC03ETutIijrEGP8dH2NP_ZjVg1Lnajw8nxkUboBcdRO9ATM0LixjUxCrXNi7q3376WPLE1da0YqsUjqekaAM2cnw5HIOXw4kS-kE6tPF_PnOjrzteKXPCWNOF5Ksewp8ezLUpeIyzstsFHslRvzY9G_H2bn2n8QevMypP7g54h-9C8peFLSEEcuTISNiFD6GsmLQDXgwaH4taJ_xnLHrttIx15tHvSV0xb2SaBxmkQWeomKneX09E6tg9mFeKFQnnS9kyOz1dO2KQ1qtF9DyAa3DhN4_ikkRyqTcCQUVjkOS_WtIgt7lIQYDp2e1c8DzFa7AhYNkuI0k_vBUUz51HomWS5__KFL3raN6W6PJEMkw_74sIQZjglc');
        }
    }

    /**
     * Clean up after running each test case
     */
    public function tearDown()
    {

    }

    /**
     * Clean up after running all test cases
     */
    public static function tearDownAfterClass()
    {

    }

    /**
     * Test case for addGroup
     *
     * .
     *
     */
    public function testAddGroup()
    {
        try {

            $groupAtt = new GroupAttributes();
            $groupAtt->setCode('Test Code '.mt_rand(100000,999999));
            $groupAtt->setTitle('Group title');

            /** @var GroupItem $result */
            $result = $this->apiInstance->addGroup(new GroupCreateItem([
                'data' => new Group(['attributes' => $groupAtt])
            ]));

            $this->assertNotNull($result->getData()->getId());
            $this->assertEquals('Group title', $result->getData()->getAttributes()->getTitle());
            //print_r($result->getData());
            return $result->getData()->getId();

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for addUser
     *
     * .
     *
     */
    public function testAddUser()
    {
        try {
            $userAtt = new UserAttributes();
            $userAtt->setFirstname('Jonny');
            $userAtt->setLastname('Doe');
            $userAtt->setPassword('password');
            $userAtt->setUsername('Username' . mt_rand(100000,999999));
            $userAtt->setEmail('email@at.com');

            /** @var UserItem $result */
            $result = $this->apiInstance->addUser(new UserCreateItem([
                'data' => new User(['attributes' => $userAtt])
            ]));

            $this->assertNotNull($result->getData()->getId());
            $this->assertEquals('Jonny', $result->getData()->getAttributes()->getFirstname());
            //print_r($result->getData());
            return $result->getData()->getId();
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }

    }

    /**
     * Test case for addUsersToGroup
     *
     * .
     *
     */
    public function testAddUsersToGroup()
    {
        /** @var string $groupId */
        $groupId = $this->testAddGroup();
        $this->assertNotNull($groupId, 'Group should be created');

        /** @var string $userIdId */
        $userId = $this->testAddUser();
        $this->assertNotNull($userId, 'User should be created');

        $GroupAddUsersItem = new GroupAddUsersItem([
            'data' => new UserIds([
                'users' => [$userId]
            ])
        ]);

        try {
            /** @var ResultSuccess $result */
            $result = $this->apiInstance->addUsersToGroup($groupId, $GroupAddUsersItem);
            //print_r($result->getMeta());
            $this->assertEquals('1021', $result->getMeta()->getCode(), 'User should be attached to the Group');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    public function testRemoveUsersFromGroup()
    {
        /** @var string $groupId */
        $groupId = $this->testAddGroup();
        $this->assertNotNull($groupId, 'Group should be created');

        /** @var string $userIdId */
        $userId = $this->testAddUser();
        $this->assertNotNull($userId, 'User should be created');


        $UsersItem = new GroupRemoveUsersItem([
            'data' => new UserIds([
                'users' => [$userId]
            ])
        ]);

        /** Try to attach UsersItem to given group
         * @var MetaResult $result */
        $result = $this->apiInstance->addUsersToGroup($groupId, $UsersItem);
        $this->assertEquals('1021', $result->getMeta()->getCode(), 'User should be attached to the Group');

        try {
            /** @var MetaResult $result */
            $result = $this->apiInstance->removeUsersFromGroup($groupId, $UsersItem);
            //print_r($result->getMeta());
            $this->assertEquals('1024', $result->getMeta()->getCode(), 'User should be detached from the Group');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for syncUsersToGroup
     */


    public function testSyncUsersToGroup()
    {
        /** @var string $groupId */
        $groupId = $this->testAddGroup();
        $this->assertNotNull($groupId, 'Group should be created');

        /** @var string $userIdId */
        $userId = $this->testAddUser();
        $this->assertNotNull($userId, 'User should be created');


        $UsersItem = new GroupRemoveUsersItem([
            'data' => new UserIds([
                'users' => [$userId]
            ])
        ]);

        try {
            /** @var ResultSuccess $result */
            $result = $this->apiInstance->syncUsersToGroup($groupId, $UsersItem);
            $this->assertEquals('1021', $result->getMeta()->getCode(), 'User should be attached with Group');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for findGroupById
     *
     *
     *
     */
    public function testFindGroupById()
    {
        $groupId = $this->testAddGroup();
        $userId = $this->testAddUser();
        try {
            /** @var GroupAttributes $result */
            $result = $this->apiInstance->findGroupById($groupId)->getData()->getAttributes();

            $this->assertCount(0, $result->getUsers(), 'Should be no users attached');

            $GroupAddUsersItem = new GroupAddUsersItem(['data' => new UserIds(['users' => [$userId]])]);
            $this->apiInstance->addUsersToGroup($groupId, $GroupAddUsersItem);

            /** @var GroupAttributes $result */
            $result = $this->apiInstance->findGroupById($groupId)->getData()->getAttributes();

            $this->assertCount(1, $result->getUsers(), 'Should be one user attached');
            $this->assertEquals($userId, $result->getUsers()[0], 'Should be proper User Id');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for findGroups
     *
     */
    public function testFindGroups()
    {
        try {
            /** @var Group[] $result */
            $result = $this->apiInstance->findGroups()->getData();
            $this->assertGreaterThan(0, count($result));
            //print_r($result);
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for findUserById
     *
     */
    public function testFindUserById()
    {

    }

    /**
     * Test case for findUsers
     *
     */
    public function testFindUsers()
    {

    }

    /**
     * Test case for updateGroup
     *
     */
    public function testUpdateGroup()
    {

    }

    /**
     * Test case for updateUser
     */
    public function testUpdateUser()
    {

    }

    /**
     * Test case for deleteUser
     *
     */
    public function testDeleteUser()
    {
        /** @var string $userId */
        $userId = $this->testAddUser();
        $this->assertNotNull($userId, 'User should be created');

        try {
            /** @var ResultSuccess $result */
            $result = $this->apiInstance->deleteUser($userId);
            $this->assertEquals('1002', $result->getMeta()->getCode(), 'Result code expected');
            //return $result->getData()->getId();
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for deleteGroup
     */
    public function testDeleteGroup()
    {
        /** @var string $userId */
        $userId = $this->testAddGroup();
        $this->assertNotNull($userId, 'Group should be created');

        try {
            /** @var ResultSuccess $result */
            $result = $this->apiInstance->deleteGroup($userId);
            $this->assertEquals('1017', $result->getMeta()->getCode(), 'Result code expected');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    private function dumpError(ApiException $e, $methodName)
    {
        echo "Exception when calling $methodName, ". $e->getMessage(). PHP_EOL;
        if ($e->getResponseObject()) {
            /** @var Error[] $errorArray */
            $errorArray = $e->getResponseObject()->getErrors();
            print_r($errorArray);
        }
    }

    /**
     * Test case for addProcess
     *
     */

    public function testAddProcess() {
        try {

            $processAtt = new ProcessAttributes();
            $processAtt->setStatus('ACTIVE');
            $processAtt->setName('Process name');
            $processAtt->setDurationBy('WORKING_DAYS');
            $processAtt->setType('NORMAL');
            $processAtt->setDesignAccess('PUBLIC');
            $processAtt->setCreateUserId($this->testAddUser());

            /** @var ProcessItem $result */
            $result = $this->apiInstance->addProcess(new ProcessCreateItem(
                    [
                        'data' => new Process(['attributes' => $processAtt])
                    ]
                )
            );

            $this->assertNotNull($result->getData()->getId());
            $this->assertEquals('Process name', $result->getData()->getAttributes()->getName());
            //print_r($result->getData());
            return $result->getData()->getId();

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }

    }

    /**
     * Test case for findProcessById
     *
     */

    public function testFindProcessById()
    {
        $this->markTestIncomplete();
        $processId = $this->testAddProcess();

        try {
            /** @var ProcessAttributes $result */
            $result = $this->apiInstance->findProcessById($processId)->getData()->getAttributes();
            $this->assertNotEmpty($result);

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for findProcesses
     *
     */

    public function testFindProcesses()
    {
        $this->markTestIncomplete();
        try {
            $this->testAddProcess();
            $result = $this->apiInstance->findProcesses()->getData();
            $this->assertGreaterThan(0, count($result));
            print_r($result);
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for deleteProcess
     *
     */

    public function testDeleteProcess()
    {
        $this->markTestIncomplete();
        $processId = $this->testAddProcess();
        $this->assertNotNull($processId, 'Process should be created');

        try {
            /** @var ResultSuccess $result */
            $result = $this->apiInstance->deleteProcess($processId);
            $this->assertEquals('1202', $result->getMeta()->getCode(), 'Result code expected');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }


    /**
     * Test case for addTask
     * @param boolean $process
     * @return array of IDs
     */

    public function testAddTask($process = false) {
        try {
            ($process == false) ? $processUid = $this->testAddProcess() : $processUid = $process;
            $taskAttr = new TaskAttributes();
            $taskAttr->setName('Task name');
            $taskAttr->setType('NORMAL');
            $taskAttr->setProcessId($processUid);
            $taskAttr->setAssignType('BALANCED');
            $taskAttr->setTransferFly(true);
            $taskAttr->setCanUpload(true);
            $taskAttr->setViewUpload(true);
            $taskAttr->setViewAdditionalDocumentation(true);
            $taskAttr->setStart(false);
            $taskAttr->setSendLastEmail(true);
            $taskAttr->setSelfserviceTimeout(10);

            /** @var TaskItem $result */
            $result = $this->apiInstance->addTask(
                $processUid,
                new TaskCreateItem(
                    [
                        'data' => new Task(['attributes' => $taskAttr])
                    ]
                )
            );

            $this->assertNotNull($result->getData()->getId());
            $this->assertEquals('Task name', $result->getData()->getAttributes()->getName());
            //print_r($result->getData());
            return ['task_uid'=>$result->getData()->getId(),'process_uid'=>$processUid];

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }

    }

    /**
     * Test case for findTasks
     *
     */

    public function testFindTasks()
    {
        try {
            $result = $this->apiInstance->findTasks($this->testAddTask()['process_uid'])->getData();
            $this->assertGreaterThan(0, count($result));
            //print_r($result);
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for findTaskById
     *
     */

    public function testFindTaskById()
    {
        $array_ids = $this->testAddTask();
        try {
            $result = $this->apiInstance->findTaskById($array_ids['process_uid'],$array_ids['task_uid'])->getData()->getAttributes();
            $this->assertNotEmpty($result);

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for deleteTask
     *
     */

    public function testDeleteTask()
    {
        $array_ids = $this->testAddTask();
        try {
            $result = $this->apiInstance->deleteTask($array_ids['process_uid'],$array_ids['task_uid']);
            $this->assertEquals('1122', $result->getMeta()->getCode(), 'Result code expected');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for addGroupsToTask
     *
     */

    public function testAddGroupsToTask()
    {
        /** @var string $groupId */
        $groupId = $this->testAddGroup();
        $this->assertNotNull($groupId, 'Group should be created');

        /** @var array $array_ids of Process and Task*/
        $array_ids = $this->testAddTask();
        $this->assertEquals(2,count($array_ids), 'We should get Process UID and Task UID');

        $taskAddGroupsItem = new TaskAddGroupsItem([
            'data' => new GroupIds([
                'groups' => [$groupId]
            ])
        ]);

        try {
            /** @var ResultSuccess $result */
            $result = $this->apiInstance->addGroupsToTask(
                $array_ids['process_uid'],
                $array_ids['task_uid'],
                $taskAddGroupsItem
            );
            //print_r($result->getMeta());
            $this->assertEquals('1126', $result->getMeta()->getCode(), 'User should be attached to the Group');
            $array_ids['group_uid'] = $groupId;
            return $array_ids;
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for syncGroupsToTask
     *
     */

    public function testSyncGroupsToTask()
    {
        /** @var string $groupId */
        $groupId = $this->testAddGroup();
        $this->assertNotNull($groupId, 'Group should be created');

        /** @var array $array_ids of Process and Task*/
        $array_ids = $this->testAddTask();
        $this->assertEquals(2, count($array_ids), 'We should get Process UID and Task UID');


        $groupItems = new TaskSyncGroupsItem([
            'data' => new GroupIds([
                'groups' => [$groupId]
            ])
        ]);

        try {
            /** @var ResultSuccess $result */
            $result = $this->apiInstance->syncGroupsToTask(
                $array_ids['process_uid'],
                $array_ids['task_uid'],
                $groupItems
            );
            $this->assertEquals('1126', $result->getMeta()->getCode(), 'User should be attached with Group');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for removeGroupsFromTask
     *
     */

    public function testRemoveGroupsFromTask()
    {
        /** @var array $array_ids of Process and Task and ID of attached group*/
        $array_ids = $this->testAddGroupsToTask();

        $groupItems = new TaskRemoveGroupsItem([
            'data' => new GroupIds([
                'groups' => [$array_ids['group_uid']]
            ])
        ]);

        try {

            /** @var MetaResult $result */
            $result = $this->apiInstance->removeGroupsFromTask(
                $array_ids['process_uid'],
                $array_ids['task_uid'],
                $groupItems);
            //print_r($result->getMeta());
            $this->assertEquals('1128', $result->getMeta()->getCode(), 'User should be detached from the Group');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for addEvent
     *@param  boolean $process
     *@return array of process ID and event ID
     */

    public function testAddEvent($process = false) {
        try {
            ($process == false) ? $processUid = $this->testAddProcess() : $processUid = $process;
            $eventAttr = new EventAttributes();
            $eventAttr->setName('Event name');
            $eventAttr->setType('START');
            $eventAttr->setProcessId($processUid);
            $eventAttr->setDefinition('MESSAGE');

            $result = $this->apiInstance->addEvent(
                $processUid,
                new EventCreateItem(
                    [
                        'data' => new Event(['attributes' => $eventAttr])
                    ]
                )
            );

            $this->assertNotNull($result->getData()->getId());
            $this->assertEquals('Event name', $result->getData()->getAttributes()->getName());
            //print_r($result->getData());
            return ['event_uid'=>$result->getData()->getId(),'process_uid'=>$processUid];

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }

    }

    /**
     * Test case for findEvents
     *
     */

    public function testFindEvents()
    {
        try {
            $result = $this->apiInstance->findEvents($this->testAddEvent()['process_uid'])->getData();
            $this->assertGreaterThan(0, count($result));
            //print_r($result);
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for findEventById
     *
     */

    public function testFindEventById()
    {
        $array_ids = $this->testAddEvent();
        try {

            $result = $this->apiInstance->findEventById($array_ids['process_uid'],$array_ids['event_uid'])->getData()->getAttributes();
            $this->assertNotEmpty($result);

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for DeleteEvent
     *
     */

    public function testDeleteEvent()
    {
        $array_ids = $this->testAddEvent();
        try {
            $result = $this->apiInstance->deleteEvent($array_ids['process_uid'],$array_ids['event_uid']);
            $this->assertEquals('1770', $result->getMeta()->getCode(), 'Result code expected');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for addGateway
     *
     */

    public function testAddGateway() {
        try {
            $processUid = $this->testAddProcess();
            $gatewayAttr = new GatewayAttributes();
            $gatewayAttr->setName('Gateway name');
            $gatewayAttr->setType('EXCLUSIVE');
            $gatewayAttr->setProcessId($processUid);

            $result = $this->apiInstance->addGateway(
                $processUid,
                new GatewayCreateItem(
                    [
                        'data' => new Gateway(['attributes' => $gatewayAttr])
                    ]
                )
            );

            $this->assertNotNull($result->getData()->getId());
            $this->assertEquals('Gateway name', $result->getData()->getAttributes()->getName());
            //print_r($result->getData());
            return ['gateway_uid'=>$result->getData()->getId(),'process_uid'=>$processUid];

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }

    }

    /**
     * Test case for findGateways
     *
     */

    public function testFindGateways()
    {
        try {
            $result = $this->apiInstance->findGateways($this->testAddGateway()['process_uid'])->getData();
            $this->assertGreaterThan(0, count($result));
            //print_r($result);
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for findGatewayById
     *
     */

    public function testFindGatewayById()
    {
        $array_ids = $this->testAddGateway();
        try {

            $result = $this->apiInstance->findGatewayById($array_ids['process_uid'],$array_ids['gateway_uid'])->getData()->getAttributes();
            $this->assertNotEmpty($result);

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for deleteGateway
     *
     */

    public function testDeleteGateway()
    {
        $array_ids = $this->testAddGateway();
        try {
            $result = $this->apiInstance->deleteGateway($array_ids['process_uid'],$array_ids['gateway_uid']);
            $this->assertEquals('1751', $result->getMeta()->getCode(), 'Result code expected');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for addFlow
     *
     */

    public function testAddFlow() {
        try {
            $processUid = $this->testAddProcess();
            /*Creating 2 objects for Flow under the same Process Id */
            $task = $this->testAddTask($processUid);
            $event = $this->testAddEvent($processUid);
            $flowAttr= new FlowAttributes();
            $flowAttr->setName('Flow name');
            $flowAttr->setType('SEQUENTIAL');
            $flowAttr->setProcessId($processUid);
            $flowAttr->setFromObjectUid($task['task_uid']);
            $flowAttr->setFromObjectType('task');
            $flowAttr->setToObjectUid($event['event_uid']);
            $flowAttr->setToObjectType('event');
            $flowAttr->setDefault(false);
            $flowAttr->setOptional(false);
            $result = $this->apiInstance->addFlow(
                $processUid,
                new FlowCreateItem(
                    [
                        'data' => new Flow(['attributes' => $flowAttr])
                    ]
                )
            );

            $this->assertNotNull($result->getData()->getId());
            $this->assertEquals('Flow name', $result->getData()->getAttributes()->getName());
            //print_r($result->getData());
            return ['flow_uid'=>$result->getData()->getId(),'process_uid'=>$processUid];

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }

    }

    /**
     * Test case for findFlows
     *
     */

    public function testFindFlows()
    {
        try {
            $result = $this->apiInstance->findFlows($this->testAddFlow()['process_uid'])->getData();
            $this->assertGreaterThan(0, count($result));
            //print_r($result);
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for findFlowById
     *
     */

    public function testFindFlowById()
    {
        $array_ids = $this->testAddFlow();
        try {

            $result = $this->apiInstance->findFlowById($array_ids['process_uid'],$array_ids['flow_uid'])->getData()->getAttributes();
            $this->assertNotEmpty($result);

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for deleteFlow
     *
     */

    public function testDeleteFlow()
    {
        $array_ids = $this->testAddFlow();
        try {
            $result = $this->apiInstance->deleteFlow($array_ids['process_uid'],$array_ids['flow_uid']);
            $this->assertEquals('1761', $result->getMeta()->getCode(), 'Result code expected');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for addInstance
     *
     */

    public function testAddInstance() {
        try {
            $processUid = $this->testAddProcess();
            $instanceAttr = new InstanceAttributes();
            $instanceAttr->setName('Instance name');
            $instanceAttr->setStatus('TODO');
            $instanceAttr->setPin('123456');
            $instanceAttr->setProcessId($processUid);
            $result = $this->apiInstance->addInstance(
                $processUid,
                new InstanceCreateItem(
                    [
                        'data' => new Instance(['attributes' => $instanceAttr])
                    ]
                )
            );

            $this->assertNotNull($result->getData()->getId());
            $this->assertEquals('Instance name', $result->getData()->getAttributes()->getName());
            //print_r($result->getData());
            return ['instance_uid'=>$result->getData()->getId(),'process_uid'=>$processUid];

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }

    }

    /**
     * Test case for findInstances
     *
     */

    public function testFindInstances()
    {
        try {
            $result = $this->apiInstance->findInstances($this->testAddInstance()['process_uid'])->getData();
            $this->assertGreaterThan(0, count($result));
            //print_r($result);
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for findInstanceById
     *
     */

    public function testFindInstanceById()
    {
        $array_ids = $this->testAddInstance();
        try {
            $result = $this->apiInstance->findInstanceById($array_ids['process_uid'],$array_ids['instance_uid'])->getData()->getAttributes();
            $this->assertNotEmpty($result);

        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }

    /**
     * Test case for updateInstance
     *
     */

    public function testUpdateInstance()
    {
        $array_ids = $this->testAddInstance();
        $itemData = new InstanceAttributes();
        $itemData->setName('New Instance name');
        $itemData->setStatus('DRAFT');
        $result = $this->apiInstance->updateInstance(
            $array_ids['process_uid'],
            $array_ids['instance_uid'],
            new InstanceUpdateItem(['data' => new Instance(['attributes' => $itemData])])
        );
        $this->assertEquals('New Instance name', $result->getData()->getAttributes()->getName(), 'Name should be updated');
        $this->assertEquals('DRAFT', $result->getData()->getAttributes()->getStatus(), 'Status should be updated');
    }

    /**
     * Test case for deleteInstance
     *
     */

    public function testDeleteInstance()
    {
        $array_ids = $this->testAddInstance();
        try {
            $result = $this->apiInstance->deleteInstance($array_ids['process_uid'],$array_ids['instance_uid']);
            $this->assertEquals('1756', $result->getMeta()->getCode(), 'Result code expected');
        } catch (ApiException $e) {
            $this->dumpError($e, __METHOD__);
        }
    }



}
