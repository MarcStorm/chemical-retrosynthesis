package utilities;

import java.util.*;

/**
 * Created by Alexander, Gabriel, Marc & Marcus on 02/03/16.
 */
public class KnowledgeBase {

    // Field
    private List<Reaction> reactions = new LinkedList<>();
    private Map<String, List<Reaction>> knowledgeBase = new HashMap<>();

    // Constructor
    public KnowledgeBase() {
    }

    // Cleans up the reactions in the knowledge base of all easy accessible reagents as explained in the paper.
    public void process(){
        for(Reaction reaction : reactions){
            //System.out.println(reaction.toString());
            Iterator<String> reactantIterator =  reaction.getReactants().keySet().iterator();
            while(reactantIterator.hasNext()) {
                String reactant = reactantIterator.next();
                if (knowledgeBase.get(reactant) != null) {
                    for (Reaction solution : knowledgeBase.get(reactant)) {
                        if (solution.getNumberOfReactants() == 0) {
                            reaction.changeCoefficient(reactant, 0);
                        }
                    }
                }
            }
        }
        for(Reaction reaction : reactions){
            System.out.println(reaction.toString());
        }
    }

    public void printKnowledgeBase() {
        for (String key : getMap().keySet()) {
            List<Reaction> reactions = getReactionsOf(key);
            System.out.println("Product = " + key + ":");
            for (Reaction r : reactions) {
                System.out.println(r.toString());
            }
        }
    }

    public void putReactionsFor(String molecule, List<Reaction> reactions) {
        knowledgeBase.put(molecule, reactions);
    }

    // Returns a list of all the reactions that has molecule as a product.
    public List<Reaction> getReactionsOf(String molecule) {
        return knowledgeBase.get(molecule);
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    // Returns the knowledge base.
    public Map<String, List<Reaction>> getMap(){
        return knowledgeBase;
    }
}
