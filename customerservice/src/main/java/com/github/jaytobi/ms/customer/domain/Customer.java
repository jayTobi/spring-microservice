package com.github.jaytobi.ms.customer.domain;

import org.springframework.hateoas.ResourceSupport;

/**
 * A simple customer domain object.
 */
public class Customer extends ResourceSupport {

    private Long id;
    private String name;
    private String firstName;
    private String eMail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
