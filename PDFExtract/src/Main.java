import pdfextract.Common;
import pdfextract.PDFExtract;

/**
 * @author MickeyVI
 */
public class Main {
	public static void main(String[] args) {
		try {

			Common common = new Common();

			if (args == null || args.length == 0 || args[0].equals("--help")) {

				/**
				 * Print out help
				 */
				common.printHelp();
				System.exit(0);

			}

			String input = "", output = "", batchfile = "", options = "", logpath = "", rulepath = "", threadcount = "",
					language = "", debug = "", verbose = "";
			String key = "";

			/**
			 * Get command-line arguments
			 */
			for (String parm : args) {

				if (parm.startsWith("-")) {
					key = parm.substring(1);
					if (key.equals("D")) {
						debug = "1";
						key = "";
					} else if (key.equals("v")) {
						verbose = "1";
						key = "";
					}
				} else {
					if (key.equals("I")) {
						input = parm;
					} else if (key.equals("O")) {
						output = parm;
					} else if (key.equals("B")) {
						batchfile = parm;
					} else if (key.equals("L")) {
						logpath = parm;
					} else if (key.equals("R")) {
						rulepath = parm;
					} else if (key.equals("T")) {
						threadcount = parm;
					} else if (key.equals("LANG")) {
						language = parm;
					} else if (key.equals("o")) {
						options = parm;
					}
					key = "";
				}
			}

			common.setVerbose(common.getInt(verbose));
			if (!common.IsEmpty(input) && !common.IsEmpty(output)) {
				/**
				 * Call function to extract single PDF file
				 */
				try {
					PDFExtract oExtractor = new PDFExtract(logpath, common.getInt(verbose));
					oExtractor.Extract(input, output, rulepath, language, options, common.getInt(debug));
				} catch (Exception e) {
					common.print("File: " + input + ", " + e.getMessage());
				}

			} else if (!common.IsEmpty(batchfile)) {
				/**
				 * Call function to extract PDF with batch file
				 */
				try {
					PDFExtract oExtractor = new PDFExtract(logpath, common.getInt(verbose));
					oExtractor.Extract(batchfile, rulepath, common.getInt(threadcount), language, options,
							common.getInt(debug));
				} catch (Exception e) {
					common.print("File: " + batchfile + ", " + e.getMessage());
				}

			} else {
				common.print("Cannot start extract: Invalid parameters.");
				System.exit(0);
			}

		} catch (Exception ex) {
			System.out.println("Cannot start extract. Error=" + ex.getMessage());
			System.exit(0);
		} finally {
		}
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}