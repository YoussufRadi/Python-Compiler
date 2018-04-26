package Compilers;
import java_cup.runtime.Symbol;
import java.util.Stack;


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
	static Stack<Symbol> dedents = new Stack<Symbol>();
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
		93
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
		/* 27 */ YY_START,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
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
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
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
		/* 135 */ YY_NOT_ACCEPT,
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
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"10:8,1,8,2,10,1:2,10:18,4,10,11,9,10:3,38,49,50,42,41,52,37,35,43,23:10,55," +
"51,45,44,46,10:2,22:4,36,33,22:13,31,22:6,53,10,54,10,21,10,30,39,27,5,6,7," +
"34,13,14,22,40,15,18,28,16,19,22,17,24,20,29,22,12,26,25,22,47,32,48,10:2,3" +
",0")[0];

	private int yy_rmap[] = unpackFromString(1,162,
"0,1,2,1,3,4,1,5,1,6,1:5,7,1:8,8,1:3,9,10:3,1:2,10,11,10:2,12,13,10:12,14,15" +
",16,17,1,18,1:2,19,20,21,22,23,24,25,10,26,21,27,28,29,30,31,32,33,26,34,35" +
",36,37,38,39,40,41,42,43,44,16,45,18,46,47,48,49,50,51,52,53,54,55,56,57,58" +
",59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83" +
",84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,10,103,104,105" +
",106,107,108,109,110,111,112,113,114")[0];

	private int yy_nxt[][] = unpackFromString(115,56,
"1,2,3,4,2,5,132,144,6,7,8,55,148,149,53,150,61,151,149,152,153,62,149,9,149" +
",154,149,155,156,149,157,158,65,159,160,10,149,11,68,161,149,12,13,14,15,16" +
",17,18,19,20,21,22,23,24,25,26,-1:57,2,-1:2,2,-1:52,52,27,-1,4,-1:3,4,-1:52" +
",149,64,149,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:16,7,-" +
"1:2,7:52,-1:23,9,-1:11,71,-1:64,32,-1:65,33,-1:12,79,-1:49,149:3,-1:4,149:9" +
",60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,35:3,-1:4,35:9,-1,35:10,-" +
"1,35:2,-1,35,-1:2,35:2,-1:36,38,-1:57,39,-1:12,85,-1:20,52,27,-1,52,-1:3,52" +
",-1:52,149:2,29,-1:4,149:6,84,149:2,60,149,67:2,149:3,30,149:3,-1,149:2,-1," +
"149,-1:2,149:2,-1:16,79:2,-1,79:7,54,79:44,-1,63:2,-1,63:7,28,63:44,-1:23,5" +
"7,-1:37,35:3,-1:4,35:9,-1,35,-1,35:8,-1,35:2,-1,35,-1:2,35:2,-1:20,149:3,-1" +
":4,149:5,31,149:3,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,66:3,-1:" +
"4,66:9,69,66,-1,66:8,-1,66:2,-1,66,-1:2,66:2,-1:16,63:2,-1,63:7,56,63:44,-1" +
":5,149:2,34,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:32,73," +
"-1:12,75,-1:30,66:3,-1:4,66:9,38,66:10,-1,66:2,-1,66,-1:2,66:2,-1:16,77:2,-" +
"1,77:34,56,77:17,-1:5,149:3,-1:4,149:2,99,149:6,60,149,67,100,149:7,-1,149:" +
"2,-1,149,-1:2,149:2,-1:38,39,-1:37,149:3,-1:4,149:9,60,149,67,149:3,146,149" +
":4,-1,149:2,-1,149,-1:2,149:2,-1:44,81,-1:31,149:3,-1:4,149:9,60,149,67,149" +
":4,138,149:3,-1,149:2,-1,149,-1:2,149:2,-1:30,83,-1:45,149:3,-1:4,149:5,36," +
"149:3,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:4,101" +
",149:4,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:16,79:2,-1,79:7,135,79" +
":44,-1:5,149:3,-1:4,149:3,136,149:5,60,149,67,149:8,-1,149:2,-1,149,-1:2,14" +
"9:2,-1:21,58,-1:54,149:3,-1:4,149:2,102,149:6,60,149,67,149:8,-1,149:2,-1,1" +
"49,-1:2,149:2,-1:39,87,-1:36,149:3,-1:4,149:7,103,149,60,149,67,149:8,-1,14" +
"9:2,-1,149,-1:2,149:2,-1:38,57,-1:13,91,-1:3,91,-1:19,149:3,-1:4,149:6,104," +
"149:2,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:21,59,-1:54,149:3,-1:4," +
"149:8,139,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9" +
",60,149,67,106,149:7,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9,60,1" +
"49,67,149,37,149:3,107,149:2,-1,149:2,-1,149,-1:2,149:2,-1:15,1,-1:2,1,-1:5" +
"7,149,140,149,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,1" +
"49:3,-1:4,149:9,60,149,67,149:6,108,149,-1,149:2,-1,149,-1:2,149:2,-1:20,14" +
"9:3,-1:4,149:8,40,60,149,67,149:4,110,149:3,-1,149:2,-1,149,-1:2,149:2,-1:2" +
"0,41,149:2,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:" +
"3,-1:4,149:9,60,149,67,149:5,107,149:2,-1,149:2,-1,149,-1:2,149:2,-1:20,149" +
":2,67,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149,42,14" +
"9,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,14" +
"9:6,43,149:2,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,14" +
"9:3,114,149:5,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,1" +
"49:4,115,149:4,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4," +
"149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,116,149,-1:20,149:3,-1:4,149:9,6" +
"0,149,67,149:8,-1,149,117,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9,60,149,6" +
"7:2,149:7,-1,149:2,-1,149,-1:2,149:2,-1:20,149,44,149,-1:4,149:9,60,149,67," +
"149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9,60,149,67,119,149:7" +
",-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:8,120,60,149,67,149:8,-1,1" +
"49:2,-1,149,-1:2,149:2,-1:20,149,67,149,-1:4,149:9,60,149,67,149:8,-1,149:2" +
",-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9,60,149,67,149:6,121,149,-1,149:2," +
"-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:7,122,149,60,149,67,149:8,-1,149:2,-" +
"1,149,-1:2,149:2,-1:20,149,45,149,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,14" +
"9,-1:2,149:2,-1:20,149,46,149,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1" +
":2,149:2,-1:20,149:3,-1:4,149:5,124,149:3,60,149,67,149:8,-1,149:2,-1,149,-" +
"1:2,149:2,-1:20,125,149:2,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,1" +
"49:2,-1:20,149,47,149,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2" +
",-1:20,67,149:2,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20" +
",149:3,-1:4,149:9,60,149,67,48,149:7,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3" +
",-1:4,149:2,127,149:6,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:" +
"3,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,149,67,-1:20,149:3,-1:4,1" +
"49:8,49,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:3,1" +
"29,149:5,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:8," +
"50,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9,60,149" +
",67,149:6,67,149,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9,60,149,6" +
"7,149:4,67,149:3,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9,60,149,6" +
"7,149:4,130,149:3,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:3,67,149:" +
"5,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9,60,149," +
"67,149,67,149:6,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9,60,149,67" +
",149:5,131,149:2,-1,149:2,-1,149,-1:2,149:2,-1:20,149,51,149,-1:4,149:9,60," +
"149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:3,70,149:5,60," +
"149,67,149:2,72,149:5,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:9,60," +
"149,67,149:4,105,149:3,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3,-1:4,149:4,14" +
"1,149:4,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:16,79:2,-1,79:7,89,79" +
":44,-1:5,149:3,-1:4,149:9,60,149,67,113,149:7,-1,149:2,-1,149,-1:2,149:2,-1" +
":20,149,111,149,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20" +
",149:3,-1:4,149:9,60,149,67,149:6,147,149,-1,149:2,-1,149,-1:2,149:2,-1:20," +
"149:3,-1:4,149:9,60,149,67,149:5,143,149:2,-1,149:2,-1,149,-1:2,149:2,-1:20" +
",149:3,-1:4,149:3,118,149:5,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:2" +
"0,149:3,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,142,149,-1:20,149:3" +
",-1:4,149:9,60,149,67,149:6,128,149,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3," +
"-1:4,149:5,126,149:3,60,149,67,149:8,-1,149:2,-1,149,-1:2,149:2,-1:20,149:3" +
",-1:4,149:2,74,149,76,78,149:3,60,149,67,149:6,80,149,-1,149:2,-1,149,-1:2," +
"149:2,-1:20,149:3,-1:4,149:9,60,149,67,149:4,109,149:3,-1,149:2,-1,149,-1:2" +
",149:2,-1:20,149,112,149,-1:4,149:9,60,149,67,149:8,-1,149:2,-1,149,-1:2,14" +
"9:2,-1:20,149:3,-1:4,149:3,123,149:5,60,149,67,149:8,-1,149:2,-1,149,-1:2,1" +
"49:2,-1:20,149:3,-1:4,149,82,149:7,60,149,67,149:8,-1,149:2,-1,149,-1:2,149" +
":2,-1:20,149:3,-1:4,149:9,60,149,67,149:6,86,149,-1,149:2,-1,149,-1:2,149:2" +
",-1:20,149,88,149,-1:4,149:9,60,149,67,149:6,133,149,-1,149:2,-1,149,-1:2,1" +
"49:2,-1:20,149:3,-1:4,149:9,60,149,67,149:6,90,149,-1,149:2,-1,149,-1:2,149" +
":2,-1:20,149:3,-1:4,149:5,92,149:3,60,149,67,149:8,-1,149:2,-1,149,-1:2,149" +
":2,-1:20,149:3,-1:4,149:2,94,149:6,60,149,67,149:8,-1,149:2,-1,149,-1:2,149" +
":2,-1:20,149:3,-1:4,149:3,95,145,149:4,60,149,67,149:8,-1,149:2,-1,149,-1:2" +
",149:2,-1:20,149:3,-1:4,149:4,96,149:4,60,149,67,149:8,-1,149:2,-1,149,-1:2" +
",149:2,-1:20,149:3,-1:4,149:9,60,149,67,149:4,97,149:3,-1,149:2,-1,149,-1:2" +
",149:2,-1:20,149:3,-1:4,149:5,98,149:3,60,149,67,149:8,-1,149:2,-1,149,-1:2" +
",149:2,-1:20,149:3,-1:4,149:9,60,149,67,149:6,80,149,-1,149:2,-1,149,-1:2,1" +
"49:2,-1:20,149:3,-1:4,149:3,134,149:5,60,149,67,149:8,-1,149:2,-1,149,-1:2," +
"149:2,-1:20,149:3,-1:4,149:5,137,149:3,60,149,67,149:8,-1,149:2,-1,149,-1:2" +
",149:2,-1:15");

	public Symbol next_token ()
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
          return new Symbol(sym.DED, pop + "");
      } 
      return null;
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
						{ }
					case -3:
						break;
					case 3:
						{
isIndent = false;
newIndex = yylength();
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
    System.out.println("Indent");
     return new Symbol(sym.IND, tmp+"");
  }
  else if (count < indentCount.peek()) { 
      if (indentCount.contains(count)) {
              int pop = indentCount.pop();
              if ((yylength() + index) < yy_buffer_index)
              yy_buffer_index -= yylength() + index;
              yybegin(YYINITIAL);
              System.out.println("DEDENT");
             return new Symbol(sym.DED,pop + "");
      }
      else {
              System.out.println("EROROROROR");
          return new Symbol(sym.ERROR, "Unexpected indentation");
      }
  }
}
					case -5:
						break;
					case 5:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -6:
						break;
					case 6:
						{
  System.out.println("FIXED INDENT");
  return new Symbol(sym.IND, 33); 
}
					case -7:
						break;
					case 7:
						{ }
					case -8:
						break;
					case 8:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.UD, yytext()); 
}
					case -9:
						break;
					case 9:
						{
  System.out.println("INTEGER" + yytext());
  return new Symbol(sym.INT, yytext()); 
}
					case -10:
						break;
					case 10:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.DO, yytext()); 
}
					case -11:
						break;
					case 11:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.MO, yytext()); 
}
					case -12:
						break;
					case 12:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.PO, yytext()); 
}
					case -13:
						break;
					case 13:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.MB, yytext()); 
}
					case -14:
						break;
					case 14:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.DB, yytext()); 
}
					case -15:
						break;
					case 15:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.AO, yytext()); 
}
					case -16:
						break;
					case 16:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.LT, yytext()); 
}
					case -17:
						break;
					case 17:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.GT, yytext()); 
}
					case -18:
						break;
					case 18:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.LC, yytext()); 
}
					case -19:
						break;
					case 19:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.RC, yytext()); 
}
					case -20:
						break;
					case 20:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.LB, yytext()); 
}
					case -21:
						break;
					case 21:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.RB, yytext()); 
}
					case -22:
						break;
					case 22:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.SM, yytext()); 
}
					case -23:
						break;
					case 23:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.FA, yytext()); 
}
					case -24:
						break;
					case 24:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.LS, yytext()); 
}
					case -25:
						break;
					case 25:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.RS, yytext()); 
}
					case -26:
						break;
					case 26:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.SC, yytext()); 
}
					case -27:
						break;
					case 27:
						{
int tmp = index++;
index = yylength() + newIndex;
newIndex = index - tmp;
index = yylength() + newIndex + 1;
}
					case -28:
						break;
					case 28:
						{ 
  System.out.println("STRING" + yytext());
  return new Symbol(sym.ST, yytext()); 
}
					case -29:
						break;
					case 29:
						{
  System.out.println("IF" + yytext());
  return new Symbol(sym.IFS, yytext());
}
					case -30:
						break;
					case 30:
						{
  System.out.println("IN" + yytext());
  return new Symbol(sym.IN, yytext()); 
}
					case -31:
						break;
					case 31:
						{
  System.out.println("OR" + yytext());
  return new Symbol(sym.OR, yytext()); 
}
					case -32:
						break;
					case 32:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.EQ, yytext()); 
}
					case -33:
						break;
					case 33:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.AA, yytext()); 
}
					case -34:
						break;
					case 34:
						{
 return new Symbol(sym.DF, yytext()); }
					case -35:
						break;
					case 35:
						{
  System.out.println("FUNCTION" + yytext());
  return new Symbol(sym.FC, yytext()); 
}
					case -36:
						break;
					case 36:
						{
  System.out.println("FOR" + yytext());
  return new Symbol(sym.FOR, yytext()); 
}
					case -37:
						break;
					case 37:
						{
  System.out.println("TRY" + yytext());
  return new Symbol(sym.TY, yytext());
}
					case -38:
						break;
					case 38:
						{
  System.out.println("PACKAGE" + yytext());
  return new Symbol(sym.PK, yytext()); 
}
					case -39:
						break;
					case 39:
						{
  System.out.println("NUMBER" + yytext());
  return new Symbol(sym.DL, yytext()); 
}
					case -40:
						break;
					case 40:
						{
  System.out.println("NOT" + yytext());
  return new Symbol(sym.NT, yytext()); 
}
					case -41:
						break;
					case 41:
						{
  System.out.println("AND" + yytext());
  return new Symbol(sym.AD, yytext()); 
}
					case -42:
						break;
					case 42:
						{
  System.out.println("ELSE" + yytext());
  return new Symbol(sym.EI, yytext()); 
}
					case -43:
						break;
					case 43:
						{
  System.out.println("FROM" + yytext());
  return new Symbol(sym.FR, yytext()); 
}
					case -44:
						break;
					case 44:
						{
  System.out.println("TRUE" + yytext());
  return new Symbol(sym.TR, yytext()); 
}
					case -45:
						break;
					case 45:
						{
  System.out.println("FALSE" + yytext());
  return new Symbol(sym.FL, yytext()); 
}
					case -46:
						break;
					case 46:
						{
  System.out.println("WHILE" + yytext());
  return new Symbol(sym.WH, yytext()); 
}
					case -47:
						break;
					case 47:
						{
  System.out.println("RANGE" + yytext());
  return new Symbol(sym.RG, yytext()); 
}
					case -48:
						break;
					case 48:
						{
  System.out.println("CLASS" + yytext());
  return new Symbol(sym.CL, yytext()); 
}
					case -49:
						break;
					case 49:
						{
  System.out.println("EXCEPT" + yytext());
  return new Symbol(sym.EX, yytext()); 
}
					case -50:
						break;
					case 50:
						{
  System.out.println("IMPORT" + yytext());
  return new Symbol(sym.IM, yytext()); 
}
					case -51:
						break;
					case 51:
						{
  System.out.println("CONTINUE" + yytext());
  return new Symbol(sym.CN, yytext()); 
}
					case -52:
						break;
					case 53:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -53:
						break;
					case 54:
						{ }
					case -54:
						break;
					case 55:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.UD, yytext()); 
}
					case -55:
						break;
					case 56:
						{ 
  System.out.println("STRING" + yytext());
  return new Symbol(sym.ST, yytext()); 
}
					case -56:
						break;
					case 57:
						{
  System.out.println("NUMBER" + yytext());
  return new Symbol(sym.DL, yytext()); 
}
					case -57:
						break;
					case 58:
						{
  System.out.println("TRUE" + yytext());
  return new Symbol(sym.TR, yytext()); 
}
					case -58:
						break;
					case 59:
						{
  System.out.println("FALSE" + yytext());
  return new Symbol(sym.FL, yytext()); 
}
					case -59:
						break;
					case 61:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -60:
						break;
					case 62:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.UD, yytext()); 
}
					case -61:
						break;
					case 64:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -62:
						break;
					case 65:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.UD, yytext()); 
}
					case -63:
						break;
					case 67:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -64:
						break;
					case 68:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.UD, yytext()); 
}
					case -65:
						break;
					case 70:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -66:
						break;
					case 72:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -67:
						break;
					case 74:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -68:
						break;
					case 76:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -69:
						break;
					case 78:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -70:
						break;
					case 80:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -71:
						break;
					case 82:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -72:
						break;
					case 84:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -73:
						break;
					case 86:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -74:
						break;
					case 88:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -75:
						break;
					case 90:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -76:
						break;
					case 92:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -77:
						break;
					case 94:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -78:
						break;
					case 95:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -79:
						break;
					case 96:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -80:
						break;
					case 97:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -81:
						break;
					case 98:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -82:
						break;
					case 99:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -83:
						break;
					case 100:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -84:
						break;
					case 101:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -85:
						break;
					case 102:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -86:
						break;
					case 103:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -87:
						break;
					case 104:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -88:
						break;
					case 105:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -89:
						break;
					case 106:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -90:
						break;
					case 107:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -91:
						break;
					case 108:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -92:
						break;
					case 109:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -93:
						break;
					case 110:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -94:
						break;
					case 111:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -95:
						break;
					case 112:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -96:
						break;
					case 113:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -97:
						break;
					case 114:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -98:
						break;
					case 115:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -99:
						break;
					case 116:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -100:
						break;
					case 117:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -101:
						break;
					case 118:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -102:
						break;
					case 119:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -103:
						break;
					case 120:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -104:
						break;
					case 121:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -105:
						break;
					case 122:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -106:
						break;
					case 123:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -107:
						break;
					case 124:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -108:
						break;
					case 125:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -109:
						break;
					case 126:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -110:
						break;
					case 127:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -111:
						break;
					case 128:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -112:
						break;
					case 129:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -113:
						break;
					case 130:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -114:
						break;
					case 131:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -115:
						break;
					case 132:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -116:
						break;
					case 133:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -117:
						break;
					case 134:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -118:
						break;
					case 136:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -119:
						break;
					case 137:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -120:
						break;
					case 138:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -121:
						break;
					case 139:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -122:
						break;
					case 140:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -123:
						break;
					case 141:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -124:
						break;
					case 142:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -125:
						break;
					case 143:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -126:
						break;
					case 144:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -127:
						break;
					case 145:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -128:
						break;
					case 146:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -129:
						break;
					case 147:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -130:
						break;
					case 148:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -131:
						break;
					case 149:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -132:
						break;
					case 150:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -133:
						break;
					case 151:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -134:
						break;
					case 152:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -135:
						break;
					case 153:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -136:
						break;
					case 154:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -137:
						break;
					case 155:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -138:
						break;
					case 156:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -139:
						break;
					case 157:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -140:
						break;
					case 158:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -141:
						break;
					case 159:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -142:
						break;
					case 160:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -143:
						break;
					case 161:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -144:
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
