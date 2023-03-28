package kz.iitu.kidtirp.model.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import kz.iitu.kidtirp.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDto {
    @NotBlank
    @Size(max = 20)
    private String username;
    private String legalForm;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private String sex;
    private String phone;
    private String email;
    private Boolean citizenRK;
    private String iin;
    private String docNumber;
    private String nationality;
    private String docIssuedBy;
    private Date docDateOfIssue;
    private Date docExpiredDate;
    private String birthPlace;

    public AgentDto(User user){
        this.username = user.getUsername();
    }
}
