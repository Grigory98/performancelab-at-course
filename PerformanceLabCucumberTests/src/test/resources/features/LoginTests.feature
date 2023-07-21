Feature: LoginTests
  Я как пользователь,
  хочу авторизоваться в системе

  Scenario: Авторизация пользователя в системе
    Given DriverManager. Открыть браузер
    And LoginPage. Войти в систему под пользователем user@pflb.ru. Пароль: user
    Then LoginPage. Проверить, что авторизация произошла успешно