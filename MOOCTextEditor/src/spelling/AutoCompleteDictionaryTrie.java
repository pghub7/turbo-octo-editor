package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Parth
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		try {
			
		//size = 0;	
		String lcWord = word.toLowerCase();
		TrieNode currNode = root;
		TrieNode node = root;
		TrieNode Node = root;
		boolean charAlreadyPresent = false;
		int index = 0;
		
		//looking for a word or a part of it
		for(int i = 0; i < lcWord.length(); i++ ) {
			//System.out.println("parent: " + node.getText());
			Node = node;
			node = node.getChild(lcWord.charAt(i));
			if(node != null ) {
				//System.out.println("child: " + node.getText());
				index = i;
				charAlreadyPresent = true;
				if(node.getText().equals(lcWord) && !node.endsWord()) {
					node.setEndsWord(true);
					size+=1;
				}
			}
			else break;
		}
		
		//System.out.println("Node: " + Node.getText());
		//System.out.println("index after break : " + index); 
		
		if(charAlreadyPresent) 		
 			index+=1;
		
			for(int i = index; i < lcWord.length(); i++ ) {
				Node = Node.insert(lcWord.charAt(i));
				System.out.println(Node.getText());
			}
			
			
			if(Node.getText().equals(lcWord)) {
				Node.setEndsWord(true);
				size+=1;
				return true;
			}
			else Node.setEndsWord(false);
				//System.out.println(word + " is present in testDict");
			
		} catch(NullPointerException e) {
			
		}
		

		
		return false; 
		
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
		
	
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method

	try {
   	 	TrieNode currNode = root;
   	 	int counter = 0;
   	 	boolean stemFound = false;
   	 	String word = s.toLowerCase();
   	 	for(int i = 0; i < word.length(); i++) {
   	 		if(currNode.getChild(word.charAt(i))!= null) {
   	 			counter+=1;
   	 			currNode = currNode.getChild(word.charAt(i));
   	 		}
   	 	}
   	 	//System.out.println("Stem: " + counter + "StemNode: " + currNode.getText());
   	 	if(counter == word.length()  ) {
   	 		stemFound = true;
   	 		//System.out.println("stem found");
   	 	} 
   		 
   	 	if(stemFound) {
   			if(currNode.endsWord())
   				return true;
   		}
		
	}
	catch (NullPointerException e) {
			
	}
   	
	return false;
		
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 
    	 // finding the stem 
    	 TrieNode currNode = root;
    	// TrieNode stemNode = currNode;
    	 int counter = 0;
    	 boolean stemFound = false;
    	 String word = prefix.toLowerCase();
    	 for(int i = 0; i < word.length(); i++) {
    		 if(currNode.getChild(word.charAt(i))!= null) {
    			 counter+=1;
    			 //stemNode = currNode;
    			 currNode = currNode.getChild(word.charAt(i));
    		 }
    	 }
    	 //System.out.println("Stem: " + counter + "StemNode: " + currNode.getText());
    	 if(counter == word.length()  ) {
    		 stemFound = true;
    		 //System.out.println("stem found");
    	 }
    	 
    	 if(stemFound) {
    		 Queue<TrieNode> q = new LinkedList<TrieNode>();
    		 q.add(currNode);
    		 //System.out.println("currNode: " + currNode.getText());
    		 List<String> completions = new ArrayList<String>();
    		 
    		 while(!q.isEmpty() && numCompletions>0) {
    			 //System.out.println("inside while loop");
    			 TrieNode thisNode = q.remove();
    			 //System.out.println("thisNode text: " + thisNode.getText());
    			 if(thisNode.endsWord()) {
    				 numCompletions-=1;
    				 //System.out.println("thisNode isWord: " + thisNode.getText());
    				 completions.add(thisNode.getText());
    				 }
				 TrieNode child = null;
				 for(Character c : thisNode.getValidNextCharacters()) {
					 child = thisNode.getChild(c);
					 //System.out.println("child: " + child.getText());
					 q.add(child);
    			 }
    		 }
    		 System.out.println("Completions list: " + completions);
    		 return completions;
    	 }
    	 
    		 
    	 else return new ArrayList<String>();
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
 	/*public boolean checkForWord(TrieNode curr, String s) {
 		
 	boolean isWord = false;
 	try {
 	if(curr != null) {
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			if(next.getText().equals(s))
 				isWord = true;
 			checkForWord(next,s);
 		}
 	}
 	}
 	catch (NullPointerException e) {
 		
 	}
 	return isWord;
 	} */
 	
     
	
}