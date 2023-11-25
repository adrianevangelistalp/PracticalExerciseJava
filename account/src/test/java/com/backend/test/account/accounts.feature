Feature: account CRUD tests

  Background:
    * url 'http://localhost:8081'

  Scenario: save an account and then get it
    * def account =
      """
        {
            "customerId": 1,
            "type": "CA",
            "initialBalance": 120.00,
            "state" : true
        }
      """

    Given path 'accounts'
    And request account
    When method post
    Then status 201

    Given path 'accounts', response.id
    When method get
    Then status 200
    And match response.customerId == 1
    And match response.type == "CA"
    And match response.balance == 120.00
    And match response.state == true

  Scenario: save an account and then update it
    * def account =
      """
        {
            "customerId": 1,
            "type": "CA",
            "initialBalance": 20.00,
            "state" : true
        }
      """

    Given path 'accounts'
    And request account
    When method post
    Then status 201

    * def editedAccount =
      """
        {
            "customerId": 1,
            "type": "CC",
            "state" : false
        }
      """

    Given path 'accounts', response.id
    And request editedAccount
    When method put
    Then status 200

    Given path 'accounts', response.id
    When method get
    Then status 200
    And match response.customerId == 1
    And match response.type == "CC"
    And match response.state == false


  Scenario: save an account and then delete it
    * def account =
      """
        {
            "customerId": 1,
            "type": "CA",
            "initialBalance": 4420.00,
            "state" : true
        }
      """

    Given path 'accounts'
    And request account
    When method post
    Then status 201

    * def idToDelete = response.id

    Given path 'accounts', response.id
    When method delete
    Then status 204

    Given path 'accounts', idToDelete
    When method get
    Then status 400
    And match response contains { message: 'Account not found' }
