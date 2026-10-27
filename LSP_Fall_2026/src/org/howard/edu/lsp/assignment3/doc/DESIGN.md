1. **How was your Assignment #2 solution organized?**
   - It was structured procedurally with execution logic combined in a single script/class.
2. **What design changes did you make for Assignment #3?**
   - Decoupled the pipeline stages into distinct single-responsibility object-oriented classes.
3. **What classes or abstractions did you introduce and why?**
   - `Employee`: Represents data state.
   - `ETLExtractor`: Handles input parsing.
   - `ETLTransformer`: Manages business rules/transformations.
   - `ETLLoader`: Manages file writing and output.
   - `ETLPipeline`: Orchestrates workflow.
4. **How did you divide responsibilities differently?**
   - Separated data management, file I/O, and business transformation into isolated components rather than one monolithic block.
5. **Why do you believe your Assignment #3 design is an improvement?**
   - It increases modularity, readability, maintainability, and reusability.
   
   **AI / Resource Documentation:**
- Gemini & Claude were used