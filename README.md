Account Service:
1 - To create Account: access http://localhost:8080/addAccount via POST and provide a body in this format:
{
     "id": 321,
     "email":"myemail@gmail.com",
     "address":"any street number",
     "payment":["visa", "master", "paypal"]
}
Or using postman, there`s also other requests to check in Cassandra database for data, like findAllAccounts, findAccountById.

All account will be saved in the Cassandra database, and it will be posted in the topic: “ACCOUNT_CREATED_EVENT”.
So if want to obtain all the accounts created just listen to this topic.


2 - To obtain a specific Account:
The Account Service listens to the topic “GET_ACCOUNT_EVENT” were anyone that want to obtain an account need to send the account object to this topic.
Create the Account object in the format:
public class Account {
    @PrimaryKey
    private int id;
    private String email;
    private String address;
    @Column
    private List<String> payment;
}

And populate the data like this: 
List<String> paymentList = new ArrayList<>();
paymentList.add("");
Account account = new Account(1, "","myemail@gmail.com", paymentList);
All the data can be default values, except for the email, because this is the field that Account Service will search for the account.


2 – How to get the Account (if exists):
To get the result, create a consumer for the topic: RESPONSE_GET_ACCOUNT_EVENT
This is where the account entity will be posted by the Account Service, if an account with the provided email exists, if the account is not found, there won’t be any messages posted to this topic.
