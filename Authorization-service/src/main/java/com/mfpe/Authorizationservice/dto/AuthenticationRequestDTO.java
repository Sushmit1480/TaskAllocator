package com.mfpe.Authorizationservice.dto;

/*import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
*/

/*@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor*/

public class AuthenticationRequestDTO {
	private String username;
	private String password;
	
	public AuthenticationRequestDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public AuthenticationRequestDTO() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}