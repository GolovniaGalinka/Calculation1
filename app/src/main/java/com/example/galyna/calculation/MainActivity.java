package com.example.galyna.calculation;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
ArrayList aa=new ArrayList();
    Button a;

EditText editText;
    double r=0;
    String result=new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.editText);

       }
    public void onClick(View v){

        switch(v.getId()){
            case R.id.sin:
                editText.append("sin(");
                result+="2s";
                aa.add("Math.sin(");
              //  System.out.println(aa.size());
                break;
            case R.id.cos:
                editText.append("cos(");
                aa.add("Math.cos(");
               result+=("2c");
                break;
            case R.id.tan:
                editText.append("tan(");
                result+="2t";

                break;

            case R.id.log:
                editText.append("log(");
                result+="2L";
                break;
            case R.id.e:
                editText.append("e");
                result+="2e";
                break;
            case R.id.pi:
                editText.append("pi");
                result+="2p";

                break;

            case R.id.square:
                editText.append("^");
                result+="^";

                break;
            case R.id.root:
                editText.append("sqrt(");

                result+="2S";
                break;
            case R.id.seven:
                editText.append("7");
                result+="7";
                break;
            case R.id.nine:
                editText.append("9");
                result+="9";
                break;
            case R.id.eight:
                editText.append("8");
                result+="8";
                break;
            case R.id.six:
                editText.append("6");
                result+="6";
                break;
            case R.id.five:
                editText.append("5");
                result+="5";
                break;
            case R.id.four:
                editText.append("4");
                result+="4";
                break;
            case R.id.three:
                editText.append("3");
                result+="3";
                break;
            case R.id.two:
                editText.append("2");
                result+="2";
                break;
            case R.id.one:
                editText.append("1");
                result+="1";
                break;
            case R.id.zero:
                editText.append("0");
                result+="0";
                break;
            case R.id.plus:
                editText.append("+");
                result+="+";
                break;
            case R.id.point:
                editText.append(".");
                result+=".";
                break;
            case R.id.minus:
                editText.append("-");
                result+="-";
                break;
            case R.id.multiple:
                editText.append("*");
                result+="*";
                break;
            case R.id.split:
                editText.append("/");
                result+="/";
                break;
            case R.id.duzka1:
                editText.append("(");
                result+="(";
                break;
            case R.id.duzka2:
                editText.append(")");
                result+=")";
                break;
            case R.id.equals:
                try{
                double ss= eval(result);
               String qq1=Double.toString(ss);

                                editText.setText(qq1);}
                catch (Exception e){
                    editText.setText("wrong");
                    result="";
                }

                break;
            case R.id.button7:
result="";
                editText.setText("");
                break;

        }

    }

    public static double eval(final String str) {
        class Parser {
            int pos = -1, c;

            void eatChar() {
                c = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            void eatSpace() {
                while (Character.isWhitespace(c)) eatChar();
            }

            double parse() {
                eatChar();
                double v = parseExpression();
                if (c != -1) throw new RuntimeException("Unexpected: " + (char)c);
                return v;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor | term brackets
            // factor = brackets | number | factor `^` factor
            // brackets = `(` expression `)`

            double parseExpression() {
                double v = parseTerm();
                for (;;) {
                    eatSpace();
                    if (c == '+') { // addition
                        eatChar();
                        v += parseTerm();
                    } else if (c == '-') { // subtraction
                        eatChar();
                        v -= parseTerm();
                    } else {
                        return v;
                    }
                }
            }

            double parseTerm() {
                double v = parseFactor();
                for (;;) {
                    eatSpace();
                    if (c == '/') { // division
                        eatChar();
                        v /= parseFactor();
                    } else if (c == '*' || c == '(') { // multiplication
                        if (c == '*') eatChar();
                        v *= parseFactor();
                    } else {
                        return v;
                    }
                }
            }

            double parseFactor() {
                double v;
                boolean negate = false;
                eatSpace();
                if (c == '+' || c == '-') { // unary plus & minus
                    negate = c == '-';
                    eatChar();
                    eatSpace();
                }
                if (c == '(' ) { // brackets
                    eatChar();
                    v = parseExpression();
                    if (c == ')') eatChar();
                } else { // numbers
                    StringBuilder sb = new StringBuilder();
                    while ((c >= '0' && c <= '9') || c == '.') {
                        sb.append((char)c);
                        eatChar();
                    }
                    if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char)c);
                    v = Double.parseDouble(sb.toString());
                }
                eatSpace();
                if (c == '^') { // exponentiation
                    eatChar();
                    v = Math.pow(v, parseFactor());}
                    if (c == 's') {
                        eatSpace();
                        eatChar();

                        v = Math.sin(parseFactor());
                        if(c==')'){
                            eatSpace();
                            eatChar();
                        }
                    }
                if (c == 'c') {
                    eatChar();

                    v = Math.cos(parseFactor());
                    if(c==')'){
                        eatSpace();
                        eatChar();
                    }
                }
                if (c == 'p') {

                    eatChar();
                    v = Math.PI;
                }
                if (c == 'e') {

                    eatChar();
                    v = Math.E;
                }
                if (c == 'S') {
                    eatChar();
                   v = Math.sqrt(parseFactor());
                    if(c==')'){
                        eatSpace();
                        eatChar();
                    }
                }
                if (c == 't') {
                    eatChar();

                    v = Math.tan(parseFactor());
                    if(c==')'){
                        eatSpace();
                        eatChar();
                    }
                }
                if (c == 'L') {
                    eatChar();

                    v = Math.log(parseFactor());
                    if(c==')'){
                        eatSpace();
                        eatChar();
                    }
                } if (c == 'l') {
                    eatChar();

                    v = Math.log10(parseFactor());
                    if(c==')'){
                        eatSpace();
                        eatChar();
                    }
                }

                if (negate) v = -v; // unary minus is applied after exponentiation; e.g. -3^2=-9
                return v;
            }
        }
        return new Parser().parse();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
