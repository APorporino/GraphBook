#Author(s): Karl Doumar, Andrei Guevorkian

Feature: Logout

  As a user of the GraphBook, I would like to be able to logout from GraphBook so that I am no longer seen as available by
  other users

  Background:
    Given that I am a registered user of GraphBook
    And I am logged into GraphBook

    Scenario: Logout from GraphBook (Normal Flow)
      Given the user is logged into GraphBook
      When the user requests to logout
      Then the system logs the user out
      