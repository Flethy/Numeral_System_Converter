# Numeral_System_Converter

To convert a fractional number to another base, we should use the algorithm described below.

To convert a number from one base to another, first, we need to convert it to decimal if it’s not decimal yet, and only then convert it to another base.

To convert the number into decimal, we need to:

   - Split the number into two parts: integer and fractional;
   - Convert the integer part into decimal using the method parseInt() (if base is not equal to 1);
   - Convert the fractional part into decimal using the following formula:
   ![Alt-текст](https://i.yapx.ru/JAT3F.png)
   
   To convert a decimal fractional number into any other base, we need to:

   - Split the decimal number into two parts: integer and fractional;
   - Convert the integer part into the new base;
   - Convert the fractional part from decimal to any other base.

   Multiply the fractional part by the new base: 0.5168∗19=9.81920. The integer part of the result is the first digit (or letter if the integer part is greater than 9) in the fractional part of a number in the new base. In this case, the first digit in the fractional part is 9.

  Take the fractional part from the result of the multiplication and multiply it by the new base again: 0.8192∗19=15.56480. In this case, the second digit (literal) is f (15).
  
  User will input three lines:

   1. The source radix;
   2. The source number;
   3. The target radix.
