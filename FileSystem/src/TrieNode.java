import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    private Map<String, TrieNode> children;
    private int val;

    public TrieNode(){
        this.children = new HashMap<>();
        this.val =-1;
    }

    public TrieNode(int val) {
        this.val = val;
    }

    public TrieNode(Map<String, TrieNode> children, int val) {
        this.children = children;
        this.val = val;
    }

    public Map<String, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<String, TrieNode> children) {
        this.children = children;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

}
