# Mutant Analist App
In this app you will be able to know if you have MUTANT DNA! (Awesome we know...)

## Required Setup
* Java 1.8

#Rest apis:

* ../mutant
```
arg JSON , format: { "dna": {"ATGCGA","CCGTGC","TTATGT","CCCCTA","AAAAA","TCACTG" }

returns ->          OK          (You are a mutant)
                    FORBIDDEN   (You are a human)
```

* ../mutant/list      no arg
```
returns ->          list of sequences in JSON format

```

* ../stats            no arg
```
returns ->          stats of mutant && humans stored in JSON format

```

#APP INFO

Prod Url : https://pacific-sands-32569.herokuapp.com/

Important information: The dynos of heroku might be hibernating, with one request the app will be up in a few minutes
