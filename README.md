# JavaEchoServer
This Java Echo Server allows a client to communicate with a server via a socket at a specficied localhost. After the connection is established, the client can send messages to the server and that messages is echoed back from the server.

## Requirements
This utitlizes Java with Gradle to automate building, testing, and running of the application.

## Setup
Once cloned, in the terminal, enter into the application
> `cd EchoServer`

Once in the appropriate directory, build the appication
> `./gradlew build`

## Usage

This is a two step process that will utilize two terminals to function.

### Using the Server
First the server need to start. This is done by running the following gradle command in the terminal.
> `./gradlew run`

This command will automatically default to the localhost 4444. If you need to override this default and select a custom local host, then the following command should be initiated instead.

> `./gradlew run --args[localhost]`

### Starting the Client

In a separate terminal, when the server is running, initiate the following command.
> `nc localhost [localhost]`

In this terminal, the client will then type in messages and press return to send a message to the server that will then be echoed back.
