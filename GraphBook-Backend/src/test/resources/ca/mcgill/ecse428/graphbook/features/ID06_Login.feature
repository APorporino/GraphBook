#Author: Ketan Rampurkar

@tag
Feature: Login on Graphbook
  As a university student 
  I want to login on Graphbook
  so that I can start marking people off that I know on the website.

  @tag1
  Scenario: (Normal flow) Login using email address
    Given I have an existing Graphbook account
    When I sign in using existing account's email address
    And corresponding account password
    Then I should be logged in the Graphbook system.
  
  @tag2
  Scenario: (Normal flow) Login using username
    Given I have an existing Graphbook account
    When I sign in using existing account's username
    And corresponding account password
    Then I should be logged in the Graphbook system.
    
  @tag3
  Scenario: (Error flow) Invalid login credentials
    Given I have an existing Graphbook account
    When I sign in with invalid login credentials
    Then I should be prompted with an error message
    And I should be asked to re-enter my login information.
