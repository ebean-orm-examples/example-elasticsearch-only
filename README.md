# example-elasticsearch-only
Using Ebean to map to ElasticSearch (without any other DB, insert, update, delete, query etc) 


### Annotate beans with @DocStore (and not @Entity) 

```java

@DocStore
public class Content extends BaseDomain {

  @DocSortable(store = true)
  String title;

  @DocEmbedded(doc = "id, name")
  @ManyToOne
  Author author;

  String subject;

  @DbJson
  Map<String,Object> content;

  /**
   * These are unmapped properties at the top level.
   */
  @UnmappedJson
  Map<String,Object> unmapped;

```

### define properties (test-ebean.properties)

```properties
ebean.docstoreonly=true

ebean.docstore.url=http://127.0.0.1:9200
ebean.docstore.active=true
ebean.docstore.generateMapping=true
ebean.docstore.dropCreate=true
```

### Do stuff, insert, update, delete query etc

```java

    Author rob = new Author("rob");
    rob.setAddress(new Address("93 fow", "auckland"));

    rob.save();

```
