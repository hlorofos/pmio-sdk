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
use Swagger\Client\Model\MetaResult;
use Swagger\Client\Model\User;
use Swagger\Client\Model\UserAttributes;
use Swagger\Client\Model\UserCreateItem;
use Swagger\Client\Model\UserIds;
use Swagger\Client\Model\UserItem;

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
            /** @var GroupItem $result */
            $result = $this->apiInstance->addGroup(new GroupCreateItem([
                'data' => new Group(['attributes' => new GroupAttributes([
                    'code' => 'Test Code '.mt_rand(100000,999999),
                    'title' => 'Group title']
                )])
            ]));

            $this->assertNotNull($result->getData()->getId());
            $this->assertEquals('Group title', $result->getData()->getAttributes()->getTitle());
            //print_r($result->getData());
            return $result->getData()->getId();

        } catch (ApiException $e) {
            echo 'Exception when calling ProcessmakerApi->addGroup: ', $e->getMessage(), PHP_EOL;
            if ($e->getResponseObject()) {
                /** @var Error[] $errorArray */
                $errorArray = $e->getResponseObject()->getErrors();
                print_r($errorArray);
            }
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
            echo 'Exception when calling ProcessmakerApi->addUser: ', $e->getMessage(), PHP_EOL;
            if ($e->getResponseObject()) {
                /** @var Error[] $errorArray */
                $errorArray = $e->getResponseObject()->getErrors();
                print_r($errorArray);
            }
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
            /** @var MetaResult $result */
            $result = $this->apiInstance->addUsersToGroup($groupId, $GroupAddUsersItem);
            //print_r($result->getMeta());
            $this->assertEquals('1021', $result->getMeta()->getCode(), 'User should be attached to the Group');
        } catch (ApiException $e) {
            echo 'Exception when calling ProcessmakerApi->addUsersToGroup: ', $e->getMessage(), PHP_EOL;
            if ($e->getResponseObject()) {
                /** @var Error[] $errorArray */
                $errorArray = $e->getResponseObject()->getErrors();
                print_r($errorArray);
            }
        }
    }

    /**
     * Test case for deleteGroup
     *
     * .
     *
     */
    public function testDeleteGroup()
    {

    }

    /**
     * Test case for deleteUser
     *
     * .
     *
     */
    public function testDeleteUser()
    {

    }

    /**
     * Test case for findGroupById
     *
     * .
     *
     */
    public function testFindGroupById()
    {

    }

    /**
     * Test case for findGroups
     *
     * .
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
            echo 'Exception when calling ProcessmakerApi->testFindGroups: ', $e->getMessage(), PHP_EOL;
            if ($e->getResponseObject()) {
                /** @var Error[] $errorArray */
                $errorArray = $e->getResponseObject()->getErrors();
                print_r($errorArray);
            }
        }

    }

    /**
     * Test case for findUserById
     *
     * .
     *
     */
    public function testFindUserById()
    {

    }

    /**
     * Test case for findUsers
     *
     * .
     *
     */
    public function testFindUsers()
    {

    }

    /**
     * Test case for updateGroup
     *
     * .
     *
     */
    public function testUpdateGroup()
    {

    }

    /**
     * Test case for updateUser
     *
     * .
     *
     */
    public function testUpdateUser()
    {

    }

}
