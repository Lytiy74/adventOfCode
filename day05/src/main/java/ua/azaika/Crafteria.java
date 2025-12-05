package ua.azaika;

import java.util.ArrayList;
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
        int solve = crafteria.solvePart1(INPUT);
        System.out.println(solve);
        System.out.println(crafteria.solvePart2(INPUT));
    }

    public int solvePart1(String input) {
        List<String> freshRanges = Arrays.stream(input.substring(0, input.indexOf("\n\n")).split("\n")).toList();
        List<Long> ids = Arrays.stream(input.substring(input.indexOf("\n\n") + 2).split("\n")).map(Long::parseLong).toList();
        int freshCount = 0;

        for (long id : ids) {
            if (isFreshPart1(id, freshRanges)) freshCount++;
        }

        return freshCount;
    }

    private boolean isFreshPart1(long id, List<String> freshRanges) {
        for (String range : freshRanges) {
            long lowerBound = Long.parseLong(range.split("-")[0]);
            long upperBound = Long.parseLong(range.split("-")[1]);
            if (id >= lowerBound && id <= upperBound) return true;
        }
        return false;
    }

    public long solvePart2(String input) {
        List<Range> freshRanges = Arrays.stream(input.substring(0, input.indexOf("\n\n")).split("\n"))
                .map(range -> new Range(Long.parseLong(range.split("-")[0]), Long.parseLong(range.split("-")[1])))
                .sorted()
                .toList();

        List<Range> mergedRanges = mergeRanges(freshRanges);

        long totalFreshIds = 0;
        for (Range range : mergedRanges) {
            totalFreshIds += (range.upperBound - range.lowerBound + 1);
        }

        return totalFreshIds;
    }

    private List<Range> mergeRanges(List<Range> freshRanges) {
        List<Range> merged = new ArrayList<>();
        Range currentMerged = freshRanges.getFirst();

        for (int i = 1; i < freshRanges.size(); i++) {
            Range next = freshRanges.get(i);

            if (currentMerged.upperBound >= next.lowerBound) {
                currentMerged = new Range(currentMerged.lowerBound, Math.max(currentMerged.upperBound, next.upperBound));
            } else {
                merged.add(currentMerged);
                currentMerged = next;
            }
        }

        merged.add(currentMerged);
        return merged;
    }

    record Range(long lowerBound, long upperBound) implements Comparable<Range> {
        @Override
        public int compareTo(Range o) {
            return Long.compare(lowerBound, o.lowerBound);
        }
    }
}