## Magic Bitboard Core

This project implements the core functions for building a highly efficient move generator and chess engine. It utilizes [Magic Bitboards](https://www.chessprogramming.org/Magic_Bitboards) for sliding pieces, enabling **fast attack computations**. 

## Features
✅ Magic Bitboard lookup tables for **bishops, rooks and queens**  
✅ Efficient **pseudo-legal move generation**  
✅ **Bitwise operations** for ultra-fast computations  
✅ Fully **commented** and **tested**  

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
#### Example: King Attacks
```bash
int square = 36;
long occupied = 0x0000001008000000L; // Some occupied squares
long attacks = Queen.getAttacks(square, occupied);
printBoard(attacks);
```

#### Printed Output:
```bash
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
 │     ├── attacks/        # Move generation logic
 │     ├── lookup/         # Precomputed attack tables
 │     ├── utils/          # Utility functions (e.g., bitboard printing)
 │     ├── pieces/         # Piece-specific attack logic
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
