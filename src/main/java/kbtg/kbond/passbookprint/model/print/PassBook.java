package kbtg.kbond.passbookprint.model.print;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassBook {

	private Integer copies;
	private String header1;
	private String header2;
	private String branch;
	private String accountNo;
	private String holderName;
	private String holderAddress1;
	private String holderAddress2;
	private String holderAddress3;
	private boolean printCoverPage = true;
	private PaperSize paperSize;
	private String[] lines = new String[24];

}
