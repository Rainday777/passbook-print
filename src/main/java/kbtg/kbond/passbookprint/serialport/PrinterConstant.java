package kbtg.kbond.passbookprint.serialport;
/**
 * @author ThaChie
 */
public class PrinterConstant {

	// private PrinterXMLUtils xml;

	public PrinterConstant(String printer) {

		// for test in workbench
		// String
		// path=this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		// path=path.replace("file:/", "").replace("/TellerPlugin/bin/",
		// "").concat("/Epson/printer/printer-config.xml");
		// path = "C:/Program Files/Fidelity/Profile
		// Workbench/workspace/Epson/printer/printer-config.xml";
		// xml = new PrinterXMLUtils(path);

		// for builds
		// xml = new
		// PrinterXMLUtils("plugins/EpsonPOS_1.0.0/printer/printer-config.xml");
		// xml = new PrinterXMLUtils("printer/printer-config.xml");

		// xml.setPrinter(printer);

		// $ = getCharFromString("dollar-sign");
		// ABSOLUTE_HORIZONTAL_POSITION =
		// getFloatFromString("horizontal-position");
		// ADVANCE_VERTICAL_POSITION = getFloatFromString("vertical-position");
		// PASSBOOK_ADVANCE_VERTICAL_POSITION =
		// getFloatFromString("pbk-vertical-position");
		// PASSBOOK_ABSOLUTE_HORIZONTAL_POSITION =
		// getFloatFromString("pbk-horizontal-position");
		// ARGUMENT_0 = getCharFromString("number-0");
		// ARGUMENT_1 = getCharFromString("number-1");
		// ARGUMENT_2 = getCharFromString("number-2");
		// ARGUMENT_3 = getCharFromString("number-3");
		// ARGUMENT_15 = getCharFromString("number-15");
		// ARGUMENT_16 = getCharFromString("number-16");
		// ARGUMENT_32 = getCharFromString("number-32");
		// ARGUMENT_35 = getCharFromString("number-35");
		// ARGUMENT_49 = getCharFromString("number-49");
		// ARGUMENT_51 = getCharFromString("number-51");
		// ARGUMENT_53 = getCharFromString("number-53");
		// ARGUMENT_91 = getCharFromString("number-91");
		// ARGUMENT_113 = getCharFromString("number-113");
		// AT = getCharFromString("at");
		// BACKSLASH = getCharFromString("backslash");
		// BEL = getCharFromString("bel");
		// CR = getCharFromString("cr");
		// E = getCharFromString("e-large");
		// ESC = getCharFromString("esc");
		// F = getCharFromString("f-large");
		// FF = getCharFromString("ff");
		// g = getCharFromString("g-small");
		// J = getCharFromString("j-large");
		// l = getCharFromString("l-small");
		// LINE_FEED = getCharFromString("line-feed");
		// p = getCharFromString("p-small");
		// P = getCharFromString("p-large");
		// PARENTHESIS_LEFT = getCharFromString("parenthesis-left");
		// Q = getCharFromString("q-large");
		// t = getCharFromString("t-small");
		// TAB = getCharFromString("tab");
		// USA = getCharFromString("usa");
		// x = getCharFromString("x-small");
		// PLUS=getCharFromString("PLUS");
		// V =getCharFromString("v-large");
		// M =getCharFromString("m-large");

		$ = 36;
		ABSOLUTE_HORIZONTAL_POSITION = 1f;
		ADVANCE_VERTICAL_POSITION = 0f;
		PASSBOOK_ADVANCE_VERTICAL_POSITION = 0.5f;
		PASSBOOK_ABSOLUTE_HORIZONTAL_POSITION = 1f;
		ARGUMENT_0 = 0;
		ARGUMENT_1 = 1;
		ARGUMENT_2 = 2;
		ARGUMENT_3 = 3;
		ARGUMENT_15 = 15;
		ARGUMENT_16 = 16;
		ARGUMENT_32 = 32;
		ARGUMENT_35 = 35;
		ARGUMENT_49 = 49;
		ARGUMENT_51 = 51;
		ARGUMENT_53 = 53;
		ARGUMENT_91 = 91;
		ARGUMENT_113 = 113;
		AT = 64;
		BACKSLASH = 92;
		BEL = 7;
		CR = 13;
		E = 69;
		ESC = 27;
		F = 70;
		FF = 12;
		g = 103;
		J = 74;
		LINE_FEED = 10;
		l = 108;
		M = 77;
		P = 80;
		PARENTHESIS_LEFT = 40;
		PLUS = 43;
		p = 112;
		Q = 81;
		t = 120;
		TAB = 9;
		USA = 127;
		x = 120;
		V = 86;
	}

	// private char getCharFromString(String key){
	// char result=Character.MIN_VALUE;
	// try{
	// result = Character.toChars(Integer.parseInt(xml.getValue(key)))[0];
	// }catch (Exception ex){
	// System.err.print("ERROR : invalid key (" + key+")");
	// }
	// return result;
	// }

	// private float getFloatFromString(String key){
	// float result=0;
	// try{
	// result = Float.parseFloat(xml.getValue(key));
	// }catch (Exception ex){
	// System.err.print("ERROR : invalid key (" + key+")");
	// }
	// return result;
	// }

	/* decimal ascii values for epson ESC/P commands */
	public char AT; // @
	public char BACKSLASH;
	public char BEL;
	public char CR; // carriage return
	public char $; // used for absolute horizontal positioning
	public char ESC; // escape
	public char FF; // form feed
	public char g; // 15cpi pitch
	public char l; // used for setting left margin
	public char LINE_FEED; // line feed/new line
	public char PLUS; // Plus sign
	public char V; // absolute vertical

	public char E; // bold font on
	public char F; // bold font off
	public char J; // used for advancing paper vertically
	public char p; // used for choosing proportional mode or fixed-pitch
	public char P; // 10cpi pitch
	public char M; // 12cpi pitch
	public char PARENTHESIS_LEFT;
	public char Q; // used for setting right margin

	public char t; // used for character set assignment/selection
	public char TAB; // horizontal tab
	public char ARGUMENT_0;
	public char ARGUMENT_1;
	public char ARGUMENT_2;
	public char ARGUMENT_3;
	// public char ARGUMENT_4;
	// public char ARGUMENT_5;
	// public char ARGUMENT_6;
	// public char ARGUMENT_7;
	public char ARGUMENT_15;
	public char ARGUMENT_16;
	public char ARGUMENT_32;

	public char ARGUMENT_35;
	public char ARGUMENT_49;
	public char ARGUMENT_51;
	public char ARGUMENT_53;
	public char ARGUMENT_91;
	public char ARGUMENT_113;

	public char x = 120; // used for setting draft or letter quality (LQ)
	// printing
	/* character sets */
	public char USA = 1;

	/* layout */
	public float ADVANCE_VERTICAL_POSITION; // margin-top
	public float ABSOLUTE_HORIZONTAL_POSITION; // margin-left
	public float PASSBOOK_ADVANCE_VERTICAL_POSITION; // margin-top
	public float PASSBOOK_ABSOLUTE_HORIZONTAL_POSITION; // margin-left

}
