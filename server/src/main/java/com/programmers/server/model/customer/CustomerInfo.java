package com.programmers.server.model.customer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
public class CustomerInfo {
    private final String name;
    private final String email;

    public CustomerInfo(String name, String email) {
        validateName(name);
        validateEmail(email);
        this.name = name;
        this.email = email;
    }

    private void validateName(String name){
        Assert.notNull(name, "이름은 공백일 수 없습니다.");
        Assert.isTrue(name.length() >= 3 || name.length() <= 12,"이름의 길이는 3이상 12이하여야합니다.");
    }

    private void validateEmail(String email) {
        Assert.notNull(email, "address should not be null");
        Assert.isTrue(email.length() >= 4 && email.length() <=50,
                "address length must be between 4 and 50 characters");
        Assert.isTrue(checkAddress(email), "Invalid email address");
    }

    private static boolean checkAddress(String address){
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b",address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInfo that = (CustomerInfo) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
