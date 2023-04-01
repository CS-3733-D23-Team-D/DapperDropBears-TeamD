package edu.wpi.teamname.database;

import lombok.Getter;
import lombok.Setter;

public class Login {
    @Getter @Setter private String username;
    @Getter @Setter private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
