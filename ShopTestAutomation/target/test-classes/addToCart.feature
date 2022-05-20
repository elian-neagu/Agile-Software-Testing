
@Test
Feature: Add products to cart

  Scenario:  Add First Most Expensive Products to the cart
    Given Bob Navigates to Category Menu
    When Bob click on the "Telefoane mobile si Accesorii" category
    And  Bob clicks on the "Telefoane mobile" subcategory
    And  Bob filters for "Most Expensive" filter
    And  Bob scrolls page to Add to Cart button
    And  Bob clicks on the first available "Add to cart" button
    Then Item should be added to the cart

  Scenario: Add Second Most Expensive Product to the cart
    Given Bob is still in the search page with filters
    And  Bob scrolls page to Add to Cart button
    When Bob clicks on the second available "Add to cart" button
    Then The Item Should be added to the cart
