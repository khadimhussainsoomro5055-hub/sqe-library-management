
import java.util.ArrayList;
import java.util.List;

public class MemberTest {

    public static void main(String[] args) {

        Member member1 = new Member(1, "Ali", 5);
        Member member2 = new Member(2, "Ahmed", 10);
        Member member3 = new Member(3, "Sara", 15);

        List<Member> library = new ArrayList<>();
        library.add(member1);
        library.add(member2);
        library.add(member3);

        // Test 1: Normal calculation
        double result = Member.borrow_percentile(member2, library);

        if (Math.abs(result - 50.0) > 0.0001) {
            throw new AssertionError("Normal calculation test failed!");
        }

        System.out.println("Normal calculation test passed!");

        // Test 2: Single-member library
        List<Member> singleMemberLibrary = new ArrayList<>();
        singleMemberLibrary.add(member1);

        double singleResult =
            Member.borrow_percentile(member1, singleMemberLibrary);

        if (singleResult != 100.0) {
            throw new AssertionError("Single-member test failed!");
        }

        System.out.println("Single-member test passed!");

        // Test 3: Lowest percentile
        double lowestResult =
            Member.borrow_percentile(member1, library);

        if (lowestResult != 0.0) {
            throw new AssertionError("Lowest percentile test failed!");
        }

        System.out.println("Lowest percentile test passed!");

        // Test 4: Highest percentile
        double highestResult =
            Member.borrow_percentile(member3, library);

        if (highestResult != 100.0) {
            throw new AssertionError("Highest percentile test failed!");
        }

        System.out.println("Highest percentile test passed!");

        // Test 5: Empty library
        try {
            Member.borrow_percentile(member1, new ArrayList<>());
            throw new AssertionError("Empty library test failed!");

        } catch (IllegalArgumentException e) {
            System.out.println("Empty library test passed!");
        }
    }
}
