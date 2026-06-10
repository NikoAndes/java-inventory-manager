# Java Inventory Manager

![Language](https://img.shields.io/badge/Language-Java-orange) ![Storage](https://img.shields.io/badge/Storage-CSV-lightgrey) ![Pattern](https://img.shields.io/badge/Pattern-OOP-blue) ![License](https://img.shields.io/badge/License-MIT-yellow) ![Status](https://img.shields.io/badge/Status-Active-brightgreen)

## Overview

Java console application for managing product inventory. Supports full CRUD operations (Create, Read, Update, Delete) on products, persists data to CSV files using Java I/O, and provides low-stock alerts. Designed around OOP principles with a clear class hierarchy and separation of concerns.

## Features

- Add, view, edit, and delete products from inventory
- CSV file persistence (data survives application restart)
- Low-stock alert system with configurable threshold
- Search and filter products by name, category, or stock level
- Generate inventory summary report (total value, items per category)
- Input validation and error handling throughout
- Console-based interactive menu

## Tech Stack

| Component | Technology |
|-----------|------------|
| Language | Java 17 |
| Data Persistence | CSV files (Java I/O) |
| Design Pattern | OOP, Repository pattern |
| Build Tool | Maven |
| Testing | JUnit 5 |
| IDE | IntelliJ IDEA |

## Project Structure

```
java-inventory-manager/
├── src/
│   ├── main/java/com/nikoandes/inventory/
│   │   ├── Main.java                  # Entry point
│   │   ├── model/
│   │   │   └── Product.java              # Product entity
│   │   ├── repository/
│   │   │   ├── ProductRepository.java    # CRUD interface
│   │   │   └── CsvProductRepository.java # CSV implementation
│   │   ├── service/
│   │   │   └── InventoryService.java     # Business logic
│   │   ├── ui/
│   │   │   └── ConsoleMenu.java          # Console UI
│   │   └── util/
│   │       └── CsvParser.java            # CSV read/write helper
│   └── test/java/com/nikoandes/inventory/
│       └── InventoryServiceTest.java
├── data/
│   └── products.csv                  # Sample inventory data
├── pom.xml
├── .gitignore
├── LICENSE
└── README.md
```

## How to Run

### Prerequisites
- Java 17+
- Maven 3.8+

### Build and Run
```bash
git clone https://github.com/NikoAndes/java-inventory-manager.git
cd java-inventory-manager
mvn clean package
java -jar target/inventory-manager.jar
```

### Sample Interaction
```
=== Java Inventory Manager ===
1. Add product
2. View all products
3. Search product
4. Update product
5. Delete product
6. Low stock report
7. Inventory summary
0. Exit

Select option: 2

ID  | Name          | Category    | Stock | Price
----|---------------|-------------|-------|------
001 | Arduino Nano  | Electronics |  15   | $4.50
002 | DHT22 Sensor  | Sensors     |   3   | $3.20  [LOW STOCK]
003 | L298N Driver  | Motors      |   8   | $2.80
```

## What I Learned

- Java file I/O: reading and writing CSV with BufferedReader/PrintWriter
- Repository pattern for decoupling data access from business logic
- Interface-based design for flexible data source switching
- Input validation and exception handling in console applications
- OOP principles: encapsulation, abstraction, polymorphism
- Writing meaningful unit tests with JUnit 5 assertions

## Future Improvements

- [ ] Replace CSV with SQLite database
- [ ] Add JavaFX GUI for visual inventory management
- [ ] Implement barcode scanning simulation
- [ ] Add multi-user support with authentication
- [ ] Export reports to PDF with Apache PDFBox
- [ ] Implement import from external supplier catalogs

## Author

**Nicolas Isaza Sierra** — [GitHub @NikoAndes](https://github.com/NikoAndes)

Mechatronics engineering student | Java developer | UMNG, Colombia

## License

MIT License — see [LICENSE](LICENSE) for details.
