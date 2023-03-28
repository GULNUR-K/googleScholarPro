@jira-2255

Feature: Purchase An Item For Dresses

  @regression
  Scenario Outline: Purchase an item for dresses
    When user is on main page
    Then user should able to accept cookies
    And  user can choose "<category>" category from the nav menu
    Then user can select random 15 item
    And  store the product name as a string
    Then user can select random 3 product size
    And  user selects add to bag button
    Then selected product name appears in top cart bag on the top right
    Then select the bag icon on the top right
    Then user is on your bag page
    And  user should be able to see the selected product on your bag page
    Then stored the subtotal value in a string
    And  user can select proceed to checkout button
    Then user should able to input "userName"
    And  click continue button
    Then user should able to input "password"
    And  click continue button
    Then user bag subtotal should be equal the checkout subtotal
    And  user scrolls to the payment method section
    Then user can select a payment method "<method>"

    Examples:
      | category | method   |
      | dress    | paypal   |
      | figure   | clear    |
      | tops     | worldpay |

#  @smoke
#  Scenario: Purchase an item for figure
#    When user is on main page
#    Then user should able to accept cookies
#    And  user can choose "figure" category from the nav menu
#    Then user can select random 34 item
#    And  store the product name as a string
#    Then user can select random 2 product size
#    And  user selects add to bag button
#    Then selected product name appears in top cart bag on the top right
#    Then select the bag icon on the top right
#    Then user is on your bag page
#    And  user should be able to see the selected product on your bag page
#    Then stored the subtotal value in a string
#    And  user can select proceed to checkout button
#    Then user should able to input "username"
#    And  click continue button
#    Then user should able to input "password"
#    And  click continue button
#    Then user bag subtotal should be equal the checkout subtotal
#    And  user scrolls to the payment method section
#    Then user can select a payment method "paypal"

#    1)launch chromedriver
#    2)Navigate to https://www.prettylittlething.com
#    3)Choose random Category from the nav menu
#    4)Choose random item from PDP page
#    5)Store the Product name in a string
#    6)Choose random product size
#    7)Select add to bag
#    8)Assert the you product name against the product name in your bag page
#    9)Select the bag icon on the top right
#    10)Store the subtotal value in a string
#    11)Select Proceed to checkout
#    12)Enter username and select continue
#    12)Enter password and select continue
#    13)Assert the you bag subtotal against the checkout subtotal
#    14)Scroll to the payment method
#    15)Select a payment method
#    16)close browser