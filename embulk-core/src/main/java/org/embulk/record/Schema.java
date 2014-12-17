package org.embulk.record;

import java.util.List;
import com.google.common.base.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class Schema
{
    private final List<Column> columns;

    @JsonCreator
    public Schema(List<Column> columns)
    {
        this.columns = columns;
    }

    @JsonValue
    public List<Column> getColumns()
    {
        return columns;
    }

    public int getColumnCount()
    {
        return columns.size();
    }

    public Column getColumn(int index)
    {
        return columns.get(index);
    }

    public String getColumnName(int index)
    {
        return getColumn(index).getName();
    }

    public Type getColumnType(int index)
    {
        return getColumn(index).getType();
    }

    public boolean isEmpty()
    {
        return columns.isEmpty();
    }

    public int size()
    {
        return columns.size();
    }

    public int getFixedStorageSize()
    {
        int total = 0;
        for (Column column : columns) {
            total += column.getType().getFixedStorageSize();
        }
        return total;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Schema)) {
            return false;
        }
        Schema other = (Schema) obj;
        return Objects.equal(columns, other.columns);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(columns);
    }

    @Override
    public String toString()
    {
        StringBuilder sbuf = new StringBuilder();
        sbuf.append(String.format(" Schema: %d columns", columns.size())).append('\n');
        for (Column c : columns) {
            sbuf.append("   " + c.toString()).append('\n');
        }
        return sbuf.toString();
    }
}