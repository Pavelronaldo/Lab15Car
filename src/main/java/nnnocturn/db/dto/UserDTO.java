package nnnocturn.db.dto;

import nnnocturn.db.entity.Entity;

import java.util.Objects;

public class UserDTO extends Entity {

    private String login;

    private String firstName;

    private String lastName;

    private int age;

    private String status;

    private String role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return age == userDTO.age &&
                login.equals(userDTO.login) &&
                firstName.equals(userDTO.firstName) &&
                lastName.equals(userDTO.lastName) &&
                status.equals(userDTO.status) &&
                role.equals(userDTO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, firstName, lastName, age, status, role);
    }

    @Override
    public int compareTo(Object o) {
        UserDTO u = (UserDTO) o;
        return login.compareTo(u.login);
    }
}
