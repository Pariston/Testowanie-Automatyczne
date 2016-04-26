Scenario: User searches for a single step
 
Given user is on Home page
When user opens Wykopalisko link
Then Wykopalisko page is shown

When user clicks OpenSearch button
Then Search input is shown

When user types Pooffy in Search input and clicks button
Then Search result is shown

Given user is on Login page
When user types invalid data
Then login error message is shown

When user types valid data
Then user should be logged in

Given user is on Register page
When user types used login
Then register error message is shown

Given user is on Settings page
When user changes color version
Then selected color should be changed