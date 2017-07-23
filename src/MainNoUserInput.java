import utilities.KnowledgeBase;
import utilities.Parser;

import java.io.IOException;
import java.util.HashMap;
/**
 * Created by Alexander, Gabriel, Marc & Marcus on 13/04/2016.
 */
public class MainNoUserInput {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser("knowledgeBase.txt");
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        parser.readInput(knowledgeBase);

        HashMap<String, Integer> problems = new HashMap<>();
        problems.put("Na2CO3", -1);

        //Choose the wanted search algorithm.
        //System.out.println(SearchAlgorithm.breadthFirstSearch(problems,knowledgeBase));
        //System.out.println(SearchAlgorithm.uniformCostSearch(problems,knowledgeBase));
        System.out.println(SearchAlgorithm.iterativeDeepening(15,problems,knowledgeBase));

    }
}
