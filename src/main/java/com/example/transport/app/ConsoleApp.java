package com.example.transport.app;

import com.example.transport.domain.Car;
import com.example.transport.domain.Express;
import com.example.transport.domain.Train;
import com.example.transport.domain.Transport;
import com.example.transport.service.TransportService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApp {

    private final TransportService service;

    public ConsoleApp(TransportService service) {
        this.service = service;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Выберите пункт: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> addElement(sc);
                    case "2" -> removeByIndex(sc);
                    case "3" -> printAll();
                    case "4" -> compareByIndexes(sc);
                    case "5" -> { running = false; System.out.println("Завершение работы."); }
                    default -> System.out.println("Неизвестная команда.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                -------------------------------
                1) Добавить новый элемент
                2) Удалить элемент по индексу
                3) Вывести все элементы
                4) Сравнить два элемента (по индексам)
                5) Выход
                -------------------------------""");
    }

    private void addElement(Scanner sc) {
        System.out.println("Выберите тип: 1) Транспортное средство 2) Автомобиль 3) Поезд 4) Экспресс");
        String type = sc.nextLine().trim();
        try {
            switch (type) {
                case "1" -> {
                    String name = askText(sc, "Название: ");
                    int speed = askInt(sc, "Макс. скорость (0..1200): ", 0, 1200);
                    service.add(new Transport(name, speed) {});
                }
                case "2" -> {
                    String name = askText(sc, "Название: ");
                    int speed = askInt(sc, "Макс. скорость (0..1200): ", 0, 1200);
                    String model = askText(sc, "Модель: ");
                    int seats = askInt(sc, "Число мест (1..9): ", 1, 9);
                    service.add(new Car(name, speed, model, seats));
                }
                case "3" -> {
                    String name = askText(sc, "Название: ");
                    int speed = askInt(sc, "Макс. скорость (0..1200): ", 0, 1200);
                    String line = askText(sc, "Линия/Маршрут: ");
                    int carriages = askInt(sc, "Кол-во вагонов (1..40): ", 1, 40);
                    service.add(new Train(name, speed, line, carriages));
                }
                case "4" -> {
                    String name = askText(sc, "Название: ");
                    int speed = askInt(sc, "Макс. скорость (0..1200): ", 0, 1200);
                    String line = askText(sc, "Линия/Маршрут: ");
                    int carriages = askInt(sc, "Кол-во вагонов (1..40): ", 1, 40);
                    String svc = askText(sc, "Класс сервиса: ");
                    int prio = askInt(sc, "Приоритет (1..5): ", 1, 5);
                    service.add(new Express(name, speed, line, carriages, svc, prio));
                }
                default -> System.out.println("Неизвестный тип.");
            }
            System.out.println("Элемент добавлен. Всего: " + service.size());
        } catch (IllegalArgumentException ex) {
            System.out.println("Валидация не пройдена: " + ex.getMessage());
        }
    }

    private void removeByIndex(Scanner sc) {
        int idx = askInt(sc, "Индекс для удаления: ", 0, Integer.MAX_VALUE);
        var removed = service.removeAt(idx);
        System.out.println("Удалено: " + removed);
    }

    private void printAll() {
        List<Transport> list = service.listAll();
        if (list.isEmpty()) System.out.println("Список пуст.");
        else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("[%d] %s%n", i, list.get(i));
            }
        }
    }

    private void compareByIndexes(Scanner sc) {
        int i1 = askInt(sc, "Индекс 1: ", 0, Integer.MAX_VALUE);
        int i2 = askInt(sc, "Индекс 2: ", 0, Integer.MAX_VALUE);
        boolean eq = service.equalsByIndex(i1, i2);
        System.out.println("Равны: " + eq);
    }

    private String askText(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isBlank()) return s;
            System.out.println("Строка не должна быть пустой.");
        }
    }

    private int askInt(Scanner sc, String prompt, int minInclusive, int maxInclusive) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(s);
                if (val < minInclusive || val > maxInclusive) {
                    System.out.printf("Число вне диапазона [%d..%d].%n", minInclusive, maxInclusive);
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число.");
            }
        }
    }
}
