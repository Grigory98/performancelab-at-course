Feature: LoginTests
  Тесты на авторизацию

  Scenario: Авторизация пользователя в системе
    Given DriverManager. Открыть браузер
    And LoginPage. Ввести логин пользователя user@pflb.ru
    And LoginPage. Ввести пароль пользователя user
    When LoginPage. Войти в систему
    Then LoginPage. Проверить, что авторизация произошла успешно