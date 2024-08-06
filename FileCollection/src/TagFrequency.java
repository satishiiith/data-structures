import java.util.*;

public class TagFrequency {

    List<File> fileList;
    Map<String, Long> tagsSizeMap;

    PriorityQueue<TagSize> tagSizePriorityQueue;
    int K;


    public TagFrequency(List<File> fileList, int K) {
        this.fileList = fileList;
        tagsSizeMap = new HashMap<>();
        this.tagSizePriorityQueue = new PriorityQueue<>(Comparator.comparing(TagSize::getSize));
        this.K =K;

    }

    public List<TagSize> getTopK() {
        for (File file : fileList) {
            List<String> tags = file.getTags();
            for (String tag : tags) {
                long size = tagsSizeMap.getOrDefault(tag, 0l);
                size += file.getSize();
                tagsSizeMap.put(tag, size);
            }

        }

        for(String tag : tagsSizeMap.keySet()){
            TagSize tagSize = new TagSize( tagsSizeMap.get(tag),tag);
            tagSizePriorityQueue.add(tagSize);
            if(tagSizePriorityQueue.size()>K){
                tagSizePriorityQueue.poll();
            }
        }


        List<TagSize> topTags = new ArrayList<>();
        while (!tagSizePriorityQueue.isEmpty()) {
            System.out.println(tagSizePriorityQueue.peek());
            topTags.add(0,tagSizePriorityQueue.poll());

        }
        return topTags;
    }

}


