
Service for managing the city parking spaces.
=============================================

Model desription and assumpions are written down in `model_requirements.odt` file.

Run the tests
------------------

Navigate to project root directory (`parking-management`).<br>
```bash
	mvn test
```

Tests are also performed by Travis CI after each git push.
To view Travis CI tests results:
Navigate to: 
		https://travis-ci.com/Patrolfr/parking-management

Build:<br>
-----------
Navigate to project root directory (`parking-management`).
```bash
	./start.sh
```

Test in the browser
-------------------
Check fake customer's user stories created with data.sql script with H2DB console:<br>
		http://localhost:8080/h2-console
* Driver Class:	`org.h2.Driver`
* JDBC URL: `jdbc:h2:mem:testdb`
* User Name: `sa`


