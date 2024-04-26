# identity-application-authz-topaz
An authorization service to be integrated to the WSO2 Identity Server using the Topaz authorization service to perform
the authorization. This repo implements the interfaces to interface to interact with the authorization service.

## Topaz
Topaz is an open-source, fine-grained access control service for modern cloud applications. It uses the Open Policy
Agent (OPA) as its decision engine, and provides a built-in directory that implements the Google Zanzibar data model.
The decision engine is called the _Authorizer_ and the directory that implements the Google Zanzibar data model is
called the _Directory_.
It exposes both HTTPS and gRPC REST APIs to interact with the Authorizer and the Directory.

### Authorizer endpoints
1. Is - evaluate a specific policy. This is equivalent to asking "Can User U perform Action A on Resource R"
2. Decisiontree - evaluate a subset of policies. This is equivalent to asking "What actions can User U perform on
   Resource R"
3. Query - the most abstract API for interacting with the Authorizer. It allows the caller to send a general query to
   the Authorizer, along with an input, and returns the output from the Authorizer.

### Directory endpoints
1. Check - check whether a subject has a relation or permission to an object
2. GetObject - get an object by type and identifier
3. GetRelation - get a relation instance between the provided subject and object using the relation type.
4. GetGraph - searches the graph to find all subjects of a given type with a specified relation to a given subject or
   vice versa, all objects of a given type that a specified subject has a given relation to.

## Interfaces
There are four interfaces defined.
1. _ManagementInterface_ - Defines the methods to create, get and delete Directory objects and relations.
2. _DirectoryInterface_ - Defines the _check_ and _graph_ methods to call the Check and getGraph endpoints of the
   Directory respectively.
3. _AuthorizerInterface_ - Defines the _is, query_ and _decisiontree_ endpoints to call the _is, query_ and
   _decisiontree_ endpoints of the Authorizer respectively.
4. DirectoryItemInterface - Defines a common interface for directory relations, directory objects, directory request
   objects

## Objects
It is required to maintain the following representations to execute the requests to the topaz service successfully.
1. DirectoryObject - This class provides the representation of a directory object.
2. DirectoryRelation - This class provides the representation of a directory relation.
3. DirectoryRequestObject - This class provides the representation of the request sent to the Directory to the
   authorization endpoints i.e. check and graph.
4. PolicyContextObject - This class provides the representation of a request sent to the Authorizer to verify by
   evaluating policies