## CreditApp
This project was created to learn and understand microservices architecture. The system allows you to draw a loan, download borrower's and credit details and delete it on the basis of a unique number.    

It is planned to add a module responsible for logging in and creating authorization on the basis of JWT and create a Frontend based on Angular.

### Functional services


Method POST  
http://localhost:4200/api/credit/add

Body:
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

Method GET  
http://localhost:4200/api/credit/{creditNumber}

Method DELETE  
http://localhost:4200/api/credit/{creditNumber}
