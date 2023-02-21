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
package io.github.carlomicieli.catalog.scales;

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
@RequestMapping("/scales")
public class ScalesController {
    private final ScalesService scalesService;

    public ScalesController(ScalesService scalesService) {
        this.scalesService = scalesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postScale(@Valid @RequestBody ScaleRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    @PostMapping("/{scaleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putScale(@PathVariable Slug scaleId, @Valid @RequestBody ScaleRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    @GetMapping("/{scaleId}")
    ScaleModel getScaleById(@PathVariable Slug scaleId) {
        throw new UnsupportedOperationException("TODO");
    }

    @GetMapping
    CollectionModel<ScaleModel> getAllScales() {
        throw new UnsupportedOperationException("TODO");
    }
}
