# BankingAPI

_**# Components**_

The API is composed by its users, its accounts and its functions.

_**# Users**_

All users extend from an abstract class User in order to make an easy way to work with them because in addition to having their own attributes they also share some.

There are three kind of users: Admins, Account Holders and Third Parties. They share the next attributes:

* An ID.
* A name
* A password
* A role

**1- Admins**

Admins could be bank workers, system administrators or automated process.

As every user, it extends from the User class making its attributes the next:

* An ID. 
* A name
* A password
* A role

Admins don't have any special features but are the only ones who can access some routes to do special functions.

There is only one admin in the basic configuration of the API but admins can create more admins.

**2- Account Holders**

Account Holders represent the clients of the banking service. They could be a singular person, or an entity.

Account Holders also extends from User, but they work with more attributes making them the next:

* An ID.
* A name
* A password
* A role
* A mail
* A date of birth
* A phone number
* An address
* A mailing address (optional)
* A list of accounts where they are the primary owners
* A list of accounts where they are the secondary owners

Mailing address is different from their main address. Also, they have two lists, containing the accounts where they are owners.

Using the correct route, everyone could create an Account Holder.

**3- Third Party**

Third Party represent external entities that interact with the banking system. They could be other banking institutions or petitions by external entities like automated payments.

Account Holders also extends from User, but they work with more attributes making them the next:
* An ID
* A name
* A password
* A role
* A hashed key

Third parties can only be created by Admins.

**Passwords and Hashed keys are encrypted by a codifier to add security in the app**

_**# Accounts**_

All accounts extend from an abstract class Account in order to make an easy way to work with them because in addition to having their own attributes they also share some.

There are four kind of accounts: Checking, Student checking, Credit card and Saving. They share the next attributes:

* An ID
* A balance (Money object)
* A primary account holder
* A secondary account holder
* A penalty fee (Money object)
* A creation date
* A status
* A list of transactions

**1- Checking Accounts**

Checking accounts are the default type of account used it for everyday transactions, and they work with these attributes:

* An ID
* A balance (Money object)
* A primary account holder
* A secondary account holder
* A penalty fee (Money object)
* A creation date
* A status
* A list of transactions
* A minimum balance (Money object)
* A monthly maintenance Fee
* A last interest day

Checking accounts have a minimum balance established by its parameter. If the balance is lower than the minimum balance, a penalty fee is activated. Also, Checking accounts have a maintenance fee to cover the expenses that the account suppose to the banking entity. This fee is deduced from the balance every month after its creation.

**2- Student Checking Accounts**

Student Checking accounts are very similar to the normal Checking accounts but are suited for any user whose age is less than 24.

The Students Checking accounts eliminates all the fees that the normal Checking account have in order to make it easier for students, and they work with these attributes:

* An ID
* A balance (Money object)
* A primary account holder
* A secondary account holder
* A creation date
* A status
* A list of transactions

**3- Saving Accounts**

This type of account have an interest rate that make the deposited money grow annually, these are the attributes:

* An ID
* A balance (Money object)
* A primary account holder
* A secondary account holder
* A creation date
* A status
* A list of transactions
* A minimum balance
* An interest rate
* A last interest day

Saving accounts have an interest rate that is added to the deposited funds annually.

**4- Credit Card Accounts**

These accounts are the mechanism behind the functioning of a Credit Card, there are the attributes:

* An ID
* A balance (Money object)
* A primary account holder
* A secondary account holder
* A penalty fee (Money object)
* A creation date
* A status
* A list of transactions
* A credit limit (Money object)
* An interest rate
* A last interest day

Credit card accounts have an interest rate that is added to the deposited funds monthly.

_**# Routes**_

We will work with three different type of routs here: Admin routes, Account Holder routes and ThirdParty routes.

**1- Admin**

* /admin/showAccounts

This route allows an admin to get a list of all the accounts in the api.

* /admin/createAccount

This route allows an admin to create a new account binding it to an Account Holder. The API could throw the next exception:

`406 NOT_ACCEPTABLE. Please choose one between Checking, Savings or CreditCard.`

* /admin/createThirdParty

This route allows an admin to create a Third Party User

* /admin/createAdmin

This route allows an admin to create an Admin User

* /admin/delete/{id}

This route allows an admin to delete an account by the id associated to the account. The API could throw the next exception:

`404 NOT_FOUND, Not an existing account.`

* /admin/checkBalance/{id}

This route allows an admin to get the balance of all the accounts using the id of an account. The API could throw the next exception:

`404 NOT_FOUND, Not an existing account.`

* /admin/modifyBalance

This route allows an admin to manually change the balance of the account using the id of it. The API could throw the next exception:

`404 NOT_FOUND, Not an existing account.`

* /admin/modifyStatus

This route allows an admin to change the status of the account using the id of it. The API could throw the next exception:

`404 NOT_FOUND, Not an existing account.`

* /admin/showUsers

This route allows an admin to get a list of all the users using the application.

**2- Account Holder**

* /holderAccount/showAccounts/{id}

This route allows a user to get the information from his accounts using an id. The API could throw the next exception:

`404 NOT_FOUND, Not an existing account.`

* /holderAccount/createUser

This route allows a user to create a new account.

* /holderAccount/checkBalance

This route allows a user to check the balance from any of their accounts using the id. The API could throw the next exception:

`404 NOT_FOUND, Not an existing user.`

`404 NOT_FOUND, Not an existing account.`

`401 UNAUTHORIZED, Can't access to this account.`

* /holderAccount/transfer

This route allows a user to transfer money from one of their accounts to other account. The API could throw the next exception:

`404 NOT_FOUND, Not an existing account.`

`406 NOT_ACCEPTABLE, Not enough money to transfer.`

**3- Third Party**

* /thirdParty/transfer

This route allows a Third Party to transfer money from an account to other account using both account id and a hashedKey . The API could throw the next exception:

`404 NOT_FOUND, Not an existing account.`

`406 NOT_ACCEPTABLE, Not available`

`406 NOT_ACCEPTABLE, Not enough founds.`

For this route work you need to provide the hash key in the header looking like this:

* /thirdParty/transfer/{hashedKey}

###  Many thanks to IRONHACK and my colleagues for helping to make this project possible.

**_PROJECT MADE BY OSCAR CURTO_**