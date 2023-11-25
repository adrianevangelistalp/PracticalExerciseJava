Feature: customers CRUD tests

  Background:
    * url 'http://localhost:8080'

  Scenario: save a customer and then get it by id
    * def customer =
      """
      {
        "personId": 1,
        "name": "Jose Lema",
        "genre": "F",
        "age": 20,
        "address" : "Otavalo sn y principal",
        "phone" : "1312",
        "password": "secret",
        "state": true
      }
      """

    Given path 'customers'
    And request customer
    When method post
    Then status 201

    Given path 'customers', response.id
    When method get
    Then status 200
    And match response contains customer

  Scenario: save a customer and then update it
    * def customer =
      """
      {
        "personId": 2,
        "name": "Jose Lema",
        "genre": "F",
        "age": 20,
        "address" : "Otavalo sn y principal",
        "phone" : "1312",
        "password": "secret",
        "state": true
      }
      """

    Given path 'customers'
    And request customer
    When method post
    Then status 201

    * def editedCustomer =
      """
      {
        "personId": 2,
        "name": "Jose Lema cambiado",
        "genre": "M",
        "age": 21,
        "address" : "Otavalo sn y principal nada",
        "phone" : "13123",
        "password": "secret1",
        "state": false
      }
      """

    Given path 'customers', response.id
    And request editedCustomer
    When method put
    Then status 200

    Given path 'customers', response.id
    When method get
    Then status 200
    And match response contains editedCustomer

  Scenario: save a customer and then delete it
    * def customer =
      """
      {
        "personId": 4,
        "name": "Jose Lema",
        "genre": "F",
        "age": 20,
        "address" : "Otavalo sn y principal",
        "phone" : "1312",
        "password": "secret",
        "state": true
      }
      """

    Given path 'customers'
    And request customer
    When method post
    Then status 201

    * def idToDelete = response.id

    Given path 'customers', response.id
    When method delete
    Then status 204

    Given path 'customers', idToDelete
    When method get
    Then status 400
    And match response contains { message: 'Customer not found' }



