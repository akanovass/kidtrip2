package kz.iitu.kidtirp.model.dto.response;

import kz.iitu.kidtirp.security.service.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private List<String> roles;

	private String errorMessage;

	public JwtResponse(String accessToken, UserDetailsImpl userDetails, List<String> roles) {
		this.token = accessToken;
		this.id = userDetails.getId();
		this.username = userDetails.getUsername();
		this.roles = roles;
	}
}
