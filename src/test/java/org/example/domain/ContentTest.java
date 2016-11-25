package org.example.domain;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.text.json.EJson;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.UUID;

public class ContentTest {

  Author rob;

  String contentId;// = UUID.randomUUID();

  Content content;

  @Test
  public void insertRob() throws InterruptedException {

    rob = new Author("rob");
    rob.setAddress(new Address("93 fow", "auckland"));

    rob.save();

    Thread.sleep(2000);
  }

  @Test(dependsOnMethods = "insertRob")
  public void insertContentForRob() throws IOException, InterruptedException {

    content = new Content();
    //content.setId(contentId);
    content.setAuthor(rob);
    content.setSubject("Hello world");
    content.setTitle("DocStore only beans");

    String someJson = "{\"name\":\"roberto\", \"age\":46, \"address\": {\"street\": \"80 banana\",\"city\": \"auckland\"}}";
    content.setContent(EJson.parseObject(someJson));

    String someUnmapped = "{\"alpha\":\"aa\", \"zumo\":true, \"host\": {\"ip\": \"1234\",\"core\": \"JDFS\", \"active\": true}}";
    content.setUnmapped(EJson.parseObject(someUnmapped));

    content.save();

    contentId = content.getId();
    System.out.println("inserted "+contentId);
    Thread.sleep(2000);

  }

  @Test(dependsOnMethods = {"insertRob","insertContentForRob"})
  public void findById() throws InterruptedException {

    Content found = Ebean.find(Content.class)
        //.setId(content.getId())
        //.setId(contentId)
        .where().eq("title", "DocStore only beans")
        //.setDisableLazyLoading(true)
        .findUnique();

    Author author = found.getAuthor();
    author.getName();
    author.getId();
    Address address = author.getAddress();


    author.setName("Not Rob but roberto");
    author.save();

    Thread.sleep(3000);

    Content found2 = Ebean.find(Content.class)
        .setId(found.getId())
        //.setDisableLazyLoading(true)
        .findUnique();

    Author author1 = found2.getAuthor();
    System.out.println("name:" + author1.getName());
  }

//  @Test
//  public void findAll() throws InterruptedException, IOException {
//
//    Content content = new Content();
//    //content.setId(UUID.randomUUID());
//    //content.setAuthor("Rob");
//    content.setSubject("Hello world");
//    content.setTitle("DocStore only beans");
//
//    String someJson = "{\"name\":\"roberto\", \"age\":46, \"address\": {\"line1\": \"hello\",\"city\": \"auckland\"}}";
//    content.setContent(EJson.parseObject(someJson));
//
//
//    content.save();
//
//    Thread.sleep(1000);
//
//    List<Content> list = Ebean.find(Content.class)
//        .where()
//          .jsonEqualTo("content", "address.line1", "hello")
//        .findList();
//
//    for (Content content1 : list) {
//      content1.setTitle("Change title");
//      content1.save();
//    }
//
//    Thread.sleep(1000);
//  }
//
//  @Test
//  public void insertWithId() throws InterruptedException {
//
//    Content content = new Content();
//    content.setId(UUID.randomUUID());
//    //content.setAuthor("Foo");
//    content.setSubject("Hello foo");
//
//    Ebean.save(content);
//
//    Thread.sleep(1000);
//
//    Content found = Ebean.find(Content.class, content.getId());
//    found.setSubject("Not really foo");
//    Ebean.save(found);
//
//    Thread.sleep(1000);
//
//    Ebean.delete(found);
//
//  }
//
//  @Test
//  public void useTransaction() throws InterruptedException {
//
//    Transaction transaction = Ebean.beginTransaction();
//    transaction.setDocStoreBatchSize(7);
//
//    for (int i = 0; i < 10; i++) {
//      Content content = new Content();
//      //content.setAuthor("batch me "+i);
//      content.setSubject("some batch stuff");
//      content.save();
//    }
//
//    Ebean.commitTransaction();
//
//    Thread.sleep(1000);
//
//    List<Content> all = Ebean.find(Content.class)
//        .where().startsWith("author", "batch")
//        .findList();
//
//    System.out.println(all);
//  }

}