package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexander, Gabriel, Marc & Marcus on 02/03/16.
 */
public class Parser {

    // Field
    private String fileName;

    // Constructor
    public Parser(String fileName){
        this.fileName = fileName;
    }


    public KnowledgeBase readInput(KnowledgeBase knowledgeBase) throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line = bufferedReader.readLine();

        // Runs as long as there's input in the knowledge base file.
        while(line != null) {
            // Eliminates spaces.
            line = line.replace(" ", "");

            StringTokenizer stCost = new StringTokenizer(line,"$");

            // Gets information from the line in the file.
            String lineNoCost = stCost.nextToken();
            int cost = getCost(stCost, lineNoCost);

            // Split the string to get reactants and products from the reaction.
            StringTokenizer st = new StringTokenizer(lineNoCost,"->");

            String reactants = "";
            String products = "";

            // Assign reacants and products of the reaction to the variables.
            if (st.countTokens() == 1){
                products = st.nextToken();
            } else {
                reactants = st.nextToken();
                products = st.nextToken();
            }

            StringTokenizer stR = new StringTokenizer(reactants,"+");
            StringTokenizer stP = new StringTokenizer(products,"+");

            Reaction reaction = new Reaction();
            reaction.setCost(cost);

            // Splits a molecule into coefficient and substance.
            String regex = "(\\d*)(.+)";
            Pattern p = Pattern.compile(regex);

            splitReactans(stR, reaction, p);
            splitProducts(knowledgeBase, stP, reaction, p);

            knowledgeBase.getReactions().add(reaction);
            reaction.setReactionString();
            reaction.setNumberOfReactants();
            line = bufferedReader.readLine();
        }

        //knowledgeBase.printKnowledgeBase();
        //knowledgeBase.process();

        return knowledgeBase;
    }

    private void splitProducts(KnowledgeBase knowledgeBase, StringTokenizer stP, Reaction reaction, Pattern p) {
        while(stP.hasMoreTokens()){
            String product = stP.nextToken();
            // Mathes p in the string "product".
            Matcher m = p.matcher(product);

            int coefficient = 1;
            String molecule = "";

            if (m.find()){
                if (!m.group(1).equals("")) {
                    coefficient = Integer.parseInt(m.group(1));
                }

                molecule = m.group(2);
                reaction.addProduct(molecule,coefficient);
            }
            checkIfListForProductAlreadyExistsInKnowledgeBase(knowledgeBase, reaction, molecule);
        }
    }

    // If there's a list for the product in the knowledge base, the reaction is added to the list, otherwise the
    // list is created before the reaction is added.
    private void checkIfListForProductAlreadyExistsInKnowledgeBase(KnowledgeBase knowledgeBase, Reaction reaction, String molecule) {
        if (knowledgeBase.getReactionsOf(molecule) == null){
            List<Reaction> reactions = new LinkedList<Reaction>();
            reactions.add(reaction);
            knowledgeBase.putReactionsFor(molecule,reactions);
        } else knowledgeBase.getReactionsOf(molecule).add(reaction);
    }

    private void splitReactans(StringTokenizer stR, Reaction reaction, Pattern p) {
        while(stR.hasMoreTokens()){
            String reactant = stR.nextToken();
            // Mathes p in the string "reactant".
            Matcher m = p.matcher(reactant);

            int coefficient = 1;

            if (m.find()){

                if (!m.group(1).equals("")) {
                    coefficient = Integer.parseInt(m.group(1));
                }

                String molecule = m.group(2);
                reaction.addReactant(molecule,coefficient);
            }
        }
    }

    // Cost is assigned to a value if not defined in file.
    private int getCost(StringTokenizer stCost, String lineNoCost) {
        int cost;
        if(stCost.hasMoreTokens()) {
            cost = Integer.parseInt(stCost.nextToken());
        }else{
            cost = 10;
            System.out.println("Reaction " + lineNoCost + " has no predefined cost.");
        }
        return cost;
    }
}
