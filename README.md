ClojureScript Resolver for Parcel Apps
----

[This project](https://github.com/kurtharriger/parcel-cljs-demo) demonstrates how to include ClojureScript in a Parcel based app (with working clojurescript repl) without this resolver.

This is next step in simplifying this process by creating a resolver that maps cljs file paths to the target output path instead of creating aliases and/or using /target/app.main.js as js path

The demo project is now moved to example folder and updated to use the resolver.

This resolver is not yet published to npm so you must first buld and install it locally first before running the demo.

# Build resolver

```
yarn install
yarn link
```

# Run example

```
cd example
yarn install
yarn link parcel-resolver-cljs

```
Then follow instructions in the example/README.md

# Hacking

By using yarn link you should be able to update this resolver without needing to reinstall the package in the example project.

Just run in the resovler to make changes
```
yarn  parcel watch
```

However when you rebuild the example parcel may use cached files...
I also found that special console sequences can clear terminal and console.log messages may not be visible.  To workaround this I delete the cache and pipe through cat as follows when rebuliding the example project

```
 rm -r .parcel-cache; yarn parcel index.html | cat
```


# Assumptions

This resolver hardcodes a few potentailly configurable assumptions..
clojure code is in /src/cljs/
When clojure source file wants to import file from typescript it needs to use @project/src/ts/file.ts instead of /src/ts/file.ts otherwise shadow-cljs attempts to resolve the file and fails.  There might be a better fix, but using an alias '@project' prevents shadow-cljs from attempting to resolve it and then this resolver will strip '@project' so that source file can be found correctly... 
This appears to work fine if the import is a local source file, but I'm not sure how this might work if the resource requires another resolver to be included.  