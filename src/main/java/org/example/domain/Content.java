package org.example.domain;

import com.avaje.ebean.annotation.DbJson;
import com.avaje.ebean.annotation.DocEmbedded;
import com.avaje.ebean.annotation.DocSortable;
import com.avaje.ebean.annotation.DocStore;
import com.avaje.ebean.annotation.UnmappedJson;

import javax.persistence.ManyToOne;
import java.util.Map;

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


  public String toString() {
    return "id:"+id+" author:"+author+" title:"+title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Map<String, Object> getContent() {
    return content;
  }

  public void setContent(Map<String, Object> content) {
    this.content = content;
  }

  public Map<String, Object> getUnmapped() {
    return unmapped;
  }

  public void setUnmapped(Map<String, Object> unmapped) {
    this.unmapped = unmapped;
  }
}
