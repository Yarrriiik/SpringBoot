Transport AOP Console (Java, Spring Boot, AOP)
Консольное Java‑приложение про иерархию “Транспортное средство → Автомобиль/Поезд → Экспресс” с валидацией ввода, полиморфной коллекцией и меню (добавление/удаление/вывод/сравнение), а также АОП‑логгером, который пишет имя метода, параметры, возвращаемое значение и необработанные исключения.

Требования
JDK 17+ (проверить командой: java -version)

Maven 3.8+ (проверить командой: mvn -version)

Сборка и запуск из командной строки
Клонирование:

git clone https://github.com/<user>/<repo>.git

cd <repo>

Сборка (без тестов):

mvn -q -DskipTests package

Запуск:

java -jar target/transport-aop-console-1.0.0.jar

После запуска появится консольное меню:

Добавить новый элемент

Удалить элемент по индексу

Вывести все элементы

Сравнить два элемента (по индексам)

Выход

Ввод чисел проверяется на корректность формата и на диапазон (например, скорость 0..1200, вагоны 1..40, приоритет 1..5). При ошибках ввод повторяется.

Структура проекта
pom.xml — конфигурация Maven (зависимости Spring Boot и AOP, плагин сборки)

src/main/java/com/example/transport

domain/

Transport — базовый класс: name (String), maxSpeed (int), валидация, equals/hashCode/toString

Car — модель, места; валидация, equals/hashCode/toString

Train — линия, вагоны; валидация, equals/hashCode/toString

Express — serviceClass, priorityLevel; валидация, equals/hashCode/toString

service/

TransportService — коллекция List<Transport> и операции: add/removeAt/listAll/equalsByIndex/size

aop/

LoggingAspect — аспект логирования @Aspect (@Around, @AfterThrowing)

app/

ConsoleApp — консольное меню, ввод и валидация

TransportApplication — точка входа Spring Boot (main)

Как устроен АОП‑логгер
Используется аспект с советами:

@Around — оборачивает вызовы методов сервисов и консольного класса. Пишет:

имя метода

список параметров

возвращаемое значение

время выполнения (мс)

@AfterThrowing — фиксирует необработанные исключения

Зачем это нужно:

централизованное логирование без засорения бизнес‑кода

упрощение диагностики (видно, что вызывалось и с какими аргументами)

Гарантии:

аспекты применяются к Spring‑бинам (классы помечены @Component/@Service)

точка сканирования — пакет com.example.transport

Ввод и валидация
Текст: запрещены пустые строки; строка “подчищается” методом strip()

Числа: проверяется, что введено целое число и что оно в нужном диапазоне:

Transport.maxSpeed: 0..1200

Car.seats: 1..9

Train.carriages: 1..40

Express.priorityLevel: 1..5

При нарушении условий выбрасывается IllegalArgumentException (в доменных сеттерах) либо запрашивается повторный ввод (в ConsoleApp)

Равенство, хэш, печать
equals() и hashCode() переопределены во всех доменных классах

контракт: если a.equals(b) == true, то a.hashCode() == b.hashCode()

сравнение строится из полей родителя + собственных полей класса

toString() возвращает человекочитаемую строку, используемую при выводе списка элементов

Примеры команд
Сборка: mvn -q -DskipTests package

Запуск: java -jar target/transport-aop-console-1.0.0.jar

Логи (консоль): при каждом действии видны строки вида:

Enter Class.method(..) args=[...]

Exit Class.method(..) result=... timeMs=...
