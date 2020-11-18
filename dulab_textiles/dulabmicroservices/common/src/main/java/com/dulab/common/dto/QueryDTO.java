package com.dulab.common.dto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "query")
@XmlAccessorType(XmlAccessType.FIELD)
public class QueryDTO {

    /**
     * name.
     */
    @XmlAttribute
    private String name;
    /**
     * query.
     */
    @XmlValue
    private String query;

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param nameIn the name to set
     */
    public final void setName(final String nameIn) {
        name = nameIn;
    }

    /**
     * @return the query
     */
    public final String getQuery() {
        return query;
    }

    /**
     * @param queryIn the query to set
     */
    public final void setQuery(final String queryIn) {
        query = queryIn;
    }
}
