package test.week8;

/**Trie(字典树)，又称前缀树或字典树，是一棵有根树，其每个节点包含以下字段：
 指向子节点的指针数组 \textit{children}children。对于本题而言，数组长度为 2626，即小写英文字母的数量。此时 \textit{children}[0]children[0] 对应小写字母 aa，\textit{children}[1]children[1] 对应小写字母 bb，…，\textit{children}[25]children[25] 对应小写字母 zz。
 布尔字段 \textit{isEnd}isEnd，表示该节点是否为字符串的结尾。

 插入字符串
 我们从字典树的根开始，插入字符串。对于当前字符对应的子节点，有两种情况：
 子节点存在。沿着指针移动到子节点，继续处理下一个字符。
 子节点不存在。创建一个新的子节点，记录在 \textit{children}children 数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
 重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾。

 查找前缀
 我们从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：
 子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
 子节点不存在。说明字典树中不包含该前缀，返回空指针。
*/
public class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
