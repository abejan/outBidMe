package com.outbidme.authentication;

import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.authentication.AccountGateway;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AuthenticationService {
    private Set<Account> loggedAccounts = new HashSet<Account>();

    public boolean login(String username, String password) {
        if (isLoggedIn(username)){
            return false;
        }
        final Account account = getAccount(username, password);
        if (account == null){
            return false;
        }
        final boolean isPasswordValid = verifyCredentials(account, password);
        if (isPasswordValid) {
            loggedAccounts.add(account);
        }
        return isPasswordValid;
    }

    public boolean isLoggedIn(String username) { 
        for (Account account : loggedAccounts) {
            if (accountMatches(username, account)){
                return true;
            }
        }
        return false;
    }

    public boolean logout(String username) {
        Iterator<Account> it = loggedAccounts.iterator();
        while (it.hasNext()){
            Account account = it.next();
            if (accountMatches(username, account)){
                it.remove();
                return true;
            }
        }
        return false;
    }

    private Account getAccount(String username, String password) {
        final AccountGateway accountGateway = PersistanceFactory.getAccountGateway();
        return accountGateway.findAccountByUserName(username);

    }

    private boolean verifyCredentials(Account account, String password){
        return account.getPassword().equals(password);
    }

    private boolean accountMatches(String username, Account account) {
        return account.getUsername().equals(username);
    }
}
