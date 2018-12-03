import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov{
    private Map<WordGram, ArrayList<String>> myMap;

    /**
     * Construct an EfficientWordMarkov object with
     * the specified order
     * @param order size of this markov generator
     */
    public EfficientWordMarkov(int order){
        super(order);
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }

    /**
     * Default constructor has order 2
     */
    public EfficientWordMarkov(){
        this(2);
    }

    /**
     * Looks up a key (String) in myMap and returns associated ArrayList of single character strings
     * @param wGram - String key to look up in myMap
     * @return - ArrayList of single character strings that follow key in myText
     */
    public ArrayList<String> getFollows(WordGram wGram){
        //Checks to ssee if myMap contains wGram parameter
        if (myMap.containsKey(wGram)){
            return myMap.get(wGram);
        }
        //Throw exception
        else {
            throw new NoSuchElementException(wGram + " not in map");
        }
    }

    /**
     * Stores text parameter into array myWords and initializes myMap with ArrayLists of single character strings
     * following each substring
     * @param text - text that is split into array myWords; text that will be worked with to obtain keys
     * and values of myMap
     */
    public void setTraining(String text){
        myWords = text.split("\\s+");
        //Creates new HashMap
        myMap = new HashMap<WordGram, ArrayList<String>>();
        for (int i = 0; i <= myWords.length - myOrder; i++){
            WordGram temp = new WordGram(myWords, i, myOrder);
            //Checks to see if it is at the last character
            if (i == myWords.length - myOrder){
                String nullChar = PSEUDO_EOS;
                //If myMap doesn't contain temp, create new ArrayList and add PSEUDO_EOS
                if (!myMap.containsKey(temp)){
                    ArrayList<String> tempAList = new ArrayList<String>();
                    tempAList.add(nullChar);
                    myMap.put(temp, tempAList);
                }
                //If myMap contains temp, add PSEUDO_EOS to ArrayList associated with temp
                else {
                    ArrayList<String> tempAList2 = myMap.get(temp);
                    tempAList2.add(nullChar);
                    myMap.put(temp,tempAList2);
                }
                return;
            }
            //Creating new String object temp2
            String temp2 = myWords[i + myOrder];
            //If myMap does not contain temp, create a new ArrayList and add following String object to that ArrayList
            if (!myMap.containsKey(temp)){
                ArrayList<String> tempAList = new ArrayList<String>();
                tempAList.add(temp2);
                myMap.put(temp, tempAList);
            }
            //If myMap contains temp, add following String object to existing ArrayList
            else {
                ArrayList<String> tempAList2 = myMap.get(temp);
                tempAList2.add(temp2);
                myMap.put(temp,tempAList2);
            }
        }
    }
}
