## CreditApp
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
