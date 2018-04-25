
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Wed Apr 25 20:13:05 EET 2018
//----------------------------------------------------

package Compilers;

import java_cup.runtime.*;
import java.io.*;
import java.util.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Wed Apr 25 20:13:05 EET 2018
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\052\000\002\002\004\000\002\002\004\000\002\002" +
    "\002\000\002\003\003\000\002\003\003\000\002\003\003" +
    "\000\002\003\003\000\002\003\003\000\002\003\003\000" +
    "\002\003\003\000\002\003\003\000\002\022\003\000\002" +
    "\022\003\000\002\022\003\000\002\004\007\000\002\005" +
    "\005\000\002\005\005\000\002\006\010\000\002\007\010" +
    "\000\002\012\005\000\002\012\004\000\002\011\004\000" +
    "\002\011\003\000\002\014\005\000\002\014\004\000\002" +
    "\013\004\000\002\013\003\000\002\017\006\000\002\017" +
    "\005\000\002\023\006\000\002\023\005\000\002\020\004" +
    "\000\002\010\006\000\002\015\003\000\002\015\003\000" +
    "\002\015\004\000\002\015\004\000\002\016\006\000\002" +
    "\016\007\000\002\016\010\000\002\016\010\000\002\021" +
    "\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\121\000\022\002\uffff\003\uffff\007\uffff\010\uffff\015" +
    "\uffff\031\uffff\033\uffff\075\uffff\001\002\000\022\002\013" +
    "\003\021\007\007\010\016\015\005\031\012\033\017\075" +
    "\014\001\002\000\004\012\120\001\002\000\022\002\ufffc" +
    "\003\ufffc\007\ufffc\010\ufffc\015\ufffc\031\ufffc\033\ufffc\075" +
    "\ufffc\001\002\000\004\033\105\001\002\000\022\002\ufffb" +
    "\003\ufffb\007\ufffb\010\ufffb\015\ufffb\031\ufffb\033\ufffb\075" +
    "\ufffb\001\002\000\022\002\ufffe\003\ufffe\007\ufffe\010\ufffe" +
    "\015\ufffe\031\ufffe\033\ufffe\075\ufffe\001\002\000\004\033" +
    "\070\001\002\000\004\002\000\001\002\000\022\002\uffd8" +
    "\003\uffd8\007\uffd8\010\uffd8\015\uffd8\031\uffd8\033\uffd8\075" +
    "\uffd8\001\002\000\022\002\ufff8\003\ufff8\007\ufff8\010\ufff8" +
    "\015\ufff8\031\ufff8\033\ufff8\075\ufff8\001\002\000\006\024" +
    "\047\033\046\001\002\000\006\056\027\064\030\001\002" +
    "\000\022\002\001\003\001\007\001\010\001\015\001\031" +
    "\001\033\001\075\001\001\002\000\022\002\ufff7\003\ufff7" +
    "\007\ufff7\010\ufff7\015\ufff7\031\ufff7\033\ufff7\075\ufff7\001" +
    "\002\000\004\075\026\001\002\000\022\002\ufff9\003\ufff9" +
    "\007\ufff9\010\ufff9\015\ufff9\031\ufff9\033\ufff9\075\ufff9\001" +
    "\002\000\022\002\ufffa\003\ufffa\007\ufffa\010\ufffa\015\ufffa" +
    "\031\ufffa\033\ufffa\075\ufffa\001\002\000\022\002\ufffd\003" +
    "\ufffd\007\ufffd\010\ufffd\015\ufffd\031\ufffd\033\ufffd\075\ufffd" +
    "\001\002\000\022\002\uffe2\003\uffe2\007\uffe2\010\uffe2\015" +
    "\uffe2\031\uffe2\033\uffe2\075\uffe2\001\002\000\006\026\045" +
    "\027\044\001\002\000\012\004\036\033\031\036\033\065" +
    "\035\001\002\000\006\065\ufff4\067\ufff4\001\002\000\004" +
    "\065\043\001\002\000\006\065\ufff6\067\ufff6\001\002\000" +
    "\006\065\uffe7\067\037\001\002\000\004\075\uffe5\001\002" +
    "\000\006\065\ufff5\067\ufff5\001\002\000\010\004\036\033" +
    "\031\036\033\001\002\000\004\065\uffe8\001\002\000\006" +
    "\065\uffe9\067\037\001\002\000\004\065\uffea\001\002\000" +
    "\004\075\uffe6\001\002\000\022\002\ufff1\003\ufff1\007\ufff1" +
    "\010\ufff1\015\ufff1\031\ufff1\033\ufff1\075\ufff1\001\002\000" +
    "\022\002\ufff2\003\ufff2\007\ufff2\010\ufff2\015\ufff2\031\ufff2" +
    "\033\ufff2\075\ufff2\001\002\000\010\057\053\060\054\073" +
    "\056\001\002\000\004\033\050\001\002\000\004\073\051" +
    "\001\002\000\004\075\052\001\002\000\022\002\uffdb\003" +
    "\uffdb\007\uffdb\010\uffdb\015\uffdb\031\uffdb\033\uffdb\075\uffdb" +
    "\001\002\000\010\004\uffdf\033\uffdf\056\067\001\002\000" +
    "\010\004\uffe0\033\uffe0\056\066\001\002\000\006\004\061" +
    "\033\060\001\002\000\004\075\057\001\002\000\022\002" +
    "\uffdc\003\uffdc\007\uffdc\010\uffdc\015\uffdc\031\uffdc\033\uffdc" +
    "\075\uffdc\001\002\000\004\073\064\001\002\000\004\073" +
    "\062\001\002\000\004\075\063\001\002\000\022\002\uffd9" +
    "\003\uffd9\007\uffd9\010\uffd9\015\uffd9\031\uffd9\033\uffd9\075" +
    "\uffd9\001\002\000\004\075\065\001\002\000\022\002\uffda" +
    "\003\uffda\007\uffda\010\uffda\015\uffda\031\uffda\033\uffda\075" +
    "\uffda\001\002\000\006\004\uffde\033\uffde\001\002\000\006" +
    "\004\uffdd\033\uffdd\001\002\000\004\064\074\001\002\000" +
    "\004\073\072\001\002\000\004\075\073\001\002\000\022" +
    "\002\uffe1\003\uffe1\007\uffe1\010\uffe1\015\uffe1\031\uffe1\033" +
    "\uffe1\075\uffe1\001\002\000\006\033\075\065\077\001\002" +
    "\000\006\065\uffeb\067\102\001\002\000\004\065\100\001" +
    "\002\000\004\073\uffe3\001\002\000\004\073\uffe4\001\002" +
    "\000\004\065\uffec\001\002\000\004\033\103\001\002\000" +
    "\006\065\uffed\067\102\001\002\000\004\065\uffee\001\002" +
    "\000\004\035\106\001\002\000\004\030\107\001\002\000" +
    "\004\064\113\001\002\000\004\073\111\001\002\000\004" +
    "\075\112\001\002\000\022\002\uffef\003\uffef\007\uffef\010" +
    "\uffef\015\uffef\031\uffef\033\uffef\075\uffef\001\002\000\004" +
    "\004\114\001\002\000\004\067\115\001\002\000\004\004" +
    "\116\001\002\000\004\065\117\001\002\000\004\073\ufff0" +
    "\001\002\000\004\011\121\001\002\000\004\013\122\001" +
    "\002\000\004\075\123\001\002\000\022\002\ufff3\003\ufff3" +
    "\007\ufff3\010\ufff3\015\ufff3\031\ufff3\033\ufff3\075\ufff3\001" +
    "\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\121\000\004\002\003\001\001\000\024\003\017\004" +
    "\010\005\005\007\007\010\023\016\022\017\021\020\014" +
    "\021\024\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\023\070\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\013\031\022\033\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\014\037\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\022\040\001\001\000\002\001\001\000\004\014\041" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\015\054\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\011\075\001\001\000\004\012" +
    "\100\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\012" +
    "\103\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\006\107\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 41: // empty_stmnt ::= NL 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("empty_stmnt",15, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 40: // while_stmnt ::= WH ID bool_expr INT SC NL 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("while_stmnt",12, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39: // while_stmnt ::= WH ID bool_expr ID SC NL 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("while_stmnt",12, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38: // while_stmnt ::= WH NT ID SC NL 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("while_stmnt",12, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // while_stmnt ::= WH ID SC NL 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("while_stmnt",12, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // bool_expr ::= LT AO 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("bool_expr",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // bool_expr ::= GT AO 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("bool_expr",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // bool_expr ::= LT 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("bool_expr",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // bool_expr ::= GT 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("bool_expr",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // def_stmnt ::= DF def SC NL 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("def_stmnt",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // fn_stmnt ::= fn NL 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("fn_stmnt",14, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // def ::= ID LB RB 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("def",17, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // def ::= ID LB params_def RB 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("def",17, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // fn ::= ID LB RB 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("fn",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // fn ::= ID LB params_fn RB 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("fn",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // params_fn ::= value 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("params_fn",9, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // params_fn ::= value params_fn_help 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("params_fn",9, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // params_fn_help ::= FA value 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("params_fn_help",10, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // params_fn_help ::= FA value params_fn_help 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("params_fn_help",10, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // params_def ::= ID 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("params_def",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // params_def ::= ID params_def_help 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("params_def",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // params_def_help ::= FA ID 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("params_def_help",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // params_def_help ::= FA ID params_def_help 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("params_def_help",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // for_stmnt ::= FOR ID IN range_fn SC NL 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("for_stmnt",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // range_fn ::= RG LB INT FA INT RB 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("range_fn",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // bool_stmnt ::= ID AO FL 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("bool_stmnt",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // bool_stmnt ::= ID AO TR 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("bool_stmnt",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // import_stmnt ::= FR PK IM FC NL 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("import_stmnt",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // value ::= ID 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("value",16, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // value ::= INT 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("value",16, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // value ::= ST 
            {
              Boolean RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("value",16, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // statement ::= error 
            {
              Boolean RESULT =null;
		RESULT = false;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // statement ::= fn_stmnt 
            {
              Boolean RESULT =null;
		int fcleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int fcright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Boolean fc = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		RESULT = fc;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // statement ::= while_stmnt 
            {
              Boolean RESULT =null;
		int wsleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int wsright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Boolean ws = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		RESULT = ws;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // statement ::= def_stmnt 
            {
              Boolean RESULT =null;
		int dsleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int dsright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Boolean ds = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		RESULT = ds;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // statement ::= for_stmnt 
            {
              Boolean RESULT =null;
		int flleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int flright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Boolean fl = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		RESULT = fl;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // statement ::= bool_stmnt 
            {
              Boolean RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Boolean b = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		RESULT = b;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // statement ::= empty_stmnt 
            {
              Boolean RESULT =null;
		int esleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int esright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Boolean es = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		RESULT = es;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // statement ::= import_stmnt 
            {
              Boolean RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Boolean s = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		RESULT = s;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // program ::= 
            {
              Boolean RESULT =null;
		RESULT = true;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Boolean start_val = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // program ::= program statement 
            {
              Boolean RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Boolean p = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Boolean e = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		RESULT = p && e;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}
