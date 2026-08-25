public class test01 {

    public static void main(String[] args) {

        System.out.println("=== Library Management Defect Tests ===");

        books book1 = new books(
            101,
            "Java Programming",
            "James Gosling",
            -5
        );

        books.addBook(book1);

        System.out.println(
            "Defect 1 - Negative quantity: "
            + book1.getBook_Quantity()
        );

        books book2 = new books(
            101,
            "Clean Code",
            "Robert Martin",
            5
        );

        books.addBookWithoutDuplicateCheck(book2);

        System.out.println(
            "Defect 2 - Duplicate ID accepted: "
            + book2.getBook_Id()
        );

        books book3 = new books(
            102,
            "Software Engineering",
            "Ian Sommerville",
            0
        );

        books.addBook(book3);

        boolean issued = books.issueBook(
            102,
            "Ali"
        );

        System.out.println(
            "Defect 3 - Unavailable book issued: "
            + issued
        );

        books book4 = new books(
            103,
            "Operating Systems",
            "Abraham Silberschatz",
            1
        );

        books.addBook(book4);

        books.issueBook(103, "Ahmed");

        System.out.println(
            "Quantity after issue: "
            + book4.getBook_Quantity()
        );

        books.returnBook(103, "Ahmed");

        System.out.println(
            "Defect 4 - Quantity after return: "
            + book4.getBook_Quantity()
        );

        books book5 = new books(
            104,
            "Database Systems",
            "Elmasri",
            3
        );

        books.addBook(book5);

        books result = books.searchBook(
            "database systems"
        );

        System.out.println(
            "Defect 5 - Case-sensitive search result: "
            + result
        );
    }
}
