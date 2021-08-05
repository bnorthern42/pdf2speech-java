package net.pdf2speech;


// Java code to convert text to speech



import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

//pdf to text: https://stackoverflow.com/questions/18098400/how-to-get-raw-text-from-pdf-file-using-java
//text to speech: https://www.geeksforgeeks.org/converting-text-speech-java/


public class main {
    public static void main(String[] args) throws IOException {




        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter path to PDF");

        //REPLACE WITH YOUR PATH to file
        String filePath = ("drive:\\dir\\anotherDIR\\pdf_file.pdf");// =scan.next();
        File f = new File(filePath);
        String parsedText;
       PDFManager  pdfManager = new PDFManager();

       pdfManager.setFilePath(filePath);





        try {
            parsedText = pdfManager.toText();
            System.out.println(parsedText);
            // Set property as Kevin Dictionary
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                            + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            Synthesizer synthesizer
                    = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();

            // Speaks the given text
            // until the queue is empty.
            synthesizer.speakPlainText(
                    parsedText, null);
            synthesizer.waitEngineState(
                    Synthesizer.QUEUE_EMPTY);

            // Deallocate the Synthesizer.
            synthesizer.deallocate();
        }

        catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, e);
        }

        System.out.println("DONE");
    }
}
