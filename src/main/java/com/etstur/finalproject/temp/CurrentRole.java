package com.etstur.finalproject.temp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentRole {
    private Long id;
    private String name;

    public CurrentRole() {
    }

    public CurrentRole(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "CurrentRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
