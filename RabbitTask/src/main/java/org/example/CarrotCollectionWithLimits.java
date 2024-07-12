package org.example;

import java.util.Arrays;

public class CarrotCollectionWithLimits {

    /**
     * Сделал Рахимбердиев Темур Джамалович

     * Техническое задание. Время для решение задачи до 12.07.2024 до 19.00
     * Задача такая "Составить эффективный алгоритм":
     * Есть 5 полянок. На каждой полянке огород с морковкой.
     * На первой полянке морковь массой в 1 кг, на второй полянке - морковь массой в 2 кг,
     * на третьей полянке - морковь массой в 3 кг, на 4 полянке - морковь массой в 4 кг,
     * на пятой полянке - морковь массой в 5 кг. Зайцу необходимо за 10 ходок унести максимальное количество моркови с полянок за 1 день,
     * причем заяц за раз может взять только 5 кг моркови. Необходимо составить эффективный алгоритм, который самостоятельно должен найти решение на java.
     */

    public static void main(String[] args) {
        int[] fieldWeights = {1, 2, 3, 4, 5}; // Массив веса моркови на каждой полянке
        int weightPerTrip = 5; // Максимальный вес моркови за одну ходку (кг)
        int totalCarrotWeight = 0; // Общий вес моркови
        int remainingCarrotWeight = 0; // Оставшийся вес моркови
        int tripCount = 0; // Количество ходок
        int[] carrotFromField = new int[fieldWeights.length]; // Массив для хранения веса моркови, взятой с каждой поляны

        // Инициализация общего и оставшегося веса моркови
        for (int weight : fieldWeights) {
            totalCarrotWeight += weight;
        }
        remainingCarrotWeight = totalCarrotWeight;

        // Цикл сбора моркови (не более 10 ходок)
        while (remainingCarrotWeight > 0 && tripCount < 10) {
            // Выбор поляны с наибольшим оставшимся весом (не более weightPerTrip)
            int fieldIndex = selectFieldWithMaxCarrot(fieldWeights, weightPerTrip);

            // Если не найдено поле с морковью, выход из цикла
            if (fieldIndex == -1) {
                break;
            }

            int carrotToTake = Math.min(fieldWeights[fieldIndex], weightPerTrip); // Вес моркови, которую можно взять

            // Проверка ограничения по весу на полянке
            if (carrotToTake + carrotFromField[fieldIndex] > weightPerTrip) {
                carrotToTake = weightPerTrip - carrotFromField[fieldIndex];
            }

            // Обновление веса моркови
            remainingCarrotWeight -= carrotToTake;
            fieldWeights[fieldIndex] -= carrotToTake; // Уменьшение оставшегося веса на поле
            carrotFromField[fieldIndex] += carrotToTake;
            tripCount++;

            // Вывод информации о текущей ходке
            System.out.println("Ходка " + tripCount + ":");
            System.out.println("Полянка " + (fieldIndex + 1) + ": взято " + carrotToTake + " кг");
            System.out.println("Оставшийся вес на полянках: " + Arrays.toString(fieldWeights));
            System.out.println("Собрано с полянок: " + Arrays.toString(carrotFromField));
            System.out.println();
        }

        // Добавление ходок, если осталось меньше 10
        while (tripCount < 10) {
            tripCount++;
            System.out.println("Ходка " + tripCount + ":");
            System.out.println("Нет доступной моркови для сбора.");
            System.out.println();
        }

        // Вывод итоговой информации о сборе моркови
        System.out.println("Количество ходок: " + tripCount);
        System.out.println("Собрано моркови: " + (totalCarrotWeight - remainingCarrotWeight));
        System.out.println("Морковь с каждой поляны:");
        for (int i = 0; i < fieldWeights.length; i++) {
            System.out.println("Полянка " + (i + 1) + ": " + carrotFromField[i] + " кг");
        }
    }

    /**
     * Выбирает поле с наибольшим оставшимся весом моркови, не превышающим weightPerTrip.
     */
    private static int selectFieldWithMaxCarrot(int[] fieldWeights, int weightPerTrip) {
        int maxIndex = -1;
        for (int i = 0; i < fieldWeights.length; i++) {
            if (fieldWeights[i] > 0 && fieldWeights[i] <= weightPerTrip && (maxIndex == -1 || fieldWeights[i] > fieldWeights[maxIndex])) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}
