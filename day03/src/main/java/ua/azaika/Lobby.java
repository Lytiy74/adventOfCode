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
        for (String block : blocksOfBatteries) {
            answer += solve(block);
        }
        System.out.println(answer);
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