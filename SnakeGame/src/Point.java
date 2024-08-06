import java.util.Objects;

public class Point {
    private int row_pos;
    private int col_pos;

    public Point(int row_pos, int col_pos) {
        this.row_pos = row_pos;
        this.col_pos = col_pos;
    }

    public int getRow_pos() {
        return row_pos;
    }

    public void setRow_pos(int row_pos) {
        this.row_pos = row_pos;
    }

    public int getCol_pos() {
        return col_pos;
    }

    public void setCol_pos(int col_pos) {
        this.col_pos = col_pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return row_pos == point.row_pos && col_pos == point.col_pos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row_pos, col_pos);
    }
}
