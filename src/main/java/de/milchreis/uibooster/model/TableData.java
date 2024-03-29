package de.milchreis.uibooster.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TableData {

    private List<String> header;
    private String[][] data;

    public TableData(List<String> header, String[][] data) {
        this.header = header;
        this.data = data;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TableData{" +
            "header=" + header +
            ", data=" + Arrays.toString(data) +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableData tableData = (TableData) o;
        return Objects.equals(header, tableData.header) && Arrays.equals(data, tableData.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(header);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
