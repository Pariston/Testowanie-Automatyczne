Scenario: User searches for a single step
 
Given user is on Home page
When user opens Wykopalisko link
Then Wykopalisko page is shown

When user clicks OpenSearch button
Then Search input is shown

When user types Pooffy in Search input and clicks button
Then Search result is shown

Given user is on Login page
When user types invalid data - Pooffy and tajnehaslo
Then page title is Wykop.pl - Zaloguj się and login error message Niepoprawny login lub hasło is shown

When user types valid data - Pooffy and dlatestu
Then user should be logged in

Given user is on Register page
When user types used login - Pooffy
Then page title is Wykop.pl - Rejestracja użytkownika and register error message Wybrany login jest zajęty is shown

Given user is on Settings page - if not logged in, then do using Pooffy and dlatestu
When user changes color version
Then selected color should be changed