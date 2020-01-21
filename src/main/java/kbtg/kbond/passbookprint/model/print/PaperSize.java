package kbtg.kbond.passbookprint.model.print;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaperSize {
	private String width;
	private String height;
	private boolean autoResize;

}
