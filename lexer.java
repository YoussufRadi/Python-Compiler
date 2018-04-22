import java_cup.runtime.Symbol;


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

int indentCount = 1;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
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
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
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
		/* 4 */ YY_NO_ANCHOR,
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
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
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
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
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
		/* 134 */ YY_NOT_ACCEPT,
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
		/* 162 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"4:8,5,1,2,4,5:2,4:18,5,4,6,3,4:3,36,47,48,40,39,50,35,33,41,22:10,53,49,43," +
"42,44,4:2,21:4,34,31,21:13,29,21:6,51,4,52,4,20,4,27,37,25,28,15,16,32,13,1" +
"0,21,38,14,18,11,17,19,21,9,7,8,26,21,12,24,23,21,45,30,46,4:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,163,
"0,1:2,2,3,1,4,5,1:5,6,1:8,7,1:2,8,9,10:2,1:2,10,11,10:3,12,13,10:12,14,15,1" +
"6,17,1,18,1:2,19,20,21,22,23,24,20,25,10,26,27,28,29,30,31,25,32,33,34,35,3" +
"6,37,38,39,40,41,42,15,43,18,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,5" +
"9,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,8" +
"4,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,10,1" +
"06,107,108,109,110,111,112,113,114,115,116,117")[0];

	private int yy_nxt[][] = unpackFromString(118,54,
"1,2,3,4,5,3,52,6,132,145,53,148,149,150,151,152,153,60,150,154,59,150,7,155" +
",150,156,150,157,158,159,62,160,161,8,150,9,65,162,150,10,11,12,13,14,15,16" +
",17,18,19,20,21,22,23,24,-1:56,3,-1:2,3,-1:49,4,-1,4:51,-1:7,150,63,150:11," +
"58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:37,7,-1:10,67,-1:62,29,-1:63," +
"30,-1:7,75,-1:54,150,31,150:11,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-" +
"1:22,150:13,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,32:13,-1,32:9," +
"-1,32:2,-1,32,-1:2,32:2,-1:35,36,-1:55,37,-1:11,81,-1:20,50:5,54,50:47,-1,7" +
"5:5,51,75:47,-1,50:5,25,50:47,-1:7,66,150:3,26,150:4,27,150,74,150,58,150,6" +
"6,150:7,-1,150:2,-1,150,-1:2,150:2,-1:37,55,-1:38,32:13,-1,32,-1,32:7,-1,32" +
":2,-1,32,-1:2,32:2,-1:22,61:13,64,61,-1,61:7,-1,61:2,-1,61,-1:2,61:2,-1:22," +
"150:2,28,150:10,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,61:13,36,6" +
"1:9,-1,61:2,-1,61,-1:2,61:2,-1:24,69,-1:17,71,-1:33,150:2,31,150:10,58,150," +
"66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:16,73:35,54,73:17,-1:22,37,-1:38,150" +
":13,58,150,66,33,150:2,96,150:3,-1,150:2,-1,150,-1:2,150:2,-1:41,77,-1:34,1" +
"50,97,150:11,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:29,79,-1:46,150:" +
"4,98,150:8,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:12,147,58,1" +
"50,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:16,75:5,134,75:47,-1:7,150,34,150" +
":2,99,150:8,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:30,56,-1:45,150:3" +
",137,150:9,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,83,-1:53,150:11" +
",100,150,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:37,55,-1:12,87,-1:3," +
"87,-1:21,101,150:2,102,150:9,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:" +
"30,57,-1:45,150:13,58,150,66,150:2,103,150:4,-1,150:2,-1,150,-1:2,150:2,-1:" +
"22,150:10,104,150:2,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:2," +
"35,150:10,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:7,138,150:5," +
"58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,106,150:12,58,150,66,150:7" +
",-1,150:2,-1,150,-1:2,150:2,-1:22,150:8,107,150:4,58,150,66,150:7,-1,150:2," +
"-1,150,-1:2,150:2,-1:22,150:13,58,150,66,150:4,108,150:2,-1,150:2,-1,150,-1" +
":2,150:2,-1:22,150:13,58,150,66,150:5,38,150,-1,150:2,-1,150,-1:2,150:2,-1:" +
"22,150:9,39,150:3,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:13,5" +
"8,150,66,150:3,96,150:3,-1,150:2,-1,150,-1:2,150:2,-1:22,150:8,40,150:4,58," +
"150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:13,58,150,66,150:3,111,15" +
"0:3,-1,150:2,-1,150,-1:2,150:2,-1:22,150:13,58,150,66,150:7,-1,150,112,-1,1" +
"50,-1:2,150:2,-1:22,150:8,66,150:4,58,150,66,150:7,-1,150:2,-1,150,-1:2,150" +
":2,-1:22,150:13,58,150,66,150:7,-1,150:2,-1,150,-1:2,114,150,-1:22,150:8,41" +
",150:4,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:9,66,150:3,58,1" +
"50,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:12,115,58,150,66,150:7,-1," +
"150:2,-1,150,-1:2,150:2,-1:22,150:11,42,150,58,150,66,150:7,-1,150:2,-1,150" +
",-1:2,150:2,-1:22,150:4,117,150:8,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:" +
"2,-1:22,66,150:12,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:7,11" +
"8,150:5,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,119,150:12,58,150," +
"66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150,120,150:11,58,150,66,150:7,-1" +
",150:2,-1,150,-1:2,150:2,-1:22,150:13,58,150,66,150:4,121,150:2,-1,150:2,-1" +
",150,-1:2,150:2,-1:22,150:2,122,150:10,58,150,66,150:7,-1,150:2,-1,150,-1:2" +
",150:2,-1:22,150:8,43,150:4,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:2" +
"2,150:8,35,150:4,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:13,58" +
",150,66,150:5,124,150,-1,150:2,-1,150,-1:2,150:2,-1:22,150:8,125,150:4,58,1" +
"50,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:8,44,150:4,58,150,66,150:7" +
",-1,150:2,-1,150,-1:2,150:2,-1:22,150,45,150:11,58,150,66,150:7,-1,150:2,-1" +
",150,-1:2,150:2,-1:22,150:13,58,150,66,150:5,66,150,-1,150:2,-1,150,-1:2,15" +
"0:2,-1:22,46,150:12,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:3," +
"127,150:9,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:13,58,150,66" +
",150:7,-1,150:2,-1,150,-1:2,150,66,-1:22,150:4,66,150:8,58,150,66,150:7,-1," +
"150:2,-1,150,-1:2,150:2,-1:22,150,47,150:11,58,150,66,150:7,-1,150:2,-1,150" +
",-1:2,150:2,-1:22,150:13,58,150,66,150:4,66,150:2,-1,150:2,-1,150,-1:2,150:" +
"2,-1:22,150,48,150:11,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:" +
"7,129,150:5,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:4,130,150:" +
"8,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:7,66,150:5,58,150,66" +
",150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:13,58,150,66:2,150:6,-1,150:2,-" +
"1,150,-1:2,150:2,-1:22,150:13,58,150,66,150:3,131,150:3,-1,150:2,-1,150,-1:" +
"2,150:2,-1:22,150:8,49,150:4,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:" +
"22,150:2,68,150:10,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:4,1" +
"40,150:8,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:16,75:5,85,75:47,-1:" +
"7,150:3,105,150:9,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:10,1" +
"41,150:2,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:7,113,150:5,5" +
"8,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,116,150:12,58,150,66,150:7," +
"-1,150:2,-1,150,-1:2,150:2,-1:22,150:8,110,150:4,58,150,66,150:7,-1,150:2,-" +
"1,150,-1:2,150:2,-1:22,150:13,58,150,66,150:4,142,150:2,-1,150:2,-1,150,-1:" +
"2,150:2,-1:22,150:13,58,150,66,150:7,-1,150:2,-1,150,-1:2,143,150,-1:22,150" +
":7,126,150:5,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:13,58,150" +
",66,150:4,128,150:2,-1,150:2,-1,150,-1:2,150:2,-1:22,150:2,123,150:10,58,15" +
"0,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:8,70,150:4,58,150,66,150:4," +
"72,150:2,-1,150:2,-1,150,-1:2,150:2,-1:22,150:4,109,150:8,58,150,66,150:7,-" +
"1,150:2,-1,150,-1:2,150:2,-1:22,150:10,144,150:2,58,150,66,150:7,-1,150:2,-" +
"1,150,-1:2,150:2,-1:22,150:10,76,150:2,58,150,66,150:7,-1,150:2,-1,150,-1:2" +
",150:2,-1:22,150:6,78,150:6,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:2" +
"2,150:13,58,150,66,150:4,80,150:2,-1,150:2,-1,150,-1:2,150:2,-1:22,150:7,82" +
",150:5,58,150,66,150,84,150:5,-1,150:2,-1,150,-1:2,150:2,-1:22,150:2,86,133" +
",150:6,88,150:2,58,150,66,150:4,89,150:2,-1,150:2,-1,150,-1:2,150:2,-1:22,1" +
"50:2,135,150:10,58,150,66,150:4,90,150:2,-1,150:2,-1,150,-1:2,150:2,-1:22,1" +
"50:3,91,150:9,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:7,92,150" +
":2,146,150:2,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:4,93,150:" +
"8,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:8,94,150:4,58,150,66" +
",150:7,-1,150:2,-1,150,-1:2,150:2,-1:22,150:2,95,150:10,58,150,66,150:7,-1," +
"150:2,-1,150,-1:2,150:2,-1:22,150:13,58,150,66,150:4,89,150:2,-1,150:2,-1,1" +
"50,-1:2,150:2,-1:22,150:7,136,150:5,58,150,66,150:7,-1,150:2,-1,150,-1:2,15" +
"0:2,-1:22,150:2,139,150:10,58,150,66,150:7,-1,150:2,-1,150,-1:2,150:2,-1:15");

	public java_cup.runtime.Symbol next_token ()
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
						{
  return new Symbol(sym.IND, indentCount++); 
}
					case -3:
						break;
					case 3:
						{ indentCount = 1; }
					case -4:
						break;
					case 4:
						{ indentCount = 1; }
					case -5:
						break;
					case 5:
						{ 
  indentCount = 1; 
  return new Symbol(sym.UD, yytext()); 
}
					case -6:
						break;
					case 6:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -7:
						break;
					case 7:
						{
  indentCount = 1;  
  return new Symbol(sym.INT, yytext()); 
}
					case -8:
						break;
					case 8:
						{ 
  indentCount = 1; 
  return new Symbol(sym.DO, yytext()); 
}
					case -9:
						break;
					case 9:
						{ 
  indentCount = 1; 
  return new Symbol(sym.MO, yytext()); 
}
					case -10:
						break;
					case 10:
						{ 
  indentCount = 1; 
  return new Symbol(sym.PO, yytext()); 
}
					case -11:
						break;
					case 11:
						{ 
  indentCount = 1; 
  return new Symbol(sym.MB, yytext()); 
}
					case -12:
						break;
					case 12:
						{ 
  indentCount = 1; 
  return new Symbol(sym.DB, yytext()); 
}
					case -13:
						break;
					case 13:
						{ 
  indentCount = 1; 
  return new Symbol(sym.AO, yytext()); 
}
					case -14:
						break;
					case 14:
						{ 
  indentCount = 1; 
  return new Symbol(sym.LT, yytext()); 
}
					case -15:
						break;
					case 15:
						{ 
  indentCount = 1; 
  return new Symbol(sym.GT, yytext()); 
}
					case -16:
						break;
					case 16:
						{ 
  indentCount = 1; 
  return new Symbol(sym.LC, yytext()); 
}
					case -17:
						break;
					case 17:
						{ 
  indentCount = 1; 
  return new Symbol(sym.RC, yytext()); 
}
					case -18:
						break;
					case 18:
						{ 
  indentCount = 1; 
  return new Symbol(sym.LB, yytext()); 
}
					case -19:
						break;
					case 19:
						{ 
  indentCount = 1; 
  return new Symbol(sym.RB, yytext()); 
}
					case -20:
						break;
					case 20:
						{ 
  indentCount = 1; 
  return new Symbol(sym.SM, yytext()); 
}
					case -21:
						break;
					case 21:
						{ 
  indentCount = 1; 
  return new Symbol(sym.FA, yytext()); 
}
					case -22:
						break;
					case 22:
						{ 
  indentCount = 1; 
  return new Symbol(sym.LS, yytext()); 
}
					case -23:
						break;
					case 23:
						{ 
  indentCount = 1; 
  return new Symbol(sym.RS, yytext()); 
}
					case -24:
						break;
					case 24:
						{ 
  indentCount = 1; 
  return new Symbol(sym.SC, yytext()); 
}
					case -25:
						break;
					case 25:
						{ 
  indentCount = 1; 
  return new Symbol(sym.ST, yytext()); 
}
					case -26:
						break;
					case 26:
						{
  indentCount = 1;  
  return new Symbol(sym.IN, yytext()); 
}
					case -27:
						break;
					case 27:
						{
  indentCount = 1; 
  return new Symbol(sym.IF, yytext()); 
}
					case -28:
						break;
					case 28:
						{
  indentCount = 1;  
  return new Symbol(sym.OR, yytext()); 
}
					case -29:
						break;
					case 29:
						{ 
  indentCount = 1; 
  return new Symbol(sym.EQ, yytext()); 
}
					case -30:
						break;
					case 30:
						{ 
  indentCount = 1; 
  return new Symbol(sym.AA, yytext()); 
}
					case -31:
						break;
					case 31:
						{
  indentCount = 1; 
  return new Symbol(sym.KW, yytext()); 
}
					case -32:
						break;
					case 32:
						{
  indentCount = 1; 
  return new Symbol(sym.FC, yytext()); 
}
					case -33:
						break;
					case 33:
						{
  indentCount = 1; 
  return new Symbol(sym.TY, yytext());
}
					case -34:
						break;
					case 34:
						{
  indentCount = 1;  
  return new Symbol(sym.NT, yytext()); 
}
					case -35:
						break;
					case 35:
						{ 
  indentCount = 1;
  return new Symbol(sym.LP, yytext()); 
}
					case -36:
						break;
					case 36:
						{
  indentCount = 1; 
  return new Symbol(sym.PK, yytext()); 
}
					case -37:
						break;
					case 37:
						{
  indentCount = 1;  
  return new Symbol(sym.DL, yytext()); 
}
					case -38:
						break;
					case 38:
						{
  indentCount = 1;  
  return new Symbol(sym.AD, yytext()); 
}
					case -39:
						break;
					case 39:
						{
  indentCount = 1;  
  return new Symbol(sym.DF, yytext()); 
}
					case -40:
						break;
					case 40:
						{
  indentCount = 1;  
  return new Symbol(sym.TR, yytext()); 
}
					case -41:
						break;
					case 41:
						{
  indentCount = 1; 
  return new Symbol(sym.EI, yytext()); 
}
					case -42:
						break;
					case 42:
						{
  indentCount = 1; 
  return new Symbol(sym.FR, yytext()); 
}
					case -43:
						break;
					case 43:
						{
  indentCount = 1;  
  return new Symbol(sym.RG, yytext()); 
}
					case -44:
						break;
					case 44:
						{
  indentCount = 1;  
  return new Symbol(sym.FL, yytext()); 
}
					case -45:
						break;
					case 45:
						{
  indentCount = 1; 
  return new Symbol(sym.PR, yytext()); 
}
					case -46:
						break;
					case 46:
						{
  indentCount = 1;  
  return new Symbol(sym.CL, yytext()); 
}
					case -47:
						break;
					case 47:
						{
  indentCount = 1; 
  return new Symbol(sym.IM, yytext()); 
}
					case -48:
						break;
					case 48:
						{
  indentCount = 1;  
  return new Symbol(sym.EX, yytext()); 
}
					case -49:
						break;
					case 49:
						{
  indentCount = 1;  
  return new Symbol(sym.CN, yytext()); 
}
					case -50:
						break;
					case 51:
						{ indentCount = 1; }
					case -51:
						break;
					case 52:
						{ 
  indentCount = 1; 
  return new Symbol(sym.UD, yytext()); 
}
					case -52:
						break;
					case 53:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -53:
						break;
					case 54:
						{ 
  indentCount = 1; 
  return new Symbol(sym.ST, yytext()); 
}
					case -54:
						break;
					case 55:
						{
  indentCount = 1;  
  return new Symbol(sym.DL, yytext()); 
}
					case -55:
						break;
					case 56:
						{
  indentCount = 1;  
  return new Symbol(sym.TR, yytext()); 
}
					case -56:
						break;
					case 57:
						{
  indentCount = 1;  
  return new Symbol(sym.FL, yytext()); 
}
					case -57:
						break;
					case 59:
						{ 
  indentCount = 1; 
  return new Symbol(sym.UD, yytext()); 
}
					case -58:
						break;
					case 60:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -59:
						break;
					case 62:
						{ 
  indentCount = 1; 
  return new Symbol(sym.UD, yytext()); 
}
					case -60:
						break;
					case 63:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -61:
						break;
					case 65:
						{ 
  indentCount = 1; 
  return new Symbol(sym.UD, yytext()); 
}
					case -62:
						break;
					case 66:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -63:
						break;
					case 68:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -64:
						break;
					case 70:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -65:
						break;
					case 72:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -66:
						break;
					case 74:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -67:
						break;
					case 76:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -68:
						break;
					case 78:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -69:
						break;
					case 80:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -70:
						break;
					case 82:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -71:
						break;
					case 84:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -72:
						break;
					case 86:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -73:
						break;
					case 88:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -74:
						break;
					case 89:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -75:
						break;
					case 90:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -76:
						break;
					case 91:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -77:
						break;
					case 92:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -78:
						break;
					case 93:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -79:
						break;
					case 94:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -80:
						break;
					case 95:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -81:
						break;
					case 96:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -82:
						break;
					case 97:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -83:
						break;
					case 98:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -84:
						break;
					case 99:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -85:
						break;
					case 100:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -86:
						break;
					case 101:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -87:
						break;
					case 102:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -88:
						break;
					case 103:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -89:
						break;
					case 104:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -90:
						break;
					case 105:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -91:
						break;
					case 106:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -92:
						break;
					case 107:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -93:
						break;
					case 108:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -94:
						break;
					case 109:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -95:
						break;
					case 110:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -96:
						break;
					case 111:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -97:
						break;
					case 112:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -98:
						break;
					case 113:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -99:
						break;
					case 114:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -100:
						break;
					case 115:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -101:
						break;
					case 116:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -102:
						break;
					case 117:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -103:
						break;
					case 118:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -104:
						break;
					case 119:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -105:
						break;
					case 120:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -106:
						break;
					case 121:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -107:
						break;
					case 122:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -108:
						break;
					case 123:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -109:
						break;
					case 124:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -110:
						break;
					case 125:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -111:
						break;
					case 126:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -112:
						break;
					case 127:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -113:
						break;
					case 128:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -114:
						break;
					case 129:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -115:
						break;
					case 130:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -116:
						break;
					case 131:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -117:
						break;
					case 132:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -118:
						break;
					case 133:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -119:
						break;
					case 135:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -120:
						break;
					case 136:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -121:
						break;
					case 137:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -122:
						break;
					case 138:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -123:
						break;
					case 139:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -124:
						break;
					case 140:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -125:
						break;
					case 141:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -126:
						break;
					case 142:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -127:
						break;
					case 143:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -128:
						break;
					case 144:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -129:
						break;
					case 145:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -130:
						break;
					case 146:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -131:
						break;
					case 147:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -132:
						break;
					case 148:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -133:
						break;
					case 149:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -134:
						break;
					case 150:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -135:
						break;
					case 151:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -136:
						break;
					case 152:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -137:
						break;
					case 153:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -138:
						break;
					case 154:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -139:
						break;
					case 155:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -140:
						break;
					case 156:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -141:
						break;
					case 157:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -142:
						break;
					case 158:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -143:
						break;
					case 159:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -144:
						break;
					case 160:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -145:
						break;
					case 161:
						{
  indentCount = 1;  
  return new Symbol(sym.ID, yytext()); 
}
					case -146:
						break;
					case 162:
						{
  indentCount = 1;  
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