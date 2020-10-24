package by.belhard.project.model;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

@Getter
@Setter
@ToString(exclude = "password")
@EqualsAndHashCode(of = "id")
public class Account {

    private int id;
    private String username;
    private int cash;
    @Setter(AccessLevel.NONE)
    private String password;

    public Account(int id, String username, int cash, String password) {
        this.id = id;
        this.username = username;
        this.cash = cash;
        setPassword(password);
    }

    public Account(int id, String username, int cash) {
        this.id = id;
        this.username = username;
        this.cash = cash;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha256Hex(password);
    }

    public boolean checkPassword(String rawPassword){
        return DigestUtils.sha256Hex(rawPassword).equals(password);
    }
}
