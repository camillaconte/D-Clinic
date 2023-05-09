package develhope.DClinic.security.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;

import java.util.Set;


@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(Collections.emptySet()),
    MANAGER(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;


}
