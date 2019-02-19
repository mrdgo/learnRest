package org.mrdgo.messenger.resources.beans;

import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;

public class MessageFilterBean
{
    private @DefaultValue("-1") @QueryParam("year") int year;
    private @DefaultValue("-1") @QueryParam("start") int start;
    private @DefaultValue("-1") @QueryParam("size") int size;
 
 public int getYear()
 {
     return year;
 }
 
 public void setYear(int year)
 {
     this.year = year;
 }
 
 public int getStart()
 {
     return start;
 }
 
 public void setStart(int start)
 {
     this.start = start;
 }
 
 public int getSize()
 {
     return size;
 }
 
 public void setSize(int size)
 {
     this.size = size;
 }
}
