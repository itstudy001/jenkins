Feature: jenkins
    Scenario: Sum two numbers
        Given I have two numbers: 1 and 2
        When the jenkins sums them
        Then I receive 3 as a result