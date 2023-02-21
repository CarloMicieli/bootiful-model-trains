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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import io.github.carlomicieli.catalog.util.Slug;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.RepresentationModel;

public class BrandModel extends RepresentationModel<BrandModel> {
    private final Brand model;

    public BrandModel(Brand model) {
        super(linkTo(BrandsController.class).slash(model.brandId()).withRel(IanaLinkRelations.SELF));
        this.model = model;
    }

    public Slug getBrandId() {
        return model.brandId();
    }

    public String getName() {
        return model.name();
    }

    public String getRegisteredCompanyName() {
        return model.registeredCompanyName();
    }

    public OrganizationEntityType getOrganizationEntityType() {
        return model.organizationEntityType();
    }

    public String getGroupName() {
        return model.groupName();
    }

    public LocalizedText getDescription() {
        return model.description();
    }

    public ContactInfo getContactInfo() {
        return model.contactInfo();
    }

    public Address getAddress() {
        return model.address();
    }

    public Socials getSocials() {
        return model.socials();
    }

    public BrandKind getKind() {
        return model.kind();
    }

    public BrandStatus getStatus() {
        return model.status();
    }

    public ResourceMetadata getMetadata() {
        return new ResourceMetadata(model.createdDate(), model.lastModifiedDate(), model.version());
    }
}
