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

        double result = Member.borrow_percentile(member2, library);

        System.out.println("Borrow percentile: " + result);

        List<Member> singleMemberLibrary = new ArrayList<>();
        singleMemberLibrary.add(member1);

        double singleResult = Member.borrow_percentile(member1, singleMemberLibrary);

        System.out.println("Single-member percentile: " + singleResult);

        try {
            Member.borrow_percentile(member1, new ArrayList<>());
            System.out.println("Empty library test failed!");
        } catch (IllegalArgumentException e) {
            System.out.println("Empty library test passed!");
        }
    }
}