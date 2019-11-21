Feature: Book Service API Test

  Scenario: Test Book Title
  Given url demoBaseUrl
  And path 'bookTitle'
  When method get
  Then status 200
  And match response == 'DUMMY'