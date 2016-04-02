package driver;

import interoperability.asciiToText;
import interoperability.binaryToText;

import java.util.ArrayList;
import java.util.LinkedList;

import code_generator.codeGeneration;
import english_spanish.translate;
import english_spanish.windowsAzureTranslate;
import lexical.Lexer;
import lexical.Lexer.Token;
import preprocessing.Preprocess;
import semantic.Semantic;
import speech.SynthesizerRNB;
import speech.SynthesizerTest1;
import symboltable.SymbolTable;
import syntax.Parser;

public class driver {

	public static void main(String[] args) throws Exception {
		
		/*
		// TODO Auto-generated method stub
		Preprocess p = new Preprocess();
		Lexer l = new Lexer();
		Parser pr = new Parser();
		SymbolTable table= new SymbolTable();
		
		LinkedList<String> ast = new LinkedList();
		LinkedList<String> result = new LinkedList();
		
		ArrayList<Token> t = new ArrayList<Token>();
		
		Semantic sem = new Semantic();
		
		
		String filePath = p.processText("./Lyrics/unbreak-my-heart.txt");
		t = l.dealFile(filePath);
		ast = pr.parse(t);
		
		result = sem.semanticAnalysis(ast);
		
		
		
		//System.out.println("Huh" +ast.toString());
		//System.out.println(result.toString());
		
		for(String node: result)
		{
			System.out.println(node.toString());
			
		}
		
		//p.parseText("./Lyrics/break-every-chain.txt");
		//l.dealFile("./Gospel/break-every-chain-Processed.txt");
		//p.parseText("./Lyrics/break-every-chain2.txt");
		//l.dealFile("./Gospel/break-every-chain2-Processed.txt");
		table.createTable("./Lyrics/every-praise.txt");
		
		//table.createTable("./Lyrics/every-praise.txt");		
		
		
		
		codeGeneration c = new codeGeneration();
		
		c.bin(ast);//convert to binary 
		
		
		//SynthesizerTest1.playVerse();
		SynthesizerTest1.playRNB();
		
		
		
		translate z = new translate();
		String returned = "";
		
		for(String node: ast)
		{
			returned = z.translateText(node.toString(),false);
			System.out.println(returned.toString());
			
		}
		
		z.translateText("",true);
	
		
		
		try{
			String spanishTranslation = "";
			
			windowsAzureTranslate w = new windowsAzureTranslate();
			spanishTranslation = w.translateEnglishToSpanish("Unbreak My Heart");
			System.out.println(spanishTranslation.toString());
		}catch(Exception e)
		{
			translate z = new translate();
			String returned = "";
			
			try{
				returned = z.translateText("Unbreak My Heart",false);
				System.out.println(returned.toString());
			}catch(Exception ex)
			{
				System.out.println("Please ensure that you have internet connection");
			}
			z.translateText("",true);
		}
		*/
		
		//SynthesizerTest1.playVerse();
		//SynthesizerTest1.playRNB();
		//SynthesizerTest1.playRNB();
		
		//binaryToText zz = new binaryToText();
		
		//zz.processBinary("./Lyrics/every-praise-Ascii.txt");
		
		/*translate z = new translate();
		z.translateText("./Lyrics/every-praise.txt");
		
		String source_filepath = "./Lyrics/every-praise.txt";

		CompressFileGzip gZipFile = new CompressFileGzip();
		String t = gZipFile.gzipFile(source_filepath);
		
		decompress g = new decompress();

		g.gUnzipFile(t);*/
		
		asciiToText t = new asciiToText();
		
		String file = t.processFile("./Gospel/every-praise-Ascii");
		System.out.println(file);
		
	}
}
  