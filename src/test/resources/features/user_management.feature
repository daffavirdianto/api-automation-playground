Feature: Automation of user management

  Scenario Outline: Create a new user
    When I send a registration request with:
      | name          | <name>          |
      | email         | <email>         |
      | password      | <password>      |
      | firstname     | <firstname>     |
      | lastname      | <lastname>      |
      | company       | <company>       |
      | address1      | <address1>      |
      | country       | <country>       |
      | zipcode       | <zipcode>       |
      | state         | <state>         |
      | city          | <city>          |
      | mobile_number | <mobile_number> |
    Then the user should be created successfully

    Examples:
      | name            | email                     | password          | firstname | lastname  | company    | address1       | country   | zipcode | state     | city     | mobile_number |
      | Daffa Virdianto | daffa.virdianto@gmail.com | securepassword123 | Daffa     | Virdianto | Playground | Jl. Raya No. 1 | Indonesia |   12345 | Indonesia | Semarang |   +1234567890 |

  Scenario Outline: Login with valid credentials
    When I send a login request with:
      | email    | <email>    |
      | password | <password> |
    Then the user should be logged in successfully

    Examples:
      | email                     | password          |
      | daffa.virdianto@gmail.com | securepassword123 |

  Scenario Outline: Update user account information
    When I send an update account request with:
      | email         | <email>         |
      | password      | <password>      |
      | name          | <name>          |
      | firstname     | <firstname>     |
      | lastname      | <lastname>      |
      | company       | <company>       |
      | address1      | <address1>      |
      | country       | <country>       |
      | zipcode       | <zipcode>       |
      | state         | <state>         |
      | city          | <city>          |
      | mobile_number | <mobile_number> |
    Then the user account should be updated successfully

    Examples:
      | name                | email                     | password          | firstname | lastname  | company    | address1       | country   | zipcode | state     | city     | mobile_number |
      | Daffa Virdianto New | daffa.virdianto@gmail.com | securepassword123 | Daffa     | Virdianto | Playground | Jl. Raya No. 1 | Indonesia |   12345 | Indonesia | Semarang |   +1234567890 |

  Scenario Outline: Get user details by email
    When I send a get user detail request with email "<email>"
    Then the response should match the "schema/user-detail-schema.json" JSON schema

    Examples:
      | email                     |
      | daffa.virdianto@gmail.com |

  Scenario Outline: Delete user account
    When I send a delete account request with:
      | email    | <email>    |
      | password | <password> |
    Then the user account should be deleted successfully

    Examples:
      | email                     | password          |
      | daffa.virdianto@gmail.com | securepassword123 |
