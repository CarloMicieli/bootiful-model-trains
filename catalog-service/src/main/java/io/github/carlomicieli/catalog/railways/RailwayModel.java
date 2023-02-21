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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import io.github.carlomicieli.catalog.shared.ResourceMetadata;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.RepresentationModel;

public class RailwayModel extends RepresentationModel<RailwayModel> {
    private final Railway model;

    public RailwayModel(Railway model) {
        super(linkTo(RailwaysController.class).slash(model.railwayId()).withRel(IanaLinkRelations.SELF));
        this.model = model;
    }

    public ResourceMetadata getMetadata() {
        return new ResourceMetadata(model.createdDate(), model.lastModifiedDate(), model.version());
    }
}
