package io.github.carlomicieli.catalog.catalogitems;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Railcar extends RollingStock {
    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("road_number")
    private String roadNumber;

    @JsonProperty("series")
    private String series;

    @JsonProperty("depot")
    private String depot;

    @JsonProperty("railcar_type")
    private RailcarType railcarType;

    @JsonProperty("dcc_interface")
    private DccInterface dccInterface;

    @JsonProperty("control")
    private Control control;

    @JsonProperty("is_dummy")
    private Boolean isDummy;
}
