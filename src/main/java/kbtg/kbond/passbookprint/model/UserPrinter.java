package kbtg.kbond.passbookprint.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrinter implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	private String username;
	private String printerName;
	
	public UserPrinter(String usrNm) {
		username = usrNm;
	}

}
