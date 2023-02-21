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

import io.github.carlomicieli.catalog.util.Slug;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/railways")
public class RailwaysController {
    private final RailwaysService railwaysService;

    public RailwaysController(RailwaysService railwaysService) {
        this.railwaysService = railwaysService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postRailway(@Valid @RequestBody RailwayRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    @PostMapping("/{railwayId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putRailway(@PathVariable Slug railwayId, @Valid @RequestBody RailwayRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    @GetMapping("/{railwayId}")
    RailwayModel getRailwayById(@PathVariable Slug railwayId) {
        throw new UnsupportedOperationException("TODO");
    }

    @GetMapping
    CollectionModel<RailwayModel> getAllRailways() {
        throw new UnsupportedOperationException("TODO");
    }
}
