package test.week8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindWords {
    static char[][] mBoard = null;
    static List<String> ans = new ArrayList<>();

    public FindWords() {}

    public static void main(String[] args) {
        char[][] board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[]{"oath","pea","eat","rain"};
        System.out.print(new FindWords().findWords(board,words));
    }

    public List<String> findWords(char[][] board, String[] words) {
        // 构建字典树
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;
        }

        this.mBoard = board;
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this.ans;
    }

    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this.mBoard[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this.ans.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this.mBoard[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this.mBoard.length || newCol < 0
                    || newCol >= this.mBoard[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this.mBoard[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this.mBoard[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }

    // 字典树定义
    static class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;
        public TrieNode() {}
    }
}
