package ua.azaika;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GiftShop {
    public static void main(String[] args) {
        String strings = readInputStreamToQueue(GiftShop.class.getResourceAsStream("/input.txt"));
        GiftShop giftShop = new GiftShop();
        long solve = giftShop.solveFirstPart(strings);
        long solve2 = giftShop.solveSecondPart(strings);
        System.out.println(solve);
        System.out.println(solve2);
    }

    private long solveFirstPart(String string) {
        String[] ranges = string.split(",");

        long answer = 0;

        for (String range : ranges) {

            String[] bounds = range.split("-");
            long min = Long.parseLong(bounds[0]);
            long max = Long.parseLong(bounds[1]);

            for (long i = min; i <= max; i++) {
                if (isInvalidPart1(String.valueOf(i))) {
                    answer += i;
                }
            }

        }

        return answer;
    }

    private long solveSecondPart(String string) {
        String[] ranges = string.split(",");

        long answer = 0;

        for (String range : ranges) {
            String[] bounds = range.split("-");
            long min = Long.parseLong(bounds[0]);
            long max = Long.parseLong(bounds[1]);

            for (long i = min; i <= max; i++) {
                if (isInvalidPart2(String.valueOf(i))) {
                    System.out.println(i);
                    answer += i;
                }
            }
        }
        return answer;
    }

    private boolean isInvalidPart2(String id){
            for (int i = 0; i < id.length() - 1; i++) {
                String substring = id.substring(0, i + 1);
                String repeat = substring.repeat(id.length() / substring.length());
                if (repeat.equals(id)) return true;
            }
            return false;
    }

    private boolean isInvalidPart1(String id) {
        if (id.length() % 2 == 1) return false;

        String sb1 = id.substring(0, id.length() / 2);
        String sb2 = id.substring(id.length() / 2);

        return sb1.equals(sb2);
    }


    public static String readInputStreamToQueue(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}