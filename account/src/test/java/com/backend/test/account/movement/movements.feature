Feature: movements CRUD tests

  Background:
    * url 'http://localhost:8081'
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

    * def accountId = response.id

  Scenario: save a movement and then get it

    * def movement = { accountId: '#(accountId)', type: "haberes", amount : 10.54, date : "2019-01-01 12:00:00", balance : 10.54 }

    Given path 'movements'
    And request movement
    When method post
    Then status 201


    Given path 'movements', response.id
    When method get
    Then status 200
    And match response.type == movement.type
    And match response.amount == movement.amount
    And match response.date == movement.date
    And match response.balance == movement.balance

  Scenario: save a movement and then update it
    * def movement = { accountId: '#(accountId)', type: "haberes", amount : 10.54, date : "2019-01-01 12:00:00", balance : 10.54 }

    Given path 'movements'
    And request movement
    When method post
    Then status 201

    * def editedMovement = { accountId: '#(accountId)', type: "varios", amount : 310.54, date : "2019-01-01 12:00:00", balance : 310.54 }

    Given path 'movements', response.id
    And request editedMovement
    When method put
    Then status 200

    Given path 'movements', response.id
    When method get
    Then status 200
    And match response.type == editedMovement.type
    And match response.amount == editedMovement.amount
    And match response.date == editedMovement.date
    And match response.balance == editedMovement.balance

  Scenario: save a movement and then delete it
    * def movement = { accountId: '#(accountId)', type: "haberes", amount : 10.54, date : "2019-01-01 12:00:00", balance : 10.54 }

    Given path 'movements'
    And request movement
    When method post
    Then status 201

    * def idToDelete = response.id

    Given path 'movements', response.id
    When method delete
    Then status 204

    Given path 'movements', idToDelete
    When method get
    Then status 400
    And match response.message == "Movement not found"
