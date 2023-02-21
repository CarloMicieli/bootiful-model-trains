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
package io.github.carlomicieli.catalog.railways;

import static org.springframework.data.relational.core.mapping.Embedded.OnEmpty.USE_NULL;

import io.github.carlomicieli.catalog.brands.Address;
import io.github.carlomicieli.catalog.shared.LocalizedText;
import io.github.carlomicieli.catalog.shared.OrganizationEntityType;
import io.github.carlomicieli.catalog.shared.Socials;
import io.github.carlomicieli.catalog.util.Slug;
import io.soabase.recordbuilder.core.RecordBuilder;
import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table("railways")
@RecordBuilder
public record Railway(
        @Id Slug railwayId,
        String name,
        String abbreviation,
        String registeredCompanyName,
        OrganizationEntityType organizationEntityType,
        @Embedded(onEmpty = USE_NULL, prefix = "description_") LocalizedText description,
        String country,
        @Embedded(onEmpty = USE_NULL) RailwayPeriodOfActivity periodOfActivity,
        @Embedded(onEmpty = USE_NULL) RailwayGauge gauge,
        String headquarters,
        @Embedded(onEmpty = USE_NULL, prefix = "total_length_") RailwayTotalLength totalLength,
        @Embedded(onEmpty = USE_NULL, prefix = "address_") Address address,
        @Embedded(onEmpty = USE_NULL, prefix = "socials_") Socials socials,
        @CreatedDate Instant createdDate,
        @LastModifiedDate Instant lastModifiedDate,
        @Version Integer version) {}
