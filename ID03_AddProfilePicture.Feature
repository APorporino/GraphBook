@tag
Feature: Create User Account

  As a university student
  I would like to add a profile picture for my GraphBook profile
  So that others and including myself can see my profile picture

  @tag1
  Scenario Outline: (Normal Flow)

    Given An existing GraphBook user without a profile picture
    When A user adds a profile picture
    Then the user's profile picture is added to their account

  @tag2
  Scenario Outline: (Alternative Flow)

    Given An existing GraphBook user with a profile picture
    When  A user adds a profile picture
    Then The user's profile picture is swapped with the added one

  @tag3
  Scenario Outline: Existing user attempts to become a user (Error Flow)

    Given An existing GraphBook user with or without a profile picture
    When  A user adds an invalid profile picture
    Then an error message is shown to upload a photo with the correct format