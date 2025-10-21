package edu.dosw.masterchef.model.dto.Request;

import edu.dosw.masterchef.model.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChefRequestDTO {

    private String name;

    private Role role;

}
