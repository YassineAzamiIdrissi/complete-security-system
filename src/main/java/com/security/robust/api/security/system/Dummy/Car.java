package com.security.robust.api.security.system.Dummy;

import com.security.robust.api.security.system.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Car {
    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private String description;
    private Integer prodYear;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
