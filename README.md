# TapTempo: Clojure version

A command line tap tempo. Hit a key with style and you'll get the BPM.

Clojure implementation of https://github.com/moleculext/taptempo. It started as a joke on LinuxFR where everybody implements its own TapTempo using its favorite language.

## Usage

Run with

~~~~
  lein run
~~~~

Compile with:

~~~~
  lein uberjar
~~~~

and run with:

~~~~
  java -jar target/taptempo-0.1.0-SNAPSHOT-standalone.jar
~~~~

Options are:

~~~~
  java -jar target/taptempo-0.1.0-SNAPSHOT-standalone.jar --help
  Usage: TapTempo [options]

  Options:
    -p, --precision PREC  0  change the number of decimal for the tempo. The default is 0 decimal places, the max is 5 decimals
    -r, --reset-time T    5  change the time in seconds to reset the calculation. The default is 5 seconds
    -s, --sample-size N   5  change the number of samples needed to calculate the tempo. The default is 5 samples
    -v, --version            print the version number
    -h, --help
~~~~

Unit test can be launch with:

~~~~
  lein test
~~~~

## License

Copyright © 2018 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
