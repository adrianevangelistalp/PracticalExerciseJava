Feature: account state report generation

  Background:
    * url 'http://localhost:8080'
    * def accountsUrl = 'http://localhost:8081'
    * def customerUrl = 'http://localhost:8080'
    * def sleep = function(pause){ java.lang.Thread.sleep(pause*1000) }

    * def customer1 = { personId: 11, name: "Jose Lema", genre: "M",  age: 25, address : "Otavalo sn y principal", phone : "098254785", password: "1234", state: true}
    * def customer2 = { personId: 12, name: "Marianela Montalvo", genre: "F",  age: 25, address : "Amazonas y NNUU", phone : "097548965", password: "5678", state: true}
    * def customer3 = { personId: 13, name: "Juan Osorio", genre: "M",  age: 25, address : "13 junio y Equinoccial", phone : "098874587", password: "1245", state: true}

    Given url customerUrl
    And path 'customers'
    And request customer1
    When method post
    Then status 201

    * def customer1Id = response.id

    Given url customerUrl
    And path 'customers'
    And request customer2
    When method post
    Then status 201

    * def customer2Id = response.id

    Given url customerUrl
    And path 'customers'
    And request customer3
    When method post
    Then status 201

    * def customer3Id = response.id

    * def account1 = { customerId: '#(customer1Id)', type: 'Ahorros', initialBalance: 2000.00, state: true }
    * def account2 = { customerId: '#(customer2Id)', type: 'Corriente', initialBalance: 100.00, state: true }
    * def account3 = { customerId: '#(customer3Id)', type: 'Ahorros ', initialBalance: 0.00, state: true }
    * def account4 = { customerId: '#(customer2Id)', type: 'Ahorros ', initialBalance: 540.00, state: true }

    Given url accountsUrl
    And path 'accounts'
    And request account1
    When method post
    Then status 201

    * def account1Id = response.id

    Given url accountsUrl
    And path 'accounts'
    And request account2
    When method post
    Then status 201

    * def account2Id = response.id

    Given url accountsUrl
    And path 'accounts'
    And request account3
    When method post
    Then status 201

    * def account3Id = response.id

    Given url accountsUrl
    And path 'accounts'
    And request account4
    When method post
    Then status 201

    * def account4Id = response.id

    * def newAccount = { customerId: '#(customer1Id)', type: 'Corriente', initialBalance: 1000.00, state: true }

    Given url accountsUrl
    And path 'accounts'
    And request newAccount
    When method post
    Then status 201

    * def newAccountId = response.id

    * def movement1 = { type: 'Retiro', amount: 575 }

    Given url accountsUrl
    And path 'accounts', account1Id, 'movement'
    And request movement1
    When method post
    Then status 200

    * def movement2 = { type: 'Deposito', amount: 600 }

    Given url accountsUrl
    And path 'accounts', account2Id, 'movement'
    And request movement2
    When method post
    Then status 200

    * def movement3 = { type: 'Deposito', amount: 150 }

    Given url accountsUrl
    And path 'accounts', account3Id, 'movement'
    And request movement3
    When method post
    Then status 200

    * def movement4 = { type: 'Retiro', amount: 540 }

    Given url accountsUrl
    And path 'accounts', account4Id, 'movement'
    And request movement4
    When method post
    Then status 200

    Given url accountsUrl
    And path 'accounts', response.id, 'movement'
    And request { "type": "Deposito", "amount": 20.00 }
    When method post
    Then status 200

  Scenario: report generation for a customer with accounts and movements

    Given url customerUrl
    And path 'reports', customer2Id
    And param from = '2021-01-01'
    And param to = '2024-12-31'
    When method get
    Then status 204

    Given url customerUrl
    And path 'reports', 'customer', customer2Id
    When method get
    Then status 200
    And match response == []

    * call sleep 15

    Given url customerUrl
    And path 'reports', 'customer', customer2Id
    When method get
    Then status 200

    * def report = response[0].id

    Given url customerUrl
    And path 'reports', report, 'detail'
    When method get
    Then status 200
    And match response == '#[2]'
    And match response[0].customerId == customer2Id
    And match response[0].id == account2Id
    And match response[1].customerId == customer2Id
    And match response[1].id == account4Id
    And match response[0].transactions == '#[1]'
    And match response[0].transactions[0].type == 'Deposito'
    And match response[0].transactions[0].amount == 600
    And match response[1].transactions == '#[2]'
    And match response[1].transactions[0].type == 'Retiro'
    And match response[1].transactions[0].amount == 540
    And match response[1].transactions[1].type == 'Deposito'
    And match response[1].transactions[1].amount == 20.00

  Scenario: report generation for a customer with accounts and without movements in the period

    Given url customerUrl
    And path 'reports', customer3Id
    And param from = '2021-01-01'
    And param to = '2022-12-31'
    When method get
    Then status 204

    Given url customerUrl
    And path 'reports', 'customer', customer3Id
    When method get
    Then status 200
    And match response == []

    * call sleep 15

    Given url customerUrl
    And path 'reports', 'customer', customer3Id
    When method get
    Then status 200

    * def report = response[0].id

    Given url customerUrl
    And path 'reports', report, 'detail'
    When method get
    Then status 200
    And match response == '#[1]'
    And match response[0].customerId == customer3Id
    And match response[0].id == account3Id
    And match response[0].transactions == []

  Scenario: report generation for a customer without accounts

    * def customer4 = { personId: 14, name: "Juan Osorio", genre: "M",  age: 25, address : "13 junio y Equinoccial", phone : "098874587", password: "1245", state: true}

    Given url customerUrl
    And path 'customers'
    And request customer4
    When method post
    Then status 201

    * def customer4Id = response.id

    Given url customerUrl
    And path 'reports', customer4Id
    And param from = '2021-01-01'
    And param to = '2022-12-31'
    When method get
    Then status 204

    Given url customerUrl
    And path 'reports', 'customer', customer4Id
    When method get
    Then status 200
    And match response == []

    * call sleep 15

    Given url customerUrl
    And path 'reports', 'customer', customer4Id
    When method get
    Then status 200

    * def report = response[0].id

    Given url customerUrl
    And path 'reports', report, 'detail'
    When method get
    Then status 200
    And match response == []

