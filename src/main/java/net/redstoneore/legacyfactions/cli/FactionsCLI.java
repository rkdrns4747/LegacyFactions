package net.redstoneore.legacyfactions.cli;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import net.redstoneore.legacyfactions.cli.doc.DocDescription;
import net.redstoneore.legacyfactions.cli.doc.DocSection;
import net.redstoneore.legacyfactions.entity.Conf;
import picocli.CommandLine;
import picocli.CommandLine.Option;


public class FactionsCLI {

	// -------------------------------------------------- //
	// MAIN
	// -------------------------------------------------- //
	
	private static FactionsCLI instance = null;
	public static void main(String[] args) {
		instance = CommandLine.populateCommand(new FactionsCLI(), args);
		instance.exec();
	}
	
	// -------------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------------- //

	private FactionsCLI() { }
	
	// -------------------------------------------------- //
	// FIELDS
	// -------------------------------------------------- //
	
    @Option(names = { "-v", "--verbose" }, description = "Be verbose.")
    private boolean verbose = false;
    
    @Option(names = { "-createmanual", "--createmanual" }, description = "Create config manual pages.")
    private boolean generareHelp = false;
    
    @Option(names = { "-dir", "--dir" }, description = "Create config manual pages.")
    private String saveDirectory = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();

	// -------------------------------------------------- //
	// METHODS
	// -------------------------------------------------- //
	
    public void exec() {
		if (this.generareHelp) {
			this.actionCreateManual();
			return;
		}
		
		CommandLine.usage(instance, System.out);
    }
    
    public void actionCreateManual() {
    	// Ensure the directory ends with a slash
    	if (!this.saveDirectory.endsWith("/")) {
    		this.saveDirectory += "/";
    	}
    	
    	System.out.println("Creating Manual... saving into directory '" + this.saveDirectory + "' ");
    	
    	String tableOfContents = "# Table of Contents\r\n";
    	String result = "";
    	
    	System.out.println("Reading Conf.class Fields");
    
    	
    	for (Field field : Conf.class.getDeclaredFields()) {
    		if (this.verbose) {
    			System.out.println("Found field " + field.getName());
    		}

			String newSection = null;
			String fieldTitle = null;
			String fieldDescription = null;

    		for (Annotation annotation : field.getAnnotations()) {
    			if (this.verbose) {
        			System.out.println("Found annotation " + annotation.toString());    				
    			}

    			if (annotation.annotationType() == DocSection.class) {
    				DocSection desc = field.getAnnotation(DocSection.class);
    				newSection = desc.name();
    			}
    			
    			if (annotation.annotationType() == DocDescription.class) {
    				DocDescription desc = field.getAnnotation(DocDescription.class);
    				fieldTitle = desc.title();
    				fieldDescription = desc.description();
    			}

    		}
    		
    		if (newSection != null) {
    			if (this.verbose) {
                	System.out.println("Adding new section: " + newSection);    				
    			}
            	result += "# " + newSection + "\r\n";
    			tableOfContents += "- " + newSection + "\r\n";
    		}
    		
    		if (fieldTitle != null) {
    			if (this.verbose) {
                	System.out.println("Conf field added: " + field.getName());    				
    			}

    			result += "## " + field.getName() + ": " + fieldTitle + "\r\n" + fieldDescription + "\r\n\r\n";
    		}
    	}
    	
    	String finalResult = "# LegacyFactions\r\n" + tableOfContents+"\r\n\r\n" + result;
    	
    	Charset charset = Charset.forName("US-ASCII");
    	try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.saveDirectory, "docs.md"))) {
    	    writer.write(finalResult, 0, finalResult.length());
    	} catch (IOException x) {
    	    System.err.format("IOException: %s%n", x);
    	}
    	
    	System.out.println("Saved as docs.md");
    	
    }
	
}
