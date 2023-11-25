package com.backend.test.customer;

import com.intuit.karate.junit5.Karate;

class CustomersRunner {
    
    @Karate.Test
    Karate testAccounts() {
        return Karate.run("customers").relativeTo(getClass());
    }    

}
