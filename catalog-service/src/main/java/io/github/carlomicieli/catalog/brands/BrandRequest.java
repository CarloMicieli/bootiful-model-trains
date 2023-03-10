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
package io.github.carlomicieli.catalog.brands;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.carlomicieli.catalog.shared.ContactInfo;
import io.github.carlomicieli.catalog.shared.LocalizedText;
import io.github.carlomicieli.catalog.shared.OrganizationEntityType;
import io.github.carlomicieli.catalog.shared.Socials;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@RecordBuilder
public record BrandRequest(
        @JsonProperty("name") @NotNull @Size(min = 3, max = 50) String name,
        @JsonProperty("registered_company_name") @Size(max = 100) String registeredCompanyName,
        @JsonProperty("organization_entity_type") OrganizationEntityType organizationEntityType,
        @JsonProperty("group_name") @Size(max = 100) String groupName,
        @JsonProperty("description") @Valid LocalizedText description,
        @JsonProperty("contact_info") ContactInfo contactInfo,
        @JsonProperty("address") Address address,
        @JsonProperty("socials") Socials socials,
        @JsonProperty("kind") @NotNull @Valid BrandKind kind,
        @JsonProperty("status") BrandStatus status) {}
