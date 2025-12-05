package ua.azaika;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Lobby {
    public static void main(String[] args) {
        List<String> blocksOfBatteries = readInputStreamToQueue(Lobby.class.getResourceAsStream("/input.txt"));
        long answer = 0L;
        long answer2 = 0L;
        for (String block : blocksOfBatteries) {
            answer += solve(block);
            answer2 += Long.parseLong(solvePartTwo(block));
        }
        System.out.println(answer);
        System.out.println(answer2);
    }

    private static long solve(String block) {
        char[] charArray = block.toCharArray();
        int current;
        int next;
        int max = 0;
        for (int i = 0; i < charArray.length-1; i++) {
            current = Integer.parseInt(String.valueOf(charArray[i]));
            for (int j = i+1; j < charArray.length; j++) {
                next = Integer.parseInt(String.valueOf(charArray[j]));
                if (Integer.parseInt("" + current + next) > max) max = Integer.parseInt("" + current + next);
            }
        }
        System.out.println(max  );
        return max;
    }

    private static String solvePartTwo(String block) {
        int N = block.length();
        int M = 12; // Кількість цифр, які потрібно залишити
        int K = N - M; // Кількість цифр, які потрібно видалити

        if (K < 0) {
            // Рядок закороткий, повертаємо його повністю (якщо M=12, а N<12, але за умовою N >= 12)
            return block;
        }

        // Використовуємо StringBuilder як стек для побудови результату
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N; i++) {
            char currentDigit = block.charAt(i);

            // Крок 1: Видаляємо менші попередні цифри (жадібний вибір)
            while (K > 0 && result.length() > 0 && result.charAt(result.length() - 1) < currentDigit) {
                result.deleteCharAt(result.length() - 1);
                K--;
            }

            // Крок 2: Додаємо поточну цифру
            result.append(currentDigit);
        }

        // Крок 3: Якщо K все ще більше 0, відкидаємо K останніх (найменш значущих) цифр
        if (K > 0) {
            result.setLength(result.length() - K);
        }

        // Перевірка, що довжина результату дорівнює 12
        if (result.length() > M) {
            result.setLength(M);
        }

        return result.toString();
    }


    public static List<String> readInputStreamToQueue(InputStream inputStream) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String s;
            while ((s = br.readLine()) != null) {
                result.add(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}