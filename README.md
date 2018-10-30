# birthdaykata
Birthday Kata in functional style with kotlin

The goal is to try to implement birthday kata using a pure functional style

https://github.com/xpmatteo/birthday-greetings-kata



---

On purpose I won't use Arrow or other functional libraries

Step 1 - design

I identified the following morphisms:
filename -> ReadFile<CsvRow>   
from a filename I need a Monad to read CsvRow from the file

CsvRow -> Employee             
parsing the csv to a data type with all info

Date => (Employee -> Boolean)  
from a date I need a filter on Employee born on same date

template => (Employee -> Greeting)
from a template string returns the function that create the actual greeting text

Greeting -> Email<Greeting>
from greeting text I need a monad to send the emails
