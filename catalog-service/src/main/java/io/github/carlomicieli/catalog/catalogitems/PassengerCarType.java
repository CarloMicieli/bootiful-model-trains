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

public enum PassengerCarType {
    BAGGAGE_CAR("BAGGAGE_CAR"),

    COMBINE_CAR("COMBINE_CAR"),

    COMPARTMENT_COACH("COMPARTMENT_COACH"),

    DINING_CAR("DINING_CAR"),

    DOUBLE_DECKER("DOUBLE_DECKER"),

    DRIVING_TRAILER("DRIVING_TRAILER"),

    LOUNGE("LOUNGE"),

    OBSERVATION("OBSERVATION"),

    OPEN_COACH("OPEN_COACH"),

    RAILWAY_POST_OFFICE("RAILWAY_POST_OFFICE"),

    SLEEPING_CAR("SLEEPING_CAR");

    private String value;

    PassengerCarType(String value) {
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
    public static PassengerCarType fromValue(String value) {
        for (PassengerCarType b : PassengerCarType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
