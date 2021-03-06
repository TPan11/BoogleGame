package maingame;

import java.io.File;
import java.net.URL;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

class makedict{
	
	void dict(TrieMethods t){
		//URL path = makedict.class.getClassLoader().getResource();
		File f = new File("./resources/BoogleDictionary.txt");
		try{
			Scanner sc = new Scanner(f);
			while(sc.hasNext()){
				t.addWord(sc.nextLine());
			}
			sc.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
public class Boogle {
	
	static boolean isValid(int i, int j, boolean visited[][]){
		int m = visited.length;
		int n = visited[0].length;
		if(i>=0 && i<m && j>=0 && j<n && !visited[i][j]){
			return true;
		}
		return false;
	}
	
	static void searchWord(TrieNode root, char[][] board, int i, int j, boolean[][] visited, String sb, Set<String> result){
		if(root.isWord){
			//System.out.println(sb);
			result.add(sb);
		}
		
		if(isValid(i, j, visited) && !root.isLeaf){
			visited[i][j] = true;
			
			for(char c = 'a' ;c<='z'; c++){
				if(root.getNode(c) != null){
					if (isValid(i+1,j+1,visited) && board[i+1][j+1] == c){
	                    searchWord(root.getNode(c), board, i+1, j+1, visited, sb + c, result);
					}
					if (isValid(i,j+1,visited) && board[i][j+1] == c){
	                    searchWord(root.getNode(c), board, i, j+1, visited, sb + c, result);
					}
					if (isValid(i-1,j+1,visited) && board[i-1][j+1] == c){
	                    searchWord(root.getNode(c), board, i-1, j+1, visited, sb + c, result);
					}
					if (isValid(i-1,j,visited) && board[i-1][j] == c){
	                    searchWord(root.getNode(c), board, i-1, j, visited, sb + c, result);
					}
					if (isValid(i-1,j-1,visited) && board[i-1][j-1] == c){
	                    searchWord(root.getNode(c), board, i-1, j-1, visited, sb + c, result);
					}
					if (isValid(i,j-1,visited) && board[i][j-1] == c){
	                    searchWord(root.getNode(c), board, i, j-1, visited, sb + c, result);
					}
					if (isValid(i+1,j-1,visited) && board[i+1][j-1] == c){
	                    searchWord(root.getNode(c), board, i+1, j-1, visited, sb + c, result);
					}
					if (isValid(i+1,j,visited) && board[i+1][j] == c){
	                    searchWord(root.getNode(c), board, i+1, j, visited, sb + c, result);
					}
				}
			}
			visited[i][j] = false;
		}
	}
	
	static void goBoogle(char[][] board, TrieNode root, Set<String> result){
		int m = board.length;
		int n = board[0].length;
		boolean visited[][] = new boolean[m][n];
		
		String sb = new String();
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(root.getNode(board[i][j])!=null){
					sb = sb + board[i][j];
					searchWord(root.getNode(board[i][j]), board, i, j, visited, sb, result);
					sb = "";
				}
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrieMethods t = new TrieMethods();
		makedict md = new makedict();
		md.dict(t);
		Random rand = new Random();
		String s = "abcdefghijklmnopqrstuvwxyz";
		char board[][] = new char[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				int k = rand.nextInt(26);
				board[i][j] = s.charAt(k);
			}
		}
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
		System.out.println();
		
		Set<String> result = new HashSet<>();
		
		goBoogle(board, t.root, result);
		int i=1;
		for(String str: result){
			System.out.println((i++) + " " + str);
		}
	}

}
