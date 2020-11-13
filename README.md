# Choco-Factory-WebService
1 of 4 part from Chocolate Factory Website


## Prerequisites
1. Java 1.9+
1. [Jaxws-ri runtime](https://mvnrepository.com/artifact/com.sun.xml.ws/jaxws-rt/2.3.3) - [Direct Link](https://repo1.maven.org/maven2/com/sun/xml/ws/jaxws-rt/2.3.3/jaxws-rt-2.3.3.jar)
1. [MySQL connector](https://mvnrepository.com/artifact/mysql/mysql-connector-java) - [Direct link](https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.22/mysql-connector-java-8.0.22.jar)

## Setup
1. Populate database with `chocofactory20201111.sql`
1. Download [jaxws-ri](https://mvnrepository.com/artifact/com.sun.xml.ws/jaxws-rt/2.3.3)
1. Extract, copy all files under `lib` to `<repo_root>/WebContent/WEB-INF/lib`

## Compiling and Running
1. Compile all java file under `<repo_root>/src`
1. Run `com.chocofactory.webservice.FactoryPublisher`
1. Web Service will be hosted in [localhost:8080](http://localhost:8080/)

## Technical
### Database Schema
ChocoRequest(RequestID, ChocoID, Amount, Status, RequestDate)
ChocoStock(ChocoID, Amount)
IngredientStock(StockID, IngredientID, Amount, ExpiryDate)
Recipe(ChocoID, IngredientID, Amount)
Balance(Balance)

### Services (WIP)
- POST /addchoco
{
	"chocoid": integer,
	"ingredients":[
		{
			"id": integer,
			"amount": integer,
		},
		...
		{
			"id": integer,
			"amount": integer,
		}
	]
}
if chocoid not in recipe:
  return false
for every ingredients:
  if i_id not in GET Supplier/:
    return false

insert to Recipe : map chocoid with every ingredients
return true


- POST /addstock
{
	"chocoid": integer,
	"amount": integer
}
if amount < 0 or chocoid not in recipe:
  return false
insert to chocorequest (chocoid, amount, "pending")
return requestid


- GET /request{?id=integer}
if id not in ChocoRequest:
  return false

return chocorequest[id].status

- POST /processchoco
{
	"chocoid": integer,
	"amount": integer
}
fetch ingredients, amount_need for chocoid from Recipe
for every ingredient, amount_need:
  stock = sum (fetch amount WHERE kadaluarsa < NOW())
  can_make = stock // amount_need
  if can_make < amount:
    return false

for every ingredient, amount_need:
  fetch i_id, amount WHERE kadaluarsa < NOW(), id=ingredient ORDER BY kadaluarsa ASC
  for every i_id, amount:
    amount_need -= amount
    if amount_need < 0:
      update stock_id, amount = -amount_need
    else:
      (stok abis, amount = 0)
      delete row tsb
      
return true

- POST /sendchoco{?id=integer}
{
	"id": integer
}
if id not in ChocoRequest:
  return false

update id, status="Delivered"
return true

- POST /addbalance{?balance=integer}
{
	"balance": integer
}
if balance < 0:
  return false
update balance, balance += balance
return true

- GET /balance
return balance


## Author
[Jonathan Yudi Gunawan](https://github.com/JonathanGun/) - 13518084

## Acknowledgement
This project is made to fulfill IF3110 Website Development course
