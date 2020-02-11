package kbtg.kbond.passbookprint.serialport;

import com.tnis.device.IBM;

public class IBMPrinter extends Printer implements Printable {
	
    /* printer config */
    private PrinterConstant conf;
    public final int MAX_ADVANCE_9PIN = 216; //for 24/48 pin esc/p2 printers this should be 180
    public final int MAX_ADVANCE_24PIN = 180;
    public final int MAX_UNITS = 127; //for vertical positioning range is between 0 - 255 (0 <= n <= 255) according to epson ref. but 255 gives weird errors at 1.5f, 127 as max (0 - 128) seems to be working
    public final float CM_PER_INCH = 2.54f;    /** Creates a new instance of ESCPrinter */
    
	private IBM ibm;
    
    private StringBuffer pstream;
    private String defaultCPI;
    
    
    public IBMPrinter(String printer, boolean escp24pin) {
      
        this.conf = new PrinterConstant("epson");
        this.printer = printer;
        this.escp24pin = escp24pin;
        //ibm = IBMInterface.INSTANCE;
        ibm = new IBM();


    }

    
      
    public boolean initialize() {
        //post: returns true if stream to network printer successfully opened, streams for writing to esc/p printer created
        streamOpenSuccess = true;

        //System.out.println("[method]initialize->"+this);
        	pstream = new StringBuffer("");
//        	streamOpenSuccess = ibm.ibmInitialize();
        	reset();
            //layout
        	
//            this.advanceVertical(conf.ADVANCE_VERTICAL_POSITION);
//            this.setAbsoluteHorizontalPosition(conf.ABSOLUTE_HORIZONTAL_POSITION);
//            
        	//set line space 
            //selectLineSpace();
        	selectLineSpace();
//            
            //select 10-cpi character pitch
        	defaultCPI = select15CPI();
//            
            //select draft quality printing
            selectDraftPrinting();
//
//            //set character set
//            setCharacterSet(conf.USA);
//            streamOpenSuccess = true;

        
        return streamOpenSuccess;
    }
   
    public boolean pbkInitialize() {
        //post: returns true if stream to network printer successfully opened, streams for writing to esc/p printer created
        streamOpenSuccess = true;

        //System.out.println("[method]pbkInitialize->"+this);
       
        	
        	pstream = new StringBuffer("");
//        	streamOpenSuccess = ibm.ibmInitialize();
        	reset();
            //reset default settings
            //pstream.append(conf.ESC);
            //pstream.append(conf.AT);

            //layout
            //this.advanceVertical(conf.PASSBOOK_ADVANCE_VERTICAL_POSITION);
            //this.setAbsoluteHorizontalPosition(conf.PASSBOOK_ABSOLUTE_HORIZONTAL_POSITION);
            
            //set line space 
            selectPBKLineSpace();
            
            //select 10-cpi character pitch
            defaultCPI = select12CPI();
            
            //setLeftMargin(1);
            
            //select draft quality printing
            selectDraftPrinting();

            //set character set
            //setCharacterSet(conf.USA);
            
            //pbkLineFeed();
            //pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkLineFeed();pbkLineFeed();            
            pbkLineFeed();pbkLineFeed();

       
        
        return streamOpenSuccess;
    }
    public boolean pbkfixInitialize() {
        //post: returns true if stream to network printer successfully opened, streams for writing to esc/p printer created
        streamOpenSuccess = true;

        //System.out.println("[method]pbkInitialize->"+this);
       
        	
        	pstream = new StringBuffer("");
//        	streamOpenSuccess = ibm.ibmInitialize();
        	reset();
            //reset default settings
            //pstream.append(conf.ESC);
            //pstream.append(conf.AT);

            //layout
            //this.advanceVertical(conf.PASSBOOK_ADVANCE_VERTICAL_POSITION);
            //this.setAbsoluteHorizontalPosition(conf.PASSBOOK_ABSOLUTE_HORIZONTAL_POSITION);
            
            //set line space 
        	selectPBKfixLineSpace();
            
            //select 10-cpi character pitch
        	defaultCPI = select15CPI();
            
            //setLeftMargin(1);
            
            //select draft quality printing
            selectDraftPrinting();

            //set character set
            //setCharacterSet(conf.USA);
            
            //pbkLineFeed();
            //pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkHalfLineFeed();
            pbkLineFeed();pbkLineFeed();            
            pbkLineFeed();pbkLineFeed();
        
        return streamOpenSuccess;
    }    
    public boolean pbkCoverInitialize() {
        //post: returns true if stream to network printer successfully opened, streams for writing to esc/p printer created
        streamOpenSuccess = true;

        //System.out.println("[method]pbkCoverInitialize->"+this);
        
        pstream = new StringBuffer("");
//        streamOpenSuccess = ibm.ibmInitialize();
        	reset();
            //layout
            //this.advanceVertical(conf.PASSBOOK_ADVANCE_VERTICAL_POSITION);
            //this.setAbsoluteHorizontalPosition(conf.PASSBOOK_ABSOLUTE_HORIZONTAL_POSITION);
            
            //set line space 
            selectPBKLineSpace();
            
            //select 10-cpi character pitch
            defaultCPI = select15CPI();
            //setLeftMargin(10);
            
            //select draft quality printing
            selectDraftPrinting();

            //set character set
            //setCharacterSet(conf.USA);
//            streamOpenSuccess = true;
            pbkHalfLineFeed();
       
        
        return streamOpenSuccess;
    }
    
    public void setCharacterSet(char charset) {
        //System.out.println("[method]setCharacterSet->" + charset);
        //assign character table
        pstream.append(conf.ESC);
        pstream.append(conf.ARGUMENT_91);
        pstream.append(conf.ARGUMENT_53);
        pstream.append(conf.ARGUMENT_53);
        pstream.append(conf.ARGUMENT_51);
        pstream.append(conf.ARGUMENT_35);
        pstream.append(conf.ARGUMENT_113);

        /*27 ESC
        91
        53
        53
        51
        35
        113*/
        /*pstream.print(conf.ESC);
        pstream.print(conf.PARENTHESIS_LEFT);
        pstream.print(conf.t);
        pstream.print(conf.ARGUMENT_3); //always 3
        pstream.print(conf.ARGUMENT_0); //always 0
        pstream.print(conf.ARGUMENT_1); //selectable character table 1
        pstream.print(conf.ARGUMENT_1); //registered character table (arg_25 is brascii)
        pstream.print(conf.ARGUMENT_0); //always 0*/
        
        //select character table
        /*pstream.print(conf.ESC);
        pstream.print(conf.t);
        pstream.print(conf.ARGUMENT_1); //selectable character table 1
        //pstream.print('5'); //selectable character table 1
        */
      
    }
    
    public void formFeed() {
        //System.out.println("[method]formFeed");
        //post: ejects single sheet
        pstream.append(conf.CR); //according to epson esc/p ref. manual it is recommended to send carriage return before form feed
        pstream.append(conf.FF);
    }

    public void lineFeed() {
        //System.out.println("[method]lineFeed");
        //post: performs new line
        pstream.append(conf.CR); //according to epson esc/p ref. manual always send carriage return before line feed
        pstream.append(conf.LINE_FEED);
        pstream.append(conf.LINE_FEED);
//        this.setAbsoluteHorizontalPosition(conf.ABSOLUTE_HORIZONTAL_POSITION);
    }
    
    public void lineFeed(String size) {
    	int num = Integer.parseInt(size);    	
    	selectLineSpace(num);
    	lineFeed();
    	selectLineSpace();
    }
    
    public void halfLineFeed() {
        //System.out.println("[method]halfLineFeed");
        //post: performs new line
        pstream.append(conf.CR); //according to epson esc/p ref. manual always send carriage return before line feed
        pstream.append(conf.LINE_FEED);
//        this.setAbsoluteHorizontalPosition(conf.ABSOLUTE_HORIZONTAL_POSITION);
    }

    public void setAbsoluteVerticalPosition(float centimeters) {
        //pre: centimenters >= 0 (cm)
        //post: sets absolute vertical add position to x centimeters from top margin

        //System.out.println("[method]setAbsoluteVerticalPosition->" + centimeters);
        float inches = centimeters / CM_PER_INCH;
        int units_low = (int) (inches * 60) % 256;
        int units_high = (int) (inches * 60) / 256;

        pstream.append(conf.ESC);
        pstream.append(conf.PARENTHESIS_LEFT);
        pstream.append(conf.V);
        pstream.append((char) units_low);
        pstream.append((char) units_high);
    }

    public void setAbsoluteHorizontalPosition(float centimeters) {
        //pre: centimenters >= 0 (cm)
        //post: sets absolute horizontal add position to x centimeters from left margin
    	
        //System.out.println("[method]setAbsoluteHorizontalPosition->" + centimeters);
        float inches = centimeters / CM_PER_INCH;
        int units_low = (int) (inches * 60) % 256;
        int units_high = (int) (inches * 60) / 256;

        pstream.append(conf.ESC);
        pstream.append(conf.$);
        pstream.append((char) units_low);
        pstream.append((char) units_high);
    }
    
    public void advanceVertical(float centimeters) {
        //pre: centimeters >= 0 (cm)
        //post: advances vertical add position approx. y centimeters (not precise due to truncation)
        float inches = centimeters / CM_PER_INCH;
        int units = (int) (inches * (escp24pin ? MAX_ADVANCE_24PIN : MAX_ADVANCE_9PIN));

        while (units > 0) {
            char n;
            if (units > MAX_UNITS) {
                n = (char) MAX_UNITS;
            } //want to move more than range of parameter allows (0 - 255) so move max amount
            else {
                n = (char) units;
            } //want to move a distance which fits in range of parameter (0 - 255)

            pstream.append(conf.ESC);
            pstream.append(conf.J);
            pstream.append(n);

            units -= MAX_UNITS;
        }
    }

    public void advanceHorizontal(float centimeters) {
        //pre: centimeters >= 0
        //post: advances horizontal add position approx. centimeters
        float inches = centimeters /CM_PER_INCH;
        int units_low = (int) (inches * 120) % 256;
        int units_high = (int) (inches * 120) / 256;

        pstream.append(conf.ESC);
        pstream.append(conf.BACKSLASH);
        pstream.append((char) units_low);
        pstream.append((char) units_high);
    }
    
    public void horizontalTab(int tabs) {
        //pre: tabs >= 0
        //post: performs horizontal tabs tabs number of times
        for (int i = 0; i < tabs; i++) {
            pstream.append(conf.TAB);
        }
    }
    
    public void setAdvanceVertical(){
/*    	for(int i=0;i<1;i++){
    		this.pbkHalfLineFeed();
    	}*/

    }
    
    public void bold(boolean bold) {
        //System.out.println("[method]bold->" + bold);
        pstream.append(conf.ESC);
        if (bold) {
            pstream.append(conf.E);
        } else {
            pstream.append(conf.F);
        }
    }
    
    public void beep() {
        //System.out.println("[method]beep");
        pstream.append(conf.BEL);
    }

    public void space(){
    	pstream.append(conf.ARGUMENT_32);
    }
    
    public void setDoubleWide(){
    	pstream.append((char) 14);
    }
    
    public void cancelDoubleWide(){
    	pstream.append((char) 20);
    }
    
    public void print(String text) {
    	
        if(isChangeFont(text)){
        	while(!text.equals("")){
    			
    			int startStr = text.indexOf("{");
    			int endStr = text.indexOf("}"); 		
      		
				if( startStr != 0){
					this.selectDefaultCPI();
					
					startStr = text.indexOf("{");
					if(startStr>-1){
						this.print(text.substring(0, startStr).toCharArray());
						text = text.substring(startStr) ;
    				}else{
    					this.print(text.toCharArray());
						text = "";
    					break;
    				}
				}else{
            		String symbol = text.substring(startStr, endStr+1);
            		String size = symbol.replace("{", "").replace("}", "");
            		
            		if(size.equals("40")){
            			this.select10CPI();
            			this.setDoubleWide();
            			endStr = text.indexOf("}");
            			text = text.substring(endStr+1) ;
        				startStr = text.indexOf("{");
        				this.print(text.substring(0,startStr).toCharArray());
        				endStr = text.indexOf("}");
            			text = text.substring(endStr+1) ;
            			this.cancelDoubleWide();
            		}else if(size.equals("30")){
            			this.select10CPI();
            			endStr = text.indexOf("}");
            			text = text.substring(endStr+1) ;
        				startStr = text.indexOf("{");
        				this.print(text.substring(0,startStr).toCharArray());
        				endStr = text.indexOf("}");
            			text = text.substring(endStr+1) ;
            		}else if(size.equals("20")){
            			this.select12CPI();
            			endStr = text.indexOf("}");
            			text = text.substring(endStr+1) ;
        				startStr = text.indexOf("{");
        				this.print(text.substring(0,startStr).toCharArray());
        				endStr = text.indexOf("}");
            			text = text.substring(endStr+1) ;
            		}else if(size.equals("10")){
            			this.select15CPI();
            			endStr = text.indexOf("}");
            			text = text.substring(endStr+1) ;
        				startStr = text.indexOf("{");         					
        				this.print(text.substring(0,startStr).toCharArray());
        				endStr = text.indexOf("}");
            			text = text.substring(endStr+1) ;
            		}else{
            			this.selectDefaultCPI();
            			endStr = text.indexOf("}");
            			text = text.substring(endStr+1) ;
        				startStr = text.indexOf("{");
        				this.print(text.substring(0,startStr).toCharArray());
        				endStr = text.indexOf("}");
            			text = text.substring(endStr+1) ;
            		}	
				}
    		}
    		
    		if(text.equals("")){
    			this.selectDefaultCPI();
			}
        }else{
        	this.print(text.toCharArray());
        }
        
        //###### Do line-feed after the end of line ######  
        this.halfLineFeed();
    
    }
    
    public void print(char[] data){
    	for(int i=0;i<data.length;i++){
        	pstream.append(data[i]);
        }
    }
    
    public boolean isChangeFont(String text){
    	boolean changeFont = false;
    	if(text!=null){
    		if(text.indexOf("{")>-1&&text.indexOf("}")>-1&&text.lastIndexOf("{")!=text.indexOf("{")){       
    			changeFont = true;
    		}
    	}
    	return changeFont;
    }
    
    public void print(String[] texts) {
    	for(String text:texts){
    		this.print(text);
    	}
    }

    public void print(Object[] object) {
        
    	Integer level = (Integer)object[0];
		String[] text = (String[]) object[1];
    	
		if(level==0){
			this.print(text);
		}else if(object!=null){
			this.print(""); 
		}
    }

    public void proportionalMode(boolean proportional) {
        pstream.append(conf.ESC);
        pstream.append(conf.p);
        if (proportional) {
            pstream.append((char) 49);
        } else {
            pstream.append((char) 48);
        }
    }
    
    public void setMargins(int columnsLeft, int columnsRight) {
        //pre: columnsLeft > 0 && <= 255, columnsRight > 0 && <= 255
        //post: sets left margin to columnsLeft columns and right margin to columnsRight columns
        //left
        pstream.append(conf.ESC);
        pstream.append(conf.l);
        pstream.append((char) columnsLeft);

        //right
        pstream.append(conf.ESC);
        pstream.append(conf.Q);
        pstream.append((char) columnsRight);
    }

    public String select10CPI() { //10 characters per inch (condensed available)
        //System.out.println("[method]select10CPI");
        StringBuffer stream = new StringBuffer();
        stream.append((char)27);
        stream.append((char)18);
        pstream.append(stream);
        return stream.toString();
    }

    public String select15CPI() { //15 characters per inch (condensend not available)
        //System.out.println("[method]select15CPI");
        StringBuffer stream = new StringBuffer();
        stream.append((char)27);
        stream.append((char)18);
        stream.append((char)27);
        stream.append((char)15);
        pstream.append(stream);
        return stream.toString();
    }
    
    public String select12CPI() { //12 characters per inch (condensend not available)
        //System.out.println("[method]select15CPI");
        StringBuffer stream = new StringBuffer();
        stream.append((char)27);
        stream.append((char)58);
        pstream.append(stream);
        return stream.toString();
    }
    
    public void selectDefaultCPI(){
    	if(defaultCPI!=null){
    		char[] data = defaultCPI.toCharArray();
    		for(int i=0;i<data.length;i++){
            	pstream.append(data[i]);
            }
    	}
    }

    public void selectDraftPrinting() { //set draft quality adding
    	pstream.append(conf.ESC);
    	pstream.append((char) 72);
    }

    public void selectLQPrinting() { //set letter quality adding
        pstream.append(conf.ESC);
        pstream.append((char) 71);
    }
    
    public void selectLineSpace() {
    	
        pstream.append((char)27);
        pstream.append((char)51); 
        pstream.append((char)8);//edit 8 to 11 by rock

    }
    
    public void selectLineSpace(int size) {
    	
        pstream.append((char)27);
        pstream.append((char)51); 
        pstream.append((char)size);

    }
    
    //for PBK
    public void selectPBKLineSpace() {
    	pstream.append((char)27);
        pstream.append((char)51); 
        pstream.append((char)9);//edit 6 to 9 by rock
    }
    
    public void selectPBKfixLineSpace() {
    	pstream.append((char)27);
        pstream.append((char)51); 
        pstream.append((char)11);//edit 6 to 9 by rock
    }

    
    public void pbkPrint(String text) {
        //System.out.println("[method]pbkPrint -->" + text);
        pstream.append(text);
        pbkLineFeed();
    }
    
    public void pbkPrint(String[] text) {
        
        for(int i=0;i<text.length;i++){
        	System.out.println("[method]add -->" + text[i]);	
            pstream.append(text[i]);
        	switch(i){
	//        	case 0 : pbkHalfLineFeed(); break;
	//    		case 1 : pbkLineFeed();pbkHalfLineFeed();  break;
	//    		case 2 : pbkLineFeed();pbkHalfLineFeed(); break;
	//    		case 3 : pbkHalfLineFeed();break;
        		case 0 : pbkHalfLineFeed(); break;
        		case 1 : pbkHalfLineFeed(); break;
        		case 2 : pbkHalfLineFeed(); break;
        		case 3 : pbkHalfLineFeed(); break;
        	}
        }
    }
    
    public void passbookLineFeed() {
    	for(int i=0;i<4;i++){
    		this.pbkHalfLineFeed();
    	}

    }
    
    public void pbkLineFeed() {
        //System.out.println("[method]pbkLineFeed");
        //post: performs new line
        pstream.append(conf.CR); //according to epson esc/p ref. manual always send carriage return before line feed
        //pstream.append((char)63);
        pstream.append(conf.LINE_FEED);
        pstream.append(conf.LINE_FEED); //comment by rock
        //reverseLineFeed();
        //this.setAbsoluteHorizontalPosition(conf.ABSOLUTE_HORIZONTAL_POSITION);
    }
    
    public void pbkHalfLineFeed() {
        //System.out.println("[method]halfLineFeed");
        //post: performs new line
        pstream.append(conf.CR); //according to epson esc/p ref. manual always send carriage return before line feed
        pstream.append(conf.LINE_FEED);
        //this.setAbsoluteHorizontalPosition(conf.ABSOLUTE_HORIZONTAL_POSITION);
    }
    
    //Thongchai create this method for set left, right margin
    public void setLeftMargin(int indent) {
        pstream.append((char)27);
        pstream.append((char)88); 
        pstream.append((char)indent);
        pstream.append((char)142);
    }
    
    public void reverseLineFeed() {
        pstream.append((char)27);
        pstream.append((char)93); 
    }
    
    public void reverseLineFeed(String size ) {
    	
    	int num = Integer.parseInt(size);
    	
    	selectLineSpace(num);
    	reverseLineFeed();
    	selectLineSpace();
    }
    
    public void close() {
    	boolean printInit = false;
    	boolean checkDevice = false;
    	boolean printerConfirm = false;
    	try{
    		
    		try{

    			do{
    				System.out.println("CheckDevice");
    				checkDevice = ibm.ibmCheckDevice();
    				if(!checkDevice){
//    					printerConfirm = MessageDialog.openConfirm(Application.getShell(), "Passbook Printer Check Status", "Please check your printer status. Would you like to try again?");
    				}
    			}while(checkDevice==false&&printerConfirm==true);
    		}catch(java.lang.UnsatisfiedLinkError e){
    			e.printStackTrace();
    			checkDevice = true;
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    		if(checkDevice==true){
    			//Vorapoj Modify
    			System.out.println("Initialize");
    			printInit = ibm.ibmInitialize();
    			String printData = "";
    			printData = pstream.toString();

    			if(printData.trim().length()>0){
    				try{

						System.out.println("AddBuffer String Int");
						ibm.ibmAddBuffer(printData,printData.length());
						System.out.println("Print Int");
						ibm.ibmPrint(1);
						
    				}catch(java.lang.UnsatisfiedLinkError e){
    					e.printStackTrace();
    				}

    				if(printInit&&streamOpenSuccess){
    					try{


    					}catch(java.lang.UnsatisfiedLinkError e){
    						ibm.ibmPrint(1);
    						
    					}
    				}
    				else{
    					if(printInit==false){
    						throw new Exception("Passbook Print cannot initialize.");
    					}
    				}
    			}
    		}
    		else {
				throw new Exception("Passbook Print Status is not ready.");
			}
    	} catch (Exception ex) {
//    		Application.handleException(ex);
    		ex.printStackTrace();
    	}
    	finally{
    		//System.out.println("[method]TN.close");
    		//printDialog(10,"","");
    		System.out.println("Close");
    		ibm.ibmClose();
    		streamOpenSuccess = false;
    	}
    }
    
  //TN Customized method - End
	public void print(Object[] object, String lineFeed, String reverseLineFeed) {		
		if(lineFeed!=null&&lineFeed.trim().length()>0){
			lineFeed(lineFeed);
		}
		if(reverseLineFeed!=null&&reverseLineFeed.trim().length()>0){
			reverseLineFeed(reverseLineFeed);
		}
		print(object);
	}
	
	


	public void cancelCondense() {
		// TODO Auto-generated method stub
		
	}


	public void print(Object[] text, String lineFeed) {
		// TODO Auto-generated method stub
		
	}
	
	//Cancels subscript Cancels emphasized
	public void reset(){
        pstream.append((char)27);
        pstream.append((char)70); 
        pstream.append((char)27);
        pstream.append((char)84); 
	}

	
    public void close(String tittle, String message) {
    	boolean printInit = false;
    	boolean checkDevice = false;
    	boolean printerConfirm = false;
    	try{
    		
    		try{

    			do{
    				checkDevice = ibm.ibmCheckDevice();
    				if(!checkDevice){
//    					printerConfirm = MessageDialog.openConfirm(Application.getShell(), "Passbook Printer Check Status", "Please check your printer status. Would you like to try again?");
    				}
    			}while(checkDevice==false&&printerConfirm==true);
    		}catch(java.lang.UnsatisfiedLinkError e){
    			e.printStackTrace();
    			checkDevice = true;
    		}
    		

    			printInit = ibm.ibmInitialize();
    			String printData = "";
    			printData = pstream.toString();

    			if(printData.trim().length()>0){
    				try{
    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x5B,0x4B,0x01,0x00,0x04});
    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x5B,0x46,0x01,0x00,0x03});
    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x32,0x12});
    				}catch(java.lang.UnsatisfiedLinkError e){
    					//e.printStackTrace();
    				}
    				ibm.ibmAddBuffer(printData,printData.length());
    				if(printInit&&streamOpenSuccess){
    					try{

    						int printerTimeOut = 30;

    						boolean printerReturn = false;
    						boolean stopMessage = false;
    						short rc =0;

    						//rc =  ibm.ibmBeep();


    						System.out.println("    printerReturn          "+printerReturn);
    						System.out.println("    stopMessage            "+stopMessage);
    						    						
    						if(stopMessage){
    				    		ibm.ibmClose();  							
    							printInit = ibm.ibmInitialize();
    							System.out.println("      printInit     "+printInit);
    							if(printInit){
	    		    				try{
	    		    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x5B,0x4B,0x01,0x00,0x04});
	    		    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x5B,0x46,0x01,0x00,0x03});
	    		    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x32,0x12});
	    		    				}catch(java.lang.UnsatisfiedLinkError e){
	    		    					//e.printStackTrace();
	    		    				}
	    		    				ibm.ibmAddBuffer(printData,printData.length());
	    							printerReturn = ibm.ibmPrint(printerTimeOut);
    							}
    						}else{
        						//rc = ibm.ibmPrintData();
        						//rc = ibm.ibmEject();   
    						}
    						
    						if(!printerReturn){
//    							Application.handleException(new Exception("Print Time Out "+printerTimeOut+" sec."));
    						}
    					}catch(java.lang.UnsatisfiedLinkError e){
    						//ibm.ibmPrint();
    						
    					}
    				}
    				else{
    					if(printInit==false){
    						throw new Exception("Passbook Print cannot initialize.");
    					}
    				}
    			}
//    		}
    		else {
				throw new Exception("Passbook Print Status is not ready.");
			}
    	} catch (Exception ex) {
//    		Application.handleException(ex);
    	}
    	finally{
    		//System.out.println("[method]TN.close");
    		//printDialog(10,"","");
//    		ibm.ibmClose();
    		streamOpenSuccess = false;
    	}
    }
    
    public void closeSlipless(String tittle, String message) {
    	boolean printInit = false;
    	boolean checkDevice = false;
    	boolean printerConfirm = false;
    	try{
    		
    		try{

    			do{
    				checkDevice = ibm.ibmCheckDevice();
    				if(!checkDevice){
//    					printerConfirm = MessageDialog.openConfirm(Application.getShell(), "Passbook Printer Check Status", "Please check your printer status. Would you like to try again?");
    				}
    			}while(checkDevice==false&&printerConfirm==true);
    		}catch(java.lang.UnsatisfiedLinkError e){
    			e.printStackTrace();
    			checkDevice = true;
    		}
    		
    		if(checkDevice==true){
    			//Vorapoj Modify
    			printInit = ibm.ibmInitialize();
    			String printData = "";
    			printData = pstream.toString();

    			if(printData.trim().length()>0){
    				try{
    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x5B,0x4B,0x01,0x00,0x04});
    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x5B,0x46,0x01,0x00,0x03});
    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x32,0x12});
    				}catch(java.lang.UnsatisfiedLinkError e){
    					//e.printStackTrace();
    				}
    				ibm.ibmAddBuffer(printData,printData.length());
    				if(printInit&&streamOpenSuccess){
    					try{

    						int printerTimeOut = 30;

    						boolean printerReturn = false;
    						boolean stopMessage = false;
    						boolean stopPrint = false;
    						short rc =0;

    						//rc =  ibm.ibmBeep();


    						System.out.println("    printerReturn          "+printerReturn);
    						System.out.println("    stopMessage            "+stopMessage);
    						System.out.println("    stopPrint            "+stopPrint);
    						
    						if(stopMessage){
    				    		ibm.ibmClose();
    							printInit = ibm.ibmInitialize();
    							System.out.println("      printInit     "+printInit);
    							if(printInit){
	    		    				try{
	    		    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x5B,0x4B,0x01,0x00,0x04});
	    		    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x5B,0x46,0x01,0x00,0x03});
	    		    					//ibm.ibmPrintByteArray(new byte[]{0x1B,0x32,0x12});
	    		    				}catch(java.lang.UnsatisfiedLinkError e){
	    		    					//e.printStackTrace();
	    		    				}
	    		    				ibm.ibmAddBuffer(printData,printData.length());
	    							printerReturn = ibm.ibmPrint(printerTimeOut);
	        						if(!printerReturn){
//	        							Application.handleException(new Exception("Print Time Out "+printerTimeOut+" sec."));
	        						}
    							}   							
    						}else if(stopPrint){
    							printerReturn = true;
    						}else{
        						//rc = ibm.ibmPrintData();
        						//rc = ibm.ibmEject();   
    						}
    						

    					}catch(java.lang.UnsatisfiedLinkError e){
    						//ibm.ibmPrint();
    						
    					}
    				}
    				else{
    					if(printInit==false){
    						throw new Exception("Passbook Print cannot initialize.");
    					}
    				}
    			}
    		}
    		else {
				throw new Exception("Passbook Print Status is not ready.");
			}
    	} catch (Exception ex) {
//    		Application.handleException(ex);
    	}
    	finally{
    		System.out.print("final");
    		ibm.ibmClose();
    		streamOpenSuccess = false;
    	}
    }
 
}
