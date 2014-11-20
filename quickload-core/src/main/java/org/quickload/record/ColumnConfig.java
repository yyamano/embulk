package org.quickload.record;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;

public class ColumnConfig
{
    private final String name;
    private final Type type;
    private final String format;

    @JsonCreator
    public ColumnConfig(
            @JsonProperty("name") String name,
            @JsonProperty("type") Type type,
            @JsonProperty("format") String format)
    {
        this.name = name;
        this.type = type;
        this.format = format;
    }

    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    @JsonProperty("type")
    public Type getType()
    {
        return type;
    }

    @JsonProperty("format")
    public String getFormat()
    {
        return format;
    }

    public Column toColumn(int index)
    {
        return new Column(index, name, type);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ColumnConfig)) {
            return false;
        }
        ColumnConfig other = (ColumnConfig) obj;
        return Objects.equal(this.name, other.name) &&
            Objects.equal(type, other.type);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(name, type);
    }

    @Override
    public String toString()
    {
        return String.format("ColumnConfig[%s, %s]",
                getName(), getType());
    }
}
