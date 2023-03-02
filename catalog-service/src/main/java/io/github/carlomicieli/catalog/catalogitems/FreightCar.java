package io.github.carlomicieli.catalog.catalogitems;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FreightCar extends RollingStock {
    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("road_number")
    private String roadNumber;

    @JsonProperty("freight_car_type")
    private FreightCarType freightCarType;

}
