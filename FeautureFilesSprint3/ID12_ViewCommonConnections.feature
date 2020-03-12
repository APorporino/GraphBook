@tag
Feature: Create User Account

As a university student
I would like to become a user of GraphBook.
So that I can use all of GraphBook's feautures
	
	@tag1
	Scenario: (Normal Flow)

	Given a unique <username>, <password>
	And <firstName>, <lastName> 
	When A person requests to become a user
	Then the persons credentials are stored and he becomes a user

	@tag2
	Scenario: (Alternative Flow)

	Given a unique <username>, <password>
	When a person submits a requests to become a user
	Then the persons credentials are stored and he becomes a user

	@tag3
	Scenario: Existing user attempts to become a user (Error Flow)

	Given an existing <username>, <password>
	When a person requests to become a user
	Then an error message is shown and person's credentials are not saved
