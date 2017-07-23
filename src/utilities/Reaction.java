package utilities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexander, Gabriel, Marc & Marcus on 02/03/16.
 */
public class Reaction {

    // Field
    private HashMap<String, Integer> reaction = new HashMap<>();
    private int numberOfReactants;
    private String reactionString;
    private int cost;

    // Contructor
    public Reaction(){
    }

    // Coefficient is negated because it's a reactant.
    public void addReactant(String reactant, int coefficient) {
        reaction.put(reactant,-coefficient);
    }

    public void addProduct(String product, int coefficient) {
        reaction.put(product,coefficient);
    }

    // Collects the information from the program to print out a reaction on the normal form.
    public String toString() {
        String s = "";
        LinkedList<String> reactant = new LinkedList<>();
        LinkedList<String> product = new LinkedList<>();

        splitReactionIntoReactantAndProduct(reactant, product);

        s = addReactantToPrintString(s, reactant);
        s = addProductToPrintString(s, product);
        return s + " Cost: " + this.cost + "$";
    }

    private String addProductToPrintString(String s, LinkedList<String> product) {
        if(!product.isEmpty()) {
            s += " -> " + product.removeFirst();
            for (String s2 : product) {
                s += " + " + s2;
            }
        }
        return s;
    }

    private String addReactantToPrintString(String s, LinkedList<String> reactant) {
        if(!reactant.isEmpty()) {
            s += reactant.removeFirst();
            for (String s2 : reactant) {
                s += " + " + s2;
            }
        }
        return s;
    }

    private void splitReactionIntoReactantAndProduct(LinkedList<String> reactant, LinkedList<String> product) {
        for(String substance : reaction.keySet()){
            int coefficient = reaction.get(substance);
            if (coefficient < 0){
                if(-coefficient == 1){
                    reactant.add("" + substance);
                }else {
                    reactant.add("" + -coefficient + " " + substance);
                }
            }
            else if (coefficient > 0){
                if(coefficient == 1){
                    product.add("" + substance);
                }else {
                    product.add("" + coefficient + " " + substance);
                }
            }
        }
    }

    public void changeCoefficient(String substance, int coefficient){
        reaction.put(substance,coefficient);
    }

    public Map getReactants() {
        Map<String, Integer> reactants = new HashMap<>();
        for (String key : reaction.keySet()) {
            if (reaction.get(key) < 0) {
                reactants.put(key, reaction.get(key));
            }
        }
        return reactants;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public HashMap<String, Integer> getMap() {
        return reaction;
    }

    public void setReactionString() {
        this.reactionString = toString();
    }
    public String getReactionString() {
        return reactionString;
    }

    public int getNumberOfReactants() {
        return numberOfReactants;
    }

    public void setNumberOfReactants() {
        this.numberOfReactants = getReactants().size();
    }

}
