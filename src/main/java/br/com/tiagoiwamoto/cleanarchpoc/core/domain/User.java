package br.com.tiagoiwamoto.cleanarchpoc.core.domain;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:00
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.domain.enums.UserPreferenceEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 120)
    private String name;
    private String cep;
    private String address;
    private String neighborhood;
    private String city;
    private String state;
    @Enumerated(EnumType.ORDINAL)
    private UserPreferenceEnum preference;
    private Integer userPrefenceId;
    private LocalDateTime createdAt;
    private LocalDateTime removedAt;

    public User() {}

    public static User build() {
        return new User();
    }

}
