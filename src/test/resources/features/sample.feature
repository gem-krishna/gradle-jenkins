Feature: Google Search

  Scenario: Search for something on Google
    Given I am on the Google search page
    When I search for "Serenity BDD"
    Then I should see results containing "Serenity"
