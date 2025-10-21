package edu.dosw.masterchef.model.entity;

import edu.dosw.masterchef.model.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Chef {

    private String name;

    private Role role;

}
