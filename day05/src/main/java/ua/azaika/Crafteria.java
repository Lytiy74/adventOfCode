package ua.azaika;

import java.util.Arrays;
import java.util.List;

public class Crafteria {
    private final static String INPUT = """
            3-5
            10-14
            16-20
            12-18
            
            1
            5
            8
            11
            17
            32
            """;

    public static void main(String[] args) {
        Crafteria crafteria = new Crafteria();
        int solve = crafteria.solve(INPUT);
        System.out.println(solve);
    }

    public int solve(String input) {
        List<String> freshRanges = Arrays.stream(input.substring(0,input.indexOf("\n\n")).split("\n")).toList();
        List<Long> ids = Arrays.stream(input.substring(input.indexOf("\n\n")+2).split("\n")).map(Long::parseLong).toList();
        int freshCount = 0;

        for (long id : ids) {
            if(isFresh(id,freshRanges)) freshCount++;
        }

          return freshCount;
    }

    private boolean isFresh(long id, List<String> freshRanges) {
        for (String range : freshRanges) {
            long lowerBound = Long.parseLong(range.split("-")[0]);
            long upperBound = Long.parseLong(range.split("-")[1]);
            if (id >= lowerBound && id <= upperBound) return  true;
        }
        return  false;
    }
}