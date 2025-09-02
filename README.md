# SpringLens-Reflection

Reflection is one of those features that feels magical when you first
meet it, but behind the scenes it's just the JVM giving you a flashlight
and a set of tools to peek inside classes **at runtime**.

This repo is both code + guide. Run the code in while reading this file.
Each section here explains the *concepts, scenarios, and caveats*. Each
code package shows those ideas in action.


## 0) Why Reflection? (The Story)

Imagine you're writing a framework.
You don't know which classes your users will write, what constructors
they'll provide, or which methods they'll expose. You only discover that
at **runtime**.

Reflection is the JVM's built-in "X-ray & remote control" that lets you:

-   **Inspect** any class that's been loaded (its fields, methods,
    constructors, annotations).
-   **Read & write** field values (even private ones).
-   **Call** methods by name and signature, even if you didn't code them
    directly.
-   **Instantiate** objects through chosen constructors.

Frameworks like **Spring**, **Hibernate**, **Jackson**, JUnit... all
lean on reflection heavily. That's how they can wire dependencies, map
JSON, auto-detect tests, or hook up HTTP routes without you doing `new`
everywhere.

↪️ In short: **Reflection trades compile-time certainty for runtime
flexibility.**

### When it shines

-   Dependency Injection containers (Spring).
-   Serialization / Deserialization (Jackson, Gson).
-   Plugin systems / dynamic module loading (lombok).
-   Testing frameworks (JUnit, Mockito).
-   Generic tools like ORMs, mappers, routers.

------------------------------------------------------------------------

## 1) Mental Model (How the JVM Sees Your Code)

1.  You compile `.java` → `.class`. These contain **metadata**: names,
    types, modifiers, annotations.
2.  A **ClassLoader** loads the bytes and creates a `Class<?>` object =
    the runtime "mirror" of your type.
3.  That `Class<?>` lets you explore **declared members** (fields,
    methods, constructors).
4.  From those handles, you can **invoke, set, get, or instantiate**.

``` mermaid
flowchart LR
    A[.class file] --> B[ClassLoader]
    B --> C[Class<?> mirror]
    C -->|methods/fields/ctors| D[Reflective Handles]
    D -->|invoke/get/set/newInstance| E[Real Object Behavior]
```

------------------------------------------------------------------------

## 2) Methods: See, Describe, Invoke
###  Code [Reflect Methods ](https://github.com/hanin-mohamed/SpringLens-Reflection/tree/main/Reflection/src/main/java/com/springlens/reflection/a10_methods)

### Key ideas

-   `getDeclaredMethods()` → all methods declared in that class
    (including `private`).
-   `getMethods()` → only `public`, including inherited ones.
-   Look up specific methods with
    `getDeclaredMethod("name", ParamTypes...)`.

### Invocation

-   Instance → `method.invoke(instance, args...)`
-   Static → `method.invoke(null, args...)` 
-   Return is `Object` → cast if needed.

### Tips

-   Always match parameter types exactly (`int.class` vs `Integer.class`
    matters).
-   Exceptions are wrapped in `InvocationTargetException` → unwrap
    cause.

### Pitfalls

-   `NoSuchMethodException` → wrong name or wrong signature.
-   `IllegalAccessException` → trying to call a `private` method without
    `setAccessible(true)`.

------------------------------------------------------------------------

## 3) Fields: List, Read, Write (static, private, generics, inheritance, final)

###  Code [Reflect Methods ](https://github.com/hanin-mohamed/SpringLens-Reflection/tree/main/Reflection/src/main/java/com/springlens/reflection/a20_fields)


### Discovering

-   `getDeclaredFields()` → every field declared on that class, any
    visibility.
-   `getFields()` → only `public`, including from superclasses.

### Reading & Writing

-   Instance field: `field.get(obj)` / `field.set(obj, value)`.
-   Static field: pass `null` instead of an instance.
-   Private field: call `setAccessible(true)` first.

### Generics

-   `getType()` → erasure type (e.g., `List`).
-   `getGenericType()` → shows `List<String>` or similar.
-   Cast to `ParameterizedType` to extract the actual arguments.

### Inheritance

-   Declared vs inherited matters:
    -   `getDeclaredFields()` on a child won't show parent's fields.
    -   `getFields()` will show public parent fields.

### Final fields

-   Don't rely on changing them: reflection may appear to "succeed" but
    the JVM can inline constants or ignore the write.
-   Safe assumption: final is **read-only**.

``` mermaid
sequenceDiagram
    participant U as Your Code
    participant R as Reflection API
    participant O as Object
    U->>R: getDeclaredField("count")
    R-->>U: Field handle
    U->>R: setAccessible(true)
    U->>O: field.set(obj, value)
    O-->>U: field value updated
```

------------------------------------------------------------------------

## 4) Constructors: Discover & Instantiate

###  Code [Reflect Methods ](https://github.com/hanin-mohamed/SpringLens-Reflection/tree/main/Reflection/src/main/java/com/springlens/reflection/a30_constructors)


### Discovering

-   `getDeclaredConstructors()` → all cnstructors, any visibility.
-   `getConstructors()` → only public ones.

### Instantiation

-   Call `constructor.newInstance(args...)`.
-   Match argument types exactly.
-   For `private` constructors, use `setAccessible(true)`.

### Inheritance

-   Constructors are **not inherited**.
-   A child must declare its own constructors and call `super(...)`.

### Pitfalls

-   `NoSuchMethodException` → signature mismatch.
-   `IllegalAccessException` → forgot `setAccessible(true)`.
-   `InvocationTargetException` → the constructor itself threw an
    exception.

``` mermaid
flowchart TD
  A[Class<?>] --> B[getDeclaredConstructors]
  B --> C[match signature]
  C --> D[setAccessible(true) if private]
  D --> E[newInstance(args...)]
  E --> F[Fresh object]
```

------------------------------------------------------------------------

## 5) Why Frameworks Love This

Put together: 
1. **Scan classes** at runtime. 
2. **Read metadata**
(annotations).
3. **Pick constructors** and build objects.
4. **Inject dependencies** into fields.
5. **Dispatch calls** by invoking methods dynamically.

That's how Spring wires `@Autowired` beans, how JUnit finds `@Test`
methods, and how Jackson maps JSON keys onto your object fields.

``` mermaid
sequenceDiagram
    participant S as Scanner
    participant M as Metadata
    participant I as Instantiator
    participant W as Injector
    S->>M: discover classes & members
    M-->>I: tell what to build
    I->>W: object created
    W->>App: fields set, ready to run
```


## Final Advice

-   Reflection is powerful, but slower than direct calls. Use it for
    **flexibility**, not for every-day business logic.
-   Always wrap reflective code in clear utilities --- don't spread it
    everywhere.
-   Remember: what you gain is runtime adaptability; what you lose is
    compile-time safety.

> Keep the repo open while you read: run, tweak, break, and fix. That's
> how the ideas become second nature.
