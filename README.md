## Magic Bitboard Core

Magic Bitboard Core is a high-performance library for efficient attack computations in chess engines. It leverages [Magic Bitboards](https://www.chessprogramming.org/Magic_Bitboards) to enable **fast attack lookups for sliding pieces**.

## Features
✅ Magic Bitboard lookup tables for **bishops, rooks and queens**  
✅ **Efficient attack computations** for building fast move generators in chess engines  
✅ **Bitwise operations** for ultra-fast computations  
✅ Fully **documented** and **unit-tested for reliability**  

## Installation & Dependencies
This project is built with Java and Maven. To use it, clone the repository and build with Maven:
### Requirements:
- Java 11+
- Maven

### Clone and Build:
```bash
git clone https://github.com/T-Lak/MagicBitboardCore.git
cd MagicBitboardCore
mvn clean install
```

### Usage
To compute pseudo-legal attacks for different pieces:
#### Example: Queen Attacks
```java
int square = 36;
long occupied = 0x0000001008000000L; // Some occupied squares
long attacks = Queen.getAttacks(square, occupied);
printBoard(attacks);
```

#### Printed Output:
```plaintext
0 1 0 0 1 0 0 1 |8
0 0 1 0 1 0 1 0 |7
0 0 0 1 1 1 0 0 |6
1 1 1 1 0 1 1 1 |5
0 0 0 1 1 1 0 0 |4
0 0 0 0 1 0 1 0 |3
0 0 0 0 1 0 0 1 |2
0 0 0 0 1 0 0 0 |1
----------------+
A B C D E F G H
```

#### Project Structure
```bash
/src
 ├── main/java/bitboard
 │     ├── attacks/        # Attack computation for all pieces
 │     ├── lookup/         # Precomputed attack tables
 │     ├── utils/          # Utility functions (e.g., bitboard printing)
 ├── test/java/bitboard    # Unit tests
```

## Testing
Run unit tests using Maven:
```bash
mvn test
```

## Contributing
If you find any issues or optimizations, feel free to open an issue or submit a pull request!

## Attribution
This project utilizes precomputed magic numbers from [**Shallow Blue**](https://github.com/GunshipPenguin/shallow-blue).

## License
This project is licensed under the MIT License.
