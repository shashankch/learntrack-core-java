# Design Notes

## Why ArrayList instead of array?
- Arrays have **fixed size**. If you need to add more students/courses later, you would need to create a new array.
- `ArrayList` grows dynamically and is easier for CRUD-style operations (add/list/search) in a beginner project.

## Where static members are used and why?
- `IdGenerator` uses **static counters and static methods** to generate unique IDs without creating an object.
  This is a simple, clear use-case for `static`.

## Where inheritance is used and what we gained?
- `Person` is a base class with common fields (`id`, `firstName`, `lastName`, `email`).
- `Student` (and `Trainer`) extend `Person` to reuse those fields and behaviors.
- Overriding `getDisplayName()` shows basic polymorphism: calling the same method can behave differently based on object type.

## Separation of concerns (clean code)
- `entity/` contains data models only (fields + constructors + getters/setters).
- `repository/` stores in-memory lists (ArrayList) and basic find operations.
- `service/` contains business rules and validations.
- `Main` focuses on menu + input + calling services.
