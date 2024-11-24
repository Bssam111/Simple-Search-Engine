import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Index index = new Index("dataset.csv");
        Invertedindex invertedIndex = new Invertedindex(index);
        InvertedindexBST invertedIndexBST = new InvertedindexBST(invertedIndex);
        QueryProcessing queryProcessing = new QueryProcessing(index, invertedIndex, invertedIndexBST);
        Ranking ranking = new Ranking(index, invertedIndex, invertedIndexBST);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Index");
            System.out.println("2. Inverted Index");
            System.out.println("3. Inverted Index with BST");
            System.out.println("4. Print Tokens and Vocabulary");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            int indexChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (indexChoice) {
                case 1:
                    handleBooleanOrRanked(scanner, ranking, queryProcessing, true);
                    break;
                case 2:
                    handleBooleanOrRanked(scanner, ranking, queryProcessing, false);
                    break;
                case 3:
                    handleBooleanOrRanked(scanner, ranking, queryProcessing, false);
                    break;
                case 4:
                    printTokensAndVocabulary(index);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleBooleanOrRanked(Scanner scanner, Ranking ranking, QueryProcessing queryProcessing, boolean isIndex) {
        System.out.println("Choose Retrieval Type:");
        System.out.println("1. Boolean Retrieval");
        System.out.println("2. Ranked Retrieval");
        System.out.print("Choose an option (1 or 2): ");

        int retrievalChoice = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter your query: ");
        String query = scanner.nextLine();

        if (retrievalChoice == 1) {
            if (isIndex) {
                System.out.println("------------------------------------------------");
                queryProcessing.IndexQuery(query);
                System.out.println("------------------------------------------------");
            } else {
                if (ranking != null) {
                    if (ranking.invertedindexBST != null) {
                        System.out.println("------------------------------------------------");
                        queryProcessing.BSTQuery(query);
                        System.out.println("------------------------------------------------");
                    } else {
                        System.out.println("------------------------------------------------");
                        queryProcessing.invertedIndexQuery(query);
                        System.out.println("------------------------------------------------");
                    }
                }
            }
        } else if (retrievalChoice == 2) {
            if (isIndex) {
                ranking.createNewLinkedList();
                ranking.indexRanking(query);
            } else {
                if (ranking != null) {
                    if (ranking.invertedindexBST != null) {
                        ranking.createNewLinkedList();
                        ranking.invertedindexBSTRanking(query);
                    } else {
                        ranking.createNewLinkedList();
                        ranking.invertedindexRanking(query);
                    }
                }
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void printTokensAndVocabulary(Index index) {
        System.out.println("Number of Tokens: " + index.getTokens());
        System.out.println("Number of Vocabulary: " + index.getVocabulary());
    }
}
