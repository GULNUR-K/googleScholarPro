@regression

Feature:Home Page

  Scenario:Basic search with a valid keyword
    Given  user is on main page of Google Scholar
    Then   user should be able to enter some valid keywords in the search bar
    And    user should click on the search button
    Then   user verifies that relevant search results are displayed