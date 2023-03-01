/*
 *   Copyright (c) 2023 Carlo Micieli
 *
 *    Licensed to the Apache Software Foundation (ASF) under one
 *    or more contributor license agreements.  See the NOTICE file
 *    distributed with this work for additional information
 *    regarding copyright ownership.  The ASF licenses this file
 *    to you under the Apache License, Version 2.0 (the
 *    "License"); you may not use this file except in compliance
 *    with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an
 *    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *    KIND, either express or implied.  See the License for the
 *    specific language governing permissions and limitations
 *    under the License.
 */
package io.github.carlomicieli.catalog.catalogitems;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FreightCarType {
    AUTO_TRANSPORT_CARS("AUTO_TRANSPORT_CARS"),

    BRAKE_WAGON("BRAKE_WAGON"),

    CONTAINER_CARS("CONTAINER_CARS"),

    COVERED_FREIGHT_CARS("COVERED_FREIGHT_CARS"),

    DEEP_WELL_FLAT_CARS("DEEP_WELL_FLAT_CARS"),

    DUMP_CARS("DUMP_CARS"),

    GONDOLA("GONDOLA"),

    HEAVY_GOODS_WAGONS("HEAVY_GOODS_WAGONS"),

    HINGED_COVER_WAGONS("HINGED_COVER_WAGONS"),

    HOPPER_WAGON("HOPPER_WAGON"),

    REFRIGERATOR_CARS("REFRIGERATOR_CARS"),

    SILO_CONTAINER_CARS("SILO_CONTAINER_CARS"),

    SLIDE_TARPAULIN_WAGON("SLIDE_TARPAULIN_WAGON"),

    SLIDING_WALL_BOXCARS("SLIDING_WALL_BOXCARS"),

    SPECIAL_TRANSPORT("SPECIAL_TRANSPORT"),

    STAKE_WAGONS("STAKE_WAGONS"),

    SWING_ROOF_WAGON("SWING_ROOF_WAGON"),

    TANK_CARS("TANK_CARS"),

    TELESCOPE_HOOD_WAGONS("TELESCOPE_HOOD_WAGONS");

    private String value;

    FreightCarType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static FreightCarType fromValue(String value) {
        for (FreightCarType b : FreightCarType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
