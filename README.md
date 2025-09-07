# 🚇 Transport AOP Console

**Java + Spring Boot + AOP**  
Консольное приложение про иерархию:

`Транспортное средство → Автомобиль / Поезд → Экспресс`

- ✅ Валидация ввода  
- ✅ Полиморфная коллекция  
- ✅ Консольное меню (добавление / удаление / вывод / сравнение)  
- ✅ AOP-логгер (метод, параметры, результат, исключения)

---

## 📦 Требования

- **JDK 17+**  
  Проверить:  
  ```bash
  java -version
Maven 3.8+
Проверить:

bash
Copy code
mvn -version
🚀 Сборка и запуск
Клонирование:

bash
Copy code
git clone https://github.com/<user>/<repo>.git
cd <repo>
Сборка (без тестов):

bash
Copy code
mvn -q -DskipTests package
Запуск:

bash
Copy code
java -jar target/transport-aop-console-1.0.0.jar
🖥️ Консольное меню
После запуска доступно меню:

➕ Добавить новый элемент

➖ Удалить элемент по индексу

📋 Вывести все элементы

⚖️ Сравнить два элемента (по индексам)

🚪 Выход

🔒 Ввод чисел проверяется на корректность (например: скорость 0..1200, вагоны 1..40, приоритет 1..5).
При ошибках ввод повторяется.

📂 Структура проекта
swift
Copy code
src/main/java/com/example/transport
 ├── domain/
 │    ├── Transport      // базовый класс: name, maxSpeed
 │    ├── Car            // места
 │    ├── Train          // вагоны
 │    └── Express        // priorityLevel
 │
 ├── service/
 │    └── TransportService // List<Transport>, add/remove/list/compare
 │
 ├── aop/
 │    └── LoggingAspect    // @Around, @AfterThrowing
 │
 ├── app/
 │    ├── ConsoleApp        // консольное меню
 │    └── TransportApplication // точка входа Spring Boot
 └── pom.xml
📜 Как устроен AOP-логгер
Используется аспект с советами:

@Around — оборачивает вызовы методов, пишет:

имя метода

список параметров

возвращаемое значение

время выполнения (мс)

@AfterThrowing — фиксирует необработанные исключения

🎯 Зачем:

централизованное логирование без засорения бизнес-кода

диагностика (видно, что и с какими аргументами вызывалось)

🧹 Ввод и валидация
Текст: запрещены пустые строки (strip())

Числа: проверка на int и диапазон:

Transport.maxSpeed: 0..1200

Car.seats: 1..9

Train.carriages: 1..40

Express.priorityLevel: 1..5

При нарушении:

IllegalArgumentException (в сеттерах доменных классов)

повторный запрос ввода (в ConsoleApp)

⚖️ Равенство, хэш, печать
equals() и hashCode() переопределены

Контракт: если a.equals(b) == true, то a.hashCode() == b.hashCode()

toString() → человекочитаемый вывод для консоли

🛠️ Примеры команд
Сборка:

bash
Copy code
mvn -q -DskipTests package
Запуск:

bash
Copy code
java -jar target/transport-aop-console-1.0.0.jar
Логи (пример):

pgsql
Copy code
Enter TransportService.add(..) args=[Car{name=BMW, seats=4}]
Exit TransportService.add(..) result=true timeMs=1
sql
Copy code
Enter ConsoleApp.removeAt(..) args=[2]
Exit ConsoleApp.removeAt(..) result=null timeMs=0
💡 Проект демонстрирует использование AOP-логирования и валидации в Java-консольном приложении.
