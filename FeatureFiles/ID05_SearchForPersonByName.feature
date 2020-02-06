#Author: tylerjrwatson@gmail.com

@tag
Feature: Search For Person By Name
  A user wants to search for a user

  @tag1
  Scenario: Registered User Searches for an Existent User (Normal Flow)
    Given	user "Charlie Chaplin" is logged into the GraphBook system
    And the following users are registered in the GraphBook system:
    | user_name 		| full_name        | password |
		|alaus123   		|Alex Austin       |aa001     |
		|charchap5 		  |Charlie Chaplin   |cc002     |
		|aa99  	  			|Alex Austin 			 |123abc    |
		|daved45   			|Dave Danger       |dd004     |
		|ale005      		|Alex Austin       |70aa      |
		
    When user "Charlie Chaplin" searches for "Alex Austin"
    Then the following list of users is generated:
    | user_name 		| full_name        | password |
		|alaus123   		|Alex Austin       |aa001     |
		|aa99  	  			|Alex Austin 			 |123abc    |
		|ale005      		|Alex Austin       |70aa      |
		
  @tag2
  Scenario Outline: Registered User Searches for a Non-Existent User (Alternate Flow)
    Given user "Charlie Chaplin" is logged into the GraphBook system
    And the following users are registered in the GraphBook system:
    | user_name 		| full_name        | password |
		|alaus123   		|Alex Austin       |aa001     |
		|charchap5 		  |Charlie Chaplin   |cc002     |
		|aa99  	  			|Alex Austin 			 |123abc    |
		|daved45   			|Dave Danger       |dd004     |
		|ale005      		|Alex Austin       |70aa      |
    When user "Charlie Chaplin" searches for "Jimmy Flimmy"
    Then a message indicating "User Does Not Exist" is generated
      
 	
