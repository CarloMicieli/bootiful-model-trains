package io.github.carlomicieli.catalog.catalogitems;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElectricMultipleUnit extends RollingStock {
    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("road_number")
    private String roadNumber;

    @JsonProperty("series")
    private String series;

    @JsonProperty("depot")
    private String depot;

    @JsonProperty("electric_multiple_unit_type")
    private ElectricMultipleUnitType electricMultipleUnitType;

    @JsonProperty("dcc_interface")
    private DccInterface dccInterface;

    @JsonProperty("control")
    private Control control;

    @JsonProperty("is_dummy")
    private Boolean isDummy;
}
