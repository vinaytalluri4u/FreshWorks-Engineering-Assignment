# Freshworks-Engineering-Assignment

A file-based key-value data store that supports the basic CRD (create, read, and delete) operations. This data store is meant to be used as a local storage for one single process on one laptop.
<br>
# Guide on using the Application:

1) The Application is based on REPL(Read, Eval, Print, Loop), an interactive commandline interface.
2) This project built in eclipse, maven project with JUNIT test cases for few  validations.
3) It is a file-based key-value data store that supports the basic CRD (create, read, and delete) operations. This data store is meant to be used as a local storage for one single process on one laptop.
4) The key-value will be stored in (datastore.json) file in specified directory.
5) Run DataStore.java file to interact with applicaion.
6) The project contains two custom build exceptions<br>
-> when key is greater than 32 chars, KeySizeExceededException is raised.<br>
-> when file size greater than 1GB, FileSizeExceededException is raised.
7) It can be initialized using an optional file path. If one is not provided, it will reliably create itself in a reasonable location on the laptop.
8) Every key supports setting a Time-To-Live property when it is created. it will be evaluated as an integer defining the number of seconds the key will be retained in the data store. Once the Time-To-Live for a key has expired, the key will no longer be available for Read or Delete operations.
9) Appropriate error responses are returned to client if it uses the data store in unexpected ways or breaches any limits.
10) Unit test case is developed to validate the key with it's TTL property.
