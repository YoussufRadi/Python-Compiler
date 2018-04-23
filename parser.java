
//----------------------------------------------------
// The following code was generated by CUP v0.10k
// Mon Apr 23 20:01:45 EET 2018
//----------------------------------------------------

import java_cup.runtime.*;
import java.io.*;
import java.util.*;

/** CUP v0.10k generated parser.
  * @version Mon Apr 23 20:01:45 EET 2018
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\034\000\002\003\004\000\002\002\004\000\002\003" +
    "\002\000\002\004\003\000\002\004\003\000\002\004\003" +
    "\000\002\004\003\000\002\004\003\000\002\004\003\000" +
    "\002\005\006\000\002\006\005\000\002\006\005\000\002" +
    "\007\010\000\002\010\007\000\002\013\005\000\002\013" +
    "\004\000\002\012\004\000\002\012\003\000\002\011\010" +
    "\000\002\011\007\000\002\014\003\000\002\014\003\000" +
    "\002\014\004\000\002\014\004\000\002\015\005\000\002" +
    "\015\006\000\002\015\007\000\002\015\007" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\067\000\016\002\uffff\003\uffff\007\uffff\014\uffff\030" +
    "\uffff\032\uffff\001\002\000\016\002\013\003\016\007\006" +
    "\014\005\030\012\032\014\001\002\000\004\011\067\001" +
    "\002\000\006\023\041\032\040\001\002\000\016\002\ufffd" +
    "\003\ufffd\007\ufffd\014\ufffd\030\ufffd\032\ufffd\001\002\000" +
    "\016\002\ufffc\003\ufffc\007\ufffc\014\ufffc\030\ufffc\032\ufffc" +
    "\001\002\000\016\002\ufffe\003\ufffe\007\ufffe\014\ufffe\030" +
    "\ufffe\032\ufffe\001\002\000\004\032\024\001\002\000\004" +
    "\002\000\001\002\000\004\055\021\001\002\000\016\002" +
    "\001\003\001\007\001\014\001\030\001\032\001\001\002" +
    "\000\016\002\ufff9\003\ufff9\007\ufff9\014\ufff9\030\ufff9\032" +
    "\ufff9\001\002\000\016\002\ufffa\003\ufffa\007\ufffa\014\ufffa" +
    "\030\ufffa\032\ufffa\001\002\000\016\002\ufffb\003\ufffb\007" +
    "\ufffb\014\ufffb\030\ufffb\032\ufffb\001\002\000\006\025\023" +
    "\026\022\001\002\000\016\002\ufff6\003\ufff6\007\ufff6\014" +
    "\ufff6\030\ufff6\032\ufff6\001\002\000\016\002\ufff7\003\ufff7" +
    "\007\ufff7\014\ufff7\030\ufff7\032\ufff7\001\002\000\004\063" +
    "\025\001\002\000\006\032\026\064\027\001\002\000\006" +
    "\064\ufff0\066\035\001\002\000\004\072\033\001\002\000" +
    "\004\064\031\001\002\000\004\072\032\001\002\000\016" +
    "\002\uffef\003\uffef\007\uffef\014\uffef\030\uffef\032\uffef\001" +
    "\002\000\016\002\uffee\003\uffee\007\uffee\014\uffee\030\uffee" +
    "\032\uffee\001\002\000\004\064\ufff1\001\002\000\004\032" +
    "\036\001\002\000\006\064\ufff2\066\035\001\002\000\004" +
    "\064\ufff3\001\002\000\012\034\045\056\044\057\046\072" +
    "\050\001\002\000\004\032\042\001\002\000\004\072\043" +
    "\001\002\000\016\002\uffe8\003\uffe8\007\uffe8\014\uffe8\030" +
    "\uffe8\032\uffe8\001\002\000\010\004\uffec\032\uffec\055\066" +
    "\001\002\000\004\027\056\001\002\000\010\004\uffed\032" +
    "\uffed\055\055\001\002\000\006\004\052\032\051\001\002" +
    "\000\016\002\uffe9\003\uffe9\007\uffe9\014\uffe9\030\uffe9\032" +
    "\uffe9\001\002\000\004\072\054\001\002\000\004\072\053" +
    "\001\002\000\016\002\uffe6\003\uffe6\007\uffe6\014\uffe6\030" +
    "\uffe6\032\uffe6\001\002\000\016\002\uffe7\003\uffe7\007\uffe7" +
    "\014\uffe7\030\uffe7\032\uffe7\001\002\000\006\004\uffeb\032" +
    "\uffeb\001\002\000\004\063\061\001\002\000\004\072\060" +
    "\001\002\000\016\002\ufff4\003\ufff4\007\ufff4\014\ufff4\030" +
    "\ufff4\032\ufff4\001\002\000\004\004\062\001\002\000\004" +
    "\066\063\001\002\000\004\004\064\001\002\000\004\064" +
    "\065\001\002\000\004\072\ufff5\001\002\000\006\004\uffea" +
    "\032\uffea\001\002\000\004\010\070\001\002\000\004\012" +
    "\071\001\002\000\016\002\ufff8\003\ufff8\007\ufff8\014\ufff8" +
    "\030\ufff8\032\ufff8\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\067\000\004\003\003\001\001\000\016\004\014\005" +
    "\010\006\006\010\007\011\017\015\016\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\012" +
    "\027\001\001\000\004\013\033\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\013\036\001\001\000\002\001\001\000\004\014\046\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\007\056\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001" });

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
          case 27: // while_stmnt ::= LP ID bool_expr INT SC 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(11/*while_stmnt*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // while_stmnt ::= LP ID bool_expr ID SC 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(11/*while_stmnt*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // while_stmnt ::= LP NT ID SC 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(11/*while_stmnt*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // while_stmnt ::= LP ID SC 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(11/*while_stmnt*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // bool_expr ::= LT AO 
            {
              Boolean RESULT = null;

              CUP$parser$result = new java_cup.runtime.Symbol(10/*bool_expr*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // bool_expr ::= GT AO 
            {
              Boolean RESULT = null;

              CUP$parser$result = new java_cup.runtime.Symbol(10/*bool_expr*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // bool_expr ::= LT 
            {
              Boolean RESULT = null;

              CUP$parser$result = new java_cup.runtime.Symbol(10/*bool_expr*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // bool_expr ::= GT 
            {
              Boolean RESULT = null;

              CUP$parser$result = new java_cup.runtime.Symbol(10/*bool_expr*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // def_stmnt ::= DF ID LB RB SC 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(7/*def_stmnt*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // def_stmnt ::= DF ID LB params RB SC 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(7/*def_stmnt*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // params ::= ID 
            {
              Boolean RESULT = null;

              CUP$parser$result = new java_cup.runtime.Symbol(8/*params*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // params ::= ID params_help 
            {
              Boolean RESULT = null;

              CUP$parser$result = new java_cup.runtime.Symbol(8/*params*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // params_help ::= FA ID 
            {
              Boolean RESULT = null;

              CUP$parser$result = new java_cup.runtime.Symbol(9/*params_help*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // params_help ::= FA ID params_help 
            {
              Boolean RESULT = null;

              CUP$parser$result = new java_cup.runtime.Symbol(9/*params_help*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // for_stmnt ::= LP ID IN range_fn SC 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(6/*for_stmnt*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // range_fn ::= RG LB INT FA INT RB 
            {
              Boolean RESULT = null;

              CUP$parser$result = new java_cup.runtime.Symbol(5/*range_fn*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // bool_stmnt ::= ID AO FL 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(4/*bool_stmnt*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // bool_stmnt ::= ID AO TR 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(4/*bool_stmnt*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // import_stmnt ::= FR PK IM FC 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(3/*import_stmnt*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // statement ::= error 
            {
              Boolean RESULT = null;
		RESULT = false;
              CUP$parser$result = new java_cup.runtime.Symbol(2/*statement*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // statement ::= while_stmnt 
            {
              Boolean RESULT = null;
		int wsleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int wsright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		Boolean ws = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		RESULT = ws;
              CUP$parser$result = new java_cup.runtime.Symbol(2/*statement*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // statement ::= def_stmnt 
            {
              Boolean RESULT = null;
		int dsleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int dsright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		Boolean ds = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		RESULT = ds;
              CUP$parser$result = new java_cup.runtime.Symbol(2/*statement*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // statement ::= for_stmnt 
            {
              Boolean RESULT = null;
		int flleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int flright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		Boolean fl = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		RESULT = fl;
              CUP$parser$result = new java_cup.runtime.Symbol(2/*statement*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // statement ::= bool_stmnt 
            {
              Boolean RESULT = null;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		Boolean b = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		RESULT = b;
              CUP$parser$result = new java_cup.runtime.Symbol(2/*statement*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // statement ::= import_stmnt 
            {
              Boolean RESULT = null;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		Boolean s = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		RESULT = s;
              CUP$parser$result = new java_cup.runtime.Symbol(2/*statement*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // program ::= 
            {
              Boolean RESULT = null;
		RESULT = true;
              CUP$parser$result = new java_cup.runtime.Symbol(1/*program*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= program EOF 
            {
              Object RESULT = null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Boolean start_val = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = new java_cup.runtime.Symbol(0/*$START*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // program ::= program statement 
            {
              Boolean RESULT = null;
		int pleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Boolean p = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		Boolean e = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		RESULT = p && e;
              CUP$parser$result = new java_cup.runtime.Symbol(1/*program*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

