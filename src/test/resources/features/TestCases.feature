Feature: Andersen Website Functionality

  Scenario: User attempts to log in with incorrect credentials
    Given the user is on the login page
    When the user enters incorrect email "subsatura@gmail.com" and password "Sample@124"
    Then an error message should be displayed

  Scenario: User is redirected to the main page after successful login
    Given the user is on the login page
    When the user enters valid email "subsatura@gmail.com" and password "21121488" and submits the form
    Then the user should be redirected to the main page

  Scenario: User is redirected to the main page if logged in and tries to access registration
    Given the user is logged in
    When the user tries to access the registration page
    Then the user should be redirected to the main page
