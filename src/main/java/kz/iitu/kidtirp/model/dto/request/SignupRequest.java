package kz.iitu.kidtirp.model.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private Long id;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private String fullName;
}
