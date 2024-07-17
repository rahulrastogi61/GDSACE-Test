
@FormSubmission
Feature: Application Form Submission

  Background: : Navigate to Business Grants page
    Given User Navigates to My Grants Page
    Then User applies for New Grant
    And User selects sector "IT"
    And User selects I need this grant to "Bring my business overseas or establish a stronger international presence"
    And User selects assistance "Market Readiness Assistance"
    And User proceeds and Applies for grant

  @ac1
  Scenario: AC1 Submit Grant Application
    Given User is on Eligibility section
    And  User completes and saves all mandatory details on the Eligibility page
    And  User navigates,fills and saves all mandatory details on the Contact Details page
    And User navigates,fills and saves all mandatory details on the Proposal page
    And User navigates,fills and saves all mandatory details on the Business Impact page
    And User navigates,fills and saves all mandatory details on the Cost page
    When The user clicks on the declare review menu
    And User answers the declaration questions and accepts the acknowledgement
    And User reviews and submits the declaration
    Then User verify the read only Summary page and accepts Consent and Acknowledgement checkbox


  @ac2
  Scenario: AC2 Verify errors on the incomplete application
    Given User is on Eligibility section
    When The user clicks on the declare review menu
    And User click the Review button on declare review page
    Then Verify the count of actual and expected errors as below
      | Expected Count |
      | 5              |
      | 8              |
      | 7              |
      | 11             |
      | 1              |
      | 9              |

    #TODO
  @ac3
  Scenario: AC3 Verify the applicants details in the review summary page
    Given User is on Eligibility section
    And  User completes and saves all mandatory details on the Eligibility page
    And  User navigates,fills and saves all mandatory details on the Contact Details page
    And User navigates,fills and saves all mandatory details on the Proposal page
    And User navigates,fills and saves all mandatory details on the Business Impact page
    And User navigates,fills and saves all mandatory details on the Cost page
    When The user clicks on the declare review menu
    And User answers the declaration questions and accepts the acknowledgement
    And User reviews and submits the declaration
    #Then User verify the read only Summary page and accepts Consent and Acknowledgement checkbox
    And Verify all the Application details matches in the Review Summary page

  @ac4
  Scenario: AC4 Verify Consent and Acknowledgement checkbox on Review and Summary page
    Given User is on Eligibility section
    And  User completes and saves all mandatory details on the Eligibility page
    And  User navigates,fills and saves all mandatory details on the Contact Details page
    And User navigates,fills and saves all mandatory details on the Proposal page
    And User navigates,fills and saves all mandatory details on the Business Impact page
    And User navigates,fills and saves all mandatory details on the Cost page
    When The user clicks on the declare review menu
    And User answers the declaration questions and accepts the acknowledgement
    And User reviews and submits the declaration
    And User checks Consent and Acknowledgement checkbox is present on Review and Summary page

  @ac5
  Scenario: AC5 Submitting the application successfully
    Given User is on Eligibility section
    And  User completes and saves all mandatory details on the Eligibility page
    And  User navigates,fills and saves all mandatory details on the Contact Details page
    And User navigates,fills and saves all mandatory details on the Proposal page
    And User navigates,fills and saves all mandatory details on the Business Impact page
    And User navigates,fills and saves all mandatory details on the Cost page
    When The user clicks on the declare review menu
    And User answers the declaration questions and accepts the acknowledgement
    And User reviews and submits the declaration
    Then User verify the read only Summary page and accepts Consent and Acknowledgement checkbox
    And the User checks the Consent and Acknowledgement checkbox
    And User submits the Application and verifies the Success message box is displayed
    Then User is greeted a Success message box is displayed
    And the Agency details display Enterprise Singapore as the receiving Agency

  @ac6
  Scenario: AC6 Verify App ID
    Given User is on Eligibility section
    Given User completes and saves all mandatory details on the Eligibility page
    When The user clicks on the Contact Details menu
    And User navigates,fills and saves all mandatory details on the Contact Details page
    And User navigates,fills and saves all mandatory details on the Proposal page
    And User navigates,fills and saves all mandatory details on the Business Impact page
    And User navigates,fills and saves all mandatory details on the Cost page
    And User answers the declaration questions and accepts the acknowledgement
    And User reviews and submits the declaration
    Then User verify the read only Summary page and accepts Consent and Acknowledgement checkbox
    And User submits the Application and verifies the Success message box is displayed
    And User navigates to My Application table and verifies the Application is Shown in Processing Tab
