package kbtg.kbond.passbookprint.model;

import lombok.Data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassbookData  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String accountNo;
	private String bookNo;

}
