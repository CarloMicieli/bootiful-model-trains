package io.github.carlomicieli.catalog.catalogitems;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Locomotive extends RollingStock {
    @JsonProperty("class_name")
    private String className;

    @JsonProperty("road_number")
    private String roadNumber;

    @JsonProperty("series")
    private String series;

    @JsonProperty("depot")
    private String depot;

    @JsonProperty("locomotive_type")
    private LocomotiveType locomotiveType;

    @JsonProperty("dcc_interface")
    private DccInterface dccInterface;

    @JsonProperty("control")
    private Control control;

    @JsonProperty("is_dummy")
    private Boolean isDummy;
}
