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
            if(tagSizePriorityQueue.size()<K){
                tagSizePriorityQueue.add(tagSize);
            }
            else if(tagSizePriorityQueue.peek().getSize()<tagSize.getSize()){
                tagSizePriorityQueue.poll();
                tagSizePriorityQueue.add(tagSize);

            }


        }


        List<TagSize> topTags = new ArrayList<>();
        while (!tagSizePriorityQueue.isEmpty()) {
            topTags.add(tagSizePriorityQueue.poll());
        }
        Collections.reverse(topTags); // Optional: Reverse to have the largest sizes first
        return topTags;
    }

}


