package kbtg.kbond.passbookprint.model.print;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.concurrent.atomic.AtomicReference;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintPassBook implements Printable {
	private static final Logger log = LoggerFactory.getLogger(PrintPassBook.class);
	
	public static final int DEFAULT_FONT_SIZE = 10;
	public static final String DEFAULT_FONT_NAME = "MS Sans Serif";
    
    private final AtomicReference<PrintService> ps = new AtomicReference<>(null);
    private final AtomicReference<String> jobName = new AtomicReference<>("Passbook Printing");
    private final AtomicReference<Paper> paper = new AtomicReference<>(null);
    private final AtomicReference<PaperFormat> paperSize = new AtomicReference<>(null);
    private final AtomicReference<Float> pixelsPerInch = new AtomicReference<>(null);
    private final AtomicReference<Integer> copies = new AtomicReference<>(null);
    private final AtomicReference<Boolean> logPostScriptFeatures = new AtomicReference<>(false);
    private final AtomicReference<String> header1 = new AtomicReference<>("");
    private final AtomicReference<String> header2 = new AtomicReference<>("");
    private final AtomicReference<String> branch = new AtomicReference<>("");
    private final AtomicReference<String> accountNo = new AtomicReference<>("");
    private final AtomicReference<String> holderName = new AtomicReference<>("");
    private final AtomicReference<String> holderAddress1 = new AtomicReference<>("");
    private final AtomicReference<String> holderAddress2 = new AtomicReference<>("");
    private final AtomicReference<String> holderAddress3 = new AtomicReference<>("");
    private boolean printCoverPage = true;
    private AtomicReference<String[]> lines = new AtomicReference<>(new String[24]);

	
    
    
	public PrintPassBook(PassBook passbook, PrintService printer) {
		if(isNull(passbook))
			return;
		ps.set(printer);
		accountNo.set(passbook.getAccountNo());
		branch.set(passbook.getBranch());
		copies.set(passbook.getCopies());
		header1.set(passbook.getHeader1());
		header2.set(passbook.getHeader2());
		holderAddress1.set(passbook.getHolderAddress1());
		holderAddress2.set(passbook.getHolderAddress2());
		holderAddress3.set(passbook.getHolderAddress3());
		holderName.set(passbook.getHolderName());
		printCoverPage = passbook.isPrintCoverPage();
		lines.set(passbook.getLines());
		
		PaperSize pz = passbook.getPaperSize();
		if(nonNull(pz)) {
			paperSize.set(PaperFormat.parseSize(pz.getWidth(), pz.getHeight()));
			paperSize.get().setAutoSize(pz.isAutoResize());
		}
		
		
		
		
	}
    
    
    public void print() throws PrinterException {
    	log.info("Start To Print");
        PrinterJob job = PrinterJob.getPrinterJob();

        HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();

        if (paperSize.get() != null) {
            attr.add(paperSize.get().getOrientationRequested());

            attr.add(new MediaPrintableArea(0f, 0f, paperSize.get().getAutoWidth(), paperSize.get().getAutoHeight(), paperSize.get().getUnits()));

        } else {
            attr.add(new MediaPrintableArea(0f, 0f, 5f, 7f, Size2DSyntax.INCH));
        }

        if (copies.get() != null) {
        	log.info("copy = {}",copies.get());
            attr.add(new Copies(copies.get().intValue()));
        }
    
        log.info("Printer service : {}",ps.get());
        job.setPrintService(ps.get());
        job.setPrintable(this);
        job.setJobName(jobName.get());
        job.print(attr);

    }
    
    private int printLine(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        
        final int TAB_LINE = 20;
        log.info("pageIndex = {}",pageIndex);
        if (graphics == null) {
            throw new PrinterException("No graphics specified");
        }
        if (pageFormat == null) {
            throw new PrinterException("No page format specified");
        }
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        if (paperSize.get() != null) {
            pageFormat.setOrientation(paperSize.get().getOrientation());
        }

        Font typeFont = new Font("Courier New", Font.PLAIN, 10);
        graphics.setFont(typeFont);
        graphics.setColor(Color.black);
        FontMetrics fm = graphics.getFontMetrics();

        graphics.setFont(typeFont);
        
        // header
        int yPosition = 102;
        for (int i = 0; i < 24; i++) {    	
            String item = lines.get()[i];
            log.info(item);

            if (item == null) {
                item = "";
            }
            if (i == 11) {

                yPosition += (4 * fm.getHeight()) - 6;
            }
            graphics.drawString(item, TAB_LINE, yPosition);
            yPosition += fm.getHeight();

        }
        log.info("PAGE_EXISTS = "+PAGE_EXISTS);
        return PAGE_EXISTS;

    }
    
    private int printImage(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        /* Graphics and pageFormat are required. Page index is zero-based */
        final int TAB1 = 40;
        final int MAX_PERLINE = 30;
        if (graphics == null) {
            throw new PrinterException("No graphics specified");
        }
        if (pageFormat == null) {
            throw new PrinterException("No page format specified");
        }
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        if (paperSize.get() != null) {
            pageFormat.setOrientation(paperSize.get().getOrientation());
        }

        Font typeFont = new Font(DEFAULT_FONT_NAME, Font.PLAIN, DEFAULT_FONT_SIZE);
        graphics.setFont(typeFont);
        graphics.setColor(Color.black);
        FontMetrics fm = graphics.getFontMetrics();

        graphics.setFont(typeFont);
        // header
        graphics.drawString(header1.get(), 10, fm.getHeight() + 5);
        graphics.drawString(header2.get(), 10, (fm.getHeight() * 2) + 5);

        // branch

        if (branch.get().length() <= 27) {

            graphics.drawString(branch.get(), 80, 285);
        } else {
            String branchL1 = branch.get().substring(0, 28);

            String branchL2 = branch.get().substring(28);

            graphics.drawString(branchL1, 80, 285);
            graphics.drawString(branchL2, 80, 285 + fm.getHeight());

        }

        // Name

        if (holderName.get().length() <= MAX_PERLINE) {

            graphics.drawString(holderName.get(), TAB1, 340);

        } else {

            String holderNameX = WordUtils.wrap(holderName.get(), MAX_PERLINE);
            String[] hName = holderNameX.split("\\r?\\n");

            for (int index = 0; index < hName.length; index++) {
                if (index == 0) {
                    graphics.drawString(hName[index].trim(), TAB1, 340);
                } else {
                    graphics.drawString(hName[index].trim(), TAB1, 340 + fm.getHeight() * index);
                }
            }
        }

        // account
        graphics.drawString(accountNo.get(), 230, 340);

        // Address
        graphics.drawString(holderAddress1.get(), TAB1, 405 + (fm.getHeight() * 1));
        graphics.drawString(holderAddress2.get(), TAB1, 405 + (fm.getHeight() * 2));
        graphics.drawString(holderAddress3.get(), TAB1, 405 + (fm.getHeight() * 3));
        return PAGE_EXISTS;
    }
    
    
    @Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		log.info("Begin Print Default");
        if (paper.get() != null) {
            pageFormat.setPaper(paper.get());
        }

        if (printCoverPage) {
        	log.info("Print Image");
            return printImage(graphics, pageFormat, pageIndex);
        } else {
        	log.info("Print Line");
            return printLine(graphics, pageFormat, pageIndex);

        }
	}



}
