import java.util.List;

public class File {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<String> getTags() {
        return tags;
    }

    public File(String name, long size, List<String> tags) {
        this.name = name;
        this.size = size;
        this.tags = tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    String name;
    long size;
    List<String> tags;
}
