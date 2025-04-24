# CSC120: Object-Oriented Programming
## FINAL PROJECT Checklist

Listed below are various aspects of the assignment.  When you turn in your work, please indicate the status of each item

- **YES** : indicates that the item is fully complete
- **NO** : indicates that the item is not attempted
- **PART** : indicates that the item is attempted but not fully complete


## Core Game Mechanics:

_____ Game initializes with default startup values (starting funds, products, time in business, etc.)

_____ Game tracks time progression (e.g. years in business, advancing after each cycle/turn)

_____ Game ends appropriately under one of the three win/loss conditions

_____ Player can do standard actions each year (e.g., expand, repay debt, reinvest)

### Level 1: Product System

_____ Abstract `Product` class or interface created with required attributes (e.g., name, price, production cost, location)

_____ Subclass(es) of `Product` defined with specific implementations

_____ Player can manage products (e.g., add new product, adjust price, produce item)

### Level 2: Financial Management System

_____ `FinancialStatement` class tracks revenue, expenses, assets, and liabilities

_____ Methods for financial operations implemented (`addRevenue(...)`, `addExpense(...)`, `repayLoan(...)`, etc.)

_____ Yearly financial statements are generated and accessible for reference

_____ Game correctly calculates profitability and financial status (e.g., bankruptcy, net income)

### Level 3: Events & Strategy

_____ Game can generate random events or roadblocks (e.g., disasters, market shifts)

_____ Events affect business variables meaningfully (e.g., raise production costs, affect revenue)

_____ Player is offered choices or must respond strategically to events

### Level 4: Competition

___ `Competitor` class (or similar) implemented with key attributes (e.g., name, market share, pricing, products)

___ Competitor behavior is simulated (e.g., adjusts prices, grows over time)

___ Market dynamics consider both player and competitor activity (e.g., pricing competition, market share shifts)

___ Player can take strategic actions against competitors (e.g., undercut pricing, expand to new markets, innovate)



## General Items:

_____ 4 pts: Programs compile without errors or warnings

_____ 2 pts: Executes fully & consistently without crashing (exception/freeze)

_____ 2 pt: Complies with style guidelines (missing items 1 pt each):

      ___YES__ Classes & class members all have Javadoc header comments

      ___YES__ Clear and consistent indentation of bracketed sections

      ___YES__ Adheres to Java conventions on naming & capitalization

      ___YES__ Methods & variables all have clear and accurate names

      ___YES__ Methods avoid confusing side effects

_____ 1 pt: All required files included with submission (including completed `rubric.md` file)

_____ 1 pt: `reflection.md` contains your reflection on the assignment
