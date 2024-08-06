public class TagSize {
    private long size;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TagSize{" +
                "size=" + size +
                ", tag='" + tag + '\'' +
                '}';
    }

    public TagSize(long size, String tag) {
        this.size = size;
        this.tag = tag;
    }

    private String tag;

}
