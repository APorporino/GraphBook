@tag
Feature: View Personal Connection Graph

As a user
I would like to view my personal connection graph
So that I can see my connections and the relationship with them

  @tag1
  Scenario: View Personal Connection Graph (Normal Flow)
		Given a user is logged into the GraphBook system
    And they has at least one connection
    When going in their profile page
    Then their personal connection graph is displayed
    
  @tag2
  Scenario: View Empty Personal Connection Graph (Alternate Flow)
    Given a user is logged into the GraphBook system
    And they has no connections
    When going in their profile page
    Then an empty graph is displayed

  @tag3
  Scenario: Attempt To View Personal Connection Graph While Logged Out (Error Flow)
    Given a user is logged out of the GraphBook system
    When going in their profile page
    Then an error message notifies the user that they need to log in

