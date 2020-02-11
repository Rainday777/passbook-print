package kbtg.kbond.passbookprint.serialport;

public interface Printable {
	public void close();
	public void close(String title,String message);
	public void closeSlipless(String title,String message);
	public boolean initialize();
	public void setCharacterSet(char charset);
	public void lineFeed();
	public void formFeed();
	public void bold(boolean bold);
	public void setAbsoluteHorizontalPosition(float centimeters);
	public void setAbsoluteVerticalPosition(float centimeters);
	public void print(String text);
	public void print(String[] text);
	public void print(Object[] text);
	public void print(Object[] text,String lineFeed);
	public void print(Object[] text,String lineFeed,String reverseFeed);

	public void selectPBKLineSpace();
	public String getShare();

	public void space();
	public void setAdvanceVertical();
	public void passbookLineFeed();
	public void pbkHalfLineFeed();
	public boolean pbkInitialize();
	public void pbkLineFeed();
	public void pbkPrint(String[] lineData);
	public boolean pbkCoverInitialize();
	public void reverseLineFeed();
	public void selectLineSpace();
	public void selectLineSpace(int size);
	public void cancelCondense();
	public String select15CPI();
	public void setLeftMargin(int indent);

}