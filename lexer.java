import java.util.*;
import java.io.*;
/* semantic value of token returned by scanner */
public class lexer
{
	BufferedReader reader;
	Yylex yy;
	public lexer(String filein) throws FileNotFoundException 
	{
		reader = new BufferedReader(new FileReader(filein));
		yy = new Yylex (reader);
	}
	public String getNextToken(String filein) throws IOException
	{
		return yy.next_token();
	}
	public static void main (String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("ms1.py"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("testout.txt"));
		Yylex yy = new Yylex (reader);
		while(true)
		{
			String x =yy.next_token();
			if(x==null)
				break;
			writer.write(x);	
			writer.write('\n');
		}
		reader.close();
		writer.close();
	}
}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
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
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
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
		/* 68 */ YY_NOT_ACCEPT,
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
		/* 114 */ YY_NOT_ACCEPT,
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
		/* 130 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"3:8,4:2,1,3,4:2,3:18,4,3,5,2,3:3,35,44,45,37,36,47,34,32,38,21:10,50,46,40," +
"39,41,3:2,20:4,33,30,20:13,28,20:6,48,3,49,3,19,3,26,20,24,27,14,15,31,12,9" +
",20:2,13,17,10,16,18,20,8,6,7,25,20,11,23,22,20,42,29,43,3:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,131,
"0,1,2,3,1,4,5,1:5,6,1:8,7,1:2,8,9,10:2,1:2,10,11,10:3,12,13,10:12,14,15,16," +
"17,1,18,1:2,19,20,21,22,23,24,20,25,10,26,27,28,29,30,31,25,32,33,34,35,36," +
"37,38,39,40,41,42,15,43,18,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59," +
"60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,10,79,80,81,82,83," +
"84,85,86")[0];

	private int yy_nxt[][] = unpackFromString(87,51,
"1,2,3,4,2,51,5,112,119,52,120,121,122:2,123,124,59,122,125,58,122,6,122:2,1" +
"26,122,127,128,129,61,130,122,7,122,8,64,9,10,11,12,13,14,15,16,17,18,19,20" +
",21,22,23,-1:52,2,-1:2,2,-1:48,3:49,-1:6,122,62,122:11,57,122,65,122:7,-1,1" +
"22:2,-1,122,-1:38,6,-1:10,66,-1:57,28,-1:60,29,-1:6,74,-1:51,122,30,122:11," +
"57,122,65,122:7,-1,122:2,-1,122,-1:23,122:13,57,122,65,122:7,-1,122:2,-1,12" +
"2,-1:23,31:13,-1,31:9,-1,31:2,-1,31,-1:36,35,-1:52,36,-1:11,80,-1:18,49:4,5" +
"3,49:45,-1,74:4,50,74:45,-1,49:4,24,49:45,-1:6,122:4,25,122:4,26,122,71,122" +
",57,122,65,122:7,-1,122:2,-1,122,-1:38,54,-1:35,31:13,-1,31,-1,31:7,-1,31:2" +
",-1,31,-1:23,60:13,63,60,-1,60:7,-1,60:2,-1,60,-1:23,122:2,27,122:10,57,122" +
",65,122:7,-1,122:2,-1,122,-1:23,60:13,35,60:9,-1,60:2,-1,60,-1:25,68,-1:17," +
"70,-1:30,122:2,30,122:10,57,122,65,122:7,-1,122:2,-1,122,-1:18,72:34,53,72:" +
"15,-1:21,36,-1:35,122:13,57,122,65,32,122:2,91,122:3,-1,122:2,-1,122,-1:42," +
"76,-1:31,122:4,92,122:8,57,122,65,122:7,-1,122:2,-1,122,-1:30,78,-1:43,122:" +
"12,116,57,122,65,122:7,-1,122:2,-1,122,-1:23,122,33,122:11,57,122,65,122:7," +
"-1,122:2,-1,122,-1:18,74:4,114,74:45,-1:6,122:3,117,122:9,57,122,65,122:7,-" +
"1,122:2,-1,122,-1:31,55,-1:42,93,122:12,57,122,65,122:7,-1,122:2,-1,122,-1:" +
"23,82,-1:50,122:13,57,122,65,122:2,94,122:4,-1,122:2,-1,122,-1:38,54,-1:12," +
"86,-1,86,-1:20,122:10,95,122:2,57,122,65,122:7,-1,122:2,-1,122,-1:31,56,-1:" +
"42,122:2,34,122:10,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:7,96,122:5,57," +
"122,65,122:7,-1,122:2,-1,122,-1:23,122:13,57,122,65,122:4,118,122:2,-1,122:" +
"2,-1,122,-1:23,122:13,57,122,65,122:5,37,122,-1,122:2,-1,122,-1:23,122:9,38" +
",122:3,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:13,57,122,65,122:3,91,122:" +
"3,-1,122:2,-1,122,-1:23,122:8,39,122:4,57,122,65,122:7,-1,122:2,-1,122,-1:2" +
"3,122:13,57,122,65,122:7,-1,122,99,-1,122,-1:23,122:8,40,122:4,57,122,65,12" +
"2:7,-1,122:2,-1,122,-1:23,122:12,102,57,122,65,122:7,-1,122:2,-1,122,-1:23," +
"122:11,41,122,57,122,65,122:7,-1,122:2,-1,122,-1:23,103,122:12,57,122,65,12" +
"2:7,-1,122:2,-1,122,-1:23,122:4,104,122:8,57,122,65,122:7,-1,122:2,-1,122,-" +
"1:23,122,106,122:11,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:8,42,122:4,57" +
",122,65,122:7,-1,122:2,-1,122,-1:23,122:2,107,122:10,57,122,65,122:7,-1,122" +
":2,-1,122,-1:23,122:8,34,122:4,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:8," +
"108,122:4,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:8,43,122:4,57,122,65,12" +
"2:7,-1,122:2,-1,122,-1:23,122,44,122:11,57,122,65,122:7,-1,122:2,-1,122,-1:" +
"23,45,122:12,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:3,109,122:9,57,122,6" +
"5,122:7,-1,122:2,-1,122,-1:23,122,46,122:11,57,122,65,122:7,-1,122:2,-1,122" +
",-1:23,122,47,122:11,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:4,110,122:8," +
"57,122,65,122:7,-1,122:2,-1,122,-1:23,122:13,57,122,65,122:3,111,122:3,-1,1" +
"22:2,-1,122,-1:23,122:8,48,122:4,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:" +
"2,67,122:10,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:4,98,122:8,57,122,65," +
"122:7,-1,122:2,-1,122,-1:18,74:4,84,74:45,-1:6,122:3,97,122:9,57,122,65,122" +
":7,-1,122:2,-1,122,-1:23,122:10,100,122:2,57,122,65,122:7,-1,122:2,-1,122,-" +
"1:23,122:7,101,122:5,57,122,65,122:7,-1,122:2,-1,122,-1:23,105,122:12,57,12" +
"2,65,122:7,-1,122:2,-1,122,-1:23,122:13,57,122,65,122:4,69,122:2,-1,122:2,-" +
"1,122,-1:23,122:10,73,122:2,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:6,75," +
"122:6,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:7,77,122:5,57,122,65,122,79" +
",122:5,-1,122:2,-1,122,-1:23,122:2,81,122:7,83,122:2,57,122,65,122:4,85,122" +
":2,-1,122:2,-1,122,-1:23,122:2,115,122:10,57,122,65,122:7,-1,122:2,-1,122,-" +
"1:23,122:7,87,122:2,113,122:2,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:4,8" +
"8,122:8,57,122,65,122:7,-1,122:2,-1,122,-1:23,122:8,89,122:4,57,122,65,122:" +
"7,-1,122:2,-1,122,-1:23,122:2,90,122:10,57,122,65,122:7,-1,122:2,-1,122,-1:" +
"23,122:13,57,122,65,122:4,85,122:2,-1,122:2,-1,122,-1:17");

	public String next_token ()
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
						{ }
					case -4:
						break;
					case 4:
						{ 
  return "Undefined\t"+yytext(); 
}
					case -5:
						break;
					case 5:
						{ 
  return "ID\t"+yytext(); 
}
					case -6:
						break;
					case 6:
						{ 
  return "IN\t"+yytext(); 
}
					case -7:
						break;
					case 7:
						{ 
  return "DO\t."; 
}
					case -8:
						break;
					case 8:
						{ 
  return "MO\t-"; 
}
					case -9:
						break;
					case 9:
						{ 
  return "PO\t+"; 
}
					case -10:
						break;
					case 10:
						{ 
  return "MB\t*"; 
}
					case -11:
						break;
					case 11:
						{ 
  return "DB\t/"; 
}
					case -12:
						break;
					case 12:
						{ 
  return "AO\t="; 
}
					case -13:
						break;
					case 13:
						{ 
  return "LT\t<";  
}
					case -14:
						break;
					case 14:
						{ 
  return "GT\t>"; 
}
					case -15:
						break;
					case 15:
						{ 
  return "LC\t{"; 
}
					case -16:
						break;
					case 16:
						{ 
  return "RC\t}"; 
}
					case -17:
						break;
					case 17:
						{ 
  return "LB\t("; 
}
					case -18:
						break;
					case 18:
						{ 
  return "RB\t)"; 
}
					case -19:
						break;
					case 19:
						{ 
  return "SM\t;"; 
}
					case -20:
						break;
					case 20:
						{ 
  return "FA\t,"; 
}
					case -21:
						break;
					case 21:
						{ 
  return "LS\t["; 
}
					case -22:
						break;
					case 22:
						{ 
  return "RS\t]"; 
}
					case -23:
						break;
					case 23:
						{ 
  return "SC\t]"; 
}
					case -24:
						break;
					case 24:
						{ 
  return "ST\t"+yytext(); 
}
					case -25:
						break;
					case 25:
						{ 
  return "IN\t"+yytext(); 
}
					case -26:
						break;
					case 26:
						{ 
  return "IF\t"+yytext(); 
}
					case -27:
						break;
					case 27:
						{ 
  return "OR\t"+yytext(); 
}
					case -28:
						break;
					case 28:
						{ 
  return "EQ\t=="; 
}
					case -29:
						break;
					case 29:
						{ 
  return "AA\t[]"; 
}
					case -30:
						break;
					case 30:
						{ 
  return "KW\t"+yytext(); 
}
					case -31:
						break;
					case 31:
						{ 
  return "FC\t"+yytext(); 
}
					case -32:
						break;
					case 32:
						{ 
  return "TY\t"+yytext(); 
}
					case -33:
						break;
					case 33:
						{ 
  return "NT\t"+yytext(); 
}
					case -34:
						break;
					case 34:
						{ 
  return "LP\t"+yytext(); 
}
					case -35:
						break;
					case 35:
						{ 
  return "PK\t"+yytext(); 
}
					case -36:
						break;
					case 36:
						{ 
  return "DL\t"+yytext(); 
}
					case -37:
						break;
					case 37:
						{ 
  return "AD\t"+yytext(); 
}
					case -38:
						break;
					case 38:
						{ 
  return "DF\t"+yytext(); 
}
					case -39:
						break;
					case 39:
						{ 
  return "TR\t"+yytext(); 
}
					case -40:
						break;
					case 40:
						{ 
  return "EI\t"+yytext(); 
}
					case -41:
						break;
					case 41:
						{ 
  return "FR\t"+yytext(); 
}
					case -42:
						break;
					case 42:
						{ 
  return "RG\t"+yytext(); 
}
					case -43:
						break;
					case 43:
						{ 
  return "FL\t"+yytext(); 
}
					case -44:
						break;
					case 44:
						{ 
  return "PR\t"+yytext(); 
}
					case -45:
						break;
					case 45:
						{ 
  return "CL\t"+yytext(); 
}
					case -46:
						break;
					case 46:
						{ 
  return "IM\t"+yytext(); 
}
					case -47:
						break;
					case 47:
						{ 
  return "EX\t"+yytext(); 
}
					case -48:
						break;
					case 48:
						{ 
  return "CN\t"+yytext(); 
}
					case -49:
						break;
					case 50:
						{ }
					case -50:
						break;
					case 51:
						{ 
  return "Undefined\t"+yytext(); 
}
					case -51:
						break;
					case 52:
						{ 
  return "ID\t"+yytext(); 
}
					case -52:
						break;
					case 53:
						{ 
  return "ST\t"+yytext(); 
}
					case -53:
						break;
					case 54:
						{ 
  return "DL\t"+yytext(); 
}
					case -54:
						break;
					case 55:
						{ 
  return "TR\t"+yytext(); 
}
					case -55:
						break;
					case 56:
						{ 
  return "FL\t"+yytext(); 
}
					case -56:
						break;
					case 58:
						{ 
  return "Undefined\t"+yytext(); 
}
					case -57:
						break;
					case 59:
						{ 
  return "ID\t"+yytext(); 
}
					case -58:
						break;
					case 61:
						{ 
  return "Undefined\t"+yytext(); 
}
					case -59:
						break;
					case 62:
						{ 
  return "ID\t"+yytext(); 
}
					case -60:
						break;
					case 64:
						{ 
  return "Undefined\t"+yytext(); 
}
					case -61:
						break;
					case 65:
						{ 
  return "ID\t"+yytext(); 
}
					case -62:
						break;
					case 67:
						{ 
  return "ID\t"+yytext(); 
}
					case -63:
						break;
					case 69:
						{ 
  return "ID\t"+yytext(); 
}
					case -64:
						break;
					case 71:
						{ 
  return "ID\t"+yytext(); 
}
					case -65:
						break;
					case 73:
						{ 
  return "ID\t"+yytext(); 
}
					case -66:
						break;
					case 75:
						{ 
  return "ID\t"+yytext(); 
}
					case -67:
						break;
					case 77:
						{ 
  return "ID\t"+yytext(); 
}
					case -68:
						break;
					case 79:
						{ 
  return "ID\t"+yytext(); 
}
					case -69:
						break;
					case 81:
						{ 
  return "ID\t"+yytext(); 
}
					case -70:
						break;
					case 83:
						{ 
  return "ID\t"+yytext(); 
}
					case -71:
						break;
					case 85:
						{ 
  return "ID\t"+yytext(); 
}
					case -72:
						break;
					case 87:
						{ 
  return "ID\t"+yytext(); 
}
					case -73:
						break;
					case 88:
						{ 
  return "ID\t"+yytext(); 
}
					case -74:
						break;
					case 89:
						{ 
  return "ID\t"+yytext(); 
}
					case -75:
						break;
					case 90:
						{ 
  return "ID\t"+yytext(); 
}
					case -76:
						break;
					case 91:
						{ 
  return "ID\t"+yytext(); 
}
					case -77:
						break;
					case 92:
						{ 
  return "ID\t"+yytext(); 
}
					case -78:
						break;
					case 93:
						{ 
  return "ID\t"+yytext(); 
}
					case -79:
						break;
					case 94:
						{ 
  return "ID\t"+yytext(); 
}
					case -80:
						break;
					case 95:
						{ 
  return "ID\t"+yytext(); 
}
					case -81:
						break;
					case 96:
						{ 
  return "ID\t"+yytext(); 
}
					case -82:
						break;
					case 97:
						{ 
  return "ID\t"+yytext(); 
}
					case -83:
						break;
					case 98:
						{ 
  return "ID\t"+yytext(); 
}
					case -84:
						break;
					case 99:
						{ 
  return "ID\t"+yytext(); 
}
					case -85:
						break;
					case 100:
						{ 
  return "ID\t"+yytext(); 
}
					case -86:
						break;
					case 101:
						{ 
  return "ID\t"+yytext(); 
}
					case -87:
						break;
					case 102:
						{ 
  return "ID\t"+yytext(); 
}
					case -88:
						break;
					case 103:
						{ 
  return "ID\t"+yytext(); 
}
					case -89:
						break;
					case 104:
						{ 
  return "ID\t"+yytext(); 
}
					case -90:
						break;
					case 105:
						{ 
  return "ID\t"+yytext(); 
}
					case -91:
						break;
					case 106:
						{ 
  return "ID\t"+yytext(); 
}
					case -92:
						break;
					case 107:
						{ 
  return "ID\t"+yytext(); 
}
					case -93:
						break;
					case 108:
						{ 
  return "ID\t"+yytext(); 
}
					case -94:
						break;
					case 109:
						{ 
  return "ID\t"+yytext(); 
}
					case -95:
						break;
					case 110:
						{ 
  return "ID\t"+yytext(); 
}
					case -96:
						break;
					case 111:
						{ 
  return "ID\t"+yytext(); 
}
					case -97:
						break;
					case 112:
						{ 
  return "ID\t"+yytext(); 
}
					case -98:
						break;
					case 113:
						{ 
  return "ID\t"+yytext(); 
}
					case -99:
						break;
					case 115:
						{ 
  return "ID\t"+yytext(); 
}
					case -100:
						break;
					case 116:
						{ 
  return "ID\t"+yytext(); 
}
					case -101:
						break;
					case 117:
						{ 
  return "ID\t"+yytext(); 
}
					case -102:
						break;
					case 118:
						{ 
  return "ID\t"+yytext(); 
}
					case -103:
						break;
					case 119:
						{ 
  return "ID\t"+yytext(); 
}
					case -104:
						break;
					case 120:
						{ 
  return "ID\t"+yytext(); 
}
					case -105:
						break;
					case 121:
						{ 
  return "ID\t"+yytext(); 
}
					case -106:
						break;
					case 122:
						{ 
  return "ID\t"+yytext(); 
}
					case -107:
						break;
					case 123:
						{ 
  return "ID\t"+yytext(); 
}
					case -108:
						break;
					case 124:
						{ 
  return "ID\t"+yytext(); 
}
					case -109:
						break;
					case 125:
						{ 
  return "ID\t"+yytext(); 
}
					case -110:
						break;
					case 126:
						{ 
  return "ID\t"+yytext(); 
}
					case -111:
						break;
					case 127:
						{ 
  return "ID\t"+yytext(); 
}
					case -112:
						break;
					case 128:
						{ 
  return "ID\t"+yytext(); 
}
					case -113:
						break;
					case 129:
						{ 
  return "ID\t"+yytext(); 
}
					case -114:
						break;
					case 130:
						{ 
  return "ID\t"+yytext(); 
}
					case -115:
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
