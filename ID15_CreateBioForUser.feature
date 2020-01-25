@tag
Feature: Create User Bio

As a user fo GraphBook
I would like to create a short user bio for a user profile
So that other users can know a little more about another user

  @tag1
  Scenario: Add a short user bio (Normal Flow)
    Given a user of GraphBook
    And this user is logged in 
    When the user edits the bio input field
    And presses the save/submit button
    Then I validate the content and length of the bio
    And the content of the bio is assigned and stored to this particular user

  @tag2
  Scenario Outline: Adding an empty user bio (Alternate Flow)
    Given a user enters an empty bio
    When a user submits the form
    Then the user's previously saved bio is replaced with a blank value
    
  @tag3
  Scenario Outline: Attempting to add a bio which is too long (Error Flow)
    Given a user enters a bio
    And the length of the bio is greater than the defined bio length limit
    When the user submits the form
    Then the user receives a warning message saying he/she must reduce the number of characters entered
    And the bio is not stored to the user's account