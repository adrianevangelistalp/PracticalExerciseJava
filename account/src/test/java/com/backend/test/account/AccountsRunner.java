package com.backend.test.account;

import com.intuit.karate.junit5.Karate;

class AccountsRunner {
    
    @Karate.Test
    Karate testAccounts() {
        return Karate.run("accounts").relativeTo(getClass());
    }

    @Karate.Test
    Karate testAccountMovementRegistration() {
        return Karate.run("accounts.movement").relativeTo(getClass());
    }

    @Karate.Test
    Karate testAccountReport() {
        return Karate.run("accounts.report").relativeTo(getClass());
    }
}
