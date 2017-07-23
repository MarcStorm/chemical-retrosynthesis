import utilities.KnowledgeBase;
import utilities.Parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Alexander, Gabriel, Marc & Marcus on 02/03/16.
 */
public class Main {
    static String problem;
    static String filePath;
    static String search;
    static int maxDepth = -1;
    public static void main(String[] args) throws IOException {
        getInput();
        Parser parser = new Parser(filePath);
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        parser.readInput(knowledgeBase);
        knowledgeBase.printKnowledgeBase();

        HashMap<String, Integer> problems = new HashMap<>();
        problems.put(problem, -1);

        // Chooses the search algorithm based on user input.
        if(search.equalsIgnoreCase("BFS")){
            System.out.println(SearchAlgorithm.breadthFirstSearch(problems,knowledgeBase));
        }else if(search.equalsIgnoreCase("UCS")){
            System.out.println(SearchAlgorithm.uniformCostSearch(problems,knowledgeBase));
        }else if(search.equalsIgnoreCase("IDS")){
            System.out.println(SearchAlgorithm.iterativeDeepening(maxDepth,problems,knowledgeBase));
        }else{
            System.out.println("No Valid search algorithm was choosen");
        }
    }

    // Method to get user input.
    private static void getInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to knowledgebase:");
        filePath = scanner.nextLine();
        System.out.println("Enter the chemical compound you want to create, write it with the same notation used in the knowledge base:");
        problem = scanner.nextLine();
        System.out.println("Choose search algorithm you wish to use, Use the abbreviation to choose, you can choose between:");
        System.out.println("BFS - Breadth first search");
        System.out.println("IDS - Iterative deepening search");
        System.out.println("UCS - Uniform cost search");
        search = scanner.nextLine();
        if(search.equalsIgnoreCase("IDS")){
            System.out.println("Choose the maximum depth");
            while(maxDepth < 0){
                try{
                    maxDepth = Integer.parseInt(scanner.nextLine());
                }catch (NumberFormatException e){
                    System.out.println("Please enter an integer");
                }
            }
        }
    }
}
