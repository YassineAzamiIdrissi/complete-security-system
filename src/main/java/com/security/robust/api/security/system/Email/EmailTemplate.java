package com.security.robust.api.security.system.Email;

import lombok.Getter;

@Getter
public enum EmailTemplate {
    ACTIVATE_ACCOUNT("activate_account");
    private final String name;

    EmailTemplate(String name) {
        this.name = name;
    }
}
