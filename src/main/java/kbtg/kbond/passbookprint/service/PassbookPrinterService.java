package kbtg.kbond.passbookprint.service;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;

import javax.print.PrintService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import kbtg.kbond.passbookprint.model.PassbookData;
import kbtg.kbond.passbookprint.model.print.PassBook;
import kbtg.kbond.passbookprint.model.print.PrintPassBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassbookPrinterService {

	private static final Logger log = LoggerFactory.getLogger(PassbookPrinterService.class);
	@Autowired
	private PrintService printer;

	@Bean
	public PrintService createPrintService(@Value("${printer.name}") String name) {
		log.info("createPrintService printerName = {}", name);
		PassbookPrinterService service = new PassbookPrinterService();
		return service.setupPrinter(name);

	}

	public PrintService setupPrinter(String name) {

		return findPrinter(name);
	}

	public PrintService findPrinter(String printerName) {

		Pattern patten1 = Pattern.compile("\\b" + printerName + "\\b", Pattern.CASE_INSENSITIVE);
		Pattern patten2 = Pattern.compile("\\b" + printerName, Pattern.CASE_INSENSITIVE);
		Pattern patten3 = Pattern.compile(printerName, Pattern.CASE_INSENSITIVE);

		BiPredicate<PrintService, Pattern> isMatch = (s, p) -> p.matcher(s.getName()).find();

		PrintService[] services = PrinterJob.lookupPrintServices();
		printer = null;
		if (services.length > 0) {
			for (PrintService s : services) {
				log.info("findPrinter service {} ", s);
				if (isMatch.test(s, patten1) || isMatch.test(s, patten2) || isMatch.test(s, patten3)) {
					printer = s;
					break;
				} 
			}

			log.info("selected printer: {}", printer);

		}
		

		return printer;

	}

	public PassbookData print(PassBook passbook) throws PrinterException {

		PrintPassBook pb = new PrintPassBook(passbook,printer);
		try {
			pb.print();
		} catch (PrinterException e) {
			log.error("Passbook printing Error passbook = {} , printer = {}",passbook,printer,e);
			throw new PrinterException();
		}
		return new PassbookData();
	}
}
