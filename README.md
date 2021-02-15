# Readability_Score
Everyone has their own personal reading history, and as we grow up, we are able to comprehend more and more complicated texts. But how do you estimate the level of difficulty of a given text, and how do you teach a computer to do that? In this project, you will find it out: this program determines how difficult the text is and for which age it is most suitable.

## Table of Contents
* [About this program](#about-this-program)
* [Technologies](#technologies)
* [Example](#example)
* [Regex reminder](#regex-reminder)

## About this program
This project is a solution to the problem of JetBrains Academy - **"Readability Score"**. The purpose of this project - learn to call programs from the command-line and get more experience with using regexes and Math class.

This program reads a text file by passing the file name through command line arguments and calculates readability indexes such as:
* [Automated readability index](https://en.wikipedia.org/wiki/Automated_readability_index)
* [Flesch–Kincaid readability tests](https://en.wikipedia.org/wiki/Flesch%E2%80%93Kincaid_readability_tests)
* [Simple Measure of Gobbledygook](https://en.wikipedia.org/wiki/SMOG)
* [Coleman–Liau index](https://en.wikipedia.org/wiki/Coleman%E2%80%93Liau_index)

and prints in console the calculated index and for what age it is most suitable.

## Technologies
- JDK 9+

## Example
The greater-than symbol followed by a space ( **>** ) represents the user input. Note that it's not part of the input.

```
> java Main in.txt  
The text is:  
This is the front page of the Simple English Wikipedia. Wikipedias are places where people work together to write encyclopedias in different languages. We use Simple English words and grammar here. The Simple English Wikipedia is for everyone! That includes children and adults who are learning English. There are 142,262 articles on the Simple English Wikipedia. All of the pages are free to use. They have all been published under both the Creative Commons License and the GNU Free Documentation License. You can help here! You may change these pages and make new pages. Read the help pages and other good pages to learn how to write pages here. If you need help, you may ask questions at Simple talk. Use Basic English vocabulary and shorter sentences. This allows people to understand normally complex terms or phrases.  
  
Words: 137  
Sentences: 14  
Characters: 687  
Syllables: 210  
Polysyllables: 17  
Enter the score you want to calculate (ARI, FK, SMOG, CL, all):
> all  
  
Automated Readability Index: 7.08 (about 13-year-olds).  
Flesch–Kincaid readability tests: 6.31 (about 12-year-olds).  
Simple Measure of Gobbledygook: 9.42 (about 15-year-olds).  
Coleman–Liau index: 10.66 (about 17-year-olds).  
```

## Regex reminder
| Regex           | Description |
|:---------------:|:------------|
|`.`              | The dot character matches any single character except for `\n` |
|`?`              | The question mark ? character means "the previous character can occur once or zero times in a string". |
|`[]`             | The **set** is written in square brackets, `[]`. For example, the set `"[abc]"` means that a single character `"a"`, `"b"` or `"c"` can match it.  |
|`[0-9]`          | The **range** of characters designated by the dash symbol `-`. For example the set`[0-9]` matches any digit from 0 to 9. | 
|`[-a-z]` `[A-Z-]`| To match the dash character itself, we should put it in the first or in the last position in the set. |
|`[\[\]]`         | The square brackets should always be escaped |
|`[^abc]`         | **Excepting characters** - The hat character `^` as the first character of the set defines which characters are not wanted. For example the set `[^abc]` matches everything except for "a", "b", "c". |
|"yes &#124; no"  | The vertical &#124; bar is used to match either the character sequences before or after the symbol. |
|`(b &#124; r)at | The vertical bar can be used together with parentheses that designate the boundaries of alternating substrings: everything that's in the parentheses is an optional substrings that can match the alternation block. |
|`\d`             | is any digit, short for `[0-9]` |
|`\s`             | is a whitespace character (including tab and newline), short for `[^\t\n\x0B\f\r]` |
|`\w`             | is an alphanumeric character (word), short for `[a-zA-Z_0-9]` |
|`\b`             | is a word boundary. it doesn't match any specific character, it rather matches a boundary between an alphanumeric character and a non-alphanumeric character (for example, a whitespace character) or a boundary of the string (the end or the start of it). This way, `"\ba"` matches all words (sequences of alphanumeric characters) starting with "a", `"a\b"` matches all words ending with "a", and `"\ba\b"` matches all separate "a" preceded and followed by non-alphanumeric characters. |
|`\D`             | is a non-digit, short for `[^0-9]` |
|`\S`             | is a non-whitespace character, short for `[^ \t\n\x0B\f\r]` |
|`\W`             | is a non-alphanumeric character, short for `[^a-zA-Z_0-9]` |
|`\B`             | is a non-word boundary. It matches the situation opposite to that one of the `\b` shorthand: it finds its match every time whenever there is no "gap" between alphanumeric characters. For example, `"a\B"` matches all words that start with "a". |
|`+`              | matches one or more repetitions of the preceding character |
|`*`              | matches zero or more repetitions of the preceding character |
|`{n}`            | matches exactly `n` repetitions of the preceding character |
|`{n,m}`          | matches at least `n` but not more than `m` repetitions of the preceding character |
|`{n,}`           | matches at least `n` repetitions of the preceding character |
|`{0,m}`          | matches no more than `m` repetitions of the preceding character |
|`{0,1}`          | the same as `?`, quantifier  -  that makes the preceding character optional |