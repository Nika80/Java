# Микросервисный калькулятор

## Сборка

1. Убедиться, что установлен и запущен Docker

2. Сборка и навешивание тегов на все образы

```shell
cd adder
docker build -t adder:latest .
cd ../multiplier
docker build -t multiplier:latest .
cd ../divider
docker build -t divider:latest .
cd ../subtractor
docker build -t subtractor:latest .
```

3. Запуск теста [CalculatorIntegrationTest](./src/test/java/CalculatorIntegrationTest.java)

## Микросервисы

1. [adder](./adder) - складывает два числа (GET /{a}/{b}), работает на порту 8081
2. [divider](./divider) - делит два числа (GET /{a}/{b}), работает на порту 8083
3. [multiplier](./multiplier) - умножает два числа (GET /{a}/{b}), работает на порту 8084
4. [subtractor](./subtractor) - вычитает из одного числа другое (GET /{a}/{b}), работает на порту 8082