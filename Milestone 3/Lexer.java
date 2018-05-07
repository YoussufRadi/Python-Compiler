import java_cup.runtime.Symbol;
import java.util.*;
import java.io.*;


class Lexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

	//initialize  variables to be used by class
	static Stack<Integer> indentCount = new Stack<Integer>();
	static int count = 0;
	static String lex = "";
	static boolean isIndent = false;
	int index = 0;
	int newIndex = 0;
	@Override
	public Symbol next_token() throws Exception {
		// TODO Auto-generated method stub
		Symbol next=null;
		try
		{
		 next= next_Symbol();
		}
		catch(Exception e)
		{
		}
		return next;
	}
	public static void main(String[] args) throws FileNotFoundException {
		String inFile = "ms3.py";
		String outFile = "Sample.out";
		if (args.length > 1) {
			inFile = args[0];
			outFile = args[1];
		}
		Lexer lexer = new Lexer(new BufferedReader(new FileReader(inFile)));
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
			Symbol t;
			while ((t = lexer.next_Symbol()) != null) {
				System.out.println(t.toString());
				writer.write(t.toString());
				writer.newLine();
			}
			writer.close(); 
			System.out.println("Done tokenizing file: " + inFile);
			System.out.println("Output written in file: " + outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Lexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Lexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

    indentCount.push(0);
//Add code to be executed on initialization of the lexer
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int SPACE = 1;
	private final int yy_state_dtrans[] = {
		0,
		112
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_START,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_START,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"9:8,1,4,2,9,1:2,9:18,40,39,8,57,9,23,24,42,12,13,21,19,15,20,10,22,41:10,11" +
",18,28,14,27,9,58,59:5,54,59:13,53,59:6,16,44,17,26,59,9,38,51,29,5,6,7,59," +
"48,33,59,52,35,49,31,30,50,59,37,36,32,34,59,47,56,55,59,45,25,46,43,9,3,0")[0];

	private int yy_rmap[] = unpackFromString(1,153,
"0,1:3,2,3,4,1,5,1:3,6,1:4,7,8,9,10,11,12,13,14,15,16,1,17,1:5,18,5,1:4,19,1" +
":7,20,21:3,22,21,1:2,21:2,1:4,23,21:10,1:3,21:3,24,25,26,27,1,28,29,30,31,3" +
"2,33,34,35,5,21,16,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54" +
",55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79" +
",80,81,82,83,84,85,86,87,21,88,89,90,91,92")[0];

	private int yy_nxt[][] = unpackFromString(93,60,
"1,2,3,4,2,5,137,143,6,7,8,9,10,11,12,13,14,15,16,17,18,19,81,20,21,22,86,23" +
",24,144,80,145,146,85,147:3,148,88,89,2,25,26,27,28,29,30,149,147:3,150,147" +
",151,152,147:2,31,32,147,-1:61,79,33,-1,4,-1:35,4,-1:24,147,91,147,-1:21,14" +
"7:10,-1:2,93,-1:5,147:10,-1:2,147,-1,84:2,-1,84:4,34,84:51,-1:41,35,-1:32,3" +
"6,-1:59,37,-1:59,38,-1:59,39,-1:6,40,-1:52,42,-1:59,43,-1:59,44,-1:59,46,-1" +
":12,90,-1:46,47,-1:13,48,-1:41,92,-1:30,82,-1:19,94:2,-1,94:38,83,94:17,-1:" +
"44,55,-1:23,96,-1:65,58,-1:59,61,-1:50,147:3,-1:21,147:10,-1:2,93,-1:5,147:" +
"10,-1:2,147,-1:5,147:3,-1:21,147:10,-1,98,93,-1:5,147:10,-1:2,147,-1:5,147:" +
"3,-1:21,147:10,-1,102,93,-1:5,147:10,-1:2,147,-1,79,33,-1,79,-1:35,79,-1:24" +
",147:3,-1:21,147:8,49,147,-1:2,93,-1:5,147:10,-1:2,147,-1:14,41,-1:7,87,-1:" +
"78,82,-1:19,84:2,-1,84:4,83,84:51,-1:5,147:2,50,-1:21,147:2,51,147:4,52,147" +
":2,-1:2,93,-1:5,147:2,109,147:7,-1:2,147,-1:14,45,-1:59,59,-1:50,147:3,-1:2" +
"1,147:2,113,147:4,53,147:2,-1:2,93,-1:5,147:10,-1:2,147,-1:14,54,-1:59,60,-" +
"1:50,147:2,56,-1:21,147:10,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,14" +
"7:4,118,147:2,119,147:2,-1:2,93,-1:5,147:10,-1:2,147,-1,96:2,-1,96:4,100,96" +
":51,-1:5,147:3,-1:21,139,147:9,-1:2,93,-1:5,147:10,-1:2,147,-1:31,104,-1:33" +
",147:3,-1:21,147:8,57,147,-1:2,93,-1:5,147:10,-1:2,147,-1:8,106,-1:56,147:3" +
",-1:21,147,120,147:8,-1:2,93,-1:5,147:10,-1:2,147,-1:33,108,-1:31,147:3,-1:" +
"21,147:2,138,147:7,-1:2,93,-1:5,147:10,-1:2,147,-1:30,110,-1:34,147:3,-1:21" +
",147:3,62,147:6,-1:2,93,-1:5,147:10,-1:2,147,-1:8,73,-1:56,147:3,-1:21,147:" +
"10,-1:2,93,-1:5,147:8,63,147,-1:2,147,-1:31,74,-1:33,147:3,-1:21,147:10,-1:" +
"2,93,-1:5,147:3,121,147:6,-1:2,147,-1:32,75,-1:32,147:3,-1:21,147:3,140,147" +
":6,-1:2,93,-1:5,147:10,-1:2,147,1,-1:2,1,-1:61,64,147:2,-1:21,147:10,-1:2,9" +
"3,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147:4,141,147:5,-1:2,93,-1:5,147:10" +
",-1:2,147,-1:5,147,122,147,-1:21,147:10,-1:2,93,-1:5,147:10,-1:2,147,-1:5,1" +
"47:3,-1:21,147:5,123,147:4,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,14" +
"7:6,124,147:3,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:2,65,-1:21,147:10,-1:2," +
"93,-1:5,147:10,-1:2,147,-1:5,147,66,147,-1:21,147:10,-1:2,93,-1:5,147:10,-1" +
":2,147,-1:5,147:3,-1:21,147:10,-1:2,93,-1:5,147:2,67,147:7,-1:2,147,-1:5,14" +
"7:3,-1:21,147,127,147:8,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147:9" +
",129,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147,68,147,-1:21,147:10,-1:2,93,-1:5" +
",147:10,-1:2,147,-1:5,147:3,-1:21,147:7,130,147:2,-1:2,93,-1:5,147:10,-1:2," +
"147,-1:5,147:3,-1:21,147:10,-1:2,93,-1:5,147:3,131,147:6,-1:2,147,-1:5,147:" +
"3,-1:21,147:4,132,147:5,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147:8" +
",133,147,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147,69,147,-1:21,147:10,-1:2,93," +
"-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147:10,-1:2,93,-1:5,147:5,70,147:4,-1" +
":2,147,-1:5,147,71,147,-1:21,147:10,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3" +
",-1:21,147:3,72,147:6,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147:2,1" +
"35,147:7,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147:3,76,147:6,-1:2," +
"93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147:2,77,147:7,-1:2,93,-1:5,147:10" +
",-1:2,147,-1:5,147:3,-1:21,147:5,136,147:4,-1:2,93,-1:5,147:10,-1:2,147,-1:" +
"5,147,78,147,-1:21,147:10,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147" +
":6,95,147:3,-1:2,93,-1:5,147:9,97,-1:2,147,-1:5,147:3,-1:21,147:3,126,147:6" +
",-1:2,93,-1:5,147:10,-1:2,147,-1:5,147,125,147,-1:21,147:10,-1:2,93,-1:5,14" +
"7:10,-1:2,147,-1:5,147:3,-1:21,147:5,142,147:4,-1:2,93,-1:5,147:10,-1:2,147" +
",-1:5,147:3,-1:21,147:6,128,147:3,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-" +
"1:21,147:8,134,147,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147,99,147" +
":6,101,147,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147,103,147:8,-1:2" +
",93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147,105,147:8,-1:2,93,-1:5,147:10" +
",-1:2,147,-1:5,147:3,-1:21,147:8,107,147,-1:2,93,-1:5,147:10,-1:2,147,-1:5," +
"147,111,147,-1:21,147:10,-1:2,93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147:" +
"10,-1:2,93,-1:5,147,114,147:8,-1:2,147,-1:5,147:3,-1:21,147:8,115,147,-1:2," +
"93,-1:5,147:10,-1:2,147,-1:5,147:3,-1:21,147:8,116,147,-1:2,93,-1:5,147:10," +
"-1:2,147,-1:5,147:3,-1:21,147:9,117,-1:2,93,-1:5,147:10,-1:2,147");

	public java_cup.runtime.Symbol next_Symbol ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
  
      if (!indentCount.isEmpty() && indentCount.peek() != 0) {
          int pop = indentCount.pop();
          yybegin(YYINITIAL);
          return new Symbol(sym.DEDENT, pop + "");
      } 
      return new Symbol(sym.EOF, null);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{  }
					case -3:
						break;
					case 3:
						{ 
isIndent = false;
newIndex = yylength();
return new Symbol(sym.NEWLINE, ""); 
}
					case -4:
						break;
					case 4:
						{
count = yytext().length();
 if (count > indentCount.peek()) {
     indentCount.push(count);
     int tmp  = count;
     count = 0; 
     return new Symbol(sym.INDENT, tmp+"");
  }
  else if (count < indentCount.peek()) { 
      if (indentCount.contains(count)) {
              int pop = indentCount.pop();
              if ((yylength() + index) <  yy_buffer_index)
              yy_buffer_index -= yylength() + index;
              yybegin(YYINITIAL);
             return new Symbol(sym.DEDENT,pop + "");   
             } 
      else {
          return new Symbol(sym.ERROR, "Unexpected indentation");
      }
  }
}
					case -5:
						break;
					case 5:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -6:
						break;
					case 6:
						{ 
 return new Symbol(sym.DOUBLEQOUTE, yytext()); }
					case -7:
						break;
					case 7:
						{
  return new Symbol(sym.ERROR, "Invalid input: " + yytext() + " at line " + yyline + 1);
}
					case -8:
						break;
					case 8:
						{ 
 return new Symbol(sym.DOT, yytext()); }
					case -9:
						break;
					case 9:
						{ 
 return new Symbol(sym.COLON, yytext()); }
					case -10:
						break;
					case 10:
						{ 
 return new Symbol(sym.OPARAN, yytext()); }
					case -11:
						break;
					case 11:
						{ 
 return new Symbol(sym.CPARAN, yytext()); }
					case -12:
						break;
					case 12:
						{ 
 return new Symbol(sym.EQUAL, yytext()); }
					case -13:
						break;
					case 13:
						{ 
 return new Symbol(sym.COMMA, yytext()); }
					case -14:
						break;
					case 14:
						{ 
 return new Symbol(sym.OSQUARE, yytext()); }
					case -15:
						break;
					case 15:
						{ 
 return new Symbol(sym.CSQAURE, yytext()); }
					case -16:
						break;
					case 16:
						{ 
 return new Symbol(sym.SEMICOLON, yytext()); }
					case -17:
						break;
					case 17:
						{ 
 return new Symbol(sym.PLUS, yytext()); }
					case -18:
						break;
					case 18:
						{ 
 return new Symbol(sym.MINUS, yytext()); }
					case -19:
						break;
					case 19:
						{ 
 return new Symbol(sym.ASTRICK, yytext()); }
					case -20:
						break;
					case 20:
						{ 
 return new Symbol(sym.MOD, yytext()); }
					case -21:
						break;
					case 21:
						{ 
 return new Symbol(sym.AMBER, yytext()); }
					case -22:
						break;
					case 22:
						{ 
 return new Symbol(sym.XOR, yytext()); }
					case -23:
						break;
					case 23:
						{ 
 return new Symbol(sym.GROP, yytext()); }
					case -24:
						break;
					case 24:
						{ 
 return new Symbol(sym.LESSOP, yytext()); }
					case -25:
						break;
					case 25:
						{ 
 return new Symbol(sym.NUMBER, yytext()); }
					case -26:
						break;
					case 26:
						{ 
 return new Symbol(sym.SINGLEQUOTE, yytext()); }
					case -27:
						break;
					case 27:
						{ 
 return new Symbol(sym.LOGNOT, yytext()); }
					case -28:
						break;
					case 28:
						{ 
 return new Symbol(sym.SLASH, yytext()); }
					case -29:
						break;
					case 29:
						{ 
 return new Symbol(sym.OCURLY, yytext()); }
					case -30:
						break;
					case 30:
						{ 
 return new Symbol(sym.CCURLY, yytext()); }
					case -31:
						break;
					case 31:
						{ 
 return new Symbol(sym.HASH, yytext()); }
					case -32:
						break;
					case 32:
						{ 
 return new Symbol(sym.AT , yytext()); }
					case -33:
						break;
					case 33:
						{
int tmp = index++;
index = yylength() + newIndex;
newIndex = index - tmp;
index = yylength() + newIndex + 1;
}
					case -34:
						break;
					case 34:
						{ 
 return new Symbol(sym.STRING, yytext()); }
					case -35:
						break;
					case 35:
						{ 
 return new Symbol(sym.DOUBLE, yytext()); }
					case -36:
						break;
					case 36:
						{ 
 return new Symbol(sym.EQ, yytext()); }
					case -37:
						break;
					case 37:
						{ 
 return new Symbol(sym.PLUSEQ, yytext()); }
					case -38:
						break;
					case 38:
						{ 
 return new Symbol(sym.MINUSEQ, yytext()); }
					case -39:
						break;
					case 39:
						{ 
 return new Symbol(sym.ASTRICKEQ, yytext()); }
					case -40:
						break;
					case 40:
						{ 
 return new Symbol(sym.POW, yytext()); }
					case -41:
						break;
					case 41:
						{ 
 return new Symbol(sym.DIVEQ, yytext()); }
					case -42:
						break;
					case 42:
						{ 
 return new Symbol(sym.MODEQ, yytext()); }
					case -43:
						break;
					case 43:
						{ 
 return new Symbol(sym.ANDEQ, yytext()); }
					case -44:
						break;
					case 44:
						{ 
 return new Symbol(sym.OREQ, yytext()); }
					case -45:
						break;
					case 45:
						{ 
 return new Symbol(sym.NOTEQ, yytext()); }
					case -46:
						break;
					case 46:
						{ 
 return new Symbol(sym.GREQ, yytext()); }
					case -47:
						break;
					case 47:
						{ 
 return new Symbol(sym.LESSEQ, yytext()); }
					case -48:
						break;
					case 48:
						{ 
 return new Symbol(sym.RS, yytext()); }
					case -49:
						break;
					case 49:
						{ 
 return new Symbol(sym.OR, yytext()); }
					case -50:
						break;
					case 50:
						{ 
 return new Symbol(sym.IF, yytext()); }
					case -51:
						break;
					case 51:
						{ 
 return new Symbol(sym.IN, yytext()); }
					case -52:
						break;
					case 52:
						{ 
 return new Symbol(sym.IS, yytext()); }
					case -53:
						break;
					case 53:
						{ 
 return new Symbol(sym.AS, yytext()); }
					case -54:
						break;
					case 54:
						{ 
 return new Symbol(sym.NOTEQUAL, yytext()); }
					case -55:
						break;
					case 55:
						{ 
 return new Symbol(sym.DOUBLESLASH, yytext()); }
					case -56:
						break;
					case 56:
						{
 return new Symbol(sym.DEF, yytext()); }
					case -57:
						break;
					case 57:
						{ 
 return new Symbol(sym.FOR, yytext()); }
					case -58:
						break;
					case 58:
						{ 
 return new Symbol(sym.POWEQ, yytext()); }
					case -59:
						break;
					case 59:
						{ 
 return new Symbol(sym.INTDIVEQ, yytext()); }
					case -60:
						break;
					case 60:
						{ 
 return new Symbol(sym.RSEQ, yytext()); }
					case -61:
						break;
					case 61:
						{ 
 return new Symbol(sym.LSEQ, yytext()); }
					case -62:
						break;
					case 62:
						{ 
 return new Symbol(sym.NOT, yytext()); }
					case -63:
						break;
					case 63:
						{ 
 return new Symbol(sym.TRY, yytext()); }
					case -64:
						break;
					case 64:
						{ 
 return new Symbol(sym.AND, yytext()); }
					case -65:
						break;
					case 65:
						{ 
 return new Symbol(sym.ELIF, yytext()); }
					case -66:
						break;
					case 66:
						{ 
 return new Symbol(sym.ELSE, yytext()); }
					case -67:
						break;
					case 67:
						{ 
 return new Symbol(sym.FROM, yytext()); }
					case -68:
						break;
					case 68:
						{ 
 return new Symbol(sym.TRUE, yytext()); }
					case -69:
						break;
					case 69:
						{ 
 return new Symbol(sym.WHILE, yytext()); }
					case -70:
						break;
					case 70:
						{ 
 return new Symbol(sym.BREAK, yytext()); }
					case -71:
						break;
					case 71:
						{ 
 return new Symbol(sym.FALSE, yytext()); }
					case -72:
						break;
					case 72:
						{ 
 return new Symbol(sym.EXCEPT, yytext()); }
					case -73:
						break;
					case 73:
						{  }
					case -74:
						break;
					case 74:
						{ 
 return new Symbol(sym.NOTIN, yytext()); }
					case -75:
						break;
					case 75:
						{ 
 return new Symbol(sym.ISNOT , yytext()); }
					case -76:
						break;
					case 76:
						{ 
 return new Symbol(sym.IMPORT, yytext()); }
					case -77:
						break;
					case 77:
						{ 
 return new Symbol(sym.RETURN, yytext()); }
					case -78:
						break;
					case 78:
						{ 
 return new Symbol(sym.CONTINUE, yytext()); }
					case -79:
						break;
					case 80:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -80:
						break;
					case 81:
						{
  return new Symbol(sym.ERROR, "Invalid input: " + yytext() + " at line " + yyline + 1);
}
					case -81:
						break;
					case 82:
						{ 
 return new Symbol(sym.NUMBER, yytext()); }
					case -82:
						break;
					case 83:
						{ 
 return new Symbol(sym.STRING, yytext()); }
					case -83:
						break;
					case 85:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -84:
						break;
					case 86:
						{
  return new Symbol(sym.ERROR, "Invalid input: " + yytext() + " at line " + yyline + 1);
}
					case -85:
						break;
					case 88:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -86:
						break;
					case 89:
						{
  return new Symbol(sym.ERROR, "Invalid input: " + yytext() + " at line " + yyline + 1);
}
					case -87:
						break;
					case 91:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -88:
						break;
					case 93:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -89:
						break;
					case 95:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -90:
						break;
					case 97:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -91:
						break;
					case 99:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -92:
						break;
					case 101:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -93:
						break;
					case 103:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -94:
						break;
					case 105:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -95:
						break;
					case 107:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -96:
						break;
					case 109:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -97:
						break;
					case 111:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -98:
						break;
					case 113:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -99:
						break;
					case 114:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -100:
						break;
					case 115:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -101:
						break;
					case 116:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -102:
						break;
					case 117:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -103:
						break;
					case 118:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -104:
						break;
					case 119:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -105:
						break;
					case 120:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -106:
						break;
					case 121:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -107:
						break;
					case 122:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -108:
						break;
					case 123:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -109:
						break;
					case 124:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -110:
						break;
					case 125:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -111:
						break;
					case 126:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -112:
						break;
					case 127:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -113:
						break;
					case 128:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -114:
						break;
					case 129:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -115:
						break;
					case 130:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -116:
						break;
					case 131:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -117:
						break;
					case 132:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -118:
						break;
					case 133:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -119:
						break;
					case 134:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -120:
						break;
					case 135:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -121:
						break;
					case 136:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -122:
						break;
					case 137:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -123:
						break;
					case 138:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -124:
						break;
					case 139:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -125:
						break;
					case 140:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -126:
						break;
					case 141:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -127:
						break;
					case 142:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -128:
						break;
					case 143:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -129:
						break;
					case 144:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -130:
						break;
					case 145:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -131:
						break;
					case 146:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -132:
						break;
					case 147:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -133:
						break;
					case 148:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -134:
						break;
					case 149:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -135:
						break;
					case 150:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -136:
						break;
					case 151:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -137:
						break;
					case 152:
						{ 
 return new Symbol(sym.ID, yytext()); }
					case -138:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
