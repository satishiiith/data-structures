import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<File> files = new ArrayList<>();
        File f1 = new File("a", 400, Arrays.asList("confluence","jira"));
        File f2 = new File("b", 100, Arrays.asList("confluence"));
        File f3 = new File("c", 1000, Arrays.asList("git"));


        List<File> fileList = new ArrayList<>();
        fileList.add(f1);
        fileList.add(f2);
        fileList.add(f3);

        TagFrequency tagFrequency = new TagFrequency(fileList,2);
        List<TagSize> tagSizeList = tagFrequency.getTopK();
        for(TagSize tagSize : tagSizeList){
            System.out.println(tagSize.getTag());
        }







    }
}