
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MemberPercentile {

    public static double borrowPercentile(
            String member,
            Map<String, Integer> library
    ) {

        List<Map.Entry<String, Integer>> members =
                new ArrayList<>(library.entrySet());

        members.sort(
                Comparator.comparingInt(
                        (Map.Entry<String, Integer> m) -> m.getValue()
                ).reversed()
        );

        int x = 0;

        for (Map.Entry<String, Integer> m : members) {

            if (m.getKey().equals(member)) {
                break;
            }

            x++;
        }

        if (x == members.size()) {
            return -1;
        }

        return (double) x / members.size() * 100;
    }
}
