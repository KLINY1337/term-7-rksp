package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_fullnames")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFullname {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String fullname;
}