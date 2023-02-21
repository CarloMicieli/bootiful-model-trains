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

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import javax.validation.constraints.Size;
import org.springframework.data.relational.core.mapping.Column;

public record LocalizedText(
        @Size(max = 2500) @Column("en") @JsonProperty("en") String englishText,
        @Size(max = 2500) @Column("it") @JsonProperty("it") String italianText) {

    private static final String ENGLISH = "en";
    private static final String ITALIAN = "it";

    public Map<String, String> toMap() {
        return Map.of(ENGLISH, this.englishText, ITALIAN, this.italianText);
    }
}
