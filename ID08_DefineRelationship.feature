@tag
Feature: Define Relationship with another user

  As a user of Graph Book
  I would like to define my relationship with another user as strong, medium or none
  So that I can use Graph Book to its full potential
  
  @tag1
   Scenario: Define a (strong,medium or none) relationship with user X (Normal Flow)
   	Given A logged in user selects another user X
    When They define their relationship with that user (strong/medium/none)
    Then The GraphBook system records this directional relationship

  @tag2
  Scenario: Redefine a (strong,medium or none) relationship with user X (Alternative Flow)
    Given A logged in user selects another user X
    And A relationship definition already exists
    When They redefine their relationship with that user (strong/medium/none)
    Then The GraphBook system overwrites the previous relationship with the new one 
    
  @tag3
  Scenario: Define or redefine a (strong,medium or none) relationship with user X with no internet (Error Flow)
    Given A logged in user selects another user X
    And There is no access to the database(no connection)
    When They define or redefine their relationship with another user (strong/medium/none)
    Then The GraphBook system detects that the database cannot be reached and gives an error message

