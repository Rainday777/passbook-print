package kbtg.kbond.passbookprint.serialport;

public class PrinterSerialPortException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PrinterSerialPortException() {
		super();
	}

	public PrinterSerialPortException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);	
	}

	public PrinterSerialPortException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PrinterSerialPortException(String message) {
		super(message);
	}

	public PrinterSerialPortException(Throwable cause) {
		super(cause);
	}
	

}
