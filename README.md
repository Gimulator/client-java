# Installation

First you need to clone project into a directory and then you need to install libraries that we are using in java client.

* [org.json]()
* [websocket]()
* [slf4j simple]()
* [slf4j api]()

# Using java client

To connect to gimulator with java client you have to set two environment variables:
* `CLEINT_ID`
* `GIMULATOR_HOST`

Which `CLIENT_ID` is your Identifier in gimulator and `GIMULATOR_HOST` is the address of where gimulator is running.

## Registering to gimulator

To register in gimulator and getting access, create a `Client` object. the client will automatically get your `CLEINT_ID` from environment variables and registers you in gimulator.

```Java
Client client = new ClientImpl(new GimulatorObserver() {
            @Override
            public void onObserved(GimulatorObject observed) {
                //TODO do your observation here.
            }
        });
```

> The gimulator observer will notify you the changes that you want to observe.

## Creating key

Key is using to find objects in gimulator. you need to create Key in order to call this method:

* set
* get
* find
* delete
* watch

You can create a key using `KeyBuilder` class.

```java
Key.KeyBuilder.newKey()
        .setName("name")
        .setNamespace("namespace")
        .setType("type")
        .get();
```

## Set

To set a value to a Key you can call `set` method and pass the right parameters.

```Java
client.set(key,"value");
```

## Get

To get an object from gimulator you can call `get` method in client and pass the key.

```Java
GimulatorObject object = client.get(key);
```

## find

To find an object from objects available in gimulator, you can call `find` method in client and pass the key.

```Java
List<GimulatorObjects> objects = client.find(key);
```

> Note that key in find method can be incomplete and gimulator will return you all objects that you have access and your key matches wih object key.

## delete

In order to delete a key, you can call `delete` method.

```Java
client.delete(key);
```

## Watch

With `watch` method you can observe a key and get changes on it from `GimulatorObserver` interface that you passed to `Client` constructor.

```Java
client.watch(key);
```
