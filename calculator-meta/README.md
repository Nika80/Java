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

3. Запуск теста [EndToEndTest](./src/test/java/EndToEndTest.java)

## Микросервисы

1. adder - складывает два числа (GET /{a}/{b})
2. divider - делит два числа (GET /{a}/{b})
3. multiplier - умножает два числа (GET /{a}/{b})
4. subtractor - вычитает из одного числа другое (GET /{a}/{b})