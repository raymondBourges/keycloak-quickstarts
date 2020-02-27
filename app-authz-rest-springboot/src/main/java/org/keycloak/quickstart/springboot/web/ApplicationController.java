/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.keycloak.quickstart.springboot.web;

import org.keycloak.AuthorizationContext;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.idm.authorization.Permission;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
@RestController
public class ApplicationController {

    @RequestMapping(value = "/api/resourcea", method = RequestMethod.GET)
    public String handleResourceA(HttpServletRequest request) {
        return createResponse(request);
    }

    @RequestMapping(value = "/api/resourceb", method = RequestMethod.GET)
    public String handleResourceB(HttpServletRequest request) {
        return createResponse(request);
    }

    @RequestMapping(value = "/api/premium", method = RequestMethod.GET)
    public String handlePremiumResource(HttpServletRequest request) {
        return createResponse(request);
    }

    @RequestMapping(value = "/api/admin", method = RequestMethod.GET)
    public String handleAdminResource(HttpServletRequest request) {
        return createResponse(request);
    }

    private String createResponse(HttpServletRequest request) {
        KeycloakSecurityContext keycloakSecurityContext =
                (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        AuthorizationContext authzContext =
                keycloakSecurityContext.getAuthorizationContext();
        for (Permission permission : authzContext.getPermissions()) {
            System.out.println(permission);
        }
        return "Access Granted";
    }
}
