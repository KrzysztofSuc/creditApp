## CreditApp
This project was created to learn and understand microservices architecture. The system allows you to draw a loan, download borrower's and credit details and delete it on the basis of a unique number.    

Login system has been implemented that uses JWT tokens.

### Functions

#### Login  
Method POST  
http://localhost:4200/api/auth/login
```
{
    "email" : "user@wp.pl",
    "password" : "user"
}
```
![alt tag](/Screenshots/login.jpg)  
#### Create credit
Method POST  
http://localhost:4200/api/credit/add 
```
{
  "customerDto" : {
		"firstName" : "Jan",
		"lastName" : "Kowlaski",
		"pesel" : "11111111111"
	},
  "productDto" : {
		"name" : "na dom",
		"value" : "1.2"
	}
}
```
![alt tag](/Screenshots/createCredit.jpg)  
#### Get credit
Method GET  
http://localhost:4200/api/credit/{creditNumber}  
![alt tag](/Screenshots/getCredit.jpg) 
#### Delete credit
Method DELETE  
http://localhost:4200/api/credit/{creditNumber}  
![alt tag](/Screenshots/deleteCredit.jpg) 
#### Credit not found
![alt tag](/Screenshots/creditNotFound.jpg)
#### Bad email or password 
![alt tag](/Screenshots/loginBC.jpg)
#### Token expired 
![alt tag](/Screenshots/tokenExpiredOut.jpg)
