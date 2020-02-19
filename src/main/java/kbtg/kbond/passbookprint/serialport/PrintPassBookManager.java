package kbtg.kbond.passbookprint.serialport;

import java.awt.print.PrinterException;

import kbtg.kbond.passbookprint.model.print.PassBook;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrintPassBookManager {
	private PassBook passbook;
	public PrintPassBookManager(PassBook passbook) {
		this.passbook = passbook;
	}
	
	public void print() throws PrinterSerialPortException {
		
//		System.loadLibrary("WNT47X2");
//		System.loadLibrary("WNTSFPRT");	
//		System.loadLibrary("IBMImp");
		Printable printer = new IBMPrinter(null, false);
		Boolean printInit = printer.pbkInitialize();
		if(printInit) {
			setDefaultPassbookInitial(printer);
		}
		printer.passbookLineFeed();
		printer.print(this.passbook.getLines());
		printer.formFeed();	// eject paper

		if (printInit) {
			printer.close();	// close stream
			printInit = false;
		}
	}
	
	private void setDefaultPassbookInitial(Printable printer){
		printer.passbookLineFeed();
		printer.pbkLineFeed();
		printer.pbkLineFeed();
		printer.pbkHalfLineFeed();
	}
	

}
