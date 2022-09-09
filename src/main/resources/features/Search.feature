@SmokeTesting
Feature: User should be able to reach any subject

  Scenario: User search about any subject in English

    Given User navigates to Nagwa website

    When Display language is English

    And User click on search icon

    When User enter search keyword

    And User can browse any subject

    When User open subject worksheet

    Then User can see all questions in the worksheet