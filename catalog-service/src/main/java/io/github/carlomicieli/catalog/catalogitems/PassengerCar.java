package io.github.carlomicieli.catalog.catalogitems;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PassengerCar extends RollingStock {
    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("road_number")
    private String roadNumber;

    @JsonProperty("series")
    private String series;

    @JsonProperty("passenger_car_type")
    private PassengerCarType passengerCarType;

    @JsonProperty("service_level")
    private ServiceLevel serviceLevel;
}
