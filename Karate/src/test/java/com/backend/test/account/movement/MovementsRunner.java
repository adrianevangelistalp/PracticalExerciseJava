package com.backend.test.account.movement;

import com.intuit.karate.junit5.Karate;

class MovementsRunner {
    
    @Karate.Test
    Karate testAccounts() {
        return Karate.run("movements").relativeTo(getClass());
    }    

}
