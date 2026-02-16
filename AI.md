# AI Usage Log
This document tracks the usage of AI tools during the development of Ding.

## Tools Used
- ChatGPT 5.2: Website chat interface
- Copilot: VS Code integrated tab (Model: Auto, Mode: Agent)

## Tasks involved
### 1. A-MoreOOP
- ChatGPT was used to discuss different approaches to refactoring the code. 
- **What worked**: ChatGPT is able to point out which designs are more common practices, evalute tradeoffs between differrent approaches. It helped my decision-making process since I am a very indecisive person.
- **What didn't work**: Occasionally it suggests solutions that is over-engineered, and on prompting it reduces to very minimal designs. It takes some time to eventually reach a balance, but I guess this would be better than just reading through posts online.
- Time saved: Probably not that much if I simply ask a more experienced programmer.

### 2. A-Junit
- Copilot was used to generate testing methods for the Parser and Storage class.
- **What worked**: The test cases are very comprehensive and saved insane amount of time to coding them by hand. It also included some test cases where I would have bothered/thought of if I were to write them myself.
- **What didn't work**: The coding standard did not follow that required by the course but this is because I did not give even prompt.

### 3. Level-10
- Copilot was used to discuss different approaches to refactoring the code to work with the GUI. It was also used to modify the stylesheets and fxml files to match the new styling requested.
- **What worked**: Almost everything.
- **What didn't work**: The initial refactor plan was very convoluted. It takes more explicit prompting by suggesting my own plans for it to come up with something more reasonable. I think the model is trying to make minimal changes but that is not always the optimal.
- Time saved: Maybe frontend is really dead. I am going to learn some sophisticated animations and see if I still stand a chance against AI.

### 4. A-BetterGui
- Copilot was used to improve the GUI according to my requirements.
- **What worked**: Worked with a lot of effort
- **What didn't work**: I think AI becomes not as great once you require customisation in the frontend. The prompts need to 
be extremely clear for it to work well. For example: initially I wanted to add margin around the inputbox and send button. However, Copilot decided to adjust the anchor position which messed up the layout on resizing. I had to specifically ask it to add a wrapper.
- Time saved: Still saves lots of trouble in finding which part of the code the padding and margin are coming part. But this times it 
works like an experienced friend rather than a magician that finish everything with one click.
