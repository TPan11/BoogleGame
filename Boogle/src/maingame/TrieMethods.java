package maingame;

import java.util.ArrayList;
import java.util.List;

class TrieNode {
	TrieNode parent;
	TrieNode children[];
	boolean isWord;
	char c;
	boolean isLeaf;
	
	TrieNode(){
		children = new TrieNode[26];
		isLeaf = true;
		isWord = false;
	}
	
	TrieNode(char c){
		this();
		this.c = c;
	}
	
	void addWord(String word){
		if(word.isEmpty()){
			return;
		}
		isLeaf = false;
		int charPosition = word.charAt(0) - 'a';
		
		if(children[charPosition] == null){
			children[charPosition] = new TrieNode(word.charAt(0));
			children[charPosition].parent = this;
		}
		
		if(word.length()>1){
			children[charPosition].addWord(word.substring(1));
		}
		else{
			children[charPosition].isWord = true;
		}
	}
	
	TrieNode getNode(char c){
		return children[c-'a'];
	}
	
	List<String> getWords(){
		List<String> words = new ArrayList<>();
		
		if(isWord){
			words.add(makeString());
		}
		
		if(!isLeaf){
			for(int i=0;i<children.length;i++){
				if(children[i] != null){
					words.addAll(children[i].getWords());
				}
			}
		}
		
		return words;
	}
	
	String makeString(){
		if(parent == null){
			return "";
		}
		else{
			return parent.makeString() + new String(new char[] {c});
		}
	}
}

public class TrieMethods{
	TrieNode root;
	
	TrieMethods(){
		root = new TrieNode();
	}
	
	public void addWord(String word){
		root.addWord(word.toLowerCase());
	}
	
	public List<String> getWords(String prefix){
		TrieNode lastNode = root;
		
		for(int i=0;i<prefix.length();i++){
			lastNode = lastNode.getNode(prefix.charAt(i));
		}
		
		if(lastNode == null) return new ArrayList<>();
		return lastNode.getWords();
	}
	
}
