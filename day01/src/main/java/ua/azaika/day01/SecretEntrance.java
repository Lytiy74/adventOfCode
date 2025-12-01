package ua.azaika.day01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class SecretEntrance {

    public static void main(String[] args) {
        InputStream inputStream = SecretEntrance.class.getResourceAsStream("/input.txt");
        Queue<String> steps = readInputStreamToQueue(inputStream);
        SecretEntrance secretEntrance = new SecretEntrance();

        int answer = secretEntrance.getAnswer(steps);

        System.out.println(answer);
    }

    private int getAnswer(Queue<String> steps) {
        int answer = 0;
        int current = 50;
        for (String step : steps) {
            int stepsCount = Integer.parseInt(step.substring(1));
            for (int i = 0; i < stepsCount; i++) {
                if (step.startsWith("L")) {
                    current = (current - 1 + 100) % 100;
                } else {
                    current = (current + 1) % 100;
                }
                if (current == 0) answer++;
            }
        }
        return answer;
    }

    public static Queue<String> readInputStreamToQueue(InputStream inputStream) {
        Queue<String> result = new ArrayDeque<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
