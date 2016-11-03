package com.atm.domain.login;

import java.io.Serializable;

/**
 * Created by Axe on 2016-05-09.
 */
public class Login implements Serializable{
    private String idNumber;
    private String pin;
    private Long id;

    private Login(Builder builder)
    {
        this.idNumber = builder.idNumber;
        this.pin = builder.pin;
        this.id = builder.id;
    }

    public Long getId() {
        return id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getPin() {
        return pin;
    }

    public static class Builder{
        private String idNumber;
        private String pin;
        private Long id;

        public Builder id(Long id)
        {
            this.id = id;
            return  this;
        }

        public Builder idNumber(String idNumber)
        {
            this.idNumber = idNumber;
            return this;
        }


        public Builder pin(String pin)
        {
            this.pin = pin;
            return this;
        }

        public Builder copy(Login login)
        {
            this.idNumber = login.idNumber;
            this.pin = login.pin;
            this.id = login.id;
            return this;
        }

        public Login build()
        {
            return new Login(this);
        }
    }
}
