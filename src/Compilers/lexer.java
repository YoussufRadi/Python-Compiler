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
		94
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
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NOT_ACCEPT,
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
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NOT_ACCEPT,
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
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"10:8,1,8,2,10,1:2,10:18,4,10,11,9,10:3,38,49,50,42,41,52,37,35,43,25:10,55," +
"51,45,44,46,10:2,24:4,36,33,24:13,31,24:6,53,10,54,10,23,10,30,39,28,5,6,7," +
"34,18,15,24,40,19,21,16,20,22,24,14,12,13,29,24,17,27,26,24,47,32,48,10:2,3" +
",0")[0];

	private int yy_rmap[] = unpackFromString(1,165,
"0,1,2,1,3,4,1,5,1,6,1:5,7,1:8,8,1:3,9,10,11,10,1:2,10,12,10:4,13,14,10:11,1" +
"5,16,17,18,1,19,1:2,20,21,22,23,24,25,26,10,27,22,28,29,30,31,32,33,34,27,3" +
"5,36,37,38,39,40,41,42,43,44,45,17,46,19,47,48,49,50,51,52,53,54,55,56,57,5" +
"8,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,8" +
"3,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,1" +
"06,107,108,10,109,110,111,112,113,114,115,116,117")[0];

	private int yy_nxt[][] = unpackFromString(118,56,
"1,2,3,4,2,5,135,147,6,7,8,56,150,151,152,54,153,154,155,156,62,155,157,63,1" +
"55,9,158,155,159,155,160,161,66,162,163,10,155,11,69,164,155,12,13,14,15,16" +
",17,18,19,20,21,22,23,24,25,26,-1:57,2,-1:2,2,-1:52,53,27,-1,4,-1:3,4,-1:52" +
",155,65,155,-1:4,155:11,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:16,7," +
"-1:2,7:52,-1:25,9,-1:9,72,-1:64,32,-1:65,33,-1:12,80,-1:49,155:3,-1:4,155:1" +
"1,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155,37,155:9," +
"61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,35:3,-1:4,35:11,-1,35:8,-1" +
",35:2,-1,35,-1:2,35:2,-1:38,40,-1:57,41,-1:10,86,-1:20,53,27,-1,53,-1:3,53," +
"-1:52,155:2,29,-1:4,68,155:3,30,155:4,89,155,61,155,68,155:6,-1,155:2,-1,15" +
"5,-1:2,155:2,-1:16,80:2,-1,80:7,55,80:44,-1,64:2,-1,64:7,28,64:44,-1:25,58," +
"-1:35,35:3,-1:4,35:11,-1,35,-1,35:6,-1,35:2,-1,35,-1:2,35:2,-1:20,155:3,-1:" +
"4,155:2,31,155:8,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,67:3,-1:4" +
",67:11,70,67,-1,67:6,-1,67:2,-1,67,-1:2,67:2,-1:16,64:2,-1,64:7,57,64:44,-1" +
":5,155:2,34,-1:4,155:11,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:29,74" +
",-1:15,76,-1:30,67:3,-1:4,67:11,40,67:8,-1,67:2,-1,67,-1:2,67:2,-1:16,78:2," +
"-1,78:34,57,78:17,-1:5,155:3,-1:4,101,155:2,102,155:7,61,155,68,155:6,-1,15" +
"5:2,-1,155,-1:2,155:2,-1:40,41,-1:35,155:3,-1:4,155:11,61,155,68,155:2,139," +
"155:3,-1,155:2,-1,155,-1:2,155:2,-1:44,82,-1:31,155:3,-1:4,155:8,103,155:2," +
"61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:34,84,-1:41,155:3,-1:4,155:4," +
"142,155:6,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:2" +
",36,155:8,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:16,80:2,-1,80:7,138" +
",80:44,-1:5,155:3,-1:4,155:7,140,155:3,61,155,68,155:6,-1,155:2,-1,155,-1:2" +
",155:2,-1:21,59,-1:54,155:3,-1:4,155:2,37,155:8,61,155,68,155:6,-1,155:2,-1" +
",155,-1:2,155:2,-1:27,88,-1:48,155:3,-1:4,155:11,61,155,68,38,155:2,104,155" +
":2,-1,155:2,-1,155,-1:2,155:2,-1:40,58,-1:11,92,-1:3,92,-1:19,155:3,-1:4,15" +
"5,105,155:9,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:21,60,-1:54,155:3" +
",-1:4,155:10,107,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:" +
"4,155,39,155:2,108,155:6,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,1" +
"55:3,-1:4,155:3,109,155:7,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:15," +
"1,-1:2,1,-1:57,155:3,-1:4,155:9,110,155,61,155,68,155:6,-1,155:2,-1,155,-1:" +
"2,155:2,-1:20,155:3,-1:4,111,155:10,61,155,68,155:6,-1,155:2,-1,155,-1:2,15" +
"5:2,-1:20,155,143,155,-1:4,155:11,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:" +
"2,-1:20,155:3,-1:4,155:11,61,155,68,155:4,112,155,-1,155:2,-1,155,-1:2,155:" +
"2,-1:20,42,155:2,-1:4,155:11,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:" +
"20,155:3,-1:4,155:11,61,155,68,155:3,104,155:2,-1,155:2,-1,155,-1:2,155:2,-" +
"1:20,155,43,155,-1:4,155:11,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:2" +
"0,155:2,68,-1:4,155:11,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155" +
":3,-1:4,155:9,44,155,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155,4" +
"5,155,-1:4,155:11,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1" +
":4,155:11,61,155,68,155:3,117,155:2,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3," +
"-1:4,155:11,61,155,68,155:6,-1,155,118,-1,155,-1:2,155:2,-1:20,155:3,-1:4,1" +
"55:8,146,155:2,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155,68,155," +
"-1:4,155:11,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155" +
":7,119,155:3,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,15" +
"5:11,61,155,68,155:6,-1,155:2,-1,155,-1:2,120,155,-1:20,155:3,-1:4,68,155:1" +
"0,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,122,155:10,61" +
",155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155,123,155:9,61," +
"155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:11,61,155,68,1" +
"55:4,124,155,-1,155:2,-1,155,-1:2,155:2,-1:20,155,125,155,-1:4,155:11,61,15" +
"5,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155,46,155,-1:4,155:11,61,155,6" +
"8,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:2,127,155:8,61,155," +
"68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155,47,155,-1:4,155:11,61,155,68," +
"155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155,48,155,-1:4,155:11,61,155,68,155" +
":6,-1,155:2,-1,155,-1:2,155:2,-1:20,129,155:2,-1:4,155:11,61,155,68,155:6,-" +
"1,155:2,-1,155,-1:2,155:2,-1:20,68,155:2,-1:4,155:11,61,155,68,155:6,-1,155" +
":2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,49,155:10,61,155,68,155:6,-1,155:2,-1" +
",155,-1:2,155:2,-1:20,155:3,-1:4,155:3,130,155:7,61,155,68,155:6,-1,155:2,-" +
"1,155,-1:2,155:2,-1:20,155:3,-1:4,155:11,61,155,68,155:6,-1,155:2,-1,155,-1" +
":2,155,68,-1:20,155:3,-1:4,155,50,155:9,61,155,68,155:6,-1,155:2,-1,155,-1:" +
"2,155:2,-1:20,155:3,-1:4,155:7,132,155:3,61,155,68,155:6,-1,155:2,-1,155,-1" +
":2,155:2,-1:20,155:3,-1:4,155:4,68,155:6,61,155,68,155:6,-1,155:2,-1,155,-1" +
":2,155:2,-1:20,155:3,-1:4,155,51,155:9,61,155,68,155:6,-1,155:2,-1,155,-1:2" +
",155:2,-1:20,155:3,-1:4,155:11,61,155,68,155:4,68,155,-1,155:2,-1,155,-1:2," +
"155:2,-1:20,155:3,-1:4,155:4,133,155:6,61,155,68,155:6,-1,155:2,-1,155,-1:2" +
",155:2,-1:20,155:3,-1:4,155:7,68,155:3,61,155,68,155:6,-1,155:2,-1,155,-1:2" +
",155:2,-1:20,155:3,-1:4,155:11,61,155,68:2,155:5,-1,155:2,-1,155,-1:2,155:2" +
",-1:20,155:3,-1:4,155:11,61,155,68,155:3,134,155:2,-1,155:2,-1,155,-1:2,155" +
":2,-1:20,155,52,155,-1:4,155:11,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2," +
"-1:20,155:3,-1:4,155:7,71,155:3,61,155,68,155,73,155:4,-1,155:2,-1,155,-1:2" +
",155:2,-1:20,155:3,-1:4,155:8,144,155:2,61,155,68,155:6,-1,155:2,-1,155,-1:" +
"2,155:2,-1:20,155:3,-1:4,155:4,106,155:6,61,155,68,155:6,-1,155:2,-1,155,-1" +
":2,155:2,-1:16,80:2,-1,80:7,90,80:44,-1:5,155:3,-1:4,155:10,115,61,155,68,1" +
"55:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,116,155:10,61,155,68,155:6" +
",-1,155:2,-1,155,-1:2,155:2,-1:20,155,114,155,-1:4,155:11,61,155,68,155:6,-" +
"1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:11,61,155,68,155:4,149,155,-" +
"1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:7,121,155:3,61,155,68,155:6," +
"-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:11,61,155,68,155:6,-1,155:2" +
",-1,155,-1:2,145,155,-1:20,155:3,-1:4,155:11,61,155,68,155:4,131,155,-1,155" +
":2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:2,128,155:8,61,155,68,155:6,-1,15" +
"5:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:2,75,77,155:4,79,155:2,61,155,68" +
",155:4,81,155,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:4,113,155:6,6" +
"1,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:7,126,155:3," +
"61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155,83,155:9,61" +
",155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:2,85,155:8,61" +
",155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155,87,155,-1:4,155:11,61,15" +
"5,68,155:4,137,155,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:8,91,155" +
":2,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:6,93,155" +
":4,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:11,61,15" +
"5,68,155:4,95,155,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:11,61,155" +
",68,155:4,96,155,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:3,97,155:7" +
",61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:7,98,148,1" +
"55:2,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:4,99,1" +
"55:6,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:2,100," +
"155:8,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:11,61" +
",155,68,155:4,81,155,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:7,136," +
"155:3,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:20,155:3,-1:4,155:2,141" +
",155:8,61,155,68,155:6,-1,155:2,-1,155,-1:2,155:2,-1:15");

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
System.out.println("NEWLINE");
isIndent = false;
newIndex = yylength();
return new Symbol(sym.NL, "");
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
  System.out.println("KEYWORD" + yytext());
  return new Symbol(sym.KW, yytext()); 
}
					case -38:
						break;
					case 38:
						{
  System.out.println("TRY" + yytext());
  return new Symbol(sym.TY, yytext());
}
					case -39:
						break;
					case 39:
						{
  System.out.println("NOT" + yytext());
  return new Symbol(sym.NT, yytext()); 
}
					case -40:
						break;
					case 40:
						{
  System.out.println("PACKAGE" + yytext());
  return new Symbol(sym.PK, yytext()); 
}
					case -41:
						break;
					case 41:
						{
  System.out.println("NUMBER" + yytext());
  return new Symbol(sym.DL, yytext()); 
}
					case -42:
						break;
					case 42:
						{
  System.out.println("AND" + yytext());
  return new Symbol(sym.AD, yytext()); 
}
					case -43:
						break;
					case 43:
						{
  System.out.println("ELSE" + yytext());
  return new Symbol(sym.EI, yytext()); 
}
					case -44:
						break;
					case 44:
						{
  System.out.println("FROM" + yytext());
  return new Symbol(sym.FR, yytext()); 
}
					case -45:
						break;
					case 45:
						{
  System.out.println("TRUE" + yytext());
  return new Symbol(sym.TR, yytext()); 
}
					case -46:
						break;
					case 46:
						{
  System.out.println("FALSE" + yytext());
  return new Symbol(sym.FL, yytext()); 
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
  System.out.println("WHILE" + yytext());
  return new Symbol(sym.WH, yytext()); 
}
					case -49:
						break;
					case 49:
						{
  System.out.println("CLASS" + yytext());
  return new Symbol(sym.CL, yytext()); 
}
					case -50:
						break;
					case 50:
						{
  System.out.println("EXCEPT" + yytext());
  return new Symbol(sym.EX, yytext()); 
}
					case -51:
						break;
					case 51:
						{
  System.out.println("IMPORT" + yytext());
  return new Symbol(sym.IM, yytext()); 
}
					case -52:
						break;
					case 52:
						{
  System.out.println("CONTINUE" + yytext());
  return new Symbol(sym.CN, yytext()); 
}
					case -53:
						break;
					case 54:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -54:
						break;
					case 55:
						{ }
					case -55:
						break;
					case 56:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.UD, yytext()); 
}
					case -56:
						break;
					case 57:
						{ 
  System.out.println("STRING" + yytext());
  return new Symbol(sym.ST, yytext()); 
}
					case -57:
						break;
					case 58:
						{
  System.out.println("NUMBER" + yytext());
  return new Symbol(sym.DL, yytext()); 
}
					case -58:
						break;
					case 59:
						{
  System.out.println("TRUE" + yytext());
  return new Symbol(sym.TR, yytext()); 
}
					case -59:
						break;
					case 60:
						{
  System.out.println("FALSE" + yytext());
  return new Symbol(sym.FL, yytext()); 
}
					case -60:
						break;
					case 62:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -61:
						break;
					case 63:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.UD, yytext()); 
}
					case -62:
						break;
					case 65:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -63:
						break;
					case 66:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.UD, yytext()); 
}
					case -64:
						break;
					case 68:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -65:
						break;
					case 69:
						{ 
  System.out.println(yytext());
  return new Symbol(sym.UD, yytext()); 
}
					case -66:
						break;
					case 71:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -67:
						break;
					case 73:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -68:
						break;
					case 75:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -69:
						break;
					case 77:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -70:
						break;
					case 79:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -71:
						break;
					case 81:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -72:
						break;
					case 83:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -73:
						break;
					case 85:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -74:
						break;
					case 87:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -75:
						break;
					case 89:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -76:
						break;
					case 91:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -77:
						break;
					case 93:
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
					case 135:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -119:
						break;
					case 136:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -120:
						break;
					case 137:
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
					case 162:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -145:
						break;
					case 163:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -146:
						break;
					case 164:
						{
  System.out.println("ID" + yytext());
  return new Symbol(sym.ID, yytext()); 
}
					case -147:
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
