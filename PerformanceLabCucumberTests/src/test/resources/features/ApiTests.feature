Feature: ApiTests
  Апи тесты

  Scenario: Сортировка пользователей через API
    Given Api. Получить токен
    When Api. Проверить сортировку списка в порядке возрастания
    And Api. Проверить сортировку списка в порядке убывания