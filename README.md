# Queens

An iterative solver that solves the N queens problem as defined on Wikipedia: https://en.wikipedia.org/wiki/Eight_queens_puzzle.

Requires Java 1.7, use gradle to build and run. There's also an IntelliJ project.

## To build and run main
```
./gradlew --daemon run
```

## To build and run the tests
```
./gradlew --daemon test
```

## Benchmark of the concurrent implementation

Timings include the gradle runtime, its really meant mostly to check that the
concurrent implementation actually does something useful.

All run on macOS, best of 3 runs.

### N = 16

```
> i7 3930K @ 4Ghz, 6cores
# Using 12 threads.
Solving for N = 16
# Num solutions: 14772512
Total time: 34.324 secs
```

### N = 15

```
> i7 3930K @ 4Ghz, 6cores
# Using 1 threads.
Solving for N = 15
# Num solutions: 2279184
Total time: 28.617 secs
```

```
> i7 3930K @ 4Ghz, 6cores
# Using 12 threads.
Solving for N = 15
# Num solutions: 2279184
Total time: 5.829 secs
```

### N = 14

Note that this is run with the inital version which was still formatting the
solutions to a string.

```
> MBA 2.2Ghz i7, 2 cores
# Using 1 threads.
Solving for N = 14
# Num solutions: 365596
Total time: 9.082 secs
```

```
> MBA 2.2Ghz i7, 2 cores
# Using 4 threads.
Solving for N = 14
# Num solutions: 365596
Total time: 5.048 secs
```

```
> i7 3930K @ 4Ghz, 6cores
# Using 1 threads.
Solving for N = 14
# Num solutions: 365596
Total time: 6.939 secs
```

```
> i7 3930K @ 4Ghz, 6cores
# Using 12 threads.
Solving for N = 14
# Num solutions: 365596
Total time: 2.38 secs
```
