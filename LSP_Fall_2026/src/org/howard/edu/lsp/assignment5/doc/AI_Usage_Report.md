# AI Usage and External References Report — Assignment 5


## 1. External References Used
- **Oracle Java SE 17 Standard Library Documentation**:
  - `java.util.List`: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html
  - `java.util.Collections`: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collections.html
  - **How it helped**: Referenced `Collections.max()`, `Collections.min()`, and `Collections.sort()` to implement `largest()`, `smallest()`, and the ascending sorted output required by `toString()`.


## 2. AI Assistance (Google Gemini)
- **AI Tool**: Google Gemini
- **Summary of Assistance**:
  - Assisted in designing an encapsulated `IntegerSet` class utilizing `ArrayList<Integer>`.
  - Verified defensive non-mutating behavior for set operations (`union`, `intersect`, `diff`, `complement`, `symmetricDiff`) so new independent `IntegerSet` instances are returned without modifying operands.
  - Verified complete Javadoc comments across the class and all required public methods according to course specifications.
  - Guided step-by-step package setup and Git staging within Eclipse IDE.


### AI Conversation Transcript:
> **User Prompt**:
> Good morning, Gemini. I have attached my Assignment #5 to this prompt. Please verify that my package setup is correct  given the instructions


> **AI Response**:
> Good morning! I’ve reviewed your screenshots, and your package setup in Eclipse is completely correct and fully compliant with the Assignment #5 specifications.Here is the confirmation based on your setup:Source Root: Your files are properly anchored under the src/ source folder.Package Path: org.howard.edu.lsp.assignment5 is declared and spelled accurately, with both IntegerSet.java and Driver.java placed directly inside it.File Declarations: Both Java files correctly lead with:Javapackage org.howard.edu.lsp.assignment5;
Documentation Subfolder: The doc/ folder is nested directly within the package, and AI_Usage_Report.md is positioned inside it with the required naming and casing.Build State: There are no red error markers or classpath issues visible in the Package Explorer.Your setup is ready for testing. You can run Driver.java (Run As $\rightarrow$ 1 Java Application) to verify your 92/92 functional test output, then stage and push those three files to your repository!
