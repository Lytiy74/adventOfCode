package ua.azaika.day01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class SecretEntrance {

    public static final int START_POSITION = 50;
    public static final int RIGHT_EDGE = 99;
    public static final int LEFT_EDGE = 0;
    public static final int MODULO_BASE = 100;

    public static void main(String[] args) {
        InputStream inputStream = SecretEntrance.class.getResourceAsStream("/input.txt");
        Queue<String> steps = readInputStreamToQueue(inputStream);
        SecretEntrance secretEntrance = new SecretEntrance();

        int answer = secretEntrance.getAnswer(steps);

        System.out.println(answer);
    }

    private int getAnswer(Queue<String> steps) {
        int answer = 0;
        int position = START_POSITION;

        for (String step : steps) {
            char direction = step.charAt(0);
            short stepsCount = Short.parseShort(step.substring(1));
            switch (direction) {
                case 'R':
                    position += stepsCount;
                    break;
                case 'L':
                    position -= stepsCount;
                    break;
            }

            position = normalizePosition(position);

            if (position == 0) {
                answer++;
            }

            System.out.println("The dial is rotated " + step + " to point at " + position);
        }
        return answer;
    }

    private int normalizePosition(int position) {
        if (position > RIGHT_EDGE) {
            position = position % MODULO_BASE;
        } else if (position < LEFT_EDGE) {
            position = (position % MODULO_BASE + MODULO_BASE) % MODULO_BASE;
        }
        return position;
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
