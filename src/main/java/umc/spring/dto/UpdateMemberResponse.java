package umc.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateMemberResponse {
    Long id;
    String name;
}
