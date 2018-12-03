import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov{
    private Map<String, ArrayList<String>> myMap;

    /**
     * Construct an EfficientMarkov object with
     * the specified order
     * @param order - size of this markov generator
     */
    public EfficientMarkov(int order){
        super(order);
        myMap = new HashMap<String, ArrayList<String>>();
    }

    /**
     * Default constructor has order 3
     */
    public EfficientMarkov(){
        this(3);
    }

    /**
     * Looks up a key (String) in myMap and returns associated ArrayList of single character strings
     * @param key - String key to look up in myMap
     * @return - ArrayList of single character strings that follow key in myText
     */
    public ArrayList<String> getFollows(String key){
        //Checks to see if myMap contains key parameter
        if (myMap.containsKey(key)){
            return myMap.get(key);
        }
        //Throw exception
        else {
            throw new NoSuchElementException(key + " not in map");
        }
    }

    /**
     * Stores text parameter and initializes myMap with ArrayLists of single character strings following each substring
     * @param text - text that is stored in myText; text that will be worked with to obtain keys and values of myMap
     */
    public void setTraining(String text) {
        myText = text;
        //Clears myMap
        myMap.clear();
        for (int i = 0; i <= myText.length() - myOrder; i++){
            String temp = myText.substring(i, i + myOrder);
            //Checks to see if it is at the last character
            if (i == myText.length() - myOrder){
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
            //If myMap does not contain temp, create a new ArrayList and add following character to that ArrayList
            if (!myMap.containsKey(temp)){
                ArrayList<String> tempAList = new ArrayList<String>();
                tempAList.add(myText.substring(i + myOrder, i + myOrder + 1));
                myMap.put(temp, tempAList);
            }
            //If myMap contains temp, add following character to existing ArrayList
            else {
                ArrayList<String> tempAList2 = myMap.get(temp);
                tempAList2.add(myText.substring(i + myOrder, i + myOrder + 1));
                myMap.put(temp,tempAList2);
            }
        }
    }
}
