import java.util.Arrays;
import java.util.Map;

public class FileSystem {
    TrieNode root ;

    public FileSystem() {
        this.root = new TrieNode();
    }


    public  boolean insertPath(String path, int value){
        if (path == null || path.isEmpty()|| "/".equals(path) ) return false;


        TrieNode current = root;
        String[] tokens = Arrays.stream(path.split("/"))
                .filter(token -> !token.isEmpty())
                .toArray(String[]::new);


        for(int i=0;i<tokens.length;i++){
            String token = tokens[i];
            Map<String, TrieNode> children = current.getChildren();
            if(!children.containsKey(token)){
                if(i == tokens.length-1){
                    TrieNode newNode = new TrieNode();
                    children.put(token, newNode);
                    newNode.setVal(value);
                    return true;
                }
                else return  false;
            }
            current = children.get(token);
        }
        return  false;
    }

    public  int findPath(String path){
        if (path == null || path.isEmpty()) return -1;

        TrieNode current = root;

        String[] tokens = Arrays.stream(path.split("/"))
                .filter(token -> !token.isEmpty())
                .toArray(String[]::new);

        for(int i=0;i<tokens.length;i++){
            String token = tokens[i];
            Map<String, TrieNode> children = current.getChildren();
            if(!children.containsKey(token)){
                return  -1;
            }
            current = children.get(token);
        }
        return  current.getVal();

    }



}
