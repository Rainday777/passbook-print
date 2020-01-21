package kbtg.kbond.passbookprint.controller;

import java.awt.print.PrinterException;

import javax.print.PrintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import kbtg.kbond.passbookprint.model.PassbookData;
import kbtg.kbond.passbookprint.model.UserPrinter;
import kbtg.kbond.passbookprint.model.print.PassBook;
import kbtg.kbond.passbookprint.service.PassbookPrinterService;
import lombok.Data;


@Controller
@Data
public class PrinterController {
	
	@Autowired
	private PassbookPrinterService printer;

	
	@MessageMapping("/write")
	@SendTo("/topic/write/success")
	public PassbookData writePassbook(PassbookData passbookData) {
		return passbookData;
	}
	
	@MessageMapping("/read")
	@SendTo("/topic/read/success")
	public PassbookData readPassbook()  {
		return null;
	}
	
	@MessageMapping("/print")
	@SendTo("/topic/print/success")
	public PassbookData printPassbook(PassBook passbook) throws PrinterException  {
		return printer.print(passbook);
	}
	
	@MessageMapping("/find")
	@SendTo("/topic/find/success")
	public UserPrinter findPrinter()  {
		UserPrinter p = new UserPrinter();
		if(printer!=null && printer.getPrinter() != null )
			p.setPrinterName(printer.getPrinter().getName());
		return p;
		
	}
	
	@MessageMapping("/setup")
	@SendTo("/topic/setup/success")
	public UserPrinter setPrinter(UserPrinter param) {		
		PrintService s = printer.setupPrinter(param.getPrinterName());
		UserPrinter us = new UserPrinter();
		if(s!=null) us.setPrinterName(s.getName());
		return us;
		
	}
	
}
