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
package io.github.carlomicieli.catalog.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrganizationEntityType {
    CIVIL_LAW_PARTNERSHIP("CIVIL_LAW_PARTNERSHIP"),

    ENTREPRENEURIAL_COMPANY("ENTREPRENEURIAL_COMPANY"),

    GLOBAL_PARTNERSHIP("GLOBAL_PARTNERSHIP"),

    LIMITED_COMPANY("LIMITED_COMPANY"),

    LIMITED_PARTNERSHIP("LIMITED_PARTNERSHIP"),

    LIMITED_PARTNERSHIP_LIMITED_COMPANY("LIMITED_PARTNERSHIP_LIMITED_COMPANY"),

    OTHER("OTHER"),

    PUBLIC_INSTITUTION("PUBLIC_INSTITUTION"),

    PUBLIC_LIMITED_COMPANY("PUBLIC_LIMITED_COMPANY"),

    REGISTERED_SOLE_TRADER("REGISTERED_SOLE_TRADER"),

    SOLE_TRADER("SOLE_TRADER"),

    STATE_OWNED_ENTERPRISE("STATE_OWNED_ENTERPRISE");

    private String value;

    OrganizationEntityType(String value) {
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
    public static OrganizationEntityType fromValue(String value) {
        for (OrganizationEntityType b : OrganizationEntityType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
