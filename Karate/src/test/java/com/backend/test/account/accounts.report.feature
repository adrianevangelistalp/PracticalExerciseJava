Feature: account state report generation

  Background:
    * url 'http://localhost:8081'

  Scenario: positive movement registration
    * def customer =
      """
      {
        "personId": 1,
        "name": "Marianela Montalvo",
        "genre": "F",
        "age": 25,
        "address" : "Amazonas y NNUU",
        "phone" : "1312",
        "password": "secret",
        "state": "registered"
      }
      """


    * def account =
      """
        {
            "customerId": 1,
            "type": "CA",
            "initialBalance": 120.00,
            "state" : "active"
        }
      """

    Given path 'accounts'
    And request account
    When method post
    Then status 201

    Given path 'accounts', response.id, 'movement'
    And request { "type": "DEPOSIT", "amount": 20.00 }
    When method post
    Then status 200

    Given path 'accounts', response.id
    When method get
    Then status 200
    And match response.customerId == 1
    And match response.type == "CA"
    And match response.balance == 140.00
    And match response.state == "active"

  Scenario: negative movement registration
    * def account =
      """
        {
            "customerId": 1,
            "type": "CA",
            "initialBalance": 120.00,
            "state" : "active"
        }
      """

    Given path 'accounts'
    And request account
    When method post
    Then status 201

    Given path 'accounts', response.id, 'movement'
    And request { "type": "DEPOSIT", "amount": -20.00 }
    When method post
    Then status 200

    Given path 'accounts', response.id
    When method get
    Then status 200
    And match response.customerId == 1
    And match response.type == "CA"
    And match response.balance == 100.00
    And match response.state == "active"

  Scenario: positive movement registration
    * def account =
      """
        {
            "customerId": 1,
            "type": "CA",
            "initialBalance": 120.00,
            "state" : "active"
        }
      """

    Given path 'accounts'
    And request account
    When method post
    Then status 201

    Given path 'accounts', response.id, 'movement'
    And request { "type": "DEPOSIT", "amount": 20.00 }
    When method post
    Then status 200

    Given path 'accounts', response.id
    When method get
    Then status 200
    And match response.customerId == 1
    And match response.type == "CA"
    And match response.balance == 140.00
    And match response.state == "active"

  Scenario: negative movement registration with insufficient funds
    * def account =
      """
        {
            "customerId": 1,
            "type": "CA",
            "initialBalance": 120.00,
            "state" : "active"
        }
      """

    Given path 'accounts'
    And request account
    When method post
    Then status 201

    Given path 'accounts', response.id, 'movement'
    And request { "type": "DEPOSIT", "amount": -121.00 }
    When method post
    Then status 400
    And match response.message == "Insufficient funds"
