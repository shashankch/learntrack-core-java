# LearnTrack [Student & Course Management System]

Console-based app built using Core Java:
- Classes, objects, constructors(overloading), static/instance members, and enums
- Encapsulation (private fields + getters/setters)
- Inheritance + basic polymorphism (`Person` -> `Student` / `Trainer`)
- Collections (`ArrayList`) for in-memory storage
- Basic exception handling with custom exceptions
- Advanced topics like concurrency, streams, or heavy design patterns (Not Included)
- Menu-driven console UI

## Directory Structure

```
LearnTrack/
├── README.md
├── docs/
│   ├── Design_Notes.md
│   ├── JVM_Basics.md
│   ├── Setup_Instructions.md
│   ├── class.txt
│   └── diagram.png
└── src/
    └── com/
        └── edtech/
            └── learntrack/
                ├── constants/
                │   ├── AppConstants.java
                │   └── MenuOptions.java
                ├── entity/
                │   ├── Course.java
                │   ├── Enrollment.java
                │   ├── Person.java
                │   ├── Student.java
                │   └── Trainer.java
                ├── enums/
                │   ├── EnrollmentStatus.java
                │   ├── CourseStatus.java
                │   └── StudentStatus.java
                ├── exception/
                │   ├── EntityNotFoundException.java
                │   └── InvalidInputException.java
                ├── repository/
                │   ├── CourseRepository.java
                │   ├── EnrollmentRepository.java
                │   └── StudentRepository.java
                ├── service/
                │   ├── CourseService.java
                │   ├── EnrollmentService.java
                │   └── StudentService.java
                ├── ui/
                │   └── Main.java
                └── util/
                    ├── IdGenerator.java
                    └── InputValidator.java
```

## How to Compile & Run (Terminal)

From the `LearnTrack/` folder:

```bash
# compile (outputs .class files into out/)
mkdir -p out
javac -d out $(find src -name "*.java")

# run
java -cp out com.edtech.learntrack.ui.Main
```

## Features

### Student Management
- Add new student (with/without email)
- View all students
- Search by ID
- Deactivate a student (soft delete)
- Update email

### Course Management
- Add course
- View all courses
- Activate/Deactivate a course

### Enrollment Management
- Enroll a student into a course
- View enrollments for a student
- Mark enrollment as COMPLETED / CANCELLED

## Class Diagram
- `docs/class.txt`
- `docs/diagram.png`


## Docs
- `docs/Setup_Instructions.md`
- `docs/JVM_Basics.md`
- `docs/Design_Notes.md`