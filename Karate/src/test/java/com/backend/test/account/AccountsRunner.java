package com.backend.test.account;

import com.intuit.karate.junit5.Karate;

class AccountsRunner {
    
    @Karate.Test
    Karate testAccounts() {
        return Karate.run("accounts").relativeTo(getClass());
    }    

}